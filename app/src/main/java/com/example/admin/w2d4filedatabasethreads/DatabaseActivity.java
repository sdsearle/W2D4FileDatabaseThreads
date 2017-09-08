package com.example.admin.w2d4filedatabasethreads;

import android.app.PendingIntent;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DatabaseActivity extends AppCompatActivity {

    TextView tvUsers;
    EditText etEmail;
    public static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        tvUsers = (TextView) findViewById(R.id.tvUsers);
        etEmail = (EditText) findViewById(R.id.etGetEmail);

        changeTextView();

    }

    private void changeTextView() {
        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());
        ArrayList<String[]> users = databaseHelper.getContacts();
        String user = "";
        Log.d(TAG, "onCreate: " + users.size());
        if (users != null) {
            for (String[] s : users) {
                user += "NAME: " + s[0] + " " + s[1] + " EMAIL: " + s[2] +
                        " FAV COLOR: " + s[3] + "BLOOD TYPE: " + s[4] + "\n";
               // Log.d(TAG, "onCreate: forloop" + "NAME: " + s[0] + " " + s[1] + " EMAIL: " + s[2] +
               //         " FAV COLOR: " + s[3] + "BLOOD TYPE: " + s[4]);
            }
            tvUsers.setText(user);
        }
    }

    public void createUser(View view) {
        Intent createUser = new Intent(this, CreateUser.class);
        //startActivity(createUser);
        PendingIntent pendingIntent =
                TaskStackBuilder.create(this)
                        .addNextIntentWithParentStack(createUser)
                        .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentIntent(pendingIntent);
        try {
            pendingIntent.send(getBaseContext(), 1, createUser);
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }

    public void getUser(View view) {
        Intent userIntent = new Intent(this, UserActivity.class);
        userIntent.putExtra("EMAIL",etEmail.getText().toString());
        //startActivity(createUser);
        PendingIntent pendingIntent =
                TaskStackBuilder.create(this)
                        .addNextIntentWithParentStack(userIntent)
                        .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentIntent(pendingIntent);
        try {
            pendingIntent.send(getBaseContext(), 0, userIntent);
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(View view) {
        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());
        databaseHelper.deleteContact(etEmail.getText().toString());
        changeTextView();

    }
}
