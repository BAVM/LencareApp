package com.vaxwe.lencareapp.ui.VerPnAdmin.ViewThinkpad;

import static com.orhanobut.dialogplus.DialogPlus.newDialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.vaxwe.lencareapp.R;


import java.util.HashMap;
import java.util.Map;


public class ThinkpadAdapter extends FirebaseRecyclerAdapter <ThinkpadModo, ThinkpadAdapter.myViewHolderT>{

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ThinkpadAdapter(@NonNull FirebaseRecyclerOptions<ThinkpadModo> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull myViewHolderT holder, int position, @NonNull ThinkpadModo model) {

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


        //EDITAR PN

        holder.BtnEditarT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final DialogPlus dialogPlus = newDialog(holder.PN.getContext()).setContentHolder
                        (new ViewHolder(R.layout.vista_editar_thinkpad)).setExpanded(true, 1300)
                        .setContentBackgroundResource(R.color.color18).create();

                //dialogPlus.show();

                View v = dialogPlus.getHolderView();
                EditText PN = v.findViewById(R.id.txtEditarPnT);
                EditText FAMILIA = v.findViewById(R.id.txtEditarDescripcionT);
                EditText PAIS = v.findViewById(R.id.txtEditarPaisT);
                EditText SLOC = v.findViewById(R.id.txtEditarSlocT);
                EditText CPU = v.findViewById(R.id.txtEditarCpuT);
                EditText GPU = v.findViewById(R.id.txtEditarGpuT);
                EditText HDD = v.findViewById(R.id.txtEditarHddT);
                EditText SSD = v.findViewById(R.id.txtEditarSsdT);
                EditText RAM = v.findViewById(R.id.txtEditarRamT);
                EditText TECLADO = v.findViewById(R.id.txtEditarTecladoT);
                EditText PANTALLA = v.findViewById(R.id.txtEditarPantallaT);
                EditText HUELLA = v.findViewById(R.id.txtEditarHuellaT);
                EditText BIOS = v.findViewById(R.id.txtEditarBiosT);
                EditText OS = v.findViewById(R.id.txtEditarOsT);

                /*if (PN.equals("") || FAMILIA.equals("") || PAIS.equals("") || SLOC.equals("") || CPU.equals("") || GPU.equals("") ||
                        HDD.equals("") || SSD.equals("") || RAM.equals("") || TECLADO.equals("") || PANTALLA.equals("") ||
                        HUELLA.equals("") || BIOS.equals("") || OS.equals("")) {
                    Toast.makeText(holder.PN.getContext(), "Por favor llene todos los campos", Toast.LENGTH_SHORT).show();

                } else {*/

                Button BtnActualizarT = v.findViewById(R.id.BtnactualziarT);

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

                BtnActualizarT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Map<String, Object> map = new HashMap<>();
                        map.put("PN", PN.getText().toString());
                        map.put("FAMILIA", FAMILIA.getText().toString());
                        map.put("PAIS", PAIS.getText().toString());
                        map.put("SLOC", SLOC.getText().toString());
                        map.put("CPU", CPU.getText().toString());
                        map.put("GPU", GPU.getText().toString());
                        map.put("HDD", HDD.getText().toString());
                        map.put("SSD", SSD.getText().toString());
                        map.put("RAM", RAM.getText().toString());
                        map.put("TECLADO", TECLADO.getText().toString());
                        map.put("PANTALLA", PANTALLA.getText().toString());
                        map.put("HUELLA", HUELLA.getText().toString());
                        map.put("BIOS", BIOS.getText().toString());
                        map.put("OS", OS.getText().toString());


                        FirebaseDatabase.getInstance().getReference().child("THINKPAD").child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.PN.getContext(), "Update exitoso!", Toast.LENGTH_SHORT).show();

                                        dialogPlus.dismiss();

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.PN.getContext(), "Update fallido!", Toast.LENGTH_SHORT).show();

                                        dialogPlus.dismiss();

                                    }
                                });

                    }
                });


                // }
            }
        });

        //BORRAR PN
        holder.BtnBorrarT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.PN.getContext());
                builder.setTitle("Estas seguro?");
                builder.setMessage("Si elimina el PN, no se podrá recuperar.");

                builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        FirebaseDatabase.getInstance().getReference().child("THINKPAD").child(getRef(position).getKey()).removeValue();

                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.PN.getContext(), "Operación cancelada", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.show();

            }
        });


    }



    @NonNull
    @Override
    public myViewHolderT onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_thinkpad,parent,false);
        return new myViewHolderT(view);
    }



    static class myViewHolderT extends RecyclerView.ViewHolder{

        TextView PAIS,SLOC,PN,FAMILIA,CPU,GPU,HDD,SSD,RAM,TECLADO,PANTALLA,HUELLA,BIOS,OS;

        Button BtnEditarT,BtnBorrarT;

        public myViewHolderT(@NonNull View itemView) {
            super(itemView);

            PAIS = (TextView) itemView.findViewById(R.id.PaisPNT);
            SLOC = (TextView) itemView.findViewById(R.id.SlocPNT);
            PN = (TextView) itemView.findViewById(R.id.NombrePNT);
            FAMILIA = (TextView) itemView.findViewById(R.id.DescripcionPNT);
            CPU = (TextView) itemView.findViewById(R.id.CpuPNT);
            GPU = (TextView) itemView.findViewById(R.id.GpuPNT);
            HDD = (TextView) itemView.findViewById(R.id.HddPNT);
            SSD = (TextView) itemView.findViewById(R.id.SsdPNT);
            RAM = (TextView) itemView.findViewById(R.id.RamPNT);
            TECLADO = (TextView) itemView.findViewById(R.id.TecladoPNT);
            PANTALLA = (TextView) itemView.findViewById(R.id.PantallaPNT);
            HUELLA = (TextView) itemView.findViewById(R.id.HuellaPNT);
            BIOS = (TextView) itemView.findViewById(R.id.BiosPNT);
            OS = (TextView) itemView.findViewById(R.id.OsPNT);

            BtnEditarT = (Button) itemView.findViewById(R.id.BtnEditarT);
            BtnBorrarT = (Button) itemView.findViewById(R.id.BtnBorrarT);


        }

    }




}










