package com.example.prctico.Ejercicio3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.prctico.R;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SenderActivity extends AppCompatActivity {

    private EditText messageEditText;
    private Button sendButton;
    private Socket clientSocket;
    private OutputStream outputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sender);

        messageEditText = findViewById(R.id.messageEditText);
        sendButton = findViewById(R.id.sendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageEditText.getText().toString();
                sendMessage(message);
            }
        });

        // Create socket connection in a separate thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    clientSocket = new Socket("192.168.1.44", 8080);
                    outputStream = clientSocket.getOutputStream();
                    Log.d("SenderActivity", "Socket connection established");
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("SenderActivity", "Error establishing socket connection");
                }
            }
        }).start();
    }

    private void sendMessage(String message) {
        try {
            if (outputStream != null) {
                outputStream.write(message.getBytes());
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
