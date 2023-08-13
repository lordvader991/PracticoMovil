package com.example.prctico.Ejercicio3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.prctico.R;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class RecieverActivity extends AppCompatActivity {
    private TextView receivedMessageTextView;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private InputStream inputStream;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            String receivedMessage = msg.obj.toString();
            receivedMessageTextView.setText(receivedMessage);
            return true;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciever);
        receivedMessageTextView = findViewById(R.id.receivedMessageTextView);
        // Create server socket in a separate thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    serverSocket = new ServerSocket(8080);
                    clientSocket = serverSocket.accept();
                    inputStream = clientSocket.getInputStream();

                    byte[] buffer = new byte[1024];
                    int bytesRead;

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        String receivedMessage = new String(buffer, 0, bytesRead);
                        Message message = handler.obtainMessage(0, receivedMessage);
                        handler.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}