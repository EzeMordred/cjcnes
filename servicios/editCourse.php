<?php
header('Access-Control-Allow-Origin:*');
header('Access-Control-Allow-Headers: Origin, X-Requested-with, Content-type, Authorization');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');

include "connection.php";

$idCurso = mysqli_real_escape_string($conn, $_POST['ID_CURSO']); //Obligatorio
$titulo = mysqli_real_escape_string($conn, $_POST['TITULO']);
$subtitulo = mysqli_real_escape_string($conn, $_POST['SUBTITULO']); 
$modalidad = mysqli_real_escape_string($conn, $_POST['MODALIDAD']); 
$tipoCurso = mysqli_real_escape_string($conn, $_POST['TIPO_CURSO']);
$fechaInicioInscripcion = mysqli_real_escape_string($conn, $_POST['FECHA_INICIO_INSCRIPCION']);
$fechaFinInscripcion = mysqli_real_escape_string($conn, $_POST['FECHA_FIN_INSCRIPCION']);
$linkInsciprcion = mysqli_real_escape_string($conn, $_POST['LINK_INSCRIPCION']);
$idPersonaContacto = mysqli_real_escape_string($conn, $_POST['ID_PERSONA_CONTACTO']);

$sentencia = $conn->prepare("UPDATE cursos SET id_curso = ?, titulo = ?, subtitulo = ?, modalidad = ?, tipo_curso = ?, fecha_inicio_inscripcion = ?, fecha_fin_inscripcion = ?, link_inscripcion = ?, id_persona_contacto = ? WHERE id_curso = ?");
$sentencia->bind_param('issssiissi', $idCurso, $titulo, $subtitulo, $modalidad, $tipoCurso, $fechaInicioInscripcion, $fechaFinInscripcion, $linkInsciprcion, $idPersonaContacto, $idCurso);

$resultado = $sentencia->execute();

if ($resultado) {
    echo json_encode("Se edito correctamente");
}

$sentencia->close();
$conn->close();
?>