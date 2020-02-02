package server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread implements Runnable {
    InputStream is;
    OutputStream os;
    InputStreamReader isr;
    OutputStreamWriter osr;
    BufferedReader br;
    BufferedWriter bw;
    private Socket socket;
    ArrayList<ServerThread> clients  = new ArrayList<ServerThread>();
    public ServerThread(Socket socket , ArrayList<ServerThread> clients){
        this.socket  = socket;
        this.clients = clients;
    }

    @Override
    public void run() {
        System.out.println("server started");
        try {
            is = socket.getInputStream();
            os = socket.getOutputStream();
            isr = new InputStreamReader(is);
            osr = new OutputStreamWriter(os);
            br = new BufferedReader(isr);
            bw = new BufferedWriter(osr);

            while (true){
                String name = br.readLine();
                String message = br.readLine();

                for( ServerThread st : clients ){
                    if(!st.socket.isClosed()) {
                        st.bw.write(name + "\n" + message + "\n");
                        st.bw.flush();
                    }
                }

            }

        } catch (Exception e){

        }
    }
}
