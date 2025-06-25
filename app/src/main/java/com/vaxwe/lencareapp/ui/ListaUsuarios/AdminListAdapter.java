package com.vaxwe.lencareapp.ui.ListaUsuarios;

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

public class AdminListAdapter extends FirebaseRecyclerAdapter<AdminListaModel,AdminListAdapter.ViewAdminHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AdminListAdapter(@NonNull FirebaseRecyclerOptions<AdminListaModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AdminListAdapter.ViewAdminHolder holder, int position, @NonNull AdminListaModel model) {

        holder.NombreAdmin.setText(model.getNOMBRE());
        holder.ApellidoAdmin.setText(model.getAPELLIDO());
        holder.EmailAdmin.setText(model.getEMAIL());
        holder.RollAdmin.setText(model.getROLL());

        //BOTON EDITAR

        holder.BtnEditarAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DialogPlus dialogPlus = newDialog(holder.NombreAdmin.getContext()).setContentHolder
                                (new ViewHolder(R.layout.vista_editar_admin)).setExpanded(true, 1000)
                        .setContentBackgroundResource(R.color.color18).create();

                //dialogPlus.show();

                View v = dialogPlus.getHolderView();
                EditText NOMBRE = v.findViewById(R.id.txtEditarNombreAdmin);
                EditText APELLIDO = v.findViewById(R.id.txtEditarApellidoAdmin);
                EditText EMAIL = v.findViewById(R.id.txtEditarEmailAdmin);
                EditText ROLL = v.findViewById(R.id.txtEditarRollAdmin);

                Button BtnactualziarAdmin = v.findViewById(R.id.BtnactualziarAdmin);

                NOMBRE.setText(model.getNOMBRE());
                APELLIDO.setText(model.getAPELLIDO());
                EMAIL.setText(model.getEMAIL());
                ROLL.setText(model.getROLL());

                dialogPlus.show();

                BtnactualziarAdmin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("NOMBRE", NOMBRE.getText().toString());
                        map.put("APELLIDO", APELLIDO.getText().toString());
                        map.put("EMAIL", EMAIL.getText().toString());
                        map.put("ROLL", ROLL.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("DBAdmin").child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.NombreAdmin.getContext(), "Update exitoso!", Toast.LENGTH_SHORT).show();

                                        dialogPlus.dismiss();

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.NombreAdmin.getContext(), "Update fallido!", Toast.LENGTH_SHORT).show();

                                        dialogPlus.dismiss();

                                    }
                                });

                    }
                });



            }
        });

        //BORRAR PN
        holder.BtnBorrarAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.NombreAdmin.getContext());
                builder.setTitle("Estas seguro?");
                builder.setMessage("Esta seguro que desea elimiar el usuario?");

                builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        FirebaseDatabase.getInstance().getReference().child("DBAdmin").child(getRef(position).getKey()).removeValue();

                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.NombreAdmin.getContext(), "Operación cancelada", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.show();

            }
        });




    }

    @NonNull
    @Override
    public ViewAdminHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_lista_admin,parent,false);
        return new ViewAdminHolder(view);

    }

    public class ViewAdminHolder extends RecyclerView.ViewHolder {

        TextView NombreAdmin,ApellidoAdmin,EmailAdmin,RollAdmin;

        Button BtnEditarAdmin,BtnBorrarAdmin;

        public ViewAdminHolder(@NonNull View itemView) {
            super(itemView);

            NombreAdmin = (TextView) itemView.findViewById(R.id.NombreAdmin);
            ApellidoAdmin = (TextView) itemView.findViewById(R.id.ApellidoAdmin);
            EmailAdmin = (TextView) itemView.findViewById(R.id.EmailAdmin);
            RollAdmin = (TextView) itemView.findViewById(R.id.RollAdmin);

            BtnEditarAdmin = (Button) itemView.findViewById(R.id.BtnEditarAdmin);
            BtnBorrarAdmin = (Button) itemView.findViewById(R.id.BtnBorrarAdmin);


        }
    }

}
