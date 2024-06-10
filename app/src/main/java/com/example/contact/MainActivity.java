package com.example.contact;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button btnManageUnits, btnManageEmployees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnManageUnits = findViewById(R.id.btnManageUnits);
        btnManageEmployees = findViewById(R.id.btnManageEmployees);

        btnManageUnits.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, UnitActivity.class);
            startActivity(intent);
        });

        btnManageEmployees.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, EmployeeActivity.class);
            startActivity(intent);
        });
    }
}