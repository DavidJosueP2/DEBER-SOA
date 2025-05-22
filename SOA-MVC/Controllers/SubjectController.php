<?php
require_once __DIR__ . '/../database/Connexion.php';
require_once __DIR__ . '/../Models/Subject.php';

class SubjectController {
    private PDO $db;

    public function __construct() {
        $this->db = Connexion::connect();
    }

    public function getAll(): array {
        $stmt = $this->db->query("SELECT * FROM subjects");
        $subjects = [];

        while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
            $subjects[] = new Subject($row['subject_id'], $row['subject_name']);
        }

        return $subjects;
    }

    public function getById(int $id): ?Subject {
        $stmt = $this->db->prepare("SELECT * FROM subjects WHERE subject_id = ?");
        $stmt->execute([$id]);

        $row = $stmt->fetch(PDO::FETCH_ASSOC);
        return $row ? new Subject($row['subject_id'], $row['subject_name']) : null;
    }

    public function create(array $data): bool {
        if (empty($data['subject_name'])) return false;

        $stmt = $this->db->prepare("INSERT INTO subjects (subject_name) VALUES (?)");
        return $stmt->execute([$data['subject_name']]);
    }

    public function update(array $data): bool {
        if (empty($data['subject_id']) || empty($data['subject_name'])) return false;

        $stmt = $this->db->prepare("UPDATE subjects SET subject_name = ? WHERE subject_id = ?");
        return $stmt->execute([$data['subject_name'], $data['subject_id']]);
    }

    public function delete(int $id): bool {
        $stmt = $this->db->prepare("DELETE FROM subjects WHERE subject_id = ?");
        return $stmt->execute([$id]);
    }
//GET /apiSubject.php?id=3&withStudents=1
    public function getStudents(int $subject_id): array {
        $stmt = $this->db->prepare("
            SELECT st.cedula, st.firstName, st.lastName, st.address, st.phone
            FROM enrolments e
            JOIN students st ON st.cedula = e.student_ced
            WHERE e.subject_id = ?
        ");
        $stmt->execute([$subject_id]);
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }

}
