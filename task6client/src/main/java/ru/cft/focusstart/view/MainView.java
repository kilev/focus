package ru.cft.focusstart.view;

import ru.cft.focusstart.Property;
import ru.cft.focusstart.ServerCommunicator;
import ru.cft.focusstart.view.utils.ConstraintsUtils;
import ru.cft.focusstart.view.utils.WindowUtils;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class MainView {

    private static final String HOST_LABEL_TEXT = "host:";
    private static final String PORT_LABEL_TEXT = "port:";
    private static final String LOGIN_LABEL_TEXT = "login:";
    private static final String CONNECT_BUTTON_TEXT = "connect";
    private static final String SEND_BUTTON_TEXT = "send ->";
    private static final Dimension MESSAGE_AREA_DIM = new Dimension(400, 250);
    private static final Dimension USER_ONLINE_LIST_DIM = new Dimension(100, 250);
    private static final Dimension MESSAGE_FIELD_DIM = new Dimension(300, 28);
    private static final String messageSeparator = System.lineSeparator();

    private final JPanel mainPanel = new JPanel(new GridBagLayout());
    private final JLabel hostLabel = new JLabel(HOST_LABEL_TEXT);
    private final JLabel portLabel = new JLabel(PORT_LABEL_TEXT);
    private final JLabel loginLabel = new JLabel(LOGIN_LABEL_TEXT);
    private final JTextField hostTextField = new JTextField(Property.DEFAULT_HOST.getValue());
    private final JTextField portTextField = new JTextField(Property.DEFAULT_PORT.getValue());
    private final JTextField loginTextField = new JTextField(Property.DEFAULT_LOGIN.getValue());
    private final JButton connectButton = new JButton(CONNECT_BUTTON_TEXT);
    private final JTextArea messagesTextArea = new JTextArea();
    private final JList<String> userOnlineList = new JList<>();
    private final JTextField messageTextField = new JTextField();
    private final JButton sendButton = new JButton(SEND_BUTTON_TEXT);

    private final ServerCommunicator serverCommunicator = new ServerCommunicator(this);

    public MainView() {
        JFrame mainWindow = WindowUtils.getMainWindow();
        mainWindow.setContentPane(mainPanel);
        addComponents();
        WindowUtils.setAtCenter(mainWindow);
    }

    private void addComponents() {
        mainPanel.add(hostLabel, ConstraintsUtils.getConstraints(0, 0, 1, 1, GridBagConstraints.WEST));
        mainPanel.add(hostTextField, ConstraintsUtils.getConstraints(1, 0, 1, 1, GridBagConstraints.WEST));
        mainPanel.add(portLabel, ConstraintsUtils.getConstraints(2, 0, 1, 1, GridBagConstraints.WEST));
        mainPanel.add(portTextField, ConstraintsUtils.getConstraints(3, 0, 1, 1, GridBagConstraints.WEST));
        mainPanel.add(loginLabel, ConstraintsUtils.getConstraints(4, 0, 1, 1, GridBagConstraints.WEST));
        mainPanel.add(loginTextField, ConstraintsUtils.getConstraints(5, 0, 1, 1, GridBagConstraints.WEST));
        connectButton.addActionListener(e -> serverCommunicator.newConnection(hostTextField.getText(), Integer.valueOf(portTextField.getText()), loginTextField.getText()));
        mainPanel.add(connectButton, ConstraintsUtils.getConstraints(6, 0, 1, 1, GridBagConstraints.EAST));

        JPanel textAreaPanel = new JPanel();
        textAreaPanel.setBorder(new TitledBorder(new EtchedBorder(), "Global chat"));
        messagesTextArea.setPreferredSize(MESSAGE_AREA_DIM);
        messagesTextArea.setEditable(false);
        textAreaPanel.add(messagesTextArea);
        mainPanel.add(textAreaPanel, ConstraintsUtils.getConstraints(0, 1, 1, 7, GridBagConstraints.CENTER));

        messageTextField.setPreferredSize(MESSAGE_FIELD_DIM);
        mainPanel.add(messageTextField, ConstraintsUtils.getConstraints(0, 2, 1, 6, GridBagConstraints.WEST));
        sendButton.addActionListener(e -> {
            if (!messageTextField.getText().equals("")) {
                serverCommunicator.sendMessage(messageTextField.getText());
                messageTextField.setText(null);
            }
        });
        mainPanel.add(sendButton, ConstraintsUtils.getConstraints(6, 2, 1, 1, GridBagConstraints.EAST));
    }

    public void addMessage(String author, String message) {
        messagesTextArea.append("<" + author + "> " + message + messageSeparator);
    }

    public void setUserOnlineList() {
//        userOnlineList.set
    }

    public void showInfoPane(String info) {
        new JOptionPane().createDialog(info);
    }
}
