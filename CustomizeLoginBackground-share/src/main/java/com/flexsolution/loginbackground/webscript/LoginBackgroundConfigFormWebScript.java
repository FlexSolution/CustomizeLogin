package com.flexsolution.loginbackground.webscript;

import org.alfresco.repo.content.MimetypeMap;
import org.alfresco.web.scripts.forms.FormUIGet;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.extensions.surf.RequestContext;
import org.springframework.extensions.surf.ServletUtil;
import org.springframework.extensions.surf.exception.ConnectorServiceException;
import org.springframework.extensions.surf.support.ThreadLocalRequestContext;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptException;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.connector.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * proxy for getting NodeRef of Oauth2 config for admin users
 */
public class LoginBackgroundConfigFormWebScript extends FormUIGet {

    private static final String NODE_REF = "nodeRef";
    private static final String NODE = "node";
    private static final String URI = "/customize/login/config-node";

    private ConnectorService connectorService;

    @Override
    protected Map<String, Object> executeImpl(WebScriptRequest req, Status status, Cache cache) {

        String nodeString = getConfigNode();

        return this.executeChangedSuperImplMethod(req, status, cache, nodeString);
    }


    /**
     * changed super method
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> executeChangedSuperImplMethod(WebScriptRequest req, Status status, Cache cache, String itemId) {

        Map<String, Object> model;

        /* Replaced parameter! */
        if (itemId != null && itemId.length() > 0) {
            model = generateModel(NODE, itemId, req, status, cache);
            Map<String, Object> modelForm = (Map<String, Object>) model.get(MODEL_FORM);
            modelForm.put(MODEL_ENCTYPE, ENCTYPE_JSON);
        } else {
            model = new HashMap<>(1);
            model.put(MODEL_FORM, null);
        }
        return model;
    }


    private String getConfigNode() {

        try {

            RequestContext requestContext = ThreadLocalRequestContext.getRequestContext();
            String currentUserId = requestContext.getUserId();
            HttpSession currentSession = ServletUtil.getSession(true);
            Connector connector = connectorService.getConnector(ENDPOINT_ID, currentUserId, currentSession);

            ConnectorContext context = new ConnectorContext(HttpMethod.GET);
            context.setContentType(MimetypeMap.MIMETYPE_JSON);

            // call the form service
            Response response = connector.call(URI, context);

            int code = response.getStatus().getCode();

            if (code == 200) {

                // parse alfResponse to JSONObject object
                JSONObject responseObj = new JSONObject(response.getResponse());

                return responseObj.getString(NODE_REF);

            } else {
                throw new WebScriptException(code, response.getResponse());
            }

        } catch (ConnectorServiceException | JSONException e) {
            throw new WebScriptException(Status.STATUS_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


    public void setConnectorService(ConnectorService connectorService) {
        this.connectorService = connectorService;
    }
}
