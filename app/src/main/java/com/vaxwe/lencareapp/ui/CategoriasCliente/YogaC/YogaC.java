package com.vaxwe.lencareapp.ui.CategoriasCliente.YogaC;

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

public class YogaC extends AppCompatActivity {

    private Window window;

    String Color16 = "#3E6D9C";

    RecyclerView recyclerView;
    YogaCAdapter yogaCAdapter;
    YogaCBuscarAdapter yogaCBuscarAdapter;
    DatabaseReference mRef;
    ArrayList<YogaCModo> listYogaC;

    private SearchView searchViewPrimary,searchViewCl,searchViewAr,searchViewCo,searchViewMx,searchViewPe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga_c);



        ActionBar actionBar = getSupportActionBar(); //CREAMOS ACTIONBAR
        assert actionBar != null;                    //AFIRMAMOS QUE EL ACTIONBAR NO SEA NULO
        //actionBar.setTitle("Accesorios");         //LE ASIGNAMOS UN TITULO
        actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Yoga</font>"));

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.window = getWindow();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary1)));
        window.setStatusBarColor(Color.parseColor(Color16));

        searchViewPrimary = findViewById(R.id.BtnBuscarYOC);
        searchViewPrimary.clearFocus();

        searchViewCl = findViewById(R.id.BtnBuscarYOC);
        searchViewCl.clearFocus();

        searchViewAr = findViewById(R.id.BtnBuscarYOC);
        searchViewAr.clearFocus();

        searchViewCo = findViewById(R.id.BtnBuscarYOC);
        searchViewCo.clearFocus();

        searchViewMx = findViewById(R.id.BtnBuscarYOC);
        searchViewMx.clearFocus();

        searchViewPe = findViewById(R.id.BtnBuscarYOC);
        searchViewPe.clearFocus();

        recyclerView = (RecyclerView) findViewById(R.id.ViewYogaC);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRef = FirebaseDatabase.getInstance().getReference("YOGA");
        listYogaC = new ArrayList<>();
        yogaCBuscarAdapter = new YogaCBuscarAdapter(this,listYogaC);
        recyclerView.setAdapter(yogaCBuscarAdapter);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                        YogaCModo yogaCModo = snapshot.getValue(YogaCModo.class);
                        listYogaC.add(yogaCModo);
                    }

                }
                yogaCAdapter.notifyDataSetChanged();

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



        FirebaseRecyclerOptions<YogaCModo> options = new FirebaseRecyclerOptions.Builder<YogaCModo>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("YOGA"),YogaCModo.class).build();


        yogaCAdapter = new YogaCAdapter(options);
        recyclerView.setAdapter(yogaCAdapter);
        yogaCAdapter.notifyDataSetChanged();



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

                ArrayList<YogaCModo>listYogaFiltroAR = new ArrayList<>();
                for (YogaCModo obj: listYogaC){
                    if (obj.getPAIS().equals("Argentina")){
                        listYogaFiltroAR.add(obj);

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
                yogaCBuscarAdapter = new YogaCBuscarAdapter(yogaCBuscarAdapter.context, listYogaFiltroAR);
                recyclerView.setAdapter(yogaCBuscarAdapter);

                Toast.makeText(this, "Argentina", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnChile:

                ArrayList<YogaCModo>listYogaFiltroCL = new ArrayList<>();
                for (YogaCModo obj: listYogaC){
                    if (obj.getPAIS().equals("Chile")){
                        listYogaFiltroCL.add(obj);

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
                yogaCBuscarAdapter = new YogaCBuscarAdapter(yogaCBuscarAdapter.context, listYogaFiltroCL);
                recyclerView.setAdapter(yogaCBuscarAdapter);

                Toast.makeText(this, "Chile", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnColombia:

                ArrayList<YogaCModo>listYogaFiltroCO = new ArrayList<>();
                for (YogaCModo obj: listYogaC){
                    if (obj.getPAIS().equals("Colombia")){
                        listYogaFiltroCO.add(obj);

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
                yogaCBuscarAdapter = new YogaCBuscarAdapter(yogaCBuscarAdapter.context, listYogaFiltroCO);
                recyclerView.setAdapter(yogaCBuscarAdapter);

                Toast.makeText(this, "Colombia", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnMexico:

                ArrayList<YogaCModo>listYogaFiltroMX = new ArrayList<>();
                for (YogaCModo obj: listYogaC){
                    if (obj.getPAIS().equals("Mexico")){
                        listYogaFiltroMX.add(obj);

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
                yogaCBuscarAdapter = new YogaCBuscarAdapter(yogaCBuscarAdapter.context, listYogaFiltroMX);
                recyclerView.setAdapter(yogaCBuscarAdapter);

                Toast.makeText(this, "Mexico", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnPeru:

                ArrayList<YogaCModo>listYogaFiltroPE = new ArrayList<>();
                for (YogaCModo obj: listYogaC){
                    if (obj.getPAIS().equals("Peru")){
                        listYogaFiltroPE.add(obj);

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
                yogaCBuscarAdapter = new YogaCBuscarAdapter(yogaCBuscarAdapter.context, listYogaFiltroPE);
                recyclerView.setAdapter(yogaCBuscarAdapter);

                Toast.makeText(this, "Peru", Toast.LENGTH_SHORT).show();

                break;

        }
        return super.onOptionsItemSelected(item);
    }






    //BOTON BUSQUEDA PRINCIPAL CLIENTE
    private void filterList(String s) {
        ArrayList<YogaCModo>listYogaCt = new ArrayList<>();
        for (YogaCModo obj: listYogaC){
            if (obj.getFAMILIA().toLowerCase().contains(s.toLowerCase()) || obj.getPN().toLowerCase().contains(s.toLowerCase())){
                listYogaCt.add(obj);

            }

        }
        yogaCBuscarAdapter = new YogaCBuscarAdapter(yogaCBuscarAdapter.context, listYogaCt);
        recyclerView.setAdapter(yogaCBuscarAdapter);

    }


    //FILTRO BUSCAR POR CHILE
    private void filterListCl(String cl) {
        ArrayList<YogaCModo>listYogaCt = new ArrayList<>();
        for (YogaCModo obj: listYogaC){
            if (obj.getPAIS().equals("Chile")){
                if (obj.getFAMILIA().toLowerCase().contains(cl.toLowerCase()) || obj.getPN().toLowerCase().contains(cl.toLowerCase())){
                    listYogaCt.add(obj);
                }

            }

        }
        yogaCBuscarAdapter = new YogaCBuscarAdapter(yogaCBuscarAdapter.context, listYogaCt);
        recyclerView.setAdapter(yogaCBuscarAdapter);
    }

    //FILTRO BUSCAR POR ARGENTINA
    private void filterListAr(String ar) {
        ArrayList<YogaCModo>listYogaCt = new ArrayList<>();
        for (YogaCModo obj: listYogaC){
            if (obj.getPAIS().equals("Argentina")){
                if (obj.getFAMILIA().toLowerCase().contains(ar.toLowerCase()) || obj.getPN().toLowerCase().contains(ar.toLowerCase())){
                    listYogaCt.add(obj);
                }

            }

        }
        yogaCBuscarAdapter = new YogaCBuscarAdapter(yogaCBuscarAdapter.context, listYogaCt);
        recyclerView.setAdapter(yogaCBuscarAdapter);

    }

    //FILTRO BUSCAR POR COLOMBIA
    private void filterListCo(String co) {
        ArrayList<YogaCModo>listYogaCt = new ArrayList<>();
        for (YogaCModo obj: listYogaC){
            if (obj.getPAIS().equals("Colombia")){
                if (obj.getFAMILIA().toLowerCase().contains(co.toLowerCase()) || obj.getPN().toLowerCase().contains(co.toLowerCase())){
                    listYogaCt.add(obj);
                }

            }

        }
        yogaCBuscarAdapter = new YogaCBuscarAdapter(yogaCBuscarAdapter.context, listYogaCt);
        recyclerView.setAdapter(yogaCBuscarAdapter);
    }

    //FILTRO BUSCAR POR MEXICO
    private void filterListMx(String mx) {
        ArrayList<YogaCModo>listYogaCt = new ArrayList<>();
        for (YogaCModo obj: listYogaC){
            if (obj.getPAIS().equals("Mexico")){
                if (obj.getFAMILIA().toLowerCase().contains(mx.toLowerCase()) || obj.getPN().toLowerCase().contains(mx.toLowerCase())){
                    listYogaCt.add(obj);
                }

            }

        }
        yogaCBuscarAdapter = new YogaCBuscarAdapter(yogaCBuscarAdapter.context, listYogaCt);
        recyclerView.setAdapter(yogaCBuscarAdapter);
    }

    //FILTRO BUSCAR POR PERU
    private void filterListPe(String pe) {
        ArrayList<YogaCModo>listYogaCt = new ArrayList<>();
        for (YogaCModo obj: listYogaC){
            if (obj.getPAIS().equals("Peru")){
                if (obj.getFAMILIA().toLowerCase().contains(pe.toLowerCase()) || obj.getPN().toLowerCase().contains(pe.toLowerCase())){
                    listYogaCt.add(obj);
                }

            }

        }
        yogaCBuscarAdapter = new YogaCBuscarAdapter(yogaCBuscarAdapter.context, listYogaCt);
        recyclerView.setAdapter(yogaCBuscarAdapter);
    }





    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
        yogaCAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        yogaCAdapter.stopListening();
    }



}