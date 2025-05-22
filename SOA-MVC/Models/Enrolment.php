<?php

class Enrolment {
    public int $enrolment_id;
    public int $subject_id;
    public string $student_ced;

    public function __construct(int $enrolment_id, int $subject_id, string $student_ced) {
        $this->enrolment_id = $enrolment_id;
        $this->subject_id = $subject_id;
        $this->student_ced = $student_ced;
    }
}
