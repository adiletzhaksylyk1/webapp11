package com.example.todolistapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> tasks = new ArrayList<>();
        ListView listView = findViewById(R.id.listView);
        EditText editText = findViewById(R.id.editText);
        Button addButton = findViewById(R.id.addButton);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                tasks);
        listView.setAdapter(adapter);

        addButton.setOnClickListener(view -> {
            String taskText = editText.getText().toString().trim();
            if (!taskText.isEmpty()) {
                tasks.add(taskText);
                adapter.notifyDataSetChanged();
                editText.setText("");
                Toast.makeText(MainActivity.this,
                        "Task added!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Delete Task")
                    .setMessage("Are you sure you want to delete this task?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        tasks.remove(position);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Task deleted!",
                                Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", null)
                    .show();
        });
    }
}