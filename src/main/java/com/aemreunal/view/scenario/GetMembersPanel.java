package com.aemreunal.view.scenario;

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
import com.aemreunal.model.ScenarioManager;
import com.aemreunal.view.response.table.ItemTable;
import com.aemreunal.view.response.table.TableResponsePanel;
import com.aemreunal.view.beacon.BeaconTab;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

public class GetMembersPanel extends JPanel {
    private JTextField projectIdField;
    private JTextField scenarioIdField;
    private JButton    getButton;

    public GetMembersPanel(TableResponsePanel tableResponsePanel) {
        createComponents(tableResponsePanel);
        addComponents();
    }

    private void createComponents(TableResponsePanel tableResponsePanel) {
        projectIdField = new JTextField(5);
        projectIdField.addActionListener(new GetActionListener(tableResponsePanel));
        scenarioIdField = new JTextField(5);
        scenarioIdField.addActionListener(new GetActionListener(tableResponsePanel));
        getButton = new JButton("Get");
        getButton.addActionListener(new GetActionListener(tableResponsePanel));
    }

    private void addComponents() {
        this.add(new JLabel("Project ID:"));
        this.add(projectIdField);
        this.add(new JLabel("Scenario ID:"));
        this.add(scenarioIdField);
        this.add(getButton);
    }

    private class GetActionListener implements ActionListener {
        private final TableResponsePanel tableResponsePanel;

        public GetActionListener(TableResponsePanel tableResponsePanel) {
            this.tableResponsePanel = tableResponsePanel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String projectId = projectIdField.getText().trim();
            String scenarioId = scenarioIdField.getText().trim();
            if (projectId.isEmpty() || scenarioId.isEmpty()) {
                return;
            }
            HttpResponse<JsonNode> response = ScenarioManager.getScenarioMemberBeacons(scenarioId, projectId);
            tableResponsePanel.showResponseCode(response.getStatus());
            String[][] beaconResponse = null;
            if (response.getStatus() == 200) {
                beaconResponse = BeaconTab.convertBeaconJsonToTable(response.getBody().getArray());
            }
            tableResponsePanel.showResponseTable(ItemTable.BEACONS_TABLE_COL_NAMES, beaconResponse);
        }
    }
}
