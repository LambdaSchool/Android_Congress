package com.lambdaschool.congressdetails;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.lambdaschool.congressdataapiaccess.CongressDao;
import com.lambdaschool.congressdataapiaccess.CongresspersonDetails;

public class DetailActivity extends AppCompatActivity {

    CongresspersonDetailViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        final String personId = intent.getStringExtra("tag");
        viewModel = ViewModelProviders.of(this).get(CongresspersonDetailViewModel.class);
        viewModel.getSingleCongressPerson(personId).observe(this, new Observer<CongresspersonDetails>() {
            @Override
            public void onChanged(@Nullable CongresspersonDetails congresspersonDetails) {
                String temp = congresspersonDetails.getFacebookAccount() + " " + congresspersonDetails.getLastName();
                ((TextView)findViewById(R.id.textView)).setText(temp);
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                viewModel.getSingleCongressPerson(personId);

            }
        }).start();
    }
}
