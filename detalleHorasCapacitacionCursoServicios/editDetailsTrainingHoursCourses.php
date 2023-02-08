<?php
header('Access-Control-Allow-Origin:*');
header('Access-Control-Allow-Headers: Origin, X-Requested-with, Content-type, Authorization');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');

include "connection.php";

$idCurso = mysqli_real_escape_string($conn, $_POST['ID_CURSO']); //Obligatorio
$horasPresenciales = mysqli_real_escape_string($conn, $_POST['HORAS_PRESENCIALES']);
$horasAutonomas = mysqli_real_escape_string($conn, $_POST['HORAS_AUTONOMAS']); 
$horasVirtuales = mysqli_real_escape_string($conn, $_POST['HORAS_VIRTUALES']); 

$sentencia = $conn->prepare("UPDATE detalle_horas_capacitacion_cursos SET id_curso = ?, horas_presenciales = ?, horas_autonomas = ?, horas_virtuales = ? WHERE id_curso = ?");
$sentencia->bind_param('iiii', $idCurso, $horasPresenciales, $horasAutonomas, $horasVirtuales);

$resultado = $sentencia->execute();

if ($resultado) {
    echo json_encode("Se edito correctamente");
}

$sentencia->close();
$conn->close();
?>