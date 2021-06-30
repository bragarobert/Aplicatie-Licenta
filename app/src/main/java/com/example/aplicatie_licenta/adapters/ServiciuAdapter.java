package com.example.aplicatie_licenta.adapters;

import android.content.Context;
import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicatie_licenta.R;
import com.example.aplicatie_licenta.Serviciu;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ServiciuAdapter extends RecyclerView.Adapter {
    private List<Serviciu> servicii;


    public ServiciuAdapter(List<Serviciu> servicii){
        this.servicii = servicii;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_servicii,parent,false);
        ViewHolderClass viewHolderClass = new ViewHolderClass(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass viewHolderClass = (ViewHolderClass)holder;
        Serviciu serviciu = servicii.get(position);
        viewHolderClass.tvDenumire.setText(serviciu.getDenumire());
        viewHolderClass.tvDescriere.setText(serviciu.getDescriere());
        viewHolderClass.tvPret.setText(String.valueOf(serviciu.getPret()) + " RON");
        Picasso.get().load(serviciu.getImagine()).into(viewHolderClass.ivImagine);
    }

    @Override
    public int getItemCount() {
        return servicii.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder{
        TextView tvDenumire, tvDescriere, tvPret;
        ImageView ivImagine;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            tvDenumire = itemView.findViewById(R.id.tv_serviciu);
            tvDescriere = itemView.findViewById(R.id.tv_descriere_serviciu);
            tvPret = itemView.findViewById(R.id.tv_pret_serviciu);
            ivImagine = itemView.findViewById(R.id.iv_serviciu);
        }
    }


}
