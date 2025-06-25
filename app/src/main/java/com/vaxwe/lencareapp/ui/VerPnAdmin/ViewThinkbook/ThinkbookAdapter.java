package com.vaxwe.lencareapp.ui.VerPnAdmin.ViewThinkbook;

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


public class ThinkbookAdapter extends FirebaseRecyclerAdapter<ThinkbookModo, ThinkbookAdapter.myViewHolderTB> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ThinkbookAdapter(@NonNull FirebaseRecyclerOptions<ThinkbookModo> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ThinkbookAdapter.myViewHolderTB holder, int position, @NonNull ThinkbookModo model) {

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

        holder.BtnEditarTB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final DialogPlus dialogPlus = newDialog(holder.PN.getContext()).setContentHolder
                        (new ViewHolder(R.layout.vista_editar_thinkbook)).setExpanded(true, 1300)
                        .setContentBackgroundResource(R.color.color18).create();

                //dialogPlus.show();

                View v = dialogPlus.getHolderView();
                EditText PN = v.findViewById(R.id.txtEditarPnTB);
                EditText FAMILIA = v.findViewById(R.id.txtEditarDescripcionTB);
                EditText PAIS = v.findViewById(R.id.txtEditarPaisTB);
                EditText SLOC = v.findViewById(R.id.txtEditarSlocTB);
                EditText CPU = v.findViewById(R.id.txtEditarCpuTB);
                EditText GPU = v.findViewById(R.id.txtEditarGpuTB);
                EditText HDD = v.findViewById(R.id.txtEditarHddTB);
                EditText SSD = v.findViewById(R.id.txtEditarSsdTB);
                EditText RAM = v.findViewById(R.id.txtEditarRamTB);
                EditText TECLADO = v.findViewById(R.id.txtEditarTecladoTB);
                EditText PANTALLA = v.findViewById(R.id.txtEditarPantallaTB);
                EditText HUELLA = v.findViewById(R.id.txtEditarHuellaTB);
                EditText BIOS = v.findViewById(R.id.txtEditarBiosTB);
                EditText OS = v.findViewById(R.id.txtEditarOsTB);

                Button BtnActualizarTB = v.findViewById(R.id.BtnactualziarTB);

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

                BtnActualizarTB.setOnClickListener(new View.OnClickListener() {
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

                        FirebaseDatabase.getInstance().getReference().child("THINKBOOK").child(getRef(position).getKey()).updateChildren(map)
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

            }
        });

        //BORRAR PN
        holder.BtnBorrarTB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.PN.getContext());
                builder.setTitle("Estas seguro?");
                builder.setMessage("Si elimina el PN, no se podrá recuperar.");

                builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        FirebaseDatabase.getInstance().getReference().child("THINKBOOK").child(getRef(position).getKey()).removeValue();

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
    public ThinkbookAdapter.myViewHolderTB onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_thinkbook, parent, false);
        return new ThinkbookAdapter.myViewHolderTB(view);
    }

    static class myViewHolderTB extends RecyclerView.ViewHolder {

        TextView PAIS, SLOC, PN, FAMILIA, CPU, GPU, HDD, SSD, RAM, TECLADO, PANTALLA, HUELLA, BIOS, OS;

        Button BtnEditarTB, BtnBorrarTB;

        public myViewHolderTB(@NonNull View itemView) {
            super(itemView);

            PAIS = (TextView) itemView.findViewById(R.id.PaisPNTB);
            SLOC = (TextView) itemView.findViewById(R.id.SlocPNTB);
            PN = (TextView) itemView.findViewById(R.id.NombrePNTB);
            FAMILIA = (TextView) itemView.findViewById(R.id.DescripcionPNTB);
            CPU = (TextView) itemView.findViewById(R.id.CpuPNTB);
            GPU = (TextView) itemView.findViewById(R.id.GpuPNTB);
            HDD = (TextView) itemView.findViewById(R.id.HddPNTB);
            SSD = (TextView) itemView.findViewById(R.id.SsdPNTB);
            RAM = (TextView) itemView.findViewById(R.id.RamPNTB);
            TECLADO = (TextView) itemView.findViewById(R.id.TecladoPNTB);
            PANTALLA = (TextView) itemView.findViewById(R.id.PantallaPNTB);
            HUELLA = (TextView) itemView.findViewById(R.id.HuellaPNTB);
            BIOS = (TextView) itemView.findViewById(R.id.BiosPNTB);
            OS = (TextView) itemView.findViewById(R.id.OsPNTB);

            BtnEditarTB = (Button) itemView.findViewById(R.id.BtnEditarTB);
            BtnBorrarTB = (Button) itemView.findViewById(R.id.BtnBorrarTB);


        }

    }


}
