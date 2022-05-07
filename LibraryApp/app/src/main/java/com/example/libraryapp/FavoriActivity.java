package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class FavoriActivity extends AppCompatActivity {

    private Button kitapBtn;
    private SharedPreferences preferences;
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> kitaplarList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favori);

        preferences = this.getSharedPreferences("com.example.libraryapp", Context.MODE_PRIVATE);
        String data = preferences.getString("kullaniciAd", "Bilinmiyor");


        SQLite sqlLite = new SQLite(FavoriActivity.this);


        TextView text = findViewById(R.id.kullaniciText);
        listView = (ListView) findViewById(R.id.idLVFavoriler);

        kitaplarList = new ArrayList<>();
        kitaplarList = sqlLite.KitapGetir(data);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1, kitaplarList);

        listView.setAdapter(adapter);

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