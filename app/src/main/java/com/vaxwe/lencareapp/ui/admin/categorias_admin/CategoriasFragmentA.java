package com.vaxwe.lencareapp.ui.admin.categorias_admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.vaxwe.lencareapp.R;
import com.vaxwe.lencareapp.databinding.FragmentCategoriasABinding;

public class CategoriasFragmentA extends Fragment {

    Button ThinkpadA, ThinkcentreA, IdeapadA, IdeacentreA, LegionA, YogaA, TabletsA, AccesoriosA, MonitoresA, ThinkbookA, SmartHubA;
    private FragmentCategoriasABinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        CategoriasViewModel categoriasViewModel = new ViewModelProvider(this).get(CategoriasViewModel.class);

        binding = FragmentCategoriasABinding.inflate(inflater,container,false);
        View root = binding.getRoot();


        ThinkpadA = root.findViewById(R.id.ThinkpadA);
        ThinkcentreA = root.findViewById(R.id.ThinkcentreA);
        IdeapadA = root.findViewById(R.id.IdeapadA);
        IdeacentreA = root.findViewById(R.id.IdeacentreA);
        LegionA = root.findViewById(R.id.LegionA);
        YogaA = root.findViewById(R.id.YogaA);
        TabletsA = root.findViewById(R.id.TabletsA);
        AccesoriosA = root.findViewById(R.id.AccesoriosA);
        MonitoresA = root.findViewById(R.id.MonitoresA);
        ThinkbookA = root.findViewById(R.id.ThinkbookA);
        SmartHubA = root.findViewById(R.id.SmartHubA);

        ThinkpadA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), com.vaxwe.lencareapp.ui.CategoriasAdmin.ThinkpadA.ThinkpadA.class));

            }
        });

        ThinkcentreA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), com.vaxwe.lencareapp.ui.CategoriasAdmin.ThinkcentreA.ThinkcentreA.class));

            }
        });

        IdeapadA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), com.vaxwe.lencareapp.ui.CategoriasAdmin.IdeapadA.IdeapadA.class));

            }
        });

        IdeacentreA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), com.vaxwe.lencareapp.ui.CategoriasAdmin.IdeacentreA.IdeacentreA.class));

            }
        });

        LegionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), com.vaxwe.lencareapp.ui.CategoriasAdmin.LegionA.LegionA.class));

            }
        });

        YogaA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), com.vaxwe.lencareapp.ui.CategoriasAdmin.YogaA.YogaA.class));

            }
        });

        TabletsA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), com.vaxwe.lencareapp.ui.CategoriasAdmin.TabletsA.TabletsA.class));

            }
        });

        AccesoriosA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), com.vaxwe.lencareapp.ui.CategoriasAdmin.AccesoriosA.AccesoriosA.class));

            }
        });

        MonitoresA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), com.vaxwe.lencareapp.ui.CategoriasAdmin.MonitoresA.MonitoresA.class));

            }
        });

        ThinkbookA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), com.vaxwe.lencareapp.ui.CategoriasAdmin.ThinkbookA.ThinkbookA.class));

            }
        });

        SmartHubA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), com.vaxwe.lencareapp.ui.CategoriasAdmin.ThinksmartA.ThinksmartA.class));

            }
        });



        // Inflate the layout for this fragment
        return root;


    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}