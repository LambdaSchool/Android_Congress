package com.lambdaschool.congressdetails;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.lambdaschool.congressdataapiaccess.CongresspersonOverview;

import java.util.ArrayList;

public class CongresspersonOverviewViewModel extends ViewModel {
    private MutableLiveData<ArrayList<CongresspersonOverview>> people;

    public LiveData<ArrayList<CongresspersonOverview>> getAllCongresspeople() {

        if (people == null) {
            people = CongresspersonOverviewRepo.wrapPeopleArray();
        }
        return people;
    }
}
