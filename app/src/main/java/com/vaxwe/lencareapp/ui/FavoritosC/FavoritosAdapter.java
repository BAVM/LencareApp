package com.vaxwe.lencareapp.ui.FavoritosC;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.vaxwe.lencareapp.R;

public class FavoritosAdapter extends FirebaseRecyclerAdapter<FavoritosModo, FavoritosAdapter.MyViewHolderFa> {


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public FavoritosAdapter(@NonNull FirebaseRecyclerOptions<FavoritosModo> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolderFa holder, int position, @NonNull FavoritosModo model) {

        holder.PAIS.setText(model.getPAIS());
        holder.SLOC.setText(model.getSLOC());
        holder.PN.setText(model.getPN());
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


        //CHECKBOX

        holder.BtnFavoritoFA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()){

                }else {
                    FirebaseDatabase.getInstance().getReference().child("FAVORITOS").child(user.getUid()).child(getRef(position).getKey()).removeValue()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    notifyItemRemoved(position);
                                    notifyItemChanged(position);
                                    notifyDataSetChanged();
                                    Toast.makeText(holder.BtnFavoritoFA.getContext(), "PN removido de favoritos", Toast.LENGTH_SHORT).show();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(holder.BtnFavoritoFA.getContext(), "Error al eliminar favorito.", Toast.LENGTH_SHORT).show();

                                }
                            });


                }

            }

        });


    }


    @NonNull
    @Override
    public MyViewHolderFa onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_favoritos_c,parent,false);

        return new MyViewHolderFa(view);
    }

    public class MyViewHolderFa extends RecyclerView.ViewHolder {

        TextView PAIS,SLOC,PN,FAMILIA,CPU,GPU,HDD,SSD,RAM,TECLADO,PANTALLA,HUELLA,BIOS,OS;
        CheckBox BtnFavoritoFA;

        public MyViewHolderFa(@NonNull View itemView) {
            super(itemView);

            PAIS = (TextView) itemView.findViewById(R.id.PaisFA);
            SLOC = (TextView) itemView.findViewById(R.id.SlocFA);
            PN = (TextView) itemView.findViewById(R.id.NombreFA);
            FAMILIA = (TextView) itemView.findViewById(R.id.DescripcionFA);
            CPU = (TextView) itemView.findViewById(R.id.CpuFA);
            GPU = (TextView) itemView.findViewById(R.id.GpuFA);
            HDD = (TextView) itemView.findViewById(R.id.HddFA);
            SSD = (TextView) itemView.findViewById(R.id.SsdFA);
            RAM = (TextView) itemView.findViewById(R.id.RamFA);
            TECLADO = (TextView) itemView.findViewById(R.id.TecladoFA);
            PANTALLA = (TextView) itemView.findViewById(R.id.PantallaFA);
            HUELLA = (TextView) itemView.findViewById(R.id.HuellaFA);
            BIOS = (TextView) itemView.findViewById(R.id.BiosFA);
            OS = (TextView) itemView.findViewById(R.id.OsFA);

            BtnFavoritoFA =(CheckBox) itemView.findViewById(R.id.BtnFavoritoFA);

        }
    }

}
