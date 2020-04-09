package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

import static java.nio.file.Paths.get;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    //UI Component Variable Declaration
    private EditText edtUserName, edtEnterPassword,edtEnterEmail;
    private Button btnSignUp,btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("Sign Up");

        //UI component variable initialization
        edtEnterEmail=findViewById(R.id.edtEnterEmail);
        edtUserName=findViewById(R.id.edtUserName);
        edtEnterPassword=findViewById(R.id.edtEnterPassword);
        btnSignUp=findViewById(R.id.btnSignUp);
        btnLogIn=findViewById(R.id.btnLogIn);

        //Onclick declarations
        btnSignUp.setOnClickListener(this);
        btnLogIn.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogIn:
                Intent intent=new Intent(SignUp.this,LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.btnSignUp:
                final ParseUser appUser=new ParseUser();
                //Sending data to server
                appUser.setUsername(edtUserName.getText().toString());
                appUser.setEmail(edtEnterEmail.getText().toString());
                appUser.setPassword(edtEnterPassword.getText().toString());
                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e==null){
                            FancyToast.makeText(SignUp.this,"Succesfully signed up",Toast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                        }else {
                            FancyToast.makeText(SignUp.this,e.getMessage(),Toast.LENGTH_SHORT,FancyToast.ERROR,false).show();

                        }
                    }
                });
        }

    }
}
