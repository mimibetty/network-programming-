
import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    int id;
    public ClientHandler(Socket socket,int id) {
        this.clientSocket = socket;
        System.out.print(socket.getPort()+"\n");
        this.id = id;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientMessage;
            while ((clientMessage = in.readLine()) != null) {
                ServerChat.outputField.append("Client "+id+ " :" + clientMessage+"\n");
                for (Socket client: ServerChat.clients) {
                    if(client== clientSocket) continue;
                    PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                    out.println("Client "+id+": "+clientMessage);
                }
            }

        } catch (IOException e) {
            ServerChat.outputField.append("Client "+id+" ngắt kết nối\n");
//            e.printStackTrace();
        }
    }
}