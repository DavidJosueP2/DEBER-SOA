<?php
// Array asociativo
// json encode, convierte un php a String de json

$arrayAsociativo = array(
    "nombre" => "Maybe",
    "apellido" => "Navarro",
    "edad" => 30,
);

print_r($arrayAsociativo);
echo "<br>";
print_r(gettype($arrayAsociativo));
echo "<br>";
$miJsonArray = json_encode($arrayAsociativo);
echo ($miJsonArray);
echo "<br>";
echo (gettype($miJsonArray));
echo "<br>";

// json decode, convierte un String de json a php
$mijsonPhp = json_decode($miJsonArray);
print_r($mijsonPhp);
echo "<br>";
echo (gettype($mijsonPhp));
echo "<br>";

//---------------------------------------
$array = array(
    "Lunes",
    "Martes",
    "Miercoles",
);

print_r($array);
echo "<br>";
print_r(gettype($array));
echo "<br>";
$miJsonArrayN = json_encode($array);
echo $miJsonArrayN;
echo "<br>";
echo (gettype($miJsonArrayN));
echo "<br>";

$objeto = new stdClass();
$objeto->Nombre = "Dayana";
$objeto->Apellido = "Dominguez";
print_r($objeto);
echo "<br>";
print_r(gettype($objeto));
echo "<br>";

$mijsonObjeto = json_encode($objeto);
echo $mijsonObjeto;
echo "<br>";
echo (gettype($mijsonObjeto));
echo "<br>";


$estudiantes = array(
    array(
        "nombre" => "Maybe",
        "apellido" => "Navarro",
        "edad" => 30,
    ),
    array(
        "nombre" => "Dayana",
        "apellido" => "Dominguez",
        "edad" => 30,
    ),
    array(
        "nombre" => "Luis",
        "apellido" => "Navarro",
        "edad" => 30,
    ),
);

echo "<br><br>";

$estudiantesJson = json_encode($estudiantes); 
echo $estudiantesJson . "<br>";

$estudiantesPhp = json_decode($estudiantesJson);

foreach ($estudiantesPhp as $estudiante) {
    echo $estudiante->nombre . "<br>";
}
echo "<br>";

?>