package com.example.libraryapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLLite extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "KutuphaneDB";
    private static final String TABLE_USERS = "Kullanicilar";
    private static final String ROW_ID = "ID";
    private static final String ROW_USERNAME = "KullaniciAdi";
    private static final String ROW_SIFRE = "Sifre";
    private static final int DATABASE_VERSION = 1;

    public SQLLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + TABLE_USERS + "("
                + ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ROW_USERNAME + " TEXT NOT NULL, "
                + ROW_SIFRE + " TEXT NOT NULL )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public int KullaniciKontrol(String KullaniciAdi,String Sifre){
        SQLiteDatabase db = this.getReadableDatabase();
        int sonuc=0;
        try
        {
            //TODO Kayıt ol ekranı tamamlandıktan sonra çalışıyor mu diye deneyelim
            String selectQuery = "SELECT  * FROM " + TABLE_USERS + " Where " + ROW_USERNAME + "="+ KullaniciAdi + " and " + ROW_SIFRE + "=" + Sifre;
            Cursor cursor = db.rawQuery(selectQuery,null);
            cursor.moveToFirst();
            if (cursor.getCount()>0)
                sonuc=1;
            cursor.close();
        }catch (Exception e){
            sonuc=-1;
        }
        db.close();
        return sonuc;
    }
}