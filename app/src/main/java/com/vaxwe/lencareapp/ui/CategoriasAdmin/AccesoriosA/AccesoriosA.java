package com.vaxwe.lencareapp.ui.CategoriasAdmin.AccesoriosA;

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

public class AccesoriosA extends AppCompatActivity {

    private Window window;

    String Color16 = "#3E6D9C";

    EditText AgregarPnACc, AgregarFamiliaACc, AgregarRamACc, AgregarTecladoACc;
    Spinner SeleccionPaisACc, SeleccionSlocACc, SeleccionHuellaACc, SeleccionBiosACc, AgregarSoACc, SeleccionCpuACc, SeleccionGpuACc, SeleccionHddACc,
            SeleccionSsdACc, SeleccionPantallaACc;
    Button GuardarPnAACc;

    String RutaDataBase = "THINKPAD";

    DatabaseReference databaseReference;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accesorios);

        ActionBar actionBar = getSupportActionBar(); //CREAMOS ACTIONBAR
        assert actionBar != null;                    //AFIRMAMOS QUE EL ACTIONBAR NO SEA NULO
        actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Accesorios</font>"));

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.window = getWindow();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary1)));
        window.setStatusBarColor(Color.parseColor(Color16));


        AgregarPnACc = findViewById(R.id.AgregarPnACc);
        AgregarFamiliaACc = findViewById(R.id.AgregarFamiliaACc);
        AgregarRamACc = findViewById(R.id.AgregarRamACc);
        AgregarTecladoACc = findViewById(R.id.AgregarTecladoACc);

        SeleccionPaisACc = findViewById(R.id.SeleccionPaisACc);
        SeleccionSlocACc = findViewById(R.id.SeleccionSlocACc);
        SeleccionHuellaACc = findViewById(R.id.SeleccionHuellaACc);
        SeleccionBiosACc = findViewById(R.id.SeleccionBiosACc);
        SeleccionCpuACc = findViewById(R.id.SeleccionCpuACc);
        SeleccionGpuACc = findViewById(R.id.SeleccionGpuACc);
        SeleccionHddACc = findViewById(R.id.SeleccionHddACc);
        SeleccionSsdACc = findViewById(R.id.SeleccionSsdACc);
        SeleccionPantallaACc = findViewById(R.id.SeleccionPantallaACc);
        AgregarSoACc = findViewById(R.id.AgregarSoACc);

        GuardarPnAACc = findViewById(R.id.GuardarPnAACc);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        progressDialog = new ProgressDialog(AccesoriosA.this);


        //ARRAY SELECCION PAIS
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.SeleccionPais, android.R.layout.simple_spinner_item);
        SeleccionPaisACc.setAdapter(adapter1);

        //ARRAY SELECCION SLOC
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.SeleccionSloc, android.R.layout.simple_spinner_item);
        SeleccionSlocACc.setAdapter(adapter2);

        //ARRAY SELECCION HUELLA
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.Huella, android.R.layout.simple_spinner_item);
        SeleccionHuellaACc.setAdapter(adapter3);

        //ARRAY SELECCION BIOS
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.Bios, android.R.layout.simple_spinner_item);
        SeleccionBiosACc.setAdapter(adapter4);

        //ARRAY SELECCION OS
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this, R.array.Os, android.R.layout.simple_spinner_item);
        AgregarSoACc.setAdapter(adapter5);

        //ARRAY SELECCION CPU
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this, R.array.cpu, android.R.layout.simple_spinner_item);
        SeleccionCpuACc.setAdapter(adapter6);

        //ARRAY SELECCION GPU
        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(this, R.array.gpu, android.R.layout.simple_spinner_item);
        SeleccionGpuACc.setAdapter(adapter7);

        //ARRAY SELECCION HDD
        ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(this, R.array.hdd, android.R.layout.simple_spinner_item);
        SeleccionHddACc.setAdapter(adapter8);

        //ARRAY SELECCION SSD
        ArrayAdapter<CharSequence> adapter9 = ArrayAdapter.createFromResource(this, R.array.ssd, android.R.layout.simple_spinner_item);
        SeleccionSsdACc.setAdapter(adapter9);

        //ARRAY SELECCION PANTALLA
        ArrayAdapter<CharSequence> adapter10 = ArrayAdapter.createFromResource(this, R.array.pantalla, android.R.layout.simple_spinner_item);
        SeleccionPantallaACc.setAdapter(adapter10);


        GuardarPnAACc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String SelecionPaisCACc = SeleccionPaisACc.getSelectedItem().toString();
                String SelecionSlocCACc = SeleccionSlocACc.getSelectedItem().toString();
                String SeleccionHuellaCACc = SeleccionHuellaACc.getSelectedItem().toString();
                String SeleccionBiosCACc = SeleccionBiosACc.getSelectedItem().toString();
                String AgregarSoCACc = AgregarSoACc.getSelectedItem().toString();
                String AgregarPnCACc = AgregarPnACc.getText().toString();
                String SeleccionPantallaCACc = SeleccionPantallaACc.getSelectedItem().toString();
                String AgregarFamiliaCACc = AgregarFamiliaACc.getText().toString();
                String SeleccionCpuCACc = SeleccionCpuACc.getSelectedItem().toString();
                String SeleccionGpuCACc = SeleccionGpuACc.getSelectedItem().toString();
                String SeleccionHddCACc = SeleccionHddACc.getSelectedItem().toString();
                String SeleccionSsdCACc = SeleccionSsdACc.getSelectedItem().toString();
                String AgregarRamCACc = AgregarRamACc.getText().toString();
                String AgregarTecladoCACc = AgregarTecladoACc.getText().toString();


                if (SelecionPaisCACc.equals("") || SelecionSlocCACc.equals("") || SeleccionHuellaCACc.equals("") || SeleccionBiosCACc.equals("") || AgregarSoCACc.equals("")
                        || AgregarPnCACc.equals("") || AgregarFamiliaCACc.equals("") || SeleccionCpuCACc.equals("") || SeleccionGpuCACc.equals("") || SeleccionHddCACc.equals("")
                        || SeleccionSsdCACc.equals("") || AgregarRamCACc.equals("") || AgregarTecladoCACc.equals("") || SeleccionPantallaCACc.equals("")) {
                    Toast.makeText(AccesoriosA.this, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    progressDialog.show();
                    Map<String, Object> PnThinkpad = new HashMap<>();
                    PnThinkpad.put("PAIS", SelecionPaisCACc);
                    PnThinkpad.put("SLOC", SelecionSlocCACc);
                    PnThinkpad.put("PN", AgregarPnCACc);
                    PnThinkpad.put("FAMILIA", AgregarFamiliaCACc);
                    PnThinkpad.put("CPU", SeleccionCpuCACc);
                    PnThinkpad.put("GPU", SeleccionGpuCACc);
                    PnThinkpad.put("HDD", SeleccionHddCACc);
                    PnThinkpad.put("SSD", SeleccionSsdCACc);
                    PnThinkpad.put("RAM", AgregarRamCACc);
                    PnThinkpad.put("TECLADO", AgregarTecladoCACc);
                    PnThinkpad.put("PANTALLA", SeleccionPantallaCACc);
                    PnThinkpad.put("HUELLA", SeleccionHuellaCACc);
                    PnThinkpad.put("BIOS", SeleccionBiosCACc);
                    PnThinkpad.put("OS", AgregarSoCACc);

                    databaseReference.child("ACCESORIOS").push().setValue(PnThinkpad);
                    Toast.makeText(AccesoriosA.this, "Guardado exitoso", Toast.LENGTH_SHORT).show();
                    AccesoriosA.this.finish();

                }

            }
        });
        progressDialog = new ProgressDialog(AccesoriosA.this);
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