package com.vaxwe.lencareapp.ui.CategoriasCliente.YogaC;

import static com.orhanobut.dialogplus.DialogPlus.newDialog;

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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.vaxwe.lencareapp.R;

import java.util.HashMap;
import java.util.Map;

public class YogaCAdapter extends FirebaseRecyclerAdapter<YogaCModo,YogaCAdapter.myViewHolderYO> {


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public YogaCAdapter(@NonNull FirebaseRecyclerOptions<YogaCModo> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolderYO holder, int position, @NonNull YogaCModo model) {

        holder.PN.setText(model.getPN());
        holder.PAIS.setText(model.getPAIS());
        holder.SLOC.setText(model.getSLOC());
        holder.FAMILIA.setText(model.getFAMILIA());
        holder.CPU.setText(model.getCPU());
        holder.GPU.setText(model.getGPU());
        holder.HDD.setText(model.getHDD());
        holder.SSD.setText(model.getSSD());
        holder.RAM.setText(model.getRAM());
        holder.TECLADO.setText(model.getTECLADO());
        holder.PANTALLA.setText(model.getPANTALLA());
        holder.HUELLA.setText(model.getHUELLA());
        holder.BIOS.setText(model.getBIOS());
        holder.OS.setText(model.getOS());

        //BOTON VER PN
        holder.BtnVerYOC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DialogPlus dialogPlus = newDialog(holder.PN.getContext()).setContentHolder
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

                PN.setText(model.getPN());
                FAMILIA.setText(model.getFAMILIA());
                PAIS.setText(model.getPAIS());
                SLOC.setText(model.getSLOC());
                CPU.setText(model.getCPU());
                GPU.setText(model.getGPU());
                HDD.setText(model.getHDD());
                SSD.setText(model.getSSD());
                RAM.setText(model.getRAM());
                TECLADO.setText(model.getTECLADO());
                PANTALLA.setText(model.getPANTALLA());
                HUELLA.setText(model.getHUELLA());
                BIOS.setText(model.getBIOS());
                OS.setText(model.getOS());

                dialogPlus.show();

            }
        });



        //CHECKBOX
        holder.BtnFavoritoFAYO.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (compoundButton.isChecked()) {

                    TextView PN = holder.PN.findViewById(R.id.NombrePNYOC);
                    TextView PAIS = holder.PAIS.findViewById(R.id.PaisPNYOC);
                    TextView SLOC = holder.SLOC.findViewById(R.id.SlocPNYOC);
                    TextView FAMILIA = holder.FAMILIA.findViewById(R.id.DescripcionPNYOC);
                    TextView CPU = holder.CPU.findViewById(R.id.CpuPNYOC);
                    TextView GPU = holder.GPU.findViewById(R.id.GpuPNYOC);
                    TextView HDD = holder.HDD.findViewById(R.id.HddPNYOC);
                    TextView SSD = holder.SSD.findViewById(R.id.SsdPNYOC);
                    TextView RAM = holder.RAM.findViewById(R.id.RamPNYOC);
                    TextView TECLADO = holder.TECLADO.findViewById(R.id.TecladoPNYOC);
                    TextView PANTALLA = holder.PANTALLA.findViewById(R.id.PantallaPNYOC);
                    TextView HUELLA = holder.HUELLA.findViewById(R.id.HuellaPNYOC);
                    TextView BIOS = holder.BIOS.findViewById(R.id.BiosPNYOC);
                    TextView OS = holder.OS.findViewById(R.id.OsPNYOC);

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


                    FirebaseDatabase.getInstance().getReference().child("FAVORITOS").child(user.getUid()).child(getRef(position).getKey()).updateChildren(fav)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(holder.BtnFavoritoFAYO.getContext(), "PN añadido a favoritos", Toast.LENGTH_SHORT).show();


                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(holder.BtnFavoritoFAYO.getContext(), "Error al añadir favorito.", Toast.LENGTH_SHORT).show();

                                }
                            });


                } else {

                    FirebaseDatabase.getInstance().getReference().child("FAVORITOS").child(user.getUid()).child(getRef(position).getKey()).removeValue();
                    Toast.makeText(holder.BtnFavoritoFAYO.getContext(), "PN removido de favoritos", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @NonNull
    @Override
    public myViewHolderYO onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_yoga_c,parent,false);
        return new YogaCAdapter.myViewHolderYO(view);
    }

    public class myViewHolderYO extends RecyclerView.ViewHolder {

        TextView PAIS,SLOC,PN,FAMILIA,CPU,GPU,HDD,SSD,RAM,TECLADO,PANTALLA,HUELLA,BIOS,OS;
        CheckBox BtnFavoritoFAYO;
        Button BtnVerYOC;

        public myViewHolderYO(@NonNull View itemView) {
            super(itemView);

            PAIS = (TextView) itemView.findViewById(R.id.PaisPNYOC);
            SLOC = (TextView) itemView.findViewById(R.id.SlocPNYOC);
            PN = (TextView) itemView.findViewById(R.id.NombrePNYOC);
            FAMILIA = (TextView) itemView.findViewById(R.id.DescripcionPNYOC);
            CPU = (TextView) itemView.findViewById(R.id.CpuPNYOC);
            GPU = (TextView) itemView.findViewById(R.id.GpuPNYOC);
            HDD = (TextView) itemView.findViewById(R.id.HddPNYOC);
            SSD = (TextView) itemView.findViewById(R.id.SsdPNYOC);
            RAM = (TextView) itemView.findViewById(R.id.RamPNYOC);
            TECLADO = (TextView) itemView.findViewById(R.id.TecladoPNYOC);
            PANTALLA = (TextView) itemView.findViewById(R.id.PantallaPNYOC);
            HUELLA = (TextView) itemView.findViewById(R.id.HuellaPNYOC);
            BIOS = (TextView) itemView.findViewById(R.id.BiosPNYOC);
            OS = (TextView) itemView.findViewById(R.id.OsPNYOC);

            BtnFavoritoFAYO =(CheckBox) itemView.findViewById(R.id.BtnFavoritoFAYO);
            BtnVerYOC = (Button) itemView.findViewById(R.id.BtnVerYOC);


        }
    }

}
