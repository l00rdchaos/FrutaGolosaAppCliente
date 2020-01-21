package com.frutagolosa.fgapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UbicacionEnvioActiviy extends AppCompatActivity {
  //--------------------------------------------------------Declara variables que van al siguiente intent
  public static final String NombreQuienRecibe="NombreQuienRecibe" ;
  public static final String TelefonoQuienRecibe="TelfQuienRecibe" ;
  public static final String DiaEntrega="DiaQueRecibe" ;
  public static final String FranjaHorariaQueRecibe="HoraQueRecibe" ;
  public static final String canipass="asdasd" ;
  public static final String PrecioArreglo="precioarreglo" ;
  public static final String NombreArreglo="nombrearreglo" ;
  public static final String CiudadA="czxc" ;
  String date2;
  //---------------------------------------------------------------------------------------------
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_ubicacion_envio_activiy);

    final EditText NrecibeTxt= (EditText) findViewById(R.id.NombreQRecibe);
    final EditText TelerecibeTxt= (EditText) findViewById(R.id.EditTxtTelfRecib);
    final CalendarView FechaRecibe= (CalendarView) findViewById(R.id.CalendarTxt);

//Recibe variables del intent anterior---------------------------------------------
    final int cantpassAnt = getIntent().getIntExtra(CompArreglo.cantidadArreglos,-1);
    final String precio = getIntent().getStringExtra(CompArreglo.PrecioArreglo);
    final String IdArreglo = getIntent().getStringExtra(CompArreglo.NombreArreglo);
//---------------------------------------------------------------------------------
    final String a=String.valueOf(cantpassAnt);


    final Spinner FranjaHoraria;
    final Spinner SpCiudad;

    FranjaHoraria= (Spinner) findViewById(R.id.SpHorario);
    SpCiudad= (Spinner) findViewById(R.id.spCiudad);

    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.FranjaHoraria, android.R.layout.simple_spinner_item);
    FranjaHoraria.setAdapter(adapter);

    ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.Ciudades, android.R.layout.simple_spinner_item);
    SpCiudad.setAdapter(adapter3);


    final Button EnvDic = (Button) findViewById(R.id.btnEnvDireccion);

    EnvDic.setBackgroundColor(getResources().getColor(R.color.gris2));

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    date2= sdf.format(new Date(FechaRecibe.getDate()));


//Pasar al siguiente activiy con validacion de campos en blanco--------------------------------------



    TextWatcher watcher = new TextWatcher() {
      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(s.toString().trim().length()==0||NrecibeTxt.getText().toString().trim().isEmpty()||TelerecibeTxt.getText().toString().trim().isEmpty()){


          EnvDic.setBackgroundColor(getResources().getColor(R.color.gris2));



        } else {

          EnvDic.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }
      }

      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //YOUR CODE
      }

      @Override
      public void afterTextChanged(Editable s) {


      }
    };


    NrecibeTxt.addTextChangedListener(watcher);
    TelerecibeTxt.addTextChangedListener(watcher);

    CalendarView cale=(CalendarView)findViewById(R.id.CalendarTxt);
    cale.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
      @Override
      public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
        int d = dayOfMonth;
        int m = month+1;
        int y= year;
        String d1=String.valueOf(d);
        String m1=String.valueOf(m);
        String y1=String.valueOf(y);
        date2 =d1+"/"+m1+"/"+y1;
      }
    });

//////////////////////////////////////////////////////////////////

    EnvDic.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        SharedPreferences preferences=getSharedPreferences("login", Context.MODE_PRIVATE);
        final String nombreus=preferences.getString("nombreus","Registrese");
        String mailus=preferences.getString("mailus","No");
        String telefonous=preferences.getString("telefonous","No");


        String hora =  FranjaHoraria.getSelectedItem().toString().trim();
        String Ciudad=SpCiudad.getSelectedItem().toString().trim();
        final String[] date = {""};




        if (NrecibeTxt.getText().toString().trim().isEmpty()||TelerecibeTxt.getText().toString().trim().isEmpty()) {
          Toast.makeText(getApplicationContext(), "Por favor, rellene todos los campos en blanco", Toast.LENGTH_SHORT).show();
          if (!compruebaConexion(getApplicationContext())) {
            Toast.makeText(getBaseContext(), "Necesaria conexión a internet para comprar ", Toast.LENGTH_SHORT).show();
          }
        }


        else {




          SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//String datea = sdf.format(new Date(FechaRecibe.getDate()));




          String  NombRecibePass=NrecibeTxt.getText().toString().replace(","," ").trim();
          String  TelRecibePass=TelerecibeTxt.getText().toString().replace(","," ").trim();

          Intent b = new Intent(UbicacionEnvioActiviy.this, Maps4Activity.class);

          b.putExtra(NombreQuienRecibe, NombRecibePass);
          b.putExtra(TelefonoQuienRecibe, TelRecibePass);
          b.putExtra(DiaEntrega,date2 );
          b.putExtra(FranjaHorariaQueRecibe, hora);
          b.putExtra(canipass,cantpassAnt);
          b.putExtra(PrecioArreglo, precio);
          b.putExtra(NombreArreglo,IdArreglo);
          b.putExtra(CiudadA,Ciudad);
          startActivity(b);
        }
      }
    }); //boton del clicklister

    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }


    //--------------------------------------------------------------------------------------------------

  }



  public boolean onOptionsItemSelected(MenuItem item){
    switch (item.getItemId()) {
      case android.R.id.home:
        finish();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  public boolean onCreateOptionsMenu(Menu menu) {
    return true;
  }

  public static boolean compruebaConexion(Context context) {

    boolean connected = false;

    ConnectivityManager connec = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

    // Recupera todas las redes (tanto móviles como wifi)
    NetworkInfo[] redes = connec.getAllNetworkInfo();

    for (int i = 0; i < redes.length; i++) {
      // Si alguna red tiene conexión, se devuelve true
      if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
        connected = true;
      }
    }
    return connected;
  }
}
