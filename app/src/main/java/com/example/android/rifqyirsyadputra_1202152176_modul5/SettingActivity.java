package com.example.android.rifqyirsyadputra_1202152176_modul5;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {

    //Deklarasi Variable
    int warna;
    TextView color;
    AlertDialog.Builder alert;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        color = findViewById(R.id.warna);
        //Buat alert baru
        alert = new AlertDialog.Builder(this);

        SharedPreferences shp = getApplicationContext().getSharedPreferences("shp", 0);
        edit = shp.edit();
        warna = shp.getInt("background", R.color.white);

        //Warna ditampilkan
        color.setText(getWarna(warna));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() ==android.R.id.home){
            this.onBackPressed();
        }
        return true;
    }

    //Jika menu kembali di klik
    @Override
    public void onBackPressed() {
        startActivity(new Intent(SettingActivity.this, MainActivity.class));
        finish();
    }

    //Get Warna Berdasarkan id warna
    public String getWarna(int i){
        if (i==R.color.blue){
            return "Blue";
        }else if (i==R.color.red){
            return "Red";
        }else if (i==R.color.green){
            return "Green";
        }else{
            return "Default";
        }
    }

    //Get Warna ketika Button berdasarkan id di klik
    public int getIntColor(int i){
        if (i==R.color.blue){
            return R.id.btnBlue;
        }else if (i==R.color.red){
            return R.id.btnRed;
        }else if (i==R.color.green){
            return R.id.btnGreen;
        }else {
            return R.id.btnDefault;
        }
    }

    public void SettingWarna(View view) {
        alert.setTitle("Choose Color"); //Set Judul
        View v = getLayoutInflater().inflate(R.layout.activity_warna,null);
        alert.setView(v);

        final RadioGroup rg = v.findViewById(R.id.rg);
        rg.check(getIntColor(warna));//get color

        //Jika Menekan OK
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int cek = rg.getCheckedRadioButtonId();
                switch (cek){ //kondisi untuk memilih warna
                    //Bila button di klik warna akan di tampilkan sesuai id
                    case R.id.btnDefault:
                        warna = R.color.white;
                        break;
                    case R.id.btnBlue:
                        warna = R.color.blue;
                        break;
                    case R.id.btnGreen:
                        warna = R.color.green;
                        break;
                    case R.id.btnRed:
                        warna = R.color.red;
                        break;

                }
                //Merubah warna Text
                color.setText(getWarna(warna));
                //Merubah warna background
                edit.putInt("background", warna);
                edit.commit();

                startActivity(new Intent(SettingActivity.this, MainActivity.class));
                finish();
            }
        });
        //Jika memilih Cancel
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });alert.create().show();
    }
}
