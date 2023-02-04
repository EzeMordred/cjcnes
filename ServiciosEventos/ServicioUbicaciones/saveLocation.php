<?php
header('Access-Control-Allow-Origin:*');
header('Access-Control-Allow-Headers: Origin, X-Requested-with, Content-type, Authorization');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');

include "connection.php";

$idUbicacion = mysqli_real_escape_string($conn, $_POST['ID_UBICACION']); //Obligatorio
$idFacultadCentro = mysqli_real_escape_string($conn, $_POST['ID_FACULTAD_CENTRO']);
$nombre = mysqli_real_escape_string($conn, $_POST['NOMBRE']); 


$sentencia = $conn->prepare("INSERT INTO ubicaciones (id_ubicacion, id_facultad_centro, nombre) VALUES(?, ?, ?)");
$sentencia->bind_param('sss', $idUbicacion, $idFacultadCentro, $nombre);

$resultado = $sentencia->execute();

if ($resultado) {
    echo json_encode("Se guardo correctamente");
}

$sentencia->close();
$conn->close();
?>