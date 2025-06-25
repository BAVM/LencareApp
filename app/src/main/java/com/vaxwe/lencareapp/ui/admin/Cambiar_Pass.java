package com.vaxwe.lencareapp.ui.admin;

import static com.google.firebase.storage.FirebaseStorage.getInstance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.vaxwe.lencareapp.InicioSesion;
import com.vaxwe.lencareapp.MainActivityAdmin;
import com.vaxwe.lencareapp.Preferencias;
import com.vaxwe.lencareapp.R;
import com.vaxwe.lencareapp.ui.cliente.Cambiar_Pass_C;

import java.util.HashMap;

public class Cambiar_Pass extends AppCompatActivity {

    private Window window;

    String Color16 = "#3E6D9C";

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    DatabaseReference BaseDatos;

    StorageReference storageReference;
    ProgressDialog progressDialog;

    TextView PassActual;
    EditText ActualPassET,NuevoPassET;
    Button CambiarPassBTN,IrInicioBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_pass);


        ActionBar actionBar = getSupportActionBar(); //CREAMOS ACTIONBAR
        assert actionBar != null;                    //AFIRMAMOS QUE EL ACTIONBAR NO SEA NULO
        //actionBar.setTitle("Mis Credenciales");         //LE ASIGNAMOS UN TITULO

        this.window = getWindow();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary1)));
        window.setStatusBarColor(Color.parseColor(Color16));


        PassActual = findViewById(R.id.PassActual);
        ActualPassET = findViewById(R.id.ActualPassET);
        NuevoPassET = findViewById(R.id.NuevoPassET);
        CambiarPassBTN = findViewById(R.id.CambiarPassBTN);
        IrInicioBTN = findViewById(R.id.IrInicioBTN);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        storageReference = getInstance().getReference();
        progressDialog = new ProgressDialog(Cambiar_Pass.this);

        BaseDatos = FirebaseDatabase.getInstance().getReference("DBAdmin");

        //CONSULTA EN LA BASE DE DATOS
        Query query = BaseDatos.orderByChild("EMAIL").equalTo(user.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()){
                    //OBTENER PASS
                    String pass = ""+ds.child("PASSWORD").getValue();
                    PassActual.setText(pass);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        CambiarPassBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ACTUAL_PASS = ActualPassET.getText().toString().trim();
                String NUEVO_PASS = NuevoPassET.getText().toString().trim();

                //CONDICIONES
                if (TextUtils.isEmpty(ACTUAL_PASS)){
                    Toast.makeText(Cambiar_Pass.this, "El campo esta vacio", Toast.LENGTH_SHORT).show();

                }
                if (TextUtils.isEmpty(NUEVO_PASS)){
                    Toast.makeText(Cambiar_Pass.this, "El campo esta vacio", Toast.LENGTH_SHORT).show();

                }
                if (!NUEVO_PASS.matches(".*[!@#$%^&*+=?-].*")){
                    Toast.makeText(Cambiar_Pass.this, "La contraseña debe tener un caracter especial", Toast.LENGTH_SHORT).show();

                }
                if (!NUEVO_PASS.matches(".*\\d.*")){
                    Toast.makeText(Cambiar_Pass.this, "La contraseña no debe tener espacios en blanco", Toast.LENGTH_SHORT).show();

                }
                if (!NUEVO_PASS.matches(".*[A-Z].*")){
                    Toast.makeText(Cambiar_Pass.this, "La contraseña debe tener una letra mayuscula", Toast.LENGTH_SHORT).show();

                }
                if (!ACTUAL_PASS.equals("") && !NUEVO_PASS.equals("") && NUEVO_PASS.length() >= 6 && NUEVO_PASS.matches(".*[!@#$%^&*+=?-].*") &&
                        NUEVO_PASS.matches(".*\\d.*") && NUEVO_PASS.matches(".*[A-Z].*")){
                    CambioPassword(ACTUAL_PASS,NUEVO_PASS);
                }
                if (ACTUAL_PASS == NUEVO_PASS){
                    CambioPassword(ACTUAL_PASS,NUEVO_PASS);
                }
                else {
                    NuevoPassET.setError("Verifique la calidad de la contraseña");
                    NuevoPassET.setFocusable(true);
                }

            }
        });


        IrInicioBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Cambiar_Pass.this, MainActivityAdmin.class));
                finish();
            }
        });




    }



    private void CambioPassword(String pass_actual, String nuevo_pass) {
        progressDialog.show();
        progressDialog.setTitle("Actualizando");
        progressDialog.setMessage("Por favor espere...");

        AuthCredential authCredential = EmailAuthProvider.getCredential(user.getEmail(),pass_actual);
        user.reauthenticate(authCredential).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                user.updatePassword(nuevo_pass).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressDialog.dismiss();
                        String NUEVO_PASS = NuevoPassET.getText().toString().trim();
                        HashMap<String,Object> resultado = new HashMap<>();
                        resultado.put("PASSWORD",NUEVO_PASS);
                        //ACTUALIZAR CONTRASEÑA EN BD
                        BaseDatos.child(user.getUid()).updateChildren(resultado).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Cambiar_Pass.this, "Cambio exitoso!", Toast.LENGTH_SHORT).show();
                                //CERRAR SESIÓN
                                firebaseAuth.signOut();
                                startActivity(new Intent(Cambiar_Pass.this, InicioSesion.class));
                                Preferencias.clearData(Cambiar_Pass.this);
                                finish();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Cambiar_Pass.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Cambiar_Pass.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Cambiar_Pass.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {

    }

}