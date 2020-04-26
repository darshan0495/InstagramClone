package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class ProfileEdit extends AppCompatActivity {
    private EditText edtProfileName,edtProfileBio,edtProfileHobbies,edtProfileProfession,edtProfileFavSport;
    private Button btnUpdateInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        edtProfileName=findViewById(R.id.edtProfileName);
        edtProfileBio=findViewById(R.id.edtProfileBio);
        edtProfileHobbies=findViewById(R.id.edtProfileHobbies);
        edtProfileProfession=findViewById(R.id.edtProfileProfession);
        edtProfileFavSport=findViewById(R.id.edtProfileFavSport);
        btnUpdateInfo=findViewById(R.id.btnUpdateInfo);

        final ParseUser parseUser=ParseUser.getCurrentUser();
        if (parseUser.get("profileName")==null){
            edtProfileName.setText("");
        }else {
            edtProfileName.setText(parseUser.get("profileName")+"");

        }
        edtProfileBio.setText(parseUser.get("profileBio")+"");
        edtProfileHobbies.setText(parseUser.get("profileHobbies")+"");
        edtProfileProfession.setText(parseUser.get("profileProfession")+"");
        edtProfileFavSport.setText(parseUser.get("profileFavSport")+"");

        btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(ProfileEdit.this);
                progressDialog.setMessage("Updating Info");
                progressDialog.show();
                parseUser.put("profileName",edtProfileName.getText().toString());
                parseUser.put("profileBio",edtProfileBio.getText().toString());
                parseUser.put("profileHobbies",edtProfileHobbies.getText().toString());
                parseUser.put("profileProfession",edtProfileProfession.getText().toString());
                parseUser.put("profileFavSport",edtProfileFavSport.getText().toString());
                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            progressDialog.dismiss();
                            FancyToast.makeText(ProfileEdit.this, "Updated Info", Toast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                            finish();
                        } else {
                            progressDialog.dismiss();
                            FancyToast.makeText(ProfileEdit.this, e.getMessage(), Toast.LENGTH_SHORT, FancyToast.ERROR, false).show();

                        }
                    }
                });

            }
        });
    }
    }

