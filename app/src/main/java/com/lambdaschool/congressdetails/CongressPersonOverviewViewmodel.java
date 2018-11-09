package com.lambdaschool.congressdetails;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.lambdaschool.congressdataapiaccess.CongresspersonOverview;

import java.util.ArrayList;

public class CongressPersonOverviewViewmodel extends ViewModel {
    private MutableLiveData<ArrayList<CongresspersonOverview>> congressList;

    public LiveData<ArrayList<CongresspersonOverview>> getCongressList(){
        if(congressList == null){
            Log.i("LOADLIST", "list is loaded");
            loadList();
        }
        return congressList;
    }

    private void loadList(){
        congressList = CongresspersonOverviewRepo.getOverviewList();
    }
}
