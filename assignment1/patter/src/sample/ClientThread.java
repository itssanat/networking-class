package sample;

import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.Buffer;
import javafx.scene.control.TextArea;

public class ClientThread implements Runnable {
    BufferedReader br;
    BufferedWriter bw;
    TextArea textArea;

    public ClientThread(BufferedReader br, BufferedWriter bw, javafx.scene.control.TextArea textArea) {
        this.br = br;
        this.bw = bw;
        this.textArea = textArea;
    }

    @Override
    public void run() {
        System.out.println("thread running");
        while (true){
            try {
                String name = br.readLine();
                String message = br.readLine();
                textArea.appendText(name + ">>>  " + message + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
