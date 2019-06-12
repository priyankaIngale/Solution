package com.droidrank.sample.sqlite_demo;



import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.droidrank.sample.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Sqlite_MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String DATABASE_NAME = "myemployeedatabase";

    TextView textViewViewEmployees;
    EditText editTextName, editTextSalary;
    Spinner spinnerDepartment;
    Button buttonAddEmployee;

    SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite_activity_main);

        textViewViewEmployees = (TextView) findViewById(R.id.textViewViewEmployees);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextSalary = (EditText) findViewById(R.id.editTextSalary);
        spinnerDepartment = (Spinner) findViewById(R.id.spinnerDepartment);
        buttonAddEmployee = (Button) findViewById(R.id.buttonAddEmployee);

        buttonAddEmployee.setOnClickListener(this);
        textViewViewEmployees.setOnClickListener(this);

        //creating a database
        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        createEmployeeTable();
    }

    //this method will validate the name and salary
    //dept does not need validation as it is a spinner and it cannot be empty
    private boolean inputsAreCorrect(String name, String salary) {
        if (name.isEmpty()) {
            editTextName.setError("Please enter a name");
            editTextName.requestFocus();
            return false;
        }

        if (salary.isEmpty() || Integer.parseInt(salary) <= 0) {
            editTextSalary.setError("Please enter salary");
            editTextSalary.requestFocus();
            return false;
        }
        return true;
    }

    private void createEmployeeTable() {
        mDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS employees (\n" +
                        "    id int NOT NULL CONSTRAINT employees_pk PRIMARY KEY,\n" +
                        "    name varchar(200) NOT NULL,\n" +
                        "    department varchar(200) NOT NULL,\n" +
                        "    joiningdate datetime NOT NULL,\n" +
                        "    salary double NOT NULL\n" +
                        ");"
        );
    }

    //In this method we will do the create operation


    private void addEmployee() {

        String name = editTextName.getText().toString().trim();
        String salary = editTextSalary.getText().toString().trim();
        String dept = spinnerDepartment.getSelectedItem().toString();

        //getting the current time for joining date
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String joiningDate = sdf.format(cal.getTime());

        //validating the inptus
        if (inputsAreCorrect(name, salary)) {

            String insertSQL = "INSERT INTO employees \n" +
                    "(name, department, joiningdate, salary)\n" +
                    "VALUES \n" +
                    "(?, ?, ?, ?);";

            //using the same method execsql for inserting values
            //this time it has two parameters
            //first is the sql string and second is the parameters that is to be binded with the query
            mDatabase.execSQL(insertSQL, new String[]{name, dept, joiningDate, salary});

            Toast.makeText(this, "Employee Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonAddEmployee:

                addEmployee();

                break;
            case R.id.textViewViewEmployees:

                startActivity(new Intent(this, Sqlite_EmployeeActivity.class));

                break;
        }
    }
}