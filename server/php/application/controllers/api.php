<?php require_once(APPPATH . 'libraries/REST_Controller.php'); include(APPPATH . 'iCal/iCalWriter.php');

ini_set('session.use_cookies', '0');

class Api extends REST_Controller {

	public function studentLogin_get($loginId, $lastName) {
		$student = $this->db->get_where('students',  array('loginId' => $loginId, 'lastName' => $lastName))->row_array();
		if (!empty($student)) {
			$student['requests'] = $this->db->get_where('requests', array('studentId' => $student['id']))->result_array();
			$student['meetings'] = array_map(function($row) {
				return array(
					'id' => $row['id'],
					'date' => $row['date'],
					'startTime' => $row['startTime'],
					'endTime' => $row['endTime'],
					'location' => $row['location'],
					'notes' => $row['notes'],
					'faculty' => array(
						'id' => $row['facultyId'],
						'firstName' => $row['firstName'],
						'lastName' => $row['lastName'],
						'phone' => $row['phone'],
						'email' => $row['email'],
						'availability' => $row['availability'],
						'exemptions' => $row['exemptions']
					)
				);
			}, $this->db->select('faculty.*, meetings.*')->join('faculty', 'faculty.id = meetings.facultyId')->get_where('meetings', array('studentId' => $student['id']))->result_array());
			$student['departments'] = array_map(function($row) {
				return $row['departmentId'];
			}, $this->db->get_where('studentsDepartments', array('studentId' => $student['id']))->result_array());
		}
		$this->response($student);
	}

	public function staffLogin_get($username, $password) {
		$staff = $this->db->get_where('staff',  array('username' => $username, 'password' => $password))->row_array();
		if (!empty($staff)) {
			if (empty($staff['departmentId'])) {
				$staff['requests'] = $this->db->select('students.*, requests.*')->join('students', 'students.id = requests.studentId')->get('requests')->result_array();
				$staff['department'] = array('faculty' => $this->db->get('faculty')->result_array());
			} else {
				$staff['department'] = $this->db->get_where('departments', array('id' => $staff['departmentId']))->row_array();
				$staff['department']['faculty'] = $this->db->get_where('faculty', array('departmentId' => $staff['departmentId']))->result_array();
				$staff['requests'] = $this->db->select('students.*, requests.*')->join('students', 'students.id = studentsDepartments.studentId')->join('requests', 'requests.studentId = students.id')->get_where('studentsDepartments', array('departmentId' => $staff['departmentId']))->result_array();
			}
			$staff['requests'] = array_map(function($row) {
				return array(
					'id' => $row['id'],
					'studentId' => $row['studentId'],
					'visitDate' => $row['visitDate'],
					'guests' => $row['guests'],
					'otherAppointments' => $row['otherAppointments'],
					'genTourInfo' => $row['genTourInfo'],
					'startTime' => $row['startTime'],
					'endTime' => $row['endTime'],
					'student' => array(
						'id' => $row['studentId'],
						'firstName' => $row['firstName'],
						'lastName' => $row['lastName'],
						'gender' => $row['gender'],
						'homePhone' => $row['homePhone'],
						'cellPhone' => $row['cellPhone'],
						'address' => $row['address'],
						'address2' => $row['address2'],
						'city' => $row['city'],
						'state' => $row['state'],
						'country' => $row['country'],
						'zip' => $row['zip'],
						'email' => $row['email'],
						'highSchoolName' => $row['highSchoolName'],
						'highSchoolCity' => $row['highSchoolCity'],
						'highSchoolState' => $row['highSchoolState'],
						'dob' => $row['dob'],
						'yearInSchool' => $row['yearInSchool'],
						'GPA' => $row['GPA'],
						'tookTest' => $row['tookTest'],
						'state' => $row['state'],
						'departments' => array_map(function($row) {
							return $row['departmentId'];
						}, $this->db->get_where('studentsDepartments', array('studentId' => $row['studentId']))->result_array())
					)
				);
			}, $staff['requests']);
			$staff['meetings'] = array_map(function($row) {
				return array(
					'id' => $row['id'],
					'date' => $row['date'],
					'startTime' => $row['startTime'],
					'endTime' => $row['endTime'],
					'location' => $row['location'],
					'notes' => $row['notes'],
					'student' => array(
						'id' => $row['studentId'],
						'firstName' => $row['firstName'],
						'lastName' => $row['lastName'],
						'gender' => $row['gender'],
						'homePhone' => $row['homePhone'],
						'cellPhone' => $row['cellPhone'],
						'address' => $row['address'],
						'address2' => $row['address2'],
						'city' => $row['city'],
						'state' => $row['state'],
						'country' => $row['country'],
						'zip' => $row['zip'],
						'email' => $row['email'],
						'highSchoolName' => $row['highSchoolName'],
						'highSchoolCity' => $row['highSchoolCity'],
						'highSchoolState' => $row['highSchoolState'],
						'dob' => $row['dob'],
						'yearInSchool' => $row['yearInSchool'],
						'GPA' => $row['GPA'],
						'tookTest' => $row['tookTest'],
						'state' => $row['state']
					),
					'faculty' => array(
						'id' => $row['facultyId'],
						'firstName' => $row['facultyFirstName'],
						'lastName' => $row['facultyLastName'],
						'phone' => $row['phone'],
						'email' => $row['facultyEmail'],
						'availability' => $row['availability'],
						'exemptions' => $row['exemptions']
					)
				);
			}, $this->db->select('faculty.*, faculty.firstName AS facultyFirstName, faculty.lastName AS facultyLastName, faculty.email AS facultyEmail, students.*, meetings.*')->join('faculty', 'faculty.id = meetings.facultyId')->join('students', 'students.id = meetings.studentId')->get_where('meetings', array('staffId' => $staff['id']))->result_array());
		}
		$this->response($staff);
	}

