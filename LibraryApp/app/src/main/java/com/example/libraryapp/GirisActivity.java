package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GirisActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);

        Button btn_giris = findViewById(R.id.giris_btn);
        EditText KullaniciAdi=findViewById(R.id.username);
        EditText Sifre=findViewById(R.id.password);


        preferences = this.getSharedPreferences("com.example.libraryapp", Context.MODE_PRIVATE);

        btn_giris.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                String kad=KullaniciAdi.getText().toString();
                String sifre=Sifre.getText().toString();

                SQLLite db = new SQLLite(GirisActivity.this);
                int sonuc=db.KullaniciKontrol(kad,sifre);//eğer kullanıcı var ise 1 dönecek yok ise 0
                if (sonuc==1)
                {
                    Intent i = new Intent(GirisActivity.this, MainActivity.class);

                    editor = preferences.edit();
                    editor.putString("kullaniciAd", kad);
                    editor.apply();

                    startActivity(i);
                    finish();
                }
                else
                    Toast.makeText(getApplicationContext(), "Böyle bir kullanıcı bulunamadı. Hesabınız yok ise kayıt olunuz.", Toast.LENGTH_SHORT).show();
            }
        });
        Button btn_kayit = findViewById(R.id.kayitol_btn);
        btn_kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(GirisActivity.this, KayitOlActivity.class);
                startActivity(i);
            }
        });
    }
}