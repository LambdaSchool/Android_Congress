package com.lambdaschool.congressdetails;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.lambdaschool.congressdataapiaccess.CongressDao;
import com.lambdaschool.congressdataapiaccess.CongresspersonDetails;

public class CongresspersonDetailsViewModel extends ViewModel {
    private MutableLiveData<CongresspersonDetails> m_CongresspersonDetailsLD = new MutableLiveData<>();

    public LiveData<CongresspersonDetails> getCongresspersonDetailsLD() {
        return m_CongresspersonDetailsLD;
    }

    public void loadCongresspersonDetails(String id) {

        m_CongresspersonDetailsLD.postValue(CongressDao.getMemberDetails(id));

    }

}
