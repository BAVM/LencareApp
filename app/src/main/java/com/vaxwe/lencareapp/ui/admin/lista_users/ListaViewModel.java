package com.vaxwe.lencareapp.ui.admin.lista_users;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListaViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ListaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }

}
