package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class FavoriActivity extends AppCompatActivity {

    private Button kitapBtn;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favori);

        TextView text = findViewById(R.id.kullaniciText);

        preferences = this.getSharedPreferences("com.example.libraryapp", Context.MODE_PRIVATE);
        String data = preferences.getString("kullaniciAd", "Bilinmiyor");

        text.setText(data);



        kitapBtn = findViewById(R.id.kitapBtn);
        kitapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FavoriActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}