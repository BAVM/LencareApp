package com.vaxwe.lencareapp.ui.CategoriasCliente.ThinkcentreC;

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

public class ThinkcentreC extends AppCompatActivity {

    private Window window;

    String Color16 = "#3E6D9C";

    RecyclerView recyclerView;
    ThinkcentreCAdapter thinkcentreCAdapter;
    ThinkcentreCBuscarAdapter thinkcentreCBuscarAdapter;
    DatabaseReference mRef;
    ArrayList<ThinkcentreCModo> listThinkcentreC;

    private SearchView searchViewPrimary,searchViewCl,searchViewAr,searchViewCo,searchViewMx,searchViewPe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thinkcentre_c);

        ActionBar actionBar = getSupportActionBar(); //CREAMOS ACTIONBAR
        assert actionBar != null;                    //AFIRMAMOS QUE EL ACTIONBAR NO SEA NULO
        //actionBar.setTitle("Accesorios");         //LE ASIGNAMOS UN TITULO
        actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Thinkcentre</font>"));

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.window = getWindow();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary1)));
        window.setStatusBarColor(Color.parseColor(Color16));

        searchViewPrimary = findViewById(R.id.BtnBuscarTCC);
        searchViewPrimary.clearFocus();

        searchViewCl = findViewById(R.id.BtnBuscarTCC);
        searchViewCl.clearFocus();

        searchViewAr = findViewById(R.id.BtnBuscarTCC);
        searchViewAr.clearFocus();

        searchViewCo = findViewById(R.id.BtnBuscarTCC);
        searchViewCo.clearFocus();

        searchViewMx = findViewById(R.id.BtnBuscarTCC);
        searchViewMx.clearFocus();

        searchViewPe = findViewById(R.id.BtnBuscarTCC);
        searchViewPe.clearFocus();

        recyclerView = (RecyclerView) findViewById(R.id.ViewThinkcentreC);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRef = FirebaseDatabase.getInstance().getReference("THINKCENTRE");
        listThinkcentreC = new ArrayList<>();
        thinkcentreCBuscarAdapter = new ThinkcentreCBuscarAdapter(this,listThinkcentreC);
        recyclerView.setAdapter(thinkcentreCBuscarAdapter);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                        ThinkcentreCModo thinkcentreCModo = snapshot.getValue(ThinkcentreCModo.class);
                        listThinkcentreC.add(thinkcentreCModo);
                    }

                }
                thinkcentreCAdapter.notifyDataSetChanged();

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



        FirebaseRecyclerOptions<ThinkcentreCModo> options = new FirebaseRecyclerOptions.Builder<ThinkcentreCModo>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("THINKCENTRE"),ThinkcentreCModo.class).build();


        thinkcentreCAdapter = new ThinkcentreCAdapter(options);
        recyclerView.setAdapter(thinkcentreCAdapter);
        thinkcentreCAdapter.notifyDataSetChanged();


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

                ArrayList<ThinkcentreCModo>listThinkcentreFiltroAR = new ArrayList<>();
                for (ThinkcentreCModo obj: listThinkcentreC){
                    if (obj.getPAIS().equals("Argentina")){
                        listThinkcentreFiltroAR.add(obj);

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
                thinkcentreCBuscarAdapter = new ThinkcentreCBuscarAdapter(thinkcentreCBuscarAdapter.context, listThinkcentreFiltroAR);
                recyclerView.setAdapter(thinkcentreCBuscarAdapter);

                Toast.makeText(this, "Argentina", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnChile:

                ArrayList<ThinkcentreCModo>listThinkcentreFiltroCL = new ArrayList<>();
                for (ThinkcentreCModo obj: listThinkcentreC){
                    if (obj.getPAIS().equals("Chile")){
                        listThinkcentreFiltroCL.add(obj);

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
                thinkcentreCBuscarAdapter = new ThinkcentreCBuscarAdapter(thinkcentreCBuscarAdapter.context, listThinkcentreFiltroCL);
                recyclerView.setAdapter(thinkcentreCBuscarAdapter);

                Toast.makeText(this, "Chile", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnColombia:

                ArrayList<ThinkcentreCModo>listThinkcentreFiltroCO = new ArrayList<>();
                for (ThinkcentreCModo obj: listThinkcentreC){
                    if (obj.getPAIS().equals("Colombia")){
                        listThinkcentreFiltroCO.add(obj);

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
                thinkcentreCBuscarAdapter = new ThinkcentreCBuscarAdapter(thinkcentreCBuscarAdapter.context, listThinkcentreFiltroCO);
                recyclerView.setAdapter(thinkcentreCBuscarAdapter);

                Toast.makeText(this, "Colombia", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnMexico:

                ArrayList<ThinkcentreCModo>listThinkcentreFiltroMX = new ArrayList<>();
                for (ThinkcentreCModo obj: listThinkcentreC){
                    if (obj.getPAIS().equals("Mexico")){
                        listThinkcentreFiltroMX.add(obj);

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
                thinkcentreCBuscarAdapter = new ThinkcentreCBuscarAdapter(thinkcentreCBuscarAdapter.context, listThinkcentreFiltroMX);
                recyclerView.setAdapter(thinkcentreCBuscarAdapter);

                Toast.makeText(this, "Mexico", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnPeru:

                ArrayList<ThinkcentreCModo>listThinkcentreFiltroPE = new ArrayList<>();
                for (ThinkcentreCModo obj: listThinkcentreC){
                    if (obj.getPAIS().equals("Peru")){
                        listThinkcentreFiltroPE.add(obj);

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
                thinkcentreCBuscarAdapter = new ThinkcentreCBuscarAdapter(thinkcentreCBuscarAdapter.context, listThinkcentreFiltroPE);
                recyclerView.setAdapter(thinkcentreCBuscarAdapter);

                Toast.makeText(this, "Peru", Toast.LENGTH_SHORT).show();

                break;

        }
        return super.onOptionsItemSelected(item);
    }





    //BOTON BUSQUEDA PRINCIPAL CLIENTE
    private void filterList(String s) {
        ArrayList<ThinkcentreCModo>listThinkcentreCt = new ArrayList<>();
        for (ThinkcentreCModo obj: listThinkcentreC){
            if (obj.getFAMILIA().toLowerCase().contains(s.toLowerCase()) || obj.getPN().toLowerCase().contains(s.toLowerCase())){
                listThinkcentreCt.add(obj);

            }

        }
        thinkcentreCBuscarAdapter = new ThinkcentreCBuscarAdapter(thinkcentreCBuscarAdapter.context, listThinkcentreCt);
        recyclerView.setAdapter(thinkcentreCBuscarAdapter);

    }


    //FILTRO BUSCAR POR CHILE
    private void filterListCl(String cl) {
        ArrayList<ThinkcentreCModo>listThinkcentreCt = new ArrayList<>();
        for (ThinkcentreCModo obj: listThinkcentreC){
            if (obj.getPAIS().equals("Chile")){
                if (obj.getFAMILIA().toLowerCase().contains(cl.toLowerCase()) || obj.getPN().toLowerCase().contains(cl.toLowerCase())){
                    listThinkcentreCt.add(obj);
                }

            }

        }
        thinkcentreCBuscarAdapter = new ThinkcentreCBuscarAdapter(thinkcentreCBuscarAdapter.context, listThinkcentreCt);
        recyclerView.setAdapter(thinkcentreCBuscarAdapter);
    }

    //FILTRO BUSCAR POR ARGENTINA
    private void filterListAr(String ar) {
        ArrayList<ThinkcentreCModo>listThinkcentreCt = new ArrayList<>();
        for (ThinkcentreCModo obj: listThinkcentreC){
            if (obj.getPAIS().equals("Argentina")){
                if (obj.getFAMILIA().toLowerCase().contains(ar.toLowerCase()) || obj.getPN().toLowerCase().contains(ar.toLowerCase())){
                    listThinkcentreCt.add(obj);
                }

            }

        }
        thinkcentreCBuscarAdapter = new ThinkcentreCBuscarAdapter(thinkcentreCBuscarAdapter.context, listThinkcentreCt);
        recyclerView.setAdapter(thinkcentreCBuscarAdapter);

    }

    //FILTRO BUSCAR POR COLOMBIA
    private void filterListCo(String co) {
        ArrayList<ThinkcentreCModo>listThinkcentreCt = new ArrayList<>();
        for (ThinkcentreCModo obj: listThinkcentreC){
            if (obj.getPAIS().equals("Colombia")){
                if (obj.getFAMILIA().toLowerCase().contains(co.toLowerCase()) || obj.getPN().toLowerCase().contains(co.toLowerCase())){
                    listThinkcentreCt.add(obj);
                }

            }

        }
        thinkcentreCBuscarAdapter = new ThinkcentreCBuscarAdapter(thinkcentreCBuscarAdapter.context, listThinkcentreCt);
        recyclerView.setAdapter(thinkcentreCBuscarAdapter);
    }

    //FILTRO BUSCAR POR MEXICO
    private void filterListMx(String mx) {
        ArrayList<ThinkcentreCModo>listThinkcentreCt = new ArrayList<>();
        for (ThinkcentreCModo obj: listThinkcentreC){
            if (obj.getPAIS().equals("Mexico")){
                if (obj.getFAMILIA().toLowerCase().contains(mx.toLowerCase()) || obj.getPN().toLowerCase().contains(mx.toLowerCase())){
                    listThinkcentreCt.add(obj);
                }

            }

        }
        thinkcentreCBuscarAdapter = new ThinkcentreCBuscarAdapter(thinkcentreCBuscarAdapter.context, listThinkcentreCt);
        recyclerView.setAdapter(thinkcentreCBuscarAdapter);
    }

    //FILTRO BUSCAR POR PERU
    private void filterListPe(String pe) {
        ArrayList<ThinkcentreCModo>listThinkcentreCt = new ArrayList<>();
        for (ThinkcentreCModo obj: listThinkcentreC){
            if (obj.getPAIS().equals("Peru")){
                if (obj.getFAMILIA().toLowerCase().contains(pe.toLowerCase()) || obj.getPN().toLowerCase().contains(pe.toLowerCase())){
                    listThinkcentreCt.add(obj);
                }

            }

        }
        thinkcentreCBuscarAdapter = new ThinkcentreCBuscarAdapter(thinkcentreCBuscarAdapter.context, listThinkcentreCt);
        recyclerView.setAdapter(thinkcentreCBuscarAdapter);
    }







    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
        thinkcentreCAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        thinkcentreCAdapter.stopListening();
    }





}