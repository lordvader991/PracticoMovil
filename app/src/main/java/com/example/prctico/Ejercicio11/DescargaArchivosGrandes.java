package com.example.prctico.Ejercicio11;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.example.prctico.R;

public class DescargaArchivosGrandes extends AppCompatActivity{

    private ProgressBar progressBar;
    private TextView progressText;
    private Button startButton;
    private ExecutorService executorService;
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descarga_archivos_grandes);

        progressBar = findViewById(R.id.progressBar);
        progressText = findViewById(R.id.progressText);
        startButton = findViewById(R.id.startButton);

        executorService = Executors.newFixedThreadPool(1);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDownload();
            }
        });
    }

    private void startDownload() {
        startButton.setEnabled(false);
        progressBar.setProgress(0);
        progressText.setText("0%");

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                for (int progress = 0; progress <= 100; progress += 10) {
                    simulateDownload();
                    updateProgress(progress);
                }
            }
        });
    }

    private void simulateDownload() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void updateProgress(final int progress) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                progressBar.setProgress(progress);
                progressText.setText(progress + "%");
                if (progress == 100) {
                    startButton.setEnabled(true);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}
