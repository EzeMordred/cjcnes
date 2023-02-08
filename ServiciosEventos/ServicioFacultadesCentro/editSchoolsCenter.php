<?php
header('Access-Control-Allow-Origin:*');
header('Access-Control-Allow-Headers: Origin, X-Requested-with, Content-type, Authorization');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');

include "connection.php";

$idFacultadCentro = mysqli_real_escape_string($conn, $_POST['ID_FACULTAD_CENTRO']); //Obligatorio
$nombre = mysqli_real_escape_string($conn, $_POST['NOMBRE']);
$email = mysqli_real_escape_string($conn, $_POST['EMAIL']); 
$telefono = mysqli_real_escape_string($conn, $_POST['TELEFONO']); 
$direccion = mysqli_real_escape_string($conn, $_POST['DIRECCION']);

$sentencia = $conn->prepare("UPDATE facultades_centros SET id_facultad_centro = ?, nombre = ?, email = ?, telefono = ?, direccion = ? WHERE id_facultad_centro = ?");
$sentencia->bind_param('sssss', $idFacultadCentro, $nombre, $email, $telefono, $direccion);

$resultado = $sentencia->execute();

if ($resultado) {
    echo json_encode("Se edito correctamente");
}

$sentencia->close();
$conn->close();
?>