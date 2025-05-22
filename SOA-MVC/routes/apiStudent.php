<?php
require_once __DIR__ . '/../Controllers/StudentController.php';
require_once __DIR__ . '/../Models/Student.php';

header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
header("Content-Type: application/json");

if ($_SERVER['REQUEST_METHOD'] === 'OPTIONS') {
    http_response_code(200);
    exit();
}

$method = $_SERVER['REQUEST_METHOD'];
$controller = new StudentController();

switch ($method) {
case "GET":
    if (isset($_GET['cedula']) && isset($_GET['withSubjects'])) {
        $data = $controller->getSubjects($_GET['cedula']);
        echo json_encode([
            'success' => true,
            'data' => $data
        ]);
    } elseif (isset($_GET['cedula'])) {
        $student = $controller->getByCedula($_GET['cedula']);
        echo json_encode($student
            ? ['success' => true, 'data' => $student]
            : ['success' => false, 'message' => 'Estudiante no encontrado']);
    } else {
        $students = $controller->getAll();
        echo json_encode(['success' => true, 'data' => $students]);
    }
    break;

    case "POST":
        $data = json_decode(file_get_contents('php://input'), true);
        if (isset($data['cedula'], $data['firstName'], $data['lastName'], $data['address'], $data['phone'])) {
            $student = new Student(
                $data['cedula'],
                $data['firstName'],
                $data['lastName'],
                $data['address'],
                $data['phone']
            );
            if ($controller->insert($student)) {
                http_response_code(201);
                echo json_encode([
                    'success' => true,
                    'message' => 'Estudiante creado correctamente'
                ]);
            } else {
                http_response_code(500);
                echo json_encode([
                    'success' => false,
                    'message' => 'Error al crear el estudiante'
                ]);
            }
        } else {
            http_response_code(400);
            echo json_encode([
                'success' => false,
                'message' => 'Parámetros incompletos o inválidos'
            ]);
        }
        break;

    case "PUT":
        $data = json_decode(file_get_contents('php://input'), true);
        if (isset($data['cedula'], $data['firstName'], $data['lastName'], $data['address'], $data['phone'])) {
            $student = new Student(
                $data['cedula'],
                $data['firstName'],
                $data['lastName'],
                $data['address'],
                $data['phone']
            );
            if ($controller->update($student)) {
                echo json_encode([
                    'success' => true,
                    'message' => 'Estudiante actualizado correctamente'
                ]);
            } else {
                http_response_code(404);
                echo json_encode([
                    'success' => false,
                    'message' => 'No se encontró el estudiante para actualizar'
                ]);
            }
        } else {
            http_response_code(400);
            echo json_encode([
                'success' => false,
                'message' => 'Parámetros incompletos o inválidos'
            ]);
        }
        break;

    case "DELETE":
        parse_str(file_get_contents("php://input"), $data);
        $cedula = $data['cedula'] ?? $_GET['cedula'] ?? null;

        if ($cedula) {
            if ($controller->delete($cedula)) {
                echo json_encode([
                    'success' => true,
                    'message' => 'Estudiante eliminado correctamente'
                ]);
            } else {
                http_response_code(404);
                echo json_encode([
                    'success' => false,
                    'message' => 'No se encontró el estudiante para eliminar'
                ]);
            }
        } else {
            http_response_code(400);
            echo json_encode([
                'success' => false,
                'message' => 'Cédula no especificada'
            ]);
        }
        break;

    default:
        http_response_code(405);
        echo json_encode([
            'success' => false,
            'message' => 'Método HTTP no permitido'
        ]);
}
