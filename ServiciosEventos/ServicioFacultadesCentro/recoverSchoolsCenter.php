<?php
header('Access-Control-Allow-Origin:*');
header('Access-Control-Allow-Headers: Origin, X-Requested-with, Content-type, Authorization');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');

include 'connection.php';

$idFacultadCentro = mysqli_real_escape_string($conn, $_POST['ID_FACULTAD_CENTRO']);

$sentencia = $conn->prepare("UPDATE facultades_centros SET activo = 1 WHERE id_facultad_centro = ?");
$sentencia->bind_param('s', $idFacultadCentro);
$resultado = $sentencia->execute();

if ($resultado) {
    echo json_encode("Se recupero correctamente");
}

$sentencia->close();
$conn->close();
?>