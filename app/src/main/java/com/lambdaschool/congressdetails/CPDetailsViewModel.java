package com.lambdaschool.congressdetails;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.lambdaschool.congressdataapiaccess.CongresspersonDetails;

public class CPDetailsViewModel extends ViewModel {

    private MutableLiveData<CongresspersonDetails> liveDetails;

    public LiveData<CongresspersonDetails> getDetails(String id) {
        if (liveDetails == null) {
            loadData(id);
        }
        return liveDetails;
    }

    private void loadData(String id) {
        liveDetails = CPDetailsRepo.getDetails(id);

    }


}
