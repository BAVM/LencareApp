package com.vaxwe.lencareapp.ui.CategoriasCliente.YogaC;

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

public class YogaCBuscarAdapter extends RecyclerView.Adapter<YogaCBuscarAdapter.myViewHolderYO>{


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference data = FirebaseDatabase.getInstance().getReference();

    Context context;
    ArrayList<YogaCModo> listYogaC;

    public YogaCBuscarAdapter(Context context, ArrayList<YogaCModo> listYogaC) {
        this.context = context;
        this.listYogaC = listYogaC;
    }

    @NonNull
    @Override
    public YogaCBuscarAdapter.myViewHolderYO onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view_yoga_c,parent,false);
        return new YogaCBuscarAdapter.myViewHolderYO(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YogaCBuscarAdapter.myViewHolderYO holder, int position) {
        YogaCModo yogaCModo = listYogaC.get(position);

        holder.NombrePNYOC.setText((yogaCModo.getPN()));
        holder.DescripcionPNYOC.setText((yogaCModo.getFAMILIA()));
        holder.PaisPNYOC.setText((yogaCModo.getPAIS()));
        holder.SlocPNYOC.setText((yogaCModo.getSLOC()));
        holder.CpuPNYOC.setText((yogaCModo.getCPU()));
        holder.GpuPNYOC.setText((yogaCModo.getGPU()));
        holder.HddPNYOC.setText((yogaCModo.getHDD()));
        holder.SsdPNYOC.setText((yogaCModo.getSSD()));
        holder.RamPNYOC.setText((yogaCModo.getRAM()));
        holder.TecladoPNYOC.setText((yogaCModo.getTECLADO()));
        holder.PantallaPNYOC.setText((yogaCModo.getPANTALLA()));
        holder.HuellaPNYOC.setText((yogaCModo.getHUELLA()));
        holder.BiosPNYOC.setText((yogaCModo.getBIOS()));
        holder.OsPNYOC.setText((yogaCModo.getOS()));


        //BOTON VER PN
        holder.BtnVerYOC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DialogPlus dialogPlus = newDialog(holder.NombrePNYOC.getContext()).setContentHolder
                                (new ViewHolder(R.layout.vista_ver_pn_yoga)).setExpanded(true, 1700)
                        .setContentBackgroundResource(R.color.color18).create();

                //dialogPlus.show();

                View v = dialogPlus.getHolderView();
                TextView PN = v.findViewById(R.id.txtVerPnYOC);
                TextView FAMILIA = v.findViewById(R.id.txtVerDescripcionYOC);
                TextView PAIS = v.findViewById(R.id.txtVerPaisYOC);
                TextView SLOC = v.findViewById(R.id.txtVerSlocYOC);
                TextView CPU = v.findViewById(R.id.txtVerCpuYOC);
                TextView GPU = v.findViewById(R.id.txtVerGpuYOC);
                TextView HDD = v.findViewById(R.id.txtVerHddYOC);
                TextView SSD = v.findViewById(R.id.txtVerSsdYOC);
                TextView RAM = v.findViewById(R.id.txtVerRamYOC);
                TextView TECLADO = v.findViewById(R.id.txtVerTecladoYOC);
                TextView PANTALLA = v.findViewById(R.id.txtVerPantallaYOC);
                TextView HUELLA = v.findViewById(R.id.txtVerHuellaYOC);
                TextView BIOS = v.findViewById(R.id.txtVerBiosYOC);
                TextView OS = v.findViewById(R.id.txtVerOsYOC);

                PN.setText(yogaCModo.getPN());
                FAMILIA.setText(yogaCModo.getFAMILIA());
                PAIS.setText(yogaCModo.getPAIS());
                SLOC.setText(yogaCModo.getSLOC());
                CPU.setText(yogaCModo.getCPU());
                GPU.setText(yogaCModo.getGPU());
                HDD.setText(yogaCModo.getHDD());
                SSD.setText(yogaCModo.getSSD());
                RAM.setText(yogaCModo.getRAM());
                TECLADO.setText(yogaCModo.getTECLADO());
                PANTALLA.setText(yogaCModo.getPANTALLA());
                HUELLA.setText(yogaCModo.getHUELLA());
                BIOS.setText(yogaCModo.getBIOS());
                OS.setText(yogaCModo.getOS());

                dialogPlus.show();

            }
        });



        holder.BtnFavoritoFAYO.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (compoundButton.isChecked()){

                    TextView PN = holder.NombrePNYOC.findViewById(R.id.NombrePNYOC);
                    TextView PAIS = holder.PaisPNYOC.findViewById(R.id.PaisPNYOC);
                    TextView SLOC = holder.SlocPNYOC.findViewById(R.id.SlocPNYOC);
                    TextView FAMILIA = holder.DescripcionPNYOC.findViewById(R.id.DescripcionPNYOC);
                    TextView CPU = holder.CpuPNYOC.findViewById(R.id.CpuPNYOC);
                    TextView GPU = holder.GpuPNYOC.findViewById(R.id.GpuPNYOC);
                    TextView HDD = holder.HddPNYOC.findViewById(R.id.HddPNYOC);
                    TextView SSD = holder.SsdPNYOC.findViewById(R.id.SsdPNYOC);
                    TextView RAM = holder.RamPNYOC.findViewById(R.id.RamPNYOC);
                    TextView TECLADO = holder.TecladoPNYOC.findViewById(R.id.TecladoPNYOC);
                    TextView PANTALLA = holder.PantallaPNYOC.findViewById(R.id.PantallaPNYOC);
                    TextView HUELLA = holder.HuellaPNYOC.findViewById(R.id.HuellaPNYOC);
                    TextView BIOS = holder.BiosPNYOC.findViewById(R.id.BiosPNYOC);
                    TextView OS = holder.OsPNYOC.findViewById(R.id.OsPNYOC);

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
                                    Toast.makeText(holder.BtnFavoritoFAYO.getContext(), "PN añadido a favoritos", Toast.LENGTH_SHORT).show();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(holder.BtnFavoritoFAYO.getContext(), "Error al añadir favorito.", Toast.LENGTH_SHORT).show();

                                }
                            });


                }else{
                    String key = data.child("posts").push().getKey();
                    FirebaseDatabase.getInstance().getReference().child("FAVORITOS").child(user.getUid()).child(key).removeValue();
                    Toast.makeText(holder.BtnFavoritoFAYO.getContext(), "PN removido de favoritos", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return listYogaC.size();
    }

    public class myViewHolderYO extends RecyclerView.ViewHolder {

        TextView NombrePNYOC, DescripcionPNYOC, PaisPNYOC, SlocPNYOC, CpuPNYOC, GpuPNYOC, HddPNYOC,
                SsdPNYOC, RamPNYOC, TecladoPNYOC, PantallaPNYOC, HuellaPNYOC, BiosPNYOC, OsPNYOC;
        CheckBox BtnFavoritoFAYO;
        Button BtnVerYOC;

        public myViewHolderYO(@NonNull View itemView) {
            super(itemView);

            NombrePNYOC = itemView.findViewById(R.id.NombrePNYOC);
            DescripcionPNYOC = itemView.findViewById(R.id.DescripcionPNYOC);
            PaisPNYOC = itemView.findViewById(R.id.PaisPNYOC);
            SlocPNYOC = itemView.findViewById(R.id.SlocPNYOC);
            CpuPNYOC = itemView.findViewById(R.id.CpuPNYOC);
            GpuPNYOC = itemView.findViewById(R.id.GpuPNYOC);
            HddPNYOC = itemView.findViewById(R.id.HddPNYOC);
            SsdPNYOC = itemView.findViewById(R.id.SsdPNYOC);
            RamPNYOC = itemView.findViewById(R.id.RamPNYOC);
            TecladoPNYOC = itemView.findViewById(R.id.TecladoPNYOC);
            PantallaPNYOC = itemView.findViewById(R.id.PantallaPNYOC);
            HuellaPNYOC = itemView.findViewById(R.id.HuellaPNYOC);
            BiosPNYOC = itemView.findViewById(R.id.BiosPNYOC);
            OsPNYOC = itemView.findViewById(R.id.OsPNYOC);

            BtnFavoritoFAYO = itemView.findViewById(R.id.BtnFavoritoFAYO);
            BtnVerYOC = (Button) itemView.findViewById(R.id.BtnVerYOC);


        }
    }

}
