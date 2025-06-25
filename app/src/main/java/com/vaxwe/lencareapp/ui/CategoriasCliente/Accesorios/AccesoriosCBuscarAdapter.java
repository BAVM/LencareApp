package com.vaxwe.lencareapp.ui.CategoriasCliente.Accesorios;

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

public class AccesoriosCBuscarAdapter extends RecyclerView.Adapter<AccesoriosCBuscarAdapter.MyViewHolderAC>{


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference data = FirebaseDatabase.getInstance().getReference();

    Context context;
    ArrayList<AccesoriosCModo> listAccesoriosC;

    public AccesoriosCBuscarAdapter(Context context, ArrayList<AccesoriosCModo> listAccesoriosC){

        this.context = context;
        this.listAccesoriosC = listAccesoriosC;
    }

    @NonNull
    @Override
    public MyViewHolderAC onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view_accesorios_c,parent,false);
        return new AccesoriosCBuscarAdapter.MyViewHolderAC(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccesoriosCBuscarAdapter.MyViewHolderAC holder, int position) {
        AccesoriosCModo accesoriosCModo = listAccesoriosC.get(position);

        holder.NombrePNACC.setText((accesoriosCModo.getPN()));
        holder.DescripcionPNACC.setText((accesoriosCModo.getFAMILIA()));
        holder.PaisPNACC.setText((accesoriosCModo.getPAIS()));
        holder.SlocPNACC.setText((accesoriosCModo.getSLOC()));
        holder.CpuPNACC.setText((accesoriosCModo.getCPU()));
        holder.GpuPNACC.setText((accesoriosCModo.getGPU()));
        holder.HddPNACC.setText((accesoriosCModo.getHDD()));
        holder.SsdPNACC.setText((accesoriosCModo.getSSD()));
        holder.RamPNACC.setText((accesoriosCModo.getRAM()));
        holder.TecladoPNACC.setText((accesoriosCModo.getTECLADO()));
        holder.PantallaPNACC.setText((accesoriosCModo.getPANTALLA()));
        holder.HuellaPNACC.setText((accesoriosCModo.getHUELLA()));
        holder.BiosPNACC.setText((accesoriosCModo.getBIOS()));
        holder.OsPNACC.setText((accesoriosCModo.getOS()));

        //BOTON VER PN
        holder.BtnVerAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DialogPlus dialogPlus = newDialog(holder.NombrePNACC.getContext()).setContentHolder
                                (new ViewHolder(R.layout.vista_ver_pn_accesorio)).setExpanded(true, 1700)
                        .setContentBackgroundResource(R.color.color18).create();

                //dialogPlus.show();

                View v = dialogPlus.getHolderView();
                TextView PN = v.findViewById(R.id.txtVerPnAC);
                TextView FAMILIA = v.findViewById(R.id.txtVerDescripcionAC);
                TextView PAIS = v.findViewById(R.id.txtVerPaisAC);
                TextView SLOC = v.findViewById(R.id.txtVerSlocAC);
                TextView CPU = v.findViewById(R.id.txtVerCpuAC);
                TextView GPU = v.findViewById(R.id.txtVerGpuAC);
                TextView HDD = v.findViewById(R.id.txtVerHddAC);
                TextView SSD = v.findViewById(R.id.txtVerSsdAC);
                TextView RAM = v.findViewById(R.id.txtVerRamAC);
                TextView TECLADO = v.findViewById(R.id.txtVerTecladoAC);
                TextView PANTALLA = v.findViewById(R.id.txtVerPantallaAC);
                TextView HUELLA = v.findViewById(R.id.txtVerHuellaAC);
                TextView BIOS = v.findViewById(R.id.txtVerBiosAC);
                TextView OS = v.findViewById(R.id.txtVerOsAC);

                PN.setText(accesoriosCModo.getPN());
                FAMILIA.setText(accesoriosCModo.getFAMILIA());
                PAIS.setText(accesoriosCModo.getPAIS());
                SLOC.setText(accesoriosCModo.getSLOC());
                CPU.setText(accesoriosCModo.getCPU());
                GPU.setText(accesoriosCModo.getGPU());
                HDD.setText(accesoriosCModo.getHDD());
                SSD.setText(accesoriosCModo.getSSD());
                RAM.setText(accesoriosCModo.getRAM());
                TECLADO.setText(accesoriosCModo.getTECLADO());
                PANTALLA.setText(accesoriosCModo.getPANTALLA());
                HUELLA.setText(accesoriosCModo.getHUELLA());
                BIOS.setText(accesoriosCModo.getBIOS());
                OS.setText(accesoriosCModo.getOS());

                dialogPlus.show();

            }
        });



        holder.BtnFavoritoFAAC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (compoundButton.isChecked()){

                    TextView PN = holder.NombrePNACC.findViewById(R.id.NombrePNACC);
                    TextView PAIS = holder.PaisPNACC.findViewById(R.id.PaisPNACC);
                    TextView SLOC = holder.SlocPNACC.findViewById(R.id.SlocPNACC);
                    TextView FAMILIA = holder.DescripcionPNACC.findViewById(R.id.DescripcionPNACC);
                    TextView CPU = holder.CpuPNACC.findViewById(R.id.CpuPNACC);
                    TextView GPU = holder.GpuPNACC.findViewById(R.id.GpuPNACC);
                    TextView HDD = holder.HddPNACC.findViewById(R.id.HddPNACC);
                    TextView SSD = holder.SsdPNACC.findViewById(R.id.SsdPNACC);
                    TextView RAM = holder.RamPNACC.findViewById(R.id.RamPNACC);
                    TextView TECLADO = holder.TecladoPNACC.findViewById(R.id.TecladoPNACC);
                    TextView PANTALLA = holder.PantallaPNACC.findViewById(R.id.PantallaPNACC);
                    TextView HUELLA = holder.HuellaPNACC.findViewById(R.id.HuellaPNACC);
                    TextView BIOS = holder.BiosPNACC.findViewById(R.id.BiosPNACC);
                    TextView OS = holder.OsPNACC.findViewById(R.id.OsPNACC);

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
                                    Toast.makeText(holder.BtnFavoritoFAAC.getContext(), "PN añadido a favoritos", Toast.LENGTH_SHORT).show();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(holder.BtnFavoritoFAAC.getContext(), "Error al añadir favorito.", Toast.LENGTH_SHORT).show();

                                }
                            });


                }else{
                    String key = data.child("posts").push().getKey();
                    FirebaseDatabase.getInstance().getReference().child("FAVORITOS").child(user.getUid()).child(key).removeValue();
                    Toast.makeText(holder.BtnFavoritoFAAC.getContext(), "PN removido de favoritos", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return listAccesoriosC.size();
    }

    public static class MyViewHolderAC extends RecyclerView.ViewHolder {

        TextView NombrePNACC, DescripcionPNACC, PaisPNACC, SlocPNACC, CpuPNACC, GpuPNACC, HddPNACC,
                SsdPNACC, RamPNACC, TecladoPNACC, PantallaPNACC, HuellaPNACC, BiosPNACC, OsPNACC;
        CheckBox BtnFavoritoFAAC;
        Button BtnVerAC;

        public MyViewHolderAC(@NonNull View itemView) {
            super(itemView);

            NombrePNACC = itemView.findViewById(R.id.NombrePNACC);
            DescripcionPNACC = itemView.findViewById(R.id.DescripcionPNACC);
            PaisPNACC = itemView.findViewById(R.id.PaisPNACC);
            SlocPNACC = itemView.findViewById(R.id.SlocPNACC);
            CpuPNACC = itemView.findViewById(R.id.CpuPNACC);
            GpuPNACC = itemView.findViewById(R.id.GpuPNACC);
            HddPNACC = itemView.findViewById(R.id.HddPNACC);
            SsdPNACC = itemView.findViewById(R.id.SsdPNACC);
            RamPNACC = itemView.findViewById(R.id.RamPNACC);
            TecladoPNACC = itemView.findViewById(R.id.TecladoPNACC);
            PantallaPNACC = itemView.findViewById(R.id.PantallaPNACC);
            HuellaPNACC = itemView.findViewById(R.id.HuellaPNACC);
            BiosPNACC = itemView.findViewById(R.id.BiosPNACC);
            OsPNACC = itemView.findViewById(R.id.OsPNACC);

            BtnFavoritoFAAC = itemView.findViewById(R.id.BtnFavoritoFAAC);
            BtnVerAC = (Button) itemView.findViewById(R.id.BtnVerAC);

        }
    }

}
