package com.example.prctico.Ejercicio15;
import com.example.prctico.R;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProcesamientoImagenes extends AppCompatActivity{

    private EditText imageNameEditText;
    private Button addImageButton;
    private Button processButton;
    private ExecutorService executorService;
    private Handler mainHandler;
    private List<String> imageNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procesamiento_imagenes);

        mainHandler = new Handler(Looper.getMainLooper());
        executorService = Executors.newFixedThreadPool(3); // Puedes ajustar el número de hilos

        imageNameEditText = findViewById(R.id.imageNameEditText);
        addImageButton = findViewById(R.id.addImageButton);
        processButton = findViewById(R.id.processButton);

        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imageName = imageNameEditText.getText().toString();
                if (!imageName.isEmpty()) {
                    imageNames.add(imageName);
                    Toast.makeText(ProcesamientoImagenes.this, "Imagen agregada: " + imageName, Toast.LENGTH_SHORT).show();
                    imageNameEditText.getText().clear();
                }
            }
        });

        processButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!imageNames.isEmpty()) {
                    processImagesInParallel();
                } else {
                    Toast.makeText(ProcesamientoImagenes.this, "Agregue al menos una imagen", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void processImagesInParallel() {
        for (String imageName : imageNames) {
            executorService.submit(new ImageProcessorRunnable(imageName));
        }
    }

    private class ImageProcessorRunnable implements Runnable {

        private String imageName;

        public ImageProcessorRunnable(String imageName) {
            this.imageName = imageName;
        }

        @Override
        public void run() {
            simulateProcessing(); // Simula el procesamiento de la imagen

            // Mostrar un mensaje cuando termine el procesamiento simulado
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(ProcesamientoImagenes.this, "Procesamiento completado: " + imageName, Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void simulateProcessing() {
            // Simula el procesamiento con un retardo
            try {
                Thread.sleep(2000); // Simula un procesamiento de 2 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown(); // Asegúrate de cerrar el ExecutorService al final
    }
}
