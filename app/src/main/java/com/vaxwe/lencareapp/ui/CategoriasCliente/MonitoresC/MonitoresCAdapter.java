package com.vaxwe.lencareapp.ui.CategoriasCliente.MonitoresC;

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

public class MonitoresCAdapter extends FirebaseRecyclerAdapter<MonitoresCModo, MonitoresCAdapter.myViewHolderMO> {


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MonitoresCAdapter(@NonNull FirebaseRecyclerOptions<MonitoresCModo> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolderMO holder, int position, @NonNull MonitoresCModo model) {

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
        holder.BtnVerMOC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DialogPlus dialogPlus = newDialog(holder.PN.getContext()).setContentHolder
                                (new ViewHolder(R.layout.vista_ver_pn_monitores)).setExpanded(true, 1700)
                        .setContentBackgroundResource(R.color.color18).create();

                //dialogPlus.show();

                View v = dialogPlus.getHolderView();
                TextView PN = v.findViewById(R.id.txtVerPnMOC);
                TextView FAMILIA = v.findViewById(R.id.txtVerDescripcionMOC);
                TextView PAIS = v.findViewById(R.id.txtVerPaisMOC);
                TextView SLOC = v.findViewById(R.id.txtVerSlocMOC);
                TextView CPU = v.findViewById(R.id.txtVerCpuMOC);
                TextView GPU = v.findViewById(R.id.txtVerGpuMOC);
                TextView HDD = v.findViewById(R.id.txtVerHddMOC);
                TextView SSD = v.findViewById(R.id.txtVerSsdMOC);
                TextView RAM = v.findViewById(R.id.txtVerRamMOC);
                TextView TECLADO = v.findViewById(R.id.txtVerTecladoMOC);
                TextView PANTALLA = v.findViewById(R.id.txtVerPantallaMOC);
                TextView HUELLA = v.findViewById(R.id.txtVerHuellaMOC);
                TextView BIOS = v.findViewById(R.id.txtVerBiosMOC);
                TextView OS = v.findViewById(R.id.txtVerOsMOC);

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
        holder.BtnFavoritoFAMO.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (compoundButton.isChecked()) {

                    TextView PN = holder.PN.findViewById(R.id.NombrePNMOC);
                    TextView PAIS = holder.PAIS.findViewById(R.id.PaisPNMOC);
                    TextView SLOC = holder.SLOC.findViewById(R.id.SlocPNMOC);
                    TextView FAMILIA = holder.FAMILIA.findViewById(R.id.DescripcionPNMOC);
                    TextView CPU = holder.CPU.findViewById(R.id.CpuPNMOC);
                    TextView GPU = holder.GPU.findViewById(R.id.GpuPNMOC);
                    TextView HDD = holder.HDD.findViewById(R.id.HddPNMOC);
                    TextView SSD = holder.SSD.findViewById(R.id.SsdPNMOC);
                    TextView RAM = holder.RAM.findViewById(R.id.RamPNMOC);
                    TextView TECLADO = holder.TECLADO.findViewById(R.id.TecladoPNMOC);
                    TextView PANTALLA = holder.PANTALLA.findViewById(R.id.PantallaPNMOC);
                    TextView HUELLA = holder.HUELLA.findViewById(R.id.HuellaPNMOC);
                    TextView BIOS = holder.BIOS.findViewById(R.id.BiosPNMOC);
                    TextView OS = holder.OS.findViewById(R.id.OsPNMOC);

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
                                    Toast.makeText(holder.BtnFavoritoFAMO.getContext(), "PN añadido a favoritos", Toast.LENGTH_SHORT).show();


                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(holder.BtnFavoritoFAMO.getContext(), "Error al añadir favorito.", Toast.LENGTH_SHORT).show();

                                }
                            });


                } else {

                    FirebaseDatabase.getInstance().getReference().child("FAVORITOS").child(user.getUid()).child(getRef(position).getKey()).removeValue();
                    Toast.makeText(holder.BtnFavoritoFAMO.getContext(), "PN removido de favoritos", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @NonNull
    @Override
    public myViewHolderMO onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_monitores_c,parent,false);
        return new MonitoresCAdapter.myViewHolderMO(view);
    }

    public class myViewHolderMO extends RecyclerView.ViewHolder {

        TextView PAIS,SLOC,PN,FAMILIA,CPU,GPU,HDD,SSD,RAM,TECLADO,PANTALLA,HUELLA,BIOS,OS;
        CheckBox BtnFavoritoFAMO;
        Button BtnVerMOC;

        public myViewHolderMO(@NonNull View itemView) {
            super(itemView);

            PAIS = (TextView) itemView.findViewById(R.id.PaisPNMOC);
            SLOC = (TextView) itemView.findViewById(R.id.SlocPNMOC);
            PN = (TextView) itemView.findViewById(R.id.NombrePNMOC);
            FAMILIA = (TextView) itemView.findViewById(R.id.DescripcionPNMOC);
            CPU = (TextView) itemView.findViewById(R.id.CpuPNMOC);
            GPU = (TextView) itemView.findViewById(R.id.GpuPNMOC);
            HDD = (TextView) itemView.findViewById(R.id.HddPNMOC);
            SSD = (TextView) itemView.findViewById(R.id.SsdPNMOC);
            RAM = (TextView) itemView.findViewById(R.id.RamPNMOC);
            TECLADO = (TextView) itemView.findViewById(R.id.TecladoPNMOC);
            PANTALLA = (TextView) itemView.findViewById(R.id.PantallaPNMOC);
            HUELLA = (TextView) itemView.findViewById(R.id.HuellaPNMOC);
            BIOS = (TextView) itemView.findViewById(R.id.BiosPNMOC);
            OS = (TextView) itemView.findViewById(R.id.OsPNMOC);

            BtnFavoritoFAMO =(CheckBox) itemView.findViewById(R.id.BtnFavoritoFAMO);
            BtnVerMOC = (Button) itemView.findViewById(R.id.BtnVerMOC);


        }
    }
}
