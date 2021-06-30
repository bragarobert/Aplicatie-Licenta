package com.example.aplicatie_licenta.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicatie_licenta.R;
import com.example.aplicatie_licenta.utils.Locatie;
import com.example.aplicatie_licenta.utils.Serviciu;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LocatieAdapter extends RecyclerView.Adapter {
    private List<Locatie> locatii;


    public LocatieAdapter(List<Locatie> locatii){
        this.locatii = locatii;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_locatii,parent,false);
        ViewHolderClass viewHolderClass = new ViewHolderClass(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass viewHolderClass = (ViewHolderClass)holder;
        Locatie locatie = locatii.get(position);
        viewHolderClass.tvDenumire.setText(locatie.getDenumire());
        viewHolderClass.tvAdresa.setText(locatie.getAdresa());
        viewHolderClass.tvContact.setText(locatie.getContact());
        Picasso.get().load(locatie.getImagine()).into(viewHolderClass.ivImagine);
    }

    @Override
    public int getItemCount() {
        return locatii.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder{
        TextView tvDenumire, tvAdresa, tvContact;
        ImageView ivImagine;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            tvDenumire = itemView.findViewById(R.id.denumire_salon);
            tvAdresa = itemView.findViewById(R.id.adresa_salon);
            tvContact = itemView.findViewById(R.id.contact_salon);
            ivImagine = itemView.findViewById(R.id.iv_locatie);
        }
    }

}
