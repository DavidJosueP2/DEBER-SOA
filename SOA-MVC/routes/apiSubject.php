<?php
require_once __DIR__ . '/../Controllers/SubjectController.php';

header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
header("Content-Type: application/json");

if ($_SERVER['REQUEST_METHOD'] === 'OPTIONS') {
    http_response_code(200);
    exit();
}

$method = $_SERVER['REQUEST_METHOD'];
$controller = new SubjectController();

switch ($method) {
    case "GET":
        if (isset($_GET['id']) && isset($_GET['withStudents'])) {
            $data = $controller->getStudents((int)$_GET['id']);
            echo json_encode([
                'success' => true,
                'data' => $data
            ]);
        } elseif (isset($_GET['id'])) {
            $subject = $controller->getById((int)$_GET['id']);
            echo json_encode($subject
                ? ['success' => true, 'data' => $subject]
                : ['success' => false, 'message' => 'Materia no encontrada']);
        } else {
            $subjects = $controller->getAll();
            echo json_encode(['success' => true, 'data' => $subjects]);
        }
        break;

    case 'POST':
        $success = $controller->create($_POST);
        echo json_encode([
            'success' => $success,
            'message' => $success ? 'Materia creada' : 'Fallo al crear materia'
        ]);
        break;

    case 'PUT':
        parse_str(file_get_contents("php://input"), $put_vars);
        $success = $controller->update($put_vars);
        echo json_encode([
            'success' => $success,
            'message' => $success ? 'Materia actualizada' : 'Fallo al actualizar materia'
        ]);
        break;

    case 'DELETE':
        parse_str(file_get_contents("php://input"), $delete_vars);
        $id = isset($delete_vars['id']) ? (int)$delete_vars['id'] : 0;
        $success = $controller->delete($id);
        echo json_encode([
            'success' => $success,
            'message' => $success ? 'Materia eliminada' : 'Fallo al eliminar materia'
        ]);
        break;

    default:
        echo json_encode([
            'success' => false,
            'message' => 'Método no permitido'
        ]);
}
