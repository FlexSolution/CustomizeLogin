package com.flexsolution.loginbackground.webscript;

import com.flexsolution.loginbackground.model.Constants;
import com.flexsolution.loginbackground.model.LoginBackgroundConfigModel;
import com.flexsolution.loginbackground.util.ResourceService;
import org.alfresco.repo.security.authentication.AuthenticationUtil;
import org.alfresco.service.cmr.repository.ContentReader;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptRequest;

import java.util.HashMap;
import java.util.Map;


public class BackgroundLoginColorWebScript extends DeclarativeWebScript {

    private static final String COLOR = "color";
    private static final String IS_BACKGROUND_IMAGE = "isBackgroundImage";
    private ContentService contentService;
    private ResourceService resourceService;
    private NodeService nodeService;

    @Override
    protected Map<String, Object> executeImpl(WebScriptRequest req, Status status, Cache cache) {
        return AuthenticationUtil.runAs(this::prepareModel, AuthenticationUtil.getAdminUserName());
    }

    private Map<String, Object> prepareModel() {
        HashMap<String, Object> model = new HashMap<>();

        NodeRef node = resourceService.getNode(Constants.CONFIG_NODE_PATH, LoginBackgroundConfigModel.TYPE_LOGIN_BACKGROUND_CONFIG_TYPE);

        model.put(COLOR, nodeService.getProperty(node, LoginBackgroundConfigModel.PROP_BACKGROUND_COLOR));
        ContentReader reader = contentService.getReader(node, LoginBackgroundConfigModel.PROP_BACKGROUND_IMAGE);
        model.put(IS_BACKGROUND_IMAGE, reader != null && reader.exists() && reader.getSize() > 0);

        return model;
    }

    public void setContentService(ContentService contentService) {
        this.contentService = contentService;
    }

    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    public void setNodeService(NodeService nodeService) {
        this.nodeService = nodeService;
    }
}
