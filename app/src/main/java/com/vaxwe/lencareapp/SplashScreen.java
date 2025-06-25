package com.vaxwe.lencareapp;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.TextView;

import com.vaxwe.lencareapp.databinding.ActivitySplashScreenBinding;


public class SplashScreen extends AppCompatActivity {

    TextView desarrollador;

    private ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());

        //setTheme(R.style.AppTheme_Fullscreen);


        //CODIGO PARA OCULTAR ACTIONBAR
        //getSupportActionBar().hide();

        // CAMBIO DE TIPOGRAFIA
        //String ubicacion = "font/roboto_regular.ttf";
        //Typeface tf = Typeface.createFromAsset(Carga.this.getAssets(),ubicacion);

        //desarrollador = findViewById(R.id.desarrollador);

        //DURACION DE LA CARGA
        final int duracion = 1000;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // CODIGO QUE SE EJECUTA DE LA CARGA
                Intent intent = new Intent(SplashScreen.this, InicioSesion.class);
                startActivity(intent);
                finish();
            }
        },duracion);

        //app_name.setTypeface(tf);
        //desarrollador.setTypeface(tf);



        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
    }

}