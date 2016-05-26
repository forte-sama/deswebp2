<div class="row">
    <div class="input-field col s7">
        <i class="material-icons prefix">assignment</i>
        <input id="icon_prefix" type="text" name="matricula" length="12" <#if matricula??>value="${matricula}"</#if>>
        <label for="icon_prefix">Matricula</label>
    </div>
</div>
<div class="row"></div>
<div class="row">
    <div class="input-field col s6">
        <i class="material-icons prefix">person_pin</i>
        <input id="icon_prefix" name="nombres" type="text" length="100" value="<#if matricula??>${nombres}</#if>">
        <label for="icon_prefix">Nombres</label>
    </div>
    <div class="input-field col s6">
        <i class="material-icons prefix">person_pin</i>
        <input id="icon_prefix" name="apellidos" type="text" length="100" value="<#if matricula??>${apellidos}</#if>">
        <label for="icon_prefix">Apellidos</label>
    </div>
</div>
<div class="row">
    <div class="input-field col s7">
        <i class="material-icons prefix">contact_phone</i>
        <input id="icon_prefix" name="telefono" type="text" length="50" value="<#if matricula??>${telefono}</#if>">
        <label for="icon_prefix">Telefono</label>
    </div>
</div>