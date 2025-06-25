package com.vaxwe.lencareapp.ui.CategoriasAdmin.IdeacentreA;

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

public class IdeacentreA extends AppCompatActivity {

    private Window window;

    String Color16 = "#3E6D9C";

    EditText AgregarPnIc,AgregarFamiliaIc,AgregarCpuIc,AgregarGpuIc,AgregarHddIc,AgregarSsdIc,AgregarRamIc,AgregarTecladoIc,AgregarPantallaIc;
    Spinner SelecionPaisIc,SelecionSlocIc,SeleccionHuellaIc,SeleccionBiosIc,AgregarSoIc;
    Button GuardarPnAIc;

    String RutaDataBase = "THINKPAD";

    DatabaseReference databaseReference;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideacentre);

        ActionBar actionBar = getSupportActionBar(); //CREAMOS ACTIONBAR
        assert actionBar != null;                    //AFIRMAMOS QUE EL ACTIONBAR NO SEA NULO
        //actionBar.setTitle("IdeaCentre");         //LE ASIGNAMOS UN TITULO
        actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Ideacentre</font>"));


        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.window = getWindow();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary1)));
        window.setStatusBarColor(Color.parseColor(Color16));

        AgregarPnIc = findViewById(R.id.AgregarPnIc);
        AgregarFamiliaIc = findViewById(R.id.AgregarFamiliaIc);
        AgregarCpuIc = findViewById(R.id.AgregarCpuIc);
        AgregarGpuIc = findViewById(R.id.AgregarGpuIc);
        AgregarHddIc = findViewById(R.id.AgregarHddIc);
        AgregarSsdIc = findViewById(R.id.AgregarSsdIc);
        AgregarRamIc = findViewById(R.id.AgregarRamIc);
        AgregarTecladoIc = findViewById(R.id.AgregarTecladoIc);
        AgregarPantallaIc = findViewById(R.id.AgregarPantallaIc);

        SelecionPaisIc = findViewById(R.id.SelecionPaisIc);
        SelecionSlocIc = findViewById(R.id.SelecionSlocIc);
        SeleccionHuellaIc = findViewById(R.id.SeleccionHuellaIc);
        SeleccionBiosIc = findViewById(R.id.SeleccionBiosIc);
        AgregarSoIc = findViewById(R.id.AgregarSoIc);

        GuardarPnAIc = findViewById(R.id.GuardarPnAIc);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        progressDialog = new ProgressDialog(IdeacentreA.this);



        //ARRAY SELECCION PAIS
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.SeleccionPais, android.R.layout.simple_spinner_item);
        SelecionPaisIc.setAdapter(adapter1);

        //ARRAY SELECCION SLOC
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.SeleccionSloc, android.R.layout.simple_spinner_item);
        SelecionSlocIc.setAdapter(adapter2);

        //ARRAY SELECCION HUELLA
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.Huella, android.R.layout.simple_spinner_item);
        SeleccionHuellaIc.setAdapter(adapter3);

        //ARRAY SELECCION BIOS
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.Bios, android.R.layout.simple_spinner_item);
        SeleccionBiosIc.setAdapter(adapter4);

        //ARRAY SELECCION OS
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this, R.array.Os, android.R.layout.simple_spinner_item);
        AgregarSoIc.setAdapter(adapter5);


        GuardarPnAIc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String SelecionPaisCIc = SelecionPaisIc.getSelectedItem().toString();
                String SelecionSlocCIc = SelecionSlocIc.getSelectedItem().toString();
                String SeleccionHuellaCIc = SeleccionHuellaIc.getSelectedItem().toString();
                String SeleccionBiosCIc = SeleccionBiosIc.getSelectedItem().toString();
                String AgregarSoCIc = AgregarSoIc.getSelectedItem().toString();
                String AgregarPnCIc = AgregarPnIc.getText().toString();
                String AgregarFamiliaCIc = AgregarFamiliaIc.getText().toString();
                String AgregarCpuCIc = AgregarCpuIc.getText().toString();
                String AgregarGpuCIc = AgregarGpuIc.getText().toString();
                String AgregarHddCIc = AgregarHddIc.getText().toString();
                String AgregarSsdCIc = AgregarSsdIc.getText().toString();
                String AgregarRamCIc = AgregarRamIc.getText().toString();
                String AgregarTecladoCIc = AgregarTecladoIc.getText().toString();
                String AgregarPantallaCIc = AgregarPantallaIc.getText().toString();

                if (SelecionPaisCIc.equals("") || SelecionSlocCIc.equals("") || SeleccionHuellaCIc.equals("") || SeleccionBiosCIc.equals("") || AgregarSoCIc.equals("")
                        || AgregarPnCIc.equals("") || AgregarFamiliaCIc.equals("") || AgregarCpuCIc.equals("") || AgregarGpuCIc.equals("") || AgregarHddCIc.equals("")
                        || AgregarSsdCIc.equals("") || AgregarRamCIc.equals("") || AgregarTecladoCIc.equals("") || AgregarPantallaCIc.equals("")){
                    Toast.makeText(IdeacentreA.this, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show();
                }
                else{
                    progressDialog.show();
                    Map<String, Object> PnThinkpad = new HashMap<>();
                    PnThinkpad.put("PAIS",SelecionPaisCIc);
                    PnThinkpad.put("SLOC",SelecionSlocCIc);
                    PnThinkpad.put("PN",AgregarPnCIc);
                    PnThinkpad.put("FAMILIA",AgregarFamiliaCIc);
                    PnThinkpad.put("CPU",AgregarCpuCIc);
                    PnThinkpad.put("GPU",AgregarGpuCIc);
                    PnThinkpad.put("HDD",AgregarHddCIc);
                    PnThinkpad.put("SSD",AgregarSsdCIc);
                    PnThinkpad.put("RAM",AgregarRamCIc);
                    PnThinkpad.put("TECLADO",AgregarTecladoCIc);
                    PnThinkpad.put("PANTALLA",AgregarPantallaCIc);
                    PnThinkpad.put("HUELLA",SeleccionHuellaCIc);
                    PnThinkpad.put("BIOS",SeleccionBiosCIc);
                    PnThinkpad.put("OS",AgregarSoCIc);

                    databaseReference.child("IDEACENTRE").push().setValue(PnThinkpad);
                    Toast.makeText(IdeacentreA.this, "Guardado exitoso", Toast.LENGTH_SHORT).show();
                    IdeacentreA.this.finish();

                }

            }
        });
        progressDialog = new ProgressDialog(IdeacentreA.this);
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