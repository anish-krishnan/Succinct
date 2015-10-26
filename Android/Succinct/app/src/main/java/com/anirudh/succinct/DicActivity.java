package com.anirudh.succinct;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DicActivity extends Activity
{
    String tR;
    String keyTerm;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dic);
        Intent intent = getIntent();
        tR = intent.getStringExtra("result");
        Summarization sum = new Summarization(tR);
        keyTerm = sum.getKeyTerms();
        List<String> terms = new ArrayList<String>();
        int x = 0;
        /*for(int y = 0; y < keyTerm.length(); y++)
        {
            if(keyTerm.charAt(y) == ' ')
            {
                terms.add(keyTerm.substring(x, y));
                x = y + 1;
            }
        }*/
        String[] termArr = keyTerm.split("\\s+");
        for (int i = 0; i < termArr.length; i++) {
            // You may want to check for a non-word character before blindly
            // performing a replacement
            // It may also be necessary to adjust the character class
            termArr[i] = termArr[i].replaceAll("[^\\w]", "");
        }
        for(int d = 0; d < termArr.length; d++)
        {
            terms.add(termArr[d]);
        }
        for(int r = 0; r < terms.size(); r++)
        {
            if(terms.get(r).equals(" ") || terms.get(r).equals(""))
            {
                terms.remove(r);
                r--;
            }
        }
        String baseUrl = "http://dictionary.reference.com/browse/";
        TextView def1 = (TextView) findViewById(R.id.def1);
        TextView def2 = (TextView) findViewById(R.id.def2);
        TextView def3 = (TextView) findViewById(R.id.def3);
        def1.setClickable(true);
        def2.setClickable(true);
        def3.setClickable(true);
        if(terms.size() > 0)
        {
            def1.setMovementMethod(LinkMovementMethod.getInstance());
            String text = "<a href='"+baseUrl+terms.get(0)+"'>"+ terms.get(0) +"</a>";
            def1.setText(Html.fromHtml(text));
            def1.setLinkTextColor(Color.WHITE);
        }
        if(terms.size() > 1)
        {
            def2.setMovementMethod(LinkMovementMethod.getInstance());
            String text = "<a href='"+baseUrl+terms.get(1)+"'>"+ terms.get(1) +"</a>";
            def2.setText(Html.fromHtml(text));
            def2.setLinkTextColor(Color.WHITE);
        }
        if(terms.size() > 2)
        {
            def3.setMovementMethod(LinkMovementMethod.getInstance());
            String text = "<a href='"+baseUrl+terms.get(2)+"'>"+ terms.get(2) +"</a>";
            def3.setText(Html.fromHtml(text));
            def3.setLinkTextColor(Color.WHITE);
        }
        if(terms.size() == 0)
            def1.setText("No Key Terms");
    }
}
