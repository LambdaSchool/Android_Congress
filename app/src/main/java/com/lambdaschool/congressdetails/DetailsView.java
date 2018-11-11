package com.lambdaschool.congressdetails;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.lambdaschool.congressdataapiaccess.CongresspersonDetails;


public class DetailsView extends AppCompatActivity {

    TextView textName, textParty, textURL, textVotePercent;
    CPDetailsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_view);
        textName = findViewById(R.id.text_name);
        textParty = findViewById(R.id.text_party);
        textURL = findViewById(R.id.text_url);
//        textVotePercent = findViewById(R.id.text_vote_with_party);


        String id = getIntent().getStringExtra("CPID");

        if (id != null) {
            viewModel = ViewModelProviders.of(this).get(CPDetailsViewModel.class);
            viewModel.getDetails(id).observe(this, new Observer<CongresspersonDetails>() {
                @Override
                public void onChanged(@Nullable CongresspersonDetails cpDetails) {
                    if (cpDetails != null) {
                        textName.setText(String.format("%s %s", cpDetails.getFirstName(), cpDetails.getLastName()));
                        String partyName = "";
                        partyName = cpDetails.getCurrentParty();
                        if (partyName.equals("R")) {
                            partyName = "Republican";
                        } else if (partyName.equals("D")) {
                            partyName = "Democrat";
                        }
                        textParty.setText(partyName);
                        textURL.setText(cpDetails.getUrl());
                    }
                }
            });
        }


    }
}
