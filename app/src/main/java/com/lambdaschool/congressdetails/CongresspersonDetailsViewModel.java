package com.lambdaschool.congressdetails;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.lambdaschool.congressdataapiaccess.CongresspersonDetails;

public class CongresspersonDetailsViewModel extends ViewModel {
    private MutableLiveData<CongresspersonDetails> m_CongresspersonDetailsLD;
    private CongresspersonDetailsRepository repository;
    private String id;

    public LiveData<CongresspersonDetails> getCongresspersonDetailsLD(String id) {
        if (m_CongresspersonDetailsLD == null) {
            this.id = id;
            loadCongresspersonDetails();
        }
        return m_CongresspersonDetailsLD;
    }

    private void loadCongresspersonDetails() {
        repository = new CongresspersonDetailsRepository();
        m_CongresspersonDetailsLD = repository.getData(id);
    }

}
