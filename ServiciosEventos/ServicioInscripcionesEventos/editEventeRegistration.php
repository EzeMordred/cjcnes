<?php
header('Access-Control-Allow-Origin:*');
header('Access-Control-Allow-Headers: Origin, X-Requested-with, Content-type, Authorization');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');

include "connection.php";

$idEvento = mysqli_real_escape_string($conn, $_POST['ID_EVENTO']); //Obligatorio
$idPersona = mysqli_real_escape_string($conn, $_POST['ID_PERSONA']);
$asistencia = mysqli_real_escape_string($conn, $_POST['ASISTENCIA']); 

$sentencia = $conn->prepare("UPDATE inscripciones_eventos SET id_evento = ?, id_persona = ?, asistencia = ? WHERE id_evento = ?");
$sentencia->bind_param('iss', $idEvento, $idPersona, $asistencia);

$resultado = $sentencia->execute();

if ($resultado) {
    echo json_encode("Se edito correctamente");
}

$sentencia->close();
$conn->close();
?>