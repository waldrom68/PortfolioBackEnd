# <span style="color:Antiquewhite; font-size: 1.5em">Proyecto Integrador Portfolio Web Full Stack ED3

# <span style="color:Aquamarine">Diseño
<div style= "font-size: 1.5em"> El proyecto tiene arquitectura distribuida y ésta es la interfaz del backend, la cual via un servicio API Rest suministra sus datos.

Los datos se encuentran alojados en una base de datos desplegada en otro servicio web, en un DB SERVER.

Existe una interfaz FrontEnd que utiliza esta plataforma para mostrar sus datos y de corresponder interactuar con un usuario administrador para la actualizacion de los datos.
</div>
<hr>

## <span style="color:Aquamarine"> Cuenta con las siguientes entidades/modelos:
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

<span style="color:yellow"><i><b>Sin implementar en el frontEnd:</b>

- Phone: lista de telefonos posibles como medio de contacto
- DisplayData: configuración del layout, tanto del modo claro/oscuro, como la visibidad de algunos datos almacenados. P.ej. no mostrar los intereses, o la foto de perfil, o etc.
- Card: label (trayectoria, perfil, objetivo, habilidades, etc)
- ProjectMedia: archivos imagen, video u audio de los proyectos desarrollados.

La mayoria de los modelos cuenta con los atributos: ordertodeploy (determina el orden de salida de las diferentes listas), y status (para mostrarse la instancia del objeto o no), informacion válida para el frontend.
<hr>

## <span style="color:Aquamarine"> Caracteristicas e implementaciones

- Sólo servicio API, sin interface.

- Securitizado con la implementacion JWT en Spring Boot (backend) y Angular (frontend).

- Acceso con username y password a casi todos los endpoints.

- Captura de excepciones básico y elemental.

- Politica de integridad de datos con una "Validación básica de los json recibidos" a la ya existente en el ORM

- Acceso publico al suministrador de datos completos: https://yoprogramo-waldrom68.onrender.com/fullperson/view/1
<hr>

## <span style="color:Aquamarine"> Cosas con las que no quedé conforme

- La estructura del proyecto es muy desprolija y no sólo no permite su reutilización de manera simple para otros proyectos, tampoco resulta fácil seguir la lógica del flujo del sistema.

- Existen algunas reglas de negocios que se encuentran en las clases controller, no todas están en los servicios.

- Me pareció que la actualizacion en el reordenamiento (en lote) de los objetos resulta lenta. Seguramente porque por falta de timepo para la entrega, reutilicé el metodo de actualizar objeto, el cual realiza varias validaciones que para el caso resultan innecesarias. Leer documentacion para entender el ORM, repensarla, optimizarla y testear.

<hr>

## <span style="color:Aquamarine"> Pendientes o próximas actualizaciones

- Validacion en la recepcion de las fechas  (es decir, como mínimo un desde debe ser menor a su hasta).

- Crear las clases para gestion de usuarios, CRUD y modificacion y recuperacion de password, etc.

- Reorganizar la estructura del proyecto para futuras reutilizaciones. Agrupar las clases por entidad, o algo similar al paquete de seguridad.

- Implementar multiples portfolios, es decir que para cada persona con un portfolio se le genere un endpoint y un administrador único. La estructura de la DB está pensada y preparada para administrarlo así.

- En las clases model, el atributo orderdeploy no debe tener valor por default igual a cero, sino por la logica del frontend, los nuevos sin ordenar figurarán primeros, repensarlo (aqui y en el frontend) o asignarle un valor por default muy alto.

<hr>

## <span style="color:Aquamarine"> Bugs conocidos y no resueltos

- No devuelve mensajes entendibles para usuarios finales frente problemas de autorizacion en pedidos http (ejemplo: token vencido)

- No tiene una buena validacion los modelos de trayactoria laboral y formación. Podría mejorarse combinando organizacion, puesto/nivel, y las fechas.

- Evaluar refactorizar las reglas de negocios general, llevandolas a los servicios

<br></br>

##  <div style="color:Antiquewhite">Espero no  duelan los ojos al ver el código, seguramente existen formas mas simples para resolver situaciones. <br> Claramente aún no las descubrí ni las investigué.</div>


<br></br>
<span style="color:Antiquewhite"><b>¡ Gracias por leer todo esto !<br>WALTER (abril 2023)<b><em></br>
<span style="color:Antiquewhite;">waldrom68@gmail.com