package com.anirudh.succinct;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PostAnalysis extends Activity
{
    String tR = "";
    private Button mEditButton;
    private Button mImpTermsButton;
    private Button mQuizletButton;
    private Button mShareButton;
    private Button mSimResButton;
    private Button mDicButton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_analysis);
        Intent intent = getIntent();
        tR = intent.getStringExtra("result");

        mEditButton = (Button) findViewById(R.id.editButton);
        mEditButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(PostAnalysis.this, EditActivity.class);
                intent.putExtra("result", tR);
                startActivity(intent);
            }
        });

        mImpTermsButton = (Button) findViewById(R.id.impTermsButton);
        mImpTermsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(PostAnalysis.this, ImpTerms.class);
                intent.putExtra("result", tR);
                startActivity(intent);
            }
        });

        mQuizletButton = (Button) findViewById(R.id.quizletButton);
        mQuizletButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(PostAnalysis.this, QuizletActivity.class);
                intent.putExtra("result", tR);
                startActivity(intent);
            }
        });

        mShareButton = (Button) findViewById(R.id.shareButton);
        mShareButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(PostAnalysis.this, ShareActivity.class);
                intent.putExtra("result", tR);
                startActivity(intent);
            }
        });

        mSimResButton = (Button) findViewById(R.id.simResButton);
        mSimResButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(PostAnalysis.this, SimResActivity.class);
                intent.putExtra("result", tR);
                startActivity(intent);
            }
        });

        mDicButton = (Button) findViewById(R.id.dicButton);
        mDicButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(PostAnalysis.this, DicActivity.class);
                intent.putExtra("result", tR);
                startActivity(intent);
            }
        });
    }
}
