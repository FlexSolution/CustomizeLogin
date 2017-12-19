var main = function () {

    YAHOO.Bubbling.on("afterFormRuntimeInit", function (event, p_obj) {

        var form = p_obj[1].runtime;

        form.ajaxSubmitHandlers.successCallback = {
            fn: function () {
                Alfresco.util.PopupManager.displayPrompt({
                    title: "Success",
                    text: "Login background configuration has been saved"
                });
                return true;
            },
            obj: form,
            scope: this
        };
    }, this);
}();