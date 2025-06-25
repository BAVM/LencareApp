package com.vaxwe.lencareapp.ui.CategoriasCliente.ThinkpadC;

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

public class ThinkpadCBuscarAdapter extends RecyclerView.Adapter<ThinkpadCBuscarAdapter.myViewHolderTP>{


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference data = FirebaseDatabase.getInstance().getReference();

    Context context;
    ArrayList<ThinkpadCModo> listThinkpadC;

    public ThinkpadCBuscarAdapter(Context context, ArrayList<ThinkpadCModo> listThinkpadC) {
        this.context = context;
        this.listThinkpadC = listThinkpadC;
    }

    @NonNull
    @Override
    public myViewHolderTP onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view_thinkpad_c,parent,false);
        return new ThinkpadCBuscarAdapter.myViewHolderTP(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThinkpadCBuscarAdapter.myViewHolderTP holder, int position) {
        ThinkpadCModo thinkpadCModo = listThinkpadC.get(position);

        holder.NombrePNTPC.setText((thinkpadCModo.getPN()));
        holder.DescripcionPNTPC.setText((thinkpadCModo.getFAMILIA()));
        holder.PaisPNTPC.setText((thinkpadCModo.getPAIS()));
        holder.SlocPNTPC.setText((thinkpadCModo.getSLOC()));
        holder.CpuPNTPC.setText((thinkpadCModo.getCPU()));
        holder.GpuPNTPC.setText((thinkpadCModo.getGPU()));
        holder.HddPNTPC.setText((thinkpadCModo.getHDD()));
        holder.SsdPNTPC.setText((thinkpadCModo.getSSD()));
        holder.RamPNTPC.setText((thinkpadCModo.getRAM()));
        holder.TecladoPNTPC.setText((thinkpadCModo.getTECLADO()));
        holder.PantallaPNTPC.setText((thinkpadCModo.getPANTALLA()));
        holder.HuellaPNTPC.setText((thinkpadCModo.getHUELLA()));
        holder.BiosPNTPC.setText((thinkpadCModo.getBIOS()));
        holder.OsPNTPC.setText((thinkpadCModo.getOS()));

        //BOTON VER PN
        holder.BtnVerTPC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DialogPlus dialogPlus = newDialog(holder.NombrePNTPC.getContext()).setContentHolder
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

                PN.setText(thinkpadCModo.getPN());
                FAMILIA.setText(thinkpadCModo.getFAMILIA());
                PAIS.setText(thinkpadCModo.getPAIS());
                SLOC.setText(thinkpadCModo.getSLOC());
                CPU.setText(thinkpadCModo.getCPU());
                GPU.setText(thinkpadCModo.getGPU());
                HDD.setText(thinkpadCModo.getHDD());
                SSD.setText(thinkpadCModo.getSSD());
                RAM.setText(thinkpadCModo.getRAM());
                TECLADO.setText(thinkpadCModo.getTECLADO());
                PANTALLA.setText(thinkpadCModo.getPANTALLA());
                HUELLA.setText(thinkpadCModo.getHUELLA());
                BIOS.setText(thinkpadCModo.getBIOS());
                OS.setText(thinkpadCModo.getOS());

                dialogPlus.show();

            }
        });





        holder.BtnFavoritoFATP.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (compoundButton.isChecked()){

                    TextView PN = holder.NombrePNTPC.findViewById(R.id.NombrePNTPC);
                    TextView PAIS = holder.PaisPNTPC.findViewById(R.id.PaisPNTPC);
                    TextView SLOC = holder.SlocPNTPC.findViewById(R.id.SlocPNTPC);
                    TextView FAMILIA = holder.DescripcionPNTPC.findViewById(R.id.DescripcionPNTPC);
                    TextView CPU = holder.CpuPNTPC.findViewById(R.id.CpuPNTPC);
                    TextView GPU = holder.GpuPNTPC.findViewById(R.id.GpuPNTPC);
                    TextView HDD = holder.HddPNTPC.findViewById(R.id.HddPNTPC);
                    TextView SSD = holder.SsdPNTPC.findViewById(R.id.SsdPNTPC);
                    TextView RAM = holder.RamPNTPC.findViewById(R.id.RamPNTPC);
                    TextView TECLADO = holder.TecladoPNTPC.findViewById(R.id.TecladoPNTPC);
                    TextView PANTALLA = holder.PantallaPNTPC.findViewById(R.id.PantallaPNTPC);
                    TextView HUELLA = holder.HuellaPNTPC.findViewById(R.id.HuellaPNTPC);
                    TextView BIOS = holder.BiosPNTPC.findViewById(R.id.BiosPNTPC);
                    TextView OS = holder.OsPNTPC.findViewById(R.id.OsPNTPC);

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
                                    Toast.makeText(holder.BtnFavoritoFATP.getContext(), "PN añadido a favoritos", Toast.LENGTH_SHORT).show();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(holder.BtnFavoritoFATP.getContext(), "Error al añadir favorito.", Toast.LENGTH_SHORT).show();

                                }
                            });


                }else{
                    String key = data.child("posts").push().getKey();
                    FirebaseDatabase.getInstance().getReference().child("FAVORITOS").child(user.getUid()).child(key).removeValue();
                    Toast.makeText(holder.BtnFavoritoFATP.getContext(), "PN removido de favoritos", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return listThinkpadC.size();
    }

    public class myViewHolderTP extends RecyclerView.ViewHolder {

        TextView NombrePNTPC, DescripcionPNTPC, PaisPNTPC, SlocPNTPC, CpuPNTPC, GpuPNTPC, HddPNTPC,
                SsdPNTPC, RamPNTPC, TecladoPNTPC, PantallaPNTPC, HuellaPNTPC, BiosPNTPC, OsPNTPC;
        CheckBox BtnFavoritoFATP;
        Button BtnVerTPC;

        public myViewHolderTP(@NonNull View itemView) {
            super(itemView);

            NombrePNTPC = itemView.findViewById(R.id.NombrePNTPC);
            DescripcionPNTPC = itemView.findViewById(R.id.DescripcionPNTPC);
            PaisPNTPC = itemView.findViewById(R.id.PaisPNTPC);
            SlocPNTPC = itemView.findViewById(R.id.SlocPNTPC);
            CpuPNTPC = itemView.findViewById(R.id.CpuPNTPC);
            GpuPNTPC = itemView.findViewById(R.id.GpuPNTPC);
            HddPNTPC = itemView.findViewById(R.id.HddPNTPC);
            SsdPNTPC = itemView.findViewById(R.id.SsdPNTPC);
            RamPNTPC = itemView.findViewById(R.id.RamPNTPC);
            TecladoPNTPC = itemView.findViewById(R.id.TecladoPNTPC);
            PantallaPNTPC = itemView.findViewById(R.id.PantallaPNTPC);
            HuellaPNTPC = itemView.findViewById(R.id.HuellaPNTPC);
            BiosPNTPC = itemView.findViewById(R.id.BiosPNTPC);
            OsPNTPC = itemView.findViewById(R.id.OsPNTPC);

            BtnFavoritoFATP = itemView.findViewById(R.id.BtnFavoritoFATP);
            BtnVerTPC = (Button) itemView.findViewById(R.id.BtnVerTPC);

        }
    }

}
