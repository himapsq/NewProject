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

    ListView l1;

    //giving link between "EmployeeDatabase" class and to this current class
    private EmployeeDatabase m=new EmployeeDatabase(this);

    Cursor c;
    SimpleCursorAdapter s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_list_activity);

        l1= (ListView) findViewById(R.id.listView);

        m.open();

        c=m.getDetails();
        s=new SimpleCursorAdapter(this, R.layout.employee_list_item,c,new String[]{EmployeeDatabase.FIELD_ID,
                EmployeeDatabase.FIELD_USERNAME,
                EmployeeDatabase.FIELD_USER_SALARY,
                EmployeeDatabase.FIELD_USER_SUBJECT},
                new int[]{R.id.textview1,
                R.id.textview2,R.id.textview3,R.id.textview4},0);

        l1.setAdapter(s);
        s.changeCursor(c);

    }
}
