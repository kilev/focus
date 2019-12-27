package ru.cft.focusstart.view;

import ru.cft.focusstart.Property;
import ru.cft.focusstart.serverCommunicator.ServerCommunicator;
import ru.cft.focusstart.view.utils.ConstraintsUtils;
import ru.cft.focusstart.view.utils.WindowUtils;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainView {

    private static final String HOST_LABEL_TEXT = "host:";
    private static final String PORT_LABEL_TEXT = "port:";
    private static final String LOGIN_LABEL_TEXT = "login:";
    private static final String CONNECT_BUTTON_TEXT = "connect";
    private static final String SEND_BUTTON_TEXT = "send ->";
    private static final Dimension MESSAGE_AREA_DIM = new Dimension(400, 250);
    private static final Dimension USER_ONLINE_LIST_DIM = new Dimension(100, 250);
    private static final Dimension MESSAGE_FIELD_DIM = new Dimension(420, 28);
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private final JPanel mainPanel = new JPanel(new GridBagLayout());
    private final JLabel hostLabel = new JLabel(HOST_LABEL_TEXT);
    private final JLabel portLabel = new JLabel(PORT_LABEL_TEXT);
    private final JLabel loginLabel = new JLabel(LOGIN_LABEL_TEXT);
    private final JTextField hostTextField = new JTextField(Property.DEFAULT_HOST.getValue());
    private final JTextField portTextField = new JTextField(Property.DEFAULT_PORT.getValue());
    private final JTextField loginTextField = new JTextField(Property.DEFAULT_LOGIN.getValue());
    private final JButton connectButton = new JButton(CONNECT_BUTTON_TEXT);
    private final JTextArea messagesTextArea = new JTextArea();
    private final JTextArea userOnlineArea = new JTextArea();
    private final JTextField messageTextField = new JTextField();
    private final JButton sendButton = new JButton(SEND_BUTTON_TEXT);

    private final ServerCommunicator serverCommunicator = new ServerCommunicator(this);

    public MainView() {
        JFrame mainWindow = WindowUtils.getMainWindow();
        mainWindow.setContentPane(mainPanel);
        addComponents();
        WindowUtils.setAtCenter(mainWindow);
        mainWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                serverCommunicator.disconnect();
                System.exit(0);
            }
        });
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
        mainPanel.add(textAreaPanel, ConstraintsUtils.getConstraints(0, 1, 1, 6, GridBagConstraints.CENTER));

        userOnlineArea.setPreferredSize(USER_ONLINE_LIST_DIM);
        mainPanel.add(userOnlineArea, ConstraintsUtils.getConstraints(6, 1, 1, 1, GridBagConstraints.WEST));

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

    public void addMessage(String author, String message, Date date) {
        messagesTextArea.append("<" + author + "> " + "<" + new SimpleDateFormat().format(date) + "> " + message + LINE_SEPARATOR);
    }

    public void setUserOnlineArea(List<String> userOnline) {
        StringBuilder stringBuilder = new StringBuilder();
        userOnline.forEach(user -> stringBuilder.append(user).append(LINE_SEPARATOR));
        userOnlineArea.setText(stringBuilder.toString());
    }

    public void showInfoPane(String info) {
        JOptionPane.showMessageDialog(mainPanel, info);
//        new JOptionPane().createDialog(info);
    }
}
