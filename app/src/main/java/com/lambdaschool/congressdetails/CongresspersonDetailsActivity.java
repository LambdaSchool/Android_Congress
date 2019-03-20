package com.lambdaschool.congressdetails;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lambdaschool.congressdataapiaccess.CongresspersonDetails;

public class CongresspersonDetailsActivity extends AppCompatActivity {

    private CongresspersonDetailsViewModel m_congresspersonDetailsViewModel;
    private LinearLayout m_congresspersonLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congressperson_details);

        m_congresspersonLinearLayout = findViewById(R.id.linear_layout_congressperson_details);

        m_congresspersonDetailsViewModel = ViewModelProviders.of(this).get(CongresspersonDetailsViewModel.class);

        Intent intent = getIntent();
        Object congresspersonIdObj = intent.getSerializableExtra("congressperson id");
        if (congresspersonIdObj != null) {
            String congresspersonId = (String)congresspersonIdObj;
            m_congresspersonDetailsViewModel.loadCongresspersonDetails(congresspersonId);
        }

        m_congresspersonDetailsViewModel.getCongresspersonDetailsLD().observe(this, new CongresspersonDetailsObserver());
    }

    private class CongresspersonDetailsObserver implements Observer<CongresspersonDetails> {

        @Override
        public void onChanged(@Nullable CongresspersonDetails congresspersonDetails) {
            if (congresspersonDetails == null) {
                return;
            }

            TextView textViewFullName = new TextView(CongresspersonDetailsActivity.this);
            String fullName = congresspersonDetails.getFirstName();
            fullName += " " + congresspersonDetails.getMiddleName();
            fullName += " " + congresspersonDetails.getLastName();
            textViewFullName.setTextSize(25.f);
            textViewFullName.setText(fullName);

            m_congresspersonLinearLayout.addView(textViewFullName);

        }
    }
}
