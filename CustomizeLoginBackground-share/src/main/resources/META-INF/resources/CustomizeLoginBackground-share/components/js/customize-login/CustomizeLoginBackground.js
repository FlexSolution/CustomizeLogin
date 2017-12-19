var namespace = function (identifier) {
    var klasses = arguments[1] || false;
    var ns = window;

    if (identifier !== '') {
        var parts = identifier.split(".");
        for (var i = 0; i < parts.length; i++) {
            if (!ns[parts[i]]) {
                ns[parts[i]] = {};
            }
            ns = ns[parts[i]];
        }
    }

    if (klasses) {
        for (var klass in klasses) {
            if (klasses.hasOwnProperty(klass)) {
                ns[klass] = klasses[klass];
            }
        }
    }

    return ns;
};


(function () {

    namespace("FlexSolution.component");

    var Dom = YAHOO.util.Dom;

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