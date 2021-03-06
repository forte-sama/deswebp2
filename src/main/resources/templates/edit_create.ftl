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
<#include "navbar.ftl">
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
        <#if action == "new" || action == "edit">
        <form
                class="col s10 push-s1"
                action="<#if action == "new">
                        /new
                        <#elseif action == "edit">
                        /edit
                        </#if>"
                method="post">
            <#include "form_fields_student.ftl">
            <div class="row">
                <div class="col s6">
                    <button class="btn btn-large waves-effect waves-light green darken-2" type="submit" name="submit">
                        <#if action == "new">
                            Crear Nuevo Estudiante
                        <#elseif action == "edit">
                            Completar Edicion
                        </#if>
                        <i class="material-icons right">send</i>
                    </button>
                </div>
                <#if action == "edit">
                    <div class="col s5 push-s1">
                        <a href="/delete/<#if matricula??>${matricula}</#if>" class="btn waves-effect waves-light red darken-4" onClick="return confirm('Seguro que desea borrar el estudiante actual?')">
                            Borrar Estudiante
                            <i class="material-icons right">delete</i>
                        </a>
                    </div>
                </#if>
            </div>
        </form>
        <#else>
        <div class="card-panel green darken-1">
            <p class="center-align white-text">
                O Talves entraste a esta pagina por error.
            </p>
        </#if>
    </div>
</div>
<script src="/js/jquery-2.2.4.js"></script>
<script src="/js/materialize.min.js"></script>
<script src="/js/form.js"></script>
</body>
</html>