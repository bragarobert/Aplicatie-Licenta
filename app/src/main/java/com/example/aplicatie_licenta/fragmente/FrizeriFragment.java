package com.example.aplicatie_licenta.fragmente;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicatie_licenta.utils.Frizer;
import com.example.aplicatie_licenta.R;
import com.example.aplicatie_licenta.adapters.FrizerAdapter;
import com.example.aplicatie_licenta.utils.SpacingItemDecorator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FrizeriFragment extends Fragment {


    private DatabaseReference databaseReference;

    FrizerAdapter frizerAdapter;
    Spinner spinner;
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_frizeri, container, false);
        recyclerView = root.findViewById(R.id.rv_frizeri);
        spinner = root.findViewById(R.id.sp_alege_locatie_frizeri);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Locatii").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> locatii = new ArrayList<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String locatie = dataSnapshot.child("Denumire").getValue().toString();
                    locatii.add(locatie);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),R.layout.support_simple_spinner_dropdown_item,locatii);
                adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
              @Override
              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                  String val = spinner.getSelectedItem().toString();
                  databaseReference.child("Locatii").addValueEventListener(new ValueEventListener() {
                      @Override
                      public void onDataChange(@NonNull DataSnapshot snapshot) {
                          List<Frizer> frizeri = new ArrayList<>();
                          for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                              if (dataSnapshot.child("Denumire").getValue().toString().equals(val)) {
                                  for (DataSnapshot dataSnapshotFrizer : dataSnapshot.child("Frizeri").getChildren()) {
                                      String valoare = dataSnapshotFrizer.child("Nume").getValue().toString();
                                      String valoare1 = dataSnapshotFrizer.child("Varsta").getValue().toString();
                                      String valoare2 = dataSnapshotFrizer.child("Descriere").getValue().toString();
                                      String valoare3 = dataSnapshotFrizer.child("Image").getValue().toString();
                                      Frizer frizer = new Frizer(valoare,valoare1,valoare2,valoare3);
                                      frizeri.add(frizer);

                                  }
                              }
                          }

                          frizerAdapter = new FrizerAdapter(frizeri);
                          recyclerView.setAdapter(frizerAdapter);
                      }
                      @Override
                      public void onCancelled(@NonNull DatabaseError error) {

                      }
                  });
              }
              @Override
              public void onNothingSelected(AdapterView<?> parent) {

              }
        });

        SpacingItemDecorator itemDecorator = new SpacingItemDecorator(100);
        recyclerView.addItemDecoration(itemDecorator);

        return root;
    }
}