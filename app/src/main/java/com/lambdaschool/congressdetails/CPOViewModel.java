package com.lambdaschool.congressdetails;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.lambdaschool.congressdataapiaccess.CongresspersonOverview;

import java.util.ArrayList;

public class CPOViewModel extends ViewModel {
    private MutableLiveData<ArrayList<CongresspersonOverview>> liveData;
    private CPORepo repository;

    public LiveData<ArrayList<CongresspersonOverview>> getLiveData(Context context) {
        if (liveData == null) {
            loadData(context);
        }
        return liveData;
    }

    public void loadData(Context context) {
        repository = new CPORepo(context);
        liveData = repository.getData();
    }
    
}
