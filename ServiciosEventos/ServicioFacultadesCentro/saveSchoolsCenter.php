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

$sentencia = $conn->prepare("INSERT INTO facultades_centros (id_evento, nombre, email, telefono, direccion) VALUES(?, ?, ?, ?, ?)");
$sentencia->bind_param('sssss', $idFacultadCentro, $nombre, $email, $telefono, $direccion);

$resultado = $sentencia->execute();

if ($resultado) {
    echo json_encode("Se guardo correctamente");
}

$sentencia->close();
$conn->close();
?>