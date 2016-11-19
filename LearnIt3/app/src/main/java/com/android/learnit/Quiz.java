package com.android.learnit;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class Quiz extends AppCompatActivity implements TextToSpeech.OnInitListener {
    WordsDBHelper w;
    String[] wordlist;
    LinearLayout l;
    String[] deflist;
    int maxclicks = 4; int currentnumber = 0;
    int score=0;
    int ran;
    TextView t,t1,t2;
    EditText e;
    String s,text,cans,wans;
    Button button;
    public TextToSpeech tts;
    int c1=2;
    int c2=3;
    String set;
    public int flag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        l=(LinearLayout) findViewById(R.id.quizlay);
        Intent in=getIntent();
        set=in.getStringExtra("key");
        flag=in.getIntExtra("flag",0);
        tts=new TextToSpeech(this,this);
        w=new WordsDBHelper(this);
        Cursor cr;
        if(flag==1){
            cr=w.getsetData(set,"pd");}
        else {
            cr=w.getsetData(set,"nd");
        }


        wordlist=new String[cr.getCount()];
        deflist=new String[cr.getCount()];
        if (cr.moveToFirst())
        {
            for (int i = 0; i < cr.getCount(); i++)
            {
                wordlist[i] = cr.getString(c1);
                cr.moveToNext();
            }
        }
        if (cr.moveToFirst())
        {
            for (int i = 0; i < cr.getCount(); i++)
            {
                deflist[i] = cr.getString(c2);
                cr.moveToNext();
            }
        }
        cr.close();

        t=(TextView) findViewById(R.id.quiz);
        e=(EditText) findViewById(R.id.ans);
        t1=(TextView) findViewById(R.id.your);
        t2=(TextView) findViewById(R.id.correct);
        button=(Button) findViewById(R.id.test);
        random();

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (currentnumber == maxclicks) {
                    Intent in = new Intent(Quiz.this, Score.class);
                    in.putExtra("s", score);
                    in.putExtra("key",set);
                    in.putExtra("flag",flag);
                    startActivity(in);
                    finish();
                   // overridePendingTransition(R.anim.slide_up_info, R.anim.no_change);
                    button.setEnabled(false);
                } else {
                    s = e.getText().toString();
                    if (s.equals(deflist[ran])) {
                        score = score + 1;
                        t1.setTextColor(Color.GREEN);
                        t2.setTextColor(Color.GREEN);
                        t1.setText("Your answer: " + s);
                        t2.setText("Correct answer: " + deflist[ran]);
                        e.setText("");

                        Toast.makeText(Quiz.this, "true!", Toast.LENGTH_SHORT).show();
                        random();
                        speakOut();
                    } else {
                        Toast.makeText(Quiz.this, "false!", Toast.LENGTH_SHORT).show();
                        t1.setTextColor(Color.RED);
                        t2.setTextColor(Color.GREEN);
                        t1.setText("Your answer: " + s);
                        t2.setText("Correct answer: " + deflist[ran]);
                        e.setText("");
                        random();
                        speakOut();
                    }
                    currentnumber = currentnumber + 1;
                }
            }
        });









    }


    public void random(){
        ran = new Random().nextInt(wordlist.length);
        text=wordlist[ran];
        t.setText(wordlist[ran]);
    }

    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            int result;

            switch (set){
                case "english": result = tts.setLanguage(Locale.US);
                    break;
                case "hindi": result = tts.setLanguage(new Locale("hin","IND"));
                    break;
                case "Hindi": result = tts.setLanguage(new Locale("hin","IND"));
                    break;
                case "HINDI": result = tts.setLanguage(new Locale("hin","IND"));
                    break;
                case "spanish": result = tts.setLanguage(new Locale("spa","ESP"));
                    break;
                case "Spanish": result = tts.setLanguage(new Locale("spa","ESP"));
                    break;
                case "SPANISH": result = tts.setLanguage(new Locale("spa","ESP"));
                    break;
                case "russian": result = tts.setLanguage(new Locale("rus","RUS"));
                    break;
                case "Russian": result = tts.setLanguage(new Locale("rus","RUS"));
                    break;
                case "RUSSIAN": result = tts.setLanguage(new Locale("rus","RUS"));
                    break;
                case "french": result = tts.setLanguage(Locale.FRENCH);
                    break;
                case "French": result = tts.setLanguage(Locale.FRENCH);
                    break;
                case "FRENCH": result = tts.setLanguage(Locale.FRENCH);
                    break;
                case "german": result = tts.setLanguage(Locale.GERMAN);
                    break;
                case "German": result = tts.setLanguage(Locale.GERMAN);
                    break;
                case "GERMAN": result = tts.setLanguage(Locale.GERMAN);
                    break;
                case "italian": result = tts.setLanguage(Locale.ITALIAN);
                    break;
                case "Italian": result = tts.setLanguage(Locale.ITALIAN);
                    break;
                case "ITALIAN": result = tts.setLanguage(Locale.ITALIAN);
                    break;
                case "chinese": result = tts.setLanguage(Locale.CHINESE);
                    break;
                case "Chinese": result = tts.setLanguage(Locale.CHINESE);
                    break;
                case "CHINESE": result = tts.setLanguage(Locale.CHINESE);
                    break;
                case "japanese": result = tts.setLanguage(Locale.JAPANESE);
                    break;
                case "Japanese": result = tts.setLanguage(Locale.JAPANESE);
                    break;
                case "JAPANESE": result = tts.setLanguage(Locale.JAPANESE);
                    break;
                case "turkish": result = tts.setLanguage(new Locale("tr_TR"));
                    break;
                case "Turkish": result = tts.setLanguage(new Locale("tr_TR"));
                    break;
                case "TURKISH": result = tts.setLanguage(new Locale("tr_TR"));
                    break;
                default:result = tts.setLanguage(Locale.US);
            }

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                speakOut();
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }

    private void speakOut() {


        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }


}
