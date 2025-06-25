package com.vaxwe.lencareapp.ui.CategoriasAdmin.MonitoresA;

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

public class MonitoresA extends AppCompatActivity {

    private Window window;

    String Color16 = "#3E6D9C";

    EditText AgregarPnM,AgregarFamiliaM,AgregarPantallaM;
    Spinner SelecionPaisM,SelecionSlocM,SeleccionHuellaM,SeleccionBiosM,AgregarSoM,SeleccionCpuM,SeleccionGpuM,SeleccionHddM,
            SeleccionSsdM,SeleccionRamM,SeleccionTecladoM;
    Button GuardarPnAM;

    String RutaDataBase = "THINKPAD";

    DatabaseReference databaseReference;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitores);

        ActionBar actionBar = getSupportActionBar(); //CREAMOS ACTIONBAR
        assert actionBar != null;                    //AFIRMAMOS QUE EL ACTIONBAR NO SEA NULO
        //actionBar.setTitle("Monitores");         //LE ASIGNAMOS UN TITULO
        actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Monitores</font>"));

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.window = getWindow();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary1)));
        window.setStatusBarColor(Color.parseColor(Color16));

        AgregarPnM = findViewById(R.id.AgregarPnM);
        AgregarFamiliaM = findViewById(R.id.AgregarFamiliaM);
        AgregarPantallaM = findViewById(R.id.AgregarPantallaM);

        SeleccionRamM = findViewById(R.id.SeleccionRamM);
        SeleccionTecladoM = findViewById(R.id.SeleccionTecladoM);
        SelecionPaisM = findViewById(R.id.SelecionPaisM);
        SelecionSlocM = findViewById(R.id.SelecionSlocM);
        SeleccionHuellaM = findViewById(R.id.SeleccionHuellaM);
        SeleccionBiosM = findViewById(R.id.SeleccionBiosM);
        SeleccionCpuM = findViewById(R.id.SeleccionCpuM);
        SeleccionGpuM = findViewById(R.id.SeleccionGpuM);
        SeleccionHddM = findViewById(R.id.SeleccionHddM);
        SeleccionSsdM = findViewById(R.id.SeleccionSsdM);
        AgregarSoM = findViewById(R.id.AgregarSoM);

        GuardarPnAM = findViewById(R.id.GuardarPnAM);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        progressDialog = new ProgressDialog(MonitoresA.this);




        //ARRAY SELECCION PAIS
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.SeleccionPais, android.R.layout.simple_spinner_item);
        SelecionPaisM.setAdapter(adapter1);

        //ARRAY SELECCION SLOC
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.SeleccionSloc, android.R.layout.simple_spinner_item);
        SelecionSlocM.setAdapter(adapter2);

        //ARRAY SELECCION HUELLA
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.Huella, android.R.layout.simple_spinner_item);
        SeleccionHuellaM.setAdapter(adapter3);

        //ARRAY SELECCION BIOS
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.Bios, android.R.layout.simple_spinner_item);
        SeleccionBiosM.setAdapter(adapter4);

        //ARRAY SELECCION OS
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this, R.array.Os, android.R.layout.simple_spinner_item);
        AgregarSoM.setAdapter(adapter5);

        //ARRAY SELECCION CPU
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this, R.array.cpu, android.R.layout.simple_spinner_item);
        SeleccionCpuM.setAdapter(adapter6);

        //ARRAY SELECCION GPU
        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(this, R.array.gpu, android.R.layout.simple_spinner_item);
        SeleccionGpuM.setAdapter(adapter7);

        //ARRAY SELECCION HDD
        ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(this, R.array.hdd, android.R.layout.simple_spinner_item);
        SeleccionHddM.setAdapter(adapter8);

        //ARRAY SELECCION SSD
        ArrayAdapter<CharSequence> adapter9 = ArrayAdapter.createFromResource(this, R.array.ssd, android.R.layout.simple_spinner_item);
        SeleccionSsdM.setAdapter(adapter9);

        //ARRAY SELECCION RAM
        ArrayAdapter<CharSequence> adapter10 = ArrayAdapter.createFromResource(this, R.array.ram, android.R.layout.simple_spinner_item);
        SeleccionRamM.setAdapter(adapter10);

        //ARRAY SELECCION TECLADO
        ArrayAdapter<CharSequence> adapter11 = ArrayAdapter.createFromResource(this, R.array.teclado, android.R.layout.simple_spinner_item);
        SeleccionTecladoM.setAdapter(adapter11);


        GuardarPnAM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String SelecionPaisCM = SelecionPaisM.getSelectedItem().toString();
                String SelecionSlocCM = SelecionSlocM.getSelectedItem().toString();
                String SeleccionHuellaCM = SeleccionHuellaM.getSelectedItem().toString();
                String SeleccionBiosCM = SeleccionBiosM.getSelectedItem().toString();
                String AgregarSoCM = AgregarSoM.getSelectedItem().toString();
                String AgregarPnCM = AgregarPnM.getText().toString();
                String AgregarPantallaCM = AgregarPantallaM.getText().toString();
                String AgregarFamiliaCM = AgregarFamiliaM.getText().toString();
                String SeleccionCpuCM = SeleccionCpuM.getSelectedItem().toString();
                String SeleccionGpuCM = SeleccionGpuM.getSelectedItem().toString();
                String SeleccionHddCM = SeleccionHddM.getSelectedItem().toString();
                String SeleccionSsdCM = SeleccionSsdM.getSelectedItem().toString();
                String SeleccionRamCM = SeleccionRamM.getSelectedItem().toString();
                String SeleccionTecladoCM = SeleccionTecladoM.getSelectedItem().toString();


                if (SelecionPaisCM.equals("") || SelecionSlocCM.equals("") || SeleccionHuellaCM.equals("") || SeleccionBiosCM.equals("") || AgregarSoCM.equals("")
                        || AgregarPnCM.equals("") || AgregarFamiliaCM.equals("") || SeleccionCpuCM.equals("") || SeleccionGpuCM.equals("") || SeleccionHddCM.equals("")
                        || SeleccionSsdCM.equals("") || SeleccionRamCM.equals("") || SeleccionTecladoCM.equals("") || AgregarPantallaCM.equals("")){
                    Toast.makeText(MonitoresA.this, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show();
                }
                else{
                    progressDialog.show();
                    Map<String, Object> PnThinkpad = new HashMap<>();
                    PnThinkpad.put("PAIS",SelecionPaisCM);
                    PnThinkpad.put("SLOC",SelecionSlocCM);
                    PnThinkpad.put("PN",AgregarPnCM);
                    PnThinkpad.put("FAMILIA",AgregarFamiliaCM);
                    PnThinkpad.put("CPU",SeleccionCpuCM);
                    PnThinkpad.put("GPU",SeleccionGpuCM);
                    PnThinkpad.put("HDD",SeleccionHddCM);
                    PnThinkpad.put("SSD",SeleccionSsdCM);
                    PnThinkpad.put("RAM",SeleccionRamCM);
                    PnThinkpad.put("TECLADO",SeleccionTecladoCM);
                    PnThinkpad.put("PANTALLA",AgregarPantallaCM);
                    PnThinkpad.put("HUELLA",SeleccionHuellaCM);
                    PnThinkpad.put("BIOS",SeleccionBiosCM);
                    PnThinkpad.put("OS",AgregarSoCM);

                    databaseReference.child("MONITORES").push().setValue(PnThinkpad);
                    Toast.makeText(MonitoresA.this, "Guardado exitoso", Toast.LENGTH_SHORT).show();
                    MonitoresA.this.finish();

                }

            }
        });
        progressDialog = new ProgressDialog(MonitoresA.this);
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