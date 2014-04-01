<?php require_once(APPPATH . 'libraries/REST_Controller.php');

	ini_set('session.use_cookies', '0');

	class Api extends REST_Controller {

		public function login_get() {
			$_SESSION = array();
			$user = $this->db->get_where('users', $this->get())->row();
			if (!empty($user)) {
				$this->response(true);
			} else {
				$this->response(false, 403);
			}
		}

		public function students_get() {
			$student = $this->db->get_where('students', $this->get())->row();
			if (!empty($student)) {
				$this->response($student);
			} else {
				$this->response(false, 404);
			}
		}

		public function students_put() {
			if ($this->db->update('students', $this->put(), array('id' => $this->get('id')))) {
				$this->response(true, 202);
			} else {
				$this->response(false, 404);
			}
		}

		public function students_post() {
			if ($this->db->insert('students', $this->post())) {
				$this->response($this->db->insert_id(), 201);
			} else {
				$this->response(false, 403);
			}
		}

	}

?>