<?php
header('Access-Control-Allow-Origin:*');
header('Access-Control-Allow-Headers: Origin, X-Requested-with, Content-type, Authorization');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');

include "connection.php";

$idCurso = mysqli_real_escape_string($conn, $_POST['ID_CURSO']); //Obligatorio
$requisitoConocimiento = mysqli_real_escape_string($conn, $_POST['REQUISITO_CONOCIMIENTO']);

$sentencia = $conn->prepare("UPDATE detalle_requisitos_cursos SET id_curso = ?, requisito_conocimiento = ? WHERE id_curso = ?");
$sentencia->bind_param('is', $idCurso, $requisitoConocimiento);

$resultado = $sentencia->execute();

if ($resultado) {
    echo json_encode("Se edito correctamente");
}

$sentencia->close();
$conn->close();
?>