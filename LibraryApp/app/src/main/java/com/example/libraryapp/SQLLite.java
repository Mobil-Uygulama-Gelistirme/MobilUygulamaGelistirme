package com.example.libraryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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
            String selectQuery = "SELECT  * FROM " + TABLE_USERS + " Where " + ROW_USERNAME + "='"+ KullaniciAdi + "' and " + ROW_SIFRE + "='" + Sifre+"'";
            Cursor cursor = db.rawQuery(selectQuery,null);
            if (cursor.getCount()>0)
                sonuc = 1;
            cursor.close();
        }catch (Exception e){
            sonuc = -1;
        }
        db.close();
        return sonuc;
    }
    public int KullaniciVarMi(String KullaniciAdi)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        int sonuc=0;
        try
        {
            String selectQuery = "SELECT  * FROM " + TABLE_USERS + " Where " + ROW_USERNAME + "='"+ KullaniciAdi + "'";
            Cursor cursor = db.rawQuery(selectQuery,null);
            if (cursor.getCount()>0)
                sonuc= 1;
            cursor.close();
        }catch (Exception e){
            sonuc= -1;
        }
        db.close();
        return sonuc;
    }

    public int KullaniciEkle(String KullaniciAdi,String Sifre)
    {
        int sonuc=0;
        SQLiteDatabase db = this.getWritableDatabase();
        try
        {
            ContentValues cv = new ContentValues();
            cv.put(ROW_USERNAME, KullaniciAdi);
            cv.put(ROW_SIFRE,Sifre);
            db.insert(TABLE_USERS, null,cv);
            sonuc=1;
        }catch (Exception e){
        }
        db.close();
        return sonuc;
    }
}
