package com.vaxwe.lencareapp.ui.CategoriasCliente.TabletsC;

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

public class TabletsC extends AppCompatActivity {

    private Window window;

    String Color16 = "#3E6D9C";

    RecyclerView recyclerView;
    TabletsCAdapter tabletsCAdapter;
    DatabaseReference mRef;
    ArrayList<TabletsCModo> listTabletsC;
    TabletsCBuscarAdapter tabletsCBuscarAdapter;

    private SearchView searchViewPrimary,searchViewCl,searchViewAr,searchViewCo,searchViewMx,searchViewPe;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablets_c);

        ActionBar actionBar = getSupportActionBar(); //CREAMOS ACTIONBAR
        assert actionBar != null;                    //AFIRMAMOS QUE EL ACTIONBAR NO SEA NULO
        //actionBar.setTitle("Accesorios");         //LE ASIGNAMOS UN TITULO
        actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Tablets</font>"));

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.window = getWindow();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary1)));
        window.setStatusBarColor(Color.parseColor(Color16));


        searchViewPrimary = findViewById(R.id.BtnBuscarTAC);
        searchViewPrimary.clearFocus();

        searchViewCl = findViewById(R.id.BtnBuscarTAC);
        searchViewCl.clearFocus();

        searchViewAr = findViewById(R.id.BtnBuscarTAC);
        searchViewAr.clearFocus();

        searchViewCo = findViewById(R.id.BtnBuscarTAC);
        searchViewCo.clearFocus();

        searchViewMx = findViewById(R.id.BtnBuscarTAC);
        searchViewMx.clearFocus();

        searchViewPe = findViewById(R.id.BtnBuscarTAC);
        searchViewPe.clearFocus();

        recyclerView = (RecyclerView) findViewById(R.id.ViewTabletsC);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRef = FirebaseDatabase.getInstance().getReference("TABLETS");
        listTabletsC = new ArrayList<>();
        tabletsCBuscarAdapter = new TabletsCBuscarAdapter(this,listTabletsC);
        recyclerView.setAdapter(tabletsCBuscarAdapter);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                        TabletsCModo tabletsCModo = snapshot.getValue(TabletsCModo.class);
                        listTabletsC.add(tabletsCModo);
                    }

                }
                tabletsCAdapter.notifyDataSetChanged();

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



        FirebaseRecyclerOptions<TabletsCModo> options = new FirebaseRecyclerOptions.Builder<TabletsCModo>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("TABLETS"),TabletsCModo.class).build();


        tabletsCAdapter = new TabletsCAdapter(options);
        recyclerView.setAdapter(tabletsCAdapter);
        tabletsCAdapter.notifyDataSetChanged();



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

                ArrayList<TabletsCModo>listTabletsFiltroAR = new ArrayList<>();
                for (TabletsCModo obj: listTabletsC){
                    if (obj.getPAIS().equals("Argentina")){
                        listTabletsFiltroAR.add(obj);

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
                tabletsCBuscarAdapter = new TabletsCBuscarAdapter(tabletsCBuscarAdapter.context, listTabletsFiltroAR);
                recyclerView.setAdapter(tabletsCBuscarAdapter);

                Toast.makeText(this, "Argentina", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnChile:

                ArrayList<TabletsCModo>listTabletsFiltroCL = new ArrayList<>();
                for (TabletsCModo obj: listTabletsC){
                    if (obj.getPAIS().equals("Chile")){
                        listTabletsFiltroCL.add(obj);

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
                tabletsCBuscarAdapter = new TabletsCBuscarAdapter(tabletsCBuscarAdapter.context, listTabletsFiltroCL);
                recyclerView.setAdapter(tabletsCBuscarAdapter);

                Toast.makeText(this, "Chile", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnColombia:

                ArrayList<TabletsCModo>listTabletsFiltroCO = new ArrayList<>();
                for (TabletsCModo obj: listTabletsC){
                    if (obj.getPAIS().equals("Colombia")){
                        listTabletsFiltroCO.add(obj);

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
                tabletsCBuscarAdapter = new TabletsCBuscarAdapter(tabletsCBuscarAdapter.context, listTabletsFiltroCO);
                recyclerView.setAdapter(tabletsCBuscarAdapter);

                Toast.makeText(this, "Colombia", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnMexico:

                ArrayList<TabletsCModo>listTabletsFiltroMX = new ArrayList<>();
                for (TabletsCModo obj: listTabletsC){
                    if (obj.getPAIS().equals("Mexico")){
                        listTabletsFiltroMX.add(obj);

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
                tabletsCBuscarAdapter = new TabletsCBuscarAdapter(tabletsCBuscarAdapter.context, listTabletsFiltroMX);
                recyclerView.setAdapter(tabletsCBuscarAdapter);

                Toast.makeText(this, "Mexico", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnPeru:

                ArrayList<TabletsCModo>listTabletsFiltroPE = new ArrayList<>();
                for (TabletsCModo obj: listTabletsC){
                    if (obj.getPAIS().equals("Peru")){
                        listTabletsFiltroPE.add(obj);

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
                tabletsCBuscarAdapter = new TabletsCBuscarAdapter(tabletsCBuscarAdapter.context, listTabletsFiltroPE);
                recyclerView.setAdapter(tabletsCBuscarAdapter);

                Toast.makeText(this, "Peru", Toast.LENGTH_SHORT).show();

                break;

        }
        return super.onOptionsItemSelected(item);
    }





    //BOTON BUSQUEDA PRINCIPAL CLIENTE
    private void filterList(String s) {
        ArrayList<TabletsCModo>listTabletsCt = new ArrayList<>();
        for (TabletsCModo obj: listTabletsC){
            if (obj.getFAMILIA().toLowerCase().contains(s.toLowerCase()) || obj.getPN().toLowerCase().contains(s.toLowerCase())){
                listTabletsCt.add(obj);
            }

        }
        tabletsCBuscarAdapter = new TabletsCBuscarAdapter(tabletsCBuscarAdapter.context, listTabletsCt);
        recyclerView.setAdapter(tabletsCBuscarAdapter);

    }


    //FILTRO BUSCAR POR CHILE
    private void filterListCl(String cl) {
        ArrayList<TabletsCModo>listTabletsCt = new ArrayList<>();
        for (TabletsCModo obj: listTabletsC){
            if (obj.getPAIS().equals("Chile")){
                if (obj.getFAMILIA().toLowerCase().contains(cl.toLowerCase()) || obj.getPN().toLowerCase().contains(cl.toLowerCase())){
                    listTabletsCt.add(obj);
                }

            }

        }
        tabletsCBuscarAdapter = new TabletsCBuscarAdapter(tabletsCBuscarAdapter.context, listTabletsCt);
        recyclerView.setAdapter(tabletsCBuscarAdapter);
    }

    //FILTRO BUSCAR POR ARGENTINA
    private void filterListAr(String ar) {
        ArrayList<TabletsCModo>listTabletsCt = new ArrayList<>();
        for (TabletsCModo obj: listTabletsC){
            if (obj.getPAIS().equals("Argentina")){
                if (obj.getFAMILIA().toLowerCase().contains(ar.toLowerCase()) || obj.getPN().toLowerCase().contains(ar.toLowerCase())){
                    listTabletsCt.add(obj);
                }

            }

        }
        tabletsCBuscarAdapter = new TabletsCBuscarAdapter(tabletsCBuscarAdapter.context, listTabletsCt);
        recyclerView.setAdapter(tabletsCBuscarAdapter);

    }

    //FILTRO BUSCAR POR COLOMBIA
    private void filterListCo(String co) {
        ArrayList<TabletsCModo>listTabletsCt = new ArrayList<>();
        for (TabletsCModo obj: listTabletsC){
            if (obj.getPAIS().equals("Colombia")){
                if (obj.getFAMILIA().toLowerCase().contains(co.toLowerCase()) || obj.getPN().toLowerCase().contains(co.toLowerCase())){
                    listTabletsCt.add(obj);
                }

            }

        }
        tabletsCBuscarAdapter = new TabletsCBuscarAdapter(tabletsCBuscarAdapter.context, listTabletsCt);
        recyclerView.setAdapter(tabletsCBuscarAdapter);
    }

    //FILTRO BUSCAR POR MEXICO
    private void filterListMx(String mx) {
        ArrayList<TabletsCModo>listTabletsCt = new ArrayList<>();
        for (TabletsCModo obj: listTabletsC){
            if (obj.getPAIS().equals("Mexico")){
                if (obj.getFAMILIA().toLowerCase().contains(mx.toLowerCase()) || obj.getPN().toLowerCase().contains(mx.toLowerCase())){
                    listTabletsCt.add(obj);
                }

            }

        }
        tabletsCBuscarAdapter = new TabletsCBuscarAdapter(tabletsCBuscarAdapter.context, listTabletsCt);
        recyclerView.setAdapter(tabletsCBuscarAdapter);
    }

    //FILTRO BUSCAR POR PERU
    private void filterListPe(String pe) {
        ArrayList<TabletsCModo>listTabletsCt = new ArrayList<>();
        for (TabletsCModo obj: listTabletsC){
            if (obj.getPAIS().equals("Peru")){
                if (obj.getFAMILIA().toLowerCase().contains(pe.toLowerCase()) || obj.getPN().toLowerCase().contains(pe.toLowerCase())){
                    listTabletsCt.add(obj);
                }

            }

        }
        tabletsCBuscarAdapter = new TabletsCBuscarAdapter(tabletsCBuscarAdapter.context, listTabletsCt);
        recyclerView.setAdapter(tabletsCBuscarAdapter);
    }






    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
        tabletsCAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        tabletsCAdapter.stopListening();
    }


}