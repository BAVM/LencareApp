package com.vaxwe.lencareapp.ui.CategoriasCliente.IdeapadC;

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

public class IdeapadCBuscarAdapter extends RecyclerView.Adapter<IdeapadCBuscarAdapter.myViewHolderIP>{

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference data = FirebaseDatabase.getInstance().getReference();

    Context context;
    ArrayList<IdeapadCModo> listIdeapadC;

    public IdeapadCBuscarAdapter(Context context, ArrayList<IdeapadCModo> listIdeapadC) {
        this.context = context;
        this.listIdeapadC = listIdeapadC;
    }

    @NonNull
    @Override
    public myViewHolderIP onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view_ideapad_c,parent,false);
        return new IdeapadCBuscarAdapter.myViewHolderIP(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IdeapadCBuscarAdapter.myViewHolderIP holder, int position) {
        IdeapadCModo ideapadCModo = listIdeapadC.get(position);

        holder.NombrePNIPC.setText((ideapadCModo.getPN()));
        holder.DescripcionPNIPC.setText((ideapadCModo.getFAMILIA()));
        holder.PaisPNIPC.setText((ideapadCModo.getPAIS()));
        holder.SlocPNIPC.setText((ideapadCModo.getSLOC()));
        holder.CpuPNIPC.setText((ideapadCModo.getCPU()));
        holder.GpuPNIPC.setText((ideapadCModo.getGPU()));
        holder.HddPNIPC.setText((ideapadCModo.getHDD()));
        holder.SsdPNIPC.setText((ideapadCModo.getSSD()));
        holder.RamPNIPC.setText((ideapadCModo.getRAM()));
        holder.TecladoPNIPC.setText((ideapadCModo.getTECLADO()));
        holder.PantallaPNIPC.setText((ideapadCModo.getPANTALLA()));
        holder.HuellaPNIPC.setText((ideapadCModo.getHUELLA()));
        holder.BiosPNIPC.setText((ideapadCModo.getBIOS()));
        holder.OsPNIPC.setText((ideapadCModo.getOS()));

        //BOTON VER PN
        holder.BtnVerIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DialogPlus dialogPlus = newDialog(holder.NombrePNIPC.getContext()).setContentHolder
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

                PN.setText(ideapadCModo.getPN());
                FAMILIA.setText(ideapadCModo.getFAMILIA());
                PAIS.setText(ideapadCModo.getPAIS());
                SLOC.setText(ideapadCModo.getSLOC());
                CPU.setText(ideapadCModo.getCPU());
                GPU.setText(ideapadCModo.getGPU());
                HDD.setText(ideapadCModo.getHDD());
                SSD.setText(ideapadCModo.getSSD());
                RAM.setText(ideapadCModo.getRAM());
                TECLADO.setText(ideapadCModo.getTECLADO());
                PANTALLA.setText(ideapadCModo.getPANTALLA());
                HUELLA.setText(ideapadCModo.getHUELLA());
                BIOS.setText(ideapadCModo.getBIOS());
                OS.setText(ideapadCModo.getOS());

                dialogPlus.show();

            }
        });




        holder.BtnFavoritoFAIP.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (compoundButton.isChecked()){

                    TextView PN = holder.NombrePNIPC.findViewById(R.id.NombrePNIPC);
                    TextView PAIS = holder.PaisPNIPC.findViewById(R.id.PaisPNIPC);
                    TextView SLOC = holder.SlocPNIPC.findViewById(R.id.SlocPNIPC);
                    TextView FAMILIA = holder.DescripcionPNIPC.findViewById(R.id.DescripcionPNIPC);
                    TextView CPU = holder.CpuPNIPC.findViewById(R.id.CpuPNIPC);
                    TextView GPU = holder.GpuPNIPC.findViewById(R.id.GpuPNIPC);
                    TextView HDD = holder.HddPNIPC.findViewById(R.id.HddPNIPC);
                    TextView SSD = holder.SsdPNIPC.findViewById(R.id.SsdPNIPC);
                    TextView RAM = holder.RamPNIPC.findViewById(R.id.RamPNIPC);
                    TextView TECLADO = holder.TecladoPNIPC.findViewById(R.id.TecladoPNIPC);
                    TextView PANTALLA = holder.PantallaPNIPC.findViewById(R.id.PantallaPNIPC);
                    TextView HUELLA = holder.HuellaPNIPC.findViewById(R.id.HuellaPNIPC);
                    TextView BIOS = holder.BiosPNIPC.findViewById(R.id.BiosPNIPC);
                    TextView OS = holder.OsPNIPC.findViewById(R.id.OsPNIPC);

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
                                    Toast.makeText(holder.BtnFavoritoFAIP.getContext(), "PN añadido a favoritos", Toast.LENGTH_SHORT).show();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(holder.BtnFavoritoFAIP.getContext(), "Error al añadir favorito.", Toast.LENGTH_SHORT).show();

                                }
                            });


                }else{
                    String key = data.child("posts").push().getKey();
                    FirebaseDatabase.getInstance().getReference().child("FAVORITOS").child(user.getUid()).child(key).removeValue();
                    Toast.makeText(holder.BtnFavoritoFAIP.getContext(), "PN removido de favoritos", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }

    @Override
    public int getItemCount() {
        return listIdeapadC.size();
    }

    public class myViewHolderIP extends RecyclerView.ViewHolder {

        TextView NombrePNIPC, DescripcionPNIPC, PaisPNIPC, SlocPNIPC, CpuPNIPC, GpuPNIPC, HddPNIPC,
                SsdPNIPC, RamPNIPC, TecladoPNIPC, PantallaPNIPC, HuellaPNIPC, BiosPNIPC, OsPNIPC;
        CheckBox BtnFavoritoFAIP;
        Button BtnVerIP;

        public myViewHolderIP(@NonNull View itemView) {
            super(itemView);

            NombrePNIPC = itemView.findViewById(R.id.NombrePNIPC);
            DescripcionPNIPC = itemView.findViewById(R.id.DescripcionPNIPC);
            PaisPNIPC = itemView.findViewById(R.id.PaisPNIPC);
            SlocPNIPC = itemView.findViewById(R.id.SlocPNIPC);
            CpuPNIPC = itemView.findViewById(R.id.CpuPNIPC);
            GpuPNIPC = itemView.findViewById(R.id.GpuPNIPC);
            HddPNIPC = itemView.findViewById(R.id.HddPNIPC);
            SsdPNIPC = itemView.findViewById(R.id.SsdPNIPC);
            RamPNIPC = itemView.findViewById(R.id.RamPNIPC);
            TecladoPNIPC = itemView.findViewById(R.id.TecladoPNIPC);
            PantallaPNIPC = itemView.findViewById(R.id.PantallaPNIPC);
            HuellaPNIPC = itemView.findViewById(R.id.HuellaPNIPC);
            BiosPNIPC = itemView.findViewById(R.id.BiosPNIPC);
            OsPNIPC = itemView.findViewById(R.id.OsPNIPC);

            BtnFavoritoFAIP = itemView.findViewById(R.id.BtnFavoritoFAIP);
            BtnVerIP = (Button) itemView.findViewById(R.id.BtnVerIP);

        }
    }

}
