package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Trace;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    //UI component variable declaration
    private EditText edtLoginEmail,edtLoginPassword;
    private Button btnLoginActivity,btnSignUpLoginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Log In");

        //UI component variable Initialization
        edtLoginEmail=findViewById(R.id.edtLoginEmail);
        edtLoginPassword=findViewById(R.id.edtLoginPassword);

        edtLoginPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode==KeyEvent.KEYCODE_ENTER&&event.getAction()==KeyEvent.ACTION_DOWN){
                    onClick(btnLoginActivity);
                }
                return false;
            }
        });
        btnLoginActivity=findViewById(R.id.btnLoginActivity);
        btnSignUpLoginActivity=findViewById(R.id.btnSignUpLoginActivity);

        //OnClick Listener declaration
        btnSignUpLoginActivity.setOnClickListener(this);
        btnLoginActivity.setOnClickListener(this);

        if(ParseUser.getCurrentUser()!=null)
            transitionToSocialMediaActivity();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLoginActivity:
                if (edtLoginEmail.getText().toString().equals("")||edtLoginPassword.getText().toString().equals("")){
                    FancyToast.makeText(LoginActivity.this, "Email and Password is required!", Toast.LENGTH_SHORT, FancyToast.INFO, false).show();


                }else
                    ParseUser.logInInBackground(edtLoginEmail.getText().toString(), edtLoginPassword.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user!=null&&e==null){
                            FancyToast.makeText(LoginActivity.this,"Welcome Back "+user.getUsername(), Toast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                            transitionToSocialMediaActivity();
                        }else {
                            FancyToast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG, FancyToast.ERROR, false).show();
                        }
                        }
                });
                break;
            case R.id.btnSignUpLoginActivity:
                Intent intent=new Intent(LoginActivity.this,SignUp.class);
                startActivity(intent);
                break;
        }

    }
    public void rootLayoutIsTapped(View view){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void transitionToSocialMediaActivity(){
        Intent intent=new Intent(LoginActivity.this,SocialMediaActivity.class);
        startActivity(intent);
        finish();
    }
}
