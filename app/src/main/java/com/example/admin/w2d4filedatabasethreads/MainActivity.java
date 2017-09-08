package com.example.admin.w2d4filedatabasethreads;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvFile = (TextView) findViewById(R.id.tvFile);

        AsyncFileTask filetask = new AsyncFileTask(tvFile,this);
        AsyncDatabaseTask databaseTask = new AsyncDatabaseTask(this);
        //filetask.execute("test.txt", "this is a sample file");
        filetask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "test.txt", "this is a sample file.");
        databaseTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    public void goToDatabase(View view) {
        Intent databaseIntent = new Intent(this, DatabaseActivity.class);

        PendingIntent pendingIntent =
                TaskStackBuilder.create(this)
                        .addNextIntentWithParentStack(databaseIntent)
                        .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentIntent(pendingIntent);
        try {
            pendingIntent.send(getBaseContext(),0,databaseIntent);
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }
}
