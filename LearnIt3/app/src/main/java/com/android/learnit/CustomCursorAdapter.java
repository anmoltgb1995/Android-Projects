package com.android.learnit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

/**
 * Created by sarthakalang on 15/09/15.
 */
public class CustomCursorAdapter extends CursorAdapter{
    firstact f;
    String text,text1;
    String setname;
    WordsDBHelper wdb;
    SQLiteDatabase db;
Context context;
    public CustomCursorAdapter(Context context, Cursor c,String setname) {
        super(context,c);
        this.context=context;
        this.setname=setname;
    }
    TextToSpeech tts;


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // when the view will be created for first time,
        // we need to tell the adapters, how each item will look
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View retView = inflater.inflate(R.layout.texxt, parent, false);

        return retView;
    }
    @Override
    public void bindView(final View view, final Context context, Cursor cursor) {
        wdb=new WordsDBHelper(context);
        db=wdb.getWritableDatabase();



        // here we are setting our data
        // that means, take the data from the cursor and put it in views

        TextView textViewWordName = (TextView) view.findViewById(R.id.words);
        ImageButton b=(ImageButton) view.findViewById(R.id.speech);
        LinearLayout li2=(LinearLayout) view.findViewById(R.id.l2);
        textViewWordName.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));
       text=textViewWordName.getText().toString();

/*
        LinearLayout myLayout = (LinearLayout) view.findViewById(R.id.myLayout);
        myLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, flashcard.class);
                context.startActivity(intent);
            }
        });
        */



        li2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {




                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:

                                TextView t1=(TextView) view.findViewById(R.id.words);
                                text1=t1.getText().toString();
                                wdb.remove(setname, text1, db);
                                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context, firstact.class);
                               // intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                intent.putExtra("key",setname);
                                context.startActivity(intent);
                                //f.fin();
                                firstact.a.finish();

                                //Yes button clicked
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Do you want to delete this word?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();

            return true;}
        });


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView t=(TextView) view.findViewById(R.id.words);
                text=t.getText().toString();
                //Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
                tts=new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status == TextToSpeech.SUCCESS) {

                           int result;

                            switch (setname){
                                case "english": result = tts.setLanguage(Locale.US);
                                    break;
                                case "french": result = tts.setLanguage(Locale.FRENCH);
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
                });
                speakOut();
            }
        });

        TextView textViewWordDesc = (TextView) view.findViewById(R.id.descs);
        textViewWordDesc.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3))));


    }


    public void speakOut() {



            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);

    }
}
