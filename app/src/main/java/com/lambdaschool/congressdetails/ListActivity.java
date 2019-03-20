package com.lambdaschool.congressdetails;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lambdaschool.congressdataapiaccess.CongresspersonOverview;

import android.arch.lifecycle.Observer;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class ListActivity extends AppCompatActivity {

    public static final String TAG = "MyDebug";

    private CongresspeopleViewModel m_congresspeopleViewModel;
    private LinearLayout m_congresspeopleLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {
//            Log.d("MyDebug", "Write permission granted");
//        }
//        else {
//            Log.d("MyDebug", "Write permission denied");
//
//            ActivityCompat.requestPermissions(this,
//                    new String[] {Manifest.permission.INTERNET}, 1);
//        }

        //ArrayList<CongresspersonOverview> CongresspersonOverviews = CongressDao.getAllMembers();

        m_congresspeopleLinearLayout = findViewById(R.id.linear_layout_congresspeople);

        // this will create the ViewModel
        m_congresspeopleViewModel = ViewModelProviders.of(this).get(CongresspeopleViewModel.class);

        // @NOTE to future self, this loading should not be done here
        // it should be done in another thread
        m_congresspeopleViewModel.loadCongresspeople(); // this will download the data
        // bind
        m_congresspeopleViewModel.getCongresspeopleLD().observe(this, new CongresspersonObserver());

    }

    private class CongresspersonObserver implements Observer<ArrayList<CongresspersonOverview>> {

        @Override
        public void onChanged(@Nullable ArrayList<CongresspersonOverview> arg) {
            String threadName = Thread.currentThread().getName(); // @NOTE: UI thread
            Log.d(TAG, "Observer onChanged Thread: " + threadName);

            if (arg == null) {
                return;
            }

            for (int i = 0; i < arg.size(); ++i) {
                TextView newTextView = new TextView(ListActivity.this);
                final CongresspersonOverview congressperson = arg.get(i);
                String text = congressperson.getFirstName() + " " + congressperson.getLastName() + ", " +
                        congressperson.getParty() + " " + congressperson.getState();
                newTextView.setText(text);
                newTextView.setTextSize(30.f);
                newTextView.setTag(congressperson.getId());
                newTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // open details
                        Intent intent = new Intent(ListActivity.this, CongresspersonDetailsActivity.class);
                        intent.putExtra("congressperson id", congressperson.getId());
                        startActivity(intent);
                    }
                });

                m_congresspeopleLinearLayout.addView(newTextView);
            }
        }
    }

}

