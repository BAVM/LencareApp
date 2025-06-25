package com.vaxwe.lencareapp.ui.VerPnAdmin.ViewAccesorios;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.vaxwe.lencareapp.R;


public class AccesoriosAL extends AppCompatActivity {

    private Window window;

    String Color16 = "#3E6D9C";

    RecyclerView recyclerView;
    AccesoriosAdapter accesoriosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accesorios_al);

        ActionBar actionBar = getSupportActionBar(); //CREAMOS ACTIONBAR
        assert actionBar != null;                    //AFIRMAMOS QUE EL ACTIONBAR NO SEA NULO
        actionBar.setTitle("Accesorios");         //LE ASIGNAMOS UN TITULO
        actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Accesorios</font>"));

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.window = getWindow();

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary1)));
        window.setStatusBarColor(Color.parseColor(Color16));

        recyclerView = (RecyclerView) findViewById(R.id.ViewAccesoriosL);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<AccesoriosModo> options = new FirebaseRecyclerOptions.Builder<AccesoriosModo>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("ACCESORIOS"),AccesoriosModo.class).build();


        accesoriosAdapter = new AccesoriosAdapter(options);
        recyclerView.setAdapter(accesoriosAdapter);

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
        accesoriosAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        accesoriosAdapter.stopListening();
    }


    //BOTON DE BUSCAR

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_buscar,menu);
        MenuItem item = menu.findItem(R.id.BtnBuscar);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtBuscar(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String str) {
                txtBuscar(str);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void txtBuscar(String str){

        FirebaseRecyclerOptions<AccesoriosModo> options = new FirebaseRecyclerOptions.Builder<AccesoriosModo>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("ACCESORIOS").orderByChild("FAMILIA").startAt(str).endAt(str+"~"),AccesoriosModo.class).build();


        accesoriosAdapter = new AccesoriosAdapter(options);
        accesoriosAdapter.startListening();
        recyclerView.setAdapter(accesoriosAdapter);

    }


}