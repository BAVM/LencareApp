package com.vaxwe.lencareapp.ui.CategoriasCliente.LegionC;

import static com.orhanobut.dialogplus.DialogPlus.newDialog;

import android.content.Context;
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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.vaxwe.lencareapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LegionCBuscarAdapter extends RecyclerView.Adapter<LegionCBuscarAdapter.myViewHolderLE>{


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference data = FirebaseDatabase.getInstance().getReference();

    Context context;
    ArrayList<LegionCModo> listLegionC;

    public LegionCBuscarAdapter(Context context, ArrayList<LegionCModo> listLegionC) {
        this.context = context;
        this.listLegionC = listLegionC;
    }

    @NonNull
    @Override
    public LegionCBuscarAdapter.myViewHolderLE onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view_legion_c,parent,false);
        return new LegionCBuscarAdapter.myViewHolderLE(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LegionCBuscarAdapter.myViewHolderLE holder, int position) {
        LegionCModo legionCModo = listLegionC.get(position);

        holder.NombrePNLEC.setText((legionCModo.getPN()));
        holder.DescripcionPNLEC.setText((legionCModo.getFAMILIA()));
        holder.PaisPNLEC.setText((legionCModo.getPAIS()));
        holder.SlocPNLEC.setText((legionCModo.getSLOC()));
        holder.CpuPNLEC.setText((legionCModo.getCPU()));
        holder.GpuPNLEC.setText((legionCModo.getGPU()));
        holder.HddPNLEC.setText((legionCModo.getHDD()));
        holder.SsdPNLEC.setText((legionCModo.getSSD()));
        holder.RamPNLEC.setText((legionCModo.getRAM()));
        holder.TecladoPNLEC.setText((legionCModo.getTECLADO()));
        holder.PantallaPNLEC.setText((legionCModo.getPANTALLA()));
        holder.HuellaPNLEC.setText((legionCModo.getHUELLA()));
        holder.BiosPNLEC.setText((legionCModo.getBIOS()));
        holder.OsPNLEC.setText((legionCModo.getOS()));

        //BOTON VER PN
        holder.BtnVerLEC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DialogPlus dialogPlus = newDialog(holder.NombrePNLEC.getContext()).setContentHolder
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

                PN.setText(legionCModo.getPN());
                FAMILIA.setText(legionCModo.getFAMILIA());
                PAIS.setText(legionCModo.getPAIS());
                SLOC.setText(legionCModo.getSLOC());
                CPU.setText(legionCModo.getCPU());
                GPU.setText(legionCModo.getGPU());
                HDD.setText(legionCModo.getHDD());
                SSD.setText(legionCModo.getSSD());
                RAM.setText(legionCModo.getRAM());
                TECLADO.setText(legionCModo.getTECLADO());
                PANTALLA.setText(legionCModo.getPANTALLA());
                HUELLA.setText(legionCModo.getHUELLA());
                BIOS.setText(legionCModo.getBIOS());
                OS.setText(legionCModo.getOS());

                dialogPlus.show();

            }
        });



        holder.BtnFavoritoFALE.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (compoundButton.isChecked()){

                    TextView PN = holder.NombrePNLEC.findViewById(R.id.NombrePNLEC);
                    TextView PAIS = holder.PaisPNLEC.findViewById(R.id.PaisPNLEC);
                    TextView SLOC = holder.SlocPNLEC.findViewById(R.id.SlocPNLEC);
                    TextView FAMILIA = holder.DescripcionPNLEC.findViewById(R.id.DescripcionPNLEC);
                    TextView CPU = holder.CpuPNLEC.findViewById(R.id.CpuPNLEC);
                    TextView GPU = holder.GpuPNLEC.findViewById(R.id.GpuPNLEC);
                    TextView HDD = holder.HddPNLEC.findViewById(R.id.HddPNLEC);
                    TextView SSD = holder.SsdPNLEC.findViewById(R.id.SsdPNLEC);
                    TextView RAM = holder.RamPNLEC.findViewById(R.id.RamPNLEC);
                    TextView TECLADO = holder.TecladoPNLEC.findViewById(R.id.TecladoPNLEC);
                    TextView PANTALLA = holder.PantallaPNLEC.findViewById(R.id.PantallaPNLEC);
                    TextView HUELLA = holder.HuellaPNLEC.findViewById(R.id.HuellaPNLEC);
                    TextView BIOS = holder.BiosPNLEC.findViewById(R.id.BiosPNLEC);
                    TextView OS = holder.OsPNLEC.findViewById(R.id.OsPNLEC);

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

                    //final String key =  listThinkcentreC.get(position).getPN();
                    //String key = data.getRef(position).getKey();
                    String key = data.child("posts").push().getKey();


                    FirebaseDatabase.getInstance().getReference().child("FAVORITOS").child(user.getUid()).child(key).updateChildren(fav).
                            addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(holder.BtnFavoritoFALE.getContext(), "PN añadido a favoritos", Toast.LENGTH_SHORT).show();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(holder.BtnFavoritoFALE.getContext(), "Error al añadir favorito.", Toast.LENGTH_SHORT).show();

                                }
                            });


                }else{
                    String key = data.child("posts").push().getKey();
                    FirebaseDatabase.getInstance().getReference().child("FAVORITOS").child(user.getUid()).child(key).removeValue();
                    Toast.makeText(holder.BtnFavoritoFALE.getContext(), "PN removido de favoritos", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return listLegionC.size();
    }

    public class myViewHolderLE extends RecyclerView.ViewHolder {

        TextView NombrePNLEC, DescripcionPNLEC, PaisPNLEC, SlocPNLEC, CpuPNLEC, GpuPNLEC, HddPNLEC,
                SsdPNLEC, RamPNLEC, TecladoPNLEC, PantallaPNLEC, HuellaPNLEC, BiosPNLEC, OsPNLEC;
        CheckBox BtnFavoritoFALE;
        Button BtnVerLEC;

        public myViewHolderLE(@NonNull View itemView) {
            super(itemView);

            NombrePNLEC = itemView.findViewById(R.id.NombrePNLEC);
            DescripcionPNLEC = itemView.findViewById(R.id.DescripcionPNLEC);
            PaisPNLEC = itemView.findViewById(R.id.PaisPNLEC);
            SlocPNLEC = itemView.findViewById(R.id.SlocPNLEC);
            CpuPNLEC = itemView.findViewById(R.id.CpuPNLEC);
            GpuPNLEC = itemView.findViewById(R.id.GpuPNLEC);
            HddPNLEC = itemView.findViewById(R.id.HddPNLEC);
            SsdPNLEC = itemView.findViewById(R.id.SsdPNLEC);
            RamPNLEC = itemView.findViewById(R.id.RamPNLEC);
            TecladoPNLEC = itemView.findViewById(R.id.TecladoPNLEC);
            PantallaPNLEC = itemView.findViewById(R.id.PantallaPNLEC);
            HuellaPNLEC = itemView.findViewById(R.id.HuellaPNLEC);
            BiosPNLEC = itemView.findViewById(R.id.BiosPNLEC);
            OsPNLEC = itemView.findViewById(R.id.OsPNLEC);

            BtnFavoritoFALE = itemView.findViewById(R.id.BtnFavoritoFALE);
            BtnVerLEC = (Button) itemView.findViewById(R.id.BtnVerLEC);


        }
    }

}
