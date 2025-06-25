package com.vaxwe.lencareapp.ui.CategoriasCliente.MonitoresC;

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

public class MonitoresCBuscarAdapter extends RecyclerView.Adapter<MonitoresCBuscarAdapter.myViewHolderMO>{


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference data = FirebaseDatabase.getInstance().getReference();

    Context context;
    ArrayList<MonitoresCModo> listMonitoresC;

    public MonitoresCBuscarAdapter(Context context, ArrayList<MonitoresCModo> listMonitoresC) {
        this.context = context;
        this.listMonitoresC = listMonitoresC;
    }

    @NonNull
    @Override
    public MonitoresCBuscarAdapter.myViewHolderMO onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view_monitores_c,parent,false);
        return new MonitoresCBuscarAdapter.myViewHolderMO(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonitoresCBuscarAdapter.myViewHolderMO holder, int position) {
        MonitoresCModo monitoresCModo = listMonitoresC.get(position);

        holder.NombrePNMOC.setText((monitoresCModo.getPN()));
        holder.DescripcionPNMOC.setText((monitoresCModo.getFAMILIA()));
        holder.PaisPNMOC.setText((monitoresCModo.getPAIS()));
        holder.SlocPNMOC.setText((monitoresCModo.getSLOC()));
        holder.CpuPNMOC.setText((monitoresCModo.getCPU()));
        holder.GpuPNMOC.setText((monitoresCModo.getGPU()));
        holder.HddPNMOC.setText((monitoresCModo.getHDD()));
        holder.SsdPNMOC.setText((monitoresCModo.getSSD()));
        holder.RamPNMOC.setText((monitoresCModo.getRAM()));
        holder.TecladoPNMOC.setText((monitoresCModo.getTECLADO()));
        holder.PantallaPNMOC.setText((monitoresCModo.getPANTALLA()));
        holder.HuellaPNMOC.setText((monitoresCModo.getHUELLA()));
        holder.BiosPNMOC.setText((monitoresCModo.getBIOS()));
        holder.OsPNMOC.setText((monitoresCModo.getOS()));

        //BOTON VER PN
        holder.BtnVerMOC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DialogPlus dialogPlus = newDialog(holder.NombrePNMOC.getContext()).setContentHolder
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

                PN.setText(monitoresCModo.getPN());
                FAMILIA.setText(monitoresCModo.getFAMILIA());
                PAIS.setText(monitoresCModo.getPAIS());
                SLOC.setText(monitoresCModo.getSLOC());
                CPU.setText(monitoresCModo.getCPU());
                GPU.setText(monitoresCModo.getGPU());
                HDD.setText(monitoresCModo.getHDD());
                SSD.setText(monitoresCModo.getSSD());
                RAM.setText(monitoresCModo.getRAM());
                TECLADO.setText(monitoresCModo.getTECLADO());
                PANTALLA.setText(monitoresCModo.getPANTALLA());
                HUELLA.setText(monitoresCModo.getHUELLA());
                BIOS.setText(monitoresCModo.getBIOS());
                OS.setText(monitoresCModo.getOS());

                dialogPlus.show();

            }
        });



        holder.BtnFavoritoFAMO.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (compoundButton.isChecked()){

                    TextView PN = holder.NombrePNMOC.findViewById(R.id.NombrePNMOC);
                    TextView PAIS = holder.PaisPNMOC.findViewById(R.id.PaisPNMOC);
                    TextView SLOC = holder.SlocPNMOC.findViewById(R.id.SlocPNMOC);
                    TextView FAMILIA = holder.DescripcionPNMOC.findViewById(R.id.DescripcionPNMOC);
                    TextView CPU = holder.CpuPNMOC.findViewById(R.id.CpuPNMOC);
                    TextView GPU = holder.GpuPNMOC.findViewById(R.id.GpuPNMOC);
                    TextView HDD = holder.HddPNMOC.findViewById(R.id.HddPNMOC);
                    TextView SSD = holder.SsdPNMOC.findViewById(R.id.SsdPNMOC);
                    TextView RAM = holder.RamPNMOC.findViewById(R.id.RamPNMOC);
                    TextView TECLADO = holder.TecladoPNMOC.findViewById(R.id.TecladoPNMOC);
                    TextView PANTALLA = holder.PantallaPNMOC.findViewById(R.id.PantallaPNMOC);
                    TextView HUELLA = holder.HuellaPNMOC.findViewById(R.id.HuellaPNMOC);
                    TextView BIOS = holder.BiosPNMOC.findViewById(R.id.BiosPNMOC);
                    TextView OS = holder.OsPNMOC.findViewById(R.id.OsPNMOC);

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
                                    Toast.makeText(holder.BtnFavoritoFAMO.getContext(), "PN añadido a favoritos", Toast.LENGTH_SHORT).show();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(holder.BtnFavoritoFAMO.getContext(), "Error al añadir favorito.", Toast.LENGTH_SHORT).show();

                                }
                            });


                }else{
                    String key = data.child("posts").push().getKey();
                    FirebaseDatabase.getInstance().getReference().child("FAVORITOS").child(user.getUid()).child(key).removeValue();
                    Toast.makeText(holder.BtnFavoritoFAMO.getContext(), "PN removido de favoritos", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }

    @Override
    public int getItemCount() {
        return listMonitoresC.size();
    }

    public class myViewHolderMO extends RecyclerView.ViewHolder {

        TextView NombrePNMOC, DescripcionPNMOC, PaisPNMOC, SlocPNMOC, CpuPNMOC, GpuPNMOC, HddPNMOC,
                SsdPNMOC, RamPNMOC, TecladoPNMOC, PantallaPNMOC, HuellaPNMOC, BiosPNMOC, OsPNMOC;
        CheckBox BtnFavoritoFAMO;
        Button BtnVerMOC;

        public myViewHolderMO(@NonNull View itemView) {
            super(itemView);

            NombrePNMOC = itemView.findViewById(R.id.NombrePNMOC);
            DescripcionPNMOC = itemView.findViewById(R.id.DescripcionPNMOC);
            PaisPNMOC = itemView.findViewById(R.id.PaisPNMOC);
            SlocPNMOC = itemView.findViewById(R.id.SlocPNMOC);
            CpuPNMOC = itemView.findViewById(R.id.CpuPNMOC);
            GpuPNMOC = itemView.findViewById(R.id.GpuPNMOC);
            HddPNMOC = itemView.findViewById(R.id.HddPNMOC);
            SsdPNMOC = itemView.findViewById(R.id.SsdPNMOC);
            RamPNMOC = itemView.findViewById(R.id.RamPNMOC);
            TecladoPNMOC = itemView.findViewById(R.id.TecladoPNMOC);
            PantallaPNMOC = itemView.findViewById(R.id.PantallaPNMOC);
            HuellaPNMOC = itemView.findViewById(R.id.HuellaPNMOC);
            BiosPNMOC = itemView.findViewById(R.id.BiosPNMOC);
            OsPNMOC = itemView.findViewById(R.id.OsPNMOC);

            BtnFavoritoFAMO = itemView.findViewById(R.id.BtnFavoritoFAMO);
            BtnVerMOC = (Button) itemView.findViewById(R.id.BtnVerMOC);


        }
    }

}
