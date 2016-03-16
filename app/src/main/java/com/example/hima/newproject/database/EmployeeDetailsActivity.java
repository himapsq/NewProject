package com.example.hima.newproject.database;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.hima.newproject.R;

/**
 * "EmployeeDetailsActivity" class  for displaying all data of entered employee details
 */
public class EmployeeDetailsActivity extends AppCompatActivity {

    ListView listView;

    //giving link between "EmployeeDatabase" class and to this current class
    private EmployeeDatabase mEmployeeDatabase;

    Cursor cursor;
    SimpleCursorAdapter simpleCursorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_list_activity);

        listView = (ListView) findViewById(R.id.listView);
        mEmployeeDatabase =new EmployeeDatabase(this);
        mEmployeeDatabase.open();

        cursor = mEmployeeDatabase.getDetails();
        simpleCursorAdapter =new SimpleCursorAdapter(this, R.layout.employee_list_item, cursor,new String[]
                {EmployeeDatabase.FIELD_ID,
                EmployeeDatabase.FIELD_USERNAME,
                EmployeeDatabase.FIELD_USER_SALARY,
                EmployeeDatabase.FIELD_USER_SUBJECT},
                new int[]{R.id.textview1,
                R.id.textview2,R.id.textview3,R.id.textview4},0);

        listView.setAdapter(simpleCursorAdapter);
        simpleCursorAdapter.changeCursor(cursor);

    }
}
