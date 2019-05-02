package com.lambdaschool.congressdetails;

import android.arch.lifecycle.MutableLiveData;

import com.lambdaschool.congressdataapiaccess.CongressDao;
import com.lambdaschool.congressdataapiaccess.CongresspersonDetails;

public class CongresspersonDetailRepo {

    public static MutableLiveData<CongresspersonDetails> wrapSinglePerson(String id) {
        MutableLiveData<CongresspersonDetails> person = new MutableLiveData<>();
        person.setValue(CongressDao.getMemberDetails(id));
        return person;
    }
}
