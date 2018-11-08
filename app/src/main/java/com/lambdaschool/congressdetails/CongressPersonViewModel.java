package com.lambdaschool.congressdetails;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.lambdaschool.congressdataapiaccess.CongresspersonDetails;
import com.lambdaschool.congressdataapiaccess.CongresspersonOverview;

import java.util.ArrayList;

public class CongressPersonViewModel {

    private MutableLiveData<ArrayList<CongresspersonDetails>> congressPersonDetailsList;
    private CongressPersonOverviewRepo repo;

    public LiveData<ArrayList<CongresspersonOverview>> getCongresspersonOverviewList(){
        if(congressPersonDetailsList == null) {
            //create list
        }

        return congressPersonDetailsList;
    }

    private void loadlist(){
        cong
    }
}
