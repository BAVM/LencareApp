package com.vaxwe.lencareapp.ui.cliente.inicio_cliente;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vaxwe.lencareapp.NetworkChangeListener;
import com.vaxwe.lencareapp.R;
import com.vaxwe.lencareapp.databinding.FragmentHomeBinding;
import com.vaxwe.lencareapp.ui.Categorias.Cat_Dispositivo.CategoriaD;
import com.vaxwe.lencareapp.ui.Categorias.Cat_Dispositivo.ViewHolderCD;
import com.vaxwe.lencareapp.ui.Categorias.ControladorCD;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeFragment extends Fragment {

    TextView Valor_total_PN_C, ConteoThinkpadC, ConteoThinkcentreC, ConteoThinksmartC, ConteoIdeapadC, ConteoIdeacentreC,
            ConteoYogaC, ConteoLegionC, ConteoThinkbookC, ConteoTabletsC, ConteoMonitoresC, ConteoAccesoriosC;

    RecyclerView recyclerViewCategoriasD;
    FirebaseDatabase firebaseDatabaseD;
    DatabaseReference databaseReferenceD, BaseDatos;

    LinearLayoutManager linearLayoutManagerD;

    FirebaseRecyclerAdapter<CategoriaD, ViewHolderCD> firebaseRecyclerAdapterD;
    FirebaseRecyclerOptions<CategoriaD> optionsD;

    NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("LencareApp");


        firebaseDatabaseD = FirebaseDatabase.getInstance();
        databaseReferenceD = firebaseDatabaseD.getReference("CATEGORIAS_D");
        linearLayoutManagerD = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        recyclerViewCategoriasD = root.findViewById(R.id.recyclerViewCategoriasD);
        recyclerViewCategoriasD.setHasFixedSize(true);
        recyclerViewCategoriasD.setLayoutManager(linearLayoutManagerD);

        Valor_total_PN_C = root.findViewById(R.id.Valor_total_PN_C);
        ConteoThinkpadC = root.findViewById(R.id.ConteoThinkpadC);
        ConteoThinkcentreC = root.findViewById(R.id.ConteoThinkcentreC);
        ConteoThinksmartC = root.findViewById(R.id.ConteoThinksmartC);
        ConteoIdeapadC = root.findViewById(R.id.ConteoIdeapadC);
        ConteoIdeacentreC = root.findViewById(R.id.ConteoIdeacentreC);
        ConteoYogaC = root.findViewById(R.id.ConteoYogaC);
        ConteoLegionC = root.findViewById(R.id.ConteoLegionC);
        ConteoThinkbookC = root.findViewById(R.id.ConteoThinkbookC);
        ConteoTabletsC = root.findViewById(R.id.ConteoTabletsC);
        ConteoMonitoresC = root.findViewById(R.id.ConteoMonitoresC);
        ConteoAccesoriosC = root.findViewById(R.id.ConteoAccesoriosC);



        // MOSTRAR CANTIDAD DE NODOS DE LA BASE DE DATOS

        BaseDatos = FirebaseDatabase.getInstance().getReference();
        BaseDatos.child("CANTIDAD_PN").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()){
                    String cantidad2 = ""+datasnapshot.child("CANTIDADPN").getValue();
                    Valor_total_PN_C.setText(cantidad2);


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
                    ConteoThinkpadC.setText(""+ numHijos);

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
                    ConteoThinkcentreC.setText(""+ numHijos);

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
                    ConteoThinksmartC.setText(""+ numHijos);

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
                    ConteoIdeapadC.setText(""+ numHijos);

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
                    ConteoIdeacentreC.setText(""+ numHijos);

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
                    ConteoYogaC.setText(""+ numHijos);

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
                    ConteoLegionC.setText(""+ numHijos);

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
                    ConteoThinkbookC.setText(""+ numHijos);

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
                    ConteoTabletsC.setText(""+ numHijos);

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
                    ConteoMonitoresC.setText(""+ numHijos);

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
                    ConteoAccesoriosC.setText(""+ numHijos);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        VerCategoria();




        return root;


    }


    private void VerCategoria() {
        optionsD = new FirebaseRecyclerOptions.Builder<CategoriaD>().setQuery(databaseReferenceD, CategoriaD.class).build();
        firebaseRecyclerAdapterD = new FirebaseRecyclerAdapter<CategoriaD, ViewHolderCD>(optionsD) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolderCD viewHolderCD, int position, @NonNull CategoriaD categoriaD) {
                viewHolderCD.SeteoCategoriaD(
                        getActivity(), categoriaD.getCategoria(), categoriaD.getImagen()
                );
            }

            @NonNull
            @Override
            public ViewHolderCD onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.categorias_dispositivo, parent, false);
                ViewHolderCD viewHolderCD = new ViewHolderCD(itemview);

                viewHolderCD.setOnClickListener(new ViewHolderCD.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        String categoria = getItem(position).getCategoria();

                        Intent intent = new Intent(view.getContext(), ControladorCD.class);
                        intent.putExtra("Categoria", categoria);
                        startActivity(intent);
                        //Toast.makeText(getActivity(), categoria, Toast.LENGTH_SHORT).show();
                    }
                });


                return viewHolderCD;
            }
        };

        recyclerViewCategoriasD.setAdapter(firebaseRecyclerAdapterD);
    }


    @Override
    public void onStart() {

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        requireActivity().registerReceiver(networkChangeListener, filter);

        super.onStart();
        if (firebaseRecyclerAdapterD != null) {
            firebaseRecyclerAdapterD.startListening();

        }
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