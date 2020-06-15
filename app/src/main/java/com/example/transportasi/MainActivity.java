package com.example.transportasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnPengiriman, btnRiwayat, btnJadwal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPengiriman = findViewById(R.id.btn_pengiriman);
        btnRiwayat = findViewById(R.id.btn_riwayat);
        btnJadwal = findViewById(R.id.btn_jadwal);

        btnPengiriman.setOnClickListener((View.OnClickListener) this);
        btnRiwayat.setOnClickListener((View.OnClickListener) this);
        btnJadwal.setOnClickListener((View.OnClickListener) this);
    }


    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_pengiriman:
                Intent pindahPengiriman=new Intent (MainActivity.this,PengirimanActivity.class);
                startActivity(pindahPengiriman);
                break;
            case R.id.btn_riwayat:
                Intent pindahRiwayat=new Intent (MainActivity.this,RiwayatActivity.class);
                startActivity(pindahRiwayat);
                break;
            case R.id.btn_jadwal:
                Intent pindahJadwal=new Intent (MainActivity.this,JadwalActivity.class);
                startActivity(pindahJadwal);
                break;
        }
    }
}
