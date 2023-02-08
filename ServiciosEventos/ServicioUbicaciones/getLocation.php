<?php
header('Access-Control-Allow-Origin:*');
header('Access-Control-Allow-Headers: Origin, X-Requested-with, Content-type, Authorization');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');

include 'connection.php';

$idUbicacion = mysqli_real_escape_string($conn, $_POST['ID_UBICACION']);

$sentencia = $conn -> prepare("SELECT * FROM ubicaciones WHERE id_ubicacion = ?");
$sentencia -> bind_param('s', $idUbicacion);
$sentencia -> execute();

$resultado = $sentencia -> get_result();

if ($fila = $resultado -> fetch_assoc()) {
    echo json_encode($fila, JSON_UNESCAPED_UNICODE);
}

mysqli_free_result($resultado);
$sentencia -> close();
$conn -> close();
?>