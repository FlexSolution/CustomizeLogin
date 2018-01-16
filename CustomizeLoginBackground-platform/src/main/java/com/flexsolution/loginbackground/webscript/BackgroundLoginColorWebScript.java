package com.flexsolution.loginbackground.webscript;

import com.flexsolution.loginbackground.model.Constants;
import com.flexsolution.loginbackground.model.LoginBackgroundConfigModel;
import com.flexsolution.loginbackground.util.ResourceService;
import org.alfresco.repo.security.authentication.AuthenticationUtil;
import org.alfresco.service.cmr.repository.ContentReader;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.QName;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptRequest;

import java.util.HashMap;
import java.util.Map;


public class BackgroundLoginColorWebScript extends DeclarativeWebScript {

    private static final String COLOR = "color";
    private static final String IS_BACKGROUND_IMAGE = "isBackgroundImage";
    private static final String MODE = "mode";
    private static final String COPYRIGHT = "copyright";
    private static final String PRODUCT_NAME = "productName";
    private static final String PRODUCT_COMMUNITY = "productCommunity";

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
        model.put(COPYRIGHT, nodeService.getProperty(node, LoginBackgroundConfigModel.PROP_COPYRIGHT));
        model.put(PRODUCT_NAME, nodeService.getProperty(node, LoginBackgroundConfigModel.PROP_PRODUCT_NAME));
        model.put(PRODUCT_COMMUNITY, nodeService.getProperty(node, LoginBackgroundConfigModel.PROP_PRODUCT_COMMUNITY));
        model.put(MODE, nodeService.getProperty(node, LoginBackgroundConfigModel.PROP_BACKGROUND_IMAGE_DISPLAY_MODE));
        setImageProperty(model, node, LoginBackgroundConfigModel.PROP_BACKGROUND_IMAGE, IS_BACKGROUND_IMAGE);

        return model;
    }

    private void setImageProperty(HashMap<String, Object> model, NodeRef node, QName property, String nameForJson) {
        ContentReader reader = contentService.getReader(node, property);
        model.put(nameForJson, reader != null && reader.exists() && reader.getSize() > 0);
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
