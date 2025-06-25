package com.vaxwe.lencareapp.ui.admin.lista_users;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.vaxwe.lencareapp.R;
import com.vaxwe.lencareapp.databinding.FragmentListaABinding;
import com.vaxwe.lencareapp.ui.ListaUsuarios.AdminListAdapter;
import com.vaxwe.lencareapp.ui.ListaUsuarios.AdminListaModel;

public class ListaFragmentA extends Fragment {

    RecyclerView recyclerView;
    AdminListAdapter adminListAdapter;
    private FragmentListaABinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

    ListaViewModel listaViewModel = new ViewModelProvider(this).get(ListaViewModel.class);

    binding = FragmentListaABinding.inflate(inflater,container,false);
    View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.ViewListaAdmin);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<AdminListaModel> options = new FirebaseRecyclerOptions.Builder<AdminListaModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("DBAdmin"), AdminListaModel.class).build();


        adminListAdapter = new AdminListAdapter(options);
        recyclerView.setAdapter(adminListAdapter);

        // Inflate the layout for this fragment
        return root;



    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);


    }

    @Override
    public void onStart() {
        super.onStart();
        adminListAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adminListAdapter.stopListening();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}