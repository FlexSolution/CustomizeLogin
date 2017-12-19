(function () {

    namespace("FlexSolution.component");

    FlexSolution.component.CustomizeLoginBackground = function Login_constructor(htmlId) {
        FlexSolution.component.CustomizeLoginBackground.superclass.constructor.call(this, "FlexSolution.component.CustomizeLoginBackground", htmlId);

        return this;
    };

    YAHOO.extend(FlexSolution.component.CustomizeLoginBackground, Alfresco.component.Base,
        {
            options: {
                spinner: null
            },

            onReady: function Login_onReady() {

                var proxyUrl = Alfresco.constants.PROXY_URI_RELATIVE.replace("/alfresco/", "/alfresco-noauth/");
                Alfresco.util.Ajax.request(
                    {
                        url: proxyUrl + "customize/login/backgroundStyle",
                        method: Alfresco.util.Ajax.GET,
                        successCallback: {
                            fn: function (response, p_obj) {
                                var resp = response.json;

                                console.log(resp);

                                var div = document.querySelector("body.alfresco-guest .sticky-wrapper");

                                if (resp.isBackgroundImage) {
                                    div.style.backgroundImage = "url('" + proxyUrl + "customize/login/image')";
                                } else {
                                    div.style.backgroundColor = resp.color;
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