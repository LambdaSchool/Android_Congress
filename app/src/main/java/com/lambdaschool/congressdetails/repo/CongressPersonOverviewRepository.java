package com.lambdaschool.congressdetails.repo;

import android.arch.lifecycle.MutableLiveData;

import com.lambdaschool.congressdataapiaccess.CongressDao;
import com.lambdaschool.congressdataapiaccess.CongresspersonOverview;

import java.util.ArrayList;

public class CongressPersonOverviewRepository {

    public static MutableLiveData<ArrayList<CongresspersonOverview>> getOverList() {
        MutableLiveData<ArrayList<CongresspersonOverview>> liveData = new MutableLiveData<>();
        ArrayList<CongresspersonOverview> rawData = CongressDao.getAllMembers();
        liveData.setValue(rawData);
        return liveData;
    }
}
