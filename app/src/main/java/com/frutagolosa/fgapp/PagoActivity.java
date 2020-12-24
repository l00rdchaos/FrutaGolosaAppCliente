package com.frutagolosa.fgapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.frutagolosa.fgapp.api.APIService;
import com.frutagolosa.fgapp.model.BienResponse;
import com.frutagolosa.fgapp.model.Post2;
import com.frutagolosa.fgapp.model.PostUser;
import com.frutagolosa.fgapp.model.idclient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PagoActivity extends AppCompatActivity {
  public static final String NombreQuienRecibe = "NombreQuienRecibe";
  public static final String TelefonoQuienRecibe = "TelfQuienRecibe";
  public static final String DiaEntrega = "DiaQueRecibe";
  public static final String FranjaHorariaQueRecibe = "HoraQueRecibe";
  public static final String Cantidadpassm = "ad";
  public static final String Direccionpass = "bc";
  public static final String PrecioViajePass = "asdasd";
  public static final String CallePrincPassss = "dasd";
  public static final String CalleSecPass = "qweasdasd";
  public static final String RefereciaPass = "tr";
  public static final String DetalleUbicacionPass = "ert";
  public static final String DetalleAGGPass = "sdfsrdf";
  public static final String GloboOTarjetaPass = "vtfgd";
  public static final String NombreArreglo = "nombrearreglo";
  public static final String cantidadArreglos = "cantidadArreglos";
  public static final String PrecioArreglo = "precioarreglo";
  public static final String PrecioTotal = "preciototal";
  public static final String Sector = "sector";
  public static final String Numeracion = "numeracion";
  public static final String PrecioTotalA = "precioTotasdasdalA";
  public static final String Especificacion = "especificacion";
  public static final String ContadorpedidosA="DescuentoAa2" ;
    public static final String CiudadA="ciudadd" ;
  public static final String pp = "ppx";
  public static final String BANCO = "BANCO";
  private Button buttonurl;
  private Button butverf;
  private  Button btnpayphoneenviar;
  private Button buttonppvertransid;
  private Button buttonppfin;
  private Button buttonpayphone;
  private Button buttonlinks;

  private TextView textnumberpp;
  private EditText numberpayphone;

  private TextView textverifica;
  private TextView textrealicepago;
  private TextView textDescargeapp;
  private TextView textcompraexito;

  @RequiresApi(api = Build.VERSION_CODES.O)
  @SuppressLint("JavascriptInterface")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pago);


    final int cantidadpass = getIntent().getIntExtra(ResumenPedidoActivity.Cantidadpassm, -1);
    final String Nombpass = getIntent().getStringExtra(ResumenPedidoActivity.NombreQuienRecibe);
    final String TelPass = getIntent().getStringExtra(ResumenPedidoActivity.TelefonoQuienRecibe);
    final String Horapass = getIntent().getStringExtra(ResumenPedidoActivity.FranjaHorariaQueRecibe);
    final String FechaPass = getIntent().getStringExtra(ResumenPedidoActivity.DiaEntrega);
    final String DireccionPass = getIntent().getStringExtra(ResumenPedidoActivity.Direccionpass);
    final String PrecioViajePassa = getIntent().getStringExtra(ResumenPedidoActivity.PrecioViajePass);
    final String CallePrinPassad = getIntent().getStringExtra(ResumenPedidoActivity.CallePrincPassss);
    final String CalleSecPassa = getIntent().getStringExtra(ResumenPedidoActivity.CalleSecPass);
    final String ReferPass = getIntent().getStringExtra(ResumenPedidoActivity.RefereciaPass);
    final String DetaUBPassa = getIntent().getStringExtra(ResumenPedidoActivity.DetalleUbicacionPass);
    final String DetaAggPassa = getIntent().getStringExtra(ResumenPedidoActivity.DetalleAGGPass);
    final String GloboTarjPass = getIntent().getStringExtra(ResumenPedidoActivity.GloboOTarjetaPass);
    final String precioArreglo = getIntent().getStringExtra(ResumenPedidoActivity.PrecioArreglo);
    final String IdArreglo = getIntent().getStringExtra(ResumenPedidoActivity.NombreArreglo);
    final String precioTotal = getIntent().getStringExtra(ResumenPedidoActivity.PrecioTotal);
    final String sector = getIntent().getStringExtra(ResumenPedidoActivity.Sector);
    final String numeracion = getIntent().getStringExtra(ResumenPedidoActivity.Numeracion);
    final String especificacion = getIntent().getStringExtra(ResumenPedidoActivity.Especificacion);
    final String preciototal = getIntent().getStringExtra(ResumenPedidoActivity.PrecioTotalA);
    final String tp = getIntent().getStringExtra(ResumenPedidoActivity.TPp);
    final String contadorpedidos = getIntent().getStringExtra(ResumenPedidoActivity.ContadorpedidosA);
    final WebView mywebview = (WebView) findViewById(R.id.webViewa);
    final WebView mywebviewpp = (WebView) findViewById(R.id.webViewapp);
    Button btnPaypal = (Button) findViewById(R.id.btnPaypal);
    Button btnPayPhone=(Button)findViewById(R.id.btnPayPhone);
   // Button buttonpayphone=(Button)findViewById(R.id.buttonpayphone);

    butverf=findViewById(R.id.buttonppverifica);

    btnpayphoneenviar= findViewById(R.id.buttonpayphone);
    buttonurl= findViewById(R.id.buttonlink);
    buttonppvertransid=findViewById(R.id.buttonppvertransid);
    buttonppfin=findViewById(R.id.buttonppfin);



    final Button btnTransferencia = (Button) findViewById(R.id.btnTransferencia);

    final ImageView btnpacifico = (ImageView) findViewById(R.id.btnpacifico);
    final ImageView btnpichincha = (ImageView) findViewById(R.id.btnpichincha);
    final ImageView btnguayaquil = (ImageView) findViewById(R.id.btnguayaquil);
    final ImageView btnbolivariano = (ImageView) findViewById(R.id.btnbolivariano);
    final ImageView btnprodubanco = (ImageView) findViewById(R.id.btnprodubanco);
    final TextView txtpreciof=(TextView) findViewById(R.id.txtpreciototalaa);
    final TextView txtinstruct1=(TextView) findViewById(R.id.instruct1a);
    final TextView nr1=(TextView) findViewById(R.id.nro1);
    setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
    txtpreciof.setText("PRECIO TOTAL: "+precioTotal+ " USD");


    textnumberpp=(TextView)findViewById(R.id.textViewpp);
    numberpayphone=(EditText)findViewById(R.id.edittextNumberpp);
    textverifica=findViewById(R.id.textverifica);
    textrealicepago=findViewById(R.id.textrealicepago);
    textDescargeapp=findViewById(R.id.textdescargueapp);
    textcompraexito=findViewById(R.id.textcompraexito);

    if (tp.equals("t")) {

      mywebview.setVisibility(View.GONE);
      nr1.setVisibility(View.VISIBLE);
      txtinstruct1.setVisibility(View.VISIBLE);
      mywebviewpp.setVisibility(View.GONE);
      btnpayphoneenviar.setVisibility(View.GONE);
      butverf.setVisibility(View.GONE);
      textnumberpp.setVisibility(View.GONE);
      numberpayphone.setVisibility(View.GONE);
      textverifica.setVisibility(View.GONE);
      buttonurl.setVisibility(View.GONE);
      textrealicepago.setVisibility(View.GONE);
      textDescargeapp.setVisibility(View.GONE);
      buttonppvertransid.setVisibility(View.GONE);
      textcompraexito.setVisibility(View.GONE);
      buttonppfin.setVisibility(View.GONE);
    }

    if (tp.equals("p")) {


      btnTransferencia.setVisibility(View.VISIBLE);
      mywebview.setVisibility(View.VISIBLE);
      mywebviewpp.setVisibility(View.GONE);
      btnpacifico.setVisibility(View.GONE);
      btnpichincha.setVisibility(View.GONE);
      btnprodubanco.setVisibility(View.GONE);
      btnbolivariano.setVisibility(View.GONE);
      btnguayaquil.setVisibility(View.GONE);
      nr1.setVisibility(View.GONE);
      txtinstruct1.setVisibility(View.GONE);
      btnpayphoneenviar.setVisibility(View.GONE);
      butverf.setVisibility(View.GONE);
      textnumberpp.setVisibility(View.GONE);
      numberpayphone.setVisibility(View.GONE);
      textverifica.setVisibility(View.GONE);
      buttonurl.setVisibility(View.GONE);

      textrealicepago.setVisibility(View.GONE);
      textDescargeapp.setVisibility(View.GONE);
      buttonppvertransid.setVisibility(View.GONE);
      textcompraexito.setVisibility(View.GONE);
      buttonppfin.setVisibility(View.GONE);
    }


    if (tp.equals("pp")) {

///PRIMERO
      btnpacifico.setVisibility(View.GONE);
      btnpichincha.setVisibility(View.GONE);
      btnprodubanco.setVisibility(View.GONE);
      btnbolivariano.setVisibility(View.GONE);
      btnguayaquil.setVisibility(View.GONE);
      mywebview.setVisibility(View.GONE);
      nr1.setVisibility(View.GONE);
      txtinstruct1.setVisibility(View.GONE);
      mywebviewpp.setVisibility(View.GONE);
      btnpayphoneenviar.setVisibility(View.GONE);
      butverf.setVisibility(View.VISIBLE);
      textnumberpp.setVisibility(View.VISIBLE);
      numberpayphone.setVisibility(View.VISIBLE);
      textverifica.setVisibility(View.VISIBLE);
      buttonurl.setVisibility(View.GONE);
      textrealicepago.setVisibility(View.GONE);
      textDescargeapp.setVisibility(View.GONE);
      buttonppvertransid.setVisibility(View.GONE);
      textcompraexito.setVisibility(View.GONE);
      buttonppfin.setVisibility(View.GONE);

//      btnTransferencia.setVisibility(View.VISIBLE);
//      mywebview.setVisibility(View.GONE);
//      mywebviewpp.setVisibility(View.VISIBLE);
//      btnpacifico.setVisibility(View.GONE);
//      btnpichincha.setVisibility(View.GONE);
//      btnprodubanco.setVisibility(View.GONE);
//      btnbolivariano.setVisibility(View.GONE);
//      btnguayaquil.setVisibility(View.GONE);
//      nr1.setVisibility(View.GONE);
//      txtinstruct1.setVisibility(View.GONE);
    }

    btnPaypal.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        btnpacifico.setVisibility(View.GONE);
        btnpichincha.setVisibility(View.GONE);
        btnprodubanco.setVisibility(View.GONE);
        btnbolivariano.setVisibility(View.GONE);
        btnguayaquil.setVisibility(View.GONE);
        mywebview.setVisibility(View.VISIBLE);
        btnTransferencia.setVisibility(View.VISIBLE);
        nr1.setVisibility(View.GONE);
        txtinstruct1.setVisibility(View.GONE);
        mywebviewpp.setVisibility(View.GONE);
        btnpayphoneenviar.setVisibility(View.GONE);
        butverf.setVisibility(View.GONE);
        textnumberpp.setVisibility(View.GONE);
        numberpayphone.setVisibility(View.GONE);
        textverifica.setVisibility(View.GONE);
        buttonurl.setVisibility(View.GONE);
        textrealicepago.setVisibility(View.GONE);
        textDescargeapp.setVisibility(View.GONE);
        buttonppvertransid.setVisibility(View.GONE);
        textcompraexito.setVisibility(View.GONE);
        buttonppfin.setVisibility(View.GONE);
      }
    });

    btnTransferencia.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        btnpacifico.setVisibility(View.VISIBLE);
        btnpichincha.setVisibility(View.VISIBLE);
        btnprodubanco.setVisibility(View.VISIBLE);
        btnbolivariano.setVisibility(View.VISIBLE);
        btnguayaquil.setVisibility(View.VISIBLE);
        mywebview.setVisibility(View.GONE);
        nr1.setVisibility(View.VISIBLE);
        txtinstruct1.setVisibility(View.VISIBLE);
        mywebviewpp.setVisibility(View.GONE);
        btnpayphoneenviar.setVisibility(View.GONE);
        butverf.setVisibility(View.GONE);
        textnumberpp.setVisibility(View.GONE);
        numberpayphone.setVisibility(View.GONE);
        textverifica.setVisibility(View.GONE);
        buttonurl.setVisibility(View.GONE);
        textrealicepago.setVisibility(View.GONE);
        textDescargeapp.setVisibility(View.GONE);
        buttonppvertransid.setVisibility(View.GONE);
        textcompraexito.setVisibility(View.GONE);
        buttonppfin.setVisibility(View.GONE);

      }
    });

    btnPayPhone.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        //SEGUNDO

        btnpacifico.setVisibility(View.GONE);
        btnpichincha.setVisibility(View.GONE);
        btnprodubanco.setVisibility(View.GONE);
        btnbolivariano.setVisibility(View.GONE);
        btnguayaquil.setVisibility(View.GONE);
        nr1.setVisibility(View.GONE);
        txtinstruct1.setVisibility(View.GONE);
        mywebviewpp.setVisibility(View.GONE);
        mywebview.setVisibility(View.GONE);
        butverf.setVisibility(View.VISIBLE);
        btnpayphoneenviar.setVisibility(View.GONE);
        textnumberpp.setVisibility(View.VISIBLE);
        numberpayphone.setVisibility(View.VISIBLE);
        textverifica.setVisibility(View.VISIBLE);
        buttonurl.setVisibility(View.GONE);
        textrealicepago.setVisibility(View.GONE);
        textDescargeapp.setVisibility(View.GONE);
        buttonppvertransid.setVisibility(View.GONE);
        textcompraexito.setVisibility(View.GONE);
        buttonppfin.setVisibility(View.GONE);

      }
    });


    mywebviewpp.getSettings().setJavaScriptEnabled(true);
    mywebviewpp.getSettings().setSupportMultipleWindows(true);
    mywebviewpp.getSettings().setDomStorageEnabled(true);
    mywebviewpp.getSettings().setAppCacheEnabled(false);
    mywebviewpp.getSettings().setLoadWithOverviewMode(true);
    mywebviewpp.getSettings().setDomStorageEnabled(true);
    mywebviewpp.getSettings().setSafeBrowsingEnabled(true);

    mywebviewpp.setRendererPriorityPolicy(WebView.RENDERER_PRIORITY_BOUND,true);
    mywebviewpp.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
    mywebviewpp.setWebChromeClient(new WebChromeClient());
    mywebviewpp.setWebViewClient(new WebViewClient(){
      @Override
      public boolean shouldOverrideUrlLoading(WebView view, String url){
        view.loadUrl(url);
        return true;
      }
      @Override
      public void onPageFinished(WebView view, String url) {

        final String Ciudad = getIntent().getStringExtra(Maps4Activity.CiudadA);
        if(url.equals("https://frutagolosa.com/FrutaGolosaApp/PayPal/confirmado.html")){
          String banco = "Paypal";
          Intent d = new Intent(PagoActivity.this, EnvCodTransfActivity.class);
          String ppa = "1";
          d.putExtra(NombreQuienRecibe, Nombpass);
          d.putExtra(TelefonoQuienRecibe, TelPass);
          d.putExtra(DiaEntrega, FechaPass);
          d.putExtra(FranjaHorariaQueRecibe, Horapass);
          d.putExtra(Cantidadpassm, cantidadpass);
          d.putExtra(Direccionpass, DireccionPass);
          d.putExtra(PrecioViajePass, PrecioViajePassa);
          d.putExtra(CallePrincPassss, CallePrinPassad);
          d.putExtra(CalleSecPass, CalleSecPassa);
          d.putExtra(RefereciaPass, ReferPass);
          d.putExtra(DetalleUbicacionPass, DetaUBPassa);
          d.putExtra(DetalleAGGPass, DetaAggPassa);
          d.putExtra(GloboOTarjetaPass, GloboTarjPass);
          d.putExtra(PrecioArreglo, precioArreglo);
          d.putExtra(NombreArreglo, IdArreglo);
          d.putExtra(PrecioTotal, precioTotal);
          d.putExtra(Sector, sector);
          d.putExtra(Numeracion, numeracion);
          d.putExtra(Especificacion, especificacion);
          d.putExtra(PrecioTotal, precioTotal);
          d.putExtra(ContadorpedidosA,contadorpedidos);
          d.putExtra(pp, ppa);
          d.putExtra(BANCO, banco);
          d.putExtra(CiudadA,Ciudad);
          startActivity(d);



        }

        if(url.equals("https://fgrutagolosa.com/FrutaGolosaApp/PayPal/cancelar.html")){
          mywebviewpp.loadUrl("https://frutagolosa.com/FrutaGolosaApp/PayPal/pay.php?a=" + precioTotal);

        }


      }




    });
    mywebviewpp.loadUrl("https://frutagolosa.com/FrutaGolosaApp/PayPhone/Pruebas/respuestapphone.php?a=" + precioTotal);
   // mywebviewpp.loadDataWithBaseURL("https://frutagolosa.com/FrutaGolosaApp/PayPhone/Pruebas/respuestapphone.php?a=" + precioTotal,"text/html","text/html", "UTF-8", null);




    mywebview.getSettings().setJavaScriptEnabled(true);
    mywebview.setWebChromeClient(new WebChromeClient());
    mywebview.setWebViewClient(new WebViewClient(){
      @Override
      public boolean shouldOverrideUrlLoading(WebView view, String url){
        view.loadUrl(url);
        return true;
      }
      @Override
      public void onPageFinished(WebView view, String url) {

        final String Ciudad = getIntent().getStringExtra(Maps4Activity.CiudadA);
        if(url.equals("https://frutagolosa.com/FrutaGolosaApp/PayPal/confirmado.html")){
          String banco = "Paypal";
          Intent d = new Intent(PagoActivity.this, EnvCodTransfActivity.class);
          String ppa = "1";
          d.putExtra(NombreQuienRecibe, Nombpass);
          d.putExtra(TelefonoQuienRecibe, TelPass);
          d.putExtra(DiaEntrega, FechaPass);
          d.putExtra(FranjaHorariaQueRecibe, Horapass);
          d.putExtra(Cantidadpassm, cantidadpass);
          d.putExtra(Direccionpass, DireccionPass);
          d.putExtra(PrecioViajePass, PrecioViajePassa);
          d.putExtra(CallePrincPassss, CallePrinPassad);
          d.putExtra(CalleSecPass, CalleSecPassa);
          d.putExtra(RefereciaPass, ReferPass);
          d.putExtra(DetalleUbicacionPass, DetaUBPassa);
          d.putExtra(DetalleAGGPass, DetaAggPassa);
          d.putExtra(GloboOTarjetaPass, GloboTarjPass);
          d.putExtra(PrecioArreglo, precioArreglo);
          d.putExtra(NombreArreglo, IdArreglo);
          d.putExtra(PrecioTotal, precioTotal);
          d.putExtra(Sector, sector);
          d.putExtra(Numeracion, numeracion);
          d.putExtra(Especificacion, especificacion);
          d.putExtra(PrecioTotal, precioTotal);
          d.putExtra(ContadorpedidosA,contadorpedidos);
          d.putExtra(pp, ppa);
          d.putExtra(BANCO, banco);
          d.putExtra(CiudadA,Ciudad);
          startActivity(d);



        }

        if(url.equals("https://fgrutagolosa.com/FrutaGolosaApp/PayPal/cancelar.html")){
          mywebview.loadUrl("https://frutagolosa.com/FrutaGolosaApp/PayPal/pay.php?a=" + precioTotal);

        }


      }




    });
    mywebview.loadUrl("https://frutagolosa.com/FrutaGolosaApp/PayPal/pay.php?a=" + precioTotal);





      final String Ciudad = getIntent().getStringExtra(Maps4Activity.CiudadA);
    String ppa = "1";


    btnpacifico.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        String banco = "Pacifico";
        Intent d = new Intent(PagoActivity.this, EnvCodTransfActivity.class);
        String ppa = "0";
        d.putExtra(NombreQuienRecibe, Nombpass);
        d.putExtra(TelefonoQuienRecibe, TelPass);
        d.putExtra(DiaEntrega, FechaPass);
        d.putExtra(FranjaHorariaQueRecibe, Horapass);
        d.putExtra(Cantidadpassm, cantidadpass);
        d.putExtra(Direccionpass, DireccionPass);
        d.putExtra(PrecioViajePass, PrecioViajePassa);
        d.putExtra(CallePrincPassss, CallePrinPassad);
        d.putExtra(CalleSecPass, CalleSecPassa);
        d.putExtra(RefereciaPass, ReferPass);
        d.putExtra(DetalleUbicacionPass, DetaUBPassa);
        d.putExtra(DetalleAGGPass, DetaAggPassa);
        d.putExtra(GloboOTarjetaPass, GloboTarjPass);
        d.putExtra(PrecioArreglo, precioArreglo);
        d.putExtra(NombreArreglo, IdArreglo);
        d.putExtra(PrecioTotal, precioTotal);
        d.putExtra(Sector, sector);
        d.putExtra(Numeracion, numeracion);
        d.putExtra(Especificacion, especificacion);
        d.putExtra(ContadorpedidosA,contadorpedidos);
        d.putExtra(PrecioTotal, precioTotal);
        d.putExtra(pp, ppa);
        d.putExtra(BANCO, banco);
         d.putExtra(CiudadA,Ciudad);

        startActivity(d);

      }
    }); //boton del clicklister

    btnpichincha.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        String banco = "Pichincha";
        Intent d = new Intent(PagoActivity.this, EnvCodTransfActivity.class);
        String ppa = "0";
        d.putExtra(NombreQuienRecibe, Nombpass);
        d.putExtra(TelefonoQuienRecibe, TelPass);
        d.putExtra(DiaEntrega, FechaPass);
        d.putExtra(FranjaHorariaQueRecibe, Horapass);
        d.putExtra(Cantidadpassm, cantidadpass);
        d.putExtra(Direccionpass, DireccionPass);
        d.putExtra(PrecioViajePass, PrecioViajePassa);
        d.putExtra(CallePrincPassss, CallePrinPassad);
        d.putExtra(CalleSecPass, CalleSecPassa);
        d.putExtra(RefereciaPass, ReferPass);
        d.putExtra(DetalleUbicacionPass, DetaUBPassa);
        d.putExtra(DetalleAGGPass, DetaAggPassa);
        d.putExtra(GloboOTarjetaPass, GloboTarjPass);
        d.putExtra(PrecioArreglo, precioArreglo);
        d.putExtra(NombreArreglo, IdArreglo);
        d.putExtra(PrecioTotal, precioTotal);
        d.putExtra(Sector, sector);
        d.putExtra(Numeracion, numeracion);
        d.putExtra(Especificacion, especificacion);
        d.putExtra(ContadorpedidosA,contadorpedidos);
        d.putExtra(PrecioTotal, precioTotal);
        d.putExtra(pp, ppa);
        d.putExtra(BANCO, banco);
          d.putExtra(CiudadA,Ciudad);
        startActivity(d);

      }
    }); //boton del clicklister

    btnguayaquil.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        String banco = "Guayaquil";
        Intent d = new Intent(PagoActivity.this, EnvCodTransfActivity.class);
        String ppa = "0";
        d.putExtra(NombreQuienRecibe, Nombpass);
        d.putExtra(TelefonoQuienRecibe, TelPass);
        d.putExtra(DiaEntrega, FechaPass);
        d.putExtra(FranjaHorariaQueRecibe, Horapass);
        d.putExtra(Cantidadpassm, cantidadpass);
        d.putExtra(Direccionpass, DireccionPass);
        d.putExtra(PrecioViajePass, PrecioViajePassa);
        d.putExtra(CallePrincPassss, CallePrinPassad);
        d.putExtra(CalleSecPass, CalleSecPassa);
        d.putExtra(RefereciaPass, ReferPass);
        d.putExtra(DetalleUbicacionPass, DetaUBPassa);
        d.putExtra(DetalleAGGPass, DetaAggPassa);
        d.putExtra(GloboOTarjetaPass, GloboTarjPass);
        d.putExtra(PrecioArreglo, precioArreglo);
        d.putExtra(NombreArreglo, IdArreglo);
        d.putExtra(PrecioTotal, precioTotal);
        d.putExtra(Sector, sector);
        d.putExtra(Numeracion, numeracion);
        d.putExtra(Especificacion, especificacion);
        d.putExtra(ContadorpedidosA,contadorpedidos);
        d.putExtra(PrecioTotal, precioTotal);
        d.putExtra(pp, ppa);
        d.putExtra(BANCO, banco);
          d.putExtra(CiudadA,Ciudad);
        startActivity(d);

      }
    }); //boton del clicklister

    btnbolivariano.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        String banco = "Bolivariano";
        Intent d = new Intent(PagoActivity.this, EnvCodTransfActivity.class);
        String ppa = "0";
        d.putExtra(NombreQuienRecibe, Nombpass);
        d.putExtra(TelefonoQuienRecibe, TelPass);
        d.putExtra(DiaEntrega, FechaPass);
        d.putExtra(FranjaHorariaQueRecibe, Horapass);
        d.putExtra(Cantidadpassm, cantidadpass);
        d.putExtra(Direccionpass, DireccionPass);
        d.putExtra(PrecioViajePass, PrecioViajePassa);
        d.putExtra(CallePrincPassss, CallePrinPassad);
        d.putExtra(CalleSecPass, CalleSecPassa);
        d.putExtra(RefereciaPass, ReferPass);
        d.putExtra(DetalleUbicacionPass, DetaUBPassa);
        d.putExtra(DetalleAGGPass, DetaAggPassa);
        d.putExtra(GloboOTarjetaPass, GloboTarjPass);
        d.putExtra(PrecioArreglo, precioArreglo);
        d.putExtra(NombreArreglo, IdArreglo);
        d.putExtra(PrecioTotal, precioTotal);
        d.putExtra(Sector, sector);
        d.putExtra(Numeracion, numeracion);
        d.putExtra(Especificacion, especificacion);
        d.putExtra(ContadorpedidosA,contadorpedidos);
        d.putExtra(PrecioTotal, precioTotal);
        d.putExtra(pp, ppa);
        d.putExtra(BANCO, banco);
          d.putExtra(CiudadA,Ciudad);
        startActivity(d);

      }
    }); //boton del clicklister

    btnprodubanco.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        String banco = "Produbanco";
        Intent d = new Intent(PagoActivity.this, EnvCodTransfActivity.class);
        String ppa = "0";
        d.putExtra(NombreQuienRecibe, Nombpass);
        d.putExtra(TelefonoQuienRecibe, TelPass);
        d.putExtra(DiaEntrega, FechaPass);
        d.putExtra(FranjaHorariaQueRecibe, Horapass);
        d.putExtra(Cantidadpassm, cantidadpass);
        d.putExtra(Direccionpass, DireccionPass);
        d.putExtra(PrecioViajePass, PrecioViajePassa);
        d.putExtra(CallePrincPassss, CallePrinPassad);
        d.putExtra(CalleSecPass, CalleSecPassa);
        d.putExtra(RefereciaPass, ReferPass);
        d.putExtra(DetalleUbicacionPass, DetaUBPassa);
        d.putExtra(DetalleAGGPass, DetaAggPassa);
        d.putExtra(GloboOTarjetaPass, GloboTarjPass);
        d.putExtra(PrecioArreglo, precioArreglo);
        d.putExtra(NombreArreglo, IdArreglo);
        d.putExtra(PrecioTotal, precioTotal);
        d.putExtra(Sector, sector);
        d.putExtra(Numeracion, numeracion);
        d.putExtra(Especificacion, especificacion);
        d.putExtra(ContadorpedidosA,contadorpedidos);
        d.putExtra(PrecioTotal, precioTotal);
        d.putExtra(pp, ppa);
        d.putExtra(BANCO, banco);
          d.putExtra(CiudadA,Ciudad);
        startActivity(d);

      }
    }); //boton del clicklister


    ///////////PAYPHONE/////////
    SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
    String telefonous = preferences.getString("telefonous", "No");
    numberpayphone.setText(telefonous);

    ///////////PAYPHONE/////////

    butverf.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String numerotelefono=numberpayphone.getText().toString();
       // Toast.makeText(PagoActivity.this, "REntro",Toast.LENGTH_SHORT).show();
        userget(numerotelefono);
      }
    });

    btnpayphoneenviar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        ///Datos de Entrada
        int valorfinal= Integer.parseInt(precioArreglo)*100;
        String numerotelefono=numberpayphone.getText().toString();
        Random r = new Random();
        String idvalorDado = String.valueOf(r.nextInt(10000));


        datospayphone(valorfinal,numerotelefono,idvalorDado);


      }
    });



    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
  }

  public void datospayphone(Integer valorfinal, String numerotelefono,String idvalorDado){
    final String authHeader="Bearer 4k7ObhvtbqllouEaaXnxt52ycIMk63E8tB-zfrRLA2j0xVbdeghQdORzE3fsGlB1DvjglEwktp8kqmaKT5PH8iLDXULsMSe6aKAeVR-jcLMRGXe0H2kRtT5LJc2OfRKfx_VS1kvg4gpwfa_DiZ5jP3BaxD-kOLGBMWoFCRC3yLj8bFO15vB-ll0cPzjMc7wkagv53ZcwBpOPpUmlEvxMDNvWxEid_SyO4GRUQbTjN5W49AyIUNoaYDbDeXb5oALORqAKGYjV2zrOwhhmxva8yLRrRaNfi38L8Jh49HFxMCrGbm_c0VzzWp0WPo_t8KZUKAGF9w";
    final String phoneNumber=numerotelefono;
    final String countryCode="593";
    final Integer amount=valorfinal;
    final Integer amountWithoutTax=valorfinal;
    final String clientTransactionId="fg"+idvalorDado;
    final String  storeId="6495fc14-f0a1-47ed-ad45-38376803f914";

    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl("https://pay.payphonetodoesposible.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    APIService jsonHolderApi=retrofit.create(APIService.class);

    jsonHolderApi.getPost(
            authHeader,
            phoneNumber,
            countryCode,
            amount,
            amountWithoutTax,
            clientTransactionId,storeId).enqueue(new Callback<BienResponse>() {
      @Override
      public void onResponse(Call<BienResponse> call, Response<BienResponse> response) {

        Log.d("Resp: ",response.toString());


        if(response.isSuccessful()){

          final String idpp= String.valueOf(response.body().getTransactionId());
          Toast.makeText(PagoActivity.this, "Revise PayPhone",Toast.LENGTH_SHORT).show();
          buttonppvertransid.setVisibility(View.VISIBLE);
          btnpayphoneenviar.setVisibility(View.GONE);
          buttonppvertransid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              estado(idpp);

            }
          });



        }else{
          Toast.makeText(PagoActivity.this, "Registrate", Toast.LENGTH_LONG).show();

        }

      }

      @Override
      public void onFailure(Call<BienResponse> call, Throwable t) {
        Toast.makeText(PagoActivity.this, "Error",Toast.LENGTH_SHORT).show();
      }
    });



  }
  ///////////FIN PAYPHONE/////////

