package com.vaxwe.lencareapp.ui.Categorias.Cat_Dispositivo;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.vaxwe.lencareapp.R;

public class ViewHolderCD extends RecyclerView.ViewHolder{

    View mView;

    private ViewHolderCD.ClickListener mClickListener;

    public interface ClickListener{
        void onItemClick(View view, int position);
    }

    public void setOnClickListener(ViewHolderCD.ClickListener clickListener){
        mClickListener = clickListener;
    }

    public ViewHolderCD(@NonNull View itemView) {
        super(itemView);
        mView = itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view,getBindingAdapterPosition());

            }
        });

    }

    public void SeteoCategoriaD(Context context, String categoria, String imagen){

        ImageView ImagenDispositivo;
        TextView NombreCategoriaD;

        ImagenDispositivo = mView.findViewById(R.id.ImagenDispositivo);
        NombreCategoriaD = mView.findViewById(R.id.NombreCategoriaD);

        NombreCategoriaD.setText(categoria);

        try{
            Picasso.get().load(imagen).placeholder(R.drawable.categorias2).into(ImagenDispositivo);
        }
        catch (Exception e){
            //Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            Picasso.get().load(R.drawable.categorias2).into(ImagenDispositivo);
        }
    }


}
