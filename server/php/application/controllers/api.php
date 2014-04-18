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
				$staff['requests'] = $this->db->get('requests')->result_array();
				$staff['department'] = array('faculty' => $this->db->get('faculty')->result_array());
			} else {
				$staff['department'] = $this->db->get_where('departments', array('id' => $staff['departmentId']))->row_array();
				$staff['department']['faculty'] = $this->db->get_where('faculty', array('departmentId' => $staff['departmentId']))->result_array();
				$staff['requests'] = $this->db->select('students.*, requests.*')->join('students', 'students.id = studentsDepartments.studentId')->join('requests', 'requests.studentId = students.id')->get_where('studentsDepartments', array('departmentId' => $staff['departmentId']))->result_array();
				foreach ($staff['requests'] as &$request) {
					$request = array(
						'id' => $request['id'],
						'studentId' => $request['studentId'],
						'visitDate' => $request['visitDate'],
						'guests' => $request['guests'],
						'otherAppointments' => $request['otherAppointments'],
						'genTourInfo' => $request['genTourInfo'],
						'startTime' => $request['startTime'],
						'endTime' => $request['endTime'],
						'student' => array(
							'id' => $request['studentId'],
							'firstName' => $request['firstName'],
							'lastName' => $request['lastName'],
							'gender' => $request['gender'],
							'homePhone' => $request['homePhone'],
							'cellPhone' => $request['cellPhone'],
							'address' => $request['address'],
							'address2' => $request['address2'],
							'city' => $request['city'],
							'state' => $request['state'],
							'country' => $request['country'],
							'zip' => $request['zip'],
							'email' => $request['email'],
							'highSchoolName' => $request['highSchoolName'],
							'highSchoolCity' => $request['highSchoolCity'],
							'highSchoolState' => $request['highSchoolState'],
							'dob' => $request['dob'],
							'yearInSchool' => $request['yearInSchool'],
							'GPA' => $request['GPA'],
							'tookTest' => $request['tookTest'],
							'state' => $request['state']
						)
					);
				}
			}
			$staff['meetings'] = $this->db->get_where('meetings', array('staffId' => $staff['id']))->result_array();
		}
		$this->response($staff);
	}

	public function facultyLogin_get($username, $password) {
		$faculty = $this->db->get_where('faculty',  array('username' => $username, 'password' => $password))->row_array();
		if (!empty($staff)) {
			$faculty['deparment'] = $this->db->get_where('departments', array('id' => $faculty['departmentId']))->row_array();
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
			$insertId = $this->db->insert_id();
			$id = (strlen($student['firstName']) % 9 + 1) * 100000 + (strlen($student['lastName']) % 9 + 1) * 10000 + $insertId;
			$this->db->update('students', array('id' => $id), array('id' => $insertId));
			$request['studentId'] = $id;
			$this->db->insert('requests', $request);
			foreach ($departments as $department) {
				$this->db->insert('studentsDepartments', array('studentId' => $id, 'departmentId' => $department));
			}
			if ($this->db->trans_complete()) {
				$this->load->library('email');
				$this->email->from('recrutrak5000@gmail.com');
				$this->email->to($student['email']);
				$this->email->subject('Your RECRUTRAK5000 ID');
				$this->email->message('Welcome to RECRUTRAK5000!' . "\n\n" . 'Your login information:' . "\n\n" . 'ID: ' . $id . "\n" . 'Last Name: ' . $student['lastName'] . "\n\n" . 'Thank you for using RECRUTRAK5000.');
				$this->email->send();
				$this->response($id);
			} else {
				$this->response(0);
			}
		} else {
			$request['studentId'] = $studentId;
			$this->response($this->db->insert('students', $request));
		}
	}

}