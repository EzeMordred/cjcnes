<?php
header('Access-Control-Allow-Origin:*');
header('Access-Control-Allow-Headers: Origin, X-Requested-with, Content-type, Authorization');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');

include 'connection.php';

$idCurso = mysqli_real_escape_string($conn, $_POST['ID_CURSO']);

$sentencia = $conn->prepare("UPDATE detalle_asistentes_cursos SET activo = 0 WHERE id_curso = ?");
$sentencia->bind_param('s', $idCurso);
$resultado = $sentencia->execute();

if ($resultado) {
    echo json_encode("Se elimino correctamente");
}

$sentencia->close();
$conn->close();
?>