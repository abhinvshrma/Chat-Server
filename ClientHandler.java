import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private String clientName;
    private List<PrintWriter> clientWriters;

    public ClientHandler(Socket socket, List<PrintWriter> writers) {
        this.clientSocket = socket;
        this.clientWriters = writers;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            // First thing: receive name
            clientName = in.readLine();

            synchronized (clientWriters) {
                clientWriters.add(out);
            }

            broadcast(clientName + " joined the chat!");

            String message;

            while ((message = in.readLine()) != null) {
                System.out.println(clientName + ": " + message);
                broadcast(clientName + ": " + message);
            }

        } catch (IOException e) {
            System.out.println("Client disconnected");
        } finally {
            try {
                synchronized (clientWriters) {
                    clientWriters.remove(out);
                }

                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            broadcast(clientName + " left the chat.");
        }
    }

    private void broadcast(String msg) {
        synchronized (clientWriters) {
            for (PrintWriter writer : clientWriters) {
                writer.println(msg);
            }
        }
    }
}
