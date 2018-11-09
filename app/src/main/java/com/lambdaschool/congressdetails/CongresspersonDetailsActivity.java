package com.lambdaschool.congressdetails;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.lambdaschool.congressdataapiaccess.CongressDao;
import com.lambdaschool.congressdataapiaccess.CongresspersonDetails;

public class CongresspersonDetailsActivity extends AppCompatActivity {

    TextView name, party, state, totalVotes;
    CongresspersonDetailsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congressperson_details);
        name = findViewById(R.id.name);
        party = findViewById(R.id.party);
        state = findViewById(R.id.state);
        totalVotes = findViewById(R.id.votes);

        String id = getIntent().getStringExtra("PersonId");
        final String stateString = getIntent().getStringExtra("PersonState");
        final int totalVotesNumber = getIntent().getIntExtra("PersonVotes", -1);

        if(id != null && stateString != null && totalVotesNumber != -1){
            viewModel = ViewModelProviders.of(this).get(CongresspersonDetailsViewModel.class);
            LiveData<CongresspersonDetails> liveDetails = viewModel.getDetails(id);
            liveDetails.observe(this, new Observer<CongresspersonDetails>() {
                @Override
                public void onChanged(@Nullable CongresspersonDetails congresspersonDetails) {
                    if(congresspersonDetails != null){
                        name.setText(congresspersonDetails.getFirstName() + " " + congresspersonDetails.getLastName());
                        party.setText(congresspersonDetails.getCurrentParty());
                        state.setText(stateString);
                        totalVotes.setText(totalVotesNumber);
                    }
                }
            });
        }
    }
}
