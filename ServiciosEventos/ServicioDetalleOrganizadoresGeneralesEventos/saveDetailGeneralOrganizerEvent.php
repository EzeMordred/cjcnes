<?php
header('Access-Control-Allow-Origin:*');
header('Access-Control-Allow-Headers: Origin, X-Requested-with, Content-type, Authorization');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');

include "connection.php";

$idEvento = mysqli_real_escape_string($conn, $_POST['ID_EVENTO']); //Obligatorio
$idOrganizadorGeneral = mysqli_real_escape_string($conn, $_POST['ID_ORGANIZADOR_GENERAL']);

$sentencia = $conn->prepare("INSERT INTO detalle_organizadores_generales_eventos (id_evento, id_organizador_general) VALUES(?, ?)");
$sentencia->bind_param('is', $idEvento, $idOrganizadorGeneral);

$resultado = $sentencia->execute();

if ($resultado) {
    echo json_encode("Se guardo correctamente");
}

$sentencia->close();
$conn->close();
?>