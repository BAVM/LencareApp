package com.vaxwe.lencareapp.ui.CategoriasCliente.ThinkcentreC;

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

public class ThinkcentreCBuscarAdapter extends RecyclerView.Adapter<ThinkcentreCBuscarAdapter.myViewHolderTC>{


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference data = FirebaseDatabase.getInstance().getReference();


    Context context;
    ArrayList<ThinkcentreCModo> listThinkcentreC;

    public ThinkcentreCBuscarAdapter(Context context, ArrayList<ThinkcentreCModo> listThinkcentreC) {
        this.context = context;
        this.listThinkcentreC = listThinkcentreC;
    }

    @NonNull
    @Override
    public myViewHolderTC onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view_thinkcentre_c,parent,false);
        return new ThinkcentreCBuscarAdapter.myViewHolderTC(view);
    }


    @Override
    public void onBindViewHolder(@NonNull myViewHolderTC holder, int position) {
        ThinkcentreCModo thinkcentreCModo = listThinkcentreC.get(position);

        holder.NombrePNTCC.setText((thinkcentreCModo.getPN()));
        holder.DescripcionPNTCC.setText((thinkcentreCModo.getFAMILIA()));
        holder.PaisPNTCC.setText((thinkcentreCModo.getPAIS()));
        holder.SlocPNTCC.setText((thinkcentreCModo.getSLOC()));
        holder.CpuPNTCC.setText((thinkcentreCModo.getCPU()));
        holder.GpuPNTCC.setText((thinkcentreCModo.getGPU()));
        holder.HddPNTCC.setText((thinkcentreCModo.getHDD()));
        holder.SsdPNTCC.setText((thinkcentreCModo.getSSD()));
        holder.RamPNTCC.setText((thinkcentreCModo.getRAM()));
        holder.TecladoPNTCC.setText((thinkcentreCModo.getTECLADO()));
        holder.PantallaPNTCC.setText((thinkcentreCModo.getPANTALLA()));
        holder.HuellaPNTCC.setText((thinkcentreCModo.getHUELLA()));
        holder.BiosPNTCC.setText((thinkcentreCModo.getBIOS()));
        holder.OsPNTCC.setText((thinkcentreCModo.getOS()));

        //BOTON VER PN
        holder.BtnVerTCC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DialogPlus dialogPlus = newDialog(holder.NombrePNTCC.getContext()).setContentHolder
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

                PN.setText(thinkcentreCModo.getPN());
                FAMILIA.setText(thinkcentreCModo.getFAMILIA());
                PAIS.setText(thinkcentreCModo.getPAIS());
                SLOC.setText(thinkcentreCModo.getSLOC());
                CPU.setText(thinkcentreCModo.getCPU());
                GPU.setText(thinkcentreCModo.getGPU());
                HDD.setText(thinkcentreCModo.getHDD());
                SSD.setText(thinkcentreCModo.getSSD());
                RAM.setText(thinkcentreCModo.getRAM());
                TECLADO.setText(thinkcentreCModo.getTECLADO());
                PANTALLA.setText(thinkcentreCModo.getPANTALLA());
                HUELLA.setText(thinkcentreCModo.getHUELLA());
                BIOS.setText(thinkcentreCModo.getBIOS());
                OS.setText(thinkcentreCModo.getOS());

                dialogPlus.show();

            }
        });




        holder.BtnFavoritoFATC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (compoundButton.isChecked()){

                    TextView PN = holder.NombrePNTCC.findViewById(R.id.NombrePNTCC);
                    TextView PAIS = holder.PaisPNTCC.findViewById(R.id.PaisPNTCC);
                    TextView SLOC = holder.SlocPNTCC.findViewById(R.id.SlocPNTCC);
                    TextView FAMILIA = holder.DescripcionPNTCC.findViewById(R.id.DescripcionPNTCC);
                    TextView CPU = holder.CpuPNTCC.findViewById(R.id.CpuPNTCC);
                    TextView GPU = holder.GpuPNTCC.findViewById(R.id.GpuPNTCC);
                    TextView HDD = holder.HddPNTCC.findViewById(R.id.HddPNTCC);
                    TextView SSD = holder.SsdPNTCC.findViewById(R.id.SsdPNTCC);
                    TextView RAM = holder.RamPNTCC.findViewById(R.id.RamPNTCC);
                    TextView TECLADO = holder.TecladoPNTCC.findViewById(R.id.TecladoPNTCC);
                    TextView PANTALLA = holder.PantallaPNTCC.findViewById(R.id.PantallaPNTCC);
                    TextView HUELLA = holder.HuellaPNTCC.findViewById(R.id.HuellaPNTCC);
                    TextView BIOS = holder.BiosPNTCC.findViewById(R.id.BiosPNTCC);
                    TextView OS = holder.OsPNTCC.findViewById(R.id.OsPNTCC);

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
                                    Toast.makeText(holder.BtnFavoritoFATC.getContext(), "PN añadido a favoritos", Toast.LENGTH_SHORT).show();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(holder.BtnFavoritoFATC.getContext(), "Error al añadir favorito.", Toast.LENGTH_SHORT).show();

                                }
                            });


                }else{
                    String key = data.child("posts").push().getKey();
                    FirebaseDatabase.getInstance().getReference().child("FAVORITOS").child(user.getUid()).child(key).removeValue();
                    Toast.makeText(holder.BtnFavoritoFATC.getContext(), "PN removido de favoritos", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }


    @Override
    public int getItemCount() {
        return listThinkcentreC.size();
    }

    public class myViewHolderTC extends RecyclerView.ViewHolder {

        TextView NombrePNTCC, DescripcionPNTCC, PaisPNTCC, SlocPNTCC, CpuPNTCC, GpuPNTCC, HddPNTCC,
                SsdPNTCC, RamPNTCC, TecladoPNTCC, PantallaPNTCC, HuellaPNTCC, BiosPNTCC, OsPNTCC;
        CheckBox BtnFavoritoFATC;
        Button BtnVerTCC;

        public myViewHolderTC(@NonNull View itemView) {
            super(itemView);

            NombrePNTCC = itemView.findViewById(R.id.NombrePNTCC);
            DescripcionPNTCC = itemView.findViewById(R.id.DescripcionPNTCC);
            PaisPNTCC = itemView.findViewById(R.id.PaisPNTCC);
            SlocPNTCC = itemView.findViewById(R.id.SlocPNTCC);
            CpuPNTCC = itemView.findViewById(R.id.CpuPNTCC);
            GpuPNTCC = itemView.findViewById(R.id.GpuPNTCC);
            HddPNTCC = itemView.findViewById(R.id.HddPNTCC);
            SsdPNTCC = itemView.findViewById(R.id.SsdPNTCC);
            RamPNTCC = itemView.findViewById(R.id.RamPNTCC);
            TecladoPNTCC = itemView.findViewById(R.id.TecladoPNTCC);
            PantallaPNTCC = itemView.findViewById(R.id.PantallaPNTCC);
            HuellaPNTCC = itemView.findViewById(R.id.HuellaPNTCC);
            BiosPNTCC = itemView.findViewById(R.id.BiosPNTCC);
            OsPNTCC = itemView.findViewById(R.id.OsPNTCC);

            BtnFavoritoFATC = itemView.findViewById(R.id.BtnFavoritoFATC);
            BtnVerTCC = (Button) itemView.findViewById(R.id.BtnVerTCC);


        }
    }

}