	public function facultyLogin_get($username, $password) {
		$faculty = $this->db->get_where('faculty',  array('username' => $username, 'password' => $password))->row_array();
		if (!empty($faculty)) {
			$faculty['deparment'] = $this->db->get_where('departments', array('id' => $faculty['departmentId']))->row_array();
			$faculty['meetings'] = array_map(function($row) {
				return array(
					'id' => $row['id'],
					'date' => $row['date'],
					'startTime' => $row['startTime'],
					'endTime' => $row['endTime'],
					'location' => $row['location'],
					'notes' => $row['notes'],
					'student' => array(
						'id' => $row['studentId'],
						'firstName' => $row['firstName'],
						'lastName' => $row['lastName'],
						'gender' => $row['gender'],
						'homePhone' => $row['homePhone'],
						'cellPhone' => $row['cellPhone'],
						'address' => $row['address'],
						'address2' => $row['address2'],
						'city' => $row['city'],
						'state' => $row['state'],
						'country' => $row['country'],
						'zip' => $row['zip'],
						'email' => $row['email'],
						'highSchoolName' => $row['highSchoolName'],
						'highSchoolCity' => $row['highSchoolCity'],
						'highSchoolState' => $row['highSchoolState'],
						'dob' => $row['dob'],
						'yearInSchool' => $row['yearInSchool'],
						'GPA' => $row['GPA'],
						'tookTest' => $row['tookTest'],
						'state' => $row['state']
					)
				);
			}, $this->db->select('students.*, meetings.*')->join('students', 'students.id = meetings.studentId')->get_where('meetings', array('facultyId' => $faculty['id']))->result_array());
		}
		$this->response($faculty);
	}

	public function requests_post() {
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
		$student = $request['student'];
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
		if ($student['id']) {
			unset($student['requests']);
			unset($student['meetings']);
			$id = $student['id'];
			$this->db->update('students', $student, array('id' => $id));
			$this->db->delete('studentsDepartments', array('studentId' => $id));
		} else {
			unset($student['id']);
			$this->db->insert('students', $student);
			$id = $this->db->insert_id();
			$loginId = (strlen($student['firstName']) % 9 + 1) * 100000 + (strlen($student['lastName']) % 9 + 1) * 10000 + $id;
			$this->db->update('students', array('loginId' => $loginId), array('id' => $id));
		}
		$request['studentId'] = $id;
		$this->db->insert('requests', $request);
		foreach ($departments as $department) {
			$this->db->insert('studentsDepartments', array('studentId' => $id, 'departmentId' => $department));
		}
		if ($this->db->trans_complete()) {
			if (!isset($student['id'])) {
				$this->load->library('email');
				$this->email->from('recrutrak5000@gmail.com');
				$this->email->to($student['email']);
				$this->email->subject('Your RECRUTRAK5000 ID');
				$this->email->message('Welcome to RECRUTRAK5000!' . "\n\n" . 'Your login information:' . "\n\n" . 'ID: ' . $loginId . "\n" . 'Last Name: ' . $student['lastName'] . "\n\n" . 'Thank you for using RECRUTRAK5000.');
				$this->email->send();
			}
			$this->response($id);
		} else {
			$this->response(0);
		}
	}

