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
    <div class="nav-wrapper red darken-4">
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
    <div class="row"></div>
    <div class="row">
        <div class="col s10 push-s1">
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
    </div>
</div>
<script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
<script src="/js/materialize.min.js"></script>
</body>
</html>