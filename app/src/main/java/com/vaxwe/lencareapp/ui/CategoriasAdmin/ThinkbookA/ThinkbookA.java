package com.vaxwe.lencareapp.ui.CategoriasAdmin.ThinkbookA;

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

public class ThinkbookA extends AppCompatActivity {

    private Window window;

    String Color16 = "#3E6D9C";

    EditText AgregarPnTb,AgregarFamiliaTb,AgregarCpuTb,AgregarGpuTb,AgregarHddTb,AgregarSsdTb,AgregarRamTb,AgregarTecladoTb,AgregarPantallaTb;
    Spinner SelecionPaisTb,SelecionSlocTb,SeleccionHuellaTb,SeleccionBiosTb,AgregarSoTb;
    Button GuardarPnATb;

    String RutaDataBase = "THINKPAD";

    DatabaseReference databaseReference;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thinkbook);


        ActionBar actionBar = getSupportActionBar(); //CREAMOS ACTIONBAR
        assert actionBar != null;                    //AFIRMAMOS QUE EL ACTIONBAR NO SEA NULO
        //actionBar.setTitle("Thinkbook");         //LE ASIGNAMOS UN TITULO
        actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Thinkbook</font>"));

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.window = getWindow();

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary1)));
        window.setStatusBarColor(Color.parseColor(Color16));


        AgregarPnTb = findViewById(R.id.AgregarPnTb);
        AgregarFamiliaTb = findViewById(R.id.AgregarFamiliaTb);
        AgregarCpuTb = findViewById(R.id.AgregarCpuTb);
        AgregarGpuTb = findViewById(R.id.AgregarGpuTb);
        AgregarHddTb = findViewById(R.id.AgregarHddTb);
        AgregarSsdTb = findViewById(R.id.AgregarSsdTb);
        AgregarRamTb = findViewById(R.id.AgregarRamTb);
        AgregarTecladoTb = findViewById(R.id.AgregarTecladoTb);
        AgregarPantallaTb = findViewById(R.id.AgregarPantallaTb);

        SelecionPaisTb = findViewById(R.id.SelecionPaisTb);
        SelecionSlocTb = findViewById(R.id.SelecionSlocTb);
        SeleccionHuellaTb = findViewById(R.id.SeleccionHuellaTb);
        SeleccionBiosTb = findViewById(R.id.SeleccionBiosTb);
        AgregarSoTb = findViewById(R.id.AgregarSoTb);

        GuardarPnATb = findViewById(R.id.GuardarPnATb);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        progressDialog = new ProgressDialog(ThinkbookA.this);



        //ARRAY SELECCION PAIS
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.SeleccionPais, android.R.layout.simple_spinner_item);
        SelecionPaisTb.setAdapter(adapter1);

        //ARRAY SELECCION SLOC
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.SeleccionSloc, android.R.layout.simple_spinner_item);
        SelecionSlocTb.setAdapter(adapter2);

        //ARRAY SELECCION HUELLA
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.Huella, android.R.layout.simple_spinner_item);
        SeleccionHuellaTb.setAdapter(adapter3);

        //ARRAY SELECCION BIOS
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.Bios, android.R.layout.simple_spinner_item);
        SeleccionBiosTb.setAdapter(adapter4);

        //ARRAY SELECCION OS
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this, R.array.Os, android.R.layout.simple_spinner_item);
        AgregarSoTb.setAdapter(adapter5);


        GuardarPnATb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String SelecionPaisCTb = SelecionPaisTb.getSelectedItem().toString();
                String SelecionSlocCTb = SelecionSlocTb.getSelectedItem().toString();
                String SeleccionHuellaCTb = SeleccionHuellaTb.getSelectedItem().toString();
                String SeleccionBiosCTb = SeleccionBiosTb.getSelectedItem().toString();
                String AgregarSoCTb = AgregarSoTb.getSelectedItem().toString();
                String AgregarPnCTb = AgregarPnTb.getText().toString();
                String AgregarFamiliaCTb = AgregarFamiliaTb.getText().toString();
                String AgregarCpuCTb = AgregarCpuTb.getText().toString();
                String AgregarGpuCTb = AgregarGpuTb.getText().toString();
                String AgregarHddCTb = AgregarHddTb.getText().toString();
                String AgregarSsdCTb = AgregarSsdTb.getText().toString();
                String AgregarRamCTb = AgregarRamTb.getText().toString();
                String AgregarTecladoCTb = AgregarTecladoTb.getText().toString();
                String AgregarPantallaCTb = AgregarPantallaTb.getText().toString();

                if (SelecionPaisCTb.equals("") || SelecionSlocCTb.equals("") || SeleccionHuellaCTb.equals("") || SeleccionBiosCTb.equals("") || AgregarSoCTb.equals("")
                        || AgregarPnCTb.equals("") || AgregarFamiliaCTb.equals("") || AgregarCpuCTb.equals("") || AgregarGpuCTb.equals("") || AgregarHddCTb.equals("")
                        || AgregarSsdCTb.equals("") || AgregarRamCTb.equals("") || AgregarTecladoCTb.equals("") || AgregarPantallaCTb.equals("")){
                    Toast.makeText(ThinkbookA.this, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show();
                }
                else{
                    progressDialog.show();
                    Map<String, Object> PnThinkpad = new HashMap<>();
                    PnThinkpad.put("PAIS",SelecionPaisCTb);
                    PnThinkpad.put("SLOC",SelecionSlocCTb);
                    PnThinkpad.put("PN",AgregarPnCTb);
                    PnThinkpad.put("FAMILIA",AgregarFamiliaCTb);
                    PnThinkpad.put("CPU",AgregarCpuCTb);
                    PnThinkpad.put("GPU",AgregarGpuCTb);
                    PnThinkpad.put("HDD",AgregarHddCTb);
                    PnThinkpad.put("SSD",AgregarSsdCTb);
                    PnThinkpad.put("RAM",AgregarRamCTb);
                    PnThinkpad.put("TECLADO",AgregarTecladoCTb);
                    PnThinkpad.put("PANTALLA",AgregarPantallaCTb);
                    PnThinkpad.put("HUELLA",SeleccionHuellaCTb);
                    PnThinkpad.put("BIOS",SeleccionBiosCTb);
                    PnThinkpad.put("OS",AgregarSoCTb);

                    databaseReference.child("THINKBOOK").push().setValue(PnThinkpad);
                    Toast.makeText(ThinkbookA.this, "Guardado exitoso", Toast.LENGTH_SHORT).show();
                    ThinkbookA.this.finish();

                }

            }
        });
        progressDialog = new ProgressDialog(ThinkbookA.this);
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