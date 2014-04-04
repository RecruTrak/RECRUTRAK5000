<?php

	require_once(APPPATH . 'libraries/REST_Controller.php');
	ini_set('session.use_cookies', '0');

	class Api extends REST_Controller {

		public function studentLogin_get($id, $lastName) {
			$_SESSION = array();
			$student = $this->db->get_where('students',  array('id' => $id, 'lastName' => $lastName))->row();
			$this->response($student);
		}

		public function requests_post($studentId) {
			$request = $this->post();
			unset($request['id']);
			$request['studentId'] = $studentId;
			$this->response($this->db->insert('requests', $request));
		}

	}

?>