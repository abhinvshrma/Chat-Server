import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class server {

    private static final int PORT = 1234;
    public static void main(String[] args) {
        // Making the SERVER
        try(ServerSocket serverSocket = new ServerSocket(PORT)){
            System.out.println("'Server started on port "+ PORT);
            System.out.println("Waiting for client......");

            // Making a socket for client and Waiting for client to join
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected "+ clientSocket.getInetAddress().getHostAddress());

            // Setting up input and ouput streams 
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Takes message from client and echoes back 
            String inputLine;
            while((inputLine = in.readLine()) != null){
                System.out.println("Client says: "+ inputLine);
                out.println("Server received: "+ inputLine);
            }
            in.close();
            out.close();
            clientSocket.close();
        }
        catch(IOException e){
            System.out.println("Couldn't listen on port "+ PORT);
            e.printStackTrace();
        }
    }
}
