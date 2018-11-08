package com.lambdaschool.congressdetails;

import android.arch.lifecycle.MutableLiveData;

import com.lambdaschool.congressdataapiaccess.CongressDao;
import com.lambdaschool.congressdataapiaccess.CongresspersonOverview;

import java.util.ArrayList;

public class CongressPersonOverviewRepo {

    public static MutableLiveData<ArrayList<CongresspersonOverview>> getOverviewList() {
        final MutableLiveData<ArrayList<CongresspersonOverview>> liveDataList = new MutableLiveData<>();

        ArrayList<CongresspersonOverview> rawdata = CongressDao.getAllMembers();
        liveDataList.setValue(rawdata);
        return liveDataList;
    }


}


