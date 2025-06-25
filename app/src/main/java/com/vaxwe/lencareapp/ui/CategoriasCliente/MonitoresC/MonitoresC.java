package com.vaxwe.lencareapp.ui.CategoriasCliente.MonitoresC;

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

public class MonitoresC extends AppCompatActivity {

    private Window window;

    String Color16 = "#3E6D9C";

    RecyclerView recyclerView;
    MonitoresCAdapter monitoresCAdapter;
    DatabaseReference mRef;
    ArrayList<MonitoresCModo> listMonitoresC;
    MonitoresCBuscarAdapter monitoresCBuscarAdapter;

    private SearchView searchViewPrimary,searchViewCl,searchViewAr,searchViewCo,searchViewMx,searchViewPe;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitores_c);

        ActionBar actionBar = getSupportActionBar(); //CREAMOS ACTIONBAR
        assert actionBar != null;                    //AFIRMAMOS QUE EL ACTIONBAR NO SEA NULO
        //actionBar.setTitle("Accesorios");         //LE ASIGNAMOS UN TITULO
        actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Monitores</font>"));

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.window = getWindow();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary1)));
        window.setStatusBarColor(Color.parseColor(Color16));

        searchViewPrimary = findViewById(R.id.BtnBuscarMOC);
        searchViewPrimary.clearFocus();

        searchViewCl = findViewById(R.id.BtnBuscarMOC);
        searchViewCl.clearFocus();

        searchViewAr = findViewById(R.id.BtnBuscarMOC);
        searchViewAr.clearFocus();

        searchViewCo = findViewById(R.id.BtnBuscarMOC);
        searchViewCo.clearFocus();

        searchViewMx = findViewById(R.id.BtnBuscarMOC);
        searchViewMx.clearFocus();

        searchViewPe = findViewById(R.id.BtnBuscarMOC);
        searchViewPe.clearFocus();

        recyclerView = (RecyclerView) findViewById(R.id.ViewMonitoresC);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRef = FirebaseDatabase.getInstance().getReference("MONITORES");
        listMonitoresC = new ArrayList<>();
        monitoresCBuscarAdapter = new MonitoresCBuscarAdapter(this,listMonitoresC);
        recyclerView.setAdapter(monitoresCBuscarAdapter);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                        MonitoresCModo monitoresCModo = snapshot.getValue(MonitoresCModo.class);
                        listMonitoresC.add(monitoresCModo);
                    }

                }
                monitoresCAdapter.notifyDataSetChanged();

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



        FirebaseRecyclerOptions<MonitoresCModo> options = new FirebaseRecyclerOptions.Builder<MonitoresCModo>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("MONITORES"),MonitoresCModo.class).build();


        monitoresCAdapter = new MonitoresCAdapter(options);
        recyclerView.setAdapter(monitoresCAdapter);
        monitoresCAdapter.notifyDataSetChanged();



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

                ArrayList<MonitoresCModo>listMonitoresFiltroAR = new ArrayList<>();
                for (MonitoresCModo obj: listMonitoresC){
                    if (obj.getPAIS().equals("Argentina")){
                        listMonitoresFiltroAR.add(obj);

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
                monitoresCBuscarAdapter = new MonitoresCBuscarAdapter(monitoresCBuscarAdapter.context, listMonitoresFiltroAR);
                recyclerView.setAdapter(monitoresCBuscarAdapter);

                Toast.makeText(this, "Argentina", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnChile:

                ArrayList<MonitoresCModo>listMonitoresFiltroCL = new ArrayList<>();
                for (MonitoresCModo obj: listMonitoresC){
                    if (obj.getPAIS().equals("Chile")){
                        listMonitoresFiltroCL.add(obj);

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
                monitoresCBuscarAdapter = new MonitoresCBuscarAdapter(monitoresCBuscarAdapter.context, listMonitoresFiltroCL);
                recyclerView.setAdapter(monitoresCBuscarAdapter);

                Toast.makeText(this, "Chile", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnColombia:

                ArrayList<MonitoresCModo>listMonitoresFiltroCO = new ArrayList<>();
                for (MonitoresCModo obj: listMonitoresC){
                    if (obj.getPAIS().equals("Colombia")){
                        listMonitoresFiltroCO.add(obj);

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
                monitoresCBuscarAdapter = new MonitoresCBuscarAdapter(monitoresCBuscarAdapter.context, listMonitoresFiltroCO);
                recyclerView.setAdapter(monitoresCBuscarAdapter);

                Toast.makeText(this, "Colombia", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnMexico:

                ArrayList<MonitoresCModo>listMonitoresFiltroMX = new ArrayList<>();
                for (MonitoresCModo obj: listMonitoresC){
                    if (obj.getPAIS().equals("Mexico")){
                        listMonitoresFiltroMX.add(obj);

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
                monitoresCBuscarAdapter = new MonitoresCBuscarAdapter(monitoresCBuscarAdapter.context, listMonitoresFiltroMX);
                recyclerView.setAdapter(monitoresCBuscarAdapter);

                Toast.makeText(this, "Mexico", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnPeru:

                ArrayList<MonitoresCModo>listMonitoresFiltroPE = new ArrayList<>();
                for (MonitoresCModo obj: listMonitoresC){
                    if (obj.getPAIS().equals("Peru")){
                        listMonitoresFiltroPE.add(obj);

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
                monitoresCBuscarAdapter = new MonitoresCBuscarAdapter(monitoresCBuscarAdapter.context, listMonitoresFiltroPE);
                recyclerView.setAdapter(monitoresCBuscarAdapter);

                Toast.makeText(this, "Peru", Toast.LENGTH_SHORT).show();

                break;

        }
        return super.onOptionsItemSelected(item);
    }






    //BOTON BUSQUEDA PRINCIPAL CLIENTE
    private void filterList(String s) {
        ArrayList<MonitoresCModo>listMonitoresCt = new ArrayList<>();
        for (MonitoresCModo obj: listMonitoresC){
            if (obj.getFAMILIA().toLowerCase().contains(s.toLowerCase()) || obj.getPN().toLowerCase().contains(s.toLowerCase())){
                listMonitoresCt.add(obj);
            }

        }
        monitoresCBuscarAdapter = new MonitoresCBuscarAdapter(monitoresCBuscarAdapter.context, listMonitoresCt);
        recyclerView.setAdapter(monitoresCBuscarAdapter);

    }


    //FILTRO BUSCAR POR CHILE
    private void filterListCl(String cl) {
        ArrayList<MonitoresCModo>listMonitoresCt = new ArrayList<>();
        for (MonitoresCModo obj: listMonitoresC){
            if (obj.getPAIS().equals("Chile")){
                if (obj.getFAMILIA().toLowerCase().contains(cl.toLowerCase()) || obj.getPN().toLowerCase().contains(cl.toLowerCase())){
                    listMonitoresCt.add(obj);
                }

            }

        }
        monitoresCBuscarAdapter = new MonitoresCBuscarAdapter(monitoresCBuscarAdapter.context, listMonitoresCt);
        recyclerView.setAdapter(monitoresCBuscarAdapter);
    }

    //FILTRO BUSCAR POR ARGENTINA
    private void filterListAr(String ar) {
        ArrayList<MonitoresCModo>listMonitoresCt = new ArrayList<>();
        for (MonitoresCModo obj: listMonitoresC){
            if (obj.getPAIS().equals("Argentina")){
                if (obj.getFAMILIA().toLowerCase().contains(ar.toLowerCase()) || obj.getPN().toLowerCase().contains(ar.toLowerCase())){
                    listMonitoresCt.add(obj);
                }

            }

        }
        monitoresCBuscarAdapter = new MonitoresCBuscarAdapter(monitoresCBuscarAdapter.context, listMonitoresCt);
        recyclerView.setAdapter(monitoresCBuscarAdapter);

    }

    //FILTRO BUSCAR POR COLOMBIA
    private void filterListCo(String co) {
        ArrayList<MonitoresCModo>listMonitoresCt = new ArrayList<>();
        for (MonitoresCModo obj: listMonitoresC){
            if (obj.getPAIS().equals("Colombia")){
                if (obj.getFAMILIA().toLowerCase().contains(co.toLowerCase()) || obj.getPN().toLowerCase().contains(co.toLowerCase())){
                    listMonitoresCt.add(obj);
                }

            }

        }
        monitoresCBuscarAdapter = new MonitoresCBuscarAdapter(monitoresCBuscarAdapter.context, listMonitoresCt);
        recyclerView.setAdapter(monitoresCBuscarAdapter);
    }

    //FILTRO BUSCAR POR MEXICO
    private void filterListMx(String mx) {
        ArrayList<MonitoresCModo>listMonitoresCt = new ArrayList<>();
        for (MonitoresCModo obj: listMonitoresC){
            if (obj.getPAIS().equals("Mexico")){
                if (obj.getFAMILIA().toLowerCase().contains(mx.toLowerCase()) || obj.getPN().toLowerCase().contains(mx.toLowerCase())){
                    listMonitoresCt.add(obj);
                }

            }

        }
        monitoresCBuscarAdapter = new MonitoresCBuscarAdapter(monitoresCBuscarAdapter.context, listMonitoresCt);
        recyclerView.setAdapter(monitoresCBuscarAdapter);
    }

    //FILTRO BUSCAR POR PERU
    private void filterListPe(String pe) {
        ArrayList<MonitoresCModo>listMonitoresCt = new ArrayList<>();
        for (MonitoresCModo obj: listMonitoresC){
            if (obj.getPAIS().equals("Peru")){
                if (obj.getFAMILIA().toLowerCase().contains(pe.toLowerCase()) || obj.getPN().toLowerCase().contains(pe.toLowerCase())){
                    listMonitoresCt.add(obj);
                }

            }

        }
        monitoresCBuscarAdapter = new MonitoresCBuscarAdapter(monitoresCBuscarAdapter.context, listMonitoresCt);
        recyclerView.setAdapter(monitoresCBuscarAdapter);
    }





    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
        monitoresCAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        monitoresCAdapter.stopListening();
    }



}