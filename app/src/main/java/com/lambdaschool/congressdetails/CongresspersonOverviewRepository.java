package com.lambdaschool.congressdetails;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.AnyThread;

import com.lambdaschool.congressdataapiaccess.CongressDao;
import com.lambdaschool.congressdataapiaccess.CongresspersonOverview;

import java.util.ArrayList;

public class CongresspersonOverviewRepository {

    public MutableLiveData<ArrayList<CongresspersonOverview>> getData() {
        final MutableLiveData<ArrayList<CongresspersonOverview>> liveData = new MutableLiveData<>();

        requestAllMembers(liveData);

        liveData.setValue(new ArrayList<CongresspersonOverview>());
        return liveData;
    }

    @AnyThread
    public void reload(MutableLiveData<ArrayList<CongresspersonOverview>> liveData) {
        requestAllMembers(liveData);
    }

    private void requestAllMembers(final MutableLiveData<ArrayList<CongresspersonOverview>> liveData) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                liveData.postValue(CongressDao.getAllMembers());
            }
        }).start();
    }

}
