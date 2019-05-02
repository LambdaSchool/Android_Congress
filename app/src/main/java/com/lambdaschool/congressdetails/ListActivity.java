package com.lambdaschool.congressdetails;

import android.content.Context;
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
        new Thread(new Runnable() {
            @Override
            public void run() {
              final ArrayList<CongresspersonOverview> networkList = CongressDao.getAllMembers();
              runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                      for(CongresspersonOverview person: networkList){
                          congressPeople.add(person);
                          listAdapter.notifyItemChanged(congressPeople.size() - 1);
                      }
                  }
              });
            }
        }).start();

    }
}
