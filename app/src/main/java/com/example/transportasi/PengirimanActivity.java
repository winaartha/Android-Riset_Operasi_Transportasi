package com.example.transportasi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PengirimanActivity extends AppCompatActivity{
    Button btnGenerate, btnNext, btnSimpan, btnProses;
    EditText jmlPabrik, jmlTujuan;
    LinearLayout listPabrik, listTujuan, containerBiaya, containerSupply, containerDemand;

    int iPabrik, iTujuan;
    String[] namaPabrik = new String[100], namaTujuan = new String[100];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengiriman);

        btnGenerate=(Button)findViewById(R.id.btn_generate);
        btnNext=(Button)findViewById(R.id.btn_next);
        btnSimpan=(Button)findViewById(R.id.btn_Simpan);
        btnProses=(Button)findViewById(R.id.btn_proses);

        jmlPabrik=(EditText)findViewById(R.id.jmlPabrik);
        jmlTujuan=(EditText)findViewById(R.id.jmlTujuan);

        listPabrik=findViewById(R.id.listPabrik);
        listTujuan=findViewById(R.id.listTujuan);
        containerBiaya=findViewById(R.id.containerBiaya);
        containerSupply=findViewById(R.id.containerSupply);
        containerDemand=findViewById(R.id.containerDemand);

        btnGenerate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String sPabrik=jmlPabrik.getText().toString();
                String sTujuan=jmlTujuan.getText().toString();

                try{
                    iPabrik=Integer.parseInt(sPabrik);
                    iTujuan=Integer.parseInt(sTujuan);
                }catch (NumberFormatException e){

                }

                for(int i=0;i<iPabrik;i++) {
                    LayoutInflater layoutInflater =
                            (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View addView = layoutInflater.inflate(R.layout.insertcompany, listPabrik, false);

                    listPabrik.addView(addView);
                }

                for(int j=0;j<iTujuan;j++){
                    LayoutInflater layoutInflater =
                            (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View addView = layoutInflater.inflate(R.layout.insertcompany,listTujuan, false);

                    listTujuan.addView(addView);
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<iPabrik;i++) {
                    View thisChild = listPabrik.getChildAt(i);
                    EditText et = (EditText) thisChild.findViewById(R.id.in_nama);

                    String str = et.getText().toString();
                    namaPabrik[i]=str;
                }

                for(int i=0;i<iTujuan;i++) {
                    View thisChild = listTujuan.getChildAt(i);
                    EditText et = (EditText) thisChild.findViewById(R.id.in_nama);
                    String str = et.getText().toString();
                    namaTujuan[i]=str;
                }


                for(int i=0;i<iPabrik;i++) {
                    for(int j=0;j<iTujuan;j++){
                        LayoutInflater layoutInflater =
                                (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        final View addView = layoutInflater.inflate(R.layout.insertnilai, containerBiaya, false);
                        TextView tv = (TextView) addView.findViewById(R.id.txtNama);
                        tv.setText(namaPabrik[i]+" x "+namaTujuan[j]);

                        containerBiaya.addView(addView);
                    }
                }


                for(int i=0;i<iPabrik;i++) {
                    LayoutInflater layoutInflater =
                            (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View addView = layoutInflater.inflate(R.layout.insertnilai, containerSupply, false);
                    TextView tv = (TextView) addView.findViewById(R.id.txtNama);
                    tv.setText(namaPabrik[i]);
                    containerSupply.addView(addView);
                }

                for(int i=0;i<iTujuan;i++) {
                    LayoutInflater layoutInflater =
                            (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View addView = layoutInflater.inflate(R.layout.insertnilai, containerDemand, false);
                    TextView tv = (TextView) addView.findViewById(R.id.txtNama);
                    tv.setText(namaTujuan[i]);
                    containerDemand.addView(addView);
                }
            }
        });


        btnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String FILE_NAME="temp.txt";
                FileOutputStream fos = null;

                String str = iPabrik + " " + iTujuan + System.lineSeparator();
                String str2;

                for(int i=0;i<iPabrik;i++){
                    View thisChild = containerSupply.getChildAt(i);
                    EditText et = (EditText) thisChild.findViewById(R.id.in_nama);
                    str2 = et.getText().toString();
                    if(i!=0)str = str + " ";
                    str = str + str2;
                }
                str = str + System.lineSeparator();
                for(int i=0;i<iTujuan;i++){
                    View thisChild = containerDemand.getChildAt(i);
                    EditText et = (EditText) thisChild.findViewById(R.id.in_nama);
                    str2 = et.getText().toString();
                    if(i!=0)str = str + " ";
                    str = str + str2;
                }
                for(int i=0;i<iPabrik*iTujuan;i++){
                    View thisChild = containerBiaya.getChildAt(i);
                    EditText et = (EditText) thisChild.findViewById(R.id.in_nama);
                    str2 = et.getText().toString();
                    if(i%iTujuan != 0)str = str + " ";
                    else str = str + System.lineSeparator();
                    str = str + str2;
                }

                for(int i=0;i<iPabrik;i++){
                    str = str + System.lineSeparator();
                    str = str + namaPabrik[i];
                }

                for(int i=0;i<iTujuan;i++){
                    str = str + System.lineSeparator();
                    str = str + namaTujuan[i];
                }

                try {
                    fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
                    fos.write(str.getBytes());
                    Toast.makeText(getApplicationContext(), "Saved to " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();

                } catch (FileNotFoundException e){
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                } finally {
                    if(fos!= null){
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                Intent pindahHasil=new Intent (PengirimanActivity.this,TransportationProblem.class);
                startActivity(pindahHasil);
            }
        });
    }
}