package com.lambdaschool.congressdetails;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lambdaschool.congressdataapiaccess.CongresspersonDetails;
import com.lambdaschool.congressdataapiaccess.CongresspersonOverview;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    LinearLayout parentLayout;
    CongressPersonOverviewViewmodel viewModel;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        context = this;
        parentLayout = findViewById(R.id.parent_layout);
        viewModel = ViewModelProviders.of(this).get(CongressPersonOverviewViewmodel.class);
        viewModel.getCongressList().observe(this, new Observer<ArrayList<CongresspersonOverview>>() {
            @Override
            public void onChanged(@Nullable ArrayList<CongresspersonOverview> congresspersonOverviews) {
                if (congresspersonOverviews != null) {
                    for (final CongresspersonOverview c : congresspersonOverviews) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                parentLayout.addView(getDefaultTextView(c));
                            }
                        });

                    }
                }
            }
        });
    }

    TextView getDefaultTextView(final CongresspersonOverview congress) {
        TextView view = new TextView(context);
        final String fName = congress.getFirstName();
        final String lName = congress.getLastName();
        final String state = congress.getState();
        final String party = congress.getParty();
        String displayText = String.format("%s, %s - %s (%s)", lName, fName, party, state);
        view.setText(displayText);
        view.setTextSize(28);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = congress.getId();
                int votes = congress.getTotalVotes();
                Intent intent = new Intent(context, CongresspersonDetailsActivity.class);
                intent.putExtra("PersonId", id);
                intent.putExtra("PersonState", state);
                intent.putExtra("PersonVotes", votes);
                startActivity(intent);
            }
        });
        return view;
    }
}
