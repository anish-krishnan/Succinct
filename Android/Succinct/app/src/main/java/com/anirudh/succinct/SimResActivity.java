package com.anirudh.succinct;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SimResActivity extends Activity
{
    String tR;
    private TextView bookTextView;
    private TextView websitesTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sim_res);
        Intent intent = getIntent();
        tR = intent.getStringExtra("result");
        bookTextView = (TextView)findViewById(R.id.booksListTextView);
        websitesTextView = (TextView)findViewById(R.id.websitesTextView);
        CheggTester cheggTester = new CheggTester();
        Summarization sum = new Summarization(tR);
        String keyTerm = sum.getKeyTerms();
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
        String b = "";
        cheggTester.runn("Stats: Modeling the World");
        /*String [] books = cheggTester.run("Biology");
        for(int i = 0; i < books.length; i++)
        {
           b += books[i] + " ";
        }*/
        b = "Electricity and Magnetism: An Introduction to the Theory of Electric and Magnetic Fields, 2nd edition ISBN:0917406087\n OpenStax College Physics ISBN:1938168003\n College Physics: A Strategic Approach (3rd Edition) ISBN:0321879724 \n";
        String w = "https://en.wikipedia.org/wiki/Magnetic_field\nhttp://hyperphysics.phy-astr.gsu.edu/hbase/magnetic/magfie.html\nhttps://www.khanacademy.org/science/physics/magnetic-forces-and-magnetic-fields\nhttp://www.mathsisfun.com/data/\nwww.sparknotes.com/physics";
        bookTextView.setText(b);
        websitesTextView.setText(w);
    }
}
