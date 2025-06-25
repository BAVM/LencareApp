package com.vaxwe.lencareapp.ui.Categorias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.vaxwe.lencareapp.R;
import com.vaxwe.lencareapp.ui.CategoriasCliente.Accesorios.AccesoriosC;
import com.vaxwe.lencareapp.ui.CategoriasCliente.IdeacentreC.IdeacentreC;
import com.vaxwe.lencareapp.ui.CategoriasCliente.IdeapadC.IdeapadC;
import com.vaxwe.lencareapp.ui.CategoriasCliente.LegionC.LegionC;
import com.vaxwe.lencareapp.ui.CategoriasCliente.MonitoresC.MonitoresC;
import com.vaxwe.lencareapp.ui.CategoriasCliente.TabletsC.TabletsC;
import com.vaxwe.lencareapp.ui.CategoriasCliente.ThinkbookC.ThinkbookC;
import com.vaxwe.lencareapp.ui.CategoriasCliente.ThinkcentreC.ThinkcentreC;
import com.vaxwe.lencareapp.ui.CategoriasCliente.ThinkpadC.ThinkpadC;
import com.vaxwe.lencareapp.ui.CategoriasCliente.ThinksmartC.ThinksmartC;
import com.vaxwe.lencareapp.ui.CategoriasCliente.YogaC.YogaC;

public class ControladorCD extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controlador_cd);


        String CategoriaRecuperada = getIntent().getStringExtra("Categoria");

        if (CategoriaRecuperada.equals("Thinkpad")){
            startActivity(new Intent(ControladorCD.this, ThinkpadC.class));
            finish();
        }
        if (CategoriaRecuperada.equals("Thinkcentre")){
            startActivity(new Intent(ControladorCD.this, ThinkcentreC.class));
            finish();
        }
        if (CategoriaRecuperada.equals("Thinkbook")){
            startActivity(new Intent(ControladorCD.this, ThinkbookC.class));
            finish();
        }
        if (CategoriaRecuperada.equals("Thinksmart")){
            startActivity(new Intent(ControladorCD.this, ThinksmartC.class));
            finish();
        }
        if (CategoriaRecuperada.equals("Ideapad")){
            startActivity(new Intent(ControladorCD.this, IdeapadC.class));
            finish();
        }
        if (CategoriaRecuperada.equals("Ideacentre")){
            startActivity(new Intent(ControladorCD.this, IdeacentreC.class));
            finish();
        }
        if (CategoriaRecuperada.equals("Legion")){
            startActivity(new Intent(ControladorCD.this, LegionC.class));
            finish();
        }
        if (CategoriaRecuperada.equals("Yoga")){
            startActivity(new Intent(ControladorCD.this, YogaC.class));
            finish();
        }
        if (CategoriaRecuperada.equals("Tablets")){
            startActivity(new Intent(ControladorCD.this, TabletsC.class));
            finish();
        }
        if (CategoriaRecuperada.equals("Accesorios")){
            startActivity(new Intent(ControladorCD.this, AccesoriosC.class));
            finish();
        }
        if (CategoriaRecuperada.equals("Monitores")){
            startActivity(new Intent(ControladorCD.this, MonitoresC.class));
            finish();
        }

    }
}