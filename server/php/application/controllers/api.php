<?php

	require_once(APPPATH . 'libraries/REST_Controller.php');
	ini_set('session.use_cookies', '0');

	class Api extends REST_Controller {

		public function login_get() {
			$_SESSION = array();
			$user = $this->db->get_where('users', $this->get())->row();
			if (!empty($user)) {
				$this->response(true, 200);
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
			unset($student['id']);
			if ($this->db->insert('students', $student)) {
				$this->response($this->db->insert_id(), 201);
			} else {
				$this->response(false, 403);
			}
		}

	}

?>