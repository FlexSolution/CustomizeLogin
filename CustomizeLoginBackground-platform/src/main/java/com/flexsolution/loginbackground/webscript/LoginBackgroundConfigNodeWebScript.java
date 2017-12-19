package com.flexsolution.loginbackground.webscript;

import com.flexsolution.loginbackground.model.Constants;
import com.flexsolution.loginbackground.model.LoginBackgroundConfigModel;
import com.flexsolution.loginbackground.util.ResourceService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.springframework.extensions.webscripts.*;

import java.util.HashMap;
import java.util.Map;

/**
 * return NodeRef of Oauth2 config for admin users
 */
public class LoginBackgroundConfigNodeWebScript extends DeclarativeWebScript {


    private static final String NODE_REF = "nodeRef";
    private ResourceService resourceService;

    protected Map<String, Object> executeImpl(WebScriptRequest req, Status status, Cache cache) {
        HashMap<String, Object> model = new HashMap<>();
        NodeRef node = resourceService.getNode(Constants.CONFIG_NODE_PATH, LoginBackgroundConfigModel.TYPE_LOGIN_BACKGROUND_CONFIG_TYPE);
        if (node != null) {
            model.put(NODE_REF, node.toString());
            return model;
        } else {
            throw new WebScriptException(Status.STATUS_INTERNAL_SERVER_ERROR, "error creating or finding oauth2 config node");
        }
    }

    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }
}
