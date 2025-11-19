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
            String msg;

            while (true) {
                msg = s.nextLine();       // read from keyboard
                out.println(msg);         // send to server
                System.out.println("SERVER: " + in.readLine()); // read server reply
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
