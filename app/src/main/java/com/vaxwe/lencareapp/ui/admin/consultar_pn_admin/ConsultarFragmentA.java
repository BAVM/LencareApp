package com.vaxwe.lencareapp.ui.admin.consultar_pn_admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.vaxwe.lencareapp.R;
import com.vaxwe.lencareapp.databinding.FragmentConsultarABinding;

public class ConsultarFragmentA extends Fragment {

    Button ThinkpadAL,ThinkcentreAL,ThinkbookAL,ThinksmartAL,IdeapadAL,IdeacentreAL,MonitoresAL,LegionAL,YogaAL,TabletsAL,AccesoriosAL;
    private FragmentConsultarABinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ConsultarViewModel consultarViewModel = new ViewModelProvider(this).get(ConsultarViewModel.class);

        binding = FragmentConsultarABinding.inflate(inflater,container,false);
        View root = binding.getRoot();

        ThinkpadAL = root.findViewById(R.id.ThinkpadAL);
        ThinkcentreAL = root.findViewById(R.id.ThinkcentreAL);
        ThinkbookAL = root.findViewById(R.id.ThinkbookAL);
        ThinksmartAL = root.findViewById(R.id.ThinksmartAL);
        IdeapadAL = root.findViewById(R.id.IdeapadAL);
        IdeacentreAL = root.findViewById(R.id.IdeacentreAL);
        MonitoresAL = root.findViewById(R.id.MonitoresAL);
        LegionAL = root.findViewById(R.id.LegionAL);
        YogaAL = root.findViewById(R.id.YogaAL);
        TabletsAL = root.findViewById(R.id.TabletsAL);
        AccesoriosAL = root.findViewById(R.id.AccesoriosAL);



        ThinkpadAL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), com.vaxwe.lencareapp.ui.VerPnAdmin.ViewThinkpad.ThinkpadAL.class));

            }
        });

        ThinkcentreAL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), com.vaxwe.lencareapp.ui.VerPnAdmin.ViewThinkcentre.ThinkcentreAL.class));

            }
        });

        ThinkbookAL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), com.vaxwe.lencareapp.ui.VerPnAdmin.ViewThinkbook.ThinkbookAL.class));

            }
        });

        ThinksmartAL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), com.vaxwe.lencareapp.ui.VerPnAdmin.ViewThinksmart.ThinksmartAL.class));

            }
        });

        IdeapadAL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), com.vaxwe.lencareapp.ui.VerPnAdmin.ViewIdeapad.IdeapadAL.class));

            }
        });

        IdeacentreAL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), com.vaxwe.lencareapp.ui.VerPnAdmin.ViewIdeacentre.IdeacentreAL.class));

            }
        });

        MonitoresAL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), com.vaxwe.lencareapp.ui.VerPnAdmin.ViewMonitores.MonitoresAL.class));

            }
        });

        LegionAL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), com.vaxwe.lencareapp.ui.VerPnAdmin.ViewLegion.LegionAL.class));

            }
        });

        YogaAL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), com.vaxwe.lencareapp.ui.VerPnAdmin.ViewYoga.YogaAL.class));

            }
        });

        TabletsAL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), com.vaxwe.lencareapp.ui.VerPnAdmin.ViewTablets.TabletsAL.class));

            }
        });

        AccesoriosAL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), com.vaxwe.lencareapp.ui.VerPnAdmin.ViewAccesorios.AccesoriosAL.class));

            }
        });




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