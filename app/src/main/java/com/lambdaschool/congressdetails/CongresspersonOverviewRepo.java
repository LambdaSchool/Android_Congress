package com.lambdaschool.congressdetails;

import android.arch.lifecycle.MutableLiveData;

import com.lambdaschool.congressdataapiaccess.CongressDao;
import com.lambdaschool.congressdataapiaccess.CongresspersonOverview;

import java.util.ArrayList;

public class CongresspersonOverviewRepo {

    public static MutableLiveData<ArrayList<CongresspersonOverview>> wrapPeopleArray() {
        MutableLiveData<ArrayList<CongresspersonOverview>> people = new MutableLiveData<>();
        ArrayList<CongresspersonOverview> rawData = CongressDao.getAllMembers();
        CongressDao.getAllMembers();
        people.setValue(rawData);
        return people;
    }
}
