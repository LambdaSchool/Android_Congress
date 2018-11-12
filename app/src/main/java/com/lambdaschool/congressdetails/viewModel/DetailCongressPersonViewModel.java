package com.lambdaschool.congressdetails.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.lambdaschool.congressdataapiaccess.CongresspersonDetails;
import com.lambdaschool.congressdetails.repo.DetailCongressPersonRepo;

public class DetailCongressPersonViewModel extends ViewModel {
    private MutableLiveData<CongresspersonDetails> liveData;

    public LiveData<CongresspersonDetails> getDetails(String id) {
        if (liveData == null) {
            loadData(id);
        }
        return liveData;
    }

    private void loadData(String id) {
        liveData = DetailCongressPersonRepo.getDetails(id);
    }
}
