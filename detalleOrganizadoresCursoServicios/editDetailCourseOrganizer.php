<?php
header('Access-Control-Allow-Origin:*');
header('Access-Control-Allow-Headers: Origin, X-Requested-with, Content-type, Authorization');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');

include "connection.php";

$idCurso = mysqli_real_escape_string($conn, $_POST['ID_CURSO']); //Obligatorio
$idOrganizador = mysqli_real_escape_string($conn, $_POST['ID_ORGANIZADOR']);

$sentencia = $conn->prepare("UPDATE detalle_organizadores_cursos SET id_curso = ?, id_organizador = ? WHERE id_curso = ?");
$sentencia->bind_param('ii', $idCurso, $idOrganizador);

$resultado = $sentencia->execute();

if ($resultado) {
    echo json_encode("Se edito correctamente");
}

$sentencia->close();
$conn->close();
?>