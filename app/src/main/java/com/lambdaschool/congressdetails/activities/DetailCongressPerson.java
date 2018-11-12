package com.lambdaschool.congressdetails.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lambdaschool.congressdataapiaccess.CongresspersonDetails;
import com.lambdaschool.congressdetails.R;
import com.lambdaschool.congressdetails.viewModel.DetailCongressPersonViewModel;

public class DetailCongressPerson extends AppCompatActivity {

    DetailCongressPersonViewModel viewModel;
    TextView name;
    TextView dateOfBirth;
    TextView gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_congress_person);

        name = findViewById(R.id.textViewName);
        dateOfBirth = findViewById(R.id.textViewDateOfBirth);
        gender = findViewById(R.id.textViewGender);

        String congressPersonId = getIntent().getStringExtra(ListActivity.CONGRESS_PERSON_ID);
        if (congressPersonId != null && congressPersonId != "") {
            viewModel = ViewModelProviders.of(this).get(DetailCongressPersonViewModel.class);
            viewModel.getDetails(congressPersonId).observe(this, new Observer<CongresspersonDetails>() {
                @Override
                public void onChanged(@Nullable CongresspersonDetails congresspersonDetails) {
                    if (congresspersonDetails != null) {
                        name.setText(new StringBuilder().append(congresspersonDetails.getFirstName()).append(" ").append(congresspersonDetails.getLastName()).toString());
                        dateOfBirth.setText(new StringBuilder().append("Born: ").append(congresspersonDetails.getDateOfBirth()).toString());
                        gender.setText(new StringBuilder().append("Gender: ").append(congresspersonDetails.getGender()).toString());
                    }
                }
            });
        }

    }

}
