<?php
header('Access-Control-Allow-Origin:*');
header('Access-Control-Allow-Headers: Origin, X-Requested-with, Content-type, Authorization');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');

include 'connection.php';

$categoria = mysqli_real_escape_string($conn, $_POST['CATEGORIA']);

$sentencia = $conn -> prepare("SELECT eventos.id_evento, categorias_eventos.nombre AS tipo_evento, eventos.titulo, eventos.subtitulo, eventos.id_ubicacion_general AS ubicacion, eventos.fecha_inicio, eventos.fecha_fin, categorias_eventos.url_imagen
FROM eventos INNER JOIN
 categorias_eventos ON eventos.id_categoria = categorias_eventos.id_categoria
WHERE categorias_eventos.nombre = ?

UNION

SELECT cursos.id_curso AS id_evento, categorias_eventos.nombre AS tipo_evento, cursos.titulo, cursos.subtitulo, cursos.id_ubicacion_general AS ubicacion, MIN(detalle_fechas_horario_cursos.fecha) AS fecha_inicio, 
                         MAX(detalle_fechas_horario_cursos.fecha) AS fecha_fin, categorias_eventos.url_imagen
FROM cursos INNER JOIN
 categorias_eventos ON cursos.id_categoria = categorias_eventos.id_categoria INNER JOIN
 detalle_fechas_horario_cursos ON cursos.id_curso = detalle_fechas_horario_cursos.id_curso
 WHERE categorias_eventos.nombre = ?
GROUP BY cursos.id_curso, cursos.titulo, cursos.subtitulo, cursos.id_ubicacion_general, categorias_eventos.nombre, categorias_eventos.url_imagen

ORDER BY fecha_inicio DESC");
$sentencia -> bind_param('ss', $categoria, $categoria);
$sentencia -> execute();

$resultado = $sentencia -> get_result();
$filas = array();

while ($fila = $resultado->fetch_assoc()) {
    $filas[] = $fila;
}
echo json_encode($filas, JSON_UNESCAPED_UNICODE);

mysqli_free_result($resultado);
$sentencia -> close();
$conn -> close();
?>