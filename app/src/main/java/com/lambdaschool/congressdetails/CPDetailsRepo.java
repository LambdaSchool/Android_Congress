package com.lambdaschool.congressdetails;

import android.arch.lifecycle.MutableLiveData;

import com.lambdaschool.congressdataapiaccess.CongresspersonDetails;
import com.lambdaschool.congressdataapiaccess.CongressDao;


public class CPDetailsRepo {
    public static MutableLiveData<CongresspersonDetails> getDetails(String id) {
        final MutableLiveData<CongresspersonDetails> liveDataList = new MutableLiveData<>();
        CongresspersonDetails details = CongressDao.getMemberDetails(id);
        liveDataList.setValue(details);
        return liveDataList;
    }
}
