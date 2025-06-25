package com.vaxwe.lencareapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.Picasso;
import com.vaxwe.lencareapp.databinding.ActivityMainClienteBinding;
import com.vaxwe.lencareapp.ui.cliente.inicio_cliente.HomeFragment;

public class MainActivityCliente extends AppCompatActivity {


    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainClienteBinding binding;

    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    DatabaseReference BaseDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainClienteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.appBarMainCliente.toolbarCliente);

        DrawerLayout drawer = binding.drawerLayoutCliente;
        NavigationView navigationView = binding.navViewCliente;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_inicio, R.id.nav_mi_cuenta, R.id.nav_favoritos,R.id.DetallesApp,R.id.InfoView)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_cliente);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        // INICIARLIZAR SESION
        firebaseAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        BaseDatos = FirebaseDatabase.getInstance().getReference("DBAdmin");

        UpdateNavHeaderU();

        // LLAMADO A NOTIFICACIONES FIREBASE
        FirebaseMessaging.getInstance().subscribeToTopic("web_app")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Done";
                        if (!task.isSuccessful()) {
                            msg = "Failed";
                        }

                    }
                });




    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_cliente, menu);
        return true;
    }

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.SalirCliente:
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
                startActivity(new Intent(MainActivityCliente.this, HomeFragment.class));
                finish();
            }
        }

        //CERRAR SESION CLIENTE
        private void CerrarSesion(){
            firebaseAuth.signOut();
            //SE CIERRA SESION SE TRANSFIERE AL INICIO DEL CLIENTE
            startActivity(new Intent(MainActivityCliente.this,InicioSesion.class));
            Preferencias.clearData(this);
            finish();
            Toast.makeText(this, "Cerraste sesión", Toast.LENGTH_SHORT).show();
        }

        public void UpdateNavHeaderU(){
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_cliente);
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
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_cliente);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}