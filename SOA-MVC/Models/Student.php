<?php
class Student {
    public string $cedula;
    public string $firstname;
    public string $lastname;
    public string $address;
    public string $phone;

    public function __construct(string $cedula, string $firstname, string $lastname, string $address, string $phone) {
        $this->cedula = $cedula;
        $this->firstname = $firstname;
        $this->lastname = $lastname;
        $this->address = $address;
        $this->phone = $phone;
    }
}
?>
