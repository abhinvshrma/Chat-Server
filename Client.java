import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234);
            System.out.println("Connected to server...");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Scanner s = new Scanner(System.in);

            // Read welcome message from server (optional)
            String serverMsg = in.readLine();
            if (serverMsg != null) {
                System.out.println("SERVER: " + serverMsg);
            }

            while (true) {
                String message = s.nextLine();  // read user input
                out.println(message);           // send to server
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
