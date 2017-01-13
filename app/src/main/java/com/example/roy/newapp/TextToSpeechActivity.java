package com.example.roy.newapp;

import java.util.Locale;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.Engine;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;

public class TextToSpeechActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener,View.OnClickListener {
    private Button enterButton;
    private EditText input;
    private TextView textv;
    private ListView listView;
    private List<String> arraylst;
    private ArrayAdapter<String> adaptr;
    private static int TTS_DATA_CHECK=1;
    private TextToSpeech tts = null;  //thext to speech object for itemClickEvents
    private boolean ttsInit = false;
    private boolean firstClick;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to);

        //this code is to make the tittle underlined
        SpannableString title = new SpannableString(("Text to speech app!"));
        TextView tit = (TextView) findViewById(R.id.mainTitle);
        title.setSpan(new UnderlineSpan(), 0, title.length(), 0);
        tit.setText(title);

        firstClick=true;
        listView = (ListView) findViewById(R.id.listView);
        enterButton= (Button) findViewById(R.id.addButton);
        input=(EditText)findViewById(R.id.myEdText);
        input.setOnClickListener(this);



        arraylst=((myApplication) getApplicationContext()).globalList;
        adaptr=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arraylst);
        listView.setAdapter(adaptr);

        initTTS();
        listView.setOnItemClickListener(this);
        enterButton.setOnClickListener(this);
        input.setOnClickListener(this);



    }

    private void initTTS() {
        Intent checkIntent=new Intent(Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent,TTS_DATA_CHECK);
    }



    protected void onActivityResult(int requesedtCode, int resultCode, Intent data) {
        if (requesedtCode == TTS_DATA_CHECK) {
            if (resultCode != Engine.CHECK_VOICE_DATA_PASS) {
                Intent installVoice = new Intent(Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installVoice);
            }
            configTextToSpeech();
        }
    }

    private void configTextToSpeech() {
        tts=new TextToSpeech(this, new OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i==TextToSpeech.SUCCESS){
                    ttsInit=true;
                    if(tts.isLanguageAvailable(Locale.ENGLISH)>=0)
                        tts.setLanguage(Locale.ENGLISH);
                    tts.setPitch(1);
                    tts.setSpeechRate(1);
                }
            }
        });//end of anonymous class
    }

    private void speakOnClick(String wordToSpeak){
        if(tts!=null&& ttsInit)
            tts.speak(wordToSpeak,TextToSpeech.QUEUE_ADD,null);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
        speakOnClick(arraylst.get(pos));
    }

    @Override
    public void onClick(View view) {
        if(view == enterButton){
            firstClick=true;
            String s=input.getText().toString();
            if(s.equals("    Enter text Here    "))
                return;

            adaptr.add(s);
            adaptr.notifyDataSetChanged();

            return;
        }
        if(view==input){
            if(firstClick)
                input.setText("");
            firstClick=false;
        }

    }
    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}

