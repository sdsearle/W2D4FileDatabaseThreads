package com.example.admin.w2d4filedatabasethreads;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by admin on 9/7/2017.
 */

public class AsyncDatabaseTask extends AsyncTask {
    Context context;

    public AsyncDatabaseTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected Object doInBackground(Object[] objects) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
    }
}
