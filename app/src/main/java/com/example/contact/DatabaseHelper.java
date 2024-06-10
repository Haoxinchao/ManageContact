package com.example.contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "university.db";
    private static final int DATABASE_VERSION = 2;

    public static final String TABLE_UNITS = "units";
    public static final String TABLE_EMPLOYEES = "employees";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_UNIT_CODE = "unit_code";
    public static final String COLUMN_UNIT_NAME = "unit_name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_WEBSITE = "website";
    public static final String COLUMN_LOGO = "logo";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_PARENT_UNIT_CODE = "parent_unit_code";

    public static final String COLUMN_EMPLOYEE_ID = "employee_id";
    public static final String COLUMN_EMPLOYEE_NAME = "employee_name";
    public static final String COLUMN_POSITION = "position";
    public static final String COLUMN_EMPLOYEE_EMAIL = "employee_email";
    public static final String COLUMN_EMPLOYEE_PHONE = "employee_phone";
    public static final String COLUMN_AVATAR = "avatar";

    private static final String TABLE_CREATE_UNITS =
            "CREATE TABLE " + TABLE_UNITS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_UNIT_CODE + " TEXT, " +
                    COLUMN_UNIT_NAME + " TEXT, " +
                    COLUMN_EMAIL + " TEXT, " +
                    COLUMN_WEBSITE + " TEXT, " +
                    COLUMN_LOGO + " BLOB, " +
                    COLUMN_ADDRESS + " TEXT, " +
                    COLUMN_PHONE + " TEXT, " +
                    COLUMN_PARENT_UNIT_CODE + " TEXT" +
                    ");";

    private static final String TABLE_CREATE_EMPLOYEES =
            "CREATE TABLE " + TABLE_EMPLOYEES + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_EMPLOYEE_ID + " TEXT, " +
                    COLUMN_EMPLOYEE_NAME + " TEXT, " +
                    COLUMN_POSITION + " TEXT, " +
                    COLUMN_EMPLOYEE_EMAIL + " TEXT, " +
                    COLUMN_EMPLOYEE_PHONE + " TEXT, " +
                    COLUMN_AVATAR + " BLOB" +
                    ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_UNITS);
        db.execSQL(TABLE_CREATE_EMPLOYEES);
        insertSampleData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UNITS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEES);
        onCreate(db);
    }

    private void insertSampleData(SQLiteDatabase db) {
        // Insert sample data into units table
        ContentValues unitValues = new ContentValues();
        unitValues.put(COLUMN_UNIT_CODE, "CS");
        unitValues.put(COLUMN_UNIT_NAME, "Computer Science Department");
        unitValues.put(COLUMN_EMAIL, "cs@university.edu");
        unitValues.put(COLUMN_WEBSITE, "http://cs.university.edu");
        unitValues.put(COLUMN_ADDRESS, "123 University St.");
        unitValues.put(COLUMN_PHONE, "1234567890");
        db.insert(TABLE_UNITS, null, unitValues);

        unitValues.put(COLUMN_UNIT_CODE, "EE");
        unitValues.put(COLUMN_UNIT_NAME, "Electrical Engineering Department");
        unitValues.put(COLUMN_EMAIL, "ee@university.edu");
        unitValues.put(COLUMN_WEBSITE, "http://ee.university.edu");
        unitValues.put(COLUMN_ADDRESS, "456 University St.");
        unitValues.put(COLUMN_PHONE, "0987654321");
        db.insert(TABLE_UNITS, null, unitValues);

        // Insert sample data into employees table
        ContentValues employeeValues = new ContentValues();
        employeeValues.put(COLUMN_EMPLOYEE_ID, "E001");
        employeeValues.put(COLUMN_EMPLOYEE_NAME, "John Doe");
        employeeValues.put(COLUMN_POSITION, "Professor");
        employeeValues.put(COLUMN_EMPLOYEE_EMAIL, "johndoe@university.edu");
        employeeValues.put(COLUMN_EMPLOYEE_PHONE, "1231231234");
        db.insert(TABLE_EMPLOYEES, null, employeeValues);

        employeeValues.put(COLUMN_EMPLOYEE_ID, "E002");
        employeeValues.put(COLUMN_EMPLOYEE_NAME, "Jane Smith");
        employeeValues.put(COLUMN_POSITION, "Lecturer");
        employeeValues.put(COLUMN_EMPLOYEE_EMAIL, "janesmith@university.edu");
        employeeValues.put(COLUMN_EMPLOYEE_PHONE, "4321432143");
        db.insert(TABLE_EMPLOYEES, null, employeeValues);
    }
}
