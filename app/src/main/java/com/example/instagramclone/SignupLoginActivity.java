package com.example.instagramclone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignupLoginActivity extends AppCompatActivity {
    EditText edtUserNameSignUp,edtPasswordSignUp,edtUsernameLogin,edtPasswordLogin;
    Button btnLogin,btnSignUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);
        edtPasswordLogin=findViewById(R.id.edtPasswordLogin);
        edtPasswordSignUp=findViewById(R.id.edtPasswordSignUp);
        edtUsernameLogin=findViewById(R.id.edtUserNameLogin);
        edtUserNameSignUp=findViewById(R.id.edtUserNameSignUp);
        btnLogin=findViewById(R.id.btnLogin);
        btnSignUp=findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser appUser= new ParseUser();
                appUser.setUsername(edtUserNameSignUp.getText().toString());
                appUser.setPassword(edtPasswordSignUp.getText().toString());
                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e==null){
                            FancyToast.makeText(SignupLoginActivity.this,"Sucess!!", FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();

                        }else {
                            FancyToast.makeText(SignupLoginActivity.this,e.getMessage(), FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();

                        }
                    }
                });
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(edtUsernameLogin.getText().toString(), edtPasswordLogin.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user!=null&&e==null){
                            FancyToast.makeText(SignupLoginActivity.this,"Sucessfuly logged in!", FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();

                        }else {
                            FancyToast.makeText(SignupLoginActivity.this,e.getMessage(), FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();

                        }
                    }
                });

            }
        });
    }
}
