<?php

class Subject {
    public int $subject_id;
    public string $subject_name;

    public function __construct(int $subject_id, string $subject_name) {
        $this->subject_id = $subject_id;
        $this->subject_name = $subject_name;
    }
}
