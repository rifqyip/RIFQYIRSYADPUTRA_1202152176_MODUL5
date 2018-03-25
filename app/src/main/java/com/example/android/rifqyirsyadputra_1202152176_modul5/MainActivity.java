package com.example.android.rifqyirsyadputra_1202152176_modul5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Deklarasi Variable
    DatabaseHelper db;
    RecyclerView rv;
    TodoAdapter adapter;
    ArrayList<Todoitem> listitem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rv = findViewById(R.id.recyclerview);
        //Buat array
        listitem = new ArrayList<>();

        //membuat database dengan variable db
        db = new DatabaseHelper(this);
        db.getAllItem(listitem);

        SharedPreferences shp = this.getApplicationContext().getSharedPreferences("shp", 0);
        int warna = shp.getInt("background", R.color.white); //mendefinisikan warna untuk background

        //Membuat Adapter baru
        adapter = new TodoAdapter(this, listitem, warna);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        //FAB
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //Untuk Pindah Halaman
                startActivity(new Intent(MainActivity.this, AddTodo.class));
                finish();
            }
        });

        swipe();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, SettingActivity.class)); //maka akan pindah ke kelas setting
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    //Method jika list di swipre
    public void swipe() {
        ItemTouchHelper.SimpleCallback sc = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int posisi = viewHolder.getAdapterPosition();
                Todoitem now = adapter.getItem(posisi);

                //Jika di swipe ke kanan atau kiri
                if (direction==ItemTouchHelper.LEFT||direction==ItemTouchHelper.RIGHT){
                    if (db.deleteData(now.getTask())){ //data terhapus
                        adapter.removeitem(posisi); //data terhapus dari adapter
                    }
                }
            }
        };
        //jika tidak menghapus data maka akan memanggil method callback diatas
        ItemTouchHelper helper =  new ItemTouchHelper(sc);
        helper.attachToRecyclerView(rv); //data akan ditampilkan di recycler view
    }
}
