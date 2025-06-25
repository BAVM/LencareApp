package com.vaxwe.lencareapp.ui.CategoriasCliente.LegionC;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vaxwe.lencareapp.R;

import java.util.ArrayList;

public class LegionC extends AppCompatActivity {

    private Window window;

    String Color16 = "#3E6D9C";

    RecyclerView recyclerView;
    LegionCAdapter legionCAdapter;
    DatabaseReference mRef;

    ArrayList<LegionCModo> listLegionC;
    LegionCBuscarAdapter legionCBuscarAdapter;

    private SearchView searchViewPrimary,searchViewCl,searchViewAr,searchViewCo,searchViewMx,searchViewPe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legion_c);

        ActionBar actionBar = getSupportActionBar(); //CREAMOS ACTIONBAR
        assert actionBar != null;                    //AFIRMAMOS QUE EL ACTIONBAR NO SEA NULO
        //actionBar.setTitle("Accesorios");         //LE ASIGNAMOS UN TITULO
        actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Legion</font>"));

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.window = getWindow();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary1)));
        window.setStatusBarColor(Color.parseColor(Color16));

        searchViewPrimary = findViewById(R.id.BtnBuscarLEC);
        searchViewPrimary.clearFocus();

        searchViewCl = findViewById(R.id.BtnBuscarLEC);
        searchViewCl.clearFocus();

        searchViewAr = findViewById(R.id.BtnBuscarLEC);
        searchViewAr.clearFocus();

        searchViewCo = findViewById(R.id.BtnBuscarLEC);
        searchViewCo.clearFocus();

        searchViewMx = findViewById(R.id.BtnBuscarLEC);
        searchViewMx.clearFocus();

        searchViewPe = findViewById(R.id.BtnBuscarLEC);
        searchViewPe.clearFocus();

        recyclerView = (RecyclerView) findViewById(R.id.ViewLegionC);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRef = FirebaseDatabase.getInstance().getReference("LEGION");
        listLegionC = new ArrayList<>();
        legionCBuscarAdapter = new LegionCBuscarAdapter(this,listLegionC);
        recyclerView.setAdapter(legionCBuscarAdapter);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                        LegionCModo legionCModo = snapshot.getValue(LegionCModo.class);
                        listLegionC.add(legionCModo);
                    }

                }
                legionCAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //BUSQUEDA PRINCIPAL CLIENTE
        searchViewPrimary.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });



        FirebaseRecyclerOptions<LegionCModo> options = new FirebaseRecyclerOptions.Builder<LegionCModo>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("LEGION"),LegionCModo.class).build();


        legionCAdapter = new LegionCAdapter(options);
        recyclerView.setAdapter(legionCAdapter);
        legionCAdapter.notifyDataSetChanged();

    }

    //BOTON FILTRAR

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_filtro,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.BtnArgentina:

                ArrayList<LegionCModo>listLegionFiltroAR = new ArrayList<>();
                for (LegionCModo obj: listLegionC){
                    if (obj.getPAIS().equals("Argentina")){
                        listLegionFiltroAR.add(obj);

                        searchViewAr.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String query) {
                                return false;
                            }

                            @Override
                            public boolean onQueryTextChange(String newText) {
                                filterListAr(newText);
                                return true;
                            }
                        });
                    }

                }
                legionCBuscarAdapter = new LegionCBuscarAdapter(legionCBuscarAdapter.context, listLegionFiltroAR);
                recyclerView.setAdapter(legionCBuscarAdapter);

                Toast.makeText(this, "Argentina", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnChile:

                ArrayList<LegionCModo>listLegionFiltroCL = new ArrayList<>();
                for (LegionCModo obj: listLegionC){
                    if (obj.getPAIS().equals("Chile")){
                        listLegionFiltroCL.add(obj);

                        searchViewCl.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String query) {
                                return false;
                            }

                            @Override
                            public boolean onQueryTextChange(String newText) {
                                filterListCl(newText);
                                return true;
                            }
                        });

                    }

                }
                legionCBuscarAdapter = new LegionCBuscarAdapter(legionCBuscarAdapter.context, listLegionFiltroCL);
                recyclerView.setAdapter(legionCBuscarAdapter);

                Toast.makeText(this, "Chile", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnColombia:

                ArrayList<LegionCModo>listLegionFiltroCO = new ArrayList<>();
                for (LegionCModo obj: listLegionC){
                    if (obj.getPAIS().equals("Colombia")){
                        listLegionFiltroCO.add(obj);

                        searchViewCo.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String query) {
                                return false;
                            }

                            @Override
                            public boolean onQueryTextChange(String newText) {
                                filterListCo(newText);
                                return true;
                            }
                        });
                    }

                }
                legionCBuscarAdapter = new LegionCBuscarAdapter(legionCBuscarAdapter.context, listLegionFiltroCO);
                recyclerView.setAdapter(legionCBuscarAdapter);

                Toast.makeText(this, "Colombia", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnMexico:

                ArrayList<LegionCModo>listLegionFiltroMX = new ArrayList<>();
                for (LegionCModo obj: listLegionC){
                    if (obj.getPAIS().equals("Mexico")){
                        listLegionFiltroMX.add(obj);

                        searchViewMx.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String query) {
                                return false;
                            }

                            @Override
                            public boolean onQueryTextChange(String newText) {
                                filterListMx(newText);
                                return true;
                            }
                        });
                    }

                }
                legionCBuscarAdapter = new LegionCBuscarAdapter(legionCBuscarAdapter.context, listLegionFiltroMX);
                recyclerView.setAdapter(legionCBuscarAdapter);

                Toast.makeText(this, "Mexico", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnPeru:

                ArrayList<LegionCModo>listLegionFiltroPE = new ArrayList<>();
                for (LegionCModo obj: listLegionC){
                    if (obj.getPAIS().equals("Peru")){
                        listLegionFiltroPE.add(obj);

                        searchViewPe.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String query) {
                                return false;
                            }

                            @Override
                            public boolean onQueryTextChange(String newText) {
                                filterListPe(newText);
                                return true;
                            }
                        });
                    }

                }
                legionCBuscarAdapter = new LegionCBuscarAdapter(legionCBuscarAdapter.context, listLegionFiltroPE);
                recyclerView.setAdapter(legionCBuscarAdapter);

                Toast.makeText(this, "Peru", Toast.LENGTH_SHORT).show();

                break;

        }
        return super.onOptionsItemSelected(item);
    }


    //BOTON BUSQUEDA PRINCIPAL CLIENTE
    private void filterList(String s) {
        ArrayList<LegionCModo>listLegionCt = new ArrayList<>();
        for (LegionCModo obj: listLegionC){
            if (obj.getFAMILIA().toLowerCase().contains(s.toLowerCase()) || obj.getPN().toLowerCase().contains(s.toLowerCase())){
                listLegionCt.add(obj);
            }

        }
        legionCBuscarAdapter = new LegionCBuscarAdapter(legionCBuscarAdapter.context, listLegionCt);
        recyclerView.setAdapter(legionCBuscarAdapter);

    }


    //FILTRO BUSCAR POR CHILE
    private void filterListCl(String cl) {
        ArrayList<LegionCModo>listLegionCt = new ArrayList<>();
        for (LegionCModo obj: listLegionC){
            if (obj.getPAIS().equals("Chile")){
                if (obj.getFAMILIA().toLowerCase().contains(cl.toLowerCase()) || obj.getPN().toLowerCase().contains(cl.toLowerCase())){
                    listLegionCt.add(obj);
                }

            }

        }
        legionCBuscarAdapter = new LegionCBuscarAdapter(legionCBuscarAdapter.context, listLegionCt);
        recyclerView.setAdapter(legionCBuscarAdapter);
    }

    //FILTRO BUSCAR POR ARGENTINA
    private void filterListAr(String ar) {
        ArrayList<LegionCModo>listLegionCt = new ArrayList<>();
        for (LegionCModo obj: listLegionC){
            if (obj.getPAIS().equals("Argentina")){
                if (obj.getFAMILIA().toLowerCase().contains(ar.toLowerCase()) || obj.getPN().toLowerCase().contains(ar.toLowerCase())){
                    listLegionCt.add(obj);
                }

            }

        }
        legionCBuscarAdapter = new LegionCBuscarAdapter(legionCBuscarAdapter.context, listLegionCt);
        recyclerView.setAdapter(legionCBuscarAdapter);

    }

    //FILTRO BUSCAR POR COLOMBIA
    private void filterListCo(String co) {
        ArrayList<LegionCModo>listLegionCt = new ArrayList<>();
        for (LegionCModo obj: listLegionC){
            if (obj.getPAIS().equals("Colombia")){
                if (obj.getFAMILIA().toLowerCase().contains(co.toLowerCase()) || obj.getPN().toLowerCase().contains(co.toLowerCase())){
                    listLegionCt.add(obj);
                }

            }

        }
        legionCBuscarAdapter = new LegionCBuscarAdapter(legionCBuscarAdapter.context, listLegionCt);
        recyclerView.setAdapter(legionCBuscarAdapter);
    }

    //FILTRO BUSCAR POR MEXICO
    private void filterListMx(String mx) {
        ArrayList<LegionCModo>listLegionCt = new ArrayList<>();
        for (LegionCModo obj: listLegionC){
            if (obj.getPAIS().equals("Mexico")){
                if (obj.getFAMILIA().toLowerCase().contains(mx.toLowerCase()) || obj.getPN().toLowerCase().contains(mx.toLowerCase())){
                    listLegionCt.add(obj);
                }

            }

        }
        legionCBuscarAdapter = new LegionCBuscarAdapter(legionCBuscarAdapter.context, listLegionCt);
        recyclerView.setAdapter(legionCBuscarAdapter);
    }

    //FILTRO BUSCAR POR PERU
    private void filterListPe(String pe) {
        ArrayList<LegionCModo>listLegionCt = new ArrayList<>();
        for (LegionCModo obj: listLegionC){
            if (obj.getPAIS().equals("Peru")){
                if (obj.getFAMILIA().toLowerCase().contains(pe.toLowerCase()) || obj.getPN().toLowerCase().contains(pe.toLowerCase())){
                    listLegionCt.add(obj);
                }

            }

        }
        legionCBuscarAdapter = new LegionCBuscarAdapter(legionCBuscarAdapter.context, listLegionCt);
        recyclerView.setAdapter(legionCBuscarAdapter);
    }







    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
        legionCAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        legionCAdapter.stopListening();
    }

}