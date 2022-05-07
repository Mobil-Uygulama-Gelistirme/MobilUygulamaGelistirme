package com.example.libraryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLite extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "KitaplarDB";
    private static final String TABLE_KUTUPHANE = "Kutuphane";
    private static final String ROW_KITAP = "Kitap";
    private static final String ROW_KITAP_ID = "KITAPID";
    private static final String ROW_USERNAME = "KullaniciAdi";
    private static final int DATABASE_VERSION = 1;
    public SQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + TABLE_KUTUPHANE + "("
                + ROW_KITAP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ROW_USERNAME + " TEXT, "
                + ROW_KITAP + " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KUTUPHANE);
        onCreate(db);
    }

    public void KitapEkle(String KitapAdi, String KullaniciAdi){
        SQLiteDatabase db = this.getWritableDatabase();
        try
        {
            ContentValues cv = new ContentValues();
            cv.put(ROW_USERNAME, KullaniciAdi);
            cv.put(ROW_KITAP, KitapAdi);
            db.insert(TABLE_KUTUPHANE, null,cv);
        }catch (Exception e){
        }
        db.close();
    }

    public int KitapKayitliMi(String KitapAdi,String KullaniciAdi)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        int sonuc=0;
        try
        {
            String selectQuery = "SELECT  * FROM " + TABLE_KUTUPHANE + " Where " + ROW_USERNAME + "='"+ KullaniciAdi + "' AND "+ ROW_KITAP +"='" + KitapAdi +"'";
            Cursor cursor = db.rawQuery(selectQuery,null);
            if (cursor.getCount()>0)
                sonuc= 1;
        }catch(Exception e){
            sonuc=-1;
        }
        db.close();
        return sonuc;
    }

    public ArrayList<String> KitapGetir(String KullaniciAdi){
        ArrayList<String> kitaplar = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        try
        {
            String selectQuery = "SELECT  * FROM " + TABLE_KUTUPHANE + " Where " + ROW_USERNAME + "='"+ KullaniciAdi + "'";
            Cursor cursor = db.rawQuery(selectQuery,null);
            while (cursor.moveToNext()){
                kitaplar.add(cursor.getString(2));
            }
            cursor.close();
        }catch (Exception e){
        }
        db.close();
        return kitaplar;
    }
}
