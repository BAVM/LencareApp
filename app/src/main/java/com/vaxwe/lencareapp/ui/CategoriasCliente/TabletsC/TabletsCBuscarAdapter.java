package com.vaxwe.lencareapp.ui.CategoriasCliente.TabletsC;

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

public class TabletsCBuscarAdapter extends RecyclerView.Adapter<TabletsCBuscarAdapter.myViewHolderTA>{


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference data = FirebaseDatabase.getInstance().getReference();

    Context context;
    ArrayList<TabletsCModo> listTabletsC;

    public TabletsCBuscarAdapter(Context context, ArrayList<TabletsCModo> listTabletsC) {
        this.context = context;
        this.listTabletsC = listTabletsC;
    }

    @NonNull
    @Override
    public myViewHolderTA onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view_tablets_c,parent,false);
        return new TabletsCBuscarAdapter.myViewHolderTA(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolderTA holder, int position) {
        TabletsCModo tabletsCModo = listTabletsC.get(position);

        holder.NombrePNTAC.setText((tabletsCModo.getPN()));
        holder.DescripcionPNTAC.setText((tabletsCModo.getFAMILIA()));
        holder.PaisPNTAC.setText((tabletsCModo.getPAIS()));
        holder.SlocPNTAC.setText((tabletsCModo.getSLOC()));
        holder.CpuPNTAC.setText((tabletsCModo.getCPU()));
        holder.GpuPNTAC.setText((tabletsCModo.getGPU()));
        holder.HddPNTAC.setText((tabletsCModo.getHDD()));
        holder.SsdPNTAC.setText((tabletsCModo.getSSD()));
        holder.RamPNTAC.setText((tabletsCModo.getRAM()));
        holder.TecladoPNTAC.setText((tabletsCModo.getTECLADO()));
        holder.PantallaPNTAC.setText((tabletsCModo.getPANTALLA()));
        holder.HuellaPNTAC.setText((tabletsCModo.getHUELLA()));
        holder.BiosPNTAC.setText((tabletsCModo.getBIOS()));
        holder.OsPNTAC.setText((tabletsCModo.getOS()));


        //BOTON VER PN
        holder.BtnVerTAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DialogPlus dialogPlus = newDialog(holder.NombrePNTAC.getContext()).setContentHolder
                                (new ViewHolder(R.layout.vista_ver_pn_tablets)).setExpanded(true, 1700)
                        .setContentBackgroundResource(R.color.color18).create();

                //dialogPlus.show();

                View v = dialogPlus.getHolderView();
                TextView PN = v.findViewById(R.id.txtVerPnTAC);
                TextView FAMILIA = v.findViewById(R.id.txtVerDescripcionTAC);
                TextView PAIS = v.findViewById(R.id.txtVerPaisTAC);
                TextView SLOC = v.findViewById(R.id.txtVerSlocTAC);
                TextView CPU = v.findViewById(R.id.txtVerCpuTAC);
                TextView GPU = v.findViewById(R.id.txtVerGpuTAC);
                TextView HDD = v.findViewById(R.id.txtVerHddTAC);
                TextView SSD = v.findViewById(R.id.txtVerSsdTAC);
                TextView RAM = v.findViewById(R.id.txtVerRamTAC);
                TextView TECLADO = v.findViewById(R.id.txtVerTecladoTAC);
                TextView PANTALLA = v.findViewById(R.id.txtVerPantallaTAC);
                TextView HUELLA = v.findViewById(R.id.txtVerHuellaTAC);
                TextView BIOS = v.findViewById(R.id.txtVerBiosTAC);
                TextView OS = v.findViewById(R.id.txtVerOsTAC);

                PN.setText(tabletsCModo.getPN());
                FAMILIA.setText(tabletsCModo.getFAMILIA());
                PAIS.setText(tabletsCModo.getPAIS());
                SLOC.setText(tabletsCModo.getSLOC());
                CPU.setText(tabletsCModo.getCPU());
                GPU.setText(tabletsCModo.getGPU());
                HDD.setText(tabletsCModo.getHDD());
                SSD.setText(tabletsCModo.getSSD());
                RAM.setText(tabletsCModo.getRAM());
                TECLADO.setText(tabletsCModo.getTECLADO());
                PANTALLA.setText(tabletsCModo.getPANTALLA());
                HUELLA.setText(tabletsCModo.getHUELLA());
                BIOS.setText(tabletsCModo.getBIOS());
                OS.setText(tabletsCModo.getOS());

                dialogPlus.show();

            }
        });




        holder.BtnFavoritoFATA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (compoundButton.isChecked()){

                    TextView PN = holder.NombrePNTAC.findViewById(R.id.NombrePNTAC);
                    TextView PAIS = holder.PaisPNTAC.findViewById(R.id.PaisPNTAC);
                    TextView SLOC = holder.SlocPNTAC.findViewById(R.id.SlocPNTAC);
                    TextView FAMILIA = holder.DescripcionPNTAC.findViewById(R.id.DescripcionPNTAC);
                    TextView CPU = holder.CpuPNTAC.findViewById(R.id.CpuPNTAC);
                    TextView GPU = holder.GpuPNTAC.findViewById(R.id.GpuPNTAC);
                    TextView HDD = holder.HddPNTAC.findViewById(R.id.HddPNTAC);
                    TextView SSD = holder.SsdPNTAC.findViewById(R.id.SsdPNTAC);
                    TextView RAM = holder.RamPNTAC.findViewById(R.id.RamPNTAC);
                    TextView TECLADO = holder.TecladoPNTAC.findViewById(R.id.TecladoPNTAC);
                    TextView PANTALLA = holder.PantallaPNTAC.findViewById(R.id.PantallaPNTAC);
                    TextView HUELLA = holder.HuellaPNTAC.findViewById(R.id.HuellaPNTAC);
                    TextView BIOS = holder.BiosPNTAC.findViewById(R.id.BiosPNTAC);
                    TextView OS = holder.OsPNTAC.findViewById(R.id.OsPNTAC);

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
                                    Toast.makeText(holder.BtnFavoritoFATA.getContext(), "PN añadido a favoritos", Toast.LENGTH_SHORT).show();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(holder.BtnFavoritoFATA.getContext(), "Error al añadir favorito.", Toast.LENGTH_SHORT).show();

                                }
                            });


                }else{
                    String key = data.child("posts").push().getKey();
                    FirebaseDatabase.getInstance().getReference().child("FAVORITOS").child(user.getUid()).child(key).removeValue();
                    Toast.makeText(holder.BtnFavoritoFATA.getContext(), "PN removido de favoritos", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return listTabletsC.size();
    }

    public class myViewHolderTA extends RecyclerView.ViewHolder {

        TextView NombrePNTAC, DescripcionPNTAC, PaisPNTAC, SlocPNTAC, CpuPNTAC, GpuPNTAC, HddPNTAC,
                SsdPNTAC, RamPNTAC, TecladoPNTAC, PantallaPNTAC, HuellaPNTAC, BiosPNTAC, OsPNTAC;
        CheckBox BtnFavoritoFATA;
        Button BtnVerTAC;

        public myViewHolderTA(@NonNull View itemView) {
            super(itemView);

            NombrePNTAC = itemView.findViewById(R.id.NombrePNTAC);
            DescripcionPNTAC = itemView.findViewById(R.id.DescripcionPNTAC);
            PaisPNTAC = itemView.findViewById(R.id.PaisPNTAC);
            SlocPNTAC = itemView.findViewById(R.id.SlocPNTAC);
            CpuPNTAC = itemView.findViewById(R.id.CpuPNTAC);
            GpuPNTAC = itemView.findViewById(R.id.GpuPNTAC);
            HddPNTAC = itemView.findViewById(R.id.HddPNTAC);
            SsdPNTAC = itemView.findViewById(R.id.SsdPNTAC);
            RamPNTAC = itemView.findViewById(R.id.RamPNTAC);
            TecladoPNTAC = itemView.findViewById(R.id.TecladoPNTAC);
            PantallaPNTAC = itemView.findViewById(R.id.PantallaPNTAC);
            HuellaPNTAC = itemView.findViewById(R.id.HuellaPNTAC);
            BiosPNTAC = itemView.findViewById(R.id.BiosPNTAC);
            OsPNTAC = itemView.findViewById(R.id.OsPNTAC);

            BtnFavoritoFATA = itemView.findViewById(R.id.BtnFavoritoFATA);
            BtnVerTAC = (Button) itemView.findViewById(R.id.BtnVerTAC);


        }
    }
}