//////////VERIF. TransaccionId////////////

  public void estado(String idcode){

    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl("https://pay.payphonetodoesposible.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    APIService jsonHolderApi=retrofit.create(APIService.class);

    Call<idclient> call= jsonHolderApi.getIdUser(  "Bearer 4k7ObhvtbqllouEaaXnxt52ycIMk63E8tB-zfrRLA2j0xVbdeghQdORzE3fsGlB1DvjglEwktp8kqmaKT5PH8iLDXULsMSe6aKAeVR-jcLMRGXe0H2kRtT5LJc2OfRKfx_VS1kvg4gpwfa_DiZ5jP3BaxD-kOLGBMWoFCRC3yLj8bFO15vB-ll0cPzjMc7wkagv53ZcwBpOPpUmlEvxMDNvWxEid_SyO4GRUQbTjN5W49AyIUNoaYDbDeXb5oALORqAKGYjV2zrOwhhmxva8yLRrRaNfi38L8Jh49HFxMCrGbm_c0VzzWp0WPo_t8KZUKAGF9w",
            idcode);
    call.enqueue(new Callback <idclient>() {
      @Override
      public void onResponse(Call<idclient> call, Response<idclient> response) {
        if (!response.isSuccessful()){

    Toast.makeText(PagoActivity.this,"Algo salio mal",Toast.LENGTH_LONG).show();
          return;
        }
          idclient postList=response.body();
          Integer idestado = postList.getStatusCode();
          String isestadostring= String.valueOf(idestado);



        if(idestado==1){
          Toast.makeText(PagoActivity.this,"PAGO PENDIENTE",Toast.LENGTH_LONG).show();

        }if(idestado==2){
          Toast.makeText(PagoActivity.this,"PAGO CANCELADO",Toast.LENGTH_LONG).show();

        }if(idestado==3){
          Toast.makeText(PagoActivity.this,"PAGO COMPLETADO",Toast.LENGTH_LONG).show();


//          textrealicepago.setVisibility(View.GONE);
//          buttonppvertransid.setVisibility(View.GONE);
//          textcompraexito.setVisibility(View.GONE);
//          buttonppfin.setVisibility(View.GONE);
//          textnumberpp.setVisibility(View.GONE);
//          numberpayphone.setVisibility(View.GONE);

          final int cantidadpass = getIntent().getIntExtra(ResumenPedidoActivity.Cantidadpassm, -1);
          final String Nombpass = getIntent().getStringExtra(ResumenPedidoActivity.NombreQuienRecibe);
          final String TelPass = getIntent().getStringExtra(ResumenPedidoActivity.TelefonoQuienRecibe);
          final String Horapass = getIntent().getStringExtra(ResumenPedidoActivity.FranjaHorariaQueRecibe);
          final String FechaPass = getIntent().getStringExtra(ResumenPedidoActivity.DiaEntrega);
          final String DireccionPass = getIntent().getStringExtra(ResumenPedidoActivity.Direccionpass);
          final String PrecioViajePassa = getIntent().getStringExtra(ResumenPedidoActivity.PrecioViajePass);
          final String CallePrinPassad = getIntent().getStringExtra(ResumenPedidoActivity.CallePrincPassss);
          final String CalleSecPassa = getIntent().getStringExtra(ResumenPedidoActivity.CalleSecPass);
          final String ReferPass = getIntent().getStringExtra(ResumenPedidoActivity.RefereciaPass);
          final String DetaUBPassa = getIntent().getStringExtra(ResumenPedidoActivity.DetalleUbicacionPass);
          final String DetaAggPassa = getIntent().getStringExtra(ResumenPedidoActivity.DetalleAGGPass);
          final String GloboTarjPass = getIntent().getStringExtra(ResumenPedidoActivity.GloboOTarjetaPass);
          final String precioArreglo = getIntent().getStringExtra(ResumenPedidoActivity.PrecioArreglo);
          final String IdArreglo = getIntent().getStringExtra(ResumenPedidoActivity.NombreArreglo);
          final String precioTotal = getIntent().getStringExtra(ResumenPedidoActivity.PrecioTotal);
          final String sector = getIntent().getStringExtra(ResumenPedidoActivity.Sector);
          final String numeracion = getIntent().getStringExtra(ResumenPedidoActivity.Numeracion);
          final String especificacion = getIntent().getStringExtra(ResumenPedidoActivity.Especificacion);
          final String tp = getIntent().getStringExtra(ResumenPedidoActivity.TPp);
          final String contadorpedidos = getIntent().getStringExtra(ResumenPedidoActivity.ContadorpedidosA);

          final String Ciudad = getIntent().getStringExtra(Maps4Activity.CiudadA);


          String banco = "PayPhone";
          Intent d = new Intent(PagoActivity.this, EnvCodTransfActivity.class);
          String ppa = "2";
          d.putExtra(NombreQuienRecibe, Nombpass);
          d.putExtra(TelefonoQuienRecibe, TelPass);
          d.putExtra(DiaEntrega, FechaPass);
          d.putExtra(FranjaHorariaQueRecibe, Horapass);
          d.putExtra(Cantidadpassm, cantidadpass);
          d.putExtra(Direccionpass, DireccionPass);
          d.putExtra(PrecioViajePass, PrecioViajePassa);
          d.putExtra(CallePrincPassss, CallePrinPassad);
          d.putExtra(CalleSecPass, CalleSecPassa);
          d.putExtra(RefereciaPass, ReferPass);
          d.putExtra(DetalleUbicacionPass, DetaUBPassa);
          d.putExtra(DetalleAGGPass, DetaAggPassa);
          d.putExtra(GloboOTarjetaPass, GloboTarjPass);
          d.putExtra(PrecioArreglo, precioArreglo);
          d.putExtra(NombreArreglo, IdArreglo);
          d.putExtra(PrecioTotal, precioTotal);
          d.putExtra(Sector, sector);
          d.putExtra(Numeracion, numeracion);
          d.putExtra(Especificacion, especificacion);
          d.putExtra(PrecioTotal, precioTotal);
          d.putExtra(ContadorpedidosA,contadorpedidos);
          d.putExtra(pp, ppa);
          d.putExtra(BANCO, banco);
          d.putExtra(CiudadA,Ciudad);

          // Toast.makeText(PagoActivity.this, "LISTo LocO",Toast.LENGTH_SHORT).show();
          startActivity(d);


        }

      }



      @Override
      public void onFailure(Call<idclient> call, Throwable t) {

        Toast.makeText(PagoActivity.this,"Algo salio mal",Toast.LENGTH_LONG).show();

      }
    });




  }


  //////////////////////////////


  ///////////VERIFICAR PAYPHONE/////////


  public void userget(String numerotelefono){
    final String n=numerotelefono;
    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl("https://pay.payphonetodoesposible.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();



    APIService jsonHolderApi=retrofit.create(APIService.class);

    Call<PostUser> call= jsonHolderApi.getUser(  "Bearer 4k7ObhvtbqllouEaaXnxt52ycIMk63E8tB-zfrRLA2j0xVbdeghQdORzE3fsGlB1DvjglEwktp8kqmaKT5PH8iLDXULsMSe6aKAeVR-jcLMRGXe0H2kRtT5LJc2OfRKfx_VS1kvg4gpwfa_DiZ5jP3BaxD-kOLGBMWoFCRC3yLj8bFO15vB-ll0cPzjMc7wkagv53ZcwBpOPpUmlEvxMDNvWxEid_SyO4GRUQbTjN5W49AyIUNoaYDbDeXb5oALORqAKGYjV2zrOwhhmxva8yLRrRaNfi38L8Jh49HFxMCrGbm_c0VzzWp0WPo_t8KZUKAGF9w",
            n,593);

    call.enqueue(new Callback<PostUser>() {
      @Override
      public void onResponse(Call<PostUser> call, Response<PostUser> response) {

        if (!response.isSuccessful()){
          Log.d("Resp:",response.toString());
          Log.d("Resp:",response.message());
          textverifica.setVisibility(View.GONE);
          buttonurl.setVisibility(View.VISIBLE);
          btnpayphoneenviar.setVisibility(View.GONE);
          butverf.setVisibility(View.GONE);
          textrealicepago.setVisibility(View.GONE);

          textDescargeapp.setVisibility(View.VISIBLE);

          final String url= "https://play.google.com/store/apps/details?id=payPhone_Android.PayPhone_Android";
          buttonurl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              textDescargeapp.setVisibility(View.GONE);
              textverifica.setVisibility(View.VISIBLE);
              butverf.setVisibility(View.VISIBLE);
              buttonurl.setVisibility(View.GONE);
              Uri uri = Uri.parse(url);
              Intent intent = new Intent(Intent.ACTION_VIEW, uri);
              startActivity(intent);
            }
          });



          Toast.makeText(PagoActivity.this, "Descargue la APP",Toast.LENGTH_LONG).show();


        }else{


       // Log.d("Resp:", "response 33: "+new Gson().toJson(response.raw().cacheResponse()) );


          Toast.makeText(PagoActivity.this, "REGISTRADO PAYPHONE",Toast.LENGTH_SHORT).show();
          textrealicepago.setVisibility(View.VISIBLE);
          numberpayphone.setVisibility(View.VISIBLE);
          textverifica.setVisibility(View.GONE);
          btnpayphoneenviar.setVisibility(View.VISIBLE);
          butverf.setVisibility(View.GONE);
          buttonurl.setVisibility(View.GONE);




        }


      }

      @Override
      public void onFailure(Call<PostUser> call, Throwable t) {

      }
    });


  }



  public void finalizarbutton(View view) {


    final int cantidadpass = getIntent().getIntExtra(ResumenPedidoActivity.Cantidadpassm, -1);
    final String Nombpass = getIntent().getStringExtra(ResumenPedidoActivity.NombreQuienRecibe);
    final String TelPass = getIntent().getStringExtra(ResumenPedidoActivity.TelefonoQuienRecibe);
    final String Horapass = getIntent().getStringExtra(ResumenPedidoActivity.FranjaHorariaQueRecibe);
    final String FechaPass = getIntent().getStringExtra(ResumenPedidoActivity.DiaEntrega);
    final String DireccionPass = getIntent().getStringExtra(ResumenPedidoActivity.Direccionpass);
    final String PrecioViajePassa = getIntent().getStringExtra(ResumenPedidoActivity.PrecioViajePass);
    final String CallePrinPassad = getIntent().getStringExtra(ResumenPedidoActivity.CallePrincPassss);
    final String CalleSecPassa = getIntent().getStringExtra(ResumenPedidoActivity.CalleSecPass);
    final String ReferPass = getIntent().getStringExtra(ResumenPedidoActivity.RefereciaPass);
    final String DetaUBPassa = getIntent().getStringExtra(ResumenPedidoActivity.DetalleUbicacionPass);
    final String DetaAggPassa = getIntent().getStringExtra(ResumenPedidoActivity.DetalleAGGPass);
    final String GloboTarjPass = getIntent().getStringExtra(ResumenPedidoActivity.GloboOTarjetaPass);
    final String precioArreglo = getIntent().getStringExtra(ResumenPedidoActivity.PrecioArreglo);
    final String IdArreglo = getIntent().getStringExtra(ResumenPedidoActivity.NombreArreglo);
    final String precioTotal = getIntent().getStringExtra(ResumenPedidoActivity.PrecioTotal);
    final String sector = getIntent().getStringExtra(ResumenPedidoActivity.Sector);
    final String numeracion = getIntent().getStringExtra(ResumenPedidoActivity.Numeracion);
    final String especificacion = getIntent().getStringExtra(ResumenPedidoActivity.Especificacion);
    final String tp = getIntent().getStringExtra(ResumenPedidoActivity.TPp);
    final String contadorpedidos = getIntent().getStringExtra(ResumenPedidoActivity.ContadorpedidosA);

    final String Ciudad = getIntent().getStringExtra(Maps4Activity.CiudadA);


    String banco = "PayPhone";
    Intent d = new Intent(PagoActivity.this, EnvCodTransfActivity.class);
    String ppa = "2";
    d.putExtra(NombreQuienRecibe, Nombpass);
    d.putExtra(TelefonoQuienRecibe, TelPass);
    d.putExtra(DiaEntrega, FechaPass);
    d.putExtra(FranjaHorariaQueRecibe, Horapass);
    d.putExtra(Cantidadpassm, cantidadpass);
    d.putExtra(Direccionpass, DireccionPass);
    d.putExtra(PrecioViajePass, PrecioViajePassa);
    d.putExtra(CallePrincPassss, CallePrinPassad);
    d.putExtra(CalleSecPass, CalleSecPassa);
    d.putExtra(RefereciaPass, ReferPass);
    d.putExtra(DetalleUbicacionPass, DetaUBPassa);
    d.putExtra(DetalleAGGPass, DetaAggPassa);
    d.putExtra(GloboOTarjetaPass, GloboTarjPass);
    d.putExtra(PrecioArreglo, precioArreglo);
    d.putExtra(NombreArreglo, IdArreglo);
    d.putExtra(PrecioTotal, precioTotal);
    d.putExtra(Sector, sector);
    d.putExtra(Numeracion, numeracion);
    d.putExtra(Especificacion, especificacion);
    d.putExtra(PrecioTotal, precioTotal);
    d.putExtra(ContadorpedidosA,contadorpedidos);
    d.putExtra(pp, ppa);
    d.putExtra(BANCO, banco);
    d.putExtra(CiudadA,Ciudad);

   // Toast.makeText(PagoActivity.this, "LISTo LocO",Toast.LENGTH_SHORT).show();
    startActivity(d);

  }


  public boolean onOptionsItemSelected(MenuItem item) {
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

  public class CustomJavaScriptInterface {
    Context mContext;

    /**
     * Instantiate the interface and set the context
     */
    CustomJavaScriptInterface(Context c) {
      mContext = c;
    }


    /**
     * retrieve the ids
     */
    public void getIds(final String myIds) {



    }
  }

}
