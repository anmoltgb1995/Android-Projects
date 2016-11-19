package com.android.learnit;

import android.content.Context;
import android.database.Cursor;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by sarthakalang on 20/11/15.
 */
public class CustomCursorAdapter2 extends CursorAdapter {

    Context context;
    String text,text2;
    String setname;
    TextToSpeech tts;
    public CustomCursorAdapter2(Context context, Cursor c,String setname) {
        super(context,c);
        this.context=context;
        this.setname=setname;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View retView = inflater.inflate(R.layout.dlistwords, parent, false);

        return retView;
    }

    @Override
    public void bindView(final View view, final Context context, Cursor cursor) {


        TextView textViewWordName = (TextView) view.findViewById(R.id.word2);
        ImageButton b=(ImageButton) view.findViewById(R.id.speakbut);
        textViewWordName.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));
        TextView textViewWordDesc = (TextView) view.findViewById(R.id.def2);
        textViewWordDesc.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3))));




        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView t = (TextView) view.findViewById(R.id.word2);
                text = t.getText().toString();
                TextView t2 = (TextView) view.findViewById(R.id.def2);
                text2 = t2.getText().toString();

                //Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
                tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status == TextToSpeech.SUCCESS) {

                            int result;

                            switch (setname) {
                                case "english":
                                    result = tts.setLanguage(Locale.US);
                                    break;
                                case "french":
                                    result = tts.setLanguage(Locale.FRENCH);
                                    break;
                                case "hindi":
                                    result = tts.setLanguage(new Locale("hin", "IND"));
                                    break;
                                case "Hindi":
                                    result = tts.setLanguage(new Locale("hin", "IND"));
                                    break;
                                case "HINDI":
                                    result = tts.setLanguage(new Locale("hin", "IND"));
                                    break;
                                case "spanish":
                                    result = tts.setLanguage(new Locale("spa", "ESP"));
                                    break;
                                case "Spanish":
                                    result = tts.setLanguage(new Locale("spa", "ESP"));
                                    break;
                                case "SPANISH":
                                    result = tts.setLanguage(new Locale("spa", "ESP"));
                                    break;
                                case "russian":
                                    result = tts.setLanguage(new Locale("rus", "RUS"));
                                    break;
                                case "Russian":
                                    result = tts.setLanguage(new Locale("rus", "RUS"));
                                    break;
                                case "RUSSIAN":
                                    result = tts.setLanguage(new Locale("rus", "RUS"));
                                    break;

                                case "French":
                                    result = tts.setLanguage(Locale.FRENCH);
                                    break;
                                case "FRENCH":
                                    result = tts.setLanguage(Locale.FRENCH);
                                    break;
                                case "german":
                                    result = tts.setLanguage(Locale.GERMAN);
                                    break;
                                case "German":
                                    result = tts.setLanguage(Locale.GERMAN);
                                    break;
                                case "GERMAN":
                                    result = tts.setLanguage(Locale.GERMAN);
                                    break;
                                case "italian":
                                    result = tts.setLanguage(Locale.ITALIAN);
                                    break;
                                case "Italian":
                                    result = tts.setLanguage(Locale.ITALIAN);
                                    break;
                                case "ITALIAN":
                                    result = tts.setLanguage(Locale.ITALIAN);
                                    break;
                                case "chinese":
                                    result = tts.setLanguage(Locale.CHINESE);
                                    break;
                                case "Chinese":
                                    result = tts.setLanguage(Locale.CHINESE);
                                    break;
                                case "CHINESE":
                                    result = tts.setLanguage(Locale.CHINESE);
                                    break;
                                case "japanese":
                                    result = tts.setLanguage(Locale.JAPANESE);
                                    break;
                                case "Japanese":
                                    result = tts.setLanguage(Locale.JAPANESE);
                                    break;
                                case "JAPANESE":
                                    result = tts.setLanguage(Locale.JAPANESE);
                                    break;
                                case "turkish":
                                    result = tts.setLanguage(new Locale("tr_TR"));
                                    break;
                                case "Turkish":
                                    result = tts.setLanguage(new Locale("tr_TR"));
                                    break;
                                case "TURKISH":
                                    result = tts.setLanguage(new Locale("tr_TR"));
                                    break;
                                default:
                                    result = tts.setLanguage(Locale.US);
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
                });
              //  speakOut();

            }
        });





    }
    public void speakOut() {

        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);

    }
    public void speakOut2(){
        tts.speak(text2, TextToSpeech.QUEUE_FLUSH, null);

    }
}
