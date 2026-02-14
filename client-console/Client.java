import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234);

            Scanner s = new Scanner(System.in);

            System.out.println("Enter your name: ");   // <-- FIXED
            String name = s.nextLine();

            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            out.println(name);

            String serverMsg = in.readLine();
            System.out.println("SERVER: " + serverMsg);

            while (true) {
                String message = s.nextLine();
                out.println(message);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
