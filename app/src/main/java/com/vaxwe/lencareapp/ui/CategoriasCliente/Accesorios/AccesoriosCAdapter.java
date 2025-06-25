package com.vaxwe.lencareapp.ui.CategoriasCliente.Accesorios;

import static com.orhanobut.dialogplus.DialogPlus.newDialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.vaxwe.lencareapp.R;

import java.util.HashMap;
import java.util.Map;

public class AccesoriosCAdapter extends FirebaseRecyclerAdapter<AccesoriosCModo, AccesoriosCAdapter.myViewHolderCA> {


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AccesoriosCAdapter(@NonNull FirebaseRecyclerOptions<AccesoriosCModo> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AccesoriosCAdapter.myViewHolderCA holder, int position, @NonNull AccesoriosCModo model) {

        holder.PN.setText(model.getPN());
        holder.PAIS.setText(model.getPAIS());
        holder.SLOC.setText(model.getSLOC());
        holder.FAMILIA.setText(model.getFAMILIA());
        holder.CPU.setText(model.getCPU());
        holder.GPU.setText(model.getGPU());
        holder.HDD.setText(model.getHDD());
        holder.SSD.setText(model.getSSD());
        holder.RAM.setText(model.getRAM());
        holder.TECLADO.setText(model.getTECLADO());
        holder.PANTALLA.setText(model.getPANTALLA());
        holder.HUELLA.setText(model.getHUELLA());
        holder.BIOS.setText(model.getBIOS());
        holder.OS.setText(model.getOS());


        //BOTON VER PN
        holder.BtnVerAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DialogPlus dialogPlus = newDialog(holder.PN.getContext()).setContentHolder
                                (new ViewHolder(R.layout.vista_ver_pn_accesorio)).setExpanded(true, 1700)
                        .setContentBackgroundResource(R.color.color18).create();

                //dialogPlus.show();

                View v = dialogPlus.getHolderView();
                TextView PN = v.findViewById(R.id.txtVerPnAC);
                TextView FAMILIA = v.findViewById(R.id.txtVerDescripcionAC);
                TextView PAIS = v.findViewById(R.id.txtVerPaisAC);
                TextView SLOC = v.findViewById(R.id.txtVerSlocAC);
                TextView CPU = v.findViewById(R.id.txtVerCpuAC);
                TextView GPU = v.findViewById(R.id.txtVerGpuAC);
                TextView HDD = v.findViewById(R.id.txtVerHddAC);
                TextView SSD = v.findViewById(R.id.txtVerSsdAC);
                TextView RAM = v.findViewById(R.id.txtVerRamAC);
                TextView TECLADO = v.findViewById(R.id.txtVerTecladoAC);
                TextView PANTALLA = v.findViewById(R.id.txtVerPantallaAC);
                TextView HUELLA = v.findViewById(R.id.txtVerHuellaAC);
                TextView BIOS = v.findViewById(R.id.txtVerBiosAC);
                TextView OS = v.findViewById(R.id.txtVerOsAC);

                PN.setText(model.getPN());
                FAMILIA.setText(model.getFAMILIA());
                PAIS.setText(model.getPAIS());
                SLOC.setText(model.getSLOC());
                CPU.setText(model.getCPU());
                GPU.setText(model.getGPU());
                HDD.setText(model.getHDD());
                SSD.setText(model.getSSD());
                RAM.setText(model.getRAM());
                TECLADO.setText(model.getTECLADO());
                PANTALLA.setText(model.getPANTALLA());
                HUELLA.setText(model.getHUELLA());
                BIOS.setText(model.getBIOS());
                OS.setText(model.getOS());

                dialogPlus.show();

            }
        });



        //CHECKBOX
        holder.BtnFavoritoFAAC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (compoundButton.isChecked()) {

                    TextView PN = holder.PN.findViewById(R.id.NombrePNACC);
                    TextView PAIS = holder.PAIS.findViewById(R.id.PaisPNACC);
                    TextView SLOC = holder.SLOC.findViewById(R.id.SlocPNACC);
                    TextView FAMILIA = holder.FAMILIA.findViewById(R.id.DescripcionPNACC);
                    TextView CPU = holder.CPU.findViewById(R.id.CpuPNACC);
                    TextView GPU = holder.GPU.findViewById(R.id.GpuPNACC);
                    TextView HDD = holder.HDD.findViewById(R.id.HddPNACC);
                    TextView SSD = holder.SSD.findViewById(R.id.SsdPNACC);
                    TextView RAM = holder.RAM.findViewById(R.id.RamPNACC);
                    TextView TECLADO = holder.TECLADO.findViewById(R.id.TecladoPNACC);
                    TextView PANTALLA = holder.PANTALLA.findViewById(R.id.PantallaPNACC);
                    TextView HUELLA = holder.HUELLA.findViewById(R.id.HuellaPNACC);
                    TextView BIOS = holder.BIOS.findViewById(R.id.BiosPNACC);
                    TextView OS = holder.OS.findViewById(R.id.OsPNACC);

                    Map<String, Object> fav = new HashMap<>();

                    fav.put("PN", PN.getText().toString());
                    fav.put("FAMILIA", FAMILIA.getText().toString());
                    fav.put("PAIS", PAIS.getText().toString());
                    fav.put("SLOC", SLOC.getText().toString());
                    fav.put("CPU", CPU.getText().toString());
                    fav.put("GPU", GPU.getText().toString());
                    fav.put("HDD", HDD.getText().toString());
                    fav.put("SSD", SSD.getText().toString());
                    fav.put("RAM", RAM.getText().toString());
                    fav.put("TECLADO", TECLADO.getText().toString());
                    fav.put("PANTALLA", PANTALLA.getText().toString());
                    fav.put("HUELLA", HUELLA.getText().toString());
                    fav.put("BIOS", BIOS.getText().toString());
                    fav.put("OS", OS.getText().toString());


                    FirebaseDatabase.getInstance().getReference().child("FAVORITOS").child(user.getUid()).child(getRef(position).getKey()).updateChildren(fav)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(holder.BtnFavoritoFAAC.getContext(), "PN añadido a favoritos", Toast.LENGTH_SHORT).show();


                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(holder.BtnFavoritoFAAC.getContext(), "Error al añadir favorito.", Toast.LENGTH_SHORT).show();

                                }
                            });


                } else {

                    FirebaseDatabase.getInstance().getReference().child("FAVORITOS").child(user.getUid()).child(getRef(position).getKey()).removeValue();
                    Toast.makeText(holder.BtnFavoritoFAAC.getContext(), "PN removido de favoritos", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @NonNull
    @Override
    public myViewHolderCA onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_accesorios_c,parent,false);
        return new myViewHolderCA(view);

    }

    static class myViewHolderCA extends RecyclerView.ViewHolder {

        TextView PAIS,SLOC,PN,FAMILIA,CPU,GPU,HDD,SSD,RAM,TECLADO,PANTALLA,HUELLA,BIOS,OS;
        CheckBox BtnFavoritoFAAC;
        Button BtnVerAC;

        public myViewHolderCA(@NonNull View itemView) {
            super(itemView);

            PAIS = (TextView) itemView.findViewById(R.id.PaisPNACC);
            SLOC = (TextView) itemView.findViewById(R.id.SlocPNACC);
            PN = (TextView) itemView.findViewById(R.id.NombrePNACC);
            FAMILIA = (TextView) itemView.findViewById(R.id.DescripcionPNACC);
            CPU = (TextView) itemView.findViewById(R.id.CpuPNACC);
            GPU = (TextView) itemView.findViewById(R.id.GpuPNACC);
            HDD = (TextView) itemView.findViewById(R.id.HddPNACC);
            SSD = (TextView) itemView.findViewById(R.id.SsdPNACC);
            RAM = (TextView) itemView.findViewById(R.id.RamPNACC);
            TECLADO = (TextView) itemView.findViewById(R.id.TecladoPNACC);
            PANTALLA = (TextView) itemView.findViewById(R.id.PantallaPNACC);
            HUELLA = (TextView) itemView.findViewById(R.id.HuellaPNACC);
            BIOS = (TextView) itemView.findViewById(R.id.BiosPNACC);
            OS = (TextView) itemView.findViewById(R.id.OsPNACC);

            BtnFavoritoFAAC =(CheckBox) itemView.findViewById(R.id.BtnFavoritoFAAC);
            BtnVerAC = (Button) itemView.findViewById(R.id.BtnVerAC);




        }
    }

}
