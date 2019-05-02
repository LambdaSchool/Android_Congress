package com.lambdaschool.congressdetails;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.lambdaschool.congressdataapiaccess.CongressDao;
import com.lambdaschool.congressdataapiaccess.CongresspersonDetails;

public class DetailsRepo {
    private CongresspersonDetails details;
    private String id;
    private Context context;

    public DetailsRepo(Context context, String id) {
        this.context = context;
        this.id = id;
    }

    public MutableLiveData<CongresspersonDetails> getDetails() {
        MutableLiveData<CongresspersonDetails> liveDetails =
                new MutableLiveData<>();
        details = CongressDao.getMemberDetails(id);
        liveDetails.setValue(details);
        return liveDetails;
    }
}
