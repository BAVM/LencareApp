package com.vaxwe.lencareapp.ui.VerPnAdmin.ViewYoga;

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


public class YogaAdapter extends FirebaseRecyclerAdapter<YogaModo, YogaAdapter.myViewHolderYO> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public YogaAdapter(@NonNull FirebaseRecyclerOptions<YogaModo> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull YogaAdapter.myViewHolderYO holder, int position, @NonNull YogaModo model) {

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

        holder.BtnEditarYO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final DialogPlus dialogPlus = newDialog(holder.PN.getContext()).setContentHolder
                        (new ViewHolder(R.layout.vista_editar_yoga)).setExpanded(true, 1300)
                        .setContentBackgroundResource(R.color.color18).create();

                //dialogPlus.show();

                View v = dialogPlus.getHolderView();
                EditText PN = v.findViewById(R.id.txtEditarPnYO);
                EditText FAMILIA = v.findViewById(R.id.txtEditarDescripcionYO);
                EditText PAIS = v.findViewById(R.id.txtEditarPaisYO);
                EditText SLOC = v.findViewById(R.id.txtEditarSlocYO);
                EditText CPU = v.findViewById(R.id.txtEditarCpuYO);
                EditText GPU = v.findViewById(R.id.txtEditarGpuYO);
                EditText HDD = v.findViewById(R.id.txtEditarHddYO);
                EditText SSD = v.findViewById(R.id.txtEditarSsdYO);
                EditText RAM = v.findViewById(R.id.txtEditarRamYO);
                EditText TECLADO = v.findViewById(R.id.txtEditarTecladoYO);
                EditText PANTALLA = v.findViewById(R.id.txtEditarPantallaYO);
                EditText HUELLA = v.findViewById(R.id.txtEditarHuellaYO);
                EditText BIOS = v.findViewById(R.id.txtEditarBiosYO);
                EditText OS = v.findViewById(R.id.txtEditarOsYO);

                Button BtnActualizarYO = v.findViewById(R.id.BtnactualziarYO);

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

                BtnActualizarYO.setOnClickListener(new View.OnClickListener() {
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

                        FirebaseDatabase.getInstance().getReference().child("YOGA").child(getRef(position).getKey()).updateChildren(map)
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
        holder.BtnBorrarYO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.PN.getContext());
                builder.setTitle("Estas seguro?");
                builder.setMessage("Si elimina el PN, no se podrá recuperar.");

                builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        FirebaseDatabase.getInstance().getReference().child("YOGA").child(getRef(position).getKey()).removeValue();

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
    public YogaAdapter.myViewHolderYO onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_yoga, parent, false);
        return new YogaAdapter.myViewHolderYO(view);
    }

    static class myViewHolderYO extends RecyclerView.ViewHolder {

        TextView PAIS, SLOC, PN, FAMILIA, CPU, GPU, HDD, SSD, RAM, TECLADO, PANTALLA, HUELLA, BIOS, OS;

        Button BtnEditarYO, BtnBorrarYO;

        public myViewHolderYO(@NonNull View itemView) {
            super(itemView);

            PAIS = (TextView) itemView.findViewById(R.id.PaisPNYO);
            SLOC = (TextView) itemView.findViewById(R.id.SlocPNYO);
            PN = (TextView) itemView.findViewById(R.id.NombrePNYO);
            FAMILIA = (TextView) itemView.findViewById(R.id.DescripcionPNYO);
            CPU = (TextView) itemView.findViewById(R.id.CpuPNYO);
            GPU = (TextView) itemView.findViewById(R.id.GpuPNYO);
            HDD = (TextView) itemView.findViewById(R.id.HddPNYO);
            SSD = (TextView) itemView.findViewById(R.id.SsdPNYO);
            RAM = (TextView) itemView.findViewById(R.id.RamPNYO);
            TECLADO = (TextView) itemView.findViewById(R.id.TecladoPNYO);
            PANTALLA = (TextView) itemView.findViewById(R.id.PantallaPNYO);
            HUELLA = (TextView) itemView.findViewById(R.id.HuellaPNYO);
            BIOS = (TextView) itemView.findViewById(R.id.BiosPNYO);
            OS = (TextView) itemView.findViewById(R.id.OsPNYO);

            BtnEditarYO = (Button) itemView.findViewById(R.id.BtnEditarYO);
            BtnBorrarYO = (Button) itemView.findViewById(R.id.BtnBorrarYO);


        }

    }


}
