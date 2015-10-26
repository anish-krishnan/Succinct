package com.anirudh.succinct;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EditActivity extends Activity
{
    private String tR;
    private EditText mSumEditText;
    private Button mSaveTextButton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent intent = getIntent();
        tR = intent.getStringExtra("result");
        mSumEditText = (EditText)findViewById(R.id.sumEditText);
        mSumEditText.setText(tR);
        mSaveTextButton = (Button) findViewById(R.id.saveTextButton);
        mSaveTextButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int num = (int)Math.random()*999 + 1;
                EditActivity.this.saveText("savedText" + num, tR);
            }
        });
    }

    private void saveText(String sFileName, String sBody){
        try
        {
            File root = new File(Environment.getExternalStorageDirectory(), "Summaries");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(sBody);
            writer.flush();
            writer.close();
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
