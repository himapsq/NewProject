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

/**
 * Views are 3 edittexts and 2 buttons
 * mName----------- 1st edittext to enter employee name
 * mSalary--------- 2nd edittext to enter employee saalry
 * mSubject-------- 3rd edittext to enter employee subject
 *
 * mSave-------- 1st button for saving data into the database after entering details
 * mSend-------- 2nd button for moving to next screen,, in 2nd screen entered details are displayed
 */

public class EmployeeActivity extends AppCompatActivity implements  View.OnClickListener {

    private EditText mName, mSubject, mSalary;
    private Button mSave, mSend;
    private EmployeeDatabase mEmployeeDatabase;
    private SimpleCursorAdapter mSimpleCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);                                                                            
        mEmployeeDatabase = new EmployeeDatabase(this);
        mName = (EditText) findViewById(R.id.name);
        mSubject = (EditText) findViewById(R.id.subject);
        mSalary = (EditText) findViewById(R.id.salary);

        mSave = (Button) findViewById(R.id.save1);
        mSend = (Button) findViewById(R.id.send1);

        //calling method open() for opening database --- method is written in "EmployeeDatabase" class
        mEmployeeDatabase.open();

        //getting details of entered data of employees into "Cursor"  and an userdefined method "getDetails"
         Cursor cursor = mEmployeeDatabase.getDetails();

        /**
         * assigning link with  "mSimpleCursorAdapter" and  xml file "employee_list_item"
         * "employee_list_item" is for viewing each item in each list in listview
         */
        mSimpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.employee_list_item, cursor,
                new String[]{EmployeeDatabase.FIELD_ID,EmployeeDatabase.FIELD_USERNAME, EmployeeDatabase.FIELD_USER_SALARY,
                        EmployeeDatabase.FIELD_USER_SUBJECT}, new int[]{R.id.textview1,
                        R.id.textview2, R.id.textview3, R.id.textview4}, 0);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            //for saving data into the database after entering details
            case R.id.save1:
                String tname = mName.getText().toString();
                String tsalry = mSalary.getText().toString();
                String tsubject = mSubject.getText().toString();

                mEmployeeDatabase.insertDetails(tname, Integer.parseInt(tsalry), tsubject);
                Toast.makeText(EmployeeActivity.this, "Inserted successfully", Toast.LENGTH_LONG).show();
                break;

            //for moving to next screen,, in 2nd screen entered details are displayed
            case  R.id.send1:
                Intent intent = new Intent(EmployeeActivity.this, EmployeeDetailsActivity.class);
                startActivity(intent);
                break;
        }
    }
}
