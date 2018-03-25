package com.example.android.rifqyirsyadputra_1202152176_modul5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Rifqy on 25/03/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    //Deklarasi Variable
    Context context;
    SQLiteDatabase data;
    //Nama Database
    public static final String nama_db = "Modul5.db";
    //Nama tabel
    public static final String nama_table = "TodoList";
    //Nama Kolom
    public static final String kolom1 = "kegiatan";
    public static final String kolom2 = "deskripsi";
    public static final String kolom3 = "priority";

    //Method untuk membuat Database
    public DatabaseHelper(Context context) {
        super(context, nama_db, null, 1);
        this.context = context;
        this.data = this.getWritableDatabase();
        //Membuat table bila belum ada
        data.execSQL("create table if not exists "+nama_table+" (kegiatan varchar(50) primary key, deskripsi varchar(50), priority varchar(50)) ");
    }

    //Membuat sqlite database
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists "+nama_table+" (kegiatan varchar(50) primary key, deskripsi varchar(50), priority varchar(50)) ");
    }

    //method untuk update database
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+nama_table);
        onCreate(sqLiteDatabase);
    }

    //Method untuk memasukan data ke database
    public boolean addData (Todoitem item){
        ContentValues cv = new ContentValues();
        cv.put(kolom1, item.getTask());
        cv.put(kolom2, item.getDesk());
        cv.put(kolom3, item.getPrior());
        long hasil = data.insert(nama_table, null, cv);
        if (hasil==-1){
            return false;
        }else {
            return true;
        }
    }

    //Method jika data di hapus
    public boolean deleteData (String nama){
        return data.delete(nama_table, kolom1+"=\""+nama+"\"", null)>0;
    }

    //Method untuk get semua data dengan array
    public void getAllItem (ArrayList<Todoitem> list){
        Cursor cursor = this.getReadableDatabase().rawQuery("select kegiatan, deskripsi, priority from "+nama_table, null);
        while (cursor.moveToNext()){
            list.add(new Todoitem(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
        }
    }
    //Hapus data
    public void clearTable() { //jika table di hapus
        data.execSQL("delete from "+nama_table);
    }
}
