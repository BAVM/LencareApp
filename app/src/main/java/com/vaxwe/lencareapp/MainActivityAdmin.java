package com.vaxwe.lencareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.vaxwe.lencareapp.databinding.ActivityMainAdminBinding;
import com.vaxwe.lencareapp.ui.admin.inicio_admin.InicioFragmentA;

public class MainActivityAdmin extends AppCompatActivity {

    private ActivityMainAdminBinding binding;
    private AppBarConfiguration mAppBarConfiguration;

    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    DatabaseReference BaseDatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMainAdmin.toolbarAdmin);

        DrawerLayout drawer = binding.drawerLayoutAdmin;
        NavigationView navigationView = binding.navViewAdmin;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_inicio_a,R.id.nav_mi_cuenta_a,R.id.nav_categorias_a,R.id.nav_registro_a,R.id.nav_lista_a,R.id.nav_verpn_a)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_admin);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // INICIARLIZAR SESION
        firebaseAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        BaseDatos = FirebaseDatabase.getInstance().getReference("DBAdmin");

        UpdateNavHeaderU();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.SalirAdmin:
                CerrarSesion();
                break;

        }
        return super.onOptionsItemSelected(item);

    }

    //COMPROBAR INICIO DE SESION ADMINISTRADOR
    private void CompobandoInicioSesion(){
        if (user!=null){
            //Toast.makeText(this, "Se ha iniciado sesión", Toast.LENGTH_SHORT).show();

        }else{
            //SINO SE INICIA SESION NO ES UN ADMINISTRADOR
            startActivity(new Intent(MainActivityAdmin.this, InicioFragmentA.class));
            finish();
        }
    }

    //CERRAR SESION CLIENTE
    private void CerrarSesion(){
        firebaseAuth.signOut();
        //SE CIERRA SESION SE TRANSFIERE AL INICIO DEL CLIENTE
        startActivity(new Intent(MainActivityAdmin.this,InicioSesion.class));
        Preferencias.clearData(this);
        finish();
        Toast.makeText(this, "Cerraste sesión", Toast.LENGTH_SHORT).show();
    }

    public void UpdateNavHeaderU(){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_admin);
        View headerView = navigationView.getHeaderView(0);
        TextView nombreNavA = headerView.findViewById(R.id.NombreCabezeraA);
        TextView apellidoNavA = headerView.findViewById(R.id.ApellidoCabezeraA);
        TextView correoNavA = headerView.findViewById(R.id.EmailCabezeraA);
        ImageView imagenNavA = headerView.findViewById(R.id.fotocabezoteAdmin);

        BaseDatos.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nombre = ""+snapshot.child("NOMBRE").getValue();
                String apellido = ""+snapshot.child("APELLIDO").getValue();
                String correo = ""+snapshot.child("EMAIL").getValue();
                String imagen = ""+snapshot.child("IMAGEN").getValue();

                nombreNavA.setText(nombre);
                apellidoNavA.setText(apellido);
                correoNavA.setText(correo);


                //correoNavA.setText(user.getEmail());

                try {//SI EXISTE LA IMAGEN
                    //Picasso.get().load(user.getPhotoUrl()).placeholder(R.drawable.perfil_1).into(imagenNavA);
                    Picasso.get().load(imagen).placeholder(R.drawable.perfil_1).into(imagenNavA);

                }catch (Exception e){// NO EXTISTE LA IMAGEN
                    //Picasso.get().load(R.drawable.perfil_1).into(imagenNavA);
                    Picasso.get().load(R.drawable.perfil_1).into(imagenNavA);

                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //INICIAR SESION CLIENTE
    @Override
    protected void onStart() {
        CompobandoInicioSesion();
        super.onStart();

    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_admin);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }



}