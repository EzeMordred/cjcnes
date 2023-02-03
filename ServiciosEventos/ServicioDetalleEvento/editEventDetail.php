<?php
header('Access-Control-Allow-Origin:*');
header('Access-Control-Allow-Headers: Origin, X-Requested-with, Content-type, Authorization');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');

include "connection.php";

$idEvento = mysqli_real_escape_string($conn, $_POST['ID_EVENTO']); //Obligatorio
$idParticipante = mysqli_real_escape_string($conn, $_POST['ID_PARTICIPANTE']);
$temaEspecifico = mysqli_real_escape_string($conn, $_POST['TEMA_ESPECIFICO']); 
$fechaInicio = mysqli_real_escape_string($conn, $_POST['FECHA_INICIO']);
$fechaFin = mysqli_real_escape_string($conn, $_POST['FECHA_FIN']);
$horaInicio = mysqli_real_escape_string($conn, $_POST['HORA_INICIO']);
$horaFin = mysqli_real_escape_string($conn, $_POST['HORA_FIN']);
$modalidad = mysqli_real_escape_string($conn, $_POST['MODALIDAD']);
$enlace = mysqli_real_escape_string($conn, $_POST['ENLACE']);

$sentencia = $conn->prepare("UPDATE detalle_eventos SET id_evento = ?, id_participante = ?, tema_especifico = ?, fecha_inicio = ?, fecha_fin = ?, hora_inicio = ?, hora_fin = ?, modalidad = ?, enlace = ? WHERE id_evento = ?");
$sentencia->bind_param('issssssss', $idEvento, $idParticipante, $temaEspecifico, $fechaInicio, $fechaFin, $horaInicio, $horaFin, $modalidad, $enlace);

$resultado = $sentencia->execute();

if ($resultado) {
    echo json_encode("Se edito correctamente");
}

$sentencia->close();
$conn->close();
?>