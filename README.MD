# Proyecto Integrador Portfolio Web Full Stack ED3

## Diseño:
El proyecto tiene arquitectura distribuida y ésta es la interfaz del backend, la cual via un servicio API Rest suministra sus datos.

Los datos se encuentran alojados en una base de datos desplegada en otro servicio web, en un DB SERVER.

Existe una interfaz FrontEnd que utiliza esta plataforma para mostrar sus datos y de corresponder interactuar con un usuario administrador para la actualizacion de los datos.

### Cuenta con las siguientes entidades/modelos:
- Person: datos bãsicos del portfolio
- Interest: intereses
- Hardskill: habilidades tecnicas
- Softskill: habilidades blandas o personales 
- Project: proyectos
- Degree: niveles de los estudios según mundo académico (terciario, grado, postgrado, capacitacion, especializacion, etc.) 
- Organization: organizaciones o instituciones con la cual tuvo relacion, es decir pudo haber estudiado en el mismo lugar en donde trabajo p.ej: trabaja en una universidad en la cual está haciendo una carrera.
- RolePosition: posiciones que ha desarrollado, pudiendo estas haberlas realizado en diferentes organizaciones, p.ej: programador sr en varias organizaciones
- LaboralCareer: carrera laboral, vinculacion entre tareas desarrolladas en alguna organizacion ocupando una o varias posiciones en diferentes años.
- Studie: idem para la formacion académica, pudiendo haber realizado cursos con el mismo nombre en diferentes organizaciones.
- SocialNetwork: redes sociales ya conocidas, las actuales o las proximas que pudieran crearse en el futuro.

<b>Sin implementar en el frontEnd:</b>

- Phone: lista de telefonos posibles como medio de contacto
- DisplayData: configuración del layout, tanto del modo claro/oscuro, como la visibidad de algunos datos almacenados. P.ej. no mostrar los intereses, o la foto de perfil, o etc.
- Card: label y breve resumen de los diferentes contenedores de informacion (trayectoria, perfil, objetivo, habilidades, etc)
- ProjectMedia: archivos imagen, video u audio de los proyectos desarrollados.

La mayoria de los modelos cuenta con los atributos: ordertodeploy (determina el orden de salida de las diferentes listas), y status (para mostrarse la instancia del objeto o no), informacion válida para el frontend.


## Caracteristicas e implementaciones:
- Sólo servicio API, sin interface.
- Securitizado con la implementacion JWT en Spring Boot (backend) y Angular (frontend).
- Acceso con username y password a casi todos los endpoints.
- Captura de excepciones básico y elemental.
- Politica de integridad de datos con una "Validación básica de los json recibidos" a la ya existente en el ORM
- Acceso publico al suministrador de datos completos: https://yoprogramo-waldrom68.onrender.com/fullperson/view/1

## Cosas con las que no quedé conforme:
- La estructura del proyecto es muy desprolija y no sólo no permite su reutilización de manera simple para otros proyectos, tampoco resulta fácil seguir la lógica del flujo del sistema.
- Existen algunas reglas de negocios que se encuentran en las clases controller, no todas están en los servicios.

## Pendientes o próximas actualizaciones:
- Validacion en la recepcion de las fechas  (es decir, como mínimo un desde debe ser menor a su hasta).
- Crear las clases para gestion de usuarios, recuperacion de password, etc.
- Reorganizar la estructura del proyecto para futuras reutilizaciones. Agrupar las clases por entidad, o algo similar al paquete de seguridad.

## Bugs conocidos y no resueltos:
- No tiene una buena validacion los modeloss de trayactoria laboral y formación. Podría mejorarse convinando organizacion, puesto/nivel, y las fechas.
- Evaluar refactorizar las reglas de negocios general, llevandolas a los servicios


<br></br>
### <em>Gracias por leer todo esto, y espero no te duelan los ojos al ver la estructura del proyecto.
<b>WR<b><em>