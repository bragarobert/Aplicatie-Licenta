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

import com.example.aplicatie_licenta.Frizer;
import com.example.aplicatie_licenta.R;
import com.example.aplicatie_licenta.adapters.FrizerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class FrizeriFragment extends Fragment {


    private DatabaseReference databaseReference;
    private List<Frizer> frizeri = new ArrayList<>();
    FrizerAdapter frizerAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_frizeri, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference("Frizeri");

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String valoare = snapshot.getValue().toString();
                Frizer frizer = new Frizer(valoare);
                frizeri.add(frizer);
                frizerAdapter.notifyDataSetChanged();

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

        ListView lv_frizeri = root.findViewById(R.id.lv_frizeri);
        frizerAdapter = new FrizerAdapter(frizeri,this.getContext());
        frizerAdapter.notifyDataSetChanged();
        lv_frizeri.setAdapter(frizerAdapter);
                
                
        return root;
    }
}