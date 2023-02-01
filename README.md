# Sistema de Inscripción y Administración de Eventos Académicos

Una aplicación web y móvil para la inscripción y administración de eventos académicos con pagos a través de PayPal que utiliza servicios.

![menu](menu.jpeg)
![menu](movil.jpeg)

## Descripción

La aplicación permite a los usuarios inscribirse y pagar en línea por eventos académicos. Los organizadores de los eventos pueden crear, editar y administrar los detalles del evento a través de un panel de control en línea. Existen varios roles los cuales tienen diferentes características al momento de usar la aplicación. La aplicación utiliza servicios externos para la integración con PayPal para facilitar las transacciones en línea, y un modo de poder subir las capturas transferencias realizadas. También utiliza códigos QR para obtener la información del evento al que se inscribió

## Tecnologías

- React para la aplicación web
- Android Studio para la aplicación móvil
- Servicios externos para integración con PayPal para pagos en línea
- Biblioteca de códigos QR para la lectura de información en eventos

## Características

- Inscripción en línea para eventos académicos
- Pagos en línea a través de PayPal
- Panel de control en línea para los organizadores de eventos
- Lista de asistentes y detalles del evento
- Lectura de códigos QR en eventos para obtener información sobre el evento
- Visualización de certificados de asistencia a eventos previos

## Instalación

Para instalar la aplicación, siga los siguientes pasos:

- Clone este repositorio en su máquina local
- Instale las dependencias necesarias utilizando npm o yarn (para la aplicación web) o Gradle (para la aplicación móvil)
- Configure los servicios externos necesarios para la integración con PayPal
- Instale la biblioteca de códigos QR necesaria para la lectura de información en eventos
- Ejecute la aplicación con el comando "npm start" o "yarn start"

## Estructura de Archivos
El proyecto está estructurado de la siguiente manera:
- `src`: Contiene todos los archivos fuente de la aplicación
- `components`: Contiene los componentes reutilizables de la aplicación
- `pages`: Contiene las páginas de la aplicación

## Uso de Servicios
La aplicación utiliza servicios externos para el manejo de pagos por Paypal. Asegúrese de tener las credenciales necesarias para acceder a estos servicios.

## Uso

1. Regístrese o inicie sesión en la aplicación
2. Navegue a la sección de eventos y seleccione el evento al que desea asistir
3. Complete la información de inscripción y realice el pago a través de PayPal o mediante las capturas de la transferencia
4. Recibirá un correo electrónico de confirmación de su inscripción
5. En el evento, escanee el código QR para obtener información sobre el mismo

## Contribución

Si desea contribuir al desarrollo de esta aplicación, por favor, abra una solicitud de extracción en el repositorio.

## Licencia

El código fuente de esta aplicación está disponible bajo la licencia de la Universidad Técnica de Ambato.
