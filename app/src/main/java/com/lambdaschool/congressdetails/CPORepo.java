package com.lambdaschool.congressdetails;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.lambdaschool.congressdataapiaccess.CongressDao;
import com.lambdaschool.congressdataapiaccess.CongresspersonOverview;

import java.util.ArrayList;

public class CPORepo {
    private ArrayList<CongresspersonOverview> rawData;
    private Context context;

    public CPORepo(Context context) {
        rawData = new ArrayList<>();
        this.context = context;
    }

    public MutableLiveData<ArrayList<CongresspersonOverview>> getData() {
        final MutableLiveData<ArrayList<CongresspersonOverview>> liveData =
                new MutableLiveData<>();
        rawData = CongressDao.getAllMembers();
        liveData.setValue(rawData);
        return liveData;
    }


}
