package com.fitness_tracer;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class activity_add extends AppCompatActivity {
    EditText etSteps, etCalories;
    Button btnSave;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etSteps = findViewById(R.id.etSteps);
        etCalories = findViewById(R.id.etCalories);
        btnSave = findViewById(R.id.btnSave);
        dbHelper = new DBHelper(this);

        btnSave.setOnClickListener(v -> {
            int steps = Integer.parseInt(etSteps.getText().toString());
            int calories = Integer.parseInt(etCalories.getText().toString());
            dbHelper.insertActivity(steps, calories);
            Toast.makeText(this, "Activity Saved", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
