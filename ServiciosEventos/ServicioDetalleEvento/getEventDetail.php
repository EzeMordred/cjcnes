<?php
header('Access-Control-Allow-Origin:*');
header('Access-Control-Allow-Headers: Origin, X-Requested-with, Content-type, Authorization');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');

include 'connection.php';

$idEvento = mysqli_real_escape_string($conn, $_POST['ID_EVENTO']);

$sentencia = $conn -> prepare("SELECT * FROM detalle_eventos WHERE id_evento = ?");
$sentencia -> bind_param('i', $idEvento);
$sentencia -> execute();

$resultado = $sentencia -> get_result();

if ($fila = $resultado -> fetch_assoc()) {
    echo json_encode($fila, JSON_UNESCAPED_UNICODE);
}

mysqli_free_result($resultado);
$sentencia -> close();
$conn -> close();
?>