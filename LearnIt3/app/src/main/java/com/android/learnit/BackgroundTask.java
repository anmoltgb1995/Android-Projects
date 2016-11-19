package com.android.learnit;

/**
 * Created by sarthakalang on 04/10/15.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTask extends AsyncTask<String,Void,String> {
    AlertDialog alertDialog;

    LoginActivity l=new LoginActivity();

   public String UserName;
    public String username2;


    Context ctx;
    BackgroundTask(Context ctx)
    {
        this.ctx =ctx;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login Information....");
    }
    @Override
    protected String doInBackground(String... params) {
        String s=new String();
       // String reg_url = "http://www.learnit.site88.net/android/a_signup.php";
        //  String login_url = "http://www.learnit.site88.net/android/a_login.php";
        String reg_url = "http://192.168.173.1/learnit/android/a_signup.php";
         String login_url = "http://192.168.173.1/learnit/android/a_login.php";

        String method = params[0];
        if (method.equals("register")) {
            String fname = params[1];
            String lname=params[2];
            String user_name = params[3];
            String email=params[4];
            String user_pass = params[5];
            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                //httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("first_name", "UTF-8") + "=" + URLEncoder.encode(fname, "UTF-8") + "&" +
                        URLEncoder.encode("last_name", "UTF-8") + "=" + URLEncoder.encode(lname, "UTF-8") + "&" +
                        URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") + "&" +
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(user_pass, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                //httpURLConnection.connect();
                httpURLConnection.disconnect();
                // return "Registration Success...";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            s="Registration Success...";
        }
        else if(method.equals("login"))
        {//Toast.makeText(ctx, "no", Toast.LENGTH_LONG).show();
            String response2="";
            String login_name = params[1];
            String login_pass = params[2];
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(login_name,"UTF-8")+"&"+
                        URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(login_pass,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String response = "";

                String line = "";
                while ((line = bufferedReader.readLine())!=null)
                {
                    response+= line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                response2=response;
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            s=response2;


        }

        else if(method.equals("import")){


            String mUserName=params[1];

           // String import_url="http://www.learnit.site88.net/android/a_import.php";
            String import_url="http://192.168.173.1/learnit/android/a_import.php";
            String response = "";
            WordsDBHelper wdb = new WordsDBHelper(ctx);

            try {
                URL url = new URL(import_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(mUserName,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));

                String line = "";
                while ((line = bufferedReader.readLine())!=null)
                {
                    response+= line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                //  return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                JSONObject Entries = new JSONObject(response);

                JSONArray entries = Entries.optJSONArray("entries");
                for (int i = 0; i < entries.length(); i++) {
                    JSONObject Entry = entries.getJSONObject(i);
                    String setname = Entry.optString("set").toString();
                    String setword = Entry.optString("word").toString();
                    String defination = Entry.optString("def").toString();
                    String check=Entry.optString("check").toString();
                    wdb.insert2(wdb, setname, setword, defination,check);
                 //   Toast.makeText(ctx,, Toast.LENGTH_SHORT).show();

                }
            }catch(JSONException j){}
          //  Toast.makeText(ctx,mUserName, Toast.LENGTH_SHORT).show();
            username2=mUserName;


            s="imported";






        }
        else if(method.equals("export")){

            String UserName=params[1];
            String JsonExport=params[2];

           // String import_url="http://www.learnit.site88.net/android/a_export.php";
            String import_url="http://192.168.173.1/learnit/android/a_export.php";
            String response = "";
            NewWordsDB wdb = new NewWordsDB(ctx);

            try {
                URL url = new URL(import_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(UserName,"UTF-8")+"&"+
                        URLEncoder.encode("jsondata","UTF-8")+"="+URLEncoder.encode(JsonExport,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));

                String line = "";
                while ((line = bufferedReader.readLine())!=null)
                {
                    response+= line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                //  return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

          /*  try {
                JSONObject Entries = new JSONObject(response);

               JSONArray entries = Entries.optJSONArray("entries");
                for (int i = 0; i < entries.length(); i++) {
                    JSONObject Entry = entries.getJSONObject(i);
                    String setname = Entry.optString("set").toString();
                    String setword = Entry.optString("word").toString();
                    String defination = Entry.optString("def").toString();
                    wdb.insert(wdb,setname,setword,defination);
                    //   Toast.makeText(ctx,setword, Toast.LENGTH_SHORT).show();

                }
            //    wait=1;
            }catch(JSONException j){}  */
          //  Toast.makeText(ctx, response, Toast.LENGTH_LONG).show();







s="exported";

        }





        return s;



    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
    @Override
    protected void onPostExecute(String result) {


        if(result!=null&&result.equals("Registration Success..."))
        {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        else if(result!=null&&result.substring(0, Math.min(result.length(), 3)).equals("abc")){

            Toast.makeText(ctx, "Login failed", Toast.LENGTH_SHORT).show();

         //   String UserName="";


            //   String in;

        }
        else if(result!=null&&result.equals("imported")){


            Intent intent = new Intent(ctx, ViewSets.class);
            //Toast.makeText(ctx, username2, Toast.LENGTH_SHORT).show();



            try {
                FileOutputStream fileout=ctx.openFileOutput("mytextfile.txt",ctx.MODE_PRIVATE);
                OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
                outputWriter.write(username2);
                outputWriter.close();



            } catch (Exception e) {
                e.printStackTrace();
            }


            //intent.putExtra("user", username2);
            ctx.startActivity(intent);


        }
        else if(result!=null&&result.equals("exported")){


            Toast.makeText(ctx, "exported", Toast.LENGTH_SHORT).show();


        }
        else{ //alertDialog.setMessage(result);
            //alertDialog.show();



            try{
                JSONObject details = new JSONObject(result);
                String FirstName = details.optString("FirstName");
                String LastName = details.optString("LastName");
                String Email = details.optString("Email");
                UserName = details.optString("UserName");
                String Password = details.optString("Password");


                Toast.makeText(ctx,"Welcome "+FirstName, Toast.LENGTH_SHORT).show();}catch(JSONException j){ Toast.makeText(ctx,result, Toast.LENGTH_LONG).show();


            }

            // Toast.makeText(ctx,"Synced", Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(ctx, ViewSets.class);
            // intent.putExtra("user",UserName);
            //ctx.startActivity(intent);


            BackgroundTask b = new BackgroundTask(ctx);
          //  Toast.makeText(ctx,UserName, Toast.LENGTH_SHORT).show();
            b.execute("import", UserName);





        }






    }


}