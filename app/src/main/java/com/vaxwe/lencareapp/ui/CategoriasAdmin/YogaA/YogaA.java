package com.vaxwe.lencareapp.ui.CategoriasAdmin.YogaA;

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

public class YogaA extends AppCompatActivity {

    private Window window;

    String Color16 = "#3E6D9C";


    EditText AgregarPnY,AgregarFamiliaY,AgregarCpuY,AgregarGpuY,AgregarHddY,AgregarSsdY,AgregarRamY,AgregarTecladoY,AgregarPantallaY;
    Spinner SelecionPaisY,SelecionSlocY,SeleccionHuellaY,SeleccionBiosY,AgregarSoY;
    Button GuardarPnAY;

    String RutaDataBase = "THINKPAD";

    DatabaseReference databaseReference;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga);


        ActionBar actionBar = getSupportActionBar(); //CREAMOS ACTIONBAR
        assert actionBar != null;                    //AFIRMAMOS QUE EL ACTIONBAR NO SEA NULO
        //actionBar.setTitle("Yoga");         //LE ASIGNAMOS UN TITULO
        actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Yoga</font>"));

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.window = getWindow();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary1)));
        window.setStatusBarColor(Color.parseColor(Color16));


        AgregarPnY = findViewById(R.id.AgregarPnY);
        AgregarFamiliaY = findViewById(R.id.AgregarFamiliaY);
        AgregarCpuY = findViewById(R.id.AgregarCpuY);
        AgregarGpuY = findViewById(R.id.AgregarGpuY);
        AgregarHddY = findViewById(R.id.AgregarHddY);
        AgregarSsdY = findViewById(R.id.AgregarSsdY);
        AgregarRamY = findViewById(R.id.AgregarRamY);
        AgregarTecladoY = findViewById(R.id.AgregarTecladoY);
        AgregarPantallaY = findViewById(R.id.AgregarPantallaY);

        SelecionPaisY = findViewById(R.id.SelecionPaisY);
        SelecionSlocY = findViewById(R.id.SelecionSlocY);
        SeleccionHuellaY = findViewById(R.id.SeleccionHuellaY);
        SeleccionBiosY = findViewById(R.id.SeleccionBiosY);
        AgregarSoY = findViewById(R.id.AgregarSoY);

        GuardarPnAY = findViewById(R.id.GuardarPnAY);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        progressDialog = new ProgressDialog(YogaA.this);




        //ARRAY SELECCION PAIS
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.SeleccionPais, android.R.layout.simple_spinner_item);
        SelecionPaisY.setAdapter(adapter1);

        //ARRAY SELECCION SLOC
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.SeleccionSloc, android.R.layout.simple_spinner_item);
        SelecionSlocY.setAdapter(adapter2);

        //ARRAY SELECCION HUELLA
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.Huella, android.R.layout.simple_spinner_item);
        SeleccionHuellaY.setAdapter(adapter3);

        //ARRAY SELECCION BIOS
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.Bios, android.R.layout.simple_spinner_item);
        SeleccionBiosY.setAdapter(adapter4);

        //ARRAY SELECCION OS
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this, R.array.Os, android.R.layout.simple_spinner_item);
        AgregarSoY.setAdapter(adapter5);


        GuardarPnAY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String SelecionPaisCY = SelecionPaisY.getSelectedItem().toString();
                String SelecionSlocCY = SelecionSlocY.getSelectedItem().toString();
                String SeleccionHuellaCY = SeleccionHuellaY.getSelectedItem().toString();
                String SeleccionBiosCY = SeleccionBiosY.getSelectedItem().toString();
                String AgregarSoCY = AgregarSoY.getSelectedItem().toString();
                String AgregarPnCY = AgregarPnY.getText().toString();
                String AgregarFamiliaCY = AgregarFamiliaY.getText().toString();
                String AgregarCpuCY = AgregarCpuY.getText().toString();
                String AgregarGpuCY = AgregarGpuY.getText().toString();
                String AgregarHddCY = AgregarHddY.getText().toString();
                String AgregarSsdCY = AgregarSsdY.getText().toString();
                String AgregarRamCY = AgregarRamY.getText().toString();
                String AgregarTecladoCY = AgregarTecladoY.getText().toString();
                String AgregarPantallaCY = AgregarPantallaY.getText().toString();

                if (SelecionPaisCY.equals("") || SelecionSlocCY.equals("") || SeleccionHuellaCY.equals("") || SeleccionBiosCY.equals("") || AgregarSoCY.equals("")
                        || AgregarPnCY.equals("") || AgregarFamiliaCY.equals("") || AgregarCpuCY.equals("") || AgregarGpuCY.equals("") || AgregarHddCY.equals("")
                        || AgregarSsdCY.equals("") || AgregarRamCY.equals("") || AgregarTecladoCY.equals("") || AgregarPantallaCY.equals("")){
                    Toast.makeText(YogaA.this, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show();
                }
                else{
                    progressDialog.show();
                    Map<String, Object> PnThinkpad = new HashMap<>();
                    PnThinkpad.put("PAIS",SelecionPaisCY);
                    PnThinkpad.put("SLOC",SelecionSlocCY);
                    PnThinkpad.put("PN",AgregarPnCY);
                    PnThinkpad.put("FAMILIA",AgregarFamiliaCY);
                    PnThinkpad.put("CPU",AgregarCpuCY);
                    PnThinkpad.put("GPU",AgregarGpuCY);
                    PnThinkpad.put("HDD",AgregarHddCY);
                    PnThinkpad.put("SSD",AgregarSsdCY);
                    PnThinkpad.put("RAM",AgregarRamCY);
                    PnThinkpad.put("TECLADO",AgregarTecladoCY);
                    PnThinkpad.put("PANTALLA",AgregarPantallaCY);
                    PnThinkpad.put("HUELLA",SeleccionHuellaCY);
                    PnThinkpad.put("BIOS",SeleccionBiosCY);
                    PnThinkpad.put("OS",AgregarSoCY);

                    databaseReference.child("YOGA").push().setValue(PnThinkpad);
                    Toast.makeText(YogaA.this, "Guardado exitoso", Toast.LENGTH_SHORT).show();
                    YogaA.this.finish();

                }

            }
        });
        progressDialog = new ProgressDialog(YogaA.this);
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