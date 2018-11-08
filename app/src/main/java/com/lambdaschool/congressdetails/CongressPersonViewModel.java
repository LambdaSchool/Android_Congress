package com.lambdaschool.congressdetails;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.lambdaschool.congressdataapiaccess.CongresspersonOverview;

import java.util.ArrayList;

public class CongressPersonViewModel extends ViewModel {

    private MutableLiveData<ArrayList<CongresspersonOverview>> congressPersonDetailsList;


    public LiveData<ArrayList<CongresspersonOverview>> getCongresspersonOverviewList(){
        if(congressPersonDetailsList == null) {
            loadlist();
        }
        return congressPersonDetailsList;
    }

    private void loadlist(){
        congressPersonDetailsList = CongressPersonOverviewRepo.getOverviewList();
    }
}
