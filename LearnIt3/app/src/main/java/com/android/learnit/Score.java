package com.android.learnit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class Score extends Activity implements TextToSpeech.OnInitListener {
    int score;
    TextView t;
    TextToSpeech tts;
    String text;
    public int flag;
    String set;
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        tts=new TextToSpeech(this,this);
        t=(TextView) findViewById(R.id.finalsc);
        Intent in=getIntent();
        score=in.getIntExtra("s",0);
        set=in.getStringExtra("key");
        flag=in.getIntExtra("flag",0);


        String val=String.valueOf(score);
if(score<=2){
    text="Your score is "+val+", better luck next time";
}
        else
{
    text="Well done, your score is "+val;
}



        t.setText(val);
        speakOut();


    }
    public void tryagain(View view){
        Intent intent=new Intent(Score.this,Quiz.class);
        intent.putExtra("key",set);
        intent.putExtra("flag",flag);
        startActivity(intent);
        finish();
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

            int result = tts.setLanguage(Locale.US);

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
