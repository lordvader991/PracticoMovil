package com.example.prctico.Ejercicio21;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.prctico.R;

public class ejercicio21 extends AppCompatActivity {
    EditText numero1, numero2;
    TextView respuesta;
    Button calcular;
    private Handler handler = new Handler(Looper.getMainLooper()) {
        public void handleMessage (Message message){
            respuesta.setText(message.obj.toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio21);

        numero1 = findViewById(R.id.numero1);
        numero2 = findViewById(R.id.numero2);
        respuesta = findViewById(R.id.respuesta);
        calcular = findViewById(R.id.calcular);

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int num1 = Integer.parseInt(numero1.getText().toString());
                        int num2 = Integer.parseInt(numero2.getText().toString());
                        int  mcd = calcularMCD(num1,num2);
                        Message message = new Message();
                        message.obj = mcd;
                        handler.sendMessage(message);
                    }
                }).start();
            }
        });
    }
    private int calcularMCD(int numeros1, int numeros2){
        if(numeros2 == 0){
            return numeros1;
        }else{
            return  calcularMCD(numeros2,numeros1 % numeros2);
        }
    }
}