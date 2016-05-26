<nav>
    <div
        class=
        <#if action == "new">
        "nav-wrapper light-blue darken-4"
        <#elseif action == "edit">
        "nav-wrapper orange darken-2"
        <#elseif action == "home">
        "nav-wrapper teal darken-4"
        <#elseif action == "view">
        "nav-wrapper green darken-4"
        <#else>
        "nav-wrapper red darken-4"
        </#if>>
        <a href="/home" class="right brand-logo">Practica2</a>
        <ul id="nav-mobile" class="left hide-on-med-and-down">
            <li>
                <a href="/home"><i class="material-icons left">contacts</i> Home</a>
            </li>
            <li
                <#if action??>
                    <#if action == "new">
                    class="active"
                    </#if>
                </#if>>
                <a href="/new"><i class="material-icons left">library_add</i> Nuevo Estudiante</a>
            </li>
        </ul>
    </div>
</nav>