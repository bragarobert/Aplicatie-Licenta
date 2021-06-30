package com.example.aplicatie_licenta.fragmente;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aplicatie_licenta.R;
import com.example.aplicatie_licenta.adapters.LocatieAdapter;
import com.example.aplicatie_licenta.adapters.ServiciuAdapter;
import com.example.aplicatie_licenta.utils.Locatie;
import com.example.aplicatie_licenta.utils.Serviciu;
import com.example.aplicatie_licenta.utils.SpacingItemDecorator;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class LocatiiFragment extends Fragment {

    private DatabaseReference databaseReference;
    private List<Locatie> locatii = new ArrayList<>();
    LocatieAdapter locatieAdapter;
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_locatii, container, false);

        recyclerView = root.findViewById(R.id.rv_locatii);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        databaseReference = FirebaseDatabase.getInstance().getReference("Locatii");

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                String valoare = snapshot.child("Denumire").getValue().toString();
                String valoare1 = snapshot.child("Adresa").getValue().toString();
                String valoare2 = snapshot.child("Contact").getValue().toString();
                String valoare3 = snapshot.child("Imagine").getValue().toString();


               Locatie locatie = new Locatie(valoare,valoare1,valoare2,valoare3);
                locatii.add(locatie);
                locatieAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        SpacingItemDecorator itemDecorator = new SpacingItemDecorator(100);
        recyclerView.addItemDecoration(itemDecorator);
        locatieAdapter = new LocatieAdapter(locatii);
        recyclerView.setAdapter(locatieAdapter);

        return root;
    }
}