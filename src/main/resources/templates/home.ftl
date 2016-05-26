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
    <div class="nav-wrapper teal lighten-2">
        <a href="/home" class="right brand-logo">Practica2</a>
        <ul id="nav-mobile" class="left hide-on-med-and-down">
            <li class="active">
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
                <table class="highlight centered">
                    <thead>
                    <th>Matricula</th>
                    <th>Nombres</th>
                    <th>Apellidos</th>
                    <th>Telefono</th>
                    <th>Acciones</th>
                    </thead>
                    <tbody>
                        <#list estudiantes as est>
                        <tr>
                            <td>${est.matricula?string["0"]}</td>
                            <td>${est.nombres}</td>
                            <td>${est.apellidos}</td>
                            <td>${est.telefono}</td>
                            <td>
                                <a class="waves-effect waves-teal btn" href="/view/${est.matricula?string["0"]}">
                                    <i class="material-icons left">perm_identity</i>
                                    ver
                                </a>
                                <a class="waves-effect waves-teal btn orange darken-3" href="/edit/${est.matricula?string["0"]}">
                                    <i class="material-icons left">mode_edit</i>
                                    editar
                                </a>
                            </td>
                        </tr>
                        </#list>
                </table>
            </div>
        </div>
    </div>
</body>
</html>