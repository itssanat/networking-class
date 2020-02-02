package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) {
        int portNumber = 4444;
        ArrayList<ServerThread> clients  = new ArrayList<ServerThread>();
        try {
            ServerSocket ss = new ServerSocket(portNumber);
            while (true){
                System.out.println("waiting");
                Socket socket  = ss.accept();
                System.out.println("connected");
                ServerThread client = new ServerThread(socket , clients);
                Thread thread = new Thread(client);
                thread.start();
                clients.add(client);
            }
        } catch (Exception e){

        }
    }
}
