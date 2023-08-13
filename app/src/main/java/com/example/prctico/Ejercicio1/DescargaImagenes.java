package com.example.prctico.Ejercicio1;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prctico.R;

public class DescargaImagenes extends AppCompatActivity{

    private EditText imageNameEditText;
    private Button downloadButton;
    private ListView imageListView;
    private ArrayAdapter<String> adapter;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            String message = (String) msg.obj;
            // Aquí mostrarías el mensaje en la consola o actualizarías la interfaz gráfica
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descarga_imagenes);

        imageNameEditText = findViewById(R.id.imageNameEditText);
        downloadButton = findViewById(R.id.downloadButton);
        imageListView = findViewById(R.id.imageListView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        imageListView.setAdapter(adapter);

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String imageName = imageNameEditText.getText().toString();

                Thread downloadThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // Simula la descarga
                        Log.d("DescargaImagenes", "Descargando " + imageName);

                        try {
                            // Simula un tiempo de espera para la descarga
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        // Simula el guardado
                        Log.d("DescargaImagenes", "Guardado " + imageName);

                        // Agrega el nombre a la lista simulada
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.add(imageName);
                            }
                        });
                    }
                });

                downloadThread.start();

            }
        });
    }
}
