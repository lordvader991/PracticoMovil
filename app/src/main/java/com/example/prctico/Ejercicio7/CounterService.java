package com.example.prctico.Ejercicio7;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

public class CounterService extends Service {

    private Handler handler;
    private int counter = 0;
    private static final int NOTIFICATION_INTERVAL = 2000; // Intervalo en milisegundos

    @Override
    public void onCreate() {
        super.onCreate();

        handler = new Handler(getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                counter++;
                Toast.makeText(CounterService.this, "Notificación", Toast.LENGTH_SHORT).show();
                sendEmptyMessageDelayed(0, NOTIFICATION_INTERVAL);
            }

        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler.sendEmptyMessage(0);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
