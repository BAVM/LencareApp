package com.vaxwe.lencareapp.ui.CategoriasCliente.ThinkbookC;

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

public class ThinkbookC extends AppCompatActivity {

    private Window window;

    String Color16 = "#3E6D9C";

    RecyclerView recyclerView;
    ThinkbookCAdapter thinkbookCAdapter;
    ThinkbookCBuscarAdapter thinkbookCBuscarAdapter;
    DatabaseReference mRef;
    ArrayList<ThinkbookCModo> listThinkbookC;

    private SearchView searchViewPrimary,searchViewCl,searchViewAr,searchViewCo,searchViewMx,searchViewPe;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thinkbook_c);


        ActionBar actionBar = getSupportActionBar(); //CREAMOS ACTIONBAR
        assert actionBar != null;                    //AFIRMAMOS QUE EL ACTIONBAR NO SEA NULO
        //actionBar.setTitle("Accesorios");         //LE ASIGNAMOS UN TITULO
        actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Thinkbook</font>"));

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.window = getWindow();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary1)));
        window.setStatusBarColor(Color.parseColor(Color16));


        searchViewPrimary = findViewById(R.id.BtnBuscarTBC);
        searchViewPrimary.clearFocus();

        searchViewCl = findViewById(R.id.BtnBuscarTBC);
        searchViewCl.clearFocus();

        searchViewAr = findViewById(R.id.BtnBuscarTBC);
        searchViewAr.clearFocus();

        searchViewCo = findViewById(R.id.BtnBuscarTBC);
        searchViewCo.clearFocus();

        searchViewMx = findViewById(R.id.BtnBuscarTBC);
        searchViewMx.clearFocus();

        searchViewPe = findViewById(R.id.BtnBuscarTBC);
        searchViewPe.clearFocus();

        recyclerView = (RecyclerView) findViewById(R.id.ViewThinkbookC);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRef = FirebaseDatabase.getInstance().getReference("THINKBOOK");
        listThinkbookC = new ArrayList<>();
        thinkbookCBuscarAdapter = new ThinkbookCBuscarAdapter(this,listThinkbookC);
        recyclerView.setAdapter(thinkbookCBuscarAdapter);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                        ThinkbookCModo thinkbookCModo = snapshot.getValue(ThinkbookCModo.class);
                        listThinkbookC.add(thinkbookCModo);
                    }

                }
                thinkbookCAdapter.notifyDataSetChanged();

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



        FirebaseRecyclerOptions<ThinkbookCModo> options = new FirebaseRecyclerOptions.Builder<ThinkbookCModo>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("THINKBOOK"),ThinkbookCModo.class).build();


        thinkbookCAdapter = new ThinkbookCAdapter(options);
        recyclerView.setAdapter(thinkbookCAdapter);
        thinkbookCAdapter.notifyDataSetChanged();


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

                ArrayList<ThinkbookCModo>listThinkbookFiltroAR = new ArrayList<>();
                for (ThinkbookCModo obj: listThinkbookC){
                    if (obj.getPAIS().equals("Argentina")){
                        listThinkbookFiltroAR.add(obj);

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
                thinkbookCBuscarAdapter = new ThinkbookCBuscarAdapter(thinkbookCBuscarAdapter.context, listThinkbookFiltroAR);
                recyclerView.setAdapter(thinkbookCBuscarAdapter);

                Toast.makeText(this, "Argentina", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnChile:

                ArrayList<ThinkbookCModo>listThinkbookFiltroCL = new ArrayList<>();
                for (ThinkbookCModo obj: listThinkbookC){
                    if (obj.getPAIS().equals("Chile")){
                        listThinkbookFiltroCL.add(obj);

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
                thinkbookCBuscarAdapter = new ThinkbookCBuscarAdapter(thinkbookCBuscarAdapter.context, listThinkbookFiltroCL);
                recyclerView.setAdapter(thinkbookCBuscarAdapter);

                Toast.makeText(this, "Chile", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnColombia:

                ArrayList<ThinkbookCModo>listThinkbookFiltroCO = new ArrayList<>();
                for (ThinkbookCModo obj: listThinkbookC){
                    if (obj.getPAIS().equals("Colombia")){
                        listThinkbookFiltroCO.add(obj);

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
                thinkbookCBuscarAdapter = new ThinkbookCBuscarAdapter(thinkbookCBuscarAdapter.context, listThinkbookFiltroCO);
                recyclerView.setAdapter(thinkbookCBuscarAdapter);

                Toast.makeText(this, "Colombia", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnMexico:

                ArrayList<ThinkbookCModo>listThinkbookFiltroMX = new ArrayList<>();
                for (ThinkbookCModo obj: listThinkbookC){
                    if (obj.getPAIS().equals("Mexico")){
                        listThinkbookFiltroMX.add(obj);

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
                thinkbookCBuscarAdapter = new ThinkbookCBuscarAdapter(thinkbookCBuscarAdapter.context, listThinkbookFiltroMX);
                recyclerView.setAdapter(thinkbookCBuscarAdapter);

                Toast.makeText(this, "Mexico", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnPeru:

                ArrayList<ThinkbookCModo>listThinkbookFiltroPE = new ArrayList<>();
                for (ThinkbookCModo obj: listThinkbookC){
                    if (obj.getPAIS().equals("Peru")){
                        listThinkbookFiltroPE.add(obj);

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
                thinkbookCBuscarAdapter = new ThinkbookCBuscarAdapter(thinkbookCBuscarAdapter.context, listThinkbookFiltroPE);
                recyclerView.setAdapter(thinkbookCBuscarAdapter);

                Toast.makeText(this, "Peru", Toast.LENGTH_SHORT).show();

                break;

        }
        return super.onOptionsItemSelected(item);
    }



    //BOTON BUSQUEDA PRINCIPAL CLIENTE
    private void filterList(String s) {
        ArrayList<ThinkbookCModo>listThinkbookCt = new ArrayList<>();
        for (ThinkbookCModo obj: listThinkbookC){
            if (obj.getFAMILIA().toLowerCase().contains(s.toLowerCase()) || obj.getPN().toLowerCase().contains(s.toLowerCase())){
                listThinkbookCt.add(obj);

            }

        }
        thinkbookCBuscarAdapter = new ThinkbookCBuscarAdapter(thinkbookCBuscarAdapter.context, listThinkbookCt);
        recyclerView.setAdapter(thinkbookCBuscarAdapter);

    }


    //FILTRO BUSCAR POR CHILE
    private void filterListCl(String cl) {
        ArrayList<ThinkbookCModo>listThinkbookCt = new ArrayList<>();
        for (ThinkbookCModo obj: listThinkbookC){
            if (obj.getPAIS().equals("Chile")){
                if (obj.getFAMILIA().toLowerCase().contains(cl.toLowerCase()) || obj.getPN().toLowerCase().contains(cl.toLowerCase())){
                    listThinkbookCt.add(obj);
                }

            }

        }
        thinkbookCBuscarAdapter = new ThinkbookCBuscarAdapter(thinkbookCBuscarAdapter.context, listThinkbookCt);
        recyclerView.setAdapter(thinkbookCBuscarAdapter);
    }

    //FILTRO BUSCAR POR ARGENTINA
    private void filterListAr(String ar) {
        ArrayList<ThinkbookCModo>listThinkbookCt = new ArrayList<>();
        for (ThinkbookCModo obj: listThinkbookC){
            if (obj.getPAIS().equals("Argentina")){
                if (obj.getFAMILIA().toLowerCase().contains(ar.toLowerCase()) || obj.getPN().toLowerCase().contains(ar.toLowerCase())){
                    listThinkbookCt.add(obj);
                }

            }

        }
        thinkbookCBuscarAdapter = new ThinkbookCBuscarAdapter(thinkbookCBuscarAdapter.context, listThinkbookCt);
        recyclerView.setAdapter(thinkbookCBuscarAdapter);

    }

    //FILTRO BUSCAR POR COLOMBIA
    private void filterListCo(String co) {
        ArrayList<ThinkbookCModo>listThinkbookCt = new ArrayList<>();
        for (ThinkbookCModo obj: listThinkbookC){
            if (obj.getPAIS().equals("Colombia")){
                if (obj.getFAMILIA().toLowerCase().contains(co.toLowerCase()) || obj.getPN().toLowerCase().contains(co.toLowerCase())){
                    listThinkbookCt.add(obj);
                }

            }

        }
        thinkbookCBuscarAdapter = new ThinkbookCBuscarAdapter(thinkbookCBuscarAdapter.context, listThinkbookCt);
        recyclerView.setAdapter(thinkbookCBuscarAdapter);
    }

    //FILTRO BUSCAR POR MEXICO
    private void filterListMx(String mx) {
        ArrayList<ThinkbookCModo>listThinkbookCt = new ArrayList<>();
        for (ThinkbookCModo obj: listThinkbookC){
            if (obj.getPAIS().equals("Mexico")){
                if (obj.getFAMILIA().toLowerCase().contains(mx.toLowerCase()) || obj.getPN().toLowerCase().contains(mx.toLowerCase())){
                    listThinkbookCt.add(obj);
                }

            }

        }
        thinkbookCBuscarAdapter = new ThinkbookCBuscarAdapter(thinkbookCBuscarAdapter.context, listThinkbookCt);
        recyclerView.setAdapter(thinkbookCBuscarAdapter);
    }

    //FILTRO BUSCAR POR PERU
    private void filterListPe(String pe) {
        ArrayList<ThinkbookCModo>listThinkbookCt = new ArrayList<>();
        for (ThinkbookCModo obj: listThinkbookC){
            if (obj.getPAIS().equals("Peru")){
                if (obj.getFAMILIA().toLowerCase().contains(pe.toLowerCase()) || obj.getPN().toLowerCase().contains(pe.toLowerCase())){
                    listThinkbookCt.add(obj);
                }

            }

        }
        thinkbookCBuscarAdapter = new ThinkbookCBuscarAdapter(thinkbookCBuscarAdapter.context, listThinkbookCt);
        recyclerView.setAdapter(thinkbookCBuscarAdapter);
    }








    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
        thinkbookCAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        thinkbookCAdapter.stopListening();
    }



}