<#if field.value?? && field.value!="">
    <#assign currentValue=field.value>
<#else>
    <#assign currentValue="000000">
</#if>

<div class="form-field">
    <div>${field.label?html}:</div>
    <div id="${fieldHtmlId}-picker" style="visibility: hidden; z-index: 2000; background-color: #FFFFFF; border: 1px solid #808080; position: absolute; width: 305px; height: 220px;">
        <div style="position: absolute;" id="${fieldHtmlId}-container"></div>
        <input id="${fieldHtmlId}" type="hidden" name="${field.name}">
        <div style="position: relative;top: 190px;">
            <button id="${fieldHtmlId}-hideButton">Close</button>
        </div>
    </div>
    <div>
        <div id="${fieldHtmlId}-value" style="position: relative;
    top: 3px; border: 1px solid #808080; display:inline-block; height: 25px; width: 25px; background-color: #${currentValue}"></div>
        <button id="${fieldHtmlId}-editButton">#${currentValue}</button>
    </div>
</div>

<script type="text/javascript">//<![CDATA[
(function()
{
    YAHOO.util.Event.onAvailable("${fieldHtmlId}", function () {

        new FlexSolution.component.ColorPicker("${fieldHtmlId}").setOptions({
            "pageContext": "${url.context}",
            "value" : "${currentValue}"
        });

    }, this);
})();
//]]></script>