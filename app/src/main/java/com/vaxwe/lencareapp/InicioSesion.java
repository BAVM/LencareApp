package com.vaxwe.lencareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InicioSesion extends AppCompatActivity {

    EditText Email,Password;
    Button Acceder;
    Button BtnResetPass;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    ProgressDialog progressDialog;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        //actionBar.setDisplayHomeAsUpEnabled(true);   //HABILITAMOS EL BOTON RETROCESO
        //actionBar.setDisplayShowHomeEnabled(true);   //HABILITAMOS EL BOTON RETROCESO

        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);
        Acceder = findViewById(R.id.Acceder);
        BtnResetPass = findViewById(R.id.BtnResetPass);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(InicioSesion.this);
        progressDialog.setMessage("Iniciando sesión.");
        progressDialog.setCancelable(false);

        BtnResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InicioSesion.this, ResetPass.class));

            }
        });

        Acceder.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //CONVERTIR A STRING

                String email = Email.getText().toString();
                String pass = Password.getText().toString();

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Email.setError("Correo invalido.");
                    Email.setFocusable(true);

                }else if(pass.length()<6){
                    Password.setError("La contaseña debe ser igual o mayor a 6 caracteres.");
                    Password.setFocusable(true);

                }else {
                    LoginAdmin(email,pass);
                }

            }

        });


    }

    private void LoginAdmin(String email, String pass) {
        progressDialog.show();
        progressDialog.setCancelable(false);
        firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(InicioSesion.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //SI EL INICIO DE SESION FUE EXITOSO
                if (task.isSuccessful()){

                    //NUEVO CODIGO

                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    String UserID = currentUser.getUid();

                    databaseReference = FirebaseDatabase.getInstance().getReference().child("DBAdmin").child(UserID);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String Roll = dataSnapshot.child("ROLL").getValue().toString();
                            if (Roll.equals("Admin")){
                                progressDialog.dismiss();
                                Preferencias.setDataLogin(InicioSesion.this, true);
                                Preferencias.setDataRoll(InicioSesion.this, "Admin");
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                startActivity(new Intent(InicioSesion.this,MainActivityAdmin.class));
                                assert user != null;
                                //Toast.makeText(InicioSesion.this, "Hola "+user.getEmail(), Toast.LENGTH_SHORT).show();
                                finish();

                            }else if (Roll.equals("User")){
                                progressDialog.dismiss();
                                Preferencias.setDataLogin(InicioSesion.this, true);
                                Preferencias.setDataRoll(InicioSesion.this, "User");
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                startActivity(new Intent(InicioSesion.this,MainActivityCliente.class));
                                assert user != null;
                                //Toast.makeText(InicioSesion.this, "Hola "+user.getEmail(), Toast.LENGTH_SHORT).show();
                                finish();

                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }else{
                    progressDialog.dismiss();
                    UserInvalido();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                UserInvalido();
            }
        });
    }


    private void UserInvalido() {
        AlertDialog.Builder builder = new AlertDialog.Builder(InicioSesion.this);
        builder.setCancelable(false);
        builder.setTitle("Ha ocurrido un error al iniciar sesión.");
        builder.setMessage("Por favor verifique que los datos ingresados son correctos.").setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();

    }


    //RETROCESO PARA EL ACTION BAR
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    //INICIAR SESION ADMINISTRADOR
    @Override
    protected void onStart() {
        super.onStart();
        if (Preferencias.getDataLogin(this)) {
            if (Preferencias.getDataRoll(this).equals("Admin")) {
                startActivity(new Intent(this, MainActivityAdmin.class));
                finish();
            } else {
                startActivity(new Intent(this, MainActivityCliente.class));
                finish();
            }
        }

    }





}