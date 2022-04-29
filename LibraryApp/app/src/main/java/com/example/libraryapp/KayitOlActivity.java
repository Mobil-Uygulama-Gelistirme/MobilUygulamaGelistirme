package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class KayitOlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ol);

        Button btn_kayit = findViewById(R.id.btn_kayitOl);
        EditText KullaniciAdi = findViewById(R.id.username_kayit);
        EditText Sifre = findViewById(R.id.password_kayit);
        EditText SifreOnaylama = findViewById(R.id.password_onaylama);

        btn_kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String kullaniciAdi = KullaniciAdi.getText().toString();
                String sifre = Sifre.getText().toString();
                String sifreOnaylama = SifreOnaylama.getText().toString();

                //EditText'lerden biri bile boş ise calisir.
                if(kullaniciAdi.equals("") || sifre.equals("") || sifreOnaylama.equals("")){
                    Toast.makeText(getApplicationContext(), "Hiçbir alanı boş bırakmayınız!", Toast.LENGTH_SHORT).show();
                }
                //sifre ve sifreOnaylama esit degil ise calisir.
                else if(!sifre.equals(sifreOnaylama)){
                    Toast.makeText(getApplicationContext(), "Lütfen iki şifreyi de aynı giriniz!", Toast.LENGTH_SHORT).show();
                }
                //sifre ve sifreOnaylama esit ve bos degil ise kayit islemine devam eder.
                else
                {
                    //TODO kullaniciAdi ve sifre degiskenleri ile veritabanına yeni kisi olusturulacak.
                    //TODO kayit basarili ise kullanici tekrardan login ekranina aktarilacak.
                    Toast.makeText(getApplicationContext(), "Kayıt işlemi başarılı", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}