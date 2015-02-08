package com.aemreunal.model;

/*
 ***************************
 * Copyright (c) 2014      *
 *                         *
 * This code belongs to:   *
 *                         *
 * @author Ahmet Emre Ünal *
 * S001974                 *
 *                         *
 * emre@aemreunal.com      *
 * emre.unal@ozu.edu.tr    *
 *                         *
 * aemreunal.com           *
 ***************************
 */

import javax.swing.*;
import com.aemreunal.iBeaconServerManager;

public class PrefsManager {
    public static final String HTTP_PROTOCOL       = "https://";
    public static final String SERVER_URL_KEY      = "ServerAddress";
    public static final String DEFAULT_SERVER_URL  = "localhost";
    public static final String SERVER_PORT_KEY     = "ServerPort";
    public static final String DEFAULT_SERVER_PORT = "8443";
    public static final String USERNAME_KEY        = "Username";
    public static final String DEFAULT_USERNAME    = "_";
    public static final String PASSWORD_KEY        = "Password";
    public static final String DEFAULT_PASSWORD    = "_";

    public static String getServerAddress() {
        return HTTP_PROTOCOL + getServerUrl() + ":" + getServerPort();
    }

    public static String getServerUrl() {
        return removeTrailingSlashes(iBeaconServerManager.getPreferences().get(SERVER_URL_KEY, DEFAULT_SERVER_URL));
    }

    public static void setServerUrl(String serverUrl) {
        serverUrl = removeTrailingSlashes(serverUrl);
        iBeaconServerManager.getPreferences().put(SERVER_URL_KEY, serverUrl);
    }

    public static String getServerPort() {
        return iBeaconServerManager.getPreferences().get(SERVER_PORT_KEY, DEFAULT_SERVER_PORT);
    }

    public static void setServerPort(String serverPort) {
        iBeaconServerManager.getPreferences().put(SERVER_PORT_KEY, serverPort);
    }

    private static String removeTrailingSlashes(String serverAddress) {
        while (serverAddress.charAt(serverAddress.length() - 1) == '/') {
            serverAddress = serverAddress.substring(0, serverAddress.length() - 1);
        }
        return serverAddress;
    }

    public static String getUsername() {
        return iBeaconServerManager.getPreferences().get(USERNAME_KEY, DEFAULT_USERNAME);

//        KeyStore keyStore = iBeaconServerManager.getKeyStore();
//        // get user password and file input stream
//        char[] password = "password".toCharArray();
//        java.io.FileInputStream fis = new java.io.FileInputStream(keyStore.);
//        keyStore.load(fis, password);
//        fis.close();
//
//        // get my private key
//        KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry)
//                keyStore.getEntry("privateKeyAlias", password);
//        PrivateKey myPrivateKey = pkEntry.getPrivateKey();
//
//        // save my secret key
//        javax.crypto.SecretKey mySecretKey;
//        KeyStore.SecretKeyEntry skEntry =
//                new KeyStore.SecretKeyEntry(mySecretKey);
//        keyStore.setEntry("secretKeyAlias", skEntry, password);
//
//        // store away the keystore
//        java.io.FileOutputStream fos =
//                new java.io.FileOutputStream("newKeyStoreName");
//        keyStore.store(fos, password);
//        fos.close();
    }

    public static void setUsername(String username) {
        iBeaconServerManager.getPreferences().put(USERNAME_KEY, username);
    }

    public static String getPassword() {
        return iBeaconServerManager.getPreferences().get(PASSWORD_KEY, DEFAULT_PASSWORD);
    }

    public static void setPassword(char[] password) {
        iBeaconServerManager.getPreferences().put(PASSWORD_KEY, password.toString());
    }
}
