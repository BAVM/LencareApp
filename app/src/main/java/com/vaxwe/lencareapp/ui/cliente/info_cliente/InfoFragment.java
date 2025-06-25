package com.vaxwe.lencareapp.ui.cliente.info_cliente;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vaxwe.lencareapp.R;
import com.vaxwe.lencareapp.databinding.FragmentInfoBinding;

import java.util.ArrayList;
import java.util.List;

public class InfoFragment extends Fragment {

    RecyclerView recyclerView;
    List<InfoRecyclerView> infoRecyclerViewsList;
    private FragmentInfoBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        InfoViewModel infoViewModel = new ViewModelProvider(this).get(InfoViewModel.class);

        binding = FragmentInfoBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        // Inflate the layout for this fragment


        recyclerView = root.findViewById(R.id.infoRecyclerView);

        iniData();
        setRecyclerView();



        return  root;

    }

    private void setRecyclerView() {
        InfoRecyclerAdapter infoRecyclerAdapter = new InfoRecyclerAdapter(infoRecyclerViewsList);
        recyclerView.setAdapter(infoRecyclerAdapter);
        recyclerView.setHasFixedSize(true);

    }

    private void iniData() {
        infoRecyclerViewsList = new ArrayList<>();

        infoRecyclerViewsList.add(new InfoRecyclerView("Teclado","ES LAS – Teclado no retro-iluminado español LAS.","EN USA – Teclado no retro-iluminado ingles USA",
                "B ES LAS – Teclado retro-iluminado español LAS","B EN USA – Teclado retro-iluminado Ingles USA.","B RGB ES LAS – Teclado retro-iluminado RGB español LAS.",
                "B RGB EN USA – Teclado retro-iluminado RGB ingles USA."));

        infoRecyclerViewsList.add(new InfoRecyclerView("Procesador","Intel NO VPRO - Intel I7-1165G7, no lleva las letras VP.","Intel SI VPRO – Intel I7-1185G7VP, el nombre si lleva las letras VP",
                "AMD NO PRO – AMD Ryzen 7 5800H, No cuenta con la palabra PRO","AMD SI PRO – AMD Ryzen 7 PRO 5850U, cuenta con la palabra PRO.","",
                ""));

        infoRecyclerViewsList.add(new InfoRecyclerView("BIOS","NO - Significa que el PN no esta customizado en BIOS o sistema operativo","SI - El PN esta customizado en BIOS y/o Sistema Operativo",
                "","","",
                ""));


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}