import java.io.IOException;
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
        }
        catch(IOException e){
            System.out.println("Couldn't listen on port "+ PORT);
            e.printStackTrace();
        }
    }
}
