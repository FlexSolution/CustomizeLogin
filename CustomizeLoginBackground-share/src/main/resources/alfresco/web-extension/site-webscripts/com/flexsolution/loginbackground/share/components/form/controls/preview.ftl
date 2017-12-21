<div class="form-field">
    <span id="${fieldHtmlId}" class="file-wrapper">
    </span>
</div>

<script type="text/javascript">//<![CDATA[
(function () {

    YAHOO.Bubbling.on("afterFormRuntimeInit", function (event, p_obj) {

        var form = p_obj[1].runtime;

        var formId = form.formId;

        var contentFieldId = formId.replace("-form", "_prop_${field.control.params.contentField}");

        YAHOO.util.Event.onAvailable(contentFieldId, function () {

            var value = this.getAttribute("value");

            if (value && value.length > 0) {

                var size = value.substring(value.indexOf("|size=") + 6, value.indexOf("|encoding"));

                if (size && (+size) > 0) {

                    var preview = YAHOO.util.Dom.get("${fieldHtmlId}");

                    var action = YAHOO.util.Dom.get(formId).action;

                    var itemID = action.substring(action.indexOf("node/") + 5, action.indexOf("/formprocessor"));

                    preview.innerHTML = "<img style='max-height: 400px;max-width: 800px;' src='" + Alfresco.constants.PROXY_URI + "api/node/" +
                            itemID + "/content;fslb:backgroundImage'>"
                }
            }
        });
    }, this);
})();
//]]></script>