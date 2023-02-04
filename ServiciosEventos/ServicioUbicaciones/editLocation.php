<?php
header('Access-Control-Allow-Origin:*');
header('Access-Control-Allow-Headers: Origin, X-Requested-with, Content-type, Authorization');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');

include "connection.php";

$idUbicacion = mysqli_real_escape_string($conn, $_POST['ID_UBICACION']); //Obligatorio
$idFacultadCentro = mysqli_real_escape_string($conn, $_POST['ID_FACULTAD_CENTRO']);
$nombre = mysqli_real_escape_string($conn, $_POST['NOMBRE']); 

$sentencia = $conn->prepare("UPDATE ubicaciones SET id_ubicacion = ?, id_facultad_centro = ?, nombre = ? WHERE id_ubicacion = ?");
$sentencia->bind_param('sss', $idUbicacion, $idFacultadCentro, $nombre);

$resultado = $sentencia->execute();

if ($resultado) {
    echo json_encode("Se edito correctamente");
}

$sentencia->close();
$conn->close();
?>