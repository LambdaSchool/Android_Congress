package com.lambdaschool.congressdetails;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lambdaschool.congressdataapiaccess.CongressDao;
import com.lambdaschool.congressdataapiaccess.CongresspersonOverview;

import java.util.ArrayList;


public class ListActivity extends AppCompatActivity implements View.OnClickListener {

    CongresspersonOverviewViewModel viewModel;
    Context                         context;
    LinearLayout linearLayoutChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        context = this;
        linearLayoutChild = findViewById(R.id.linear_layout_child);


        viewModel = ViewModelProviders.of(this).get(CongresspersonOverviewViewModel.class);
        viewModel.getAllCongresspeople().observe(this, new Observer<ArrayList<CongresspersonOverview>>() {
            @Override
            public void onChanged(@Nullable ArrayList<CongresspersonOverview> congresspersonOverviews) {


                if (congresspersonOverviews != null) {
                    for(CongresspersonOverview congresspersonOverview: congresspersonOverviews) {
                        TextView textView = new TextView(context);
                        String temp = congresspersonOverview.getFirstName() + " " + congresspersonOverview.getLastName() + " " + congresspersonOverview.getTitle();
                        textView.setText(temp);
                        textView.setTag(congresspersonOverview.getId());
                        textView.setOnClickListener((View.OnClickListener) context );
                        linearLayoutChild.addView(textView);

                    }
                }
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                viewModel.getAllCongresspeople();
            }
        }).start();



    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("tag", (String)v.getTag());
        startActivity(intent);
    }
}
