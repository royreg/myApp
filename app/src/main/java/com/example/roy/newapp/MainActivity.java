package com.example.roy.newapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button connector;
    private Button QuoteMachinbtn;
    private TextView headline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connector= (Button) findViewById(R.id.connector);
        QuoteMachinbtn=(Button)findViewById(R.id.quoteMachine);


        SpannableString title = new SpannableString(("Welcome to my App"));
        TextView headline = (TextView) findViewById(R.id.mainTitle);
        title.setSpan(new UnderlineSpan(), 0, title.length(), 0);
        headline.setText(title);
        connector.setOnClickListener(this);
        QuoteMachinbtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view==connector){
            startActivity(new Intent(this,TextToSpeechActivity.class));
            return;
        }
        if(view== QuoteMachinbtn){
            startActivity(new Intent(this,QuoteMachineActivity.class));
            return;
        }
    }
}