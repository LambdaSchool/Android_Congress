package com.lambdaschool.congressdetails;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.lambdaschool.congressdataapiaccess.CongresspersonOverview;

import java.util.ArrayList;

public class CongressViewModel extends ViewModel {
    private MutableLiveData<ArrayList<CongresspersonOverview>> data;
    private CongressRepository repo;

    public LiveData<ArrayList<CongresspersonOverview>> getCongress(){
        if(data == null){
            loadData();
        }
        return data;
    }

    private void loadData(){
        repo = new CongressRepository();
        data = repo.getData();
    }

    public void updateData(CongresspersonOverview person){
        data.setValue(repo.UpdateData(person));
    }
}
