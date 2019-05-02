package com.lambdaschool.congressdetails;

import android.arch.lifecycle.MutableLiveData;

import com.lambdaschool.congressdataapiaccess.CongresspersonDetails;

public class CongressPersonRepository {
    private CongresspersonDetails data;

    public CongressPersonRepository() {
        data = new CongresspersonDetails();
    }

    public CongresspersonDetails updateData(CongresspersonDetails person){
        data = person;
        return data;
    }

    public MutableLiveData<CongresspersonDetails> getData(){
        MutableLiveData<CongresspersonDetails> liveData = new MutableLiveData<>();
        liveData.setValue(data);
        return liveData;
    }
}
