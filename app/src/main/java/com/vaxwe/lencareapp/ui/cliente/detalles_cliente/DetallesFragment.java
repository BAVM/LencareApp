package com.vaxwe.lencareapp.ui.cliente.detalles_cliente;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vaxwe.lencareapp.R;
import com.vaxwe.lencareapp.databinding.FragmentDetallesBinding;

public class DetallesFragment extends Fragment {

    TextView VaxweWeb,LenovoWeb;
    private FragmentDetallesBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        DetallesViewModel detallesViewModel = new ViewModelProvider(this).get(DetallesViewModel.class);

        binding = FragmentDetallesBinding.inflate(inflater,container,false);
        View root = binding.getRoot();

        VaxweWeb = root.findViewById(R.id.VaxweWeb);
        LenovoWeb = root.findViewById(R.id.LenovoWeb);

        VaxweWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri uri = Uri.parse("https://vaxwe.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });

        LenovoWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri uri = Uri.parse("https://www.lenovo.com/co/es/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });

        return root;


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}