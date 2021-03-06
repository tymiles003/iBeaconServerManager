package com.aemreunal.view.user;

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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.aemreunal.model.UserManager;
import com.aemreunal.view.response.table.ItemTable;
import com.aemreunal.view.response.table.TableResponsePanel;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

/*
 *  Register response:
 *  {
 *    "userId" : 2,
 *    "username" : "testuser123",
 *    "links" : []
 *  }
 */

public class RegisterPanel extends JPanel {
    private JTextField usernameField;
    private JTextField passwordField;
    private JButton    registerButton;

    public RegisterPanel(final TableResponsePanel tableResponsePanel) {
        createComponents(tableResponsePanel);
        addComponents();
    }

    private void createComponents(TableResponsePanel tableResponsePanel) {
        usernameField = new JTextField(10);
        passwordField = new JTextField(10);
        registerButton = new JButton("Register");
        registerButton.addActionListener(new RegisterActionListener(tableResponsePanel));
    }

    private void addComponents() {
        this.add(new JLabel("Username:"));
        this.add(usernameField);
        this.add(new JLabel("Password:"));
        this.add(passwordField);
        this.add(registerButton);
    }

    private class RegisterActionListener implements ActionListener {
        private final TableResponsePanel tableResponsePanel;

        public RegisterActionListener(TableResponsePanel tableResponsePanel) {
            this.tableResponsePanel = tableResponsePanel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            HttpResponse<JsonNode> response = UserManager.registerUser(usernameField.getText().trim(), passwordField.getText().trim());
            tableResponsePanel.showResponseCode(response.getStatus());
            String[][] userResponse = null;
            if (response.getStatus() == 201) {
                // Normal response
                userResponse = UserTab.convertUserJsonToTable(response.getBody().getObject());
            }
            tableResponsePanel.showResponseTable(ItemTable.USER_TABLE_COL_NAMES, userResponse);
        }
    }
}
