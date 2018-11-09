package com.lambdaschool.congressdetails;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.lambdaschool.congressdataapiaccess.CongressDao;
import com.lambdaschool.congressdataapiaccess.CongresspersonDetails;

public class CongresspersonDetailsViewModel extends ViewModel {

    private MutableLiveData<CongresspersonDetails> liveDetails;

    public LiveData<CongresspersonDetails> getDetails(String id){
        if(liveDetails == null){
            loadData(id);
        }
        return liveDetails;
    }

    public void loadData(String id) {
        liveDetails = CongresspersonDetailsRepo.getDetails(id);
    }
}
