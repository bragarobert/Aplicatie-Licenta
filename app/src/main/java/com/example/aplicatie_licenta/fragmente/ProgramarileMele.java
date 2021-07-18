package com.example.aplicatie_licenta.fragmente;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aplicatie_licenta.R;
import com.example.aplicatie_licenta.adapters.LocatieAdapter;
import com.example.aplicatie_licenta.adapters.ProgramareAdapter;
import com.example.aplicatie_licenta.utils.Locatie;
import com.example.aplicatie_licenta.utils.Programare;
import com.example.aplicatie_licenta.utils.SpacingItemDecorator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ProgramarileMele extends Fragment {

    private DatabaseReference databaseReference;
    private List<Programare> programari = new ArrayList<>();
    ProgramareAdapter programareAdapter;
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_programarile_mele, container, false);

        recyclerView = root.findViewById(R.id.rv_programarile_mele);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        databaseReference = FirebaseDatabase.getInstance().getReference("Utilizatori").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Programari");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String data = dataSnapshot.getKey();
                    databaseReference.child(data).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot dataSnapshot1: snapshot.getChildren()){
                                String ora = dataSnapshot1.getKey();

                                String frizer = dataSnapshot1.child("Frizer").getValue().toString();
                                String serviciu = dataSnapshot1.child("Serviciu").getValue().toString();
                                String salon = dataSnapshot1.child("Salon").getValue().toString();
                                boolean esteAnulata = (boolean) dataSnapshot1.child("Anulata").getValue();

                                Programare programare = new Programare(serviciu,data,ora,frizer,salon,esteAnulata);
                                programari.add(programare);
                                programareAdapter.notifyDataSetChanged();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        SpacingItemDecorator itemDecorator = new SpacingItemDecorator(100);
        recyclerView.addItemDecoration(itemDecorator);
        programareAdapter = new ProgramareAdapter(programari);
        recyclerView.setAdapter(programareAdapter);

        return root;
    }
}