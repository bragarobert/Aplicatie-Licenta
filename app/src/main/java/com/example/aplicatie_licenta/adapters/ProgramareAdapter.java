package com.example.aplicatie_licenta.adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicatie_licenta.R;
import com.example.aplicatie_licenta.utils.Programare;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.List;

public class ProgramareAdapter extends RecyclerView.Adapter {
    private List<Programare> programari;


    public ProgramareAdapter(List<Programare> programari){
        this.programari = programari;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_programari,parent,false);
        ProgramareAdapter.ViewHolderClass viewHolderClass = new ProgramareAdapter.ViewHolderClass(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProgramareAdapter.ViewHolderClass viewHolderClass = (ProgramareAdapter.ViewHolderClass)holder;
        Programare programare = programari.get(position);
        viewHolderClass.tvServiciu.setText(programare.getTipServiciu());
        viewHolderClass.tvData.setText(programare.getData());
        viewHolderClass.tvOra.setText(programare.getOra());
        viewHolderClass.tvFrizer.setText(programare.getFrizer());
        viewHolderClass.tvLocatie.setText(programare.getLocatie());

        if(programare.isAnulata()){
            viewHolderClass.btnAnuleaza.setEnabled(false);
            viewHolderClass.btnFeedback.setEnabled(false);
        }
        else if(comparaData(getDataAzi(),programare.getData())){
            viewHolderClass.btnFeedback.setEnabled(false);
        }
        else{
            viewHolderClass.btnAnuleaza.setEnabled(false);

        }

        ((ViewHolderClass) holder).btnAnuleaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(v.getContext()).setMessage(R.string.anulare).setPositiveButton(R.string.da, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((ViewHolderClass) holder).databaseReference.child("Programari").child(programare.getLocatie()).child(programare.getFrizer()).child(programare.getData()).child(programare.getOra()).removeValue();
                        ((ViewHolderClass) holder).databaseReference.child("Utilizatori").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Programari").child(programare.getData()).child(programare.getOra()).child("Anulata").setValue(true);
                        programare.setAnulata(true);
                        notifyDataSetChanged();
                    }
                }).setNegativeButton(R.string.nu, null).show();


            }
        });

    }

    public String getDataAzi(){
        Calendar calendar = Calendar.getInstance();
        String zi = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String luna = String.valueOf(calendar.get(Calendar.MONTH)+1);
        String an = String.valueOf(calendar.get(Calendar.YEAR));

        return zi+"-"+luna+"-"+an;
    }

    public boolean comparaData(String dataAzi, String dataFirebase){
        String[] dataCurenta = dataAzi.split("\\-");
        String[] dataBazaDeDate = dataFirebase.split("\\-");

        if(Integer.parseInt(dataBazaDeDate[2]) > Integer.parseInt(dataCurenta[2])) {
            return true;
        } else {
            if (Integer.parseInt(dataBazaDeDate[2]) == Integer.parseInt(dataCurenta[2])){
                if (Integer.parseInt(dataBazaDeDate[1]) > Integer.parseInt(dataCurenta[1])) {
                    return true;
                } else {
                    if (Integer.parseInt(dataBazaDeDate[1]) == Integer.parseInt(dataCurenta[1])) {
                        if(Integer.parseInt(dataBazaDeDate[0]) >= Integer.parseInt(dataCurenta[0])){
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }
    }
    @Override
    public int getItemCount() {
        return programari.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder{
        TextView tvServiciu, tvData, tvOra, tvFrizer, tvLocatie;
        ImageButton btnAnuleaza , btnFeedback;
        DatabaseReference databaseReference;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            tvServiciu = itemView.findViewById(R.id.programare_serviciu);
            tvData = itemView.findViewById(R.id.programare_data);
            tvOra = itemView.findViewById(R.id.programare_ora);
            tvFrizer = itemView.findViewById(R.id.programare_frizer);
            tvLocatie = itemView.findViewById(R.id.programare_locatie);
            btnAnuleaza = itemView.findViewById(R.id.btn_anuleaza);
            btnFeedback = itemView.findViewById(R.id.btn_feedback);
            databaseReference = FirebaseDatabase.getInstance().getReference();
        }
    }

}