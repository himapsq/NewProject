package com.example.hima.newproject.passingextras;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hima.newproject.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mUserName;
    private EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserName = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        Button mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                if (validDateFields(mUserName) && validDateFields(mPassword)) {
                    sendData();
                }
                break;
        }
    }

    /**
     * checks if entered data is empty or the characters are less than 6!!
     *
     * @param editText EditText
     * @return boolean
     */
    private boolean validDateFields(EditText editText) {
        if (getString(editText).isEmpty() || getString(editText).length() < 6) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * Transferring entered data to second screen
     * entered data is username and password
     */
    private void sendData() {
        Intent intent = new Intent(this, DashBoard.class);
        intent.putExtra("username",getString(mUserName));
        intent.putExtra("password",getString(mPassword));
        startActivity(intent);
    }

    /**
     * This method will takes and returns string from edittext
     *
     * @param et EditText
     * @return String
     */
    private String getString(EditText et) {
        return et.getText().toString().trim();
    }
}
