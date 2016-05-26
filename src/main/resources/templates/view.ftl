<!DOCTYPE html>
<html lang="en">
<head>
    <link href="/css/materialize.min.css" rel="stylesheet" >
    <link href="/css/estilo.css" rel="stylesheet" >
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <meta charset="UTF-8">
    <title>Detalle Estudiante</title>
</head>
<body>
<nav>
    <div class="nav-wrapper green darken-2">
        <a href="/home" class="right brand-logo">Practica2</a>
        <ul id="nav-mobile" class="left hide-on-med-and-down">
            <li>
                <a href="/home"><i class="material-icons left">contacts</i> Home</a>
            </li>
            <li>
                <a href="/new"><i class="material-icons left">library_add</i> Nuevo Estudiante</a>
            </li>
        </ul>
    </div>
</nav>
<div class="container">
    <div class="row">
        <div class="col s10 push-s1">
            <#if estudiante??>
            <div class="card">
                <div class="card-content">
                    <ul class="collection with-header">
                        <li class="collection-header">
                            <i class="large material-icons left">perm_identity</i><h3>Info. de Estudiante</h3>
                        </li>
                        <li class="collection-item">
                            <i class="material-icons left">assignment</i> ${estudiante.matricula?string["0"]}
                        </li>
                        <li class="collection-item">
                            <i class="material-icons left">person_pin</i> ${estudiante.nombres}
                        </li>
                        <li class="collection-item">
                            <i class="material-icons left">person_pin</i> ${estudiante.apellidos}
                        </li>
                        <li class="collection-item">
                            <i class="material-icons left">contact_phone</i> ${estudiante.telefono}
                        </li>
                    </ul>
                </div>
                <div class="card-action">
                    <a href="/edit/${estudiante.matricula?string["0"]}">
                        <i class="material-icons left">mode_edit</i>
                    </a>
                    <a href="/delete/${estudiante.matricula?string["0"]}" onClick="return confirm('Seguro que desea borrar el estudiante actual?');">
                        <i class="material-icons left">delete</i>
                    </a>
                </div>
            </div>
            <#else>
            <div class="card-panel red darken-3">
                <p class="center-align white-text">
                    ${error_msg}
                </p>
            </div>
            </#if>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
<script src="/js/materialize.min.js"></script>
</body>
</html>