package com.vaxwe.lencareapp.ui.VerPnAdmin.ViewIdeacentre;

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


public class IdeacentreAdapter extends FirebaseRecyclerAdapter<IdeacentreModo, IdeacentreAdapter.myViewHolderIC> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public IdeacentreAdapter(@NonNull FirebaseRecyclerOptions<IdeacentreModo> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull IdeacentreAdapter.myViewHolderIC holder, int position, @NonNull IdeacentreModo model) {

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

        holder.BtnEditarIC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final DialogPlus dialogPlus = newDialog(holder.PN.getContext()).setContentHolder
                        (new ViewHolder(R.layout.vista_editar_ideacentre)).setExpanded(true, 1300)
                        .setContentBackgroundResource(R.color.color18).create();

                //dialogPlus.show();

                View v = dialogPlus.getHolderView();
                EditText PN = v.findViewById(R.id.txtEditarPnIC);
                EditText FAMILIA = v.findViewById(R.id.txtEditarDescripcionIC);
                EditText PAIS = v.findViewById(R.id.txtEditarPaisIC);
                EditText SLOC = v.findViewById(R.id.txtEditarSlocIC);
                EditText CPU = v.findViewById(R.id.txtEditarCpuIC);
                EditText GPU = v.findViewById(R.id.txtEditarGpuIC);
                EditText HDD = v.findViewById(R.id.txtEditarHddIC);
                EditText SSD = v.findViewById(R.id.txtEditarSsdIC);
                EditText RAM = v.findViewById(R.id.txtEditarRamIC);
                EditText TECLADO = v.findViewById(R.id.txtEditarTecladoIC);
                EditText PANTALLA = v.findViewById(R.id.txtEditarPantallaIC);
                EditText HUELLA = v.findViewById(R.id.txtEditarHuellaIC);
                EditText BIOS = v.findViewById(R.id.txtEditarBiosIC);
                EditText OS = v.findViewById(R.id.txtEditarOsIC);

                Button BtnActualizarIC = v.findViewById(R.id.BtnactualziarIC);

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

                BtnActualizarIC.setOnClickListener(new View.OnClickListener() {
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

                        FirebaseDatabase.getInstance().getReference().child("IDEACENTRE").child(getRef(position).getKey()).updateChildren(map)
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
        holder.BtnBorrarIC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.PN.getContext());
                builder.setTitle("Estas seguro?");
                builder.setMessage("Si elimina el PN, no se podrá recuperar.");

                builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        FirebaseDatabase.getInstance().getReference().child("IDEACENTRE").child(getRef(position).getKey()).removeValue();

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
    public IdeacentreAdapter.myViewHolderIC onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_ideacentre, parent, false);
        return new IdeacentreAdapter.myViewHolderIC(view);
    }

    static class myViewHolderIC extends RecyclerView.ViewHolder {

        TextView PAIS, SLOC, PN, FAMILIA, CPU, GPU, HDD, SSD, RAM, TECLADO, PANTALLA, HUELLA, BIOS, OS;

        Button BtnEditarIC, BtnBorrarIC;

        public myViewHolderIC(@NonNull View itemView) {
            super(itemView);

            PAIS = (TextView) itemView.findViewById(R.id.PaisPNIC);
            SLOC = (TextView) itemView.findViewById(R.id.SlocPNIC);
            PN = (TextView) itemView.findViewById(R.id.NombrePNIC);
            FAMILIA = (TextView) itemView.findViewById(R.id.DescripcionPNIC);
            CPU = (TextView) itemView.findViewById(R.id.CpuPNIC);
            GPU = (TextView) itemView.findViewById(R.id.GpuPNIC);
            HDD = (TextView) itemView.findViewById(R.id.HddPNIC);
            SSD = (TextView) itemView.findViewById(R.id.SsdPNIC);
            RAM = (TextView) itemView.findViewById(R.id.RamPNIC);
            TECLADO = (TextView) itemView.findViewById(R.id.TecladoPNIC);
            PANTALLA = (TextView) itemView.findViewById(R.id.PantallaPNIC);
            HUELLA = (TextView) itemView.findViewById(R.id.HuellaPNIC);
            BIOS = (TextView) itemView.findViewById(R.id.BiosPNIC);
            OS = (TextView) itemView.findViewById(R.id.OsPNIC);

            BtnEditarIC = (Button) itemView.findViewById(R.id.BtnEditarIC);
            BtnBorrarIC = (Button) itemView.findViewById(R.id.BtnBorrarIC);


        }

    }

}
