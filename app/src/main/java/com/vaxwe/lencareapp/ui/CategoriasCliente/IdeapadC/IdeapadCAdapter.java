package com.vaxwe.lencareapp.ui.CategoriasCliente.IdeapadC;

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

public class IdeapadCAdapter extends FirebaseRecyclerAdapter<IdeapadCModo,IdeapadCAdapter.myViewHolderIP> {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public IdeapadCAdapter(@NonNull FirebaseRecyclerOptions<IdeapadCModo> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolderIP holder, int position, @NonNull IdeapadCModo model) {

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
        holder.BtnVerIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DialogPlus dialogPlus = newDialog(holder.PN.getContext()).setContentHolder
                                (new ViewHolder(R.layout.vista_ver_pn_ideapad)).setExpanded(true, 1700)
                        .setContentBackgroundResource(R.color.color18).create();

                //dialogPlus.show();

                View v = dialogPlus.getHolderView();
                TextView PN = v.findViewById(R.id.txtVerPnIP);
                TextView FAMILIA = v.findViewById(R.id.txtVerDescripcionIP);
                TextView PAIS = v.findViewById(R.id.txtVerPaisIP);
                TextView SLOC = v.findViewById(R.id.txtVerSlocIP);
                TextView CPU = v.findViewById(R.id.txtVerCpuIP);
                TextView GPU = v.findViewById(R.id.txtVerGpuIP);
                TextView HDD = v.findViewById(R.id.txtVerHddIP);
                TextView SSD = v.findViewById(R.id.txtVerSsdIP);
                TextView RAM = v.findViewById(R.id.txtVerRamIP);
                TextView TECLADO = v.findViewById(R.id.txtVerTecladoIP);
                TextView PANTALLA = v.findViewById(R.id.txtVerPantallaIP);
                TextView HUELLA = v.findViewById(R.id.txtVerHuellaIP);
                TextView BIOS = v.findViewById(R.id.txtVerBiosIP);
                TextView OS = v.findViewById(R.id.txtVerOsIP);

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
        holder.BtnFavoritoFAIP.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (compoundButton.isChecked()) {

                    TextView PN = holder.PN.findViewById(R.id.NombrePNIPC);
                    TextView PAIS = holder.PAIS.findViewById(R.id.PaisPNIPC);
                    TextView SLOC = holder.SLOC.findViewById(R.id.SlocPNIPC);
                    TextView FAMILIA = holder.FAMILIA.findViewById(R.id.DescripcionPNIPC);
                    TextView CPU = holder.CPU.findViewById(R.id.CpuPNIPC);
                    TextView GPU = holder.GPU.findViewById(R.id.GpuPNIPC);
                    TextView HDD = holder.HDD.findViewById(R.id.HddPNIPC);
                    TextView SSD = holder.SSD.findViewById(R.id.SsdPNIPC);
                    TextView RAM = holder.RAM.findViewById(R.id.RamPNIPC);
                    TextView TECLADO = holder.TECLADO.findViewById(R.id.TecladoPNIPC);
                    TextView PANTALLA = holder.PANTALLA.findViewById(R.id.PantallaPNIPC);
                    TextView HUELLA = holder.HUELLA.findViewById(R.id.HuellaPNIPC);
                    TextView BIOS = holder.BIOS.findViewById(R.id.BiosPNIPC);
                    TextView OS = holder.OS.findViewById(R.id.OsPNIPC);

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
                                    Toast.makeText(holder.BtnFavoritoFAIP.getContext(), "PN añadido a favoritos", Toast.LENGTH_SHORT).show();


                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(holder.BtnFavoritoFAIP.getContext(), "Error al añadir favorito.", Toast.LENGTH_SHORT).show();

                                }
                            });


                } else {

                    FirebaseDatabase.getInstance().getReference().child("FAVORITOS").child(user.getUid()).child(getRef(position).getKey()).removeValue();
                    Toast.makeText(holder.BtnFavoritoFAIP.getContext(), "PN removido de favoritos", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @NonNull
    @Override
    public myViewHolderIP onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_ideapad_c,parent,false);
        return new IdeapadCAdapter.myViewHolderIP(view);
    }

    public class myViewHolderIP extends RecyclerView.ViewHolder {

        TextView PAIS,SLOC,PN,FAMILIA,CPU,GPU,HDD,SSD,RAM,TECLADO,PANTALLA,HUELLA,BIOS,OS;
        CheckBox BtnFavoritoFAIP;
        Button BtnVerIP;

        public myViewHolderIP(@NonNull View itemView) {
            super(itemView);

            PAIS = (TextView) itemView.findViewById(R.id.PaisPNIPC);
            SLOC = (TextView) itemView.findViewById(R.id.SlocPNIPC);
            PN = (TextView) itemView.findViewById(R.id.NombrePNIPC);
            FAMILIA = (TextView) itemView.findViewById(R.id.DescripcionPNIPC);
            CPU = (TextView) itemView.findViewById(R.id.CpuPNIPC);
            GPU = (TextView) itemView.findViewById(R.id.GpuPNIPC);
            HDD = (TextView) itemView.findViewById(R.id.HddPNIPC);
            SSD = (TextView) itemView.findViewById(R.id.SsdPNIPC);
            RAM = (TextView) itemView.findViewById(R.id.RamPNIPC);
            TECLADO = (TextView) itemView.findViewById(R.id.TecladoPNIPC);
            PANTALLA = (TextView) itemView.findViewById(R.id.PantallaPNIPC);
            HUELLA = (TextView) itemView.findViewById(R.id.HuellaPNIPC);
            BIOS = (TextView) itemView.findViewById(R.id.BiosPNIPC);
            OS = (TextView) itemView.findViewById(R.id.OsPNIPC);

            BtnFavoritoFAIP =(CheckBox) itemView.findViewById(R.id.BtnFavoritoFAIP);
            BtnVerIP = (Button) itemView.findViewById(R.id.BtnVerIP);

        }
    }

}
