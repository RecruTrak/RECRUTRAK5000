<?php require_once(APPPATH . 'libraries/REST_Controller.php');

	ini_set('session.use_cookies', '0');

	class Api extends REST_Controller {

		public function login_get() {
			$_SESSION = array();
			$user = $this->db->get_where('users', $this->get())->row();
			if (!empty($user)) {
				$this->response(array('success' => true));
			} else {
				$this->response(array('success' => false), 403);
			}
		}

	}

?>