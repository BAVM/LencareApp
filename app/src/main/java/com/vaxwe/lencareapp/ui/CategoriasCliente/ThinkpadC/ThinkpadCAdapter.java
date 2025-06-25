package com.vaxwe.lencareapp.ui.CategoriasCliente.ThinkpadC;

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

public class ThinkpadCAdapter extends FirebaseRecyclerAdapter<ThinkpadCModo, ThinkpadCAdapter.myViewHolderTP> {


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ThinkpadCAdapter(@NonNull FirebaseRecyclerOptions<ThinkpadCModo> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolderTP holder, int position, @NonNull ThinkpadCModo model) {

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
        holder.BtnVerTPC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DialogPlus dialogPlus = newDialog(holder.PN.getContext()).setContentHolder
                                (new ViewHolder(R.layout.vista_ver_pn_thinkpad)).setExpanded(true, 1700)
                        .setContentBackgroundResource(R.color.color18).create();

                //dialogPlus.show();

                View v = dialogPlus.getHolderView();
                TextView PN = v.findViewById(R.id.txtVerPnTPC);
                TextView FAMILIA = v.findViewById(R.id.txtVerDescripcionTPC);
                TextView PAIS = v.findViewById(R.id.txtVerPaisTPC);
                TextView SLOC = v.findViewById(R.id.txtVerSlocTPC);
                TextView CPU = v.findViewById(R.id.txtVerCpuTPC);
                TextView GPU = v.findViewById(R.id.txtVerGpuTPC);
                TextView HDD = v.findViewById(R.id.txtVerHddTPC);
                TextView SSD = v.findViewById(R.id.txtVerSsdTPC);
                TextView RAM = v.findViewById(R.id.txtVerRamTPC);
                TextView TECLADO = v.findViewById(R.id.txtVerTecladoTPC);
                TextView PANTALLA = v.findViewById(R.id.txtVerPantallaTPC);
                TextView HUELLA = v.findViewById(R.id.txtVerHuellaTPC);
                TextView BIOS = v.findViewById(R.id.txtVerBiosTPC);
                TextView OS = v.findViewById(R.id.txtVerOsTPC);

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
        holder.BtnFavoritoFATP.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (compoundButton.isChecked()) {

                    TextView PN = holder.PN.findViewById(R.id.NombrePNTPC);
                    TextView PAIS = holder.PAIS.findViewById(R.id.PaisPNTPC);
                    TextView SLOC = holder.SLOC.findViewById(R.id.SlocPNTPC);
                    TextView FAMILIA = holder.FAMILIA.findViewById(R.id.DescripcionPNTPC);
                    TextView CPU = holder.CPU.findViewById(R.id.CpuPNTPC);
                    TextView GPU = holder.GPU.findViewById(R.id.GpuPNTPC);
                    TextView HDD = holder.HDD.findViewById(R.id.HddPNTPC);
                    TextView SSD = holder.SSD.findViewById(R.id.SsdPNTPC);
                    TextView RAM = holder.RAM.findViewById(R.id.RamPNTPC);
                    TextView TECLADO = holder.TECLADO.findViewById(R.id.TecladoPNTPC);
                    TextView PANTALLA = holder.PANTALLA.findViewById(R.id.PantallaPNTPC);
                    TextView HUELLA = holder.HUELLA.findViewById(R.id.HuellaPNTPC);
                    TextView BIOS = holder.BIOS.findViewById(R.id.BiosPNTPC);
                    TextView OS = holder.OS.findViewById(R.id.OsPNTPC);

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
                                    Toast.makeText(holder.BtnFavoritoFATP.getContext(), "PN añadido a favoritos", Toast.LENGTH_SHORT).show();


                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(holder.BtnFavoritoFATP.getContext(), "Error al añadir favorito.", Toast.LENGTH_SHORT).show();

                                }
                            });


                } else {

                    FirebaseDatabase.getInstance().getReference().child("FAVORITOS").child(user.getUid()).child(getRef(position).getKey()).removeValue();
                    Toast.makeText(holder.BtnFavoritoFATP.getContext(), "PN removido de favoritos", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @NonNull
    @Override
    public myViewHolderTP onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_thinkpad_c,parent,false);
        return new ThinkpadCAdapter.myViewHolderTP(view);
    }

    public class myViewHolderTP extends RecyclerView.ViewHolder {

        TextView PAIS,SLOC,PN,FAMILIA,CPU,GPU,HDD,SSD,RAM,TECLADO,PANTALLA,HUELLA,BIOS,OS;
        CheckBox BtnFavoritoFATP;
        Button BtnVerTPC;

        public myViewHolderTP(@NonNull View itemView) {
            super(itemView);

            PAIS = (TextView) itemView.findViewById(R.id.PaisPNTPC);
            SLOC = (TextView) itemView.findViewById(R.id.SlocPNTPC);
            PN = (TextView) itemView.findViewById(R.id.NombrePNTPC);
            FAMILIA = (TextView) itemView.findViewById(R.id.DescripcionPNTPC);
            CPU = (TextView) itemView.findViewById(R.id.CpuPNTPC);
            GPU = (TextView) itemView.findViewById(R.id.GpuPNTPC);
            HDD = (TextView) itemView.findViewById(R.id.HddPNTPC);
            SSD = (TextView) itemView.findViewById(R.id.SsdPNTPC);
            RAM = (TextView) itemView.findViewById(R.id.RamPNTPC);
            TECLADO = (TextView) itemView.findViewById(R.id.TecladoPNTPC);
            PANTALLA = (TextView) itemView.findViewById(R.id.PantallaPNTPC);
            HUELLA = (TextView) itemView.findViewById(R.id.HuellaPNTPC);
            BIOS = (TextView) itemView.findViewById(R.id.BiosPNTPC);
            OS = (TextView) itemView.findViewById(R.id.OsPNTPC);

            BtnFavoritoFATP =(CheckBox) itemView.findViewById(R.id.BtnFavoritoFATP);
            BtnVerTPC = (Button) itemView.findViewById(R.id.BtnVerTPC);

        }


    }

}
