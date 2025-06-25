package com.vaxwe.lencareapp.ui.CategoriasCliente.Accesorios;

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

public class AccesoriosC extends AppCompatActivity {

    private Window window;

    String Color16 = "#3E6D9C";

    RecyclerView recyclerView;
    AccesoriosCAdapter accesoriosCAdapter;
    DatabaseReference mRef;

    ArrayList<AccesoriosCModo> listAccesoriosC;
    AccesoriosCBuscarAdapter accesoriosCBuscarAdapter;

    private SearchView searchViewPrimary,searchViewCl,searchViewAr,searchViewCo,searchViewMx,searchViewPe;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accesorios_c);


        ActionBar actionBar = getSupportActionBar(); //CREAMOS ACTIONBAR
        assert actionBar != null;                    //AFIRMAMOS QUE EL ACTIONBAR NO SEA NULO
        //actionBar.setTitle("Accesorios");         //LE ASIGNAMOS UN TITULO
        actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Accesorios</font>"));

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.window = getWindow();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary1)));
        window.setStatusBarColor(Color.parseColor(Color16));

        searchViewPrimary = findViewById(R.id.BtnBuscarAC);
        searchViewPrimary.clearFocus();

        searchViewCl = findViewById(R.id.BtnBuscarAC);
        searchViewCl.clearFocus();

        searchViewAr = findViewById(R.id.BtnBuscarAC);
        searchViewAr.clearFocus();

        searchViewCo = findViewById(R.id.BtnBuscarAC);
        searchViewCo.clearFocus();

        searchViewMx = findViewById(R.id.BtnBuscarAC);
        searchViewMx.clearFocus();

        searchViewPe = findViewById(R.id.BtnBuscarAC);
        searchViewPe.clearFocus();





        recyclerView = (RecyclerView) findViewById(R.id.ViewAccesoriosC);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        mRef = FirebaseDatabase.getInstance().getReference("ACCESORIOS");

        listAccesoriosC = new ArrayList<>();
        accesoriosCBuscarAdapter = new AccesoriosCBuscarAdapter(this,listAccesoriosC);
        recyclerView.setAdapter(accesoriosCBuscarAdapter);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                        AccesoriosCModo accesoriosCModo = snapshot.getValue(AccesoriosCModo.class);
                        listAccesoriosC.add(accesoriosCModo);
                    }

                }
                accesoriosCAdapter.notifyDataSetChanged();

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



        FirebaseRecyclerOptions<AccesoriosCModo> options = new FirebaseRecyclerOptions.Builder<AccesoriosCModo>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("ACCESORIOS"),AccesoriosCModo.class).build();


        accesoriosCAdapter = new AccesoriosCAdapter(options);
        recyclerView.setAdapter(accesoriosCAdapter);
        accesoriosCAdapter.notifyDataSetChanged();



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

                ArrayList<AccesoriosCModo>listAccesoriosFiltroA = new ArrayList<>();
                for (AccesoriosCModo obj: listAccesoriosC){
                    if (obj.getPAIS().equals("Argentina")){
                        listAccesoriosFiltroA.add(obj);

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
                accesoriosCBuscarAdapter = new AccesoriosCBuscarAdapter(accesoriosCBuscarAdapter.context, listAccesoriosFiltroA);
                recyclerView.setAdapter(accesoriosCBuscarAdapter);

                Toast.makeText(this, "Argentina", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnChile:

                ArrayList<AccesoriosCModo>listAccesoriosFiltroCL = new ArrayList<>();
                for (AccesoriosCModo obj: listAccesoriosC){
                    if (obj.getPAIS().equals("Chile")){
                        listAccesoriosFiltroCL.add(obj);

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
                accesoriosCBuscarAdapter = new AccesoriosCBuscarAdapter(accesoriosCBuscarAdapter.context, listAccesoriosFiltroCL);
                recyclerView.setAdapter(accesoriosCBuscarAdapter);

                Toast.makeText(this, "Chile", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnColombia:

                ArrayList<AccesoriosCModo>listAccesoriosFiltroCO = new ArrayList<>();
                for (AccesoriosCModo obj: listAccesoriosC){
                    if (obj.getPAIS().equals("Colombia")){
                        listAccesoriosFiltroCO.add(obj);

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
                accesoriosCBuscarAdapter = new AccesoriosCBuscarAdapter(accesoriosCBuscarAdapter.context, listAccesoriosFiltroCO);
                recyclerView.setAdapter(accesoriosCBuscarAdapter);

                Toast.makeText(this, "Colombia", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnMexico:

                ArrayList<AccesoriosCModo>listAccesoriosFiltroM = new ArrayList<>();
                for (AccesoriosCModo obj: listAccesoriosC){
                    if (obj.getPAIS().equals("Mexico")){
                        listAccesoriosFiltroM.add(obj);

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
                accesoriosCBuscarAdapter = new AccesoriosCBuscarAdapter(accesoriosCBuscarAdapter.context, listAccesoriosFiltroM);
                recyclerView.setAdapter(accesoriosCBuscarAdapter);

                Toast.makeText(this, "Mexico", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnPeru:

                ArrayList<AccesoriosCModo>listAccesoriosFiltroP = new ArrayList<>();
                for (AccesoriosCModo obj: listAccesoriosC){
                    if (obj.getPAIS().equals("Peru")){
                        listAccesoriosFiltroP.add(obj);

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
                accesoriosCBuscarAdapter = new AccesoriosCBuscarAdapter(accesoriosCBuscarAdapter.context, listAccesoriosFiltroP);
                recyclerView.setAdapter(accesoriosCBuscarAdapter);

                Toast.makeText(this, "Peru", Toast.LENGTH_SHORT).show();

                break;

        }
        return super.onOptionsItemSelected(item);
    }




    //BOTON BUSQUEDA PRINCIPAL CLIENTE
    private void filterList(String s) {
        ArrayList<AccesoriosCModo>listAccesoriosCt = new ArrayList<>();
        for (AccesoriosCModo obj: listAccesoriosC){
            if (obj.getFAMILIA().toLowerCase().contains(s.toLowerCase()) || obj.getPN().toLowerCase().contains(s.toLowerCase())){
                listAccesoriosCt.add(obj);
            }

        }
        accesoriosCBuscarAdapter = new AccesoriosCBuscarAdapter(accesoriosCBuscarAdapter.context, listAccesoriosCt);
        recyclerView.setAdapter(accesoriosCBuscarAdapter);

    }


    //FILTRO BUSCAR POR CHILE
    private void filterListCl(String cl) {
        ArrayList<AccesoriosCModo>listAccesoriosCt = new ArrayList<>();
        for (AccesoriosCModo obj: listAccesoriosC){
            if (obj.getPAIS().equals("Chile")){
                if (obj.getFAMILIA().toLowerCase().contains(cl.toLowerCase()) || obj.getPN().toLowerCase().contains(cl.toLowerCase())){
                    listAccesoriosCt.add(obj);
                }

            }

        }
        accesoriosCBuscarAdapter = new AccesoriosCBuscarAdapter(accesoriosCBuscarAdapter.context, listAccesoriosCt);
        recyclerView.setAdapter(accesoriosCBuscarAdapter);
    }

    //FILTRO BUSCAR POR ARGENTINA
    private void filterListAr(String ar) {
        ArrayList<AccesoriosCModo>listAccesoriosCt = new ArrayList<>();
        for (AccesoriosCModo obj: listAccesoriosC){
            if (obj.getPAIS().equals("Argentina")){
                if (obj.getFAMILIA().toLowerCase().contains(ar.toLowerCase()) || obj.getPN().toLowerCase().contains(ar.toLowerCase())){
                    listAccesoriosCt.add(obj);
                }

            }

        }
        accesoriosCBuscarAdapter = new AccesoriosCBuscarAdapter(accesoriosCBuscarAdapter.context, listAccesoriosCt);
        recyclerView.setAdapter(accesoriosCBuscarAdapter);

    }

    //FILTRO BUSCAR POR COLOMBIA
    private void filterListCo(String co) {
        ArrayList<AccesoriosCModo>listAccesoriosCt = new ArrayList<>();
        for (AccesoriosCModo obj: listAccesoriosC){
            if (obj.getPAIS().equals("Colombia")){
                if (obj.getFAMILIA().toLowerCase().contains(co.toLowerCase()) || obj.getPN().toLowerCase().contains(co.toLowerCase())){
                    listAccesoriosCt.add(obj);
                }

            }

        }
        accesoriosCBuscarAdapter = new AccesoriosCBuscarAdapter(accesoriosCBuscarAdapter.context, listAccesoriosCt);
        recyclerView.setAdapter(accesoriosCBuscarAdapter);
    }

    //FILTRO BUSCAR POR MEXICO
    private void filterListMx(String mx) {
        ArrayList<AccesoriosCModo>listAccesoriosCt = new ArrayList<>();
        for (AccesoriosCModo obj: listAccesoriosC){
            if (obj.getPAIS().equals("Mexico")){
                if (obj.getFAMILIA().toLowerCase().contains(mx.toLowerCase()) || obj.getPN().toLowerCase().contains(mx.toLowerCase())){
                    listAccesoriosCt.add(obj);
                }

            }

        }
        accesoriosCBuscarAdapter = new AccesoriosCBuscarAdapter(accesoriosCBuscarAdapter.context, listAccesoriosCt);
        recyclerView.setAdapter(accesoriosCBuscarAdapter);
    }

    //FILTRO BUSCAR POR PERU
    private void filterListPe(String pe) {
        ArrayList<AccesoriosCModo>listAccesoriosCt = new ArrayList<>();
        for (AccesoriosCModo obj: listAccesoriosC){
            if (obj.getPAIS().equals("Peru")){
                if (obj.getFAMILIA().toLowerCase().contains(pe.toLowerCase()) || obj.getPN().toLowerCase().contains(pe.toLowerCase())){
                    listAccesoriosCt.add(obj);
                }

            }

        }
        accesoriosCBuscarAdapter = new AccesoriosCBuscarAdapter(accesoriosCBuscarAdapter.context, listAccesoriosCt);
        recyclerView.setAdapter(accesoriosCBuscarAdapter);
    }




    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
        accesoriosCAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        accesoriosCAdapter.stopListening();
    }




}