package com.probein.quotes;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
public TextView textView;
public Button share,next,previous;
public ArrayList<Quote> quoteList;
public int index;
public Stack<Quote> previousQuote;
public  boolean isPrevious;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        share = findViewById(R.id.sharebutton);
        next = findViewById(R.id.next_button);
        previous = findViewById(R.id.previous_button);

        previousQuote= new Stack<>();
        //1:import quotes from strings.xml

        Resources res = getResources();
        String[] allQuotes = res.getStringArray(R.array.quotes);
        quoteList = new ArrayList<>();
        addToQuoteList(allQuotes);
        //2:generate random quote
final int quoteslength =quoteList.size();
index=getrandomQuote(quoteslength-1);
textView.setText(quoteList.get(index).toString());
        //3:generate random quote when next is pressed
next.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        isPrevious = false;
        index=getrandomQuote(quoteslength-1);

        textView.setText(quoteList.get(index).toString());
        previousQuote.push(quoteList.get(index));
    }
});
        //4:recall previous quote when back button pressed
previous.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(!isPrevious && previousQuote.size()>0){
            previousQuote.pop();
        isPrevious = true;
        }
        if(previousQuote.size()>0 && isPrevious)
textView.setText(previousQuote.pop().toString());
        else
            Toast.makeText(MainActivity.this,"Get new quote",Toast.LENGTH_SHORT).show();
    }
});
        //5:share quote on social media
share.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,quoteList.get(index).toString());
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
});

//TODO:
//1. save favourate quote
       // 2.Make Hindi Version OF app
        //3.Include Top 100 Quotes
        //4.Make UI/UX more pleasing




    }

//Adding all quotes to arrayList
    public void addToQuoteList(String[] allQuotes){
        for(int i = 0;i<allQuotes.length;i++){
String quote = allQuotes[i];
Quote newquote = new Quote(quote);
quoteList.add(newquote);

        }
    }

   public int getrandomQuote(int length){
        return (int) (Math.random()*length)+1;
   }
}
