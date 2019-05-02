package com.lambdaschool.congressdetails;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.AnyThread;

import com.lambdaschool.congressdataapiaccess.CongresspersonOverview;

import java.util.ArrayList;

public class CongresspeopleViewModel extends ViewModel {

    private MutableLiveData<ArrayList<CongresspersonOverview>> m_CongresspeopleLD;
    private CongresspersonOverviewRepository repository;

    public LiveData<ArrayList<CongresspersonOverview>> getCongresspeopleLD() {
        if (m_CongresspeopleLD == null) {
            loadCongresspeople();
        }

        return m_CongresspeopleLD;
    }

    private void loadCongresspeople() {
        repository = new CongresspersonOverviewRepository();
        m_CongresspeopleLD = repository.getData();
    }

    @AnyThread
    public void reload() {
        repository.reload(m_CongresspeopleLD);
    }


}
