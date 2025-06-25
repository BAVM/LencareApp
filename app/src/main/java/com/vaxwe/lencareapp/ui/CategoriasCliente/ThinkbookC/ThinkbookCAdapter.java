package com.vaxwe.lencareapp.ui.CategoriasCliente.ThinkbookC;

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

public class ThinkbookCAdapter extends FirebaseRecyclerAdapter<ThinkbookCModo,ThinkbookCAdapter.myViewHolderTB> {


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ThinkbookCAdapter(@NonNull FirebaseRecyclerOptions<ThinkbookCModo> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolderTB holder, int position, @NonNull ThinkbookCModo model) {

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
        holder.BtnVerTBC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DialogPlus dialogPlus = newDialog(holder.PN.getContext()).setContentHolder
                                (new ViewHolder(R.layout.vista_ver_pn_thinkbook)).setExpanded(true, 1700)
                        .setContentBackgroundResource(R.color.color18).create();

                //dialogPlus.show();

                View v = dialogPlus.getHolderView();
                TextView PN = v.findViewById(R.id.txtVerPnTBC);
                TextView FAMILIA = v.findViewById(R.id.txtVerDescripcionTBC);
                TextView PAIS = v.findViewById(R.id.txtVerPaisTBC);
                TextView SLOC = v.findViewById(R.id.txtVerSlocTBC);
                TextView CPU = v.findViewById(R.id.txtVerCpuTBC);
                TextView GPU = v.findViewById(R.id.txtVerGpuTBC);
                TextView HDD = v.findViewById(R.id.txtVerHddTBC);
                TextView SSD = v.findViewById(R.id.txtVerSsdTBC);
                TextView RAM = v.findViewById(R.id.txtVerRamTBC);
                TextView TECLADO = v.findViewById(R.id.txtVerTecladoTBC);
                TextView PANTALLA = v.findViewById(R.id.txtVerPantallaTBC);
                TextView HUELLA = v.findViewById(R.id.txtVerHuellaTBC);
                TextView BIOS = v.findViewById(R.id.txtVerBiosTBC);
                TextView OS = v.findViewById(R.id.txtVerOsTBC);

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
        holder.BtnFavoritoFATB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (compoundButton.isChecked()) {

                    TextView PN = holder.PN.findViewById(R.id.NombrePNTBC);
                    TextView PAIS = holder.PAIS.findViewById(R.id.PaisPNTBC);
                    TextView SLOC = holder.SLOC.findViewById(R.id.SlocPNTBC);
                    TextView FAMILIA = holder.FAMILIA.findViewById(R.id.DescripcionPNTBC);
                    TextView CPU = holder.CPU.findViewById(R.id.CpuPNTBC);
                    TextView GPU = holder.GPU.findViewById(R.id.GpuPNTBC);
                    TextView HDD = holder.HDD.findViewById(R.id.HddPNTBC);
                    TextView SSD = holder.SSD.findViewById(R.id.SsdPNTBC);
                    TextView RAM = holder.RAM.findViewById(R.id.RamPNTBC);
                    TextView TECLADO = holder.TECLADO.findViewById(R.id.TecladoPNTBC);
                    TextView PANTALLA = holder.PANTALLA.findViewById(R.id.PantallaPNTBC);
                    TextView HUELLA = holder.HUELLA.findViewById(R.id.HuellaPNTBC);
                    TextView BIOS = holder.BIOS.findViewById(R.id.BiosPNTBC);
                    TextView OS = holder.OS.findViewById(R.id.OsPNTBC);

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
                                    Toast.makeText(holder.BtnFavoritoFATB.getContext(), "PN añadido a favoritos", Toast.LENGTH_SHORT).show();


                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(holder.BtnFavoritoFATB.getContext(), "Error al añadir favorito.", Toast.LENGTH_SHORT).show();

                                }
                            });


                } else {

                    FirebaseDatabase.getInstance().getReference().child("FAVORITOS").child(user.getUid()).child(getRef(position).getKey()).removeValue();
                    Toast.makeText(holder.BtnFavoritoFATB.getContext(), "PN removido de favoritos", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @NonNull
    @Override
    public myViewHolderTB onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_thinkbook_c,parent,false);
        return new ThinkbookCAdapter.myViewHolderTB(view);
    }

    public class myViewHolderTB extends RecyclerView.ViewHolder {

        TextView PAIS,SLOC,PN,FAMILIA,CPU,GPU,HDD,SSD,RAM,TECLADO,PANTALLA,HUELLA,BIOS,OS;
        CheckBox BtnFavoritoFATB;
        Button BtnVerTBC;

        public myViewHolderTB(@NonNull View itemView) {
            super(itemView);

            PAIS = (TextView) itemView.findViewById(R.id.PaisPNTBC);
            SLOC = (TextView) itemView.findViewById(R.id.SlocPNTBC);
            PN = (TextView) itemView.findViewById(R.id.NombrePNTBC);
            FAMILIA = (TextView) itemView.findViewById(R.id.DescripcionPNTBC);
            CPU = (TextView) itemView.findViewById(R.id.CpuPNTBC);
            GPU = (TextView) itemView.findViewById(R.id.GpuPNTBC);
            HDD = (TextView) itemView.findViewById(R.id.HddPNTBC);
            SSD = (TextView) itemView.findViewById(R.id.SsdPNTBC);
            RAM = (TextView) itemView.findViewById(R.id.RamPNTBC);
            TECLADO = (TextView) itemView.findViewById(R.id.TecladoPNTBC);
            PANTALLA = (TextView) itemView.findViewById(R.id.PantallaPNTBC);
            HUELLA = (TextView) itemView.findViewById(R.id.HuellaPNTBC);
            BIOS = (TextView) itemView.findViewById(R.id.BiosPNTBC);
            OS = (TextView) itemView.findViewById(R.id.OsPNTBC);

            BtnFavoritoFATB =(CheckBox) itemView.findViewById(R.id.BtnFavoritoFATB);
            BtnVerTBC = (Button) itemView.findViewById(R.id.BtnVerTBC);

        }
    }

}
