package com.anirudh.succinct;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ImpTerms extends Activity
{
    String tR;
    TextView mSummaryTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imp_terms);
        mSummaryTextView = (TextView) findViewById(R.id.summaryTextView);
        Intent intent = getIntent();
        tR = intent.getStringExtra("result");
        getSummary();
    }

    private void getSummary()
    {
        Summarization sum = new Summarization(tR);
        String [] summary = sum.shorten(sum.getTopSentences());
        String sumResult = "";
        for (int i = 0; i < summary.length; i++)
        {
            sumResult += summary[i] + " ";
        }
        mSummaryTextView.setText(sumResult);
    }
}
