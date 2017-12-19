package com.flexsolution.loginbackground.model;

import org.alfresco.service.namespace.QName;


public abstract class LoginBackgroundConfigModel {

    public static final String NAMESPACE = "http://flex-solution.com/model/login-background/1.0";
    public static final String PREFIX = "fslb";

    // Types
    public static final QName TYPE_LOGIN_BACKGROUND_CONFIG_TYPE = QName.createQName(NAMESPACE, "login-background-config");

    // Properties
    public static final QName PROP_BACKGROUND_COLOR= QName.createQName(NAMESPACE, "backgroundColor");
    public static final QName PROP_BACKGROUND_IMAGE = QName.createQName(NAMESPACE, "backgroundImage");
}
