package com.github.gtache.settings;

import com.github.gtache.client.MessageDialog;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;

/**
 * The GUI for the LSP settings
 */
public class LSPGUI {

    private static final Logger LOG = Logger.getInstance(LSPGUI.class);
    private final LSPState state;
    private JLabel extName;
    private JPanel rootPanel;
    private JTextField extField;
    private JLabel servName;
    private JTextField servField;

    LSPGUI() {
        state = LSPState.getInstance();
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public JTextField getExtField() {
        return extField;
    }

    public JTextField getServField() {
        return servField;
    }

    public void apply() {
        MessageDialog.main("The changes will be applied after restarting the IDE.");
        final String ext = extField.getText();
        final String serv = servField.getText();
        LOG.info("Extracted " + ext + " and " + serv + " from GUI");
        LSPState.getInstance().setExtToServ(Collections.singletonMap(ext, serv));
    }

    public boolean isModified() {
        return !(state.getFirstExt().equals(extField.getText()) && state.getFirstServ().equals(servField.getText()));
    }

    public void reset() {
        extField.setText(state.getFirstExt());
        servField.setText(state.getFirstServ());
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new GridLayoutManager(2, 7, new Insets(0, 0, 0, 0), -1, -1));
        final Spacer spacer1 = new Spacer();
        rootPanel.add(spacer1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        rootPanel.add(spacer2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        extName = new JLabel();
        extName.setText("File Extension");
        rootPanel.add(extName, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        extField = new JTextField();
        extField.setText("");
        extField.setToolTipText("e.g. scala, java, c, js, ...");
        rootPanel.add(extField, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        servName = new JLabel();
        servName.setText("Server jar location");
        rootPanel.add(servName, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        servField = new JTextField();
        servField.setText("");
        servField.setToolTipText("e.g. D:\\dotty-server.jar");
        rootPanel.add(servField, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer3 = new Spacer();
        rootPanel.add(spacer3, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        rootPanel.add(spacer4, new GridConstraints(0, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}
