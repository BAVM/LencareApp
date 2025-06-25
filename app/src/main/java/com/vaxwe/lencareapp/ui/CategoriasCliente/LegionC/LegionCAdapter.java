package com.vaxwe.lencareapp.ui.CategoriasCliente.LegionC;

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

public class LegionCAdapter extends FirebaseRecyclerAdapter<LegionCModo, LegionCAdapter.myViewHolderLE> {


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public LegionCAdapter(@NonNull FirebaseRecyclerOptions<LegionCModo> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolderLE holder, int position, @NonNull LegionCModo model) {

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
        holder.BtnVerLEC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DialogPlus dialogPlus = newDialog(holder.PN.getContext()).setContentHolder
                                (new ViewHolder(R.layout.vista_ver_pn_legion)).setExpanded(true, 1700)
                        .setContentBackgroundResource(R.color.color18).create();

                //dialogPlus.show();

                View v = dialogPlus.getHolderView();
                TextView PN = v.findViewById(R.id.txtVerPnLEC);
                TextView FAMILIA = v.findViewById(R.id.txtVerDescripcionLEC);
                TextView PAIS = v.findViewById(R.id.txtVerPaisLEC);
                TextView SLOC = v.findViewById(R.id.txtVerSlocLEC);
                TextView CPU = v.findViewById(R.id.txtVerCpuLEC);
                TextView GPU = v.findViewById(R.id.txtVerGpuLEC);
                TextView HDD = v.findViewById(R.id.txtVerHddLEC);
                TextView SSD = v.findViewById(R.id.txtVerSsdLEC);
                TextView RAM = v.findViewById(R.id.txtVerRamLEC);
                TextView TECLADO = v.findViewById(R.id.txtVerTecladoLEC);
                TextView PANTALLA = v.findViewById(R.id.txtVerPantallaLEC);
                TextView HUELLA = v.findViewById(R.id.txtVerHuellaLEC);
                TextView BIOS = v.findViewById(R.id.txtVerBiosLEC);
                TextView OS = v.findViewById(R.id.txtVerOsLEC);

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
        holder.BtnFavoritoFALE.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (compoundButton.isChecked()) {

                    TextView PN = holder.PN.findViewById(R.id.NombrePNLEC);
                    TextView PAIS = holder.PAIS.findViewById(R.id.PaisPNLEC);
                    TextView SLOC = holder.SLOC.findViewById(R.id.SlocPNLEC);
                    TextView FAMILIA = holder.FAMILIA.findViewById(R.id.DescripcionPNLEC);
                    TextView CPU = holder.CPU.findViewById(R.id.CpuPNLEC);
                    TextView GPU = holder.GPU.findViewById(R.id.GpuPNLEC);
                    TextView HDD = holder.HDD.findViewById(R.id.HddPNLEC);
                    TextView SSD = holder.SSD.findViewById(R.id.SsdPNLEC);
                    TextView RAM = holder.RAM.findViewById(R.id.RamPNLEC);
                    TextView TECLADO = holder.TECLADO.findViewById(R.id.TecladoPNLEC);
                    TextView PANTALLA = holder.PANTALLA.findViewById(R.id.PantallaPNLEC);
                    TextView HUELLA = holder.HUELLA.findViewById(R.id.HuellaPNLEC);
                    TextView BIOS = holder.BIOS.findViewById(R.id.BiosPNLEC);
                    TextView OS = holder.OS.findViewById(R.id.OsPNLEC);

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
                                    Toast.makeText(holder.BtnFavoritoFALE.getContext(), "PN añadido a favoritos", Toast.LENGTH_SHORT).show();


                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(holder.BtnFavoritoFALE.getContext(), "Error al añadir favorito.", Toast.LENGTH_SHORT).show();

                                }
                            });


                } else {

                    FirebaseDatabase.getInstance().getReference().child("FAVORITOS").child(user.getUid()).child(getRef(position).getKey()).removeValue();
                    Toast.makeText(holder.BtnFavoritoFALE.getContext(), "PN removido de favoritos", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @NonNull
    @Override
    public myViewHolderLE onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_legion_c,parent,false);
        return new LegionCAdapter.myViewHolderLE(view);
    }

    public class myViewHolderLE extends RecyclerView.ViewHolder {

        TextView PAIS,SLOC,PN,FAMILIA,CPU,GPU,HDD,SSD,RAM,TECLADO,PANTALLA,HUELLA,BIOS,OS;
        CheckBox BtnFavoritoFALE;
        Button BtnVerLEC;

        public myViewHolderLE(@NonNull View itemView) {
            super(itemView);

            PAIS = (TextView) itemView.findViewById(R.id.PaisPNLEC);
            SLOC = (TextView) itemView.findViewById(R.id.SlocPNLEC);
            PN = (TextView) itemView.findViewById(R.id.NombrePNLEC);
            FAMILIA = (TextView) itemView.findViewById(R.id.DescripcionPNLEC);
            CPU = (TextView) itemView.findViewById(R.id.CpuPNLEC);
            GPU = (TextView) itemView.findViewById(R.id.GpuPNLEC);
            HDD = (TextView) itemView.findViewById(R.id.HddPNLEC);
            SSD = (TextView) itemView.findViewById(R.id.SsdPNLEC);
            RAM = (TextView) itemView.findViewById(R.id.RamPNLEC);
            TECLADO = (TextView) itemView.findViewById(R.id.TecladoPNLEC);
            PANTALLA = (TextView) itemView.findViewById(R.id.PantallaPNLEC);
            HUELLA = (TextView) itemView.findViewById(R.id.HuellaPNLEC);
            BIOS = (TextView) itemView.findViewById(R.id.BiosPNLEC);
            OS = (TextView) itemView.findViewById(R.id.OsPNLEC);

            BtnFavoritoFALE =(CheckBox) itemView.findViewById(R.id.BtnFavoritoFALE);
            BtnVerLEC = (Button) itemView.findViewById(R.id.BtnVerLEC);


        }
    }

}
