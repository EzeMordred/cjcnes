<?php
header('Access-Control-Allow-Origin:*');
header('Access-Control-Allow-Headers: Origin, X-Requested-with, Content-type, Authorization');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');

include "connection.php";

$idCurso = mysqli_real_escape_string($conn, $_POST['ID_CURSO']); //Obligatorio
$costoGeneral = mysqli_real_escape_string($conn, $_POST['COSTO_GENERAL']);
$costoEstudiante = mysqli_real_escape_string($conn, $_POST['COSTO_ESTUDIANTE']); 
$costoEgresado = mysqli_real_escape_string($conn, $_POST['COSTO_EGRESADO']); 
$costoGraduado = mysqli_real_escape_string($conn, $_POST['COSTO_GRADUADO']);
$costoDocente = mysqli_real_escape_string($conn, $_POST['COSTO_DOCENTE']);
$costoPersonalAdministrativo = mysqli_real_escape_string($conn, $_POST['COSTO_PERSONAL_ADMINISTRATIVO']);

$sentencia = $conn->prepare("INSERT INTO cursos (id_curso, costo_general, costo_estudiante, costo_egresado, costo_graduado, costo_docente, costo_personal_administrativo) VALUES(?, ?, ?, ?, ?, ?, ?)");
$sentencia->bind_param('iiiiiii', $idCurso, $costoGeneral, $costoEstudiante, $costoEgresado, $costoGraduado, $costoDocente, $costoPersonalAdministrativo);

$resultado = $sentencia->execute();

if ($resultado) {
    echo json_encode("Se guardo correctamente");
}

$sentencia->close();
$conn->close();
?>