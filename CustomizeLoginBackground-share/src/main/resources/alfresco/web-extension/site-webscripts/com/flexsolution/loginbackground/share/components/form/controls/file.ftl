<div class="form-field">
    <span class="file-wrapper">
        <div id="${fieldHtmlId}-preview"></div>
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

<script type="text/javascript">//<![CDATA[
(function () {

    YAHOO.Bubbling.on("afterFormRuntimeInit", function (event, p_obj) {

        var value = "${field.value}";

        if (value && value.length > 0) {

            var size = value.substring(value.indexOf("|size=") + 6, value.indexOf("|encoding"));

            if (size && (+size) > 0) {

                var preview = YAHOO.util.Dom.get("${fieldHtmlId}-preview");

                var form = p_obj[1].runtime;

                var action = Dom.get(form.formId).action;

                var itemID = action.substring(action.indexOf("node/") + 5, action.indexOf("/formprocessor"));

                preview.innerHTML = "<img style='max-height: 400px;max-width: 800px;' src='" + Alfresco.constants.PROXY_URI + "api/node/" +
                        itemID + "/content;fslb:backgroundImage'>"
            }
        }

    }, this);


})();
//]]></script>