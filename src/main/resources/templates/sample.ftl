<!DOCTYPE html>
<html>
<head>
    <title>Sample</title>
    <link href="/css/materialize.min.css" rel="stylesheet" >
    <link href="/css/estilo.css" rel="stylesheet" >
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col s12">
            <table class="highlight centered">
                <thead>
                    <th>Matricula</th>
                    <th>Nombre</th>
                </thead>
                <tbody>
                <#list estudiantes as est>
                    <tr>
                        <td>${est.matricula}</td>
                        <td>${est.nombres}</td>
                    </tr>
                </#list>
            </table>
        </div>
    </div>
</div>
<script src="/js/materialize.min.js"></script>
</body>
</html>