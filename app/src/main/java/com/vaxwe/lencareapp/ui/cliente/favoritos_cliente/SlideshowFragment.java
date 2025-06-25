package com.vaxwe.lencareapp.ui.cliente.favoritos_cliente;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vaxwe.lencareapp.R;
import com.vaxwe.lencareapp.databinding.FragmentSlideshowBinding;
import com.vaxwe.lencareapp.ui.FavoritosC.FavoritosAdapter;
import com.vaxwe.lencareapp.ui.FavoritosC.FavoritosModo;

import java.util.ArrayList;

public class SlideshowFragment extends Fragment {

    RecyclerView recyclerView;
    FavoritosAdapter favoritosAdapter;
    ArrayList<FavoritosModo> listFavoritos;
    DatabaseReference mRef;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        recyclerView = (RecyclerView) root.findViewById(R.id.ViewFavoritosC);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRef = FirebaseDatabase.getInstance().getReference("FAVORITOS");

        listFavoritos = new ArrayList<>();

        FirebaseRecyclerOptions<FavoritosModo> options = new FirebaseRecyclerOptions.Builder<FavoritosModo>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("FAVORITOS").child(user.getUid()),FavoritosModo.class).build();

        favoritosAdapter = new FavoritosAdapter(options);
        recyclerView.setAdapter(favoritosAdapter);
        favoritosAdapter.notifyDataSetChanged();


        return root;
    }


    @Override
    public void onStart() {
        super.onStart();
        favoritosAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        favoritosAdapter.stopListening();
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}