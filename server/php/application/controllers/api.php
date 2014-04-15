<?php require_once(APPPATH . 'libraries/REST_Controller.php');

ini_set('session.use_cookies', '0');

class Api extends REST_Controller {

	public function studentLogin_get($id, $lastName) {
		$student = $this->db->get_where('students',  array('id' => $id, 'lastName' => $lastName))->row_array();
		if (!empty($student)) {
			$student['requests'] = $this->db->get_where('requests', array('studentId' => $id))->result_array();
		}
		$this->response($student);
	}

	public function staffLogin_get($username, $password) {
		$staff = $this->db->get_where('staff',  array('username' => $username, 'password' => $password))->row_array();
		if (!empty($staff)) {
			if (empty($staff['departmentId'])) {
				$staff['requests'] = $this->db->get('requests');
			} else {
				$staff['deparment'] = $this->db->get_where('departments', array('departmentId' => $staff['departmentId']))->row_array();
				$staff['requests'] = $this->db->select('requests.*')->join('requests', 'requests.studentId = studentsDepartments.studentId')->get_where('studentsDepartments', array('departmentId' => $staff['departmentId']))->result_array();
			}
			$staff['meetings'] = $this->db->get_where('meetings', array('staffId' => $staff['id']));
		}
		$this->response($staff);
	}

	public function facultyLogin_get($username, $password) {
		$faculty = $this->db->get_where('faculty',  array('username' => $username, 'password' => $password))->row_array();
		if (!empty($staff)) {
			$faculty['deparment'] = $this->db->get_where('departments', array('departmentId' => $faculty['departmentId']))->row_array();
			$faculty['meetings'] = $this->db->get_where('meetings', array('facultyId' => $faculty['id']))->result_array();
		}
		$this->response($faculty);
	}

	public function requests_post($studentId = null) {
		$request = $this->post();
		$temp = strToTime($request['visitDate']);
		if ($temp === false) {
			unset($request['visitDate']);
		} else {
			$request['visitDate'] = date('Y-m-d', $temp);
		}
		$temp = strToTime($request['startTime']);
		if ($temp === false) {
			unset($request['startTime']);
		} else {
			$request['startTime'] = date('H:i:s', $temp);
		}
		$temp = strToTime($request['endTime']);
		if ($temp === false) {
			unset($request['endTime']);
		} else {
			$request['endTime'] = date('H:i:s', $temp);
		}
		if (empty($studentId)) {
			$student = $request['student'];
			unset($request['student']);
			unset($student['id']);
			$temp = strToTime($student['dob']);
			if ($temp === false) {
				unset($student['dob']);
			} else {
				$student['dob'] = date('Y-m-d', $temp);
			}
			$departments = $student['departments'];
			unset($student['departments']);
			$this->db->trans_start();
			$this->db->insert('students', $student);
			$request['studentId'] = $this->db->insert_id();
			$this->db->insert('requests', $request);
			foreach ($departments as $department) {
				$this->db->insert('studentsDepartments', array('studentId' => $request['studentId'], 'departmentId' => $department));
			}
			if ($this->db->trans_complete()) {
				$this->response($request['studentId']);
				/*$this->load->library('email');
				$this->email->from('mac.gamer@gmail.com');
				$this->email->to($student['email']);
				$this->email->subject('Your RECRUTRAK5000 ID');
				$this->email->message('Your ID is: ' . $request['studentId']);
				$this->email->send();*/
			} else {
				$this->response(0);
			}
		} else {
			$request['studentId'] = $studentId;
			$this->response($this->db->insert('students', $request));
		}
	}

}