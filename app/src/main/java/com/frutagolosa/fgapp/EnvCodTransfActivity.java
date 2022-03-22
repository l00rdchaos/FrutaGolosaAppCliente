package com.frutagolosa.fgapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.frutagolosa.fgapp.api.ApiInterface3;
import com.frutagolosa.fgapp.api.BorrarIntentoApi;
import com.frutagolosa.fgapp.api.RegisterAPI;
import com.frutagolosa.fgapp.api.RegisterApiContador;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class EnvCodTransfActivity extends AppCompatActivity {
  private final int xf = (int) (Math.random() * 10000);
  private final String xf2 = String.valueOf(xf);
  public static final String ROOT_URL = "https://frutagolosa.com/FrutaGolosaApp";
  public static final String ROOT_URL2 = "https://frutagolosa.com/FrutaGolosaApp";
  public static String NombreQuienRecibe = "NombreQuienRecibe";
  public static String TelefonoQuienRecibe = "TelfQuienRecibe";
  public static final String DiaEntrega = "DiaQueRecibe";
  public static final String FranjaHorariaQueRecibe = "HoraQueRecibe";
  public static final String Cantidadpassm = "ad";
  public static final String Direccionpass = "bc";
  public static final String PrecioViajePass = "asdasd";
  public static final String CallePrincPassss = "dasd";
  public static final String CalleSecPass = "qwe";
  public static final String RefereciaPass = "tr";
  public static final String DetalleUbicacionPass = "ert";
  public static final String DetalleAGGPass = "sdfsrdf";
  public static final String GloboOTarjetaPass = "vtfgd";
  public static final String NombreArreglo = "nombrearreglo";
  public static final String cantidadArreglos = "cantidadArreglos";
  public static final String PrecioArreglo = "precioarreglo";
  public static final String PrecioTotal = "preciototal";
  public static final String Sector = "sector";
  public static String Asunto = "vtfgd";
  private static String imgLista = "0";
  public String suma = "0";
  Bitmap bitmap;
  Bitmap bitmap2;
  int PICK_IMAGE_REQUEST = 1;
  String UPLOAD_URL = "https://frutagolosa.com/FrutaGolosaApp/Upload.php";
  String KEY_IMAGE = "foto";
  String KEY_NOMBRE = "nombre";
  String Banco = "";



  private ApiInterface3 apiInterface;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_env_cod_transf);
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

    final int cantidadpass = getIntent().getIntExtra(PagoActivity.Cantidadpassm, -1);
    final String Nombpass = getIntent().getStringExtra(PagoActivity.NombreQuienRecibe);
    final String TelPass = getIntent().getStringExtra(PagoActivity.TelefonoQuienRecibe);
    final String Horapass = getIntent().getStringExtra(PagoActivity.FranjaHorariaQueRecibe);
    final String FechaPass = getIntent().getStringExtra(PagoActivity.DiaEntrega);
    final String DireccionPass = getIntent().getStringExtra(PagoActivity.Direccionpass);
    final String PrecioViajePassa = getIntent().getStringExtra(PagoActivity.PrecioViajePass);
    final String CallePrinPassad = getIntent().getStringExtra(PagoActivity.CallePrincPassss);
    final String CalleSecPassa = getIntent().getStringExtra(PagoActivity.CalleSecPass);
    final String ReferPass = getIntent().getStringExtra(PagoActivity.RefereciaPass);
    final String DetaUBPassa = getIntent().getStringExtra(PagoActivity.DetalleUbicacionPass);
    final String DetaAggPassa = getIntent().getStringExtra(PagoActivity.DetalleAGGPass);
    final String GloboTarjPass = getIntent().getStringExtra(PagoActivity.GloboOTarjetaPass);
    final String precioArreglo = getIntent().getStringExtra(ResumenPedidoActivity.PrecioArreglo);
    final String IdArreglo = getIntent().getStringExtra(ResumenPedidoActivity.NombreArreglo);
    final String precioTotal = getIntent().getStringExtra(ResumenPedidoActivity.PrecioTotal);
    final String sector = getIntent().getStringExtra(ResumenPedidoActivity.Sector);
    final String numeracion = getIntent().getStringExtra(ResumenPedidoActivity.Numeracion);
    final String especificacion = getIntent().getStringExtra(DetalleUbicacionesActivity.Especificacion);
    final String contadorpedidos = getIntent().getStringExtra(ResumenPedidoActivity.ContadorpedidosA);
    final String banco = getIntent().getStringExtra(PagoActivity.BANCO);
    final String ppa = getIntent().getStringExtra(PagoActivity.pp);
    final ImageView btnPichincha = (ImageView) findViewById(R.id.btnPichincha);
    final ImageView btnProdubanco = (ImageView) findViewById(R.id.btnProdubanco);
    final ImageView btnBolivariano = (ImageView) findViewById(R.id.btnBolivariano);
    final ImageView btnGuayaquil = (ImageView) findViewById(R.id.btnGuayaquil);
    final ImageView btnPacifico = (ImageView) findViewById(R.id.btnPacifico);
    final String Ciudad = getIntent().getStringExtra(Maps4Activity.CiudadA);
    Button listoco = (Button) findViewById(R.id.btnListoCod);
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());


    CalendarView FechaPedidoCalendar = findViewById(R.id.FechaActualCalendar);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    final String datea = sdf.format(new Date(FechaPedidoCalendar.getDate()));
    SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
    final String nombreus = preferences.getString("nombreus", "Registrese");
    final String mailus = preferences.getString("mailus", "No");
    final String telefonous = preferences.getString("telefonous", "No");


    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }

    final EditText cedtxt = (EditText) findViewById(R.id.copytxtDni);
    final EditText nombretxt = (EditText) findViewById(R.id.copytxtname);
    final EditText cuentatxt = (EditText) findViewById(R.id.copytxtnoCuenta);

    final ImageView ImgPay = (ImageView) findViewById(R.id.imgpaypal);
    final ImageView ImgPP=(ImageView)findViewById(R.id.imgpayphone);


    if (ppa.equals("1")) {
      ImageView si = (ImageView) findViewById(R.id.SubirComp);
      TextView nro1 = (TextView) findViewById(R.id.nro1);
      TextView nro2 = (TextView) findViewById(R.id.nro2);
      TextView nro3 = (TextView) findViewById(R.id.nro3);
      TextView indicador1 = (TextView) findViewById(R.id.indicadorCedula);
      TextView indicador2 = (TextView) findViewById(R.id.indicadorcuenta);
      TextView indicador3 = (TextView) findViewById(R.id.indicadornombre);
      TextView instruccion1 = (TextView) findViewById(R.id.instruct1a);
      TextView instruccion2 = (TextView) findViewById(R.id.instruct2);
      TextView instruccion3 = (TextView) findViewById(R.id.instruct3);
      final AlertDialog.Builder builder = new AlertDialog.Builder(this);
      listoco.setVisibility(View.GONE);
      nombretxt.setVisibility(View.GONE);
      cedtxt.setVisibility(View.GONE);
      cuentatxt.setVisibility(View.GONE);
      si.setVisibility(View.GONE);
      nro1.setVisibility(View.GONE);
      nro2.setVisibility(View.GONE);
      nro3.setVisibility(View.GONE);
      indicador1.setVisibility(View.GONE);
      indicador2.setVisibility(View.GONE);
      indicador3.setVisibility(View.GONE);
      instruccion2.setVisibility(View.GONE);
      instruccion3.setVisibility(View.GONE);

      instruccion1.setText("Ha realizado con PayPal, pulse el boton aceptar para enviar su pedido a nuestros acesores, si tiene algun " +
              "inconveniente o desea comunicarse con nosotros, puede hacerlo en la pantalla de seleccion de Arreglos, a traves de nuestros telefonosos" +
              "o nuestras redes sociales.");

      imgLista = "1";
      btnBolivariano.setVisibility(View.GONE);
      btnProdubanco.setVisibility(View.GONE);
      btnGuayaquil.setVisibility(View.GONE);
      btnPichincha.setVisibility(View.GONE);
      btnPacifico.setVisibility(View.GONE);
      ImgPay.setVisibility(View.VISIBLE);
      Banco = "Paypal";
      actionBar.hide();
      if (imgLista.equals("0")) {

        Toast.makeText(EnvCodTransfActivity.this, "Por favor suba foto del pago", Toast.LENGTH_SHORT).show();

      } else {
        if (!compruebaConexion(getApplicationContext())) {
          Toast.makeText(getBaseContext(), "Necesaria conexión a internet para comprar ", Toast.LENGTH_SHORT).show();
        }


      }
      insertUser();

    }

    if (ppa.equals("2")) {
      ImageView si = (ImageView) findViewById(R.id.SubirComp);
      TextView nro1 = (TextView) findViewById(R.id.nro1);
      TextView nro2 = (TextView) findViewById(R.id.nro2);
      TextView nro3 = (TextView) findViewById(R.id.nro3);
      TextView indicador1 = (TextView) findViewById(R.id.indicadorCedula);
      TextView indicador2 = (TextView) findViewById(R.id.indicadorcuenta);
      TextView indicador3 = (TextView) findViewById(R.id.indicadornombre);
      TextView instruccion1 = (TextView) findViewById(R.id.instruct1a);
      TextView instruccion2 = (TextView) findViewById(R.id.instruct2);
      TextView instruccion3 = (TextView) findViewById(R.id.instruct3);
      final AlertDialog.Builder builder = new AlertDialog.Builder(this);
      listoco.setVisibility(View.GONE);
      nombretxt.setVisibility(View.GONE);
      cedtxt.setVisibility(View.GONE);
      cuentatxt.setVisibility(View.GONE);
      si.setVisibility(View.GONE);
      nro1.setVisibility(View.GONE);
      nro2.setVisibility(View.GONE);
      nro3.setVisibility(View.GONE);
      indicador1.setVisibility(View.GONE);
      indicador2.setVisibility(View.GONE);
      indicador3.setVisibility(View.GONE);
      instruccion2.setVisibility(View.GONE);
      instruccion3.setVisibility(View.GONE);

      instruccion1.setText("Ha realizado con Payphone, pulse el boton aceptar para enviar su pedido a nuestros acesores, si tiene algun " +
              "inconveniente o desea comunicarse con nosotros, puede hacerlo en la pantalla de seleccion de Arreglos, a traves de nuestros telefonosos" +
              "o nuestras redes sociales.");

      imgLista = "1";
      btnBolivariano.setVisibility(View.GONE);
      btnProdubanco.setVisibility(View.GONE);
      btnGuayaquil.setVisibility(View.GONE);
      btnPichincha.setVisibility(View.GONE);
      btnPacifico.setVisibility(View.GONE);
      ImgPP.setVisibility(View.VISIBLE);
      Banco = "Payphone";
      actionBar.hide();
      if (imgLista.equals("0")) {

        Toast.makeText(EnvCodTransfActivity.this, "Por favor suba foto del pago", Toast.LENGTH_SHORT).show();

      } else {
        if (!compruebaConexion(getApplicationContext())) {
          Toast.makeText(getBaseContext(), "Necesaria conexión a internet para comprar ", Toast.LENGTH_SHORT).show();
        } else {

        }


      }
      insertUser2();

    }

    if (banco.equals("Pacifico")) {

      btnBolivariano.setVisibility(View.GONE);
      btnProdubanco.setVisibility(View.GONE);
      btnGuayaquil.setVisibility(View.GONE);
      btnPichincha.setVisibility(View.GONE);
      Banco = "Pacifico";

      cuentatxt.setText("1016204875");

    }

    if (banco.equals("Pichincha")) {

      btnBolivariano.setVisibility(View.GONE);
      btnProdubanco.setVisibility(View.GONE);
      btnGuayaquil.setVisibility(View.GONE);
      btnPacifico.setVisibility(View.GONE);
      Banco = "Pichincha";

      cuentatxt.setText("2201056266");

    }

    if (banco.equals("Guayaquil")) {

      btnBolivariano.setVisibility(View.GONE);
      btnProdubanco.setVisibility(View.GONE);
      btnPacifico.setVisibility(View.GONE);
      btnPichincha.setVisibility(View.GONE);
      Banco = "Guayaquil";

      cuentatxt.setText("17492220");

    }

    if (banco.equals("Produbanco")) {

      btnBolivariano.setVisibility(View.GONE);
      btnPacifico.setVisibility(View.GONE);
      btnGuayaquil.setVisibility(View.GONE);
      btnPichincha.setVisibility(View.GONE);
      Banco = "Produbanco";

      cuentatxt.setText("12005614029");

    }

    if (banco.equals("Bolivariano")) {

      btnPacifico.setVisibility(View.GONE);
      btnProdubanco.setVisibility(View.GONE);
      btnGuayaquil.setVisibility(View.GONE);
      btnPichincha.setVisibility(View.GONE);
      Banco = "Bolivariano";

      cuentatxt.setText("1631021265");

    }


   final AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());


    listoco.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (imgLista.equals("0")) {

          Toast.makeText(EnvCodTransfActivity.this, "Por favor suba foto del pago", Toast.LENGTH_SHORT).show();

        } else {
          if (!compruebaConexion(getApplicationContext())) {
            Toast.makeText(getBaseContext(), "Necesaria conexión a internet para comprar ", Toast.LENGTH_SHORT).show();
          } else {


           uploadImage();



          }


        }


      }


    });


    ImageView si = (ImageView) findViewById(R.id.SubirComp);
    si.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        showFileChooser();
      }
    });

    Button wspend=(Button)findViewById(R.id.btnwspsend);
    wspend.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        WspFunct();
      }
    });


  }




     private void WspFunct()   {



        insertUser();



  }


  public void insertUser() {
    CalendarView FechaPedidoCalendar = findViewById(R.id.FechaActualCalendar);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String datea = sdf.format(new Date(FechaPedidoCalendar.getDate()));
    SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
    String nombreus = preferences.getString("nombreus", "Registrese");
    String mailus = preferences.getString("mailus", "No");
    String telefonous = preferences.getString("telefonous", "No");


    final int cantidadpass = getIntent().getIntExtra(PagoActivity.Cantidadpassm, -1);
    final String Nombpass = getIntent().getStringExtra(PagoActivity.NombreQuienRecibe);
    final String TelPass = getIntent().getStringExtra(PagoActivity.TelefonoQuienRecibe);
    final String Horapass = getIntent().getStringExtra(PagoActivity.FranjaHorariaQueRecibe);
    final String FechaPass = getIntent().getStringExtra(PagoActivity.DiaEntrega);
    final String DireccionPass = getIntent().getStringExtra(PagoActivity.Direccionpass);
    final String PrecioViajePassa = getIntent().getStringExtra(PagoActivity.PrecioViajePass);
    final String CallePrinPassad = getIntent().getStringExtra(PagoActivity.CallePrincPassss);
    final String CalleSecPassa = getIntent().getStringExtra(PagoActivity.CalleSecPass);
    final String ReferPass = getIntent().getStringExtra(PagoActivity.RefereciaPass);
    final String DetaUBPassa = getIntent().getStringExtra(PagoActivity.DetalleUbicacionPass);
    final String DetaAggPassa = getIntent().getStringExtra(PagoActivity.DetalleAGGPass);
    final String GloboTarjPass = getIntent().getStringExtra(PagoActivity.GloboOTarjetaPass);
    final String precioArreglo = getIntent().getStringExtra(ResumenPedidoActivity.PrecioArreglo);
    final String IdArreglo = getIntent().getStringExtra(ResumenPedidoActivity.NombreArreglo);
    final String precioTotal = getIntent().getStringExtra(ResumenPedidoActivity.PrecioTotal);

    final String sector = getIntent().getStringExtra(ResumenPedidoActivity.Sector);
    final String numeracion = getIntent().getStringExtra(ResumenPedidoActivity.Numeracion);
    final String especificacion = getIntent().getStringExtra(DetalleUbicacionesActivity.Especificacion);
    final String Ciudad = getIntent().getStringExtra(Maps4Activity.CiudadA);
    String a = datea;
    String b = telefonous;
    String c = nombreus;
    String d = mailus;
    String e = Nombpass;
    String f = TelPass;
    String g = FechaPass;
    String h = Horapass;
    String i = CallePrinPassad;
    String j = numeracion;
    String k = CalleSecPassa;
    String l = DetaUBPassa;
    String m = ReferPass;
    String n = IdArreglo;
    String o = precioTotal;
    String p = Banco.toUpperCase();
    String q = Nombpass;
    String r = DetaAggPassa;
    String s = especificacion;
    String t = "keyaccount";
    String v = sector;
    String w = "FRUTAGOLOSA";
    String x = "NO";
    String cc = "https://frutagolosa.com/FrutaGolosaApp/uploads/" + FechaPass.replace("/", "a") + xf2 + ".png";
    String y = GloboTarjPass;
    String z = "Por Confirmar";
    String aa = "motorizado";
    String bb = DireccionPass;
    String dd = Ciudad;
    String ee = PrecioViajePassa.replace("$","");

    RestAdapter adapter = new RestAdapter.Builder()
            .setEndpoint(ROOT_URL)
            .build();

    RegisterAPI api = adapter.create(RegisterAPI.class);


    api.inseruser(
            a,
            b,
            c,
            d,
            e,
            f,
            g,
            h,
            i,
            j,
            k,
            l,
            m,
            n,
            o,
            p,
            q,
            r,
            s,
            t,
            v,
            w,
            x,
            y,
            z,
            aa,
            bb,
            cc,
            dd,
            ee,


            new Callback<Response>() {
              @Override
              public void success(Response result, Response response) {
                      if(response.getBody()!=null) {
                        BufferedReader reader = null;

                        String output = "";

                        try {
                          reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                          output = reader.readLine();
                        } catch (IOException e) {
                          e.printStackTrace();
                        }
                        if (output.equals("Pedido Registrado Exitosamente")) {
                          Toast.makeText(EnvCodTransfActivity.this, output, Toast.LENGTH_LONG).show();
                          Intent d = new Intent(EnvCodTransfActivity.this, Inicio.class);
                          //  subircontador();
                          notificacion();
                          startActivity(d);

                          finish();
                        } else {


                          Toast.makeText(EnvCodTransfActivity.this, output, Toast.LENGTH_SHORT).show();

                        }

                      } }

              @Override
              public void failure(RetrofitError error) {
                Toast.makeText(EnvCodTransfActivity.this, error.toString(), Toast.LENGTH_LONG).show();

              }
            }
    );


  }
  public void insertUser2() {
    CalendarView FechaPedidoCalendar = findViewById(R.id.FechaActualCalendar);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String datea = sdf.format(new Date(FechaPedidoCalendar.getDate()));
    SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
    String nombreus = preferences.getString("nombreus", "Registrese");
    String mailus = preferences.getString("mailus", "No");
    String telefonous = preferences.getString("telefonous", "No");


    final int cantidadpass = getIntent().getIntExtra(PagoActivity.Cantidadpassm, -1);
    final String Nombpass = getIntent().getStringExtra(PagoActivity.NombreQuienRecibe);
    final String TelPass = getIntent().getStringExtra(PagoActivity.TelefonoQuienRecibe);
    final String Horapass = getIntent().getStringExtra(PagoActivity.FranjaHorariaQueRecibe);
    final String FechaPass = getIntent().getStringExtra(PagoActivity.DiaEntrega);
    final String DireccionPass = getIntent().getStringExtra(PagoActivity.Direccionpass);
    final String PrecioViajePassa = getIntent().getStringExtra(PagoActivity.PrecioViajePass);
    final String CallePrinPassad = getIntent().getStringExtra(PagoActivity.CallePrincPassss);
    final String CalleSecPassa = getIntent().getStringExtra(PagoActivity.CalleSecPass);
    final String ReferPass = getIntent().getStringExtra(PagoActivity.RefereciaPass);
    final String DetaUBPassa = getIntent().getStringExtra(PagoActivity.DetalleUbicacionPass);
    final String DetaAggPassa = getIntent().getStringExtra(PagoActivity.DetalleAGGPass);
    final String GloboTarjPass = getIntent().getStringExtra(PagoActivity.GloboOTarjetaPass);
    final String precioArreglo = getIntent().getStringExtra(ResumenPedidoActivity.PrecioArreglo);
    final String IdArreglo = getIntent().getStringExtra(ResumenPedidoActivity.NombreArreglo);
    final String precioTotal = getIntent().getStringExtra(ResumenPedidoActivity.PrecioTotal);

    final String sector = getIntent().getStringExtra(ResumenPedidoActivity.Sector);
    final String numeracion = getIntent().getStringExtra(ResumenPedidoActivity.Numeracion);
    final String especificacion = getIntent().getStringExtra(DetalleUbicacionesActivity.Especificacion);
    final String Ciudad = getIntent().getStringExtra(Maps4Activity.CiudadA);
    String a = datea;
    String b = telefonous;
    String c = nombreus;
    String d = mailus;
    String e = Nombpass;
    String f = TelPass;
    String g = FechaPass;
    String h = Horapass;
    String i = CallePrinPassad;
    String j = numeracion;
    String k = CalleSecPassa;
    String l = DetaUBPassa;
    String m = ReferPass;
    String n = IdArreglo;
    String o = precioTotal;
    String p = Banco.toUpperCase();
    String q = Nombpass;
    String r = DetaAggPassa;
    String s = especificacion;
    String t = "keyaccount";
    String v = sector;
    String w = "FRUTAGOLOSA";
    String x = "NO";
    String cc = "https://frutagolosa.com/FrutaGolosaApp/uploads/" + FechaPass.replace("/", "a") + xf2 + ".png";
    String y = GloboTarjPass;
    String z = "Por Confirmar";
    String aa = "motorizado";
    String bb = DireccionPass;
    String dd = Ciudad;
    String ee = PrecioViajePassa.replace("$","");

    RestAdapter adapter = new RestAdapter.Builder()
            .setEndpoint(ROOT_URL)
            .build();

    RegisterAPI api = adapter.create(RegisterAPI.class);


    api.inseruser(
            a,
            b,
            c,
            d,
            e,
            f,
            g,
            h,
            i,
            j,
            k,
            l,
            m,
            n,
            o,
            p,
            q,
            r,
            s,
            t,
            v,
            w,
            x,
            y,
            z,
            aa,
            bb,
            cc,
            dd,
            ee,


            new Callback<Response>() {
              @Override
              public void success(Response result, Response response) {
                if(response.getBody()!=null) {
                  BufferedReader reader = null;

                  String output = "";

                  try {
                    reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                    output = reader.readLine();
                  } catch (IOException e) {
                    e.printStackTrace();
                  }
                  if (output.equals("Pedido Registrado Exitosamente")) {
                    Toast.makeText(EnvCodTransfActivity.this, output, Toast.LENGTH_LONG).show();
                    Intent d = new Intent(EnvCodTransfActivity.this, Inicio.class);
                    //  subircontador();
                    notificacion();
                    startActivity(d);

                    finish();
                  } else {


                    Toast.makeText(EnvCodTransfActivity.this, output, Toast.LENGTH_SHORT).show();

                  }

                } }

              @Override
              public void failure(RetrofitError error) {
                Toast.makeText(EnvCodTransfActivity.this, error.toString(), Toast.LENGTH_LONG).show();

              }
            }
    );


  }
    public void BorrarPendiente() {

        SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        String nombreus = preferences.getString("nombreus", "Registrese");
        String mailus = preferences.getString("mailus", "No");
        String telefonous = preferences.getString("telefonous", "No");


        String a = telefonous;


        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build();

    BorrarIntentoApi api = adapter.create(BorrarIntentoApi.class);


        api.borrarpendiente(
                a,


                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {

                        BufferedReader reader = null;

                        String output = "";

                        try {
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                            output = reader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void failure(RetrofitError error) {


                    }
                }
        );


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

  public String getStringImagen(Bitmap bmp) {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
    byte[] imageBytes = baos.toByteArray();
    String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
    return encodedImage;
  }

  public void uploadImage() {
    final String FechaPass = getIntent().getStringExtra(PagoActivity.DiaEntrega).replace("/", "a");
    SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
    final String telefonous = preferences.getString("telefonous", "No");

    final ProgressDialog loading = ProgressDialog.show(this, "Subiendo...", "Espere por favor");

    StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
            new com.android.volley.Response.Listener<String>() {
              @Override
              public void onResponse(String response) {
                loading.dismiss();
                Toast.makeText(EnvCodTransfActivity.this, response, Toast.LENGTH_LONG).show();
                if(response.equals("Imagen Enviada")){

                    insertUser();

                }

                else{


                  Toast.makeText(EnvCodTransfActivity.this, "No se pudo enviar intente de nuevo", Toast.LENGTH_SHORT).show();

                }


              }
            }, new com.android.volley.Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        loading.dismiss();

      }
    }) {
      @Override
      protected Map<String, String> getParams() throws AuthFailureError {
        String imagen = getStringImagen(bitmap);
        String nombre = FechaPass + xf2;

        Map<String, String> params = new Hashtable<String, String>();
        params.put(KEY_IMAGE, imagen);
        params.put(KEY_NOMBRE, nombre);

        return params;
      }
    };

    RequestQueue requestQueue = Volley.newRequestQueue(this);
    requestQueue.add(stringRequest);
  }

  private void showFileChooser() {
    Intent intent = new Intent();
    intent.setType("image/*");
    intent.setAction(Intent.ACTION_GET_CONTENT);
    startActivityForResult(Intent.createChooser(intent, "Seleciona imagen"), PICK_IMAGE_REQUEST);

  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    ImageView si = (ImageView) findViewById(R.id.SubirComp);

    if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
      Uri filePath = data.getData();
      try {
        //Cómo obtener el mapa de bits de la Galería
        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
        bitmap = Bitmap.createScaledBitmap(bitmap, 440, 520, true);
        imgLista = "1";
        //Configuración del mapa de bits en ImageView
        si.setImageBitmap(bitmap);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }


  private void notificacion() {
    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    String NOTIFICATION_CHANNEL_ID = "frutagolosa_01";
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      @SuppressLint("WrongConstant") NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_MAX);
      // Configure the notification channel.
      notificationChannel.setDescription("Fruta Golosa Notifica");
      notificationChannel.enableLights(true);
      notificationChannel.setLightColor(Color.GREEN);
      notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
      notificationChannel.enableVibration(true);
      notificationManager.createNotificationChannel(notificationChannel);
    }
    PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
            new Intent(this, PedidoPendienteActiviy.class), PendingIntent.FLAG_UPDATE_CURRENT);

    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
    notificationBuilder.setAutoCancel(true)
            .setDefaults(Notification.DEFAULT_ALL)
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.mipmap.frutagolosa2)
            .setTicker("FrutaGolosa")
            //.setPriority(Notification.PRIORITY_MAX)
            .setContentTitle("Fruta Golosa App")
            .setContentText("Pedido registrado, detalles aqui")
            .setContentInfo("Compra en app")
            .setContentIntent(contentIntent)
    ;

    notificationManager.notify(1, notificationBuilder.build());
  }


  private void subircontador()

  {
    final String a = getIntent().getStringExtra(ResumenPedidoActivity.ContadorpedidosA);
    SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
    final String c = preferences.getString("mailus", "No");
    final String b= preferences.getString("telefonous", "No");

    RestAdapter adapter = new RestAdapter.Builder()
            .setEndpoint(ROOT_URL)
            .build();

    RegisterApiContador api = adapter.create(RegisterApiContador.class);


    api.inserContador(
            a,
            b,
            c,




            new Callback<Response>() {
              @Override
              public void success(Response result, Response response) {

                BufferedReader reader = null;

                String output = "";

                try {
                  reader = new BufferedReader(new InputStreamReader(result.getBody().in()));
                  output = reader.readLine();
                } catch (IOException e) {
                  e.printStackTrace();
                }


              }

              @Override
              public void failure(RetrofitError error) {
                Toast.makeText(EnvCodTransfActivity.this, error.toString(), Toast.LENGTH_LONG).show();

              }
            }
    );



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

}













