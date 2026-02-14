import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class server {

    private static final int PORT = 1234;
    private static List<PrintWriter> clientWriters = new ArrayList<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            System.out.println("Server started on port " + PORT);
            System.out.println("Waiting for clients...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New Client connected: " + clientSocket.getInetAddress().getHostAddress());

                ClientHandler handler = new ClientHandler(clientSocket, clientWriters);
                new Thread(handler).start();
            }

        } catch (IOException e) {
            System.out.println("Couldn't listen on port " + PORT);
            e.printStackTrace();
        }
    }
}
