package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btn_giris = findViewById(R.id.giris_btn);
        EditText KullaniciAdi=findViewById(R.id.username);
        EditText Sifre=findViewById(R.id.password);

        btn_giris.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                //TODO Kitap listesi activity'si yapılınca aktif edilecek
                //TODO Kayıt ol activity'si yapılınca test edilecek

                String kad=KullaniciAdi.getText().toString();
                String sifre=Sifre.getText().toString();

                SQLLite db = new SQLLite(LoginActivity.this);
                int sonuc=db.KullaniciKontrol(kad,sifre);//eğer kullanıcı var ise 1 dönecek yok ise 0
                if (sonuc==1)
                {
                    Toast.makeText(getApplicationContext(), "Giriş yapılıyor", Toast.LENGTH_SHORT).show();
                    //Intent i = new Intent(LoginActivity.this, KitapListesiActivity.class);
                    //startActivity(i);
                    //finish();
                }
                else
                    Toast.makeText(getApplicationContext(), "Böyle bir kullanıcı bulunamadı. Hesabınız yok ise kayıt olunuz.", Toast.LENGTH_SHORT).show();
            }
        });
        Button btn_kayit = findViewById(R.id.kayitol_btn);
        btn_kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO kayit olma ekranı yapılınca aktif edilecek

                //Intent i = new Intent(LoginActivity.this, KayitOlActivity.class);
                //startActivity(i);
                //finish();
                Toast.makeText(getApplicationContext(), "Kayıt ol ekranı açılıyor", Toast.LENGTH_SHORT).show();
            }
        });
    }
}