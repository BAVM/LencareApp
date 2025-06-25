package com.vaxwe.lencareapp.ui.cliente.info_cliente;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vaxwe.lencareapp.R;

import java.util.List;

public class InfoRecyclerAdapter extends RecyclerView.Adapter<InfoRecyclerAdapter.InfoRecyclerVH> {

    List<InfoRecyclerView> infoRecyclerViewsList;

    public InfoRecyclerAdapter(List<InfoRecyclerView> infoRecyclerViewsList) {
        this.infoRecyclerViewsList = infoRecyclerViewsList;
    }

    @NonNull
    @Override
    public InfoRecyclerAdapter.InfoRecyclerVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_recyclerview,parent,false);
        return new InfoRecyclerVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoRecyclerAdapter.InfoRecyclerVH holder, int position) {

        InfoRecyclerView infoRecyclerView = infoRecyclerViewsList.get(position);

        holder.TXTInformacionT0TXT.setText(infoRecyclerView.getTXTInformacionT0());
        holder.TXTInformacionT1TXT.setText(infoRecyclerView.getTXTInformacionT1());
        holder.TXTInformacionT2TXT.setText(infoRecyclerView.getTXTInformacionT2());
        holder.TXTInformacionT3TXT.setText(infoRecyclerView.getTXTInformacionT3());
        holder.TXTInformacionT4TXT.setText(infoRecyclerView.getTXTInformacionT4());
        holder.TXTInformacionT5TXT.setText(infoRecyclerView.getTXTInformacionT5());
        holder.TXTInformacionT6TXT.setText(infoRecyclerView.getTXTInformacionT6());

        boolean isInfo_explandible = infoRecyclerViewsList.get(position).isInfo_explandible();
        holder.explandibleLayout.setVisibility(isInfo_explandible ? View.VISIBLE : View.GONE);


    }

    @Override
    public int getItemCount() {
        return infoRecyclerViewsList.size();
    }

    public class InfoRecyclerVH extends RecyclerView.ViewHolder {

        TextView TXTInformacionT0TXT,TXTInformacionT1TXT,TXTInformacionT2TXT,TXTInformacionT3TXT,TXTInformacionT4TXT,TXTInformacionT5TXT,TXTInformacionT6TXT;
        LinearLayout info_linear_layout;
        RelativeLayout explandibleLayout;

        public InfoRecyclerVH(@NonNull View itemView) {
            super(itemView);

            TXTInformacionT0TXT = itemView.findViewById(R.id.Info_Teclado);
            TXTInformacionT1TXT = itemView.findViewById(R.id.info_vista_teclado1);
            TXTInformacionT2TXT = itemView.findViewById(R.id.info_vista_teclado2);
            TXTInformacionT3TXT = itemView.findViewById(R.id.info_vista_teclado3);
            TXTInformacionT4TXT = itemView.findViewById(R.id.info_vista_teclado4);
            TXTInformacionT5TXT = itemView.findViewById(R.id.info_vista_teclado5);
            TXTInformacionT6TXT = itemView.findViewById(R.id.info_vista_teclado6);

            info_linear_layout = itemView.findViewById(R.id.info_linear_layout);
            explandibleLayout = itemView.findViewById(R.id.Info_explandible);


            info_linear_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    InfoRecyclerView infoRecyclerView = infoRecyclerViewsList.get(getAdapterPosition());
                    infoRecyclerView.setInfo_explandible(!infoRecyclerView.isInfo_explandible());
                    notifyItemChanged(getAdapterPosition());


                }
            });



        }
    }
}
