package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML public TextArea textArea;
    @FXML public TextField textField;
    @FXML public Button sendButton;
    @FXML public TextField name;
    private Socket socket = null;
    String host = "localhost";
    int port = 4444;
    InetAddress address;
    InputStream is;
    OutputStream os;
    InputStreamReader isr;
    OutputStreamWriter osr;
    BufferedReader br;
    BufferedWriter bw;
    String username ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // do nothing
    }

    public void sendButtonAction(ActionEvent actionEvent) {
        String message = textField.getText();
        textField.clear();
        try {
            bw.write( username + "\n" + message+ "\n" );
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void connectButtonAction(ActionEvent actionEvent) {

        if( socket != null) return;
        username = name.getText();
        try  {
            address = InetAddress.getByName(host);
            socket = new Socket(address , port);
            System.out.println("client connected");
            is = socket.getInputStream();
            os = socket.getOutputStream();
            isr = new InputStreamReader(is);
            osr = new OutputStreamWriter(os);
            br = new BufferedReader(isr);
            bw = new BufferedWriter(osr);
            ClientThread client = new ClientThread(br,bw,textArea);
            Thread thread = new Thread(client);
            thread.start();
        } catch (Exception e){

        }
    }


}
