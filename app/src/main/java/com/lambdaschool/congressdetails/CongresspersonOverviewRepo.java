package com.lambdaschool.congressdetails;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.lambdaschool.congressdataapiaccess.CongressDao;
import com.lambdaschool.congressdataapiaccess.CongresspersonOverview;

import java.util.ArrayList;

public class CongresspersonOverviewRepo {

    public static MutableLiveData<ArrayList<CongresspersonOverview>> getOverviewList(){
        final MutableLiveData<ArrayList<CongresspersonOverview>> liveDataList = new MutableLiveData<>();
        ArrayList<CongresspersonOverview> rawData = CongressDao.getAllMembers();
        liveDataList.setValue(rawData);
        return liveDataList;
    }

}
