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

    // 🔥 Shared list of writers (will be passed from server)
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

            // Add this client's writer to shared list
            synchronized (clientWriters) {
                clientWriters.add(out);
            }

            out.println("You are connected to the server!");

            String message;

            while ((message = in.readLine()) != null) {
                System.out.println("Client says: " + message);

                // Broadcast to everyone
                broadcast("Client (" + clientSocket.getInetAddress().getHostAddress() + "): " + message);
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
        }
    }

    // 🔥 BROADCAST METHOD 
    private void broadcast(String msg) {
        synchronized (clientWriters) {
            for (PrintWriter writer : clientWriters) {
                writer.println(msg);
            }
        }
    }
}
