package com.example.roy.newapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuoteMachineActivity extends AppCompatActivity implements View.OnClickListener {
    Button getQ;
    QuoteMachine qm;
    TextView quoteTxt;
    TextView autTxt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_machine);

        getQ= (Button) findViewById(R.id.Qbtn);
        quoteTxt=(TextView)findViewById(R.id.quote);
        autTxt=(TextView) findViewById(R.id.Author);
        qm=new QuoteMachine();

        getQ.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==getQ){
            Quote q=null;
            try {
                q= qm.getQoute();
            } catch (Exception e) {
                e.printStackTrace();
            }
            quoteTxt.setText(q.quote);
            autTxt.setText(q.author);
            return;

        }
    }
}
