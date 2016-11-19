package com.android.learnit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity{
    EditText ET_NAME,ET_PASS;
    TextInputLayout t1,t2;
    String login_name,login_pass;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Boolean isInternetPresent;

  //  BackgroundTask b;


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    @Override
    protected void onResume() {

        View decorView = getWindow().getDecorView();
// Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        pref = getSharedPreferences("testapp", MODE_PRIVATE);
        editor = pref.edit();
        String getStatus=pref.getString("register", "nil");
        if(getStatus.equals("true")){
            Intent intent=new Intent(LoginActivity.this,ViewSets.class);
            startActivity(intent);
            finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




        t1=(TextInputLayout) findViewById(R.id.input_layout_ulname);
        t2=(TextInputLayout) findViewById(R.id.input_layout_lpass);
        ET_NAME = (EditText)findViewById(R.id.user_name);
        ET_PASS = (EditText)findViewById(R.id.user_pass);

    }
    public void userReg(View view)
    {
        startActivity(new Intent(this, Register.class));
    }



    public void userLogin(View view)
    {   ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();
        if(isInternetPresent) {


            editor.putString("register", "true");
            editor.commit();
            login_name = ET_NAME.getText().toString();
            login_pass = ET_PASS.getText().toString();
            if ((login_name.equals("")) || (login_pass.equals(""))) {
                Toast.makeText(this, "Text fields are empty, Please try again!", Toast.LENGTH_SHORT).show();
            } else {

                String method = "login";
                BackgroundTask backgroundTask = new BackgroundTask(this);
                backgroundTask.execute(method, login_name, login_pass);



         /*   while((backgroundTask.wait)!=1) {
                Toast.makeText(this," "+backgroundTask.wait,Toast.LENGTH_SHORT).show();
            }

                    Intent intent = new Intent(LoginActivity.this,ViewSets.class);

                    startActivity(intent);
                    */

         /*   Intent intent = new Intent(LoginActivity.this,ViewSets.class);
            intent.putExtra("username",login_name);
            startActivity(intent);
*/


                // finish();
            }
        }
        else{
            //Toast.makeText(this,"No internet connection ",Toast.LENGTH_SHORT).show();
            showAlertDialog(LoginActivity.this, "No Internet Connection",
                    "You don't have internet connection.", false);
        }
    }
    public void fin()
    {
        finish();
    }
    public void showAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();


        alertDialog.setTitle(title);


        alertDialog.setMessage(message);



        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }



}
