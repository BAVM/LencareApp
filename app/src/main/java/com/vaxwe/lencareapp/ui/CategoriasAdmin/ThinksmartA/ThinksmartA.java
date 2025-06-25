package com.vaxwe.lencareapp.ui.CategoriasAdmin.ThinksmartA;

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

public class ThinksmartA extends AppCompatActivity {

    private Window window;

    String Color16 = "#3E6D9C";


    EditText AgregarPnTs,AgregarFamiliaTs,AgregarCpuTs,AgregarGpuTs,AgregarHddTs,AgregarSsdTs,AgregarRamTs,AgregarTecladoTs,AgregarPantallaTs;
    Spinner SelecionPaisTs,SelecionSlocTs,SeleccionHuellaTs,SeleccionBiosTs,AgregarSoTs;
    Button GuardarPnATs;

    String RutaDataBase = "THINKPAD";

    DatabaseReference databaseReference;
    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thinksmart);


        ActionBar actionBar = getSupportActionBar(); //CREAMOS ACTIONBAR
        assert actionBar != null;                    //AFIRMAMOS QUE EL ACTIONBAR NO SEA NULO
        //actionBar.setTitle("Thinksmart");         //LE ASIGNAMOS UN TITULO
        actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Thinksmart</font>"));

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.window = getWindow();

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary1)));
        window.setStatusBarColor(Color.parseColor(Color16));


        AgregarPnTs = findViewById(R.id.AgregarPnTs);
        AgregarFamiliaTs = findViewById(R.id.AgregarFamiliaTs);
        AgregarCpuTs = findViewById(R.id.AgregarCpuTs);
        AgregarGpuTs = findViewById(R.id.AgregarGpuTs);
        AgregarHddTs = findViewById(R.id.AgregarHddTs);
        AgregarSsdTs = findViewById(R.id.AgregarSsdTs);
        AgregarRamTs = findViewById(R.id.AgregarRamTs);
        AgregarTecladoTs = findViewById(R.id.AgregarTecladoTs);
        AgregarPantallaTs = findViewById(R.id.AgregarPantallaTs);

        SelecionPaisTs = findViewById(R.id.SelecionPaisTs);
        SelecionSlocTs = findViewById(R.id.SelecionSlocTs);
        SeleccionHuellaTs = findViewById(R.id.SeleccionHuellaTs);
        SeleccionBiosTs = findViewById(R.id.SeleccionBiosTs);
        AgregarSoTs = findViewById(R.id.AgregarSoTs);

        GuardarPnATs = findViewById(R.id.GuardarPnATs);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        progressDialog = new ProgressDialog(ThinksmartA.this);




        //ARRAY SELECCION PAIS
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.SeleccionPais, android.R.layout.simple_spinner_item);
        SelecionPaisTs.setAdapter(adapter1);

        //ARRAY SELECCION SLOC
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.SeleccionSloc, android.R.layout.simple_spinner_item);
        SelecionSlocTs.setAdapter(adapter2);

        //ARRAY SELECCION HUELLA
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.Huella, android.R.layout.simple_spinner_item);
        SeleccionHuellaTs.setAdapter(adapter3);

        //ARRAY SELECCION BIOS
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.Bios, android.R.layout.simple_spinner_item);
        SeleccionBiosTs.setAdapter(adapter4);

        //ARRAY SELECCION OS
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this, R.array.Os, android.R.layout.simple_spinner_item);
        AgregarSoTs.setAdapter(adapter5);


        GuardarPnATs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String SelecionPaisCTs = SelecionPaisTs.getSelectedItem().toString();
                String SelecionSlocCTs = SelecionSlocTs.getSelectedItem().toString();
                String SeleccionHuellaCTs = SeleccionHuellaTs.getSelectedItem().toString();
                String SeleccionBiosCTs = SeleccionBiosTs.getSelectedItem().toString();
                String AgregarSoCTs = AgregarSoTs.getSelectedItem().toString();
                String AgregarPnCTs = AgregarPnTs.getText().toString();
                String AgregarFamiliaCTs = AgregarFamiliaTs.getText().toString();
                String AgregarCpuCTs = AgregarCpuTs.getText().toString();
                String AgregarGpuCTs = AgregarGpuTs.getText().toString();
                String AgregarHddCTs = AgregarHddTs.getText().toString();
                String AgregarSsdCTs = AgregarSsdTs.getText().toString();
                String AgregarRamCTs = AgregarRamTs.getText().toString();
                String AgregarTecladoCTs = AgregarTecladoTs.getText().toString();
                String AgregarPantallaCTs = AgregarPantallaTs.getText().toString();

                if (SelecionPaisCTs.equals("") || SelecionSlocCTs.equals("") || SeleccionHuellaCTs.equals("") || SeleccionBiosCTs.equals("") || AgregarSoCTs.equals("")
                        || AgregarPnCTs.equals("") || AgregarFamiliaCTs.equals("") || AgregarCpuCTs.equals("") || AgregarGpuCTs.equals("") || AgregarHddCTs.equals("")
                        || AgregarSsdCTs.equals("") || AgregarRamCTs.equals("") || AgregarTecladoCTs.equals("") || AgregarPantallaCTs.equals("")){
                    Toast.makeText(ThinksmartA.this, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show();
                }
                else{
                    progressDialog.show();
                    Map<String, Object> PnThinkpad = new HashMap<>();
                    PnThinkpad.put("PAIS",SelecionPaisCTs);
                    PnThinkpad.put("SLOC",SelecionSlocCTs);
                    PnThinkpad.put("PN",AgregarPnCTs);
                    PnThinkpad.put("FAMILIA",AgregarFamiliaCTs);
                    PnThinkpad.put("CPU",AgregarCpuCTs);
                    PnThinkpad.put("GPU",AgregarGpuCTs);
                    PnThinkpad.put("HDD",AgregarHddCTs);
                    PnThinkpad.put("SSD",AgregarSsdCTs);
                    PnThinkpad.put("RAM",AgregarRamCTs);
                    PnThinkpad.put("TECLADO",AgregarTecladoCTs);
                    PnThinkpad.put("PANTALLA",AgregarPantallaCTs);
                    PnThinkpad.put("HUELLA",SeleccionHuellaCTs);
                    PnThinkpad.put("BIOS",SeleccionBiosCTs);
                    PnThinkpad.put("OS",AgregarSoCTs);

                    databaseReference.child("THINKSMART").push().setValue(PnThinkpad);
                    Toast.makeText(ThinksmartA.this, "Guardado exitoso", Toast.LENGTH_SHORT).show();
                    ThinksmartA.this.finish();

                }

            }
        });
        progressDialog = new ProgressDialog(ThinksmartA.this);
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