package com.example.contact;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class EmployeeActivity extends AppCompatActivity {

    private EditText edtEmployeeId, edtName, edtPosition, edtEmail, edtPhone;
    private Button btnAddEmployee, btnSearchEmployee;
    private ListView lvEmployees;
    private DatabaseHelper dbHelper;
    private EmployeeAdapter employeeAdapter;
    private List<Employee> employeeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_employee);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHelper = new DatabaseHelper(this);
        employeeList = new ArrayList<>();

        edtEmployeeId = findViewById(R.id.edtEmployeeId);
        edtName = findViewById(R.id.edtName);
        edtPosition = findViewById(R.id.edtPosition);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhone = findViewById(R.id.edtPhone);
        btnAddEmployee = findViewById(R.id.btnAddEmployee);
        btnSearchEmployee = findViewById(R.id.btnSearchEmployee);
        lvEmployees = findViewById(R.id.lvEmployees);

        employeeAdapter = new EmployeeAdapter(this, employeeList);
        lvEmployees.setAdapter(employeeAdapter);

        btnAddEmployee.setOnClickListener(view -> addEmployee());
        btnSearchEmployee.setOnClickListener(view -> searchEmployees());

        searchEmployees(); // Load initial data
    }

    private void addEmployee() {
        String employeeId = edtEmployeeId.getText().toString();
        String name = edtName.getText().toString();
        String position = edtPosition.getText().toString();
        String email = edtEmail.getText().toString();
        String phone = edtPhone.getText().toString();

        if (employeeId.isEmpty() || name.isEmpty() || position.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_EMPLOYEE_ID, employeeId);
        values.put(DatabaseHelper.COLUMN_EMPLOYEE_NAME, name);
        values.put(DatabaseHelper.COLUMN_POSITION, position);
        values.put(DatabaseHelper.COLUMN_EMPLOYEE_EMAIL, email);
        values.put(DatabaseHelper.COLUMN_EMPLOYEE_PHONE, phone);

        long newRowId = db.insert(DatabaseHelper.TABLE_EMPLOYEES, null, values);
        db.close();

        if (newRowId != -1) {
            Toast.makeText(this, "Employee added successfully", Toast.LENGTH_SHORT).show();
            searchEmployees(); // Refresh the list
        } else {
            Toast.makeText(this, "Error adding employee", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("Range")
    private void searchEmployees() {
        employeeList.clear();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_EMPLOYEES, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Employee employee = new Employee();
                employee.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID)));
                employee.setEmployeeId(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EMPLOYEE_ID)));
                employee.setName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EMPLOYEE_NAME)));
                employee.setPosition(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_POSITION)));
                employee.setEmail(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EMPLOYEE_EMAIL)));
                employee.setPhone(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EMPLOYEE_PHONE)));
                employeeList.add(employee);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        employeeAdapter.notifyDataSetChanged(); // Notify adapter of data changes
    }
}