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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {


    private Context context;
    private LinearLayout listlayout;
    private CongressPersonViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        context = this;
        listlayout = findViewById(R.id.list_layout);

        viewModel = ViewModelProviders.of(this).get(CongressPersonViewModel.class);
        final Observer<ArrayList<CongresspersonOverview>> observer = new Observer<ArrayList<CongresspersonOverview>>() {
            @Override
            public void onChanged(@Nullable ArrayList<CongresspersonOverview> congresspersonOverviews) {
                if (congresspersonOverviews == null) {
                    refreshListView(congresspersonOverviews);
                }
            }
        };

    }

    private TextView getDefaultTextView(final CongresspersonOverview congresspersonOverviews){
        TextView textView = new TextView(context);
        textView.setText(congresspersonOverviews.getFirstName() + " " + congresspersonOverviews.getLastName());
        textView.setTextSize(18);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = congresspersonOverviews.getId();
                Intent intent = new Intent(context, DetailsActivity.class);
                //intent.putExtra(DetailsActivity.CONGRESS_DETAILS_KEY, congresspersonOverviews);
                //startActivityForResult(intent, );
            }
        });
    }

    private void refreshListView(ArrayList<CongresspersonOverview> congresspersonOverviews) {
        listlayout.removeAllViews();

        for(CongresspersonOverview cpOverview : congresspersonOverviews){
            //listlayout.addView();
        }
    }
}
