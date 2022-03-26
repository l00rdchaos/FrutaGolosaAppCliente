package com.frutagolosa.fgapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.frutagolosa.fgapp.api.ApiClient;
import com.frutagolosa.fgapp.model.Pedido;
import com.frutagolosa.fgapp.model.horas;
import com.frutagolosa.fgapp.api.apiInterfaceFranjas;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
  private List<horas> horas;
  private apiInterfaceFranjas apiInterfacef;
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
    final Pedido pedido = (Pedido) getIntent().getSerializableExtra("PEDIDO");
//---------------------------------------------------------------------------------

  date2= new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

      final Spinner FranjaHoraria;
    final Spinner SpCiudad;

    FranjaHoraria= (Spinner) findViewById(R.id.SpHorario);
    SpCiudad= (Spinner) findViewById(R.id.spCiudad);
    apiInterfacef = ApiClient.getApiClient().create(apiInterfaceFranjas.class);




          ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(getApplicationContext(),R.array.NO, android.R.layout.simple_spinner_item);
        FranjaHoraria.setAdapter(adapter3);


       if(pedido.getTipo_arreglo()=="DESAYUNO"){
          ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,R.array.CiudadesDesayuno, android.R.layout.simple_spinner_item);
          SpCiudad.setAdapter(adapter4);


        }else{

          ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,R.array.Ciudades, android.R.layout.simple_spinner_item);
          SpCiudad.setAdapter(adapter4);
        }




    final Button EnvDic = (Button) findViewById(R.id.btnEnvDireccion);

    EnvDic.setBackgroundColor(getResources().getColor(R.color.gris2));


    SpCiudad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

        String Ciudad=SpCiudad.getSelectedItem().toString().trim();
        apiInterfacef = ApiClient.getApiClient().create(apiInterfaceFranjas.class);
      //  Call<List<horas>> call = apiInterfacef.getHora("https://frutagolosa.com/FrutaGolosaApp/horas.php?d="+date2+"&&c="+Ciudad+"&&t="+tipoArreglo+"&&a="+IdArreglo);
        Call<List<horas>> call = apiInterfacef.getHora("https://frutagolosa.com/FrutaGolosaApp/horas.php?d="+date2+"&&c="+Ciudad+"&&t="+"&&a=");
        call.enqueue(new Callback<List<horas>>() {
          @Override
          public void onResponse(Call<List<horas>> call, Response<List<horas>> response) {
            if(response.body()!=null) {
              horas = response.body();


              Spinner FranjaHoraria = (Spinner) findViewById(R.id.SpHorario);
              if (horas.size() >= 1) {
                String[] s = new String[horas.size()];
                for (int i = 0; i < horas.size(); i++) {
                  s[i] = horas.get(i).getHora();
                  final ArrayAdapter a = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, s);
                  a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                  //Setting the ArrayAdapter data on the Spinner
                  FranjaHoraria.setAdapter(a);
                  EnvDic.setEnabled(true);
                  EnvDic.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                }
              } else {

                ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(getApplicationContext(), R.array.NO, android.R.layout.simple_spinner_item);
                FranjaHoraria.setAdapter(adapter3);


              }
            } }





          @Override
          public void onFailure(Call<List<horas>> call, Throwable t) {
            ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(getApplicationContext(),R.array.NO, android.R.layout.simple_spinner_item);
            FranjaHoraria.setAdapter(adapter3);
          }
        });

      }

      @Override
      public void onNothingSelected(AdapterView<?> parentView) {
        // your code here
      }

    });








    final CalendarView cale=(CalendarView)findViewById(R.id.CalendarTxt);
    cale.setMinDate(System.currentTimeMillis() - 1000);
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.DATE,Calendar.getInstance().getActualMinimum(Calendar.DATE));



    cale.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
      @Override
      public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
        int d = dayOfMonth;
        int m = month + 1;
        int y = year;
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        String p = String.valueOf(dayOfWeek);
        final Spinner FranjaHoraria = (Spinner) findViewById(R.id.SpHorario);
        FranjaHoraria.setAdapter(null);
        String d1 = String.valueOf(d);
        String m1 = String.valueOf(m);
        if (m1.length() == 1) {
          m1 = "0" + m1;
        }
        String y1 = String.valueOf(y);


        if (dayOfWeek != 1) {
          date2 = d1 + "/" + m1 + "/" + y1;
          String Ciudad = SpCiudad.getSelectedItem().toString().trim();
          apiInterfacef = ApiClient.getApiClient().create(apiInterfaceFranjas.class);

          Call<List<horas>> call = apiInterfacef.getHora("https://frutagolosa.com/FrutaGolosaApp/horas.php?d=" + date2 + "&&c=" + Ciudad);
          call.enqueue(new Callback<List<horas>>() {
            @Override
            public void onResponse(Call<List<horas>> call, Response<List<horas>> response) {
              if (response.body() != null) {
                horas = response.body();
                Spinner FranjaHoraria = (Spinner) findViewById(R.id.SpHorario);
                if (horas.size() >= 1) {
                  String[] s = new String[horas.size()];
                  for (int i = 0; i < horas.size(); i++) {
                    s[i] = horas.get(i).getHora();
                    final ArrayAdapter a = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, s);
                    a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //Setting the ArrayAdapter data on the Spinner
                    FranjaHoraria.setAdapter(a);
                    EnvDic.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    EnvDic.setEnabled(true);
                  }
                } else {

                  ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(getApplicationContext(), R.array.NO, android.R.layout.simple_spinner_item);
                  FranjaHoraria.setAdapter(adapter3);


                }
              }
            }


            @Override
            public void onFailure(Call<List<horas>> call, Throwable t) {
              ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(getApplicationContext(), R.array.NO, android.R.layout.simple_spinner_item);
              FranjaHoraria.setAdapter(adapter3);
              EnvDic.setEnabled(false);
              EnvDic.setBackgroundColor(getResources().getColor(R.color.gris2));
            }
          });


        }else{

          Toast.makeText(UbicacionEnvioActiviy.this, "No trabajamos los dias domingos", Toast.LENGTH_SHORT).show();
          ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(getApplicationContext(), R.array.NO, android.R.layout.simple_spinner_item);
          FranjaHoraria.setAdapter(adapter3);
          EnvDic.setEnabled(false);
          EnvDic.setBackgroundColor(getResources().getColor(R.color.gris2));
        }
      } });

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




        if (NrecibeTxt.getText().toString().trim().isEmpty()||TelerecibeTxt.getText().toString().trim().isEmpty()||hora.equals("ENTREGAS NO DISPONIBLES")||Ciudad.equals("SELECCIONE CIUDAD")) {
          Toast.makeText(getApplicationContext(), "Por favor, rellene todos los campos en blanco, revise que haya horas disponibles.", Toast.LENGTH_SHORT).show();
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
          pedido.setNombre_recibe(NombRecibePass);
          pedido.setTelefono_recibe(TelRecibePass);
          pedido.setDia_entrega(date2);
          pedido.setFranja_horaria(hora);
          pedido.setCiudad(Ciudad);
          b.putExtra("PEDIDO",pedido);
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
