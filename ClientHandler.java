import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket clientSocket;

    public ClientHandler(Socket socket){
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream())
            );

            PrintWriter out = new PrintWriter(
                clientSocket.getOutputStream(), true
            );

            out.println("You are connected to server!");

            String message;

            while ((message = in.readLine()) != null) {
                System.out.println("Client says: " + message);

                // 🔥 IMPORTANT FIX: send something back to client
                out.println("Server received: " + message);
            }

        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
}
