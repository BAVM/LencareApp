package com.vaxwe.lencareapp.ui.CategoriasCliente.IdeapadC;

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

public class IdeapadC extends AppCompatActivity {

    private Window window;

    String Color16 = "#3E6D9C";

    RecyclerView recyclerView;
    IdeapadCAdapter ideapadCAdapter;
    DatabaseReference mRef;

    ArrayList<IdeapadCModo> listIdeapadC;
    IdeapadCBuscarAdapter ideapadCBuscarAdapter;

    private SearchView searchViewPrimary,searchViewCl,searchViewAr,searchViewCo,searchViewMx,searchViewPe;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideapad_c);

        ActionBar actionBar = getSupportActionBar(); //CREAMOS ACTIONBAR
        assert actionBar != null;                    //AFIRMAMOS QUE EL ACTIONBAR NO SEA NULO
        //actionBar.setTitle("Accesorios");         //LE ASIGNAMOS UN TITULO
        actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Ideapad</font>"));

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.window = getWindow();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary1)));
        window.setStatusBarColor(Color.parseColor(Color16));

        searchViewPrimary = findViewById(R.id.BtnBuscarIPC);
        searchViewPrimary.clearFocus();

        searchViewCl = findViewById(R.id.BtnBuscarIPC);
        searchViewCl.clearFocus();

        searchViewAr = findViewById(R.id.BtnBuscarIPC);
        searchViewAr.clearFocus();

        searchViewCo = findViewById(R.id.BtnBuscarIPC);
        searchViewCo.clearFocus();

        searchViewMx = findViewById(R.id.BtnBuscarIPC);
        searchViewMx.clearFocus();

        searchViewPe = findViewById(R.id.BtnBuscarIPC);
        searchViewPe.clearFocus();

        recyclerView = (RecyclerView) findViewById(R.id.ViewIdeapadC);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRef = FirebaseDatabase.getInstance().getReference("IDEAPAD");

        listIdeapadC = new ArrayList<>();
        ideapadCBuscarAdapter = new IdeapadCBuscarAdapter(this,listIdeapadC);
        recyclerView.setAdapter(ideapadCBuscarAdapter);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                        IdeapadCModo ideapadCModo = snapshot.getValue(IdeapadCModo.class);
                        listIdeapadC.add(ideapadCModo);
                    }

                }
                ideapadCAdapter.notifyDataSetChanged();

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



        FirebaseRecyclerOptions<IdeapadCModo> options = new FirebaseRecyclerOptions.Builder<IdeapadCModo>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("IDEAPAD"),IdeapadCModo.class).build();


        ideapadCAdapter = new IdeapadCAdapter(options);
        recyclerView.setAdapter(ideapadCAdapter);
        ideapadCAdapter.notifyDataSetChanged();




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

                ArrayList<IdeapadCModo>listIdeapadFiltroAR = new ArrayList<>();
                for (IdeapadCModo obj: listIdeapadC){
                    if (obj.getPAIS().equals("Argentina")){
                        listIdeapadFiltroAR.add(obj);

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
                ideapadCBuscarAdapter = new IdeapadCBuscarAdapter(ideapadCBuscarAdapter.context, listIdeapadFiltroAR);
                recyclerView.setAdapter(ideapadCBuscarAdapter);

                Toast.makeText(this, "Argentina", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnChile:

                ArrayList<IdeapadCModo>listIdeapadFiltroCL = new ArrayList<>();
                for (IdeapadCModo obj: listIdeapadC){
                    if (obj.getPAIS().equals("Chile")){
                        listIdeapadFiltroCL.add(obj);

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
                ideapadCBuscarAdapter = new IdeapadCBuscarAdapter(ideapadCBuscarAdapter.context, listIdeapadFiltroCL);
                recyclerView.setAdapter(ideapadCBuscarAdapter);

                Toast.makeText(this, "Chile", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnColombia:

                ArrayList<IdeapadCModo>listIdeapadFiltroCO = new ArrayList<>();
                for (IdeapadCModo obj: listIdeapadC){
                    if (obj.getPAIS().equals("Colombia")){
                        listIdeapadFiltroCO.add(obj);

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
                ideapadCBuscarAdapter = new IdeapadCBuscarAdapter(ideapadCBuscarAdapter.context, listIdeapadFiltroCO);
                recyclerView.setAdapter(ideapadCBuscarAdapter);

                Toast.makeText(this, "Colombia", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnMexico:

                ArrayList<IdeapadCModo>listIdeapadFiltroMX = new ArrayList<>();
                for (IdeapadCModo obj: listIdeapadC){
                    if (obj.getPAIS().equals("Mexico")){
                        listIdeapadFiltroMX.add(obj);

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
                ideapadCBuscarAdapter = new IdeapadCBuscarAdapter(ideapadCBuscarAdapter.context, listIdeapadFiltroMX);
                recyclerView.setAdapter(ideapadCBuscarAdapter);

                Toast.makeText(this, "Mexico", Toast.LENGTH_SHORT).show();

                break;

            case R.id.BtnPeru:

                ArrayList<IdeapadCModo>listIdeapadFiltroPE = new ArrayList<>();
                for (IdeapadCModo obj: listIdeapadC){
                    if (obj.getPAIS().equals("Peru")){
                        listIdeapadFiltroPE.add(obj);

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
                ideapadCBuscarAdapter = new IdeapadCBuscarAdapter(ideapadCBuscarAdapter.context, listIdeapadFiltroPE);
                recyclerView.setAdapter(ideapadCBuscarAdapter);

                Toast.makeText(this, "Peru", Toast.LENGTH_SHORT).show();

                break;

        }
        return super.onOptionsItemSelected(item);
    }





    //BOTON BUSQUEDA PRINCIPAL CLIENTE
    private void filterList(String s) {
        ArrayList<IdeapadCModo>listIdeapadCt = new ArrayList<>();
        for (IdeapadCModo obj: listIdeapadC){
            if (obj.getFAMILIA().toLowerCase().contains(s.toLowerCase()) || obj.getPN().toLowerCase().contains(s.toLowerCase())){
                listIdeapadCt.add(obj);
            }

        }
        ideapadCBuscarAdapter = new IdeapadCBuscarAdapter(ideapadCBuscarAdapter.context, listIdeapadCt);
        recyclerView.setAdapter(ideapadCBuscarAdapter);

    }


    //FILTRO BUSCAR POR CHILE
    private void filterListCl(String cl) {
        ArrayList<IdeapadCModo>listIdeapadCt = new ArrayList<>();
        for (IdeapadCModo obj: listIdeapadC){
            if (obj.getPAIS().equals("Chile")){
                if (obj.getFAMILIA().toLowerCase().contains(cl.toLowerCase()) || obj.getPN().toLowerCase().contains(cl.toLowerCase())){
                    listIdeapadCt.add(obj);
                }

            }

        }
        ideapadCBuscarAdapter = new IdeapadCBuscarAdapter(ideapadCBuscarAdapter.context, listIdeapadCt);
        recyclerView.setAdapter(ideapadCBuscarAdapter);
    }

    //FILTRO BUSCAR POR ARGENTINA
    private void filterListAr(String ar) {
        ArrayList<IdeapadCModo>listIdeapadCt = new ArrayList<>();
        for (IdeapadCModo obj: listIdeapadC){
            if (obj.getPAIS().equals("Argentina")){
                if (obj.getFAMILIA().toLowerCase().contains(ar.toLowerCase()) || obj.getPN().toLowerCase().contains(ar.toLowerCase())){
                    listIdeapadCt.add(obj);
                }

            }

        }
        ideapadCBuscarAdapter = new IdeapadCBuscarAdapter(ideapadCBuscarAdapter.context, listIdeapadCt);
        recyclerView.setAdapter(ideapadCBuscarAdapter);

    }

    //FILTRO BUSCAR POR COLOMBIA
    private void filterListCo(String co) {
        ArrayList<IdeapadCModo>listIdeapadCt = new ArrayList<>();
        for (IdeapadCModo obj: listIdeapadC){
            if (obj.getPAIS().equals("Colombia")){
                if (obj.getFAMILIA().toLowerCase().contains(co.toLowerCase()) || obj.getPN().toLowerCase().contains(co.toLowerCase())){
                    listIdeapadCt.add(obj);
                }

            }

        }
        ideapadCBuscarAdapter = new IdeapadCBuscarAdapter(ideapadCBuscarAdapter.context, listIdeapadCt);
        recyclerView.setAdapter(ideapadCBuscarAdapter);
    }

    //FILTRO BUSCAR POR MEXICO
    private void filterListMx(String mx) {
        ArrayList<IdeapadCModo>listIdeapadCt = new ArrayList<>();
        for (IdeapadCModo obj: listIdeapadC){
            if (obj.getPAIS().equals("Mexico")){
                if (obj.getFAMILIA().toLowerCase().contains(mx.toLowerCase()) || obj.getPN().toLowerCase().contains(mx.toLowerCase())){
                    listIdeapadCt.add(obj);
                }

            }

        }
        ideapadCBuscarAdapter = new IdeapadCBuscarAdapter(ideapadCBuscarAdapter.context, listIdeapadCt);
        recyclerView.setAdapter(ideapadCBuscarAdapter);
    }

    //FILTRO BUSCAR POR PERU
    private void filterListPe(String pe) {
        ArrayList<IdeapadCModo>listIdeapadCt = new ArrayList<>();
        for (IdeapadCModo obj: listIdeapadC){
            if (obj.getPAIS().equals("Peru")){
                if (obj.getFAMILIA().toLowerCase().contains(pe.toLowerCase()) || obj.getPN().toLowerCase().contains(pe.toLowerCase())){
                    listIdeapadCt.add(obj);
                }

            }

        }
        ideapadCBuscarAdapter = new IdeapadCBuscarAdapter(ideapadCBuscarAdapter.context, listIdeapadCt);
        recyclerView.setAdapter(ideapadCBuscarAdapter);
    }





    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
        ideapadCAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        ideapadCAdapter.stopListening();
    }


}