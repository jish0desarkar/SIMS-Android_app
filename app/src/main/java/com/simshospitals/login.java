package com.simshospitals;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.HashMap;

public class login extends AppCompatActivity implements View.OnClickListener{

    Button loginBtn;
    private static final String LOGIN_URL = "http://103.249.207.47/sims/sims.php";

    private EditText editTextusername;
    private EditText editTextPassword;

    private Button buttonLogin;
    public static final String preference="ref";
    public static final String saveit="savekey";
    SharedPreferences sf4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextusername= (EditText) findViewById(R.id.et_username);
        editTextPassword = (EditText) findViewById(R.id.et_password);
        buttonLogin = (Button) findViewById(R.id.btn_login);

        buttonLogin.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v == buttonLogin)
            login();
    }


    private void login(){
        String username = editTextusername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        if(username.equals("")) {
            Toast.makeText(login.this, "Please Enter the Username", Toast.LENGTH_SHORT).show();
        }
        else if(password.equals(""))
            Toast.makeText(login.this, "Please Enter the password", Toast.LENGTH_SHORT).show();
        else
            userLogin(username,password);
    }

    private void userLogin(final String username, final String password){
        class UserLoginClass extends AsyncTask<String,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(login.this,"We welcome you back! ","Hold on.....",true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                System.err.println(s);
                if(s.equals("success")){
                    sf4 = getSharedPreferences(preference, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor4 = sf4.edit();
                    editor4.putString(saveit, "success");
                    editor4.commit();
                    finish();
                    Intent intent = new Intent(login.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(login.this,"Wrong Password or Username", Toast.LENGTH_LONG).show();
                    System.err.println("HELLO");
                }
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String,String> data = new HashMap<>();
                data.put("username",params[0]);
                data.put("password",params[1]);

                PostingClass ruc = new PostingClass();

                String result = ruc.sendPostRequest(LOGIN_URL,data);

                return result;
            }
        }
        UserLoginClass ulc = new UserLoginClass();
        ulc.execute(username,password);
    }

}
