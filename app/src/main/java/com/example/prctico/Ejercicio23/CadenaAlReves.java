package com.example.prctico.Ejercicio23;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import com.example.prctico.R;

public class CadenaAlReves extends AppCompatActivity {
    private EditText inputEditText;
    private Button checkButton;
    private TextView resultTextView;
    private Handler handler;
    private int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadena_reves);
        inputEditText = findViewById(R.id.numberEditText);
        checkButton = findViewById(R.id.calculateButton);
        resultTextView = findViewById(R.id.resultTextView);
        handler = new Handler(Looper.getMainLooper());

        //Cadena al reves
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputStr = inputEditText.getText().toString();

                ImprimirAlReves imprimirAlReves = new ImprimirAlReves(inputStr);
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                Future<String> futureResult = executorService.submit(imprimirAlReves);
                executorService.shutdown();
                handleResult(futureResult);
            }
        });

    }

    private void handleResult(Future<String> futureResult) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String reversedStr = futureResult.get();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            resultTextView.setText("Cadena al revés: " + reversedStr);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
