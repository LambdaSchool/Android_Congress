package com.lambdaschool.congressdetails;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.lambdaschool.congressdataapiaccess.CongressDao;
import com.lambdaschool.congressdataapiaccess.CongresspersonOverview;

import java.util.ArrayList;

public class CongresspeopleViewModel extends ViewModel {

    private MutableLiveData<ArrayList<CongresspersonOverview>> m_CongresspeopleLD = new MutableLiveData<>();

    public LiveData<ArrayList<CongresspersonOverview>> getCongresspeopleLD() {

        return m_CongresspeopleLD;
    }

    // @NOTE this is thread safe
    public void loadCongresspeople() {
        ArrayList<CongresspersonOverview> allMembers = CongressDao.getAllMembers();
        if (allMembers != null) {
            // @NOTE postValue will send notification ASYNC to observer
            m_CongresspeopleLD.postValue(allMembers);
            // @NOTE setValue will send notification SYNC to observer, must be called from UI thread
        }
    }


}
