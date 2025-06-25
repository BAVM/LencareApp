package com.vaxwe.lencareapp.ui.CategoriasCliente.IdeacentreC;

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

public class IdeacentreCBuscarAdapter extends RecyclerView.Adapter<IdeacentreCBuscarAdapter.myViewAdapterIC>{


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference data = FirebaseDatabase.getInstance().getReference();

    Context context;
    ArrayList<IdeacentreCModo> listIdeacentreC;

    public IdeacentreCBuscarAdapter(Context context, ArrayList<IdeacentreCModo> listIdeacentreC) {
        this.context = context;
        this.listIdeacentreC = listIdeacentreC;
    }

    @NonNull
    @Override
    public myViewAdapterIC onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view_ideacentre_c,parent,false);
        return new IdeacentreCBuscarAdapter.myViewAdapterIC(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IdeacentreCBuscarAdapter.myViewAdapterIC holder, int position) {
        IdeacentreCModo ideacentreCModo = listIdeacentreC.get(position);

        holder.NombrePNICC.setText((ideacentreCModo.getPN()));
        holder.DescripcionPNICC.setText((ideacentreCModo.getFAMILIA()));
        holder.PaisPNICC.setText((ideacentreCModo.getPAIS()));
        holder.SlocPNICC.setText((ideacentreCModo.getSLOC()));
        holder.CpuPNICC.setText((ideacentreCModo.getCPU()));
        holder.GpuPNICC.setText((ideacentreCModo.getGPU()));
        holder.HddPNICC.setText((ideacentreCModo.getHDD()));
        holder.SsdPNICC.setText((ideacentreCModo.getSSD()));
        holder.RamPNICC.setText((ideacentreCModo.getRAM()));
        holder.TecladoPNICC.setText((ideacentreCModo.getTECLADO()));
        holder.PantallaPNICC.setText((ideacentreCModo.getPANTALLA()));
        holder.HuellaPNICC.setText((ideacentreCModo.getHUELLA()));
        holder.BiosPNICC.setText((ideacentreCModo.getBIOS()));
        holder.OsPNICC.setText((ideacentreCModo.getOS()));

        //BOTON VER PN
        holder.BtnVerIC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DialogPlus dialogPlus = newDialog(holder.NombrePNICC.getContext()).setContentHolder
                                (new ViewHolder(R.layout.vista_ver_pn_ideacentre)).setExpanded(true, 1700)
                        .setContentBackgroundResource(R.color.color18).create();

                //dialogPlus.show();

                View v = dialogPlus.getHolderView();
                TextView PN = v.findViewById(R.id.txtVerPnIC);
                TextView FAMILIA = v.findViewById(R.id.txtVerDescripcionIC);
                TextView PAIS = v.findViewById(R.id.txtVerPaisIC);
                TextView SLOC = v.findViewById(R.id.txtVerSlocIC);
                TextView CPU = v.findViewById(R.id.txtVerCpuIC);
                TextView GPU = v.findViewById(R.id.txtVerGpuIC);
                TextView HDD = v.findViewById(R.id.txtVerHddIC);
                TextView SSD = v.findViewById(R.id.txtVerSsdIC);
                TextView RAM = v.findViewById(R.id.txtVerRamIC);
                TextView TECLADO = v.findViewById(R.id.txtVerTecladoIC);
                TextView PANTALLA = v.findViewById(R.id.txtVerPantallaIC);
                TextView HUELLA = v.findViewById(R.id.txtVerHuellaIC);
                TextView BIOS = v.findViewById(R.id.txtVerBiosIC);
                TextView OS = v.findViewById(R.id.txtVerOsIC);

                PN.setText(ideacentreCModo.getPN());
                FAMILIA.setText(ideacentreCModo.getFAMILIA());
                PAIS.setText(ideacentreCModo.getPAIS());
                SLOC.setText(ideacentreCModo.getSLOC());
                CPU.setText(ideacentreCModo.getCPU());
                GPU.setText(ideacentreCModo.getGPU());
                HDD.setText(ideacentreCModo.getHDD());
                SSD.setText(ideacentreCModo.getSSD());
                RAM.setText(ideacentreCModo.getRAM());
                TECLADO.setText(ideacentreCModo.getTECLADO());
                PANTALLA.setText(ideacentreCModo.getPANTALLA());
                HUELLA.setText(ideacentreCModo.getHUELLA());
                BIOS.setText(ideacentreCModo.getBIOS());
                OS.setText(ideacentreCModo.getOS());

                dialogPlus.show();
            }
        });



        holder.BtnFavoritoFAIC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (compoundButton.isChecked()){

                    TextView PN = holder.NombrePNICC.findViewById(R.id.NombrePNICC);
                    TextView PAIS = holder.PaisPNICC.findViewById(R.id.PaisPNICC);
                    TextView SLOC = holder.SlocPNICC.findViewById(R.id.SlocPNICC);
                    TextView FAMILIA = holder.DescripcionPNICC.findViewById(R.id.DescripcionPNICC);
                    TextView CPU = holder.CpuPNICC.findViewById(R.id.CpuPNICC);
                    TextView GPU = holder.GpuPNICC.findViewById(R.id.GpuPNICC);
                    TextView HDD = holder.HddPNICC.findViewById(R.id.HddPNICC);
                    TextView SSD = holder.SsdPNICC.findViewById(R.id.SsdPNICC);
                    TextView RAM = holder.RamPNICC.findViewById(R.id.RamPNICC);
                    TextView TECLADO = holder.TecladoPNICC.findViewById(R.id.TecladoPNICC);
                    TextView PANTALLA = holder.PantallaPNICC.findViewById(R.id.PantallaPNICC);
                    TextView HUELLA = holder.HuellaPNICC.findViewById(R.id.HuellaPNICC);
                    TextView BIOS = holder.BiosPNICC.findViewById(R.id.BiosPNICC);
                    TextView OS = holder.OsPNICC.findViewById(R.id.OsPNICC);

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
                                    Toast.makeText(holder.BtnFavoritoFAIC.getContext(), "PN añadido a favoritos", Toast.LENGTH_SHORT).show();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(holder.BtnFavoritoFAIC.getContext(), "Error al añadir favorito.", Toast.LENGTH_SHORT).show();

                                }
                            });


                }else{
                    String key = data.child("posts").push().getKey();
                    FirebaseDatabase.getInstance().getReference().child("FAVORITOS").child(user.getUid()).child(key).removeValue();
                    Toast.makeText(holder.BtnFavoritoFAIC.getContext(), "PN removido de favoritos", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }

    @Override
    public int getItemCount() {
        return listIdeacentreC.size();
    }

    public static class myViewAdapterIC extends RecyclerView.ViewHolder {

        TextView NombrePNICC, DescripcionPNICC, PaisPNICC, SlocPNICC, CpuPNICC, GpuPNICC, HddPNICC,
                SsdPNICC, RamPNICC, TecladoPNICC, PantallaPNICC, HuellaPNICC, BiosPNICC, OsPNICC;
        CheckBox BtnFavoritoFAIC;
        Button BtnVerIC;

        public myViewAdapterIC(@NonNull View itemView) {
            super(itemView);

            NombrePNICC = itemView.findViewById(R.id.NombrePNICC);
            DescripcionPNICC = itemView.findViewById(R.id.DescripcionPNICC);
            PaisPNICC = itemView.findViewById(R.id.PaisPNICC);
            SlocPNICC = itemView.findViewById(R.id.SlocPNICC);
            CpuPNICC = itemView.findViewById(R.id.CpuPNICC);
            GpuPNICC = itemView.findViewById(R.id.GpuPNICC);
            HddPNICC = itemView.findViewById(R.id.HddPNICC);
            SsdPNICC = itemView.findViewById(R.id.SsdPNICC);
            RamPNICC = itemView.findViewById(R.id.RamPNICC);
            TecladoPNICC = itemView.findViewById(R.id.TecladoPNICC);
            PantallaPNICC = itemView.findViewById(R.id.PantallaPNICC);
            HuellaPNICC = itemView.findViewById(R.id.HuellaPNICC);
            BiosPNICC = itemView.findViewById(R.id.BiosPNICC);
            OsPNICC = itemView.findViewById(R.id.OsPNICC);

            BtnFavoritoFAIC = itemView.findViewById(R.id.BtnFavoritoFAIC);
            BtnVerIC = (Button) itemView.findViewById(R.id.BtnVerIC);

        }
    }

}
