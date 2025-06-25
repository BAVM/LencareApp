package com.vaxwe.lencareapp.ui.CategoriasAdmin.ThinkcentreA;

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

public class ThinkcentreA extends AppCompatActivity {

    private Window window;

    String Color16 = "#3E6D9C";

    EditText AgregarPnTc,AgregarFamiliaTc,AgregarCpuTc,AgregarGpuTc,AgregarHddTc,AgregarSsdTc,AgregarRamTc,AgregarTecladoTc,AgregarPantallaTc;
    Spinner SelecionPaisTc,SelecionSlocTc,SeleccionHuellaTc,SeleccionBiosTc,AgregarSoTc;
    Button GuardarPnATc;

    String RutaDataBase = "THINKCENTRE";

    DatabaseReference databaseReference;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thinkcentre);


        ActionBar actionBar = getSupportActionBar(); //CREAMOS ACTIONBAR
        assert actionBar != null;                    //AFIRMAMOS QUE EL ACTIONBAR NO SEA NULO
        //actionBar.setTitle("ThinkCentre");         //LE ASIGNAMOS UN TITULO
        actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Thinkcentre</font>"));

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.window = getWindow();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary1)));
        window.setStatusBarColor(Color.parseColor(Color16));

        AgregarPnTc = findViewById(R.id.AgregarPnTc);
        AgregarFamiliaTc = findViewById(R.id.AgregarFamiliaTc);
        AgregarCpuTc = findViewById(R.id.AgregarCpuTc);
        AgregarGpuTc = findViewById(R.id.AgregarGpuTc);
        AgregarHddTc = findViewById(R.id.AgregarHddTc);
        AgregarSsdTc = findViewById(R.id.AgregarSsdTc);
        AgregarRamTc = findViewById(R.id.AgregarRamTc);
        AgregarTecladoTc = findViewById(R.id.AgregarTecladoTc);
        AgregarPantallaTc = findViewById(R.id.AgregarPantallaTc);

        SelecionPaisTc = findViewById(R.id.SelecionPaisTc);
        SelecionSlocTc = findViewById(R.id.SelecionSlocTc);
        SeleccionHuellaTc = findViewById(R.id.SeleccionHuellaTc);
        SeleccionBiosTc = findViewById(R.id.SeleccionBiosTc);
        AgregarSoTc = findViewById(R.id.AgregarSoTc);

        GuardarPnATc = findViewById(R.id.GuardarPnATc);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        progressDialog = new ProgressDialog(ThinkcentreA.this);



        //ARRAY SELECCION PAIS
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.SeleccionPais, android.R.layout.simple_spinner_item);
        SelecionPaisTc.setAdapter(adapter1);

        //ARRAY SELECCION SLOC
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.SeleccionSloc, android.R.layout.simple_spinner_item);
        SelecionSlocTc.setAdapter(adapter2);

        //ARRAY SELECCION HUELLA
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.Huella, android.R.layout.simple_spinner_item);
        SeleccionHuellaTc.setAdapter(adapter3);

        //ARRAY SELECCION BIOS
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.Bios, android.R.layout.simple_spinner_item);
        SeleccionBiosTc.setAdapter(adapter4);

        //ARRAY SELECCION OS
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this, R.array.Os, android.R.layout.simple_spinner_item);
        AgregarSoTc.setAdapter(adapter5);


        GuardarPnATc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String SelecionPaisCTc = SelecionPaisTc.getSelectedItem().toString();
                String SelecionSlocCTc = SelecionSlocTc.getSelectedItem().toString();
                String SeleccionHuellaCTc = SeleccionHuellaTc.getSelectedItem().toString();
                String SeleccionBiosCTc = SeleccionBiosTc.getSelectedItem().toString();
                String AgregarSoCTc = AgregarSoTc.getSelectedItem().toString();
                String AgregarPnCTc = AgregarPnTc.getText().toString();
                String AgregarFamiliaCTc = AgregarFamiliaTc.getText().toString();
                String AgregarCpuCTc = AgregarCpuTc.getText().toString();
                String AgregarGpuCTc = AgregarGpuTc.getText().toString();
                String AgregarHddCTc = AgregarHddTc.getText().toString();
                String AgregarSsdCTc = AgregarSsdTc.getText().toString();
                String AgregarRamCTc = AgregarRamTc.getText().toString();
                String AgregarTecladoCTc = AgregarTecladoTc.getText().toString();
                String AgregarPantallaCTc = AgregarPantallaTc.getText().toString();

                if (SelecionPaisCTc.equals("") || SelecionSlocCTc.equals("") || SeleccionHuellaCTc.equals("") || SeleccionBiosCTc.equals("") || AgregarSoCTc.equals("")
                        || AgregarPnCTc.equals("") || AgregarFamiliaCTc.equals("") || AgregarCpuCTc.equals("") || AgregarGpuCTc.equals("") || AgregarHddCTc.equals("")
                        || AgregarSsdCTc.equals("") || AgregarRamCTc.equals("") || AgregarTecladoCTc.equals("") || AgregarPantallaCTc.equals("")){
                    Toast.makeText(ThinkcentreA.this, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show();
                }
                else{
                    progressDialog.show();
                    Map<String, Object> PnThinkpad = new HashMap<>();
                    PnThinkpad.put("PAIS",SelecionPaisCTc);
                    PnThinkpad.put("SLOC",SelecionSlocCTc);
                    PnThinkpad.put("PN",AgregarPnCTc);
                    PnThinkpad.put("FAMILIA",AgregarFamiliaCTc);
                    PnThinkpad.put("CPU",AgregarCpuCTc);
                    PnThinkpad.put("GPU",AgregarGpuCTc);
                    PnThinkpad.put("HDD",AgregarHddCTc);
                    PnThinkpad.put("SSD",AgregarSsdCTc);
                    PnThinkpad.put("RAM",AgregarRamCTc);
                    PnThinkpad.put("TECLADO",AgregarTecladoCTc);
                    PnThinkpad.put("PANTALLA",AgregarPantallaCTc);
                    PnThinkpad.put("HUELLA",SeleccionHuellaCTc);
                    PnThinkpad.put("BIOS",SeleccionBiosCTc);
                    PnThinkpad.put("OS",AgregarSoCTc);

                    databaseReference.child("THINKCENTRE").push().setValue(PnThinkpad);
                    Toast.makeText(ThinkcentreA.this, "Guardado exitoso", Toast.LENGTH_SHORT).show();
                    ThinkcentreA.this.finish();

                }

            }
        });
        progressDialog = new ProgressDialog(ThinkcentreA.this);
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