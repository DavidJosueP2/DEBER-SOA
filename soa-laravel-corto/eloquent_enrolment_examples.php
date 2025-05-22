<?php

use App\Models\Enrolment;

// --------------------
// Ejemplos de métodos Eloquent para obtener registros
// --------------------

// 1. find(): Busca por clave primaria (ID). Devuelve un modelo o null si no encuentra
$enrolment = Enrolment::find(1); // SELECT * FROM enrolments WHERE id = 1;
if ($enrolment) {
    echo $enrolment->status;
}

// 2. findOrFail(): Igual que find(), pero lanza una excepción si no encuentra
try {
    $enrolment = Enrolment::findOrFail(1);
    echo $enrolment->status;
} catch (\Illuminate\Database\Eloquent\ModelNotFoundException $e) {
    echo "Matrícula no encontrada.";
}

// 3. where(): Construye una query con condiciones. Aún no ejecuta nada.
$query = Enrolment::where('status', 'activo');

// 4. first(): Ejecuta la query y devuelve el primer resultado o null
$activeEnrolment = Enrolment::where('status', 'activo')->first();
if ($activeEnrolment) {
    echo $activeEnrolment->student_id;
}

// 5. firstOrFail(): Igual que first(), pero lanza error si no hay resultado
try {
    $activeEnrolment = Enrolment::where('status', 'activo')->firstOrFail();
    echo $activeEnrolment->subject_id;
} catch (\Illuminate\Database\Eloquent\ModelNotFoundException $e) {
    echo "No se encontró ninguna matrícula activa.";
}

// 6. get(): Ejecuta la query y devuelve una colección con todos los resultados
$activos = Enrolment::where('status', 'activo')->get(); // [Enrolment, Enrolment, ...]

// 7. all(): Devuelve todos los registros de la tabla
$todos = Enrolment::all(); // SELECT * FROM enrolments;

// 8. pluck(): Devuelve una colección de una sola columna (o par clave-valor)
$ids = Enrolment::pluck('id'); // [1, 2, 3, ...]
$estadoPorId = Enrolment::pluck('status', 'id'); // [1 => 'activo', 2 => 'cancelado', ...]

// 9. value(): Devuelve un solo valor (columna) del primer registro que coincida
$status = Enrolment::where('id', 1)->value('status'); // 'activo'

?>
