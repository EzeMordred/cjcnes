<?php
header('Access-Control-Allow-Origin:*');
header('Access-Control-Allow-Headers: Origin, X-Requested-with, Content-type, Authorization');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');

include 'connection.php';

$idUbicacion = mysqli_real_escape_string($conn, $_POST['ID_UBICACION']);

$sentencia = $conn->prepare("UPDATE ubicaciones SET activo = 0 WHERE id_ubicacion = ?");
$sentencia->bind_param('s', $idUbicacion);
$resultado = $sentencia->execute();

if ($resultado) {
    echo json_encode("Se elimino correctamente");
}

$sentencia->close();
$conn->close();
?>