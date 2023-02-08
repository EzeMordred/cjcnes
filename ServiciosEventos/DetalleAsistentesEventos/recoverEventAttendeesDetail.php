<?php
header('Access-Control-Allow-Origin:*');
header('Access-Control-Allow-Headers: Origin, X-Requested-with, Content-type, Authorization');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');

include 'connection.php';

$idEvento = mysqli_real_escape_string($conn, $_POST['ID_EVENTO']);

$sentencia = $conn->prepare("UPDATE detalle_asistentes_eventos SET activo = 1 WHERE id_evento = ?");
$sentencia->bind_param('i', $idEvento);
$resultado = $sentencia->execute();

if ($resultado) {
    echo json_encode("Se recupero correctamente");
}

$sentencia->close();
$conn->close();
?>