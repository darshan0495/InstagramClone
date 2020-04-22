package com.example.instagramclone;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {
    private EditText edtProfileName,edtProfileHobbies,edtProfileBio,edtProfileProfession,edtProfileFavSport;
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
                final ProgressDialog progressDialog = new ProgressDialog(getContext());
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
                            FancyToast.makeText(getContext(), "Updated Info", Toast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                        } else {
                            progressDialog.dismiss();
                            FancyToast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT, FancyToast.ERROR, false).show();

                        }
                    }
                });

            }
        });
        return view;
    }
}
