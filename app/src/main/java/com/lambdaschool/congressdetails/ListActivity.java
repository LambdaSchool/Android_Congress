package com.lambdaschool.congressdetails;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lambdaschool.congressdataapiaccess.CongresspersonOverview;


import android.arch.lifecycle.ViewModelProviders;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    LinearLayout parentLayout;
    CPOverviewViewModel viewModel;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        parentLayout = findViewById(R.id.parent_layout);
        context = this;

        viewModel = ViewModelProviders.of(this).get(CPOverviewViewModel.class);

        viewModel.getOverViewList().observe(this, new Observer<ArrayList<CongresspersonOverview>>() {
            @Override
            public void onChanged(@Nullable ArrayList<CongresspersonOverview> cpOverviews) {
                if (cpOverviews != null) {
                    for (CongresspersonOverview person : cpOverviews) {
                        parentLayout.addView(getDefaultTextView(person));
                    }
                }
            }
        });

    }

    TextView getDefaultTextView(final CongresspersonOverview person) {
        TextView view = new TextView(context);
        String displayText = String.format("%s %s (%s)", person.getFirstName(), person.getLastName(), person.getState());
        view.setText(displayText);
        view.setTextSize(28);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = person.getId();
                Log.i("CPID", id);

                Intent intent = new Intent(context, DetailsView.class);
                intent.putExtra("CPID",id);
                startActivity(intent);
            }
        });
        return view;
    }




}
