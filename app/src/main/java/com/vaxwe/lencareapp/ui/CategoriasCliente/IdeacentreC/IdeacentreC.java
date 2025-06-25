package com.vaxwe.lencareapp.ui.CategoriasCliente.IdeacentreC;

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

public class IdeacentreC extends AppCompatActivity {

    private Window window;

    String Color16 = "#3E6D9C";

    RecyclerView recyclerView;
    IdeacentreCAdapter ideacentreCAdapter;
    DatabaseReference mRef;

    ArrayList<IdeacentreCModo> listIdeacentreC;
    IdeacentreCBuscarAdapter ideacentreCBuscarAdapter;

    private SearchView searchViewPrimary,searchViewCl,searchViewAr,searchViewCo,searchViewMx,searchViewPe;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideacentre_c);



        ActionBar actionBar = getSupportActionBar(); //CREAMOS ACTIONBAR
        assert actionBar != null;                    //AFIRMAMOS QUE EL ACTIONBAR NO SEA NULO
        //actionBar.setTitle("Accesorios");         //LE ASIGNAMOS UN TITULO
        actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Ideacentre</font>"));

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.window = getWindow();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary1)));
        window.setStatusBarColor(Color.parseColor(Color16));

        searchViewPrimary = findViewById(R.id.BtnBuscarIC);
        searchViewPrimary.clearFocus();

        searchViewCl = findViewById(R.id.BtnBuscarIC);
        searchViewCl.clearFocus();

        searchViewAr = findViewById(R.id.BtnBuscarIC);
        searchViewAr.clearFocus();

        searchViewCo = findViewById(R.id.BtnBuscarIC);
        searchViewCo.clearFocus();

        searchViewMx = findViewById(R.id.BtnBuscarIC);
        searchViewMx.clearFocus();

        searchViewPe = findViewById(R.id.BtnBuscarIC);
        searchViewPe.clearFocus();


        recyclerView = (RecyclerView) findViewById(R.id.ViewIdeacentreC);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRef = FirebaseDatabase.getInstance().getReference("IDEACENTRE");

        listIdeacentreC = new ArrayList<>();
        ideacentreCBuscarAdapter = new IdeacentreCBuscarAdapter(this,listIdeacentreC);
        recyclerView.setAdapter(ideacentreCBuscarAdapter);
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                        IdeacentreCModo ideacentreCModo = snapshot.getValue(IdeacentreCModo.class);
                        listIdeacentreC.add(ideacentreCModo);
                    }

                }
                ideacentreCAdapter.notifyDataSetChanged();

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



        FirebaseRecyclerOptions<IdeacentreCModo> options = new FirebaseRecyclerOptions.Builder<IdeacentreCModo>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("IDEACENTRE"),IdeacentreCModo.class).build();


        ideacentreCAdapter = new IdeacentreCAdapter(options);
        recyclerView.setAdapter(ideacentreCAdapter);
        ideacentreCAdapter.notifyDataSetChanged();



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

                ArrayList<IdeacentreCModo>listIdeacentreFiltroAR = new ArrayList<>();
                for (IdeacentreCModo obj: listIdeacentreC){
                    if (obj.getPAIS().equals("Argentina")){
                        listIdeacentreFiltroAR.add(obj);

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
                ideacentreCBuscarAdapter = new IdeacentreCBuscarAdapter(ideacentreCBuscarAdapter.context, listIdeacentreFiltroAR);
                recyclerView.setAdapter(ideacentreCBuscarAdapter);

                Toast.makeText(this, "Argentina", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnChile:

                ArrayList<IdeacentreCModo>listIdeacentreFiltroCL = new ArrayList<>();
                for (IdeacentreCModo obj: listIdeacentreC){
                    if (obj.getPAIS().equals("Chile")){
                        listIdeacentreFiltroCL.add(obj);

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
                ideacentreCBuscarAdapter = new IdeacentreCBuscarAdapter(ideacentreCBuscarAdapter.context, listIdeacentreFiltroCL);
                recyclerView.setAdapter(ideacentreCBuscarAdapter);

                Toast.makeText(this, "Chile", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnColombia:

                ArrayList<IdeacentreCModo>listIdeacentreFiltroCO = new ArrayList<>();
                for (IdeacentreCModo obj: listIdeacentreC){
                    if (obj.getPAIS().equals("Colombia")){
                        listIdeacentreFiltroCO.add(obj);

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
                ideacentreCBuscarAdapter = new IdeacentreCBuscarAdapter(ideacentreCBuscarAdapter.context, listIdeacentreFiltroCO);
                recyclerView.setAdapter(ideacentreCBuscarAdapter);

                Toast.makeText(this, "Colombia", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnMexico:

                ArrayList<IdeacentreCModo>listIdeacentreFiltroMX = new ArrayList<>();
                for (IdeacentreCModo obj: listIdeacentreC){
                    if (obj.getPAIS().equals("Mexico")){
                        listIdeacentreFiltroMX.add(obj);

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
                ideacentreCBuscarAdapter = new IdeacentreCBuscarAdapter(ideacentreCBuscarAdapter.context, listIdeacentreFiltroMX);
                recyclerView.setAdapter(ideacentreCBuscarAdapter);

                Toast.makeText(this, "Mexico", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnPeru:

                ArrayList<IdeacentreCModo>listIdeacentreFiltroPE = new ArrayList<>();
                for (IdeacentreCModo obj: listIdeacentreC){
                    if (obj.getPAIS().equals("Peru")){
                        listIdeacentreFiltroPE.add(obj);

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
                ideacentreCBuscarAdapter = new IdeacentreCBuscarAdapter(ideacentreCBuscarAdapter.context, listIdeacentreFiltroPE);
                recyclerView.setAdapter(ideacentreCBuscarAdapter);

                Toast.makeText(this, "Peru", Toast.LENGTH_SHORT).show();

                break;

        }
        return super.onOptionsItemSelected(item);
    }




    //BOTON BUSQUEDA PRINCIPAL CLIENTE
    private void filterList(String s) {
        ArrayList<IdeacentreCModo>listIdeacentreCt = new ArrayList<>();
        for (IdeacentreCModo obj: listIdeacentreC){
            if (obj.getFAMILIA().toLowerCase().contains(s.toLowerCase()) || obj.getPN().toLowerCase().contains(s.toLowerCase())){
                listIdeacentreCt.add(obj);
            }

        }
        ideacentreCBuscarAdapter = new IdeacentreCBuscarAdapter(ideacentreCBuscarAdapter.context, listIdeacentreCt);
        recyclerView.setAdapter(ideacentreCBuscarAdapter);

    }


    //FILTRO BUSCAR POR CHILE
    private void filterListCl(String cl) {
        ArrayList<IdeacentreCModo>listIdeacentreCt = new ArrayList<>();
        for (IdeacentreCModo obj: listIdeacentreC){
            if (obj.getPAIS().equals("Chile")){
                if (obj.getFAMILIA().toLowerCase().contains(cl.toLowerCase()) || obj.getPN().toLowerCase().contains(cl.toLowerCase())){
                    listIdeacentreCt.add(obj);
                }

            }

        }
        ideacentreCBuscarAdapter = new IdeacentreCBuscarAdapter(ideacentreCBuscarAdapter.context, listIdeacentreCt);
        recyclerView.setAdapter(ideacentreCBuscarAdapter);
    }

    //FILTRO BUSCAR POR ARGENTINA
    private void filterListAr(String ar) {
        ArrayList<IdeacentreCModo>listIdeacentreCt = new ArrayList<>();
        for (IdeacentreCModo obj: listIdeacentreC){
            if (obj.getPAIS().equals("Argentina")){
                if (obj.getFAMILIA().toLowerCase().contains(ar.toLowerCase()) || obj.getPN().toLowerCase().contains(ar.toLowerCase())){
                    listIdeacentreCt.add(obj);
                }

            }

        }
        ideacentreCBuscarAdapter = new IdeacentreCBuscarAdapter(ideacentreCBuscarAdapter.context, listIdeacentreCt);
        recyclerView.setAdapter(ideacentreCBuscarAdapter);

    }

    //FILTRO BUSCAR POR COLOMBIA
    private void filterListCo(String co) {
        ArrayList<IdeacentreCModo>listIdeacentreCt = new ArrayList<>();
        for (IdeacentreCModo obj: listIdeacentreC){
            if (obj.getPAIS().equals("Colombia")){
                if (obj.getFAMILIA().toLowerCase().contains(co.toLowerCase()) || obj.getPN().toLowerCase().contains(co.toLowerCase())){
                    listIdeacentreCt.add(obj);
                }

            }

        }
        ideacentreCBuscarAdapter = new IdeacentreCBuscarAdapter(ideacentreCBuscarAdapter.context, listIdeacentreCt);
        recyclerView.setAdapter(ideacentreCBuscarAdapter);
    }

    //FILTRO BUSCAR POR MEXICO
    private void filterListMx(String mx) {
        ArrayList<IdeacentreCModo>listIdeacentreCt = new ArrayList<>();
        for (IdeacentreCModo obj: listIdeacentreC){
            if (obj.getPAIS().equals("Mexico")){
                if (obj.getFAMILIA().toLowerCase().contains(mx.toLowerCase()) || obj.getPN().toLowerCase().contains(mx.toLowerCase())){
                    listIdeacentreCt.add(obj);
                }

            }

        }
        ideacentreCBuscarAdapter = new IdeacentreCBuscarAdapter(ideacentreCBuscarAdapter.context, listIdeacentreCt);
        recyclerView.setAdapter(ideacentreCBuscarAdapter);
    }

    //FILTRO BUSCAR POR PERU
    private void filterListPe(String pe) {
        ArrayList<IdeacentreCModo>listIdeacentreCt = new ArrayList<>();
        for (IdeacentreCModo obj: listIdeacentreC){
            if (obj.getPAIS().equals("Peru")){
                if (obj.getFAMILIA().toLowerCase().contains(pe.toLowerCase()) || obj.getPN().toLowerCase().contains(pe.toLowerCase())){
                    listIdeacentreCt.add(obj);
                }

            }

        }
        ideacentreCBuscarAdapter = new IdeacentreCBuscarAdapter(ideacentreCBuscarAdapter.context, listIdeacentreCt);
        recyclerView.setAdapter(ideacentreCBuscarAdapter);
    }






    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
        ideacentreCAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        ideacentreCAdapter.stopListening();
    }




}