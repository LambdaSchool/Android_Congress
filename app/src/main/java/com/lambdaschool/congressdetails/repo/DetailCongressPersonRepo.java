package com.lambdaschool.congressdetails.repo;

import android.arch.lifecycle.MutableLiveData;

import com.lambdaschool.congressdataapiaccess.CongressDao;
import com.lambdaschool.congressdataapiaccess.CongresspersonDetails;

public class DetailCongressPersonRepo {

    public static MutableLiveData<CongresspersonDetails> getDetails(String id) {
        final MutableLiveData<CongresspersonDetails> liveData = new MutableLiveData<>();
        CongresspersonDetails profile = CongressDao.getMemberDetails(id);
        liveData.setValue(profile);
        return liveData;
    }
}
