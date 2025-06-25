package com.vaxwe.lencareapp.ui.CategoriasCliente.ThinksmartC;

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

public class ThinksmartCBuscarAdapter extends RecyclerView.Adapter<ThinksmartCBuscarAdapter.myViewHolderTS>{


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference data = FirebaseDatabase.getInstance().getReference();

    Context context;
    ArrayList<ThinksmartCModo> listThinksmartC;

    public ThinksmartCBuscarAdapter(Context context, ArrayList<ThinksmartCModo> listThinksmartC) {
        this.context = context;
        this.listThinksmartC = listThinksmartC;
    }

    @NonNull
    @Override
    public ThinksmartCBuscarAdapter.myViewHolderTS onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view_thinksmart_c,parent,false);
        return new ThinksmartCBuscarAdapter.myViewHolderTS(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThinksmartCBuscarAdapter.myViewHolderTS holder, int position) {
        ThinksmartCModo thinksmartCModo = listThinksmartC.get(position);

        holder.NombrePNTSC.setText((thinksmartCModo.getPN()));
        holder.DescripcionPNTSC.setText((thinksmartCModo.getFAMILIA()));
        holder.PaisPNTSC.setText((thinksmartCModo.getPAIS()));
        holder.SlocPNTSC.setText((thinksmartCModo.getSLOC()));
        holder.CpuPNTSC.setText((thinksmartCModo.getCPU()));
        holder.GpuPNTSC.setText((thinksmartCModo.getGPU()));
        holder.HddPNTSC.setText((thinksmartCModo.getHDD()));
        holder.SsdPNTSC.setText((thinksmartCModo.getSSD()));
        holder.RamPNTSC.setText((thinksmartCModo.getRAM()));
        holder.TecladoPNTSC.setText((thinksmartCModo.getTECLADO()));
        holder.PantallaPNTSC.setText((thinksmartCModo.getPANTALLA()));
        holder.HuellaPNTSC.setText((thinksmartCModo.getHUELLA()));
        holder.BiosPNTSC.setText((thinksmartCModo.getBIOS()));
        holder.OsPNTSC.setText((thinksmartCModo.getOS()));

        //BOTON VER PN
        holder.BtnVerTSC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DialogPlus dialogPlus = newDialog(holder.NombrePNTSC.getContext()).setContentHolder
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

                PN.setText(thinksmartCModo.getPN());
                FAMILIA.setText(thinksmartCModo.getFAMILIA());
                PAIS.setText(thinksmartCModo.getPAIS());
                SLOC.setText(thinksmartCModo.getSLOC());
                CPU.setText(thinksmartCModo.getCPU());
                GPU.setText(thinksmartCModo.getGPU());
                HDD.setText(thinksmartCModo.getHDD());
                SSD.setText(thinksmartCModo.getSSD());
                RAM.setText(thinksmartCModo.getRAM());
                TECLADO.setText(thinksmartCModo.getTECLADO());
                PANTALLA.setText(thinksmartCModo.getPANTALLA());
                HUELLA.setText(thinksmartCModo.getHUELLA());
                BIOS.setText(thinksmartCModo.getBIOS());
                OS.setText(thinksmartCModo.getOS());

                dialogPlus.show();

            }
        });


        holder.BtnFavoritoFATS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (compoundButton.isChecked()){

                    TextView PN = holder.NombrePNTSC.findViewById(R.id.NombrePNTSC);
                    TextView PAIS = holder.PaisPNTSC.findViewById(R.id.PaisPNTSC);
                    TextView SLOC = holder.SlocPNTSC.findViewById(R.id.SlocPNTSC);
                    TextView FAMILIA = holder.DescripcionPNTSC.findViewById(R.id.DescripcionPNTSC);
                    TextView CPU = holder.CpuPNTSC.findViewById(R.id.CpuPNTSC);
                    TextView GPU = holder.GpuPNTSC.findViewById(R.id.GpuPNTSC);
                    TextView HDD = holder.HddPNTSC.findViewById(R.id.HddPNTSC);
                    TextView SSD = holder.SsdPNTSC.findViewById(R.id.SsdPNTSC);
                    TextView RAM = holder.RamPNTSC.findViewById(R.id.RamPNTSC);
                    TextView TECLADO = holder.TecladoPNTSC.findViewById(R.id.TecladoPNTSC);
                    TextView PANTALLA = holder.PantallaPNTSC.findViewById(R.id.PantallaPNTSC);
                    TextView HUELLA = holder.HuellaPNTSC.findViewById(R.id.HuellaPNTSC);
                    TextView BIOS = holder.BiosPNTSC.findViewById(R.id.BiosPNTSC);
                    TextView OS = holder.OsPNTSC.findViewById(R.id.OsPNTSC);

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
                                    Toast.makeText(holder.BtnFavoritoFATS.getContext(), "PN añadido a favoritos", Toast.LENGTH_SHORT).show();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(holder.BtnFavoritoFATS.getContext(), "Error al añadir favorito.", Toast.LENGTH_SHORT).show();

                                }
                            });


                }else{
                    String key = data.child("posts").push().getKey();
                    FirebaseDatabase.getInstance().getReference().child("FAVORITOS").child(user.getUid()).child(key).removeValue();
                    Toast.makeText(holder.BtnFavoritoFATS.getContext(), "PN removido de favoritos", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return listThinksmartC.size();
    }

    public class myViewHolderTS extends RecyclerView.ViewHolder {

        TextView NombrePNTSC, DescripcionPNTSC, PaisPNTSC, SlocPNTSC, CpuPNTSC, GpuPNTSC, HddPNTSC,
                SsdPNTSC, RamPNTSC, TecladoPNTSC, PantallaPNTSC, HuellaPNTSC, BiosPNTSC, OsPNTSC;
        CheckBox BtnFavoritoFATS;
        Button BtnVerTSC;

        public myViewHolderTS(@NonNull View itemView) {
            super(itemView);

            NombrePNTSC = itemView.findViewById(R.id.NombrePNTSC);
            DescripcionPNTSC = itemView.findViewById(R.id.DescripcionPNTSC);
            PaisPNTSC = itemView.findViewById(R.id.PaisPNTSC);
            SlocPNTSC = itemView.findViewById(R.id.SlocPNTSC);
            CpuPNTSC = itemView.findViewById(R.id.CpuPNTSC);
            GpuPNTSC = itemView.findViewById(R.id.GpuPNTSC);
            HddPNTSC = itemView.findViewById(R.id.HddPNTSC);
            SsdPNTSC = itemView.findViewById(R.id.SsdPNTSC);
            RamPNTSC = itemView.findViewById(R.id.RamPNTSC);
            TecladoPNTSC = itemView.findViewById(R.id.TecladoPNTSC);
            PantallaPNTSC = itemView.findViewById(R.id.PantallaPNTSC);
            HuellaPNTSC = itemView.findViewById(R.id.HuellaPNTSC);
            BiosPNTSC = itemView.findViewById(R.id.BiosPNTSC);
            OsPNTSC = itemView.findViewById(R.id.OsPNTSC);

            BtnFavoritoFATS = itemView.findViewById(R.id.BtnFavoritoFATS);
            BtnVerTSC = (Button) itemView.findViewById(R.id.BtnVerTSC);

        }
    }


}
