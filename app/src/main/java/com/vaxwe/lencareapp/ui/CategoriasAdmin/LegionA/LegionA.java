package com.vaxwe.lencareapp.ui.CategoriasAdmin.LegionA;

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

public class LegionA extends AppCompatActivity {

    private Window window;

    String Color16 = "#3E6D9C";

    EditText AgregarPnL,AgregarFamiliaL,AgregarCpuL,AgregarGpuL,AgregarHddL,AgregarSsdL,AgregarRamL,AgregarTecladoL,AgregarPantallaL;
    Spinner SelecionPaisL,SelecionSlocL,SeleccionHuellaL,SeleccionBiosL,AgregarSoL;
    Button GuardarPnAL;

    String RutaDataBase = "THINKPAD";

    DatabaseReference databaseReference;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legion);

        ActionBar actionBar = getSupportActionBar(); //CREAMOS ACTIONBAR
        assert actionBar != null;                    //AFIRMAMOS QUE EL ACTIONBAR NO SEA NULO
        //actionBar.setTitle("Legion");         //LE ASIGNAMOS UN TITULO
        actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Legion</font>"));

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.window = getWindow();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary1)));
        window.setStatusBarColor(Color.parseColor(Color16));


        AgregarPnL = findViewById(R.id.AgregarPnL);
        AgregarFamiliaL = findViewById(R.id.AgregarFamiliaL);
        AgregarCpuL = findViewById(R.id.AgregarCpuL);
        AgregarGpuL = findViewById(R.id.AgregarGpuL);
        AgregarHddL = findViewById(R.id.AgregarHddL);
        AgregarSsdL = findViewById(R.id.AgregarSsdL);
        AgregarRamL = findViewById(R.id.AgregarRamL);
        AgregarTecladoL = findViewById(R.id.AgregarTecladoL);
        AgregarPantallaL = findViewById(R.id.AgregarPantallaL);

        SelecionPaisL = findViewById(R.id.SelecionPaisL);
        SelecionSlocL = findViewById(R.id.SelecionSlocL);
        SeleccionHuellaL = findViewById(R.id.SeleccionHuellaL);
        SeleccionBiosL = findViewById(R.id.SeleccionBiosL);
        AgregarSoL = findViewById(R.id.AgregarSoL);

        GuardarPnAL = findViewById(R.id.GuardarPnAL);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        progressDialog = new ProgressDialog(LegionA.this);



        //ARRAY SELECCION PAIS
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.SeleccionPais, android.R.layout.simple_spinner_item);
        SelecionPaisL.setAdapter(adapter1);

        //ARRAY SELECCION SLOC
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.SeleccionSloc, android.R.layout.simple_spinner_item);
        SelecionSlocL.setAdapter(adapter2);

        //ARRAY SELECCION HUELLA
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.Huella, android.R.layout.simple_spinner_item);
        SeleccionHuellaL.setAdapter(adapter3);

        //ARRAY SELECCION BIOS
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.Bios, android.R.layout.simple_spinner_item);
        SeleccionBiosL.setAdapter(adapter4);

        //ARRAY SELECCION OS
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this, R.array.Os, android.R.layout.simple_spinner_item);
        AgregarSoL.setAdapter(adapter5);


        GuardarPnAL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String SelecionPaisCL = SelecionPaisL.getSelectedItem().toString();
                String SelecionSlocCL = SelecionSlocL.getSelectedItem().toString();
                String SeleccionHuellaCL = SeleccionHuellaL.getSelectedItem().toString();
                String SeleccionBiosCL = SeleccionBiosL.getSelectedItem().toString();
                String AgregarSoCL = AgregarSoL.getSelectedItem().toString();
                String AgregarPnCL = AgregarPnL.getText().toString();
                String AgregarFamiliaCL = AgregarFamiliaL.getText().toString();
                String AgregarCpuCL = AgregarCpuL.getText().toString();
                String AgregarGpuCL = AgregarGpuL.getText().toString();
                String AgregarHddCL = AgregarHddL.getText().toString();
                String AgregarSsdCL = AgregarSsdL.getText().toString();
                String AgregarRamCL = AgregarRamL.getText().toString();
                String AgregarTecladoCL = AgregarTecladoL.getText().toString();
                String AgregarPantallaCL = AgregarPantallaL.getText().toString();

                if (SelecionPaisCL.equals("") || SelecionSlocCL.equals("") || SeleccionHuellaCL.equals("") || SeleccionBiosCL.equals("") || AgregarSoCL.equals("")
                        || AgregarPnCL.equals("") || AgregarFamiliaCL.equals("") || AgregarCpuCL.equals("") || AgregarGpuCL.equals("") || AgregarHddCL.equals("")
                        || AgregarSsdCL.equals("") || AgregarRamCL.equals("") || AgregarTecladoCL.equals("") || AgregarPantallaCL.equals("")){
                    Toast.makeText(LegionA.this, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show();
                }
                else{
                    progressDialog.show();
                    Map<String, Object> PnThinkpad = new HashMap<>();
                    PnThinkpad.put("PAIS",SelecionPaisCL);
                    PnThinkpad.put("SLOC",SelecionSlocCL);
                    PnThinkpad.put("PN",AgregarPnCL);
                    PnThinkpad.put("FAMILIA",AgregarFamiliaCL);
                    PnThinkpad.put("CPU",AgregarCpuCL);
                    PnThinkpad.put("GPU",AgregarGpuCL);
                    PnThinkpad.put("HDD",AgregarHddCL);
                    PnThinkpad.put("SSD",AgregarSsdCL);
                    PnThinkpad.put("RAM",AgregarRamCL);
                    PnThinkpad.put("TECLADO",AgregarTecladoCL);
                    PnThinkpad.put("PANTALLA",AgregarPantallaCL);
                    PnThinkpad.put("HUELLA",SeleccionHuellaCL);
                    PnThinkpad.put("BIOS",SeleccionBiosCL);
                    PnThinkpad.put("OS",AgregarSoCL);

                    databaseReference.child("LEGION").push().setValue(PnThinkpad);
                    Toast.makeText(LegionA.this, "Guardado exitoso", Toast.LENGTH_SHORT).show();
                    LegionA.this.finish();

                }

            }
        });
        progressDialog = new ProgressDialog(LegionA.this);
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