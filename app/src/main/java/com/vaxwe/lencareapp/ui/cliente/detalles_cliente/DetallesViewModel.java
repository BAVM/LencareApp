package com.vaxwe.lencareapp.ui.cliente.detalles_cliente;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DetallesViewModel extends ViewModel {

    private  final MutableLiveData<String> mText;

    public DetallesViewModel(){
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
