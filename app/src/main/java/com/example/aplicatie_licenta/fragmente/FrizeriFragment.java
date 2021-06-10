package com.example.aplicatie_licenta.fragmente;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicatie_licenta.Frizer;
import com.example.aplicatie_licenta.R;
import com.example.aplicatie_licenta.Serviciu;
import com.example.aplicatie_licenta.adapters.FrizerAdapter;
import com.example.aplicatie_licenta.adapters.ServiciuAdapter;
import com.example.aplicatie_licenta.utils.SpacingItemDecorator;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FrizeriFragment extends Fragment {


    private DatabaseReference databaseReference;
    private List<Frizer> frizeri = new ArrayList<>();
    FrizerAdapter frizerAdapter;
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_frizeri, container, false);
        recyclerView = root.findViewById(R.id.rv_frizeri);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        databaseReference = FirebaseDatabase.getInstance().getReference("Locatii");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    for(DataSnapshot dataSnapshotFrizer:dataSnapshot.child("Frizeri").getChildren()){
                        String valoare = dataSnapshotFrizer.child("Nume").getValue().toString();
                        String valoare1 = dataSnapshotFrizer.child("Varsta").getValue().toString();

                        Frizer frizer = new Frizer(valoare,valoare1);
                        frizeri.add(frizer);
                        frizerAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        SpacingItemDecorator itemDecorator = new SpacingItemDecorator(100);
        recyclerView.addItemDecoration(itemDecorator);
        frizerAdapter = new FrizerAdapter(frizeri);
        recyclerView.setAdapter(frizerAdapter);

        return root;
    }
}