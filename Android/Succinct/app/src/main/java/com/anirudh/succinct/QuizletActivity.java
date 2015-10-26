package com.anirudh.succinct;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class QuizletActivity extends Activity
{
    String tR, keyTerm;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizlet);
        Intent intent = getIntent();
        tR = intent.getStringExtra("result");
        Summarization sum = new Summarization(tR);
        keyTerm = sum.getKeyTerms().replace(" ", "-").trim();
        String URL = "https://quizlet.com/subject/"+keyTerm+"/";
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
        startActivity(browserIntent);
    }
}
