package com.example.hima.newproject.database;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.hima.newproject.R;

public class EmployeeActivity extends AppCompatActivity {

    private EditText mName, mSubject, mSalary;
    private Button save, send;
    private EmployeeDatabase employeeDatabase;
    private Cursor cursor;
    private SimpleCursorAdapter simpleCursorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);                                                                            
        employeeDatabase = new EmployeeDatabase(this);
        mName = (EditText) findViewById(R.id.name);
        mSubject = (EditText) findViewById(R.id.subject);
        mSalary = (EditText) findViewById(R.id.salary);

        save = (Button) findViewById(R.id.save1);
        send = (Button) findViewById(R.id.send1);

        employeeDatabase.open();

        cursor = employeeDatabase.getDetails();
        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.employee_list_item, cursor, new String[]{
                EmployeeDatabase.FIELD_ID, "tname", "tsalary", "tsubject"},
                new int[]{R.id.textview1,
                        R.id.textview2, R.id.textview3, R.id.textview4}, 0);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tname = mName.getText().toString();
                String tsalry = mSalary.getText().toString();
                String tsubject = mSubject.getText().toString();

                employeeDatabase.insertDetails(tname, Integer.parseInt(tsalry), tsubject);
                Toast.makeText(EmployeeActivity.this, "Inserted successfully", Toast.LENGTH_LONG).show();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmployeeActivity.this, EmployeeDetailsActivity.class);
                startActivity(intent);

            }
        });
    }
}
