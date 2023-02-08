<?php
header('Access-Control-Allow-Origin:*');
header('Access-Control-Allow-Headers: Origin, X-Requested-with, Content-type, Authorization');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');

include 'connection.php';

$idCurso = mysqli_real_escape_string($conn, $_POST['ID_CURSO']);

$sentencia = $conn->prepare("UPDATE detalle_organizadores_cursos SET activo = 1 WHERE id_curso = ?");
$sentencia->bind_param('i', $idCurso);
$resultado = $sentencia->execute();

if ($resultado) {
    echo json_encode("Se recupero correctamente");
}

$sentencia->close();
$conn->close();
?>