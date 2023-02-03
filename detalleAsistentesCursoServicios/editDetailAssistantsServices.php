<?php
header('Access-Control-Allow-Origin:*');
header('Access-Control-Allow-Headers: Origin, X-Requested-with, Content-type, Authorization');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');

include "connection.php";

$idCurso = mysqli_real_escape_string($conn, $_POST['ID_CURSO']); //Obligatorio
$tipoAsistente = mysqli_real_escape_string($conn, $_POST['TIPO_ASISTENTE']);
$idOpcFacultad = mysqli_real_escape_string($conn, $_POST['ID_OPC_FACULTAD']); 
$idOpcCarrera = mysqli_real_escape_string($conn, $_POST['ID_OPC_CARRERA']); 

$sentencia = $conn->prepare("UPDATE detalle_asistentes_cursos SET id_curso = ?, tipo_asistente = ?, id_opc_facultad = ?, id_opc_carrera = ? WHERE id_curso = ?");
$sentencia->bind_param('isss', $idCurso, $tipoAsistente, $idOpcFacultad, $idOpcCarrera);

$resultado = $sentencia->execute();

if ($resultado) {
    echo json_encode("Se edito correctamente");
}

$sentencia->close();
$conn->close();
?>