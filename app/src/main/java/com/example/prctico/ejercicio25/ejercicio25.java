package com.example.prctico.ejercicio25;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.prctico.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ejercicio25 extends AppCompatActivity {
    private EditText numberEditText;
    private Button calculateButton, sumar;
    private TextView resultTextView;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio25);
        numberEditText = findViewById(R.id.numberEditText);
        calculateButton = findViewById(R.id.calculateButton);
        resultTextView = findViewById(R.id.resultTextView);
        handler = new Handler(Looper.getMainLooper());

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numberStr = numberEditText.getText().toString();
                int number = Integer.parseInt(numberStr);
                Sumas sumas = new Sumas(number);
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                Future<Long> futureResult = executorService.submit(sumas);
                executorService.shutdown();
                handleResult(futureResult);
            }
        });
    }
    private void handleResult(Future<Long> futureResult){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    long sumaResult = futureResult.get();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            resultTextView.setText("La suma es: "+sumaResult);
                        }
                    });
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}