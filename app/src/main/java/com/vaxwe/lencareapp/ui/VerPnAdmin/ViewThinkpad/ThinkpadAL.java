package com.vaxwe.lencareapp.ui.VerPnAdmin.ViewThinkpad;

import static com.orhanobut.dialogplus.DialogPlus.newDialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.SearchView;
import android.app.ProgressDialog;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.Html;

import android.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vaxwe.lencareapp.R;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ThinkpadAL extends AppCompatActivity {

    private Window window;

    String Color16 = "#3E6D9C";

    RecyclerView recyclerView;
    ThinkpadAdapter thinkpadAdapter;
    DatabaseReference databaseReference;

    ProgressDialog progressDialog;

    ArrayList<ThinkpadModo> listBuscarTA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thinkpad_al);

        ActionBar actionBar = getSupportActionBar(); //CREAMOS ACTIONBAR
        assert actionBar != null;                    //AFIRMAMOS QUE EL ACTIONBAR NO SEA NULO
        //actionBar.setTitle("Thinkpad");         //LE ASIGNAMOS UN TITULO
        actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Thinkpad</font>"));

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.window = getWindow();

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary1)));
        window.setStatusBarColor(Color.parseColor(Color16));


        recyclerView = (RecyclerView) findViewById(R.id.ViewThinkpadL);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        /*databaseReference = FirebaseDatabase.getInstance().getReference("THINKPAD");

        listBuscarTA = new ArrayList<>();
        thinkpadBuscarAdapter = new ThinkpadBuscarAdapter(this,listBuscarTA);
        recyclerView.setAdapter(thinkpadBuscarAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    ThinkpadModo thinkpadModo = snapshot.getValue(ThinkpadModo.class);
                    listBuscarTA.add(thinkpadModo);
                    }

                }
                thinkpadBuscarAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        FirebaseRecyclerOptions<ThinkpadModo> options = new FirebaseRecyclerOptions.Builder<ThinkpadModo>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("THINKPAD"),ThinkpadModo.class).build();


        thinkpadAdapter = new ThinkpadAdapter(options);
        recyclerView.setAdapter(thinkpadAdapter);



    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
        thinkpadAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        thinkpadAdapter.stopListening();
    }

    //BOTON DE BUSCAR

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_buscar,menu);
        MenuItem item = menu.findItem(R.id.BtnBuscar);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtBuscar(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String str) {
                txtBuscar(str);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void txtBuscar(String str){

        FirebaseRecyclerOptions<ThinkpadModo> options = new FirebaseRecyclerOptions.Builder<ThinkpadModo>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("THINKPAD").orderByChild("FAMILIA").startAt(str).endAt(str+"~"),ThinkpadModo.class).build();


        thinkpadAdapter = new ThinkpadAdapter(options);
        thinkpadAdapter.startListening();
        recyclerView.setAdapter(thinkpadAdapter);

    }







    //BOTON DE BUSCAR
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_buscar,menu);
        MenuItem item = menu.findItem(R.id.BtnBuscar);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                txtBuscar(s);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }


    private void txtBuscar(String s){

        ArrayList<ThinkpadModo>tlistBuscarTA = new ArrayList<>();
        for (ThinkpadModo obj: listBuscarTA){
            if (obj.getFAMILIA().toLowerCase().contains(s.toLowerCase()) || obj.getPN().toLowerCase().contains(s.toLowerCase())){
                tlistBuscarTA.add(obj);
            }

        }
        thinkpadBuscarAdapter = new ThinkpadBuscarAdapter(thinkpadBuscarAdapter.context, tlistBuscarTA);
        recyclerView.setAdapter(thinkpadBuscarAdapter);




    }*/

}