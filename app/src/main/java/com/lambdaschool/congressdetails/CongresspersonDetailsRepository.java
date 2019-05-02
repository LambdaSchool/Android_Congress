package com.lambdaschool.congressdetails;

import android.arch.lifecycle.MutableLiveData;

import com.lambdaschool.congressdataapiaccess.CongressDao;
import com.lambdaschool.congressdataapiaccess.CongresspersonDetails;

public class CongresspersonDetailsRepository {

    public MutableLiveData<CongresspersonDetails> getData(String id) {
        final MutableLiveData<CongresspersonDetails> liveData = new MutableLiveData<>();

        requestDetails(liveData, id);

        liveData.setValue(null);
        return liveData;
    }

    private void requestDetails(final MutableLiveData<CongresspersonDetails> liveData, final String id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                liveData.postValue(CongressDao.getMemberDetails(id));
            }
        }).start();
    }

}
