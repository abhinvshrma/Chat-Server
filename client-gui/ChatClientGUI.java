import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

public class ChatClientGUI {

    private JFrame window;
    private JTextArea chatArea;
    private JTextField messageField;
    private PrintWriter out;
    private BufferedReader in;

    public ChatClientGUI() {
        // Create window
        window = new JFrame("Chat Client");
        window.setSize(400, 500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());

        // Chat area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(chatArea);
        window.add(scrollPane, BorderLayout.CENTER);

        // Input panel
        JPanel inputPanel = new JPanel(new BorderLayout());
        messageField = new JTextField();
        JButton sendButton = new JButton("Send");

        inputPanel.add(messageField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        window.add(inputPanel, BorderLayout.SOUTH);

        // Button action
        sendButton.addActionListener(e -> sendMessage());
        messageField.addActionListener(e -> sendButton.doClick());

        window.setVisible(true);

        // Connect to server
        connectToServer();
    }

    private void connectToServer() {
        try {
            Socket socket = new Socket("localhost", 1234);

            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            // Ask for username
            String name = JOptionPane.showInputDialog("Enter your name:");
            window.setTitle("Chat Client - "+ name);
            out.println(name);

            // Start thread to receive messages
            new Thread(() -> {
                try {
                    String serverMsg;
                    while ((serverMsg = in.readLine()) != null) {
                        chatArea.append(serverMsg + "\n");
                        chatArea.setCaretPosition(chatArea.getDocument().getLength());
                    }
                } catch (IOException e) {
                    chatArea.append("Connection closed.\n");
                }
            }).start();

        } catch (IOException e) {
            chatArea.append("Could not connect to server.\n");
        }
    }

    private void sendMessage() {
        String message = messageField.getText().trim();

        if (!message.isEmpty()) {
            out.println(message);
            messageField.setText("");
        }
    }

    public static void main(String[] args) {
        new ChatClientGUI();
    }
}
