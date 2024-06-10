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

public class UnitActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private EditText edtUnitCode, edtUnitName, edtEmail, edtWebsite, edtAddress, edtPhone, edtParentUnitCode;
    private Button btnAddUnit, btnSearchUnit;
    private ListView lvUnits;
    private UnitAdapter unitAdapter;
    private ArrayList<Unit> unitList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_unit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHelper = new DatabaseHelper(this);

        edtUnitCode = findViewById(R.id.edtUnitCode);
        edtUnitName = findViewById(R.id.edtUnitName);
        edtEmail = findViewById(R.id.edtEmail);
        edtWebsite = findViewById(R.id.edtWebsite);
        edtAddress = findViewById(R.id.edtAddress);
        edtPhone = findViewById(R.id.edtPhone);
        edtParentUnitCode = findViewById(R.id.edtParentUnitCode);
        btnAddUnit = findViewById(R.id.btnAddUnit);
        btnSearchUnit = findViewById(R.id.btnSearchUnit);
        lvUnits = findViewById(R.id.lvUnits);

        unitList = new ArrayList<>();
        unitAdapter = new UnitAdapter(this, unitList);
        lvUnits.setAdapter(unitAdapter);

        btnAddUnit.setOnClickListener(view -> addUnit());
        btnSearchUnit.setOnClickListener(view -> searchUnits());

        searchUnits();


    }

    private void addUnit() {
        String unitCode = edtUnitCode.getText().toString();
        String unitName = edtUnitName.getText().toString();
        String email = edtEmail.getText().toString();
        String website = edtWebsite.getText().toString();
        String address = edtAddress.getText().toString();
        String phone = edtPhone.getText().toString();
        String parentUnitCode = edtParentUnitCode.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_UNIT_CODE, unitCode);
        values.put(DatabaseHelper.COLUMN_UNIT_NAME, unitName);
        values.put(DatabaseHelper.COLUMN_EMAIL, email);
        values.put(DatabaseHelper.COLUMN_WEBSITE, website);
        values.put(DatabaseHelper.COLUMN_ADDRESS, address);
        values.put(DatabaseHelper.COLUMN_PHONE, phone);
        values.put(DatabaseHelper.COLUMN_PARENT_UNIT_CODE, parentUnitCode);

        long newRowId = db.insert(DatabaseHelper.TABLE_UNITS, null, values);
        db.close();

        if (newRowId != -1) {
            Toast.makeText(this, "Unit added successfully", Toast.LENGTH_SHORT).show();
            searchUnits();
        } else {
            Toast.makeText(this, "Error adding unit", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("Range")
    private void searchUnits() {
        unitList.clear();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_UNITS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Unit unit = new Unit();
                unit.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID)));
                unit.setUnitCode(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_UNIT_CODE)));
                unit.setUnitName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_UNIT_NAME)));
                unit.setEmail(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EMAIL)));
                unit.setWebsite(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_WEBSITE)));
                unit.setAddress(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ADDRESS)));
                unit.setPhone(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PHONE)));
                unit.setParentUnitCode(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PARENT_UNIT_CODE)));
                unitList.add(unit);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        unitAdapter.notifyDataSetChanged();
    }
}