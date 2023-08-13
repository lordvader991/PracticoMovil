package com.example.prctico.Ejercicio9;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.prctico.R;

public class Cronometro extends AppCompatActivity {

    private TextView timerTextView;
    private Button startButton, pauseButton, resetButton;

    private boolean isRunning = false;
    private int seconds = 0;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 1) {
                updateTimer();
            }
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);

        timerTextView = findViewById(R.id.timerTextView);
        startButton = findViewById(R.id.startButton);
        pauseButton = findViewById(R.id.pauseButton);
        resetButton = findViewById(R.id.resetButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRunning) {
                    startTimer();
                    startButton.setEnabled(false);
                    pauseButton.setEnabled(true);
                    resetButton.setEnabled(true);
                }
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRunning) {
                    pauseTimer();
                    startButton.setEnabled(true);
                    pauseButton.setEnabled(false);
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
                startButton.setEnabled(true);
                pauseButton.setEnabled(false);
                resetButton.setEnabled(false);
            }
        });
    }

    private void startTimer() {
        isRunning = true;
        handler.sendEmptyMessage(1);
    }

    private void pauseTimer() {
        isRunning = false;
        handler.removeMessages(1);
    }

    private void resetTimer() {
        isRunning = false;
        handler.removeMessages(1);
        seconds = 0;
        updateTimer();
    }

    private void updateTimer() {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        timerTextView.setText(String.format("%02d:%02d:%02d", hours, minutes, secs));
        if (isRunning) {
            seconds++;
            handler.sendEmptyMessageDelayed(1, 1000);
        }
    }
}
