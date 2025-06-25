package com.vaxwe.lencareapp.ui.CategoriasAdmin.ThinkpadA;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vaxwe.lencareapp.R;

import java.util.HashMap;
import java.util.Map;

public class ThinkpadA extends AppCompatActivity {

    private Window window;

    String Color16 = "#3E6D9C";


    EditText AgregarPn,AgregarFamilia,AgregarCpu,AgregarGpu,AgregarHdd,AgregarSsd,AgregarRam,AgregarTeclado,AgregarPantalla;
    Spinner SelecionPais,SelecionSloc,SeleccionHuella,SeleccionBios,AgregarSo;
    Button GuardarPnA;

    String RutaDataBase = "THINKPAD";

    DatabaseReference databaseReference;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thinkpad);

        ActionBar actionBar = getSupportActionBar(); //CREAMOS ACTIONBAR
        assert actionBar != null;                    //AFIRMAMOS QUE EL ACTIONBAR NO SEA NULO
        //actionBar.setTitle("Thinkpad");         //LE ASIGNAMOS UN TITULO
        actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Thinkpad</font>"));

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.window = getWindow();

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary1)));
        window.setStatusBarColor(Color.parseColor(Color16));


        AgregarPn = findViewById(R.id.AgregarPn);
        AgregarFamilia = findViewById(R.id.AgregarFamilia);
        AgregarCpu = findViewById(R.id.AgregarCpu);
        AgregarGpu = findViewById(R.id.AgregarGpu);
        AgregarHdd = findViewById(R.id.AgregarHdd);
        AgregarSsd = findViewById(R.id.AgregarSsd);
        AgregarRam = findViewById(R.id.AgregarRam);
        AgregarTeclado = findViewById(R.id.AgregarTeclado);
        AgregarPantalla = findViewById(R.id.AgregarPantalla);

        SelecionPais = findViewById(R.id.SelecionPais);
        SelecionSloc = findViewById(R.id.SelecionSloc);
        SeleccionHuella = findViewById(R.id.SeleccionHuella);
        SeleccionBios = findViewById(R.id.SeleccionBios);
        AgregarSo = findViewById(R.id.AgregarSo);

        GuardarPnA = findViewById(R.id.GuardarPnA);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        progressDialog = new ProgressDialog(ThinkpadA.this);



        //ARRAY SELECCION PAIS
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.SeleccionPais, android.R.layout.simple_spinner_item);
        SelecionPais.setAdapter(adapter1);

        //ARRAY SELECCION SLOC
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.SeleccionSloc, android.R.layout.simple_spinner_item);
        SelecionSloc.setAdapter(adapter2);

        //ARRAY SELECCION HUELLA
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.Huella, android.R.layout.simple_spinner_item);
        SeleccionHuella.setAdapter(adapter3);

        //ARRAY SELECCION BIOS
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.Bios, android.R.layout.simple_spinner_item);
        SeleccionBios.setAdapter(adapter4);

        //ARRAY SELECCION OS
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this, R.array.Os, android.R.layout.simple_spinner_item);
        AgregarSo.setAdapter(adapter5);




        GuardarPnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String SelecionPaisC = SelecionPais.getSelectedItem().toString();
                String SelecionSlocC = SelecionSloc.getSelectedItem().toString();
                String SeleccionHuellaC = SeleccionHuella.getSelectedItem().toString();
                String SeleccionBiosC = SeleccionBios.getSelectedItem().toString();
                String AgregarSoC = AgregarSo.getSelectedItem().toString();
                String AgregarPnC = AgregarPn.getText().toString();
                String AgregarFamiliaC = AgregarFamilia.getText().toString();
                String AgregarCpuC = AgregarCpu.getText().toString();
                String AgregarGpuC = AgregarGpu.getText().toString();
                String AgregarHddC = AgregarHdd.getText().toString();
                String AgregarSsdC = AgregarSsd.getText().toString();
                String AgregarRamC = AgregarRam.getText().toString();
                String AgregarTecladoC = AgregarTeclado.getText().toString();
                String AgregarPantallaC = AgregarPantalla.getText().toString();

                if (SelecionPaisC.equals("") || SelecionSlocC.equals("") || SeleccionHuellaC.equals("") || SeleccionBiosC.equals("") || AgregarSoC.equals("")
                        || AgregarPnC.equals("") || AgregarFamiliaC.equals("") || AgregarCpuC.equals("") || AgregarGpuC.equals("") || AgregarHddC.equals("")
                        || AgregarSsdC.equals("") || AgregarRamC.equals("") || AgregarTecladoC.equals("") || AgregarPantallaC.equals("")){
                    Toast.makeText(ThinkpadA.this, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show();
                }
                else{
                    progressDialog.show();
                    Map<String, Object> PnThinkpad = new HashMap<>();
                    PnThinkpad.put("PAIS",SelecionPaisC);
                    PnThinkpad.put("SLOC",SelecionSlocC);
                    PnThinkpad.put("PN",AgregarPnC);
                    PnThinkpad.put("FAMILIA",AgregarFamiliaC);
                    PnThinkpad.put("CPU",AgregarCpuC);
                    PnThinkpad.put("GPU",AgregarGpuC);
                    PnThinkpad.put("HDD",AgregarHddC);
                    PnThinkpad.put("SSD",AgregarSsdC);
                    PnThinkpad.put("RAM",AgregarRamC);
                    PnThinkpad.put("TECLADO",AgregarTecladoC);
                    PnThinkpad.put("PANTALLA",AgregarPantallaC);
                    PnThinkpad.put("HUELLA",SeleccionHuellaC);
                    PnThinkpad.put("BIOS",SeleccionBiosC);
                    PnThinkpad.put("OS",AgregarSoC);

                    databaseReference.child("THINKPAD").push().setValue(PnThinkpad);
                    Toast.makeText(ThinkpadA.this, "Guardado exitoso", Toast.LENGTH_SHORT).show();
                    ThinkpadA.this.finish();

                }

            }
        });
        progressDialog = new ProgressDialog(ThinkpadA.this);
        progressDialog.setMessage("Guardando...");
        progressDialog.setCancelable(false);


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}