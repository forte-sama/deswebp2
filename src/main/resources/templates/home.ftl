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
<#include "navbar.ftl">
<div class="container">
    <div class="row"></div>
    <div class="row">
        <div class="col s10 push-s1">
            <div class="fill-window">
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
</div>
<script src="/js/jquery-2.2.4.js"></script>
<script src="/js/materialize.min.js"></script>
</body>
</html>