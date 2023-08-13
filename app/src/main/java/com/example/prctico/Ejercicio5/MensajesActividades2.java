package com.example.prctico.Ejercicio5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.prctico.R;


import androidx.appcompat.app.AppCompatActivity;

public class MensajesActividades2 extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensajes_actividades2);

        TextView textReceivedMessage = findViewById(R.id.textReceivedMessage);

        String receivedMessage = getIntent().getStringExtra("message");
        if (receivedMessage != null) {
            textReceivedMessage.setText("Mensaje recibido: " + receivedMessage);
        }
    }

    public void onClose(View view) {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}
