<?php
header('Access-Control-Allow-Origin:*');
header('Access-Control-Allow-Headers: Origin, X-Requested-with, Content-type, Authorization');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');

include "connection.php";

$idEvento = mysqli_real_escape_string($conn, $_POST['ID_EVENTO']); //Obligatorio
$tipoAsistente = mysqli_real_escape_string($conn, $_POST['TIPO_ASISTENTE']);
$idOpcFacultad = mysqli_real_escape_string($conn, $_POST['ID_OPC_FACULTAD']); 
$idOpcCarrera = mysqli_real_escape_string($conn, $_POST['ID_OPC_CARRERA']); 

$sentencia = $conn->prepare("UPDATE detalle_asistentes_eventos SET id_evento = ?, tipo_asistente = ?, id_opc_facultad = ?, id_opc_carrera = ? WHERE id_evento = ?");
$sentencia->bind_param('isss', $idEvento, $tipoAsistente, $idOpcFacultad, $idOpcCarrera);

$resultado = $sentencia->execute();

if ($resultado) {
    echo json_encode("Se editó correctamente");
}

$sentencia->close();
$conn->close();
?>