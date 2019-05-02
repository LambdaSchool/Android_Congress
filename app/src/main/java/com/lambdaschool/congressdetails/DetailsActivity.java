package com.lambdaschool.congressdetails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.lambdaschool.congressdataapiaccess.CongressDao;
import com.lambdaschool.congressdataapiaccess.CongresspersonDetails;
import com.lambdaschool.congressdataapiaccess.CongresspersonOverview;

public class DetailsActivity extends AppCompatActivity {

    TextView textTitle;
    TextView textParty;
    TextView textDOB;
    TextView textWebsite;
    TextView textTrackId;
    TextView textTwitter;
    TextView textFacebook;
    TextView textState;
    TextView textChamber;
    TextView textStartDate;
    TextView textEndDate;
    TextView textOffice;
    TextView textPhone;
    CongresspersonDetails person;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        if(intent.getExtras() != null){
            final String personId = intent.getStringExtra(CongressRepository.CONGRESS_INTENT_KEY);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final CongresspersonDetails networkPerson = CongressDao.getMemberDetails(personId);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            person = networkPerson;
                            updateUI(person);
                        }
                    });
                }
            }).start();
        }

        textTitle = findViewById(R.id.text_title);
        textParty = findViewById(R.id.text_party);
        textDOB = findViewById(R.id.text_dob);
        textWebsite = findViewById(R.id.text_website);
        textTrackId = findViewById(R.id.text_track_id);
        textTwitter = findViewById(R.id.text_twitter);
        textFacebook = findViewById(R.id.text_facebook);
    }

    private void updateUI(CongresspersonDetails person){
        textTitle.setText(String.format("%s %s", person.getFirstName(), person.getLastName()));
        textParty.setText(String.format("Party: %s", person.getCurrentParty()));
        textDOB.setText(String.format("Date of Birth: %s", person.getDateOfBirth()));
        textWebsite.setText(String.format("Website: %s", person.getUrl()));
        textTrackId.setText(String.format("Gov Track ID: %s", person.getGovtrackId()));
        textTwitter.setText(String.format("Twitter: %s", person.getTwitterAccount()));
        textFacebook.setText(String.format("Facebook: %s", person.getFacebookAccount()));

    }
}
