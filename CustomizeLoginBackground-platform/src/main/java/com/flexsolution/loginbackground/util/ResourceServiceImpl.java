package com.flexsolution.loginbackground.util;

import org.alfresco.error.AlfrescoRuntimeException;
import org.alfresco.model.ContentModel;
import org.alfresco.repo.model.Repository;
import org.alfresco.repo.security.authentication.AuthenticationUtil;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.model.FileInfo;
import org.alfresco.service.cmr.model.FileNotFoundException;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class ResourceServiceImpl implements ResourceService {

    private static final Logger logger = Logger.getLogger(ResourceServiceImpl.class);

    private Repository repository;
    private FileFolderService fileFolderService;

    @Override
    public NodeRef getNode(String fileLocation) {
        return findNode(repository.getCompanyHome(), fileLocation, ContentModel.TYPE_CONTENT);
    }

    @Override
    public NodeRef getNode(String fileLocation, QName type) {
        return findNode(repository.getCompanyHome(), fileLocation, type);
    }

    private synchronized NodeRef findNode(NodeRef startLocation, String fileLocation, QName type) {

        LinkedList<String> pathElements = new LinkedList<>(Arrays.asList(fileLocation.split("/")));

        try {

            FileInfo fileInfo = fileFolderService.resolveNamePath(startLocation, pathElements, false);

            if (fileInfo != null) {
                return fileInfo.getNodeRef();
            } else {
                String last = pathElements.removeLast();
                return AuthenticationUtil.runAs(() ->
                                createFileAndFolders(startLocation, pathElements, last, type),
                        AuthenticationUtil.getAdminUserName());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new AlfrescoRuntimeException(e.getMessage(), e);
        }
    }


    private NodeRef createFileAndFolders(NodeRef startLocation, LinkedList<String> pathElements,
                                         String fileName, QName type) {
        try {
            String folderName = pathElements.removeFirst();
            NodeRef nodeRef = fileFolderService.searchSimple(startLocation, folderName);
            if (nodeRef == null) {
                nodeRef = fileFolderService.create(startLocation, folderName, ContentModel.TYPE_FOLDER).getNodeRef();
            }
            startLocation = createFileAndFolders(nodeRef, pathElements, fileName, type);

        } catch (NoSuchElementException e) {

            logger.debug("The last folder has been created, create default file");
            return fileFolderService.create(startLocation, fileName, type).getNodeRef();

        }
        return startLocation;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public void setFileFolderService(FileFolderService fileFolderService) {
        this.fileFolderService = fileFolderService;
    }
}
