<?php
header('Access-Control-Allow-Origin:*');
header('Access-Control-Allow-Headers: Origin, X-Requested-with, Content-type, Authorization');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');

include "connection.php";

$idEvento = mysqli_real_escape_string($conn, $_POST['ID_EVENTO']); //Obligatorio
$idOrganizadorEspecifico = mysqli_real_escape_string($conn, $_POST['ID_ORGANIZADOR_ESPECIFICO']);

$sentencia = $conn->prepare("UPDATE eventosdetalle_organizadores_especificos_eventos SET id_evento = ?, id_organizador_especifico = ? WHERE id_evento = ?");
$sentencia->bind_param('is', $idEvento, $idOrganizadorEspecifico);

$resultado = $sentencia->execute();

if ($resultado) {
    echo json_encode("Se edito correctamente");
}

$sentencia->close();
$conn->close();
?>