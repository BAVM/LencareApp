package com.vaxwe.lencareapp.ui.cliente;

import static com.google.firebase.database.FirebaseDatabase.getInstance;

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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.vaxwe.lencareapp.InicioSesion;
import com.vaxwe.lencareapp.MainActivityCliente;
import com.vaxwe.lencareapp.Preferencias;
import com.vaxwe.lencareapp.R;

import java.util.HashMap;
import java.util.regex.Pattern;

public class Cambiar_Pass_C extends AppCompatActivity {

    private Window window;

    String Color16 = "#3E6D9C";

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    DatabaseReference BaseDatos;

    DatabaseReference storageReference;
    ProgressDialog progressDialog;

    TextView PassActualC;
    EditText ActualPassETC,NuevoPassETC;
    Button CambiarPassBTNC,IrInicioBTNC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_pass_c);

        ActionBar actionBar = getSupportActionBar(); //CREAMOS ACTIONBAR
        assert actionBar != null;                    //AFIRMAMOS QUE EL ACTIONBAR NO SEA NULO
        //actionBar.setTitle("Mis Credenciales");         //LE ASIGNAMOS UN TITULO


        //actionBar.setDisplayShowHomeEnabled(true);
        //actionBar.setDisplayHomeAsUpEnabled(true);

        this.window = getWindow();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary1)));
        window.setStatusBarColor(Color.parseColor(Color16));

        PassActualC = findViewById(R.id.PassActualC);
        ActualPassETC = findViewById(R.id.ActualPassETC);
        NuevoPassETC = findViewById(R.id.NuevoPassETC);
        CambiarPassBTNC = findViewById(R.id.CambiarPassBTNC);
        IrInicioBTNC = findViewById(R.id.IrInicioBTNC);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        storageReference = getInstance().getReference();
        progressDialog = new ProgressDialog(Cambiar_Pass_C.this);

        BaseDatos = getInstance().getReference("DBAdmin");


        //CONSULTA EN LA BASE DE DATOS
        Query query = BaseDatos.orderByChild("EMAIL").equalTo(user.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()){
                    //OBTENER PASS
                    String pass = ""+ds.child("PASSWORD").getValue();
                    PassActualC.setText(pass);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        CambiarPassBTNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ACTUAL_PASS = ActualPassETC.getText().toString().trim();
                String NUEVO_PASS = NuevoPassETC.getText().toString().trim();

                //CONDICIONES
                if (TextUtils.isEmpty(ACTUAL_PASS)){
                    Toast.makeText(Cambiar_Pass_C.this, "El campo esta vacio", Toast.LENGTH_SHORT).show();

                }
                if (TextUtils.isEmpty(NUEVO_PASS)){
                    Toast.makeText(Cambiar_Pass_C.this, "El campo esta vacio", Toast.LENGTH_SHORT).show();

                }
                if (!NUEVO_PASS.matches(".*[!@#$%^&*+=?-].*")){
                    Toast.makeText(Cambiar_Pass_C.this, "La contraseña debe tener un caracter especial", Toast.LENGTH_SHORT).show();

                }
                if (!NUEVO_PASS.matches(".*\\d.*")){
                    Toast.makeText(Cambiar_Pass_C.this, "La contraseña no debe tener espacios en blanco", Toast.LENGTH_SHORT).show();

                }
                if (!NUEVO_PASS.matches(".*[A-Z].*")){
                    Toast.makeText(Cambiar_Pass_C.this, "La contraseña debe tener una letra mayuscula", Toast.LENGTH_SHORT).show();

                }
                /*if (!NUEVO_PASS.matches("(?=.*[0-9])")){
                    Toast.makeText(Cambiar_Pass_C.this, "La contraseña debe tener un numero", Toast.LENGTH_SHORT).show();

                }*/

                if (!ACTUAL_PASS.equals("") && !NUEVO_PASS.equals("") && NUEVO_PASS.length() >= 6 && NUEVO_PASS.matches(".*[!@#$%^&*+=?-].*") &&
                NUEVO_PASS.matches(".*\\d.*") && NUEVO_PASS.matches(".*[A-Z].*")){
                    CambioPasswordC(ACTUAL_PASS,NUEVO_PASS);
                }
                if (ACTUAL_PASS == NUEVO_PASS){
                    CambioPasswordC(ACTUAL_PASS,NUEVO_PASS);

                } else {
                    NuevoPassETC.setError("Verifique la calidad de la contraseña");
                    NuevoPassETC.setFocusable(true);
                }

            }
        });

        IrInicioBTNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Cambiar_Pass_C.this, MainActivityCliente.class));
                finish();
            }
        });



    }

    private void CambioPasswordC(String pass_actual, String nuevo_pass) {
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
                        String NUEVO_PASS = NuevoPassETC.getText().toString().trim();
                        HashMap<String,Object> resultado = new HashMap<>();
                        resultado.put("PASSWORD",NUEVO_PASS);
                        //ACTUALIZAR CONTRASEÑA EN BD
                        BaseDatos.child(user.getUid()).updateChildren(resultado).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Cambiar_Pass_C.this, "Cambio exitoso!", Toast.LENGTH_SHORT).show();
                                //CERRAR SESIÓN
                                firebaseAuth.signOut();
                                startActivity(new Intent(Cambiar_Pass_C.this, InicioSesion.class));
                                Preferencias.clearData(Cambiar_Pass_C.this);
                                finish();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Cambiar_Pass_C.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Cambiar_Pass_C.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Cambiar_Pass_C.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }


}