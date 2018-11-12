package com.lambdaschool.congressdetails.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lambdaschool.congressdataapiaccess.CongressDao;
import com.lambdaschool.congressdataapiaccess.CongresspersonOverview;
import com.lambdaschool.congressdetails.R;
import com.lambdaschool.congressdetails.viewModel.CongressOverviewViewModel;

import java.util.ArrayList;


public class ListActivity extends AppCompatActivity {

    public static final String CONGRESS_PERSON_ID = "person_id";
    Context context;
    CongressOverviewViewModel viewModel;
    LinearLayout parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        context = this;
        parentLayout = findViewById(R.id.parentlayout);

        viewModel = ViewModelProviders.of(this).get(CongressOverviewViewModel.class);
        viewModel.getOverView().observe(this, new Observer<ArrayList<CongresspersonOverview>>() {
            @Override
            public void onChanged(@Nullable ArrayList<CongresspersonOverview> congresspersonOverviews) {
                if (congresspersonOverviews != null) {
                    for (CongresspersonOverview person : congresspersonOverviews) {
                        parentLayout.addView(getDefaultTextView(person));

                    }
                }
            }
        });

        CongressDao.getAllMembers();
    }

    TextView getDefaultTextView(final CongresspersonOverview person) {
        TextView view = new TextView(context);
        String personName = new StringBuilder().append(person.getFirstName()).append(" ").append(person.getLastName()).append("\n").append(person.getParty()).append("-").append(person.getState()).toString();
        final String idTag = person.getId();
        view.setTag(idTag);
        view.setText(personName);
        view.setTextSize(28);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCongressPerson = new Intent(context, DetailCongressPerson.class);
                intentCongressPerson.putExtra(CONGRESS_PERSON_ID, idTag);
                startActivity(intentCongressPerson);
            }
        });

        return view;
    }
}
