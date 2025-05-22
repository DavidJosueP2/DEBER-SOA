<?php
require_once __DIR__ . '/Models/Connexion.php';
require_once __DIR__ . '/Models/Student.php';
require_once __DIR__ . '/Models/StudentRepository.php';

$repo = new StudentRepository();

$newStudent = new Student("1234567890", "Juan", "Pérez", "Av. Siempre Viva", "0987654321");
$repo->insert($newStudent);

$students = $repo->getAll();
echo ($students[0]->name) ."<br>";

foreach ($students as $student) {
    echo "Cédula: {$student->cedula}, Nombre: {$student->name} {$student->lastname}, Dirección: {$student->address}, Teléfono: {$student->telephone}<br>";
}

$student = $repo->getByCedula("1234567890");
if ($student) {
    echo "Estudiante encontrado: {$student->name} {$student->lastname}<br>";
}

$student->address = "Nueva dirección 456";
$repo->update($student);

//$repo->delete("1234567890");
?>
