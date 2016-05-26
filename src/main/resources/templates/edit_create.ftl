<!DOCTYPE html>
<html lang="en">
<head>
    <link href="/css/materialize.min.css" rel="stylesheet" >
    <link href="/css/estilo.css" rel="stylesheet" >
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <meta charset="UTF-8">
    <title>${action}</title>
</head>
<body>
<nav>
    <div class="nav-wrapper <#if action == "new">light-blue darken-4<#elseif action == "edit">orange darken-2</#if>">
        <a href="/home" class="right brand-logo">Practica2</a>
        <ul id="nav-mobile" class="left hide-on-med-and-down">
            <li>
                <a href="/home"><i class="material-icons left">contacts</i> Home</a>
            </li>
            <li class="active">
                <a href="/new"><i class="material-icons left">library_add</i> Nuevo Estudiante</a>
            </li>
        </ul>
    </div>
</nav>
<div class="container">
    <div class="row"></div>
    <div class="row">
        <#if msg??>
        <#if msg_type == "error">
        <div class="card-panel red darken-3">
        <#elseif msg_type == "success">
        <div class="card-panel green darken-1">
        </#if>
            <p class="center-align white-text">
            ${msg}
            </p>
        </div>
        </#if>
    </div>
    <div class="row">
        <#if action == "new">
        <form class="col s10 push-s1" id="form_nuevo_estudiante" action="/new" method="post">
            <#include "form_fields_student.ftl">
        </form>
        <#else>
        <i class="material-icons">report_problem</i>
        </#if>
    </div>
</div>
<script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
<script src="/js/materialize.min.js"></script>
<script src="/js/form.js"></script>
</body>
</html>