package com.example.prctico.Ejercicio19;

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

public class SumarNnaturales extends AppCompatActivity {
    private EditText inputEditText;
    private Button checkButton;
    private TextView resultTextView;
    private Handler handler;
    private int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sumar_nnaturales);
        inputEditText = findViewById(R.id.numberEditText);
        checkButton = findViewById(R.id.calculateButton);
        resultTextView = findViewById(R.id.resultTextView);
        handler = new Handler(Looper.getMainLooper());

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputStr = inputEditText.getText().toString();
                int n = Integer.parseInt(inputStr);

                SumaNaturales sumaNaturales = new SumaNaturales(n);
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                Future<Integer> futureResult = executorService.submit(sumaNaturales);
                executorService.shutdown();
                handleResult(futureResult);
            }
        });



    }


    //suna de N numeros enteros
    private void handleResult(Future<Integer> futureResult) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int sum = futureResult.get();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            resultTextView.setText("La suma de los primeros " + n + " números naturales es: " + sum);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
