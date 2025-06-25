package com.vaxwe.lencareapp.ui.CategoriasCliente.ThinkbookC;

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

public class ThinkbookCBuscarAdapter extends RecyclerView.Adapter<ThinkbookCBuscarAdapter.myViewHolderTB>{


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference data = FirebaseDatabase.getInstance().getReference();

    Context context;
    ArrayList<ThinkbookCModo> listThinkbookC;

    public ThinkbookCBuscarAdapter(Context context, ArrayList<ThinkbookCModo> listThinkbookC) {
        this.context = context;
        this.listThinkbookC = listThinkbookC;
    }

    @NonNull
    @Override
    public myViewHolderTB onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view_thinkbook_c,parent,false);
        return new ThinkbookCBuscarAdapter.myViewHolderTB(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolderTB holder, int position) {
        ThinkbookCModo thinkbookCModo = listThinkbookC.get(position);

        holder.NombrePNTBC.setText((thinkbookCModo.getPN()));
        holder.DescripcionPNTBC.setText((thinkbookCModo.getFAMILIA()));
        holder.PaisPNTBC.setText((thinkbookCModo.getPAIS()));
        holder.SlocPNTBC.setText((thinkbookCModo.getSLOC()));
        holder.CpuPNTBC.setText((thinkbookCModo.getCPU()));
        holder.GpuPNTBC.setText((thinkbookCModo.getGPU()));
        holder.HddPNTBC.setText((thinkbookCModo.getHDD()));
        holder.SsdPNTBC.setText((thinkbookCModo.getSSD()));
        holder.RamPNTBC.setText((thinkbookCModo.getRAM()));
        holder.TecladoPNTBC.setText((thinkbookCModo.getTECLADO()));
        holder.PantallaPNTBC.setText((thinkbookCModo.getPANTALLA()));
        holder.HuellaPNTBC.setText((thinkbookCModo.getHUELLA()));
        holder.BiosPNTBC.setText((thinkbookCModo.getBIOS()));
        holder.OsPNTBC.setText((thinkbookCModo.getOS()));

        //BOTON VER PN
        holder.BtnVerTBC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DialogPlus dialogPlus = newDialog(holder.NombrePNTBC.getContext()).setContentHolder
                                (new ViewHolder(R.layout.vista_ver_pn_thinkbook)).setExpanded(true, 1700)
                        .setContentBackgroundResource(R.color.color18).create();

                //dialogPlus.show();

                View v = dialogPlus.getHolderView();
                TextView PN = v.findViewById(R.id.txtVerPnTBC);
                TextView FAMILIA = v.findViewById(R.id.txtVerDescripcionTBC);
                TextView PAIS = v.findViewById(R.id.txtVerPaisTBC);
                TextView SLOC = v.findViewById(R.id.txtVerSlocTBC);
                TextView CPU = v.findViewById(R.id.txtVerCpuTBC);
                TextView GPU = v.findViewById(R.id.txtVerGpuTBC);
                TextView HDD = v.findViewById(R.id.txtVerHddTBC);
                TextView SSD = v.findViewById(R.id.txtVerSsdTBC);
                TextView RAM = v.findViewById(R.id.txtVerRamTBC);
                TextView TECLADO = v.findViewById(R.id.txtVerTecladoTBC);
                TextView PANTALLA = v.findViewById(R.id.txtVerPantallaTBC);
                TextView HUELLA = v.findViewById(R.id.txtVerHuellaTBC);
                TextView BIOS = v.findViewById(R.id.txtVerBiosTBC);
                TextView OS = v.findViewById(R.id.txtVerOsTBC);

                PN.setText(thinkbookCModo.getPN());
                FAMILIA.setText(thinkbookCModo.getFAMILIA());
                PAIS.setText(thinkbookCModo.getPAIS());
                SLOC.setText(thinkbookCModo.getSLOC());
                CPU.setText(thinkbookCModo.getCPU());
                GPU.setText(thinkbookCModo.getGPU());
                HDD.setText(thinkbookCModo.getHDD());
                SSD.setText(thinkbookCModo.getSSD());
                RAM.setText(thinkbookCModo.getRAM());
                TECLADO.setText(thinkbookCModo.getTECLADO());
                PANTALLA.setText(thinkbookCModo.getPANTALLA());
                HUELLA.setText(thinkbookCModo.getHUELLA());
                BIOS.setText(thinkbookCModo.getBIOS());
                OS.setText(thinkbookCModo.getOS());

                dialogPlus.show();

            }
        });


        holder.BtnFavoritoFATB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (compoundButton.isChecked()){

                    TextView PN = holder.NombrePNTBC.findViewById(R.id.NombrePNTBC);
                    TextView PAIS = holder.PaisPNTBC.findViewById(R.id.PaisPNTBC);
                    TextView SLOC = holder.SlocPNTBC.findViewById(R.id.SlocPNTBC);
                    TextView FAMILIA = holder.DescripcionPNTBC.findViewById(R.id.DescripcionPNTBC);
                    TextView CPU = holder.CpuPNTBC.findViewById(R.id.CpuPNTBC);
                    TextView GPU = holder.GpuPNTBC.findViewById(R.id.GpuPNTBC);
                    TextView HDD = holder.HddPNTBC.findViewById(R.id.HddPNTBC);
                    TextView SSD = holder.SsdPNTBC.findViewById(R.id.SsdPNTBC);
                    TextView RAM = holder.RamPNTBC.findViewById(R.id.RamPNTBC);
                    TextView TECLADO = holder.TecladoPNTBC.findViewById(R.id.TecladoPNTBC);
                    TextView PANTALLA = holder.PantallaPNTBC.findViewById(R.id.PantallaPNTBC);
                    TextView HUELLA = holder.HuellaPNTBC.findViewById(R.id.HuellaPNTBC);
                    TextView BIOS = holder.BiosPNTBC.findViewById(R.id.BiosPNTBC);
                    TextView OS = holder.OsPNTBC.findViewById(R.id.OsPNTBC);

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
                                    Toast.makeText(holder.BtnFavoritoFATB.getContext(), "PN añadido a favoritos", Toast.LENGTH_SHORT).show();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(holder.BtnFavoritoFATB.getContext(), "Error al añadir favorito.", Toast.LENGTH_SHORT).show();

                                }
                            });


                }else{
                    String key = data.child("posts").push().getKey();
                    FirebaseDatabase.getInstance().getReference().child("FAVORITOS").child(user.getUid()).child(key).removeValue();
                    Toast.makeText(holder.BtnFavoritoFATB.getContext(), "PN removido de favoritos", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return listThinkbookC.size();
    }

    public class myViewHolderTB extends RecyclerView.ViewHolder {

        TextView NombrePNTBC, DescripcionPNTBC, PaisPNTBC, SlocPNTBC, CpuPNTBC, GpuPNTBC, HddPNTBC,
                SsdPNTBC, RamPNTBC, TecladoPNTBC, PantallaPNTBC, HuellaPNTBC, BiosPNTBC, OsPNTBC;
        CheckBox BtnFavoritoFATB;
        Button BtnVerTBC;

        public myViewHolderTB(@NonNull View itemView) {
            super(itemView);

            NombrePNTBC = itemView.findViewById(R.id.NombrePNTBC);
            DescripcionPNTBC = itemView.findViewById(R.id.DescripcionPNTBC);
            PaisPNTBC = itemView.findViewById(R.id.PaisPNTBC);
            SlocPNTBC = itemView.findViewById(R.id.SlocPNTBC);
            CpuPNTBC = itemView.findViewById(R.id.CpuPNTBC);
            GpuPNTBC = itemView.findViewById(R.id.GpuPNTBC);
            HddPNTBC = itemView.findViewById(R.id.HddPNTBC);
            SsdPNTBC = itemView.findViewById(R.id.SsdPNTBC);
            RamPNTBC = itemView.findViewById(R.id.RamPNTBC);
            TecladoPNTBC = itemView.findViewById(R.id.TecladoPNTBC);
            PantallaPNTBC = itemView.findViewById(R.id.PantallaPNTBC);
            HuellaPNTBC = itemView.findViewById(R.id.HuellaPNTBC);
            BiosPNTBC = itemView.findViewById(R.id.BiosPNTBC);
            OsPNTBC = itemView.findViewById(R.id.OsPNTBC);

            BtnFavoritoFATB = itemView.findViewById(R.id.BtnFavoritoFATB);
            BtnVerTBC = (Button) itemView.findViewById(R.id.BtnVerTBC);


        }
    }

}
