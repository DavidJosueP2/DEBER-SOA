<?php
require_once __DIR__ . '/../Controllers/EnrolmentController.php';

header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
header("Content-Type: application/json");

if ($_SERVER['REQUEST_METHOD'] === 'OPTIONS') {
    http_response_code(200);
    exit();
}

$method = $_SERVER['REQUEST_METHOD'];
$controller = new EnrolmentController();

switch ($method) {
    /*case 'GET':
        if (!empty($_GET['id'])) {
            $enrolment = $controller->getById((int)$_GET['id']);
            if ($enrolment) {
                echo json_encode(['success' => true, 'data' => $enrolment]);
            } else {
                echo json_encode(['success' => false, 'message' => 'Inscripción no encontrada']);
            }
        } else {
            $enrolments = $controller->getAll();
            echo json_encode(['success' => true, 'data' => $enrolments]);
        }
        break;*/

    case 'GET':
        $enrolment_id = isset($_GET['id']) ? (int)$_GET['id'] : null;
        $student_ced = $_GET['cedula'] ?? null;
        $subject_id = isset($_GET['subject_id']) ? (int)$_GET['subject_id'] : null;

        $results = $controller->search($enrolment_id, $student_ced, $subject_id);
        echo json_encode([
            'success' => true,
            'data' => $results
        ]);
        break;

    case 'POST':
        $data = json_decode(file_get_contents("php://input"), true);
        $success = $controller->create($data);
        echo json_encode([
            'success' => $success,
            'message' => $success ? 'Inscripción registrada correctamente' : 'Error al registrar inscripción'
        ]);
        break;

    case 'PUT':
        $data = json_decode(file_get_contents("php://input"), true);
        $success = $controller->update($data);
        echo json_encode([
            'success' => $success,
            'message' => $success ? 'Inscripción actualizada correctamente' : 'Error al actualizar inscripción'
        ]);
        break;

    case 'DELETE':
        parse_str(file_get_contents("php://input"), $data);
        $id = isset($data['id']) ? (int)$data['id'] : 0;
        $success = $controller->delete($id);
        echo json_encode([
            'success' => $success,
            'message' => $success ? 'Inscripción eliminada correctamente' : 'Error al eliminar inscripción'
        ]);
        break;

    default:
        echo json_encode(['success' => false, 'message' => 'Método HTTP no permitido']);
}
