<?php
require_once __DIR__ . '/../database/Connexion.php';
require_once __DIR__ . '/../Models/Enrolment.php';

class EnrolmentController {
    private PDO $db;

    public function __construct() {
        $this->db = Connexion::connect();
    }

    public function getAll(): array {
        $stmt = $this->db->query("SELECT * FROM enrolments");
        $enrolments = [];

        while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
            $enrolments[] = new Enrolment($row['enrolment_id'], $row['subject_id'], $row['student_ced']);
        }

        return $enrolments;
    }

    public function getById(int $id): ?Enrolment {
        $stmt = $this->db->prepare("SELECT * FROM enrolments WHERE enrolment_id = ?");
        $stmt->execute([$id]);

        $row = $stmt->fetch(PDO::FETCH_ASSOC);
        return $row ? new Enrolment($row['enrolment_id'], $row['subject_id'], $row['student_ced']) : null;
    }

    public function create(array $data): bool {
        if (empty($data['subject_id']) || empty($data['student_ced'])) return false;

        $stmt = $this->db->prepare("INSERT INTO enrolments (subject_id, student_ced) VALUES (?, ?)");
        return $stmt->execute([$data['subject_id'], $data['student_ced']]);
    }

    public function update(array $data): bool {
        if (empty($data['enrolment_id']) || empty($data['subject_id']) || empty($data['student_ced'])) return false;

        $stmt = $this->db->prepare("UPDATE enrolments SET subject_id = ?, student_ced = ? WHERE enrolment_id = ?");
        return $stmt->execute([$data['subject_id'], $data['student_ced'], $data['enrolment_id']]);
    }

    public function delete(int $id): bool {
        $stmt = $this->db->prepare("DELETE FROM enrolments WHERE enrolment_id = ?");
        return $stmt->execute([$id]);
    }

    //GET /apiEnrolment.php
    //GET /apiEnrolment.php?cedula=1234567890
    //GET /apiEnrolment.php?subject_id=3
    //GET /apiEnrolment.php?id=12&cedula=1234567890&subject_id=3

public function search(?int $enrolment_id = null, ?string $student_ced = null, ?int $subject_id = null): array {
    $sql = "
        SELECT 
            e.enrolment_id,
            s.subject_id,
            s.subject_name,
            st.cedula,
            st.firstName,
            st.lastName,
            st.address,
            st.phone
        FROM enrolments e
        JOIN subjects s ON e.subject_id = s.subject_id
        JOIN students st ON e.student_ced = st.cedula
        WHERE 1 = 1
    ";
    
    $params = [];

    if ($enrolment_id !== null) {
        $sql .= " AND e.enrolment_id = ?";
        $params[] = $enrolment_id;
    }

    if ($student_ced !== null) {
        $sql .= " AND e.student_ced = ?";
        $params[] = $student_ced;
    }

    if ($subject_id !== null) {
        $sql .= " AND e.subject_id = ?";
        $params[] = $subject_id;
    }

    $stmt = $this->db->prepare($sql);
    $stmt->execute($params);

    $results = [];
    while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
        $results[] = [
            'enrolment_id' => $row['enrolment_id'],
            'subject' => [
                'subject_id' => $row['subject_id'],
                'subject_name' => $row['subject_name']
            ],
            'student' => [
                'cedula' => $row['cedula'],
                'firstName' => $row['firstName'],
                'lastName' => $row['lastName'],
                'address' => $row['address'],
                'phone' => $row['phone']
            ]
        ];
    }

    return $results;
}


}
