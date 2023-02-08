<?php
header('Access-Control-Allow-Origin:*');
header('Access-Control-Allow-Headers: Origin, X-Requested-with, Content-type, Authorization');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');

include 'connection.php';

$SqlSelect = "SELECT * FROM facultades_centros WHERE activo = 1";
$respuesta = $conn -> query($SqlSelect);
$result = array();

if($respuesta -> num_rows > 0){
    while($filas = $respuesta -> fetch_assoc()){
        array_push($result, $filas);
    }
}else{
    $result = "No existen registros";
}

echo json_encode($result);
$conn -> close();
?>