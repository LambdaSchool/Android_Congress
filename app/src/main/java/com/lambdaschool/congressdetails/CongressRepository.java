package com.lambdaschool.congressdetails;

import android.arch.lifecycle.MutableLiveData;

import com.lambdaschool.congressdataapiaccess.CongresspersonOverview;

import java.util.ArrayList;

public class CongressRepository {
    public static final String CONGRESS_INTENT_KEY = "Person";
    private ArrayList<CongresspersonOverview> data;

    public CongressRepository() {
        data = new ArrayList<>();
    }

    public ArrayList<CongresspersonOverview> UpdateData(CongresspersonOverview person){
        data.add(person);
        return data;
    }

    public MutableLiveData<ArrayList<CongresspersonOverview>> getData (){
        MutableLiveData<ArrayList<CongresspersonOverview>> liveData = new MutableLiveData<>();
        liveData.setValue(data);

        return liveData;
    }
}
