package com.lambdaschool.congressdetails;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.lambdaschool.congressdataapiaccess.CongresspersonDetails;
import com.lambdaschool.congressdataapiaccess.CongresspersonOverview;

import java.util.ArrayList;

public class CongresspersonDetailViewModel extends ViewModel {
    private MutableLiveData<CongresspersonDetails> person;

    public LiveData<CongresspersonDetails> getSingleCongressPerson(String id) {

        if (person == null) {
            person = CongresspersonDetailRepo.wrapSinglePerson(id);
        }
        return person;
    }
}
