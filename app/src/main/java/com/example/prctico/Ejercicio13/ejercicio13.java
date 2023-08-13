package com.example.prctico.Ejercicio13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.prctico.R;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ejercicio13 extends AppCompatActivity {
    private EditText taskEditText;
    private ListView taskListView;
    private TaskAdapter taskAdapter;
    private List<Task> tasks = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio13);
        taskEditText = findViewById(R.id.taskEditText);
        taskListView = findViewById(R.id.taskListView);
        taskAdapter = new TaskAdapter(this, tasks);
        taskListView.setAdapter(taskAdapter);
        taskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tasks.remove(position);
                taskAdapter.notifyDataSetChanged();
                ExecutorService executor = Executors.newSingleThreadExecutor();
                executor.execute(() -> {
                });
                executor.shutdown();
            }
        });
    }
    public void addTask(View view) {
        String taskDescription = taskEditText.getText().toString().trim();
        if (!taskDescription.isEmpty()) {
            Task task = new Task(taskDescription);
            tasks.add(task);
            taskAdapter.notifyDataSetChanged();
            taskEditText.setText("");
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
            });
            executor.shutdown();
        }
    }
}