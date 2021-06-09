package com.example.aplicatie_licenta;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programare_noua);

        databaseReference= FirebaseDatabase.getInstance().getReference();
        initViews();
        selectareData();

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
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                tvDataProgramare.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };

        ibAlegeData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog( ProgramareNoua.this,onDateSetListener,
                        calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(new Date().getTime());
                datePickerDialog.show();
            }
        });
    }
}