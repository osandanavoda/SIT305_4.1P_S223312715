package com.example.sit305_task41p;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddActivity extends AppCompatActivity {

    EditText taskname_input, task_description, editTextDate;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);

        taskname_input = findViewById(R.id.taskname_input);
        task_description = findViewById(R.id.task_description);
        editTextDate = findViewById(R.id.editTextDate);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addTask(taskname_input.getText().toString().trim(),
                        task_description.getText().toString().trim(),
                        Integer.valueOf(editTextDate.getText().toString().trim())
                );

            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private boolean validateInput() {
        String taskName = taskname_input.getText().toString().trim();
        String taskDescription = task_description.getText().toString().trim();
        String dueDate = editTextDate.getText().toString().trim();

        if (taskName.isEmpty()) {
            taskname_input.setError("Task name is required");
            return false;
        }

        if (taskDescription.isEmpty()) {
            task_description.setError("Task description is required");
            return false;
        }

        if (dueDate.isEmpty()) {
            editTextDate.setError("Due date is required");
            return false;
        }


        return true;
    }



}