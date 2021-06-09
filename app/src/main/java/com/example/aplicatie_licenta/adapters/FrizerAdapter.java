package com.example.aplicatie_licenta.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.aplicatie_licenta.Frizer;
import com.example.aplicatie_licenta.R;

import java.util.List;

public class FrizerAdapter  extends BaseAdapter {

    private List<Frizer> frizeri;
    private Context context;
    private LayoutInflater layoutInflater;

    public FrizerAdapter(List<Frizer> frizeri, Context context){
        this.frizeri = frizeri;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return frizeri.size();
    }

    @Override
    public Object getItem(int position) {
        return frizeri.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View frizeriView = layoutInflater.inflate(R.layout.item_frizeri,parent,false);
        TextView tvNume=frizeriView.findViewById(R.id.item_nume_frizer);

        Frizer frizer = frizeri.get(position);
        tvNume.setText(frizer.getNumeFrizer());

        return  frizeriView;
    }
}
