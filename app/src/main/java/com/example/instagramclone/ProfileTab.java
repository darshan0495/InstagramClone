package com.example.instagramclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {
    private TextView edtProfileName,edtProfileHobbies,edtProfileBio,edtProfileProfession,edtProfileFavSport;
    private Button btnUpdateInfo;
    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile_tab, container, false);
        edtProfileName=view.findViewById(R.id.edtProfileName);
        edtProfileBio=view.findViewById(R.id.edtProfileBio);
        edtProfileHobbies=view.findViewById(R.id.edtProfileHobbies);
        edtProfileProfession=view.findViewById(R.id.edtProfileProfession);
        edtProfileFavSport=view.findViewById(R.id.edtProfileFavSport);
        btnUpdateInfo=view.findViewById(R.id.btnUpdateInfo);

        addProfileData();

        btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),ProfileEdit.class);
                startActivity(intent);
            }
        });

        return view;

    }
    public void addProfileData(){
        final ParseUser parseUser=ParseUser.getCurrentUser();
        if (parseUser.get("profileName")==null){
            edtProfileName.setText("");
        }else {
            edtProfileName.setText(parseUser.get("profileName")+"");

        }

        if (parseUser.get("profileBio")==null){
            edtProfileBio.setText("-");
        }else {
            edtProfileBio.setText(parseUser.get("profileBio")+"");

        }

        if (parseUser.get("profileHobbies")==null){
            edtProfileHobbies.setText("-");
        }else {
            edtProfileHobbies.setText(parseUser.get("profileHobbies")+"");

        }

        if (parseUser.get("profileProfession")==null){
            edtProfileProfession.setText("-");
        }else {
            edtProfileProfession.setText(parseUser.get("profileProfession")+"");

        }

        if (parseUser.get("profileFavSport")==null){
            edtProfileFavSport.setText("-");
        }else {
            edtProfileFavSport.setText(parseUser.get("profileFavSport")+"");

        }
    }
}
