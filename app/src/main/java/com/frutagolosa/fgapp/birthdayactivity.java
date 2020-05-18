package com.frutagolosa.fgapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.frutagolosa.fgapp.api.ApiClient;
import com.frutagolosa.fgapp.api.ApiInterfaceVersion;
import com.frutagolosa.fgapp.api.apiInterfaceFranjas;
import com.frutagolosa.fgapp.model.horas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit2.Call;

public class birthdayactivity extends AppCompatActivity {
String date2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthdayactivity);


        final ProgressDialog asycdialog;
        asycdialog = new ProgressDialog(birthdayactivity.this);
        asycdialog.setMessage("Cargando...");
        asycdialog.getWindow().setGravity(Gravity.CENTER_VERTICAL);
        asycdialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL);
        asycdialog.setCancelable(false);

        final Button btnguard=(Button) findViewById(R.id.btnguardarbd);
        final EditText edtd=(EditText) findViewById(R.id.editTextday);
        final EditText edtm=(EditText) findViewById(R.id.editTextMonth);
        final EditText edty=(EditText) findViewById(R.id.editTextYear);


        Button btncanc=(Button) findViewById(R.id.btncancelbd);


        btnguard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d1=edtd.getText().toString().trim();
                String m1=edtm.getText().toString().trim();
                String y1=edty.getText().toString().trim();
                date2=d1+"/"+m1+"/"+y1;

                if(d1.length()<=1||m1.length()<=1||y1.length()<=3){

                    Toast.makeText(birthdayactivity.this, "Formato de fecha es dd/mm/yyyy incluya 0 en dias y mes", Toast.LENGTH_SHORT).show();

                }else{
                btnguard.setEnabled(false);
                SharedPreferences preferences=getSharedPreferences("login", Context.MODE_PRIVATE);
                String telefonous=preferences.getString("telefonous","No");

                RestAdapter adapter = new RestAdapter.Builder()
                        .setEndpoint("https://frutagolosa.com/FrutaGolosaApp/birthdateupdate.php?t="+telefonous+"&&d="+date2)
                        .build();

                ApiInterfaceVersion api = adapter.create(ApiInterfaceVersion.class);
                setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

                api.evaluaversion(
                        date2,




                        new Callback<retrofit.client.Response>() {
                            @Override
                            public void success(retrofit.client.Response result, Response response) {
                                if(response.getBody()!=null) {
                                    BufferedReader reader = null;
                                    String output = "";

                                    try {
                                        reader = new BufferedReader(new InputStreamReader(result.getBody().in()));
                                        output = reader.readLine();

                                        if(output.equals("Fecha Guardada")) {
                                            SharedPreferences preferences = getSharedPreferences("birthdate", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = preferences.edit();
                                            editor.putString("bdate", date2);
                                            editor.commit();
                                            Intent d = new Intent(birthdayactivity.this, Inicio.class);
                                            startActivity(d);
                                            finish();
                                        }

                                        else{
                                            btnguard.setEnabled(true);

                                        }
                                        asycdialog.dismiss();

                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }


                                }  }

                            @Override
                            public void failure(RetrofitError error) {
                                asycdialog.dismiss();
                                Toast.makeText(birthdayactivity.this, "Problemas de conexion, servidor o red, revise que este conectado a datos o tenga Wifi", Toast.LENGTH_LONG).show();
                                btnguard.setEnabled(true);

                            }
                        }
                );

            }
        }


});


        btncanc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent d = new Intent(birthdayactivity.this, Inicio.class);
                startActivity(d);
                SharedPreferences preferences = getSharedPreferences("birthdate", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("bdate", "0");
                editor.commit();
                finish();
            }
        });

    }}



