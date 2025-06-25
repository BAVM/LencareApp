package com.vaxwe.lencareapp.ui.CategoriasCliente.ThinkpadC;

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

public class ThinkpadC extends AppCompatActivity {

    private Window window;

    String Color16 = "#3E6D9C";

    RecyclerView recyclerView;
    ThinkpadCAdapter thinkpadCAdapter;
    ThinkpadCBuscarAdapter thinkpadCBuscarAdapter;
    DatabaseReference mRef;
    ArrayList<ThinkpadCModo> listThinkpadC;

    private SearchView searchViewPrimary,searchViewCl,searchViewAr,searchViewCo,searchViewMx,searchViewPe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thinkpad_c);

        ActionBar actionBar = getSupportActionBar(); //CREAMOS ACTIONBAR
        assert actionBar != null;                    //AFIRMAMOS QUE EL ACTIONBAR NO SEA NULO
        //actionBar.setTitle("Accesorios");         //LE ASIGNAMOS UN TITULO
        actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Thinkpad</font>"));

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.window = getWindow();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary1)));
        window.setStatusBarColor(Color.parseColor(Color16));

        searchViewPrimary = findViewById(R.id.BtnBuscarTPC);
        searchViewPrimary.clearFocus();

        searchViewCl = findViewById(R.id.BtnBuscarTPC);
        searchViewCl.clearFocus();

        searchViewAr = findViewById(R.id.BtnBuscarTPC);
        searchViewAr.clearFocus();

        searchViewCo = findViewById(R.id.BtnBuscarTPC);
        searchViewCo.clearFocus();

        searchViewMx = findViewById(R.id.BtnBuscarTPC);
        searchViewMx.clearFocus();

        searchViewPe = findViewById(R.id.BtnBuscarTPC);
        searchViewPe.clearFocus();

        recyclerView = (RecyclerView) findViewById(R.id.ViewThinkpadC);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRef = FirebaseDatabase.getInstance().getReference("THINKPAD");
        listThinkpadC = new ArrayList<>();
        thinkpadCBuscarAdapter = new ThinkpadCBuscarAdapter(this,listThinkpadC);
        recyclerView.setAdapter(thinkpadCBuscarAdapter);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                        ThinkpadCModo thinkpadCModo = snapshot.getValue(ThinkpadCModo.class);
                        listThinkpadC.add(thinkpadCModo);
                    }

                }
                thinkpadCAdapter.notifyDataSetChanged();

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



        FirebaseRecyclerOptions<ThinkpadCModo> options = new FirebaseRecyclerOptions.Builder<ThinkpadCModo>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("THINKPAD"),ThinkpadCModo.class).build();


        thinkpadCAdapter = new ThinkpadCAdapter(options);
        recyclerView.setAdapter(thinkpadCAdapter);
        thinkpadCAdapter.notifyDataSetChanged();



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

                ArrayList<ThinkpadCModo>listThinkpadFiltroAR = new ArrayList<>();
                for (ThinkpadCModo obj: listThinkpadC){
                    if (obj.getPAIS().equals("Argentina")){
                        listThinkpadFiltroAR.add(obj);

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
                thinkpadCBuscarAdapter = new ThinkpadCBuscarAdapter(thinkpadCBuscarAdapter.context, listThinkpadFiltroAR);
                recyclerView.setAdapter(thinkpadCBuscarAdapter);

                Toast.makeText(this, "Argentina", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnChile:

                ArrayList<ThinkpadCModo>listThinkpadFiltroCL = new ArrayList<>();
                for (ThinkpadCModo obj: listThinkpadC){
                    if (obj.getPAIS().equals("Chile")){
                        listThinkpadFiltroCL.add(obj);

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
                thinkpadCBuscarAdapter = new ThinkpadCBuscarAdapter(thinkpadCBuscarAdapter.context, listThinkpadFiltroCL);
                recyclerView.setAdapter(thinkpadCBuscarAdapter);

                Toast.makeText(this, "Chile", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnColombia:

                ArrayList<ThinkpadCModo>listThinkpadFiltroCO = new ArrayList<>();
                for (ThinkpadCModo obj: listThinkpadC){
                    if (obj.getPAIS().equals("Colombia")){
                        listThinkpadFiltroCO.add(obj);

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
                thinkpadCBuscarAdapter = new ThinkpadCBuscarAdapter(thinkpadCBuscarAdapter.context, listThinkpadFiltroCO);
                recyclerView.setAdapter(thinkpadCBuscarAdapter);

                Toast.makeText(this, "Colombia", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnMexico:

                ArrayList<ThinkpadCModo>listThinkpadFiltroMX = new ArrayList<>();
                for (ThinkpadCModo obj: listThinkpadC){
                    if (obj.getPAIS().equals("Mexico")){
                        listThinkpadFiltroMX.add(obj);

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
                thinkpadCBuscarAdapter = new ThinkpadCBuscarAdapter(thinkpadCBuscarAdapter.context, listThinkpadFiltroMX);
                recyclerView.setAdapter(thinkpadCBuscarAdapter);

                Toast.makeText(this, "Mexico", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnPeru:

                ArrayList<ThinkpadCModo>listThinkpadFiltroPE = new ArrayList<>();
                for (ThinkpadCModo obj: listThinkpadC){
                    if (obj.getPAIS().equals("Peru")){
                        listThinkpadFiltroPE.add(obj);

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
                thinkpadCBuscarAdapter = new ThinkpadCBuscarAdapter(thinkpadCBuscarAdapter.context, listThinkpadFiltroPE);
                recyclerView.setAdapter(thinkpadCBuscarAdapter);

                Toast.makeText(this, "Peru", Toast.LENGTH_SHORT).show();

                break;

        }
        return super.onOptionsItemSelected(item);
    }




    //BOTON BUSQUEDA PRINCIPAL CLIENTE
    private void filterList(String s) {
        ArrayList<ThinkpadCModo>listThinkpadCt = new ArrayList<>();
        for (ThinkpadCModo obj: listThinkpadC){
            if (obj.getFAMILIA().toLowerCase().contains(s.toLowerCase()) || obj.getPN().toLowerCase().contains(s.toLowerCase())){
                listThinkpadCt.add(obj);

            }

        }
        thinkpadCBuscarAdapter = new ThinkpadCBuscarAdapter(thinkpadCBuscarAdapter.context, listThinkpadCt);
        recyclerView.setAdapter(thinkpadCBuscarAdapter);

    }


    //FILTRO BUSCAR POR CHILE
    private void filterListCl(String cl) {
        ArrayList<ThinkpadCModo>listThinkpadCt = new ArrayList<>();
        for (ThinkpadCModo obj: listThinkpadC){
            if (obj.getPAIS().equals("Chile")){
                if (obj.getFAMILIA().toLowerCase().contains(cl.toLowerCase()) || obj.getPN().toLowerCase().contains(cl.toLowerCase())){
                    listThinkpadCt.add(obj);
                }

            }

        }
        thinkpadCBuscarAdapter = new ThinkpadCBuscarAdapter(thinkpadCBuscarAdapter.context, listThinkpadCt);
        recyclerView.setAdapter(thinkpadCBuscarAdapter);
    }

    //FILTRO BUSCAR POR ARGENTINA
    private void filterListAr(String ar) {
        ArrayList<ThinkpadCModo>listThinkpadCt = new ArrayList<>();
        for (ThinkpadCModo obj: listThinkpadC){
            if (obj.getPAIS().equals("Argentina")){
                if (obj.getFAMILIA().toLowerCase().contains(ar.toLowerCase()) || obj.getPN().toLowerCase().contains(ar.toLowerCase())){
                    listThinkpadCt.add(obj);
                }

            }

        }
        thinkpadCBuscarAdapter = new ThinkpadCBuscarAdapter(thinkpadCBuscarAdapter.context, listThinkpadCt);
        recyclerView.setAdapter(thinkpadCBuscarAdapter);

    }

    //FILTRO BUSCAR POR COLOMBIA
    private void filterListCo(String co) {
        ArrayList<ThinkpadCModo>listThinkcentreCt = new ArrayList<>();
        for (ThinkpadCModo obj: listThinkpadC){
            if (obj.getPAIS().equals("Colombia")){
                if (obj.getFAMILIA().toLowerCase().contains(co.toLowerCase()) || obj.getPN().toLowerCase().contains(co.toLowerCase())){
                    listThinkcentreCt.add(obj);
                }

            }

        }
        thinkpadCBuscarAdapter = new ThinkpadCBuscarAdapter(thinkpadCBuscarAdapter.context, listThinkcentreCt);
        recyclerView.setAdapter(thinkpadCBuscarAdapter);
    }

    //FILTRO BUSCAR POR MEXICO
    private void filterListMx(String mx) {
        ArrayList<ThinkpadCModo>listThinkcentreCt = new ArrayList<>();
        for (ThinkpadCModo obj: listThinkpadC){
            if (obj.getPAIS().equals("Mexico")){
                if (obj.getFAMILIA().toLowerCase().contains(mx.toLowerCase()) || obj.getPN().toLowerCase().contains(mx.toLowerCase())){
                    listThinkcentreCt.add(obj);
                }

            }

        }
        thinkpadCBuscarAdapter = new ThinkpadCBuscarAdapter(thinkpadCBuscarAdapter.context, listThinkcentreCt);
        recyclerView.setAdapter(thinkpadCBuscarAdapter);
    }

    //FILTRO BUSCAR POR PERU
    private void filterListPe(String pe) {
        ArrayList<ThinkpadCModo>listThinkcentreCt = new ArrayList<>();
        for (ThinkpadCModo obj: listThinkpadC){
            if (obj.getPAIS().equals("Peru")){
                if (obj.getFAMILIA().toLowerCase().contains(pe.toLowerCase()) || obj.getPN().toLowerCase().contains(pe.toLowerCase())){
                    listThinkcentreCt.add(obj);
                }

            }

        }
        thinkpadCBuscarAdapter = new ThinkpadCBuscarAdapter(thinkpadCBuscarAdapter.context, listThinkcentreCt);
        recyclerView.setAdapter(thinkpadCBuscarAdapter);
    }







    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
        thinkpadCAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        thinkpadCAdapter.stopListening();
    }


}