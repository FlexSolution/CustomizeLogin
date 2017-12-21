(function () {

    namespace("FlexSolution.component");

    FlexSolution.component.CustomizeLoginBackground = function Login_constructor(htmlId) {
        FlexSolution.component.CustomizeLoginBackground.superclass.constructor.call(this, "FlexSolution.component.CustomizeLoginBackground", htmlId);
        return this;
    };

    YAHOO.extend(FlexSolution.component.CustomizeLoginBackground, Alfresco.component.Base, {
        onReady: function Login_onReady() {
            var proxyUrl = Alfresco.constants.PROXY_URI_RELATIVE.replace("/alfresco/", "/alfresco-noauth/");
            Alfresco.util.Ajax.request({
                url: proxyUrl + "customize/login/backgroundStyle",
                method: Alfresco.util.Ajax.GET,
                successCallback: {
                    fn: function (response) {
                        var resp = response.json;

                        var div = document.querySelector("body.alfresco-guest .sticky-wrapper");

                        div.classList.add(resp.mode);
                        div.style.backgroundColor = resp.color;

                        if (resp.isBackgroundImage) {
                            div.style.backgroundImage = "url('" + proxyUrl + "customize/login/image')";
                        }
                    },
                    scope: this
                },
                failureCallback: {
                    fn: function (response) {
                        response = response.serverResponse ? YAHOO.lang.JSON.parse(response.serverResponse.responseText) : response;
                        console.error(response);
                    },
                    scope: this
                }
            });
        }
    });
})();