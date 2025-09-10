package com.fitness_tracer;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvSteps, tvCalories, tvWorkouts;
    ProgressBar progressSteps;
    Button btnAddActivity;
    DBHelper dbHelper;
    int totalSteps = 0, totalCalories = 0, totalWorkouts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSteps = findViewById(R.id.tvSteps);
        tvCalories = findViewById(R.id.tvCalories);
        tvWorkouts = findViewById(R.id.tvWorkouts);
        progressSteps = findViewById(R.id.progressSteps);
        btnAddActivity = findViewById(R.id.btnAddActivity);

        dbHelper = new DBHelper(this);
        loadData();

        btnAddActivity.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, activity_add.class);
            startActivity(intent);
        });
    }

    private void loadData() {
        Cursor cursor = dbHelper.getAllActivities();
        totalSteps = 0;
        totalCalories = 0;
        totalWorkouts = 0;

        while (cursor.moveToNext()) {
            totalSteps += cursor.getInt(1);
            totalCalories += cursor.getInt(2);
            totalWorkouts++;
        }

        tvSteps.setText("Steps: " + totalSteps + "/10000");
        tvCalories.setText("Calories Burned: " + totalCalories);
        tvWorkouts.setText("Workouts Logged: " + totalWorkouts);
        progressSteps.setProgress(totalSteps);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }
}
