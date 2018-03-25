package com.example.android.rifqyirsyadputra_1202152176_modul5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddTodo extends AppCompatActivity {

    //Deklarasi Variable
    EditText eTodo, eDesk, ePrior;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);
        //membuat database
        db = new DatabaseHelper(this);

        eTodo = findViewById(R.id.eTodo);
        eDesk = findViewById(R.id.eDesk);
        ePrior = findViewById(R.id.ePrior);

    }

    public void Tambah(View view) {
        //Menambah data pada Database
        if (db.addData(new Todoitem(eTodo.getText().toString(), eDesk.getText().toString(), ePrior.getText().toString()))){
            //Menampilkan Text ketika berhasil
            Toast.makeText(this, "Data Berhasil Ditambah", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }else {
            //Menampilkan Text ketika gagal
            Toast.makeText(this, "Gagal Tambah Data", Toast.LENGTH_SHORT).show();

            //Set Text kosong
            eTodo.setText(null);
            eDesk.setText(null);
            ePrior.setText(null);
        }
    }

    //Jika tombol back di klik
    @Override
    public void onBackPressed() {
        //Untuk Pindah Halaman ke MainActivity
        startActivity(new Intent(AddTodo.this, MainActivity.class));
        this.finish();
    }
}
