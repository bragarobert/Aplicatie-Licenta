package com.example.aplicatie_licenta;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ProgramareNoua extends AppCompatActivity {

    private Spinner spSalon;
    private Spinner spFrizer;
    private Spinner spServiciu;
    private TextView tvDataProgramare;
    private ImageButton ibAlegeData;
    private Spinner spOra;
    private Button btnRezerva;
    DatabaseReference databaseReference;
    final Calendar astazi = Calendar.getInstance();
    private List<String> oreOcupate = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programare_noua);
        initViews();
        Map<String, Object> ore = new HashMap<>();
        populareSpinnere();
        selectareData();
        
        btnRezerva.setOnClickListener(v -> {
            String dataProgramare = tvDataProgramare.getText().toString();
            if(spOra.getSelectedItem().toString() != null){
                ore.put(spOra.getSelectedItem().toString(),true);
            }

            databaseReference.child("Programari").child(spSalon.getSelectedItem().toString()).child(spFrizer.getSelectedItem().toString()).child(dataProgramare).updateChildren(ore);
            databaseReference.child("Utilizatori").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Programari").child(dataProgramare).updateChildren(ore);
            databaseReference.child("Utilizatori").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Programari").child(dataProgramare).child(spOra.getSelectedItem().toString()).child("Frizer").setValue(spFrizer.getSelectedItem().toString());
            databaseReference.child("Utilizatori").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Programari").child(dataProgramare).child(spOra.getSelectedItem().toString()).child("Serviciu").setValue(spServiciu.getSelectedItem().toString());
            databaseReference.child("Utilizatori").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Programari").child(dataProgramare).child(spOra.getSelectedItem().toString()).child("Salon").setValue(spSalon.getSelectedItem().toString());
            databaseReference.child("Utilizatori").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Programari").child(dataProgramare).child(spOra.getSelectedItem().toString()).child("Anulata").setValue(false);

            startActivity(new Intent(ProgramareNoua.this, MenuActivity.class));
        });

    }


    private List<String> verificaOreDisponibile(String data) {
        List<String> ore = new ArrayList(Arrays.asList(getResources().getStringArray(R.array.ore)));


        List<String> oreIndisponibile = new ArrayList<>();

        preluareOreDinFirebase(data, value -> {

            for(int i=0;i<value.size();i++){
                for(int j=0;j<ore.size();j++){
                    if(value.get(i).equals(ore.get(j))){
                        oreIndisponibile.add(value.get(i));
                    }
                }
            }
            Log.v("oredisp", ore.get(5).toString());
            Log.v("oreindisp", oreIndisponibile.toString());

            for(int i=0;i<oreIndisponibile.size();i++){
                ore.remove(oreIndisponibile.get(i));
            }

        });

        return ore;
    }

    private void preluareOreDinFirebase(String dataProgramarii, final PreluareOreCallback preluareOreCallback) {
        oreOcupate.clear();
        databaseReference.child("Programari").child(spSalon.getSelectedItem().toString()).child(spFrizer.getSelectedItem().toString()).child(dataProgramarii).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String ora = (String) dataSnapshot.getKey();
                    oreOcupate.add(ora);

                }
                Log.v("oreocupate", oreOcupate.toString());
                preluareOreCallback.onCallback(oreOcupate);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public interface PreluareOreCallback{
        void onCallback(List<String> value);
    }


    void populareSpinnere(){

        databaseReference.child("Servicii").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> servicii = new ArrayList<>();

                for( DataSnapshot dataSnapshot: snapshot.getChildren()){
                    String denumire = dataSnapshot.child("Denumire").getValue().toString();
                    servicii.add(denumire);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(ProgramareNoua.this,R.layout.support_simple_spinner_dropdown_item,servicii);
                adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spServiciu.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseReference.child("Locatii").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> locatii = new ArrayList<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String locatie = dataSnapshot.child("Denumire").getValue().toString();
                    locatii.add(locatie);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(ProgramareNoua.this,R.layout.support_simple_spinner_dropdown_item,locatii);
                adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spSalon.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        spSalon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tvDataProgramare.setText(R.string.data_programare);
                String val = spSalon.getSelectedItem().toString();
                databaseReference.child("Locatii").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<String> frizeri = new ArrayList<>();
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            if(dataSnapshot.child("Denumire").getValue().toString().equals(val)) {
                                for(DataSnapshot dataSnapshotFrizer:dataSnapshot.child("Frizeri").getChildren()){
                                    String valoare = dataSnapshotFrizer.child("Nume").getValue().toString();
                                    frizeri.add(valoare);
                                }
                            }
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(ProgramareNoua.this,R.layout.support_simple_spinner_dropdown_item,frizeri);
                        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        spFrizer.setAdapter(adapter);

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

        spFrizer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tvDataProgramare.setText(R.string.data_programare);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    void initViews() {
        spSalon = findViewById(R.id.sp_salon);
        spFrizer = findViewById(R.id.sp_frizer);
        spServiciu = findViewById(R.id.sp_serviciu);
        tvDataProgramare = findViewById(R.id.tv_data);
        ibAlegeData = findViewById(R.id.btn_alege_data);
        spOra = findViewById(R.id.sp_ora);

        btnRezerva = findViewById(R.id.btn_rezerva_programare);
        databaseReference= FirebaseDatabase.getInstance().getReference();
    }

    void selectareData(){
        DatePickerDialog.OnDateSetListener onDateSetListener = (view, year, month, dayOfMonth) -> {
            astazi.set(Calendar.YEAR,year);
            astazi.set(Calendar.MONTH, month);
            astazi.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
            tvDataProgramare.setText(simpleDateFormat.format(astazi.getTime()));

            List<String> oreDisponibile = verificaOreDisponibile(simpleDateFormat.format(astazi.getTime()));
            Log.v("ore", oreDisponibile.toString());
            ArrayAdapter<String> adapter = new ArrayAdapter<>(ProgramareNoua.this,R.layout.support_simple_spinner_dropdown_item,oreDisponibile);
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            spOra.setAdapter(adapter);
        };

        ibAlegeData.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog( ProgramareNoua.this,onDateSetListener,
                    astazi.get(Calendar.YEAR),astazi.get(Calendar.MONTH),astazi.get(Calendar.DAY_OF_MONTH));
            //datePickerDialog.getDatePicker().setMinDate(new Date().getTime());
            datePickerDialog.show();


        });
    }

}