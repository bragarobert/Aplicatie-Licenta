package com.example.aplicatie_licenta.autentificare_inregistrare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplicatie_licenta.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    private TextInputEditText tietUsername;
    private TextInputEditText tietEmail;
    private TextInputEditText tietParola;
    private TextInputEditText tietConfirmaParola;
    private Button btnCreareCont;
    private FirebaseAuth  firebaseAuth;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference =  FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        firebaseAuth = FirebaseAuth.getInstance();
        initializare();


        btnCreareCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validareDate()){
                    scrieUtilizatorInBazaDeDate(tietUsername.getText().toString(),
                            tietEmail.getText().toString(),tietParola.getText().toString());
                }
            }
        });


        TextView tv_conectare = findViewById(R.id.tw_cont_existent);
        tv_conectare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });
    }


    public void scrieUtilizatorInBazaDeDate(String nume, String email, String parola) {
        firebaseAuth.createUserWithEmailAndPassword(email,parola).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SignupActivity.this, "Inregistrare cu succes",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                    finish();
                }
                else{
                    Toast.makeText(SignupActivity.this,"Inregistrare nereusita. Exista deja un cont cu aceasta adresa de email",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initializare(){
        tietUsername= findViewById(R.id.tiet_username);
        tietEmail = findViewById(R.id.tiet_email);
        tietParola = findViewById(R.id.tiet_parola);
        tietConfirmaParola = findViewById(R.id.tiet_confirma_parola);
        btnCreareCont = findViewById(R.id.btn_creare_cont);
    }

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[a-zA-Z])" +      //cel putin o litera mare si o litera mica
                    "(?=.*[@#$%^&+=])" +    //cel putin un caracter special
                    "(?=\\S+$)" +           //fara spatii libere
                    ".{6,}" +               //cel putin 6 caractere
                    "$");

    private boolean validareDate(){
        String username = tietUsername.getText().toString();
        String email = tietEmail.getText().toString();
        String parola = tietParola.getText().toString();
        String parolaConfirmata = tietConfirmaParola.getText().toString();

        if(username.isEmpty()){
            tietUsername.setError("Câmpul nume utilizator nu trebuie sa fie gol!");
            return false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            tietEmail.setError("Introduceți un email valid!");
            return false;
        }

        if (!PASSWORD_PATTERN.matcher(parola).matches()) {
            tietParola.setError("Parola nu respectă condițiile");
            return false;
        }

        if(!parolaConfirmata.equals(parola)){
            tietConfirmaParola.setError("Parolele nu corespund!");
            return false;
        }
        return true;
    }

}