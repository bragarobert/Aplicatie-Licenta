package com.example.aplicatie_licenta.fragmente;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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


public class ServiciiFragment extends Fragment {

    private DatabaseReference databaseReference;
    private List<Serviciu> servicii = new ArrayList<>();
    ServiciuAdapter serviciuAdapter;
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_servicii, container, false);

        recyclerView = root.findViewById(R.id.rv_servicii);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        databaseReference = FirebaseDatabase.getInstance().getReference("Servicii");

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                String valoare = snapshot.child("Denumire").getValue().toString();
                String valoare1 = snapshot.child("Descriere").getValue().toString();
                String valoare2 = snapshot.child("Image").getValue().toString();
                int valoare3 = Integer.parseInt(snapshot.child("Pret").getValue().toString());

                Serviciu serviciu = new Serviciu(valoare,valoare1,valoare2,valoare3);
                servicii.add(serviciu);
                serviciuAdapter.notifyDataSetChanged();

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
        serviciuAdapter = new ServiciuAdapter(servicii);
        recyclerView.setAdapter(serviciuAdapter);

        return root;
    }
}