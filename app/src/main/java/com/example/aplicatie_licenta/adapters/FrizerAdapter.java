package com.example.aplicatie_licenta.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicatie_licenta.utils.Frizer;
import com.example.aplicatie_licenta.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FrizerAdapter  extends RecyclerView.Adapter {

    private List<Frizer> frizeri;

    public FrizerAdapter(List<Frizer> frizeri){
        this.frizeri = frizeri;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_frizeri,parent,false);
        FrizerAdapter.ViewHolderClass viewHolderClass = new FrizerAdapter.ViewHolderClass(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        FrizerAdapter.ViewHolderClass viewHolderClass = (FrizerAdapter.ViewHolderClass)holder;
        Frizer frizer= frizeri.get(position);
        viewHolderClass.tvNume.setText(frizer.getNumeFrizer());
        viewHolderClass.tvVarsta.setText(frizer.getVarsta());
        viewHolderClass.tvDescriere.setText(frizer.getDescriere());
        Picasso.get().load(frizer.getImagine()).into(viewHolderClass.ivImagine);
    }

    @Override
    public int getItemCount() {
        return frizeri.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder{
        TextView tvNume, tvVarsta, tvDescriere;
        ImageView ivImagine;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            tvNume = itemView.findViewById(R.id.item_nume_frizer);
            tvVarsta = itemView.findViewById(R.id.item_varsta_frizer);
            tvDescriere = itemView.findViewById(R.id.item_descriere_frizer);
            ivImagine = itemView.findViewById(R.id.iv_frizer);
        }
    }
}
