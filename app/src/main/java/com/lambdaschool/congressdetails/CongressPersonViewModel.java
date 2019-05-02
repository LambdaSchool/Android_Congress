package com.lambdaschool.congressdetails;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.lambdaschool.congressdataapiaccess.CongresspersonDetails;

public class CongressPersonViewModel extends ViewModel {
    private MutableLiveData<CongresspersonDetails> data;
    private CongressPersonRepository repo;

    public LiveData<CongresspersonDetails> getPerson(){
        if(data == null){
            loadData();
        }
        return data;
    }

    private void loadData(){
        repo = new CongressPersonRepository();
        data = repo.getData();
    }

    public void updateData(CongresspersonDetails person){
        data.setValue(repo.updateData(person));
    }
}
