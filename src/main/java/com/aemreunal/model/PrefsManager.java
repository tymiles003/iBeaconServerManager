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
    public static final String SERVER_ADDRESS_KEY     = "ServerAddress";
    public static final String DEFAULT_SERVER_ADDRESS = "http://localhost:8080";
    public static final String USERNAME_KEY           = "Username";
    public static final String DEFAULT_USERNAME       = "testuser";
    public static final String PASSWORD_KEY           = "Password";
    public static final String DEFAULT_PASSWORD       = "testpassword";

    public static String getServerAddress() {
        return iBeaconServerManager.getPreferences().get(SERVER_ADDRESS_KEY, DEFAULT_SERVER_ADDRESS);
    }

    public static void setServerAddress(String serverAddress) {
        iBeaconServerManager.getPreferences().put(SERVER_ADDRESS_KEY, serverAddress);
        JOptionPane.showMessageDialog(null, "Server address updated.");
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
        JOptionPane.showMessageDialog(null, "Username updated.");
    }

    public static String getPassword() {
        return iBeaconServerManager.getPreferences().get(PASSWORD_KEY, DEFAULT_PASSWORD);
    }

    public static void setPassword(String password) {
        iBeaconServerManager.getPreferences().put(PASSWORD_KEY, password);
        JOptionPane.showMessageDialog(null, "Password updated.");
    }
}