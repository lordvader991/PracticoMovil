package com.example.prctico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button ejercicio1,ejercicio3,ejercicio5,ejercicio7,ejercicio9,ejercicio11, ejercicio13,ejercicio15,ejercicio17,ejercicio19,ejercicio21,ejercicio23,ejercicio25, ejercicio31;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ejercicio21 = findViewById(R.id.ejercicio21);
        ejercicio3 = findViewById(R.id.ejercicio3);
        ejercicio7 = findViewById(R.id.ejercicio7);
        ejercicio31 = findViewById(R.id.ejercicio31);
        ejercicio13 = findViewById(R.id.ejercicio13);
        ejercicio1 = findViewById(R.id.ejercicio1);
        ejercicio5 = findViewById(R.id.ejercicio5);
        ejercicio9 = findViewById(R.id.ejercicio9);
        ejercicio11 = findViewById(R.id.ejercicio11);
        ejercicio15=findViewById(R.id.ejercicio15);
        ejercicio17 = findViewById(R.id.ejercicio17);
        ejercicio19 = findViewById(R.id.ejercicio19);
        ejercicio23=findViewById(R.id.ejercicio23);
        ejercicio25=findViewById(R.id.ejercicio25);
        ejercicio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.example.prctico.Ejercicio1.DescargaImagenes.class);
                startActivity(intent);
            }
        });

        ejercicio21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.example.prctico.Ejercicio21.ejercicio21.class);
                startActivity(intent);
            }
        });
        ejercicio3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.example.prctico.Ejercicio3.SenderActivity.class);
                startActivity(intent);
            }
        });
        ejercicio5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.example.prctico.Ejercicio5.MensajesActividades.class);
                startActivity(intent);
            }
        });

        ejercicio7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.example.prctico.Ejercicio7.ejercicio7.class);
                startActivity(intent);
            }
        });
        ejercicio9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.example.prctico.Ejercicio9.Cronometro.class);
                startActivity(intent);
            }
        });
        ejercicio11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.example.prctico.Ejercicio11.DescargaArchivosGrandes.class);
                startActivity(intent);
            }
        });

        ejercicio13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.example.prctico.Ejercicio13.ejercicio13.class);
                startActivity(intent);
            }
        });
        ejercicio15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.example.prctico.Ejercicio15.ProcesamientoImagenes.class);
                startActivity(intent);
            }
        });
        ejercicio17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.example.prctico.Ejercicio17.MayorEnteros.class);
                startActivity(intent);
            }
        });
        ejercicio19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.example.prctico.Ejercicio19.SumarNnaturales.class);
                startActivity(intent);
            }
        });
        ejercicio23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.example.prctico.Ejercicio23.CadenaAlReves.class);
                startActivity(intent);
            }
        });
        ejercicio25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.example.prctico.ejercicio25.ejercicio25.class);
                startActivity(intent);
            }
        });
        ejercicio31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.example.prctico.Ejercicio3.RecieverActivity.class);
                startActivity(intent);
            }
        });


    }
}