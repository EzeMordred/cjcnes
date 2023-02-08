<?php
header('Access-Control-Allow-Origin:*');
header('Access-Control-Allow-Headers: Origin, X-Requested-with, Content-type, Authorization');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');

include "connection.php";

$idCategoria = mysqli_real_escape_string($conn, $_POST['ID_CATEGORIA']); //Obligatorio
$nombre = mysqli_real_escape_string($conn, $_POST['NOMBRE']);

$sentencia = $conn->prepare("UPDATE categorias_eventos SET id_categoria = ?, nombre = ? WHERE id_categoria = ?");
$sentencia->bind_param('ss', $idCategoria, $nombre);

$resultado = $sentencia->execute();

if ($resultado) {
    echo json_encode("Se edito correctamente");
}

$sentencia->close();
$conn->close();
?>