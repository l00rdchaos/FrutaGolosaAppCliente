package com.frutagolosa.fgapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
    Button btnPaypal = (Button) findViewById(R.id.btnPaypal);
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
    if (tp.equals("t")) {

      mywebview.setVisibility(View.GONE);
      nr1.setVisibility(View.VISIBLE);
      txtinstruct1.setVisibility(View.VISIBLE);

    }

    if (tp.equals("p")) {


      btnTransferencia.setVisibility(View.VISIBLE);
      mywebview.setVisibility(View.VISIBLE);
      btnpacifico.setVisibility(View.GONE);
      btnpichincha.setVisibility(View.GONE);
      btnprodubanco.setVisibility(View.GONE);
      btnbolivariano.setVisibility(View.GONE);
      btnguayaquil.setVisibility(View.GONE);
      nr1.setVisibility(View.GONE);
      txtinstruct1.setVisibility(View.GONE);
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

      }
    });


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

    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
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
