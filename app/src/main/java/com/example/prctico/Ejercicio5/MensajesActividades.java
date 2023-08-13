package com.example.prctico.Ejercicio5;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.prctico.R;


import androidx.appcompat.app.AppCompatActivity;

public class MensajesActividades extends AppCompatActivity {

    private static final int REQUEST_CODE_SECOND_ACTIVITY = 1;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensajes_actividades);

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if (message.what == REQUEST_CODE_SECOND_ACTIVITY) {
                    String receivedMessage = message.getData().getString("message");
                    openSecondActivity(receivedMessage);
                }
                return false;
            }
        });
    }

    public void sendMessage(View view) {
        EditText editMessage = findViewById(R.id.editMessage);
        String messageToSend = editMessage.getText().toString();

        if (!messageToSend.isEmpty()) {
            Bundle bundle = new Bundle();
            bundle.putString("message", messageToSend);

            Message message = new Message();
            message.what = REQUEST_CODE_SECOND_ACTIVITY;
            message.setData(bundle);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    handler.sendMessage(message);
                }
            }).start();
        } else {
            Toast.makeText(this, "Por favor ingrese un mensaje", Toast.LENGTH_SHORT).show();
        }
    }

    private void openSecondActivity(String message) {
        Intent intent = new Intent(this, MensajesActividades2.class);
        intent.putExtra("message", message);
        startActivityForResult(intent, REQUEST_CODE_SECOND_ACTIVITY);
    }
}
