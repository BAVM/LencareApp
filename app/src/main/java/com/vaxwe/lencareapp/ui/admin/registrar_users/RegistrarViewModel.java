package com.vaxwe.lencareapp.ui.admin.registrar_users;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegistrarViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public RegistrarViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }

}
