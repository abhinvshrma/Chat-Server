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

            while(true){
                Socket clientSocket = serverSocket.accept();
                System.out.println(" New Client connected: "+clientSocket.getInetAddress().getHostAddress());

                ClientHandler handler = new ClientHandler(clientSocket);
                new Thread(handler).start();
            }
            
        }
        catch(IOException e){
            System.out.println("Couldn't listen on port "+ PORT);
            e.printStackTrace();
        }
    }
}
