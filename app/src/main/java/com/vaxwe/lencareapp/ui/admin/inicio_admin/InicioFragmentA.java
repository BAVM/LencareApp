package com.vaxwe.lencareapp.ui.admin.inicio_admin;

import android.app.ProgressDialog;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vaxwe.lencareapp.NetworkChangeListener;
import com.vaxwe.lencareapp.R;
import com.vaxwe.lencareapp.databinding.FragmentInicioABinding;
import com.vaxwe.lencareapp.ui.CategoriasAdmin.MonitoresA.MonitoresA;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class InicioFragmentA extends Fragment {

    TextView Fecha2, Valor_total_PN_A, ConteoThinkpadA, ConteoThinkcentreA, ConteoThinksmartA, ConteoIdeapadA, ConteoIdeacentreA,
            ConteoYogaA, ConteoLegionA, ConteoThinkbookA, ConteoTabletsA, ConteoMonitoresA, ConteoAccesoriosA;

    EditText ActualizarCantidadPN;

    Button BtbActualizarCantidadPN;

    DatabaseReference BaseDatos;

    ProgressDialog progressDialog;

    NetworkChangeListener networkChangeListener = new NetworkChangeListener();
    private FragmentInicioABinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        InicioViewModel inicioViewModel = new ViewModelProvider(this).get(InicioViewModel.class);

        binding = FragmentInicioABinding.inflate(inflater,container,false);
        View root = binding.getRoot();

        //Fecha2 = root.findViewById(R.id.Fecha2);

        ActualizarCantidadPN = root.findViewById(R.id.ActualizarCantidadPN);
        BtbActualizarCantidadPN = root.findViewById(R.id.BtbActualizarCantidadPN);

        Valor_total_PN_A = root.findViewById(R.id.Valor_total_PN_A);
        ConteoThinkpadA = root.findViewById(R.id.ConteoThinkpadA);
        ConteoThinkcentreA = root.findViewById(R.id.ConteoThinkcentreA);
        ConteoThinksmartA = root.findViewById(R.id.ConteoThinksmartA);
        ConteoIdeapadA = root.findViewById(R.id.ConteoIdeapadA);
        ConteoIdeacentreA = root.findViewById(R.id.ConteoIdeacentreA);
        ConteoYogaA = root.findViewById(R.id.ConteoYogaA);
        ConteoLegionA = root.findViewById(R.id.ConteoLegionA);
        ConteoThinkbookA = root.findViewById(R.id.ConteoThinkbookA);
        ConteoTabletsA = root.findViewById(R.id.ConteoTabletsA);
        ConteoMonitoresA = root.findViewById(R.id.ConteoMonitoresA);
        ConteoAccesoriosA = root.findViewById(R.id.ConteoAccesoriosA);



        /*Date date = new Date();
        SimpleDateFormat fecha2 = new SimpleDateFormat("d 'de' MMM 'de' yyyy");
        String Sfecha = fecha2.format(date); // SE CONVIERTE LA FECHA A STRING
        Fecha2.setText(Sfecha);*/


        // MOSTRAR CANTIDAD DE NODOS DE LA BASE DE DATOS

        BtbActualizarCantidadPN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String CantidadPN = ActualizarCantidadPN.getText().toString();

                if (CantidadPN.equals("")){
                    Toast.makeText(getActivity(), "Campo vacio.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Map<String, Object>Cantidad = new HashMap<>();
                    Cantidad.put("CANTIDADPN",CantidadPN);

                    //INICIALIZAMOS FIREBASE DATABASE
                    BaseDatos.child("CANTIDAD_PN").updateChildren(Cantidad);
                    Toast.makeText(getActivity(), "Valor actualizado", Toast.LENGTH_SHORT).show();
                }

            }
        });




        BaseDatos = FirebaseDatabase.getInstance().getReference();
        BaseDatos.child("CANTIDAD_PN").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()){
                    String cantidad2 = ""+datasnapshot.child("CANTIDADPN").getValue();
                    Valor_total_PN_A.setText(cantidad2);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        BaseDatos = FirebaseDatabase.getInstance().getReference();
        BaseDatos.child("THINKPAD").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()){
                    long sum = 0;
                    long numHijos = datasnapshot.getChildrenCount();
                    sum += numHijos;
                    ConteoThinkpadA.setText(""+ numHijos);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        BaseDatos = FirebaseDatabase.getInstance().getReference();
        BaseDatos.child("THINKCENTRE").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()){
                    long sum = 0;
                    long numHijos = datasnapshot.getChildrenCount();
                    sum += numHijos;
                    ConteoThinkcentreA.setText(""+ numHijos);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        BaseDatos = FirebaseDatabase.getInstance().getReference();
        BaseDatos.child("THINKSMART").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()){
                    long sum = 0;
                    long numHijos = datasnapshot.getChildrenCount();
                    sum += numHijos;
                    ConteoThinksmartA.setText(""+ numHijos);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        BaseDatos = FirebaseDatabase.getInstance().getReference();
        BaseDatos.child("IDEAPAD").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()){
                    long sum = 0;
                    long numHijos = datasnapshot.getChildrenCount();
                    sum += numHijos;
                    ConteoIdeapadA.setText(""+ numHijos);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        BaseDatos = FirebaseDatabase.getInstance().getReference();
        BaseDatos.child("IDEACENTRE").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()){
                    long sum = 0;
                    long numHijos = datasnapshot.getChildrenCount();
                    sum += numHijos;
                    ConteoIdeacentreA.setText(""+ numHijos);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        BaseDatos = FirebaseDatabase.getInstance().getReference();
        BaseDatos.child("YOGA").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()){
                    long sum = 0;
                    long numHijos = datasnapshot.getChildrenCount();
                    sum += numHijos;
                    ConteoYogaA.setText(""+ numHijos);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        BaseDatos = FirebaseDatabase.getInstance().getReference();
        BaseDatos.child("LEGION").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()){
                    long sum = 0;
                    long numHijos = datasnapshot.getChildrenCount();
                    sum += numHijos;
                    ConteoLegionA.setText(""+ numHijos);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        BaseDatos = FirebaseDatabase.getInstance().getReference();
        BaseDatos.child("THINKBOOK").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()){
                    long sum = 0;
                    long numHijos = datasnapshot.getChildrenCount();
                    sum += numHijos;
                    ConteoThinkbookA.setText(""+ numHijos);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        BaseDatos = FirebaseDatabase.getInstance().getReference();
        BaseDatos.child("TABLETS").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()){
                    long sum = 0;
                    long numHijos = datasnapshot.getChildrenCount();
                    sum += numHijos;
                    ConteoTabletsA.setText(""+ numHijos);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        BaseDatos = FirebaseDatabase.getInstance().getReference();
        BaseDatos.child("MONITORES").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()){
                    long sum = 0;
                    long numHijos = datasnapshot.getChildrenCount();
                    sum += numHijos;
                    ConteoMonitoresA.setText(""+ numHijos);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        BaseDatos = FirebaseDatabase.getInstance().getReference();
        BaseDatos.child("ACCESORIOS").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()){
                    long sum = 0;
                    long numHijos = datasnapshot.getChildrenCount();
                    sum += numHijos;
                    ConteoAccesoriosA.setText(""+ numHijos);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        return root;

    }

    @Override
    public void onStart() {

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        requireActivity().registerReceiver(networkChangeListener, filter);

        super.onStart();

    }

    @Override
    public void onStop() {
        requireActivity().unregisterReceiver(networkChangeListener);
        super.onStop();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}