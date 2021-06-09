package com.example.aplicatie_licenta;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class ProgramareNoua extends AppCompatActivity {

    private Spinner spSalon;
    private Spinner spFrizer;
    private Spinner spServiciu;
    private TextView tvDataProgramare;
    private ImageButton ibAlegeData;
    private ListView lvOreDisponibile;
    private Button btnRezerva;

    final Calendar calendar = Calendar.getInstance();
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programare_noua);

        databaseReference= FirebaseDatabase.getInstance().getReference();
        initViews();


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
}