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

    private CongresspersonDetailsViewModel congresspersonDetailsViewModel;
    private LinearLayout m_congresspersonLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congressperson_details);

        m_congresspersonLinearLayout = findViewById(R.id.linear_layout_congressperson_details);

        congresspersonDetailsViewModel = ViewModelProviders.of(this).get(CongresspersonDetailsViewModel.class);

        Intent intent = getIntent();
        String congresspersonId = intent.getStringExtra(ListActivity.KEY_EXTRA_CONGRESSPERSON_ID);

        congresspersonDetailsViewModel.getCongresspersonDetailsLD(congresspersonId).observe(this, new CongresspersonDetailsObserver());
    }

    private class CongresspersonDetailsObserver implements Observer<CongresspersonDetails> {

        @Override
        public void onChanged(@Nullable CongresspersonDetails congresspersonDetails) {
            if (congresspersonDetails == null) {
                return;
            }

            // full name
            TextView textViewFullName = new TextView(CongresspersonDetailsActivity.this);
            String fullName = "Name: ";
            fullName += congresspersonDetails.getFirstName();
            if (!congresspersonDetails.getMiddleName().equals("null")) {
                fullName += " " + congresspersonDetails.getMiddleName();
            }
            if (!congresspersonDetails.getLastName().equals("null")) {
                fullName += " " + congresspersonDetails.getLastName();
            }
            textViewFullName.setText(fullName);
            textViewFullName.setTextSize(25.f);
            m_congresspersonLinearLayout.addView(textViewFullName);

            // party
            TextView textViewParty = new TextView(CongresspersonDetailsActivity.this);
            String party = "Party: ";
            party += congresspersonDetails.getCurrentParty();
            textViewParty.setText(party);
            textViewParty.setTextSize(25.f);
            m_congresspersonLinearLayout.addView(textViewParty);

            // bInOffice
            TextView textViewInOffice = new TextView(CongresspersonDetailsActivity.this);
            String inOffice = "In office: ";
            inOffice += congresspersonDetails.isInOffice() ? "Yes" : "No";
            textViewInOffice.setText(inOffice);
            textViewInOffice.setTextSize(25.f);
            m_congresspersonLinearLayout.addView(textViewInOffice);

        }
    }
}
