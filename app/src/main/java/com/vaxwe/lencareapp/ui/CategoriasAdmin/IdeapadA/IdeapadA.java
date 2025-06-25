package com.vaxwe.lencareapp.ui.CategoriasAdmin.IdeapadA;

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

public class IdeapadA extends AppCompatActivity {

    private Window window;

    String Color16 = "#3E6D9C";

    EditText AgregarPnIp,AgregarFamiliaIp,AgregarCpuIp,AgregarGpuIp,AgregarHddIp,AgregarSsdIp,AgregarRamIp,AgregarTecladoIp,AgregarPantallaIp;
    Spinner SelecionPaisIp,SelecionSlocIp,SeleccionHuellaIp,SeleccionBiosIp,AgregarSoIp;
    Button GuardarPnAIp;

    String RutaDataBase = "THINKPAD";

    DatabaseReference databaseReference;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideapad);

        ActionBar actionBar = getSupportActionBar(); //CREAMOS ACTIONBAR
        assert actionBar != null;                    //AFIRMAMOS QUE EL ACTIONBAR NO SEA NULO
        //actionBar.setTitle("Ideapad");         //LE ASIGNAMOS UN TITULO
        actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Ideapad</font>"));

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.window = getWindow();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary1)));
        window.setStatusBarColor(Color.parseColor(Color16));


        AgregarPnIp = findViewById(R.id.AgregarPnIp);
        AgregarFamiliaIp = findViewById(R.id.AgregarFamiliaIp);
        AgregarCpuIp = findViewById(R.id.AgregarCpuIp);
        AgregarGpuIp = findViewById(R.id.AgregarGpuIp);
        AgregarHddIp = findViewById(R.id.AgregarHddIp);
        AgregarSsdIp = findViewById(R.id.AgregarSsdIp);
        AgregarRamIp = findViewById(R.id.AgregarRamIp);
        AgregarTecladoIp = findViewById(R.id.AgregarTecladoIp);
        AgregarPantallaIp = findViewById(R.id.AgregarPantallaIp);

        SelecionPaisIp = findViewById(R.id.SelecionPaisIp);
        SelecionSlocIp = findViewById(R.id.SelecionSlocIp);
        SeleccionHuellaIp = findViewById(R.id.SeleccionHuellaIp);
        SeleccionBiosIp = findViewById(R.id.SeleccionBiosIp);
        AgregarSoIp = findViewById(R.id.AgregarSoIp);

        GuardarPnAIp = findViewById(R.id.GuardarPnAIp);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        progressDialog = new ProgressDialog(IdeapadA.this);


        //ARRAY SELECCION PAIS
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.SeleccionPais, android.R.layout.simple_spinner_item);
        SelecionPaisIp.setAdapter(adapter1);

        //ARRAY SELECCION SLOC
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.SeleccionSloc, android.R.layout.simple_spinner_item);
        SelecionSlocIp.setAdapter(adapter2);

        //ARRAY SELECCION HUELLA
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.Huella, android.R.layout.simple_spinner_item);
        SeleccionHuellaIp.setAdapter(adapter3);

        //ARRAY SELECCION BIOS
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.Bios, android.R.layout.simple_spinner_item);
        SeleccionBiosIp.setAdapter(adapter4);

        //ARRAY SELECCION OS
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this, R.array.Os, android.R.layout.simple_spinner_item);
        AgregarSoIp.setAdapter(adapter5);


        GuardarPnAIp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String SelecionPaisCIp = SelecionPaisIp.getSelectedItem().toString();
                String SelecionSlocCIp = SelecionSlocIp.getSelectedItem().toString();
                String SeleccionHuellaCIp = SeleccionHuellaIp.getSelectedItem().toString();
                String SeleccionBiosCIp = SeleccionBiosIp.getSelectedItem().toString();
                String AgregarSoCIp = AgregarSoIp.getSelectedItem().toString();
                String AgregarPnCIp = AgregarPnIp.getText().toString();
                String AgregarFamiliaCIp = AgregarFamiliaIp.getText().toString();
                String AgregarCpuCIp = AgregarCpuIp.getText().toString();
                String AgregarGpuCIp = AgregarGpuIp.getText().toString();
                String AgregarHddCIp = AgregarHddIp.getText().toString();
                String AgregarSsdCIp = AgregarSsdIp.getText().toString();
                String AgregarRamCIp = AgregarRamIp.getText().toString();
                String AgregarTecladoCIp = AgregarTecladoIp.getText().toString();
                String AgregarPantallaCIp = AgregarPantallaIp.getText().toString();

                if (SelecionPaisCIp.equals("") || SelecionSlocCIp.equals("") || SeleccionHuellaCIp.equals("") || SeleccionBiosCIp.equals("") || AgregarSoCIp.equals("")
                        || AgregarPnCIp.equals("") || AgregarFamiliaCIp.equals("") || AgregarCpuCIp.equals("") || AgregarGpuCIp.equals("") || AgregarHddCIp.equals("")
                        || AgregarSsdCIp.equals("") || AgregarRamCIp.equals("") || AgregarTecladoCIp.equals("") || AgregarPantallaCIp.equals("")){
                    Toast.makeText(IdeapadA.this, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show();
                }
                else{
                    progressDialog.show();
                    Map<String, Object> PnThinkpad = new HashMap<>();
                    PnThinkpad.put("PAIS",SelecionPaisCIp);
                    PnThinkpad.put("SLOC",SelecionSlocCIp);
                    PnThinkpad.put("PN",AgregarPnCIp);
                    PnThinkpad.put("FAMILIA",AgregarFamiliaCIp);
                    PnThinkpad.put("CPU",AgregarCpuCIp);
                    PnThinkpad.put("GPU",AgregarGpuCIp);
                    PnThinkpad.put("HDD",AgregarHddCIp);
                    PnThinkpad.put("SSD",AgregarSsdCIp);
                    PnThinkpad.put("RAM",AgregarRamCIp);
                    PnThinkpad.put("TECLADO",AgregarTecladoCIp);
                    PnThinkpad.put("PANTALLA",AgregarPantallaCIp);
                    PnThinkpad.put("HUELLA",SeleccionHuellaCIp);
                    PnThinkpad.put("BIOS",SeleccionBiosCIp);
                    PnThinkpad.put("OS",AgregarSoCIp);

                    databaseReference.child("IDEAPAD").push().setValue(PnThinkpad);
                    Toast.makeText(IdeapadA.this, "Guardado exitoso", Toast.LENGTH_SHORT).show();
                    IdeapadA.this.finish();

                }

            }
        });
        progressDialog = new ProgressDialog(IdeapadA.this);
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