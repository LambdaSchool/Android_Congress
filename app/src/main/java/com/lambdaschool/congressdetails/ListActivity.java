package com.lambdaschool.congressdetails;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lambdaschool.congressdataapiaccess.CongresspersonOverview;

import android.arch.lifecycle.Observer;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class ListActivity extends AppCompatActivity {

    public static final String TAG = "MyDebug";
    public static final String KEY_EXTRA_CONGRESSPERSON_ID = "congressperson_id";

    private CongresspeopleViewModel congresspeopleViewModel;
    private CongresspeopleAdapter congresspeopleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        RecyclerView congresspeopleRecyclerView = findViewById(R.id.activity_list_recycler_congresspeople);
        congresspeopleRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        congresspeopleAdapter = new CongresspeopleAdapter();
        congresspeopleAdapter.setOnCongresspersonClickListener(new CongresspeopleAdapter.OnCongresspersonClickListener() {
            @Override
            public void onClick(CongresspersonOverview congresspersonOverview) {
                Intent intent = new Intent(ListActivity.this, CongresspersonDetailsActivity.class);
                intent.putExtra(KEY_EXTRA_CONGRESSPERSON_ID, congresspersonOverview.getId());
                startActivity(intent);
            }
        });
        congresspeopleRecyclerView.setAdapter(congresspeopleAdapter);

        // this will create the ViewModel
        congresspeopleViewModel = ViewModelProviders.of(this).get(CongresspeopleViewModel.class);

        // bind
        congresspeopleViewModel.getCongresspeopleLD().observe(this, new CongresspersonObserver());

    }

    private class CongresspersonObserver implements Observer<ArrayList<CongresspersonOverview>> {

        @Override
        public void onChanged(ArrayList<CongresspersonOverview> arg) {
            String threadName = Thread.currentThread().getName(); // @NOTE: UI thread
            Log.d(TAG, "Observer onChanged Thread: " + threadName);

            congresspeopleAdapter.setCongresspersonOverviews(arg);

        }
    }

}

