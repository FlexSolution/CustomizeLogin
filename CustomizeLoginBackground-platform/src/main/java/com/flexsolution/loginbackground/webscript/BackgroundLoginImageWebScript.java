package com.flexsolution.loginbackground.webscript;

import com.flexsolution.loginbackground.model.Constants;
import com.flexsolution.loginbackground.model.LoginBackgroundConfigModel;
import com.flexsolution.loginbackground.util.ResourceService;
import org.alfresco.repo.content.MimetypeMap;
import org.alfresco.repo.security.authentication.AuthenticationUtil;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.namespace.QName;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.WebScriptResponse;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;


public class BackgroundLoginImageWebScript extends AbstractWebScript {

    private static final String PARAM_TYPE = "type";
    private ContentService contentService;
    private ResourceService resourceService;

    @Override
    public void execute(WebScriptRequest req, WebScriptResponse res) throws IOException {
        res.setContentType(MimetypeMap.MIMETYPE_IMAGE_JPEG);
        String type = req.getParameter(PARAM_TYPE);

        FileCopyUtils.copy(AuthenticationUtil.runAs(() ->
                        contentService.getReader(
                                resourceService.getNode(
                                        Constants.CONFIG_NODE_PATH,
                                        LoginBackgroundConfigModel.TYPE_LOGIN_BACKGROUND_CONFIG_TYPE),
                                QName.createQName(LoginBackgroundConfigModel.NAMESPACE, type))
                                .getContentInputStream(),
                AuthenticationUtil.getAdminUserName()), res.getOutputStream());
    }

    public void setContentService(ContentService contentService) {
        this.contentService = contentService;
    }

    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }
}
