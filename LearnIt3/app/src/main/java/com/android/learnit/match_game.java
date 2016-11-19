package com.android.learnit;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class match_game extends AppCompatActivity {

    String[] wordlist;
    String[] deflist;
    String setname;
    WordsDBHelper wdb;
    int c1=2;
    int c2=3;
    public int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_game);
        Intent intent=getIntent();
       setname= intent.getStringExtra("key");
        flag=intent.getIntExtra("flag",0);

        fetchdata(setname);

        // Create adapter to set value for grid view
        final GridView gridView = (GridView) findViewById(R.id.gridview);

        ArrayList<Integer> array = new ArrayList<>();
        ArrayList<String> array2 = new ArrayList<>(8);

        for(int i=0;i<wordlist.length;i++)
            array.add(new Integer(i));

        Collections.shuffle(array);

        int limit = 6 ;
        if(array.size() < limit)
            limit = array.size();

        final int totalQues = limit;

        for(int i=0;i<limit;i++) {
            array2.add(wordlist[array.get(i)]);
            array2.add(deflist[array.get(i)]);
        }

        Collections.shuffle(array2);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.gamecard_view,array2);

        gridView.setAdapter(adapter);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            private int numSelected = 0, prevSelected, num_correct = 0;

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (view.getElevation() != 0) {
                    view.setBackgroundColor(getResources().getColor(R.color.cardBackground));
                    view.setElevation(0);
                    //view.setScaleX(1f);
                    //view.setScaleY(1f);
                    numSelected--;
                    return;
                }

                numSelected++;
                if (numSelected > 2) {
                    numSelected--;
                    return;
                }

                view.setBackgroundColor(getResources().getColor(R.color.cardSelectBackground));
                view.setElevation(50f);

                if (numSelected == 1)
                    prevSelected = adapterView.getPositionForView(view);
                else if (numSelected == 2)
                    //Toast.makeText(getApplicationContext(),"2 selected", Toast.LENGTH_SHORT).show();
                    compare(prevSelected, adapterView.getPositionForView(view));
            }

            private void compare(int SelectedView1, int SelectedView2) {
                final TextView View1 = (TextView) gridView.getChildAt(SelectedView1);
                final TextView View2 = (TextView) gridView.getChildAt(SelectedView2);
                for (int i = 0; i < wordlist.length; i++) {
                    if (View1.getText().toString() == wordlist[i])
                        if (View2.getText().toString() == deflist[i]) {

                            numSelected = 0;
                            Correct(View1, View2);

                            return;
                        }
                }


                for (int i = 0; i < wordlist.length; i++) {
                    if (View2.getText().toString() == wordlist[i])
                        if (View1.getText().toString() == deflist[i]) {

                            numSelected = 0;

                            Correct(View1, View2);

                            return;
                        }

                }

                numSelected = 0;

                //Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_SHORT).show();
                View1.setBackgroundColor(getResources().getColor(R.color.answerIncorrect));
                View2.setBackgroundColor(getResources().getColor(R.color.answerIncorrect));


                ObjectAnimator nothing
                        = ObjectAnimator.ofFloat(View2, "scaleX", 1f, 1f);

                nothing.setDuration(0);
                nothing.setStartDelay(500);
                nothing.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {


                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                        View1.setElevation(0);
                        View2.setElevation(0);
                        View1.setBackgroundColor(getResources().getColor(R.color.cardBackground));
                        View2.setBackgroundColor(getResources().getColor(R.color.cardBackground));


                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });


                ObjectAnimator move1 = ObjectAnimator.ofFloat(View1, "translationX", 0, 10);
                ObjectAnimator move2 = ObjectAnimator.ofFloat(View1, "translationX", 10, -10);
                ObjectAnimator move3 = ObjectAnimator.ofFloat(View1, "translationX", -10, 0);

                final AnimatorSet animSet = new AnimatorSet();
                animSet.playSequentially(move1, move2, move3);
                animSet.setStartDelay(200);
                animSet.setDuration(75);


                ObjectAnimator move4 = ObjectAnimator.ofFloat(View2, "translationX", 0, 10);
                ObjectAnimator move5 = ObjectAnimator.ofFloat(View2, "translationX", 10, -10);
                ObjectAnimator move6 = ObjectAnimator.ofFloat(View2, "translationX", -10, 0);

                final AnimatorSet animSet2 = new AnimatorSet();
                animSet2.playSequentially(move4, move5, move6);
                animSet2.setStartDelay(200);
                animSet2.setDuration(75);


                animSet.start();
                animSet2.start();
                nothing.start();

            }


            private void Correct(final TextView View1, final TextView View2) {

                int duration = 200, delay = 1000;
                num_correct++;

                Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                View1.setBackgroundColor(getResources().getColor(R.color.answerCorrect));
                View2.setBackgroundColor(getResources().getColor(R.color.answerCorrect));
                ObjectAnimator disappearX1
                        = ObjectAnimator.ofFloat(View1, "scaleX", 1f, 0f);
                //disappearX1.setStartDelay(delay);
                //disappearX1.setDuration(duration);

                ObjectAnimator disappearX2
                        = ObjectAnimator.ofFloat(View2, "scaleX", 1f, 0f);
                //disappearX2.setStartDelay(delay);
                //disappearX2.setDuration(duration);

                ObjectAnimator disappearY1
                        = ObjectAnimator.ofFloat(View1, "scaleY", 1f, 0f);

                ObjectAnimator disappearY2
                        = ObjectAnimator.ofFloat(View2, "scaleY", 1f, 0f);

                disappearY2.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        View1.setVisibility(View.GONE);
                        View2.setVisibility(View.GONE);
                        if (num_correct == totalQues) {
                            setContentView(R.layout.game_score);
                            Button play = (Button) findViewById(R.id.play_btn);

                            play.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(match_game.this, match_game.class);
                                    intent.putExtra("key",setname);
                                    intent.putExtra("flag",flag);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

                AnimatorSet animSet = new AnimatorSet();
                animSet.setDuration(duration);
                animSet.setStartDelay(delay);
                animSet.playTogether(disappearX1, disappearX2, disappearY1, disappearY2);
                animSet.start();

                //disappearX1.start();
                //disappearX2.start();

            }

        });

        //final Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/HelveticaNeue-Regular.ttf");

        final TextView mTextField = (TextView) findViewById(R.id.timer);

        new CountDownTimer(20000, 10) {

            public void onTick(long millisUntilFinished) {
                mTextField.setText(String.format("%2.3f", (float)millisUntilFinished / 1000));
            }

            public void onFinish() {

                mTextField.setText("done!");
                setContentView(R.layout.game_score);
                //btn.setTypeface(custom_font);
            }

        }.start();


    }






    public void fetchdata(String set){
        wdb=new WordsDBHelper(this);
        Cursor cr;
        if(flag==1){
            cr=wdb.getsetData(set,"pd");}
        else {
            cr=wdb.getsetData(set,"nd");
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
    }


}
