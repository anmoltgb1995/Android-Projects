package com.android.learnit;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Register extends Activity {
    EditText ET_FNAME,ET_LNAME,ET_USER_NAME,ET_EMAIL,ET_USER_PASS;
    TextInputLayout t1,t2,t3,t4,t5;
    String fname,lname,email,user_name,user_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        t1=(TextInputLayout) findViewById(R.id.input_layout_fname);
        t2=(TextInputLayout) findViewById(R.id.input_layout_lname);
        t3=(TextInputLayout) findViewById(R.id.input_layout_uname);
        t4=(TextInputLayout) findViewById(R.id.input_layout_email);
        t5=(TextInputLayout) findViewById(R.id.input_layout_pass);
        ET_FNAME = (EditText)findViewById(R.id.fname);
        ET_LNAME = (EditText)findViewById(R.id.lname);
        ET_EMAIL = (EditText)findViewById(R.id.email);
        ET_USER_NAME= (EditText)findViewById(R.id.new_user_name);
        ET_USER_PASS = (EditText)findViewById(R.id.new_user_pass);


    }
    public void userReg(View view)
    {

        fname=ET_FNAME.getText().toString();
        lname=ET_LNAME.getText().toString();
        user_name = ET_USER_NAME.getText().toString();
        email=ET_EMAIL.getText().toString();
        user_pass =ET_USER_PASS.getText().toString();
        if((fname.equals(""))||(lname.equals(""))||(user_name.equals(""))||(email.equals(""))||(user_pass.equals(""))){
            Toast.makeText(this, "Text fields are empty, Please try again!", Toast.LENGTH_SHORT).show();
        }
        else {
            String method = "register";
            BackgroundTask backgroundTask = new BackgroundTask(this);
            backgroundTask.execute(method, fname, lname, user_name, email, user_pass);
            finish();
        }
    }

}
