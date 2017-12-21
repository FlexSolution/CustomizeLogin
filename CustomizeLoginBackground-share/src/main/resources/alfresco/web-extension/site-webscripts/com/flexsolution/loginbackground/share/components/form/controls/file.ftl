<div class="form-field">
    <span class="file-wrapper">
        <label for="${fieldHtmlId}">${field.label?html}:</label>
            <@formLib.renderFieldHelp field=field />
            <input id="${fieldHtmlId}" class="input-for-file" accept="image/*" name="${field.name}" tabindex="0"
                   type="file"
                   <#if field.control.params.styleClass??>class="${field.control.params.styleClass}"</#if>
                   <#if field.control.params.style??>style="${field.control.params.style}"</#if>
                   <#if field.value?is_number>value="${field.value?c}"<#else>value="${field.value?html}"</#if>
                   <#if field.description??>title="${field.description}"</#if>
                   <#if field.control.params.maxLength??>maxlength="${field.control.params.maxLength}"</#if>
                   <#if field.control.params.size??>size="${field.control.params.size}"</#if>
                   <#if field.disabled && !(field.control.params.forceEditable?? && field.control.params.forceEditable == "true")>disabled="true"</#if>
                <#if field.control.params.isMultiple?? && field.control.params.isMultiple=="true"> multiple</#if>/>
    </span>

</div>