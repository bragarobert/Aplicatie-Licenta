package com.example.aplicatie_licenta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ProgramareNoua extends AppCompatActivity {

    private Spinner spSalon;
    private Spinner spFrizer;
    private Spinner spServiciu;
    private TextView tvDataProgramare;
    private ImageButton ibAlegeData;
    private ListView lvOreDisponibile;
    private Button btnRezerva;

    final Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programare_noua);

        initViews();
        selectareData();

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Servicii");
        databaseReference.addValueEventListener(new ValueEventListener() {
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

        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Locatii");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> locatii = new ArrayList<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String locatie = dataSnapshot.child("Adresa").getValue().toString();
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
                String val = spSalon.getSelectedItem().toString();
                DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("Locatii");
                databaseReference2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<String> frizeri = new ArrayList<>();
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            if(dataSnapshot.child("Adresa").getValue().toString().equals(val)) {
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


    }

    private void initViews() {
        spSalon = findViewById(R.id.sp_salon);
        spFrizer = findViewById(R.id.sp_frizer);
        spServiciu = findViewById(R.id.sp_serviciu);
        tvDataProgramare = findViewById(R.id.tv_data);
        ibAlegeData = findViewById(R.id.btn_alege_data);
        lvOreDisponibile = findViewById(R.id.lv_ore_disponibile);
        btnRezerva = findViewById(R.id.btn_rezerva_programare);
    }

    private void selectareData(){
        DatePickerDialog.OnDateSetListener onDateSetListener = (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
            tvDataProgramare.setText(simpleDateFormat.format(calendar.getTime()));
        };

        ibAlegeData.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog( ProgramareNoua.this,onDateSetListener,
                    calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.getDatePicker().setMinDate(new Date().getTime());
            datePickerDialog.show();
        });
    }
}