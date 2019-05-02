package com.lambdaschool.congressdetails;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.lambdaschool.congressdataapiaccess.CongresspersonDetails;


public class DetailsViewModel extends ViewModel {

    private MutableLiveData<CongresspersonDetails> liveDetails;
    private DetailsRepo detailsRepo;

    public LiveData<CongresspersonDetails> getLiveData(Context context, String id) {
        if (liveDetails == null) {
            loadData(context, id);
        }
        return liveDetails;
    }

    public void loadData(Context context, String id) {
        detailsRepo = new DetailsRepo(context, id);
        liveDetails = detailsRepo.getDetails();
    }
}
