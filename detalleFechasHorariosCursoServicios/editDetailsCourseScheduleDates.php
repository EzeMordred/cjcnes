<?php
header('Access-Control-Allow-Origin:*');
header('Access-Control-Allow-Headers: Origin, X-Requested-with, Content-type, Authorization');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');

include "connection.php";

$idCurso = mysqli_real_escape_string($conn, $_POST['ID_CURSO']); //Obligatorio
$fecha = mysqli_real_escape_string($conn, $_POST['FECHA']);
$horaInicio = mysqli_real_escape_string($conn, $_POST['HORA_INICIO']); 
$horaFin = mysqli_real_escape_string($conn, $_POST['HORA_FIN']); 

$sentencia = $conn->prepare("UPDATE detalle_fechas_horario_cursos SET id_curso = ?, fecha = ?, hora_inicio = ?, hora_fin = ? WHERE id_curso = ?");
$sentencia->bind_param('iiii', $idCurso, $fecha, $horaInicio, $horaFin);

$resultado = $sentencia->execute();

if ($resultado) {
    echo json_encode("Se edito correctamente");
}

$sentencia->close();
$conn->close();
?>