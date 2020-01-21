package com.frutagolosa.fgapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.frutagolosa.fgapp.api.ApiInterface4;
import com.frutagolosa.fgapp.api.ApiInterfaceVersion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Inicio extends AppCompatActivity {
  private ApiInterfaceVersion apiInterface;
  String version="1.5.4";
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_inicio);
    final Button btnreiniciarapp=findViewById(R.id.btnReiniciarApp);
    final Button btncerrarapp=findViewById(R.id.btnCerrarApp);
    final Button btnActualizarApp=findViewById(R.id.btnActualizarApp);





    final ProgressDialog asycdialog;
    asycdialog = new ProgressDialog(Inicio.this);
    asycdialog.setMessage("Cargando...");
    asycdialog.getWindow().setGravity(Gravity.CENTER_VERTICAL);
    asycdialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL);
    asycdialog.setCancelable(false);

    asycdialog.show();
    Handler handler=new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {



        Intent d = new Intent(Inicio.this, Main2Activity.class);
        startActivity(d);
        finish();

        asycdialog.dismiss();


      }

    },3000);


    btncerrarapp.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });

    btnreiniciarapp.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
      }
    });

    btnActualizarApp.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String url = "https://play.google.com/store/apps/details?id=com.frutagolosa.fgapp";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
      }
    });











  }




}
