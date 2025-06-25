package com.vaxwe.lencareapp.ui.CategoriasCliente.ThinksmartC;

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

public class ThinksmartC extends AppCompatActivity {

    private Window window;

    String Color16 = "#3E6D9C";

    RecyclerView recyclerView;
    ThinksmartCAdapter thinksmartCAdapter;
    ThinksmartCBuscarAdapter thinksmartCBuscarAdapter;
    DatabaseReference mRef;
    ArrayList<ThinksmartCModo> listThinksmartC;

    private SearchView searchViewPrimary,searchViewCl,searchViewAr,searchViewCo,searchViewMx,searchViewPe;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thinksmart_c);


        ActionBar actionBar = getSupportActionBar(); //CREAMOS ACTIONBAR
        assert actionBar != null;                    //AFIRMAMOS QUE EL ACTIONBAR NO SEA NULO
        //actionBar.setTitle("Accesorios");         //LE ASIGNAMOS UN TITULO
        actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Thinksmart</font>"));

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.window = getWindow();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary1)));
        window.setStatusBarColor(Color.parseColor(Color16));

        searchViewPrimary = findViewById(R.id.BtnBuscarTSC);
        searchViewPrimary.clearFocus();

        searchViewCl = findViewById(R.id.BtnBuscarTSC);
        searchViewCl.clearFocus();

        searchViewAr = findViewById(R.id.BtnBuscarTSC);
        searchViewAr.clearFocus();

        searchViewCo = findViewById(R.id.BtnBuscarTSC);
        searchViewCo.clearFocus();

        searchViewMx = findViewById(R.id.BtnBuscarTSC);
        searchViewMx.clearFocus();

        searchViewPe = findViewById(R.id.BtnBuscarTSC);
        searchViewPe.clearFocus();

        recyclerView = (RecyclerView) findViewById(R.id.ViewThinksmartC);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRef = FirebaseDatabase.getInstance().getReference("THINKSMART");
        listThinksmartC = new ArrayList<>();
        thinksmartCBuscarAdapter = new ThinksmartCBuscarAdapter(this,listThinksmartC);
        recyclerView.setAdapter(thinksmartCBuscarAdapter);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                        ThinksmartCModo thinksmartCModo = snapshot.getValue(ThinksmartCModo.class);
                        listThinksmartC.add(thinksmartCModo);
                    }

                }
                thinksmartCAdapter.notifyDataSetChanged();

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



        FirebaseRecyclerOptions<ThinksmartCModo> options = new FirebaseRecyclerOptions.Builder<ThinksmartCModo>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("THINKSMART"),ThinksmartCModo.class).build();


        thinksmartCAdapter = new ThinksmartCAdapter(options);
        recyclerView.setAdapter(thinksmartCAdapter);
        thinksmartCAdapter.notifyDataSetChanged();




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

                ArrayList<ThinksmartCModo>listThinksmartFiltroAR = new ArrayList<>();
                for (ThinksmartCModo obj: listThinksmartC){
                    if (obj.getPAIS().equals("Argentina")){
                        listThinksmartFiltroAR.add(obj);

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
                thinksmartCBuscarAdapter = new ThinksmartCBuscarAdapter(thinksmartCBuscarAdapter.context, listThinksmartFiltroAR);
                recyclerView.setAdapter(thinksmartCBuscarAdapter);

                Toast.makeText(this, "Argentina", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnChile:

                ArrayList<ThinksmartCModo>listThinksmartFiltroCL = new ArrayList<>();
                for (ThinksmartCModo obj: listThinksmartC){
                    if (obj.getPAIS().equals("Chile")){
                        listThinksmartFiltroCL.add(obj);

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
                thinksmartCBuscarAdapter = new ThinksmartCBuscarAdapter(thinksmartCBuscarAdapter.context, listThinksmartFiltroCL);
                recyclerView.setAdapter(thinksmartCBuscarAdapter);

                Toast.makeText(this, "Chile", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnColombia:

                ArrayList<ThinksmartCModo>listThinksmartFiltroCO = new ArrayList<>();
                for (ThinksmartCModo obj: listThinksmartC){
                    if (obj.getPAIS().equals("Colombia")){
                        listThinksmartFiltroCO.add(obj);

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
                thinksmartCBuscarAdapter = new ThinksmartCBuscarAdapter(thinksmartCBuscarAdapter.context, listThinksmartFiltroCO);
                recyclerView.setAdapter(thinksmartCBuscarAdapter);

                Toast.makeText(this, "Colombia", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnMexico:

                ArrayList<ThinksmartCModo>listThinksmartFiltroMX = new ArrayList<>();
                for (ThinksmartCModo obj: listThinksmartC){
                    if (obj.getPAIS().equals("Mexico")){
                        listThinksmartFiltroMX.add(obj);

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
                thinksmartCBuscarAdapter = new ThinksmartCBuscarAdapter(thinksmartCBuscarAdapter.context, listThinksmartFiltroMX);
                recyclerView.setAdapter(thinksmartCBuscarAdapter);

                Toast.makeText(this, "Mexico", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnPeru:

                ArrayList<ThinksmartCModo>listThinksmartFiltroPE = new ArrayList<>();
                for (ThinksmartCModo obj: listThinksmartC){
                    if (obj.getPAIS().equals("Peru")){
                        listThinksmartFiltroPE.add(obj);

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
                thinksmartCBuscarAdapter = new ThinksmartCBuscarAdapter(thinksmartCBuscarAdapter.context, listThinksmartFiltroPE);
                recyclerView.setAdapter(thinksmartCBuscarAdapter);

                Toast.makeText(this, "Peru", Toast.LENGTH_SHORT).show();

                break;

        }
        return super.onOptionsItemSelected(item);
    }





    //BOTON BUSQUEDA PRINCIPAL CLIENTE
    private void filterList(String s) {
        ArrayList<ThinksmartCModo>listThinksmartCt = new ArrayList<>();
        for (ThinksmartCModo obj: listThinksmartC){
            if (obj.getFAMILIA().toLowerCase().contains(s.toLowerCase()) || obj.getPN().toLowerCase().contains(s.toLowerCase())){
                listThinksmartCt.add(obj);

            }

        }
        thinksmartCBuscarAdapter = new ThinksmartCBuscarAdapter(thinksmartCBuscarAdapter.context, listThinksmartCt);
        recyclerView.setAdapter(thinksmartCBuscarAdapter);

    }


    //FILTRO BUSCAR POR CHILE
    private void filterListCl(String cl) {
        ArrayList<ThinksmartCModo>listThinksmartCt = new ArrayList<>();
        for (ThinksmartCModo obj: listThinksmartC){
            if (obj.getPAIS().equals("Chile")){
                if (obj.getFAMILIA().toLowerCase().contains(cl.toLowerCase()) || obj.getPN().toLowerCase().contains(cl.toLowerCase())){
                    listThinksmartCt.add(obj);
                }

            }

        }
        thinksmartCBuscarAdapter = new ThinksmartCBuscarAdapter(thinksmartCBuscarAdapter.context, listThinksmartCt);
        recyclerView.setAdapter(thinksmartCBuscarAdapter);
    }

    //FILTRO BUSCAR POR ARGENTINA
    private void filterListAr(String ar) {
        ArrayList<ThinksmartCModo>listThinksmartCt = new ArrayList<>();
        for (ThinksmartCModo obj: listThinksmartC){
            if (obj.getPAIS().equals("Argentina")){
                if (obj.getFAMILIA().toLowerCase().contains(ar.toLowerCase()) || obj.getPN().toLowerCase().contains(ar.toLowerCase())){
                    listThinksmartCt.add(obj);
                }

            }

        }
        thinksmartCBuscarAdapter = new ThinksmartCBuscarAdapter(thinksmartCBuscarAdapter.context, listThinksmartCt);
        recyclerView.setAdapter(thinksmartCBuscarAdapter);

    }

    //FILTRO BUSCAR POR COLOMBIA
    private void filterListCo(String co) {
        ArrayList<ThinksmartCModo>listThinksmartCt = new ArrayList<>();
        for (ThinksmartCModo obj: listThinksmartC){
            if (obj.getPAIS().equals("Colombia")){
                if (obj.getFAMILIA().toLowerCase().contains(co.toLowerCase()) || obj.getPN().toLowerCase().contains(co.toLowerCase())){
                    listThinksmartCt.add(obj);
                }

            }

        }
        thinksmartCBuscarAdapter = new ThinksmartCBuscarAdapter(thinksmartCBuscarAdapter.context, listThinksmartCt);
        recyclerView.setAdapter(thinksmartCBuscarAdapter);
    }

    //FILTRO BUSCAR POR MEXICO
    private void filterListMx(String mx) {
        ArrayList<ThinksmartCModo>listThinksmartCt = new ArrayList<>();
        for (ThinksmartCModo obj: listThinksmartC){
            if (obj.getPAIS().equals("Mexico")){
                if (obj.getFAMILIA().toLowerCase().contains(mx.toLowerCase()) || obj.getPN().toLowerCase().contains(mx.toLowerCase())){
                    listThinksmartCt.add(obj);
                }

            }

        }
        thinksmartCBuscarAdapter = new ThinksmartCBuscarAdapter(thinksmartCBuscarAdapter.context, listThinksmartCt);
        recyclerView.setAdapter(thinksmartCBuscarAdapter);
    }

    //FILTRO BUSCAR POR PERU
    private void filterListPe(String pe) {
        ArrayList<ThinksmartCModo>listThinksmartCt = new ArrayList<>();
        for (ThinksmartCModo obj: listThinksmartC){
            if (obj.getPAIS().equals("Peru")){
                if (obj.getFAMILIA().toLowerCase().contains(pe.toLowerCase()) || obj.getPN().toLowerCase().contains(pe.toLowerCase())){
                    listThinksmartCt.add(obj);
                }

            }

        }
        thinksmartCBuscarAdapter = new ThinksmartCBuscarAdapter(thinksmartCBuscarAdapter.context, listThinksmartCt);
        recyclerView.setAdapter(thinksmartCBuscarAdapter);
    }







    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
        thinksmartCAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        thinksmartCAdapter.stopListening();
    }



}