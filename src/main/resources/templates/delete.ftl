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
<script src="/js/jquery-2.2.4.js"></script>
<script src="/js/materialize.min.js"></script>
</body>
</html>