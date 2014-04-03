<?php

	require_once(APPPATH . 'libraries/REST_Controller.php');
	ini_set('session.use_cookies', '0');

	class Api extends REST_Controller {

		public function student_login_get($id, $lastName) {
			$_SESSION = array();
			$student = $this->db->get_where('students', array('id' => $id, 'lastName' => $lastName))->row();
			if (!empty($student)) {
				$this->response($student, 200);
			} else {
				$this->response(false, 403);
			}
		}

		public function students_get($id) {
			$student = $this->db->get_where('students', array('id' => $id))->row();
			if (!empty($student)) {
				$this->response($student, 200);
			} else {
				$this->response(false, 404);
			}
		}

		public function students_put($id) {
			$student = $this->put();
			unset($student['id']);
			if ($this->db->update('students', $student, array('id' => $id))) {
				$this->response(true, 202);
			} else {
				$this->response(false, 404);
			}
		}

		public function students_post() {
			$student = $this->post();
			if ($this->db->insert('students', $student)) {
				$this->response(true, 201);
			} else {
				$this->response(false, 403);
			}
		}

	}

?>