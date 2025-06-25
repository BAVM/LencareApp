package com.vaxwe.lencareapp.ui.CategoriasCliente.ThinksmartC;

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

public class ThinksmartCAdapter extends FirebaseRecyclerAdapter<ThinksmartCModo,ThinksmartCAdapter.myViewHolderTS> {


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ThinksmartCAdapter(@NonNull FirebaseRecyclerOptions<ThinksmartCModo> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull myViewHolderTS holder, int position, @NonNull ThinksmartCModo model) {

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
        holder.BtnVerTSC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DialogPlus dialogPlus = newDialog(holder.PN.getContext()).setContentHolder
                                (new ViewHolder(R.layout.vista_ver_pn_thinksmart)).setExpanded(true, 1700)
                        .setContentBackgroundResource(R.color.color18).create();

                //dialogPlus.show();

                View v = dialogPlus.getHolderView();
                TextView PN = v.findViewById(R.id.txtVerPnTSC);
                TextView FAMILIA = v.findViewById(R.id.txtVerDescripcionTSC);
                TextView PAIS = v.findViewById(R.id.txtVerPaisTSC);
                TextView SLOC = v.findViewById(R.id.txtVerSlocTSC);
                TextView CPU = v.findViewById(R.id.txtVerCpuTSC);
                TextView GPU = v.findViewById(R.id.txtVerGpuTSC);
                TextView HDD = v.findViewById(R.id.txtVerHddTSC);
                TextView SSD = v.findViewById(R.id.txtVerSsdTSC);
                TextView RAM = v.findViewById(R.id.txtVerRamTSC);
                TextView TECLADO = v.findViewById(R.id.txtVerTecladoTSC);
                TextView PANTALLA = v.findViewById(R.id.txtVerPantallaTSC);
                TextView HUELLA = v.findViewById(R.id.txtVerHuellaTSC);
                TextView BIOS = v.findViewById(R.id.txtVerBiosTSC);
                TextView OS = v.findViewById(R.id.txtVerOsTSC);

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
        holder.BtnFavoritoFATS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (compoundButton.isChecked()) {

                    TextView PN = holder.PN.findViewById(R.id.NombrePNTSC);
                    TextView PAIS = holder.PAIS.findViewById(R.id.PaisPNTSC);
                    TextView SLOC = holder.SLOC.findViewById(R.id.SlocPNTSC);
                    TextView FAMILIA = holder.FAMILIA.findViewById(R.id.DescripcionPNTSC);
                    TextView CPU = holder.CPU.findViewById(R.id.CpuPNTSC);
                    TextView GPU = holder.GPU.findViewById(R.id.GpuPNTSC);
                    TextView HDD = holder.HDD.findViewById(R.id.HddPNTSC);
                    TextView SSD = holder.SSD.findViewById(R.id.SsdPNTSC);
                    TextView RAM = holder.RAM.findViewById(R.id.RamPNTSC);
                    TextView TECLADO = holder.TECLADO.findViewById(R.id.TecladoPNTSC);
                    TextView PANTALLA = holder.PANTALLA.findViewById(R.id.PantallaPNTSC);
                    TextView HUELLA = holder.HUELLA.findViewById(R.id.HuellaPNTSC);
                    TextView BIOS = holder.BIOS.findViewById(R.id.BiosPNTSC);
                    TextView OS = holder.OS.findViewById(R.id.OsPNTSC);

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
                                    Toast.makeText(holder.BtnFavoritoFATS.getContext(), "PN añadido a favoritos", Toast.LENGTH_SHORT).show();


                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(holder.BtnFavoritoFATS.getContext(), "Error al añadir favorito.", Toast.LENGTH_SHORT).show();

                                }
                            });


                } else {

                    FirebaseDatabase.getInstance().getReference().child("FAVORITOS").child(user.getUid()).child(getRef(position).getKey()).removeValue();
                    Toast.makeText(holder.BtnFavoritoFATS.getContext(), "PN removido de favoritos", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    @NonNull
    @Override
    public myViewHolderTS onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_thinksmart_c,parent,false);
        return new ThinksmartCAdapter.myViewHolderTS(view);
    }



    public class myViewHolderTS extends RecyclerView.ViewHolder {

        TextView PAIS,SLOC,PN,FAMILIA,CPU,GPU,HDD,SSD,RAM,TECLADO,PANTALLA,HUELLA,BIOS,OS;
        CheckBox BtnFavoritoFATS;
        Button BtnVerTSC;


        public myViewHolderTS(@NonNull View itemView) {
            super(itemView);

            PAIS = (TextView) itemView.findViewById(R.id.PaisPNTSC);
            SLOC = (TextView) itemView.findViewById(R.id.SlocPNTSC);
            PN = (TextView) itemView.findViewById(R.id.NombrePNTSC);
            FAMILIA = (TextView) itemView.findViewById(R.id.DescripcionPNTSC);
            CPU = (TextView) itemView.findViewById(R.id.CpuPNTSC);
            GPU = (TextView) itemView.findViewById(R.id.GpuPNTSC);
            HDD = (TextView) itemView.findViewById(R.id.HddPNTSC);
            SSD = (TextView) itemView.findViewById(R.id.SsdPNTSC);
            RAM = (TextView) itemView.findViewById(R.id.RamPNTSC);
            TECLADO = (TextView) itemView.findViewById(R.id.TecladoPNTSC);
            PANTALLA = (TextView) itemView.findViewById(R.id.PantallaPNTSC);
            HUELLA = (TextView) itemView.findViewById(R.id.HuellaPNTSC);
            BIOS = (TextView) itemView.findViewById(R.id.BiosPNTSC);
            OS = (TextView) itemView.findViewById(R.id.OsPNTSC);

            BtnFavoritoFATS =(CheckBox) itemView.findViewById(R.id.BtnFavoritoFATS);
            BtnVerTSC = (Button) itemView.findViewById(R.id.BtnVerTSC);

        }
    }


}
