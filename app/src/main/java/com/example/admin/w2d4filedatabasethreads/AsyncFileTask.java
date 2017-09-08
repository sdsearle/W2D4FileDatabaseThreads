package com.example.admin.w2d4filedatabasethreads;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by admin on 9/7/2017.
 */

public class AsyncFileTask extends AsyncTask<String, Integer, File> {
    TextView textView;
    private Context context;
    String filename;

    public AsyncFileTask(TextView textView, Context c) {
        this.textView = textView;
        context = c;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        textView.setText("Preparing File");
    }

    @Override
    protected File doInBackground(String... strings) {
        filename = strings[0];
        File file = new File(context.getFilesDir(), filename);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream outputStream;

        try {
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(strings[1].getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(File f) {
        super.onPostExecute(f);
        BufferedReader reader = null;
        String text = "";
       // Log.d(TAG, "onPostExecute: " + context.get);
        try {
            reader = new BufferedReader(new FileReader(f));
            String line;
            while ((line = reader.readLine()) != null) {
                text += line;
                text += '\n';
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        textView.setText(text);
    }
}

