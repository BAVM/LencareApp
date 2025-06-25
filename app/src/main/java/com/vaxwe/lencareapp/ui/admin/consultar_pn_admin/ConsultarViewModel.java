package com.vaxwe.lencareapp.ui.admin.consultar_pn_admin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConsultarViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public ConsultarViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
