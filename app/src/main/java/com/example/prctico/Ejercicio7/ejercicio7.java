package com.example.prctico.Ejercicio7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.prctico.R;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

public class ejercicio7 extends AppCompatActivity {
    private TextView counterTextView;
    private int counter = 0;
    private Handler handler;
    private static final int NOTIFICATION_INTERVAL = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio7);


    counterTextView = findViewById(R.id.counterTextView);

    // Inicializar el Handler
    handler = new Handler(getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            counter++;
            counterTextView.setText("Counter: " + counter);
            Toast.makeText(ejercicio7.this, "Notificación", Toast.LENGTH_SHORT).show();
            sendEmptyMessageDelayed(0, NOTIFICATION_INTERVAL);
        }
    };
}

    @Override
    protected void onResume() {
        super.onResume();
        // Iniciar el envío de mensajes
        handler.sendEmptyMessage(0);
    }

  protected void onDestroy(){
        super.onDestroy();
      handler.removeCallbacksAndMessages(null);

  }
}
