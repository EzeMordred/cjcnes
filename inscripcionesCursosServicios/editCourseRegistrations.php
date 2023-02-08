<?php
header('Access-Control-Allow-Origin:*');
header('Access-Control-Allow-Headers: Origin, X-Requested-with, Content-type, Authorization');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');

include "connection.php";

$idCurso = mysqli_real_escape_string($conn, $_POST['ID_CURSO']); //Obligatorio
$idPersona = mysqli_real_escape_string($conn, $_POST['ID_PERSONA']);
$asistencia = mysqli_real_escape_string($conn, $_POST['ASISTENCIA']); 
$calificacion = mysqli_real_escape_string($conn, $_POST['CALIFICACION']); 

$sentencia = $conn->prepare("UPDATE inscripciones_cursos SET id_curso = ?, id_persona = ?, asistencia = ?, calificacion = ? WHERE id_curso = ?");
$sentencia->bind_param('issi', $idCurso, $idPersona, $asistencia, $calificacion);

$resultado = $sentencia->execute();

if ($resultado) {
    echo json_encode("Se edito correctamente");
}

$sentencia->close();
$conn->close();
?>