package com.lambdaschool.congressdetails.viewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.lambdaschool.congressdataapiaccess.CongresspersonOverview;
import com.lambdaschool.congressdetails.repo.CongressPersonOverviewRepository;

import java.util.ArrayList;

public class CongressOverviewViewModel extends ViewModel {

    private MutableLiveData<ArrayList<CongresspersonOverview>> overviewList;

    public MutableLiveData<ArrayList<CongresspersonOverview>> getOverView() {
        if (overviewList == null) {
            load();
        }
        return overviewList;
    }

    private void load() {
        overviewList = CongressPersonOverviewRepository.getOverList();
    }
}
