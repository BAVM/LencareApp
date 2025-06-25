package com.vaxwe.lencareapp.ui.CategoriasCliente.TabletsC;

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

public class TabletsCAdapter extends FirebaseRecyclerAdapter<TabletsCModo,TabletsCAdapter.myViewHolderTA> {


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public TabletsCAdapter(@NonNull FirebaseRecyclerOptions<TabletsCModo> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolderTA holder, int position, @NonNull TabletsCModo model) {

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
        holder.BtnVerTAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DialogPlus dialogPlus = newDialog(holder.PN.getContext()).setContentHolder
                                (new ViewHolder(R.layout.vista_ver_pn_tablets)).setExpanded(true, 1700)
                        .setContentBackgroundResource(R.color.color18).create();

                //dialogPlus.show();

                View v = dialogPlus.getHolderView();
                TextView PN = v.findViewById(R.id.txtVerPnTAC);
                TextView FAMILIA = v.findViewById(R.id.txtVerDescripcionTAC);
                TextView PAIS = v.findViewById(R.id.txtVerPaisTAC);
                TextView SLOC = v.findViewById(R.id.txtVerSlocTAC);
                TextView CPU = v.findViewById(R.id.txtVerCpuTAC);
                TextView GPU = v.findViewById(R.id.txtVerGpuTAC);
                TextView HDD = v.findViewById(R.id.txtVerHddTAC);
                TextView SSD = v.findViewById(R.id.txtVerSsdTAC);
                TextView RAM = v.findViewById(R.id.txtVerRamTAC);
                TextView TECLADO = v.findViewById(R.id.txtVerTecladoTAC);
                TextView PANTALLA = v.findViewById(R.id.txtVerPantallaTAC);
                TextView HUELLA = v.findViewById(R.id.txtVerHuellaTAC);
                TextView BIOS = v.findViewById(R.id.txtVerBiosTAC);
                TextView OS = v.findViewById(R.id.txtVerOsTAC);

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
        holder.BtnFavoritoFATA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (compoundButton.isChecked()) {

                    TextView PN = holder.PN.findViewById(R.id.NombrePNTAC);
                    TextView PAIS = holder.PAIS.findViewById(R.id.PaisPNTAC);
                    TextView SLOC = holder.SLOC.findViewById(R.id.SlocPNTAC);
                    TextView FAMILIA = holder.FAMILIA.findViewById(R.id.DescripcionPNTAC);
                    TextView CPU = holder.CPU.findViewById(R.id.CpuPNTAC);
                    TextView GPU = holder.GPU.findViewById(R.id.GpuPNTAC);
                    TextView HDD = holder.HDD.findViewById(R.id.HddPNTAC);
                    TextView SSD = holder.SSD.findViewById(R.id.SsdPNTAC);
                    TextView RAM = holder.RAM.findViewById(R.id.RamPNTAC);
                    TextView TECLADO = holder.TECLADO.findViewById(R.id.TecladoPNTAC);
                    TextView PANTALLA = holder.PANTALLA.findViewById(R.id.PantallaPNTAC);
                    TextView HUELLA = holder.HUELLA.findViewById(R.id.HuellaPNTAC);
                    TextView BIOS = holder.BIOS.findViewById(R.id.BiosPNTAC);
                    TextView OS = holder.OS.findViewById(R.id.OsPNTAC);

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
                                    Toast.makeText(holder.BtnFavoritoFATA.getContext(), "PN añadido a favoritos", Toast.LENGTH_SHORT).show();


                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(holder.BtnFavoritoFATA.getContext(), "Error al añadir favorito.", Toast.LENGTH_SHORT).show();

                                }
                            });


                } else {

                    FirebaseDatabase.getInstance().getReference().child("FAVORITOS").child(user.getUid()).child(getRef(position).getKey()).removeValue();
                    Toast.makeText(holder.BtnFavoritoFATA.getContext(), "PN removido de favoritos", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @NonNull
    @Override
    public myViewHolderTA onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_tablets_c,parent,false);
        return new TabletsCAdapter.myViewHolderTA(view);
    }

    public class myViewHolderTA extends RecyclerView.ViewHolder {

        TextView PAIS,SLOC,PN,FAMILIA,CPU,GPU,HDD,SSD,RAM,TECLADO,PANTALLA,HUELLA,BIOS,OS;
        CheckBox BtnFavoritoFATA;
        Button BtnVerTAC;

        public myViewHolderTA(@NonNull View itemView) {
            super(itemView);

            PAIS = (TextView) itemView.findViewById(R.id.PaisPNTAC);
            SLOC = (TextView) itemView.findViewById(R.id.SlocPNTAC);
            PN = (TextView) itemView.findViewById(R.id.NombrePNTAC);
            FAMILIA = (TextView) itemView.findViewById(R.id.DescripcionPNTAC);
            CPU = (TextView) itemView.findViewById(R.id.CpuPNTAC);
            GPU = (TextView) itemView.findViewById(R.id.GpuPNTAC);
            HDD = (TextView) itemView.findViewById(R.id.HddPNTAC);
            SSD = (TextView) itemView.findViewById(R.id.SsdPNTAC);
            RAM = (TextView) itemView.findViewById(R.id.RamPNTAC);
            TECLADO = (TextView) itemView.findViewById(R.id.TecladoPNTAC);
            PANTALLA = (TextView) itemView.findViewById(R.id.PantallaPNTAC);
            HUELLA = (TextView) itemView.findViewById(R.id.HuellaPNTAC);
            BIOS = (TextView) itemView.findViewById(R.id.BiosPNTAC);
            OS = (TextView) itemView.findViewById(R.id.OsPNTAC);

            BtnFavoritoFATA =(CheckBox) itemView.findViewById(R.id.BtnFavoritoFATA);
            BtnVerTAC = (Button) itemView.findViewById(R.id.BtnVerTAC);

        }
    }


}
