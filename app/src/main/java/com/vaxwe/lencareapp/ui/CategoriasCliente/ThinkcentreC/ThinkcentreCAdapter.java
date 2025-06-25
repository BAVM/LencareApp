package com.vaxwe.lencareapp.ui.CategoriasCliente.ThinkcentreC;

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

public class ThinkcentreCAdapter extends FirebaseRecyclerAdapter<ThinkcentreCModo,ThinkcentreCAdapter.myViewHolderTC> {


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ThinkcentreCAdapter(@NonNull FirebaseRecyclerOptions<ThinkcentreCModo> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolderTC holder, int position, @NonNull ThinkcentreCModo model) {

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
        holder.BtnVerTCC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DialogPlus dialogPlus = newDialog(holder.PN.getContext()).setContentHolder
                                (new ViewHolder(R.layout.vista_ver_pn_thinkcentre)).setExpanded(true, 1700)
                        .setContentBackgroundResource(R.color.color18).create();

                //dialogPlus.show();

                View v = dialogPlus.getHolderView();
                TextView PN = v.findViewById(R.id.txtVerPnTCC);
                TextView FAMILIA = v.findViewById(R.id.txtVerDescripcionTCC);
                TextView PAIS = v.findViewById(R.id.txtVerPaisTCC);
                TextView SLOC = v.findViewById(R.id.txtVerSlocTCC);
                TextView CPU = v.findViewById(R.id.txtVerCpuTCC);
                TextView GPU = v.findViewById(R.id.txtVerGpuTCC);
                TextView HDD = v.findViewById(R.id.txtVerHddTCC);
                TextView SSD = v.findViewById(R.id.txtVerSsdTCC);
                TextView RAM = v.findViewById(R.id.txtVerRamTCC);
                TextView TECLADO = v.findViewById(R.id.txtVerTecladoTCC);
                TextView PANTALLA = v.findViewById(R.id.txtVerPantallaTCC);
                TextView HUELLA = v.findViewById(R.id.txtVerHuellaTCC);
                TextView BIOS = v.findViewById(R.id.txtVerBiosTCC);
                TextView OS = v.findViewById(R.id.txtVerOsTCC);

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
        holder.BtnFavoritoFATC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (compoundButton.isChecked()) {

                    TextView PN = holder.PN.findViewById(R.id.NombrePNTCC);
                    TextView PAIS = holder.PAIS.findViewById(R.id.PaisPNTCC);
                    TextView SLOC = holder.SLOC.findViewById(R.id.SlocPNTCC);
                    TextView FAMILIA = holder.FAMILIA.findViewById(R.id.DescripcionPNTCC);
                    TextView CPU = holder.CPU.findViewById(R.id.CpuPNTCC);
                    TextView GPU = holder.GPU.findViewById(R.id.GpuPNTCC);
                    TextView HDD = holder.HDD.findViewById(R.id.HddPNTCC);
                    TextView SSD = holder.SSD.findViewById(R.id.SsdPNTCC);
                    TextView RAM = holder.RAM.findViewById(R.id.RamPNTCC);
                    TextView TECLADO = holder.TECLADO.findViewById(R.id.TecladoPNTCC);
                    TextView PANTALLA = holder.PANTALLA.findViewById(R.id.PantallaPNTCC);
                    TextView HUELLA = holder.HUELLA.findViewById(R.id.HuellaPNTCC);
                    TextView BIOS = holder.BIOS.findViewById(R.id.BiosPNTCC);
                    TextView OS = holder.OS.findViewById(R.id.OsPNTCC);

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
                                    Toast.makeText(holder.BtnFavoritoFATC.getContext(), "PN añadido a favoritos", Toast.LENGTH_SHORT).show();


                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(holder.BtnFavoritoFATC.getContext(), "Error al añadir favorito.", Toast.LENGTH_SHORT).show();

                                }
                            });


                } else {

                    FirebaseDatabase.getInstance().getReference().child("FAVORITOS").child(user.getUid()).child(getRef(position).getKey()).removeValue();
                    Toast.makeText(holder.BtnFavoritoFATC.getContext(), "PN removido de favoritos", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @NonNull
    @Override
    public myViewHolderTC onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_thinkcentre_c,parent,false);
        return new ThinkcentreCAdapter.myViewHolderTC(view);
    }

    public class myViewHolderTC extends RecyclerView.ViewHolder {
        TextView PAIS,SLOC,PN,FAMILIA,CPU,GPU,HDD,SSD,RAM,TECLADO,PANTALLA,HUELLA,BIOS,OS;
        CheckBox BtnFavoritoFATC;
        Button BtnVerTCC;

        public myViewHolderTC(@NonNull View itemView) {
            super(itemView);

            PAIS = (TextView) itemView.findViewById(R.id.PaisPNTCC);
            SLOC = (TextView) itemView.findViewById(R.id.SlocPNTCC);
            PN = (TextView) itemView.findViewById(R.id.NombrePNTCC);
            FAMILIA = (TextView) itemView.findViewById(R.id.DescripcionPNTCC);
            CPU = (TextView) itemView.findViewById(R.id.CpuPNTCC);
            GPU = (TextView) itemView.findViewById(R.id.GpuPNTCC);
            HDD = (TextView) itemView.findViewById(R.id.HddPNTCC);
            SSD = (TextView) itemView.findViewById(R.id.SsdPNTCC);
            RAM = (TextView) itemView.findViewById(R.id.RamPNTCC);
            TECLADO = (TextView) itemView.findViewById(R.id.TecladoPNTCC);
            PANTALLA = (TextView) itemView.findViewById(R.id.PantallaPNTCC);
            HUELLA = (TextView) itemView.findViewById(R.id.HuellaPNTCC);
            BIOS = (TextView) itemView.findViewById(R.id.BiosPNTCC);
            OS = (TextView) itemView.findViewById(R.id.OsPNTCC);

            BtnFavoritoFATC =(CheckBox) itemView.findViewById(R.id.BtnFavoritoFATC);
            BtnVerTCC = (Button) itemView.findViewById(R.id.BtnVerTCC);


        }
    }

}
