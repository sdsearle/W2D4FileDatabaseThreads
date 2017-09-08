package com.example.admin.w2d4filedatabasethreads;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UserActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText etFName, etLName, etEmail, etColor, etBlood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Intent intent = getIntent();
        intent.getStringExtra("EMAIL");
         databaseHelper = new DatabaseHelper(getBaseContext());

        etFName = (EditText) findViewById(R.id.etFirst);
        etLName = (EditText) findViewById(R.id.etLast);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etColor = (EditText) findViewById(R.id.etColor);
        etBlood = (EditText) findViewById(R.id.etBType);

    }

    public void SaveData(View view) {
        databaseHelper.updateContact(etFName.getText().toString(), etLName.getText().toString(),
                etEmail.getText().toString(), etColor.getText().toString(), etBlood.getText().toString());
    }

}
