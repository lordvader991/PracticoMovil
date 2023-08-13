package com.example.prctico.Ejercicio17;

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

public class MayorEnteros extends AppCompatActivity {
    private EditText inputEditText;
    private Button checkButton;
    private TextView resultTextView;
    private Handler handler;
    private int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mayor_enteros);
        inputEditText = findViewById(R.id.numberEditText);
        checkButton = findViewById(R.id.calculateButton);
        resultTextView = findViewById(R.id.resultTextView);
        handler = new Handler(Looper.getMainLooper());

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputStr = inputEditText.getText().toString();
                String[] numbersStr = inputStr.split(" "); // Dividir la cadena en números
                int[] numbers = new int[numbersStr.length];

                for (int i = 0; i < numbersStr.length; i++) {
                    numbers[i] = Integer.parseInt(numbersStr[i]);
                }

                MayorFinder mayorFinder = new MayorFinder(numbers);
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                Future<Integer> futureResult = executorService.submit(mayorFinder);
                executorService.shutdown();
                handleResult(futureResult);
            }
        });

    }


    private void handleResult(Future<Integer> futureResult) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int largestNumber = futureResult.get();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            resultTextView.setText("El número mayor es: " + largestNumber);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}



