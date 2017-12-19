package com.flexsolution.sitelogo.webscript;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.content.MimetypeMap;
import org.alfresco.repo.security.authentication.AuthenticationUtil;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.WebScriptResponse;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

/**
 * Created by max on 12/19/17 .
 */
public class BackgroundLoginImageWebScript extends AbstractWebScript {

    private ContentService contentService;

    @Override
    public void execute(WebScriptRequest req, WebScriptResponse res) throws IOException {

        res.setContentType(MimetypeMap.MIMETYPE_IMAGE_JPEG);
        FileCopyUtils.copy(AuthenticationUtil.runAs(() -> contentService.getReader(new NodeRef("workspace://SpacesStore/b23c2a62-3ede-446d-ae02-1d384fd185a3"), ContentModel.PROP_CONTENT).getContentInputStream(),
                AuthenticationUtil.getAdminUserName()), res.getOutputStream());

    }

    public void setContentService(ContentService contentService) {
        this.contentService = contentService;
    }
}
