package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btn_giris = findViewById(R.id.giris_btn);
        btn_giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Kullanici adi ve sifre dogru ise
                //TODO Kitap listesi activity'i acilacak.
                Toast.makeText(getApplicationContext(), "Giriş yapılıyor", Toast.LENGTH_SHORT).show();
            }
        });
        Button btn_kayit = findViewById(R.id.kayitol_btn);
        btn_kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO kayit olma ekranı acılacak

                Toast.makeText(getApplicationContext(), "Kayıt ol ekranı açılıyor", Toast.LENGTH_SHORT).show();
            }
        });
    }
}