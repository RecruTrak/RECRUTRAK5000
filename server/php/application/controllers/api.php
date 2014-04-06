<?php

	require_once(APPPATH . 'libraries/REST_Controller.php');
	ini_set('session.use_cookies', '0');

	class Api extends REST_Controller {

		public function studentLogin_get($id, $lastName) {
			$_SESSION = array();
			$student = $this->db->get_where('students',  array('id' => $id, 'lastName' => $lastName))->row();
			$this->response($student);
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
				$request['studentId'] = $student['id'];
				unset($request['student']);
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
				$this->db->insert('requests', $request);
				foreach ($departments as $department) {
					$this->db->insert('studentsDepartments', array('studentId' => $student['id'], 'departmentId' => $department));
				}
				$this->response($this->db->trans_complete());
			} else {
				$request['studentId'] = $studentId;
				$this->response($this->db->insert('students', $request));
			}
		}

	}

?>