<?php
include_once __DIR__ . '/../database/Connexion.php';
require_once __DIR__ . '/../Models/Student.php';

class StudentController {
    private PDO $db;

    public function __construct() {
        $this->db = Connexion::connect();
    }

    public function getAll(): array {
        $stmt = $this->db->query("SELECT * FROM students");
        $students = [];
        while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
            $students[] = new Student(
                $row['cedula'],
                $row['firstname'],
                $row['lastname'],
                $row['address'],
                $row['phone']
            );
        }
        return $students;
    }

    public function getByCedula(string $cedula): ?Student {
        $stmt = $this->db->prepare("SELECT * FROM students WHERE cedula = ?");
        $stmt->execute([$cedula]);
        $row = $stmt->fetch(PDO::FETCH_ASSOC);
        return $row ? new Student(
            $row['cedula'],
            $row['firstname'],
            $row['lastname'],
            $row['address'],
            $row['phone']
        ) : null;
    }

    public function insert(Student $student): bool {
        $stmt = $this->db->prepare(
            "INSERT INTO students (cedula, firstname, lastname, address, phone) VALUES (?, ?, ?, ?, ?)"
        );
        return $stmt->execute([
            $student->cedula,
            $student->firstname,
            $student->lastname,
            $student->address,
            $student->phone
        ]);
    }

    public function update(Student $student): bool {
        $stmt = $this->db->prepare(
            "UPDATE students SET firstname = ?, lastname = ?, address = ?, phone = ? WHERE cedula = ?"
        );
        return $stmt->execute([
            $student->firstname,
            $student->lastname,
            $student->address,
            $student->phone,
            $student->cedula
        ]);
    }

    public function delete(string $cedula): bool {
        $stmt = $this->db->prepare("DELETE FROM students WHERE cedula = ?");
        return $stmt->execute([$cedula]);
    }
    //GET /apiStudent.php?cedula=1234567890&withSubjects=1
    public function getSubjects(string $cedula): array {
        $stmt = $this->db->prepare("
            SELECT s.subject_id, s.subject_name
            FROM enrolments e
            JOIN subjects s ON s.subject_id = e.subject_id
            WHERE e.student_ced = ?
        ");
        $stmt->execute([$cedula]);
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }

}
?>
