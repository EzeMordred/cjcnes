<?php
header('Access-Control-Allow-Origin:*');
header('Access-Control-Allow-Headers: Origin, X-Requested-with, Content-type, Authorization');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');

include "connection.php";

$idEvento = mysqli_real_escape_string($conn, $_POST['ID_EVENTO']); //Obligatorio
$titulo = mysqli_real_escape_string($conn, $_POST['TITULO']);
$subtitulo = mysqli_real_escape_string($conn, $_POST['SUBTITULO']); 
$idCategoria = mysqli_real_escape_string($conn, $_POST['ID_CATEGORIA']); 
$fechaInicio = mysqli_real_escape_string($conn, $_POST['FECHA_INICIO']);
$fechaFin = mysqli_real_escape_string($conn, $_POST['FECHA_FIN']);
$horaInicio = mysqli_real_escape_string($conn, $_POST['HORA_INICIO']);
$horaFin = mysqli_real_escape_string($conn, $_POST['HORA_FIN']);
$idUbicacionGeneral = mysqli_real_escape_string($conn, $_POST['ID_UBICACION_GENERAL']);
$idUbicacionEspecifica = mysqli_real_escape_string($conn, $_POST['ID_UBICACION_ESPECIFICA']);
$aforo = mysqli_real_escape_string($conn, $_POST['AFORO']);
$certificado = mysqli_real_escape_string($conn, $_POST['CERTIFICADO']);
$asistencia = mysqli_real_escape_string($conn, $_POST['ASISTENCIA']);

$sentencia = $conn->prepare("INSERT INTO cursos (id_evento, titulo, subtitulo, id_categoria, fecha_inicio, fecha_fin, hora_inicio, hora_fin, id_ubicacion_general, id_ubicacion_especifica, aforo, certificado, asistencia) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
$sentencia->bind_param('isssssssssiss', $idEvento, $titulo, $subtitulo, $idCategoria, $fechaInicio, $fechaFin, $horaInicio, $horaFin, $idUbicacionGeneral, $idUbicacionEspecifica, $aforo, $certificado, $asistencia);

$resultado = $sentencia->execute();

if ($resultado) {
    echo json_encode("Se guardo correctamente");
}

$sentencia->close();
$conn->close();
?>