<!DOCTYPE html>
<html lang="en">
<head>
    <link href="/css/materialize.min.css" rel="stylesheet" >
    <link href="/css/estilo.css" rel="stylesheet" >
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <meta charset="UTF-8">
    <title>Home</title>
</head>
<body>
<nav>
    <div class="nav-wrapper light-blue darken-4">
        <a href="/home" class="right brand-logo">Practica2</a>
        <ul id="nav-mobile" class="left hide-on-med-and-down">
            <li>
                <a href="/home"><i class="material-icons left">contacts</i> Home</a>
            </li>
            <li>
                <a href="/nuevo"><i class="material-icons left">library_add</i> Nuevo Estudiante</a>
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
            <h2>${error_msg}</h2>
            </#if>
        </div>
    </div>
</div>
</body>
</html>