	public function meetings_post($requestId, $studentId, $facultyId, $staffId) {
		$meeting = $this->post();
		$temp = strToTime($meeting['date']);
		if ($temp === false) {
			unset($meeting['date']);
		} else {
			$meeting['date'] = date('Y-m-d', $temp);
		}
		$temp = strToTime($meeting['startTime']);
		if ($temp === false) {
			unset($meeting['startTime']);
		} else {
			$meeting['startTime'] = date('H:i:s', $temp);
		}
		$temp = strToTime($meeting['endTime']);
		if ($temp === false) {
			unset($meeting['endTime']);
		} else {
			$meeting['endTime'] = date('H:i:s', $temp);
		}
		$meeting['studentId'] = $studentId;
		$meeting['facultyId'] = $facultyId;
		$meeting['staffId'] = $staffId;
		unset($meeting['id']);
		unset($meeting['student']);
		unset($meeting['faculty']);
		unset($meeting['staff']);
		$this->db->trans_start();
		$this->db->delete('requests', array('id' => $requestId));
		$this->db->insert('meetings', $meeting);
		$id = $this->db->insert_id();
		$student = $this->db->get_where('students', array('id' => $studentId))->row_array();
		$faculty = $this->db->select('faculty.*, departments.name')->join('departments', 'departments.id = faculty.departmentId')->get_where('faculty', array('faculty.id' => $facultyId))->row_array();
		if ($this->db->trans_complete()) {
			if (class_exists('iCalWriter')) {
				$start = getdate(strToTime($meeting['date'] . ' ' . $meeting['startTime']));
				$end = getdate(strToTime($meeting['date'] . ' ' . $meeting['endTime']));
				$event = new iCalEvent();
				$event->setStart($start['year'], sprintf('%02d', $start['mon']), sprintf('%02d', $start['mday']), false, true, 'America/Chicago', true, sprintf('%02d', $start['hours']), sprintf('%02d', $start['minutes']), sprintf('%02d', $start['seconds']));
				$event->setEnd($end['year'], sprintf('%02d', $end['mon']), sprintf('%02d', $end['mday']), false, true, 'America/Chicago', true, sprintf('%02d', $end['hours']), sprintf('%02d', $end['minutes']), sprintf('%02d', $end['seconds']));
				$event->setShortDescription('Meeting with Dr. ' . $faculty['lastName']);
				$event->setLongDescription('Location: ' . $meeting['location'] . "\n"  . 'Faculty: Dr. ' . $faculty['firstName'] . ' ' . $faculty['lastName'] . "\n" . 'Email Address: ' . $faculty['email'] . "\n" . 'Phone Number: ' . $faculty['phone']);
				$ics = new iCalWriter();
				$ics->setFileOutput();
				$ics->setFileName(APPPATH . 'cache/Meeting-' . $id . '-Student.ics');
				$ics->start();
				$ics->add($event);
				$ics->end();
			}
			$this->load->library('email');
			$this->email->from('recrutrak5000@gmail.com');
			$this->email->to($student['email']);
			$this->email->subject('Meeting Scheduled');
			$this->email->message('You have been scheduled for a meeting with ' . $faculty['firstName'] . ' ' . $faculty['lastName'] . ' from the ' . $faculty['name'] . ' department.' . "\n\n" . 'Location: ' . $meeting['location'] . "\n" . 'Date: ' . date('D M j, Y', strToTime($meeting['date'])) . "\n" . 'Time: ' . date('g:i A', strToTime($meeting['startTime'])) . "\n\n" . $faculty['firstName'] . ' '. $faculty['lastName'] . "\n" . 'Email Address: ' . $faculty['email'] . "\n" . 'Phone Number: (' . substr($faculty['phone'], 0, 3) . ') ' . substr($faculty['phone'], 3, 3) . '-' . substr($faculty['phone'], 6) . "\n\n" . 'Thank you for using RECRUTRAK5000.');
			$this->email->attach($ics->fileName);
			$this->email->send();
			$this->response($id);
		} else {
			$this->reponse(0);
		}
	}

	public function faculty_put() {
		$faculty = $this->put();
		unset($faculty['department']);
		$this->response($this->db->update('faculty', $faculty, array('id' => $faculty['id'])));
	}

}