package com.vaxwe.lencareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPass extends AppCompatActivity {

    private EditText RecuperarEmail;
    private Button BtnAccederResetPass;
    private String emailReset = "";
    private FirebaseAuth mAuth;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);

        /*ActionBar actionBar = getSupportActionBar(); //CREAMOS ACTIONBAR
        assert actionBar != null;                    //AFIRMAMOS QUE EL ACTIONBAR NO SEA NULO
        actionBar.setTitle("Restablecer contraseña");         //LE ASIGNAMOS UN TITULO
        actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Restablecer contraseña</font>"));

        actionBar.setDisplayHomeAsUpEnabled(true);   //HABILITAMOS EL BOTON RETROCESO
        actionBar.setDisplayShowHomeEnabled(true);*/   //HABILITAMOS EL BOTON RETROCESO

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        RecuperarEmail = (EditText) findViewById(R.id.RecuperarEmail);
        BtnAccederResetPass = (Button) findViewById(R.id.BtnAccederResetPass);

        BtnAccederResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                emailReset = RecuperarEmail.getText().toString();

                if (!emailReset.isEmpty()){
                    progressDialog.setMessage("Espera por favor...");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    resetPass();

                }else{
                    Toast.makeText(ResetPass.this, "Ingresa el Email", Toast.LENGTH_SHORT).show();

                }


            }
        });



    }


    private void resetPass(){
        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(emailReset).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    Toast.makeText(ResetPass.this, "Se ha enviado un correo para restablecer tu contraseña. ", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(ResetPass.this, "Hubo un error al enviar el Email para restablecer contraseña.", Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();

            }
        });

    }

    //RETROCESO PARA EL ACTION BAR
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }


}