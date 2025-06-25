package com.vaxwe.lencareapp.ui.CategoriasCliente.IdeacentreC;

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

public class IdeacentreCAdapter extends FirebaseRecyclerAdapter<IdeacentreCModo, IdeacentreCAdapter.myViewHolderIC> {


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public IdeacentreCAdapter(@NonNull FirebaseRecyclerOptions<IdeacentreCModo> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolderIC holder, int position, @NonNull IdeacentreCModo model) {


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
        holder.BtnVerIC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DialogPlus dialogPlus = newDialog(holder.PN.getContext()).setContentHolder
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
        holder.BtnFavoritoFAIC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (compoundButton.isChecked()) {

                    TextView PN = holder.PN.findViewById(R.id.NombrePNICC);
                    TextView PAIS = holder.PAIS.findViewById(R.id.PaisPNICC);
                    TextView SLOC = holder.SLOC.findViewById(R.id.SlocPNICC);
                    TextView FAMILIA = holder.FAMILIA.findViewById(R.id.DescripcionPNICC);
                    TextView CPU = holder.CPU.findViewById(R.id.CpuPNICC);
                    TextView GPU = holder.GPU.findViewById(R.id.GpuPNICC);
                    TextView HDD = holder.HDD.findViewById(R.id.HddPNICC);
                    TextView SSD = holder.SSD.findViewById(R.id.SsdPNICC);
                    TextView RAM = holder.RAM.findViewById(R.id.RamPNICC);
                    TextView TECLADO = holder.TECLADO.findViewById(R.id.TecladoPNICC);
                    TextView PANTALLA = holder.PANTALLA.findViewById(R.id.PantallaPNICC);
                    TextView HUELLA = holder.HUELLA.findViewById(R.id.HuellaPNICC);
                    TextView BIOS = holder.BIOS.findViewById(R.id.BiosPNICC);
                    TextView OS = holder.OS.findViewById(R.id.OsPNICC);

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
                                    Toast.makeText(holder.BtnFavoritoFAIC.getContext(), "PN añadido a favoritos", Toast.LENGTH_SHORT).show();


                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(holder.BtnFavoritoFAIC.getContext(), "Error al añadir favorito.", Toast.LENGTH_SHORT).show();

                                }
                            });


                } else {

                    FirebaseDatabase.getInstance().getReference().child("FAVORITOS").child(user.getUid()).child(getRef(position).getKey()).removeValue();
                    Toast.makeText(holder.BtnFavoritoFAIC.getContext(), "PN removido de favoritos", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @NonNull
    @Override
    public myViewHolderIC onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_ideacentre_c,parent,false);
        return new IdeacentreCAdapter.myViewHolderIC(view);
    }

    public class myViewHolderIC extends RecyclerView.ViewHolder {

        TextView PAIS,SLOC,PN,FAMILIA,CPU,GPU,HDD,SSD,RAM,TECLADO,PANTALLA,HUELLA,BIOS,OS;
        CheckBox BtnFavoritoFAIC;
        Button BtnVerIC;

        public myViewHolderIC(@NonNull View itemView) {
            super(itemView);

            PAIS = (TextView) itemView.findViewById(R.id.PaisPNICC);
            SLOC = (TextView) itemView.findViewById(R.id.SlocPNICC);
            PN = (TextView) itemView.findViewById(R.id.NombrePNICC);
            FAMILIA = (TextView) itemView.findViewById(R.id.DescripcionPNICC);
            CPU = (TextView) itemView.findViewById(R.id.CpuPNICC);
            GPU = (TextView) itemView.findViewById(R.id.GpuPNICC);
            HDD = (TextView) itemView.findViewById(R.id.HddPNICC);
            SSD = (TextView) itemView.findViewById(R.id.SsdPNICC);
            RAM = (TextView) itemView.findViewById(R.id.RamPNICC);
            TECLADO = (TextView) itemView.findViewById(R.id.TecladoPNICC);
            PANTALLA = (TextView) itemView.findViewById(R.id.PantallaPNICC);
            HUELLA = (TextView) itemView.findViewById(R.id.HuellaPNICC);
            BIOS = (TextView) itemView.findViewById(R.id.BiosPNICC);
            OS = (TextView) itemView.findViewById(R.id.OsPNICC);

            BtnFavoritoFAIC =(CheckBox) itemView.findViewById(R.id.BtnFavoritoFAIC);
            BtnVerIC = (Button) itemView.findViewById(R.id.BtnVerIC);

        }
    }

}
