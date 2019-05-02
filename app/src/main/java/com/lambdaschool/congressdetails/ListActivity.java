package com.lambdaschool.congressdetails;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lambdaschool.congressdataapiaccess.CongressDao;
import com.lambdaschool.congressdataapiaccess.CongresspersonOverview;

import java.util.ArrayList;


public class ListActivity extends AppCompatActivity {

    ArrayList<CongresspersonOverview> congressPeople;
    CongressPeopleListAdapter listAdapter;
    Context context;
    private CongressViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        context = this;
        congressPeople = new ArrayList<>();
        listAdapter = new CongressPeopleListAdapter(congressPeople);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        viewModel = ViewModelProviders.of(this).get(CongressViewModel.class);
        viewModel.getCongress().observe(this, new Observer<ArrayList<CongresspersonOverview>>() {
            @Override
            public void onChanged(@Nullable ArrayList<CongresspersonOverview> congresspersonOverviews) {
                if(congresspersonOverviews != null) {
                    for(CongresspersonOverview person: congresspersonOverviews){
                        if(!congressPeople.contains(person)) {
                            congressPeople.add(person);
                        }
                    }
                }
                listAdapter.notifyDataSetChanged();
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
              final ArrayList<CongresspersonOverview> networkList = CongressDao.getAllMembers();
              runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                      for(CongresspersonOverview person: networkList){
                          viewModel.updateData(person);

                      }
                  }
              });
            }
        }).start();

    }
}
