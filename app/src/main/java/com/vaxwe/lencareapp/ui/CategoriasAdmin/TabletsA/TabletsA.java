package com.vaxwe.lencareapp.ui.CategoriasAdmin.TabletsA;

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

public class TabletsA extends AppCompatActivity {

    private Window window;

    String Color16 = "#3E6D9C";


    EditText AgregarPnT,AgregarFamiliaT,AgregarCpuT,AgregarGpuT,AgregarHddT,AgregarSsdT,AgregarRamT,AgregarTecladoT,AgregarPantallaT;
    Spinner SelecionPaisT,SelecionSlocT,SeleccionHuellaT,SeleccionBiosT,AgregarSoT;
    Button GuardarPnAT;

    String RutaDataBase = "THINKPAD";

    DatabaseReference databaseReference;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablets);

        ActionBar actionBar = getSupportActionBar(); //CREAMOS ACTIONBAR
        assert actionBar != null;                    //AFIRMAMOS QUE EL ACTIONBAR NO SEA NULO
        //actionBar.setTitle("Tablets");         //LE ASIGNAMOS UN TITULO
        actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Tablets</font>"));

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.window = getWindow();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary1)));
        window.setStatusBarColor(Color.parseColor(Color16));



    AgregarPnT = findViewById(R.id.AgregarPnT);
    AgregarFamiliaT = findViewById(R.id.AgregarFamiliaT);
    AgregarCpuT = findViewById(R.id.AgregarCpuT);
    AgregarGpuT = findViewById(R.id.AgregarGpuT);
    AgregarHddT = findViewById(R.id.AgregarHddT);
    AgregarSsdT = findViewById(R.id.AgregarSsdT);
    AgregarRamT = findViewById(R.id.AgregarRamT);
    AgregarTecladoT = findViewById(R.id.AgregarTecladoT);
    AgregarPantallaT = findViewById(R.id.AgregarPantallaT);

    SelecionPaisT = findViewById(R.id.SelecionPaisT);
    SelecionSlocT = findViewById(R.id.SelecionSlocT);
    SeleccionHuellaT = findViewById(R.id.SeleccionHuellaT);
    SeleccionBiosT = findViewById(R.id.SeleccionBiosT);
    AgregarSoT = findViewById(R.id.AgregarSoT);

    GuardarPnAT = findViewById(R.id.GuardarPnAT);

    databaseReference = FirebaseDatabase.getInstance().getReference();
    progressDialog = new ProgressDialog(TabletsA.this);



    //ARRAY SELECCION PAIS
    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.SeleccionPais, android.R.layout.simple_spinner_item);
        SelecionPaisT.setAdapter(adapter1);

    //ARRAY SELECCION SLOC
    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.SeleccionSloc, android.R.layout.simple_spinner_item);
        SelecionSlocT.setAdapter(adapter2);

    //ARRAY SELECCION HUELLA
    ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.Huella, android.R.layout.simple_spinner_item);
        SeleccionHuellaT.setAdapter(adapter3);

    //ARRAY SELECCION BIOS
    ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.Bios, android.R.layout.simple_spinner_item);
        SeleccionBiosT.setAdapter(adapter4);

    //ARRAY SELECCION OS
    ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this, R.array.Os, android.R.layout.simple_spinner_item);
        AgregarSoT.setAdapter(adapter5);


        GuardarPnAT.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String SelecionPaisCT = SelecionPaisT.getSelectedItem().toString();
            String SelecionSlocCT = SelecionSlocT.getSelectedItem().toString();
            String SeleccionHuellaCT = SeleccionHuellaT.getSelectedItem().toString();
            String SeleccionBiosCT = SeleccionBiosT.getSelectedItem().toString();
            String AgregarSoCT = AgregarSoT.getSelectedItem().toString();
            String AgregarPnCT = AgregarPnT.getText().toString();
            String AgregarFamiliaCT = AgregarFamiliaT.getText().toString();
            String AgregarCpuCT = AgregarCpuT.getText().toString();
            String AgregarGpuCT = AgregarGpuT.getText().toString();
            String AgregarHddCT = AgregarHddT.getText().toString();
            String AgregarSsdCT = AgregarSsdT.getText().toString();
            String AgregarRamCT = AgregarRamT.getText().toString();
            String AgregarTecladoCT = AgregarTecladoT.getText().toString();
            String AgregarPantallaCT = AgregarPantallaT.getText().toString();

            if (SelecionPaisCT.equals("") || SelecionSlocCT.equals("") || SeleccionHuellaCT.equals("") || SeleccionBiosCT.equals("") || AgregarSoCT.equals("")
                    || AgregarPnCT.equals("") || AgregarFamiliaCT.equals("") || AgregarCpuCT.equals("") || AgregarGpuCT.equals("") || AgregarHddCT.equals("")
                    || AgregarSsdCT.equals("") || AgregarRamCT.equals("") || AgregarTecladoCT.equals("") || AgregarPantallaCT.equals("")){
                Toast.makeText(TabletsA.this, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show();
            }
            else{
                progressDialog.show();
                Map<String, Object> PnThinkpad = new HashMap<>();
                PnThinkpad.put("PAIS",SelecionPaisCT);
                PnThinkpad.put("SLOC",SelecionSlocCT);
                PnThinkpad.put("PN",AgregarPnCT);
                PnThinkpad.put("FAMILIA",AgregarFamiliaCT);
                PnThinkpad.put("CPU",AgregarCpuCT);
                PnThinkpad.put("GPU",AgregarGpuCT);
                PnThinkpad.put("HDD",AgregarHddCT);
                PnThinkpad.put("SSD",AgregarSsdCT);
                PnThinkpad.put("RAM",AgregarRamCT);
                PnThinkpad.put("TECLADO",AgregarTecladoCT);
                PnThinkpad.put("PANTALLA",AgregarPantallaCT);
                PnThinkpad.put("HUELLA",SeleccionHuellaCT);
                PnThinkpad.put("BIOS",SeleccionBiosCT);
                PnThinkpad.put("OS",AgregarSoCT);

                databaseReference.child("TABLETS").push().setValue(PnThinkpad);
                Toast.makeText(TabletsA.this, "Guardado exitoso", Toast.LENGTH_SHORT).show();
                TabletsA.this.finish();

            }

        }
    });
    progressDialog = new ProgressDialog(TabletsA.this);
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