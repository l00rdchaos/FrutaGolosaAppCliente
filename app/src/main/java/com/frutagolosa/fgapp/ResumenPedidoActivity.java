package com.frutagolosa.fgapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.frutagolosa.fgapp.api.ApiInterface4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ResumenPedidoActivity extends AppCompatActivity {
    public static final String NombreQuienRecibe="NombreQuienRecibe" ;
    public static final String TelefonoQuienRecibe="TelfQuienRecibe" ;
    public static final String DiaEntrega="DiaQueRecibe" ;
    public static final String FranjaHorariaQueRecibe="HoraQueRecibe" ;
    public static final String Cantidadpassm="ad" ;
    public static final String Direccionpass="bc" ;
    public static final String PrecioViajePass="asdasd" ;
    public static final String CallePrincPassss="dasd" ;
    public static final String CalleSecPass="qweasdasd" ;
    public static final String RefereciaPass="tr" ;
    public static final String DetalleUbicacionPass="ert" ;
    public static final String DetalleAGGPass="sdfsrdf" ;
    public static final String GloboOTarjetaPass="vtfgd" ;
    public static final String NombreArreglo="nombrearreglo" ;
    public static final String PrecioArreglo="precioarreglo" ;
    public static final String PrecioTotal="preciototal" ;
    public static final String Sector="sector" ;
    public static final String Numeracion="numeracion" ;
    public static final String Especificacion="especificacion" ;
    public static final String PrecioTotalA="preciototalaa" ;
    public static final String TPp="TPP" ;
    public static final String ContadorpedidosA="DescuentoAa2" ;
    public static final String CiudadA="ciudadd" ;
    public  int preciototal=1;
    public  String preciototal2="a";
    public  int preciototale=1;
    public String contadorpedidos;
    public  int precio;
    public int dac;
   public int preciof;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_pedido);

        final int cantidadpass=getIntent().getIntExtra(DetalleUbicacionesActivity.Cantidadpassm,-1);
        final String Nombpass=getIntent().getStringExtra(DetalleUbicacionesActivity.NombreQuienRecibe);
        final String TelPass=getIntent().getStringExtra(DetalleUbicacionesActivity.TelefonoQuienRecibe);
        final String Horapass=getIntent().getStringExtra(DetalleUbicacionesActivity.FranjaHorariaQueRecibe);
        final String FechaPass=getIntent().getStringExtra(DetalleUbicacionesActivity.DiaEntrega);
        final String DireccionPass=getIntent().getStringExtra(DetalleUbicacionesActivity.Direccionpass);
        final String PrecioViajePassa=getIntent().getStringExtra(DetalleUbicacionesActivity.PrecioViajePass);
        final String CallePrinPassad=getIntent().getStringExtra(DetalleUbicacionesActivity.CallePrincPassss);
        final String CalleSecPassa=getIntent().getStringExtra(DetalleUbicacionesActivity.CalleSecPass);
        final String ReferPass=getIntent().getStringExtra(DetalleUbicacionesActivity.RefereciaPass);
        final String DetaUBPassa=getIntent().getStringExtra(DetalleUbicacionesActivity.DetalleUbicacionPass);
        final String DetaAggPassa=getIntent().getStringExtra(DetalleUbicacionesActivity.DetalleAGGPass);
        final String GloboTarjPass=getIntent().getStringExtra(DetalleUbicacionesActivity.GloboOTarjetaPass);
        final String precioArreglo = getIntent().getStringExtra(DetalleUbicacionesActivity.PrecioArreglo);
        final String IdArreglo = getIntent().getStringExtra(DetalleUbicacionesActivity.NombreArreglo);
        final String sector = getIntent().getStringExtra(DetalleUbicacionesActivity.Sector);
        final String numeracion = getIntent().getStringExtra(DetalleUbicacionesActivity.Numeracion);
        final String especificacion = getIntent().getStringExtra(DetalleUbicacionesActivity.Especificacion);


        final int id = getResources().getIdentifier(IdArreglo+"1", "drawable", getPackageName());
        ImageView imgcomparr=findViewById(R.id.ImgCompArr);
        Glide.with(this).asBitmap().transform(new CenterCrop(),new RoundedCorners(10)).load("https://frutagolosa.com/FrutaGolosaApp/Administrador/images/" +IdArreglo+"1.jpg").into(imgcomparr);

        TextView sectortxt=(TextView) findViewById(R.id.TxtSector);
        TextView NA=(TextView) findViewById(R.id.NombreArregloPr);
        TextView PA=(TextView) findViewById(R.id.PrecioArreglo);
        TextView NP=(TextView) findViewById(R.id.NombrePrecibr);
        final TextView TP=(TextView) findViewById(R.id.TelefonoPrRecibe);
        TextView FecR=(TextView) findViewById(R.id.FechaRecibepr);
        TextView HoraRec=(TextView) findViewById(R.id.HoraRecibepr);
        TextView DirecPR=(TextView) findViewById(R.id.AdicionalPorEnvio);

        TextView CallePrin=(TextView) findViewById(R.id.CallePrincipalPr);
        TextView Calles=(TextView) findViewById(R.id.CalleSecPR);
        TextView Refer=(TextView) findViewById(R.id.ReferenciaPr);
        TextView DetalleUBICA=(TextView) findViewById(R.id.DetalleUBPR);
        TextView GloboTA=(TextView) findViewById(R.id.GloboOTG);
        TextView Detagg=(TextView) findViewById(R.id.Detagg);
        final TextView Precf=(TextView) findViewById(R.id.PrecioFinal);
        TextView Numerac=(TextView) findViewById(R.id.TxtNumeracion);
        TextView Especificaciontxa=(TextView) findViewById(R.id.TxtEspecificacionrp);
        final TextView textpreciofinal=(TextView) findViewById(R.id.textpreciofinal);
        final TextView descuentotxt=(TextView) findViewById(R.id.txtdescuento);
final Button btndescuento=(Button)findViewById(R.id.btnDescuento);
        SharedPreferences preferences2=getSharedPreferences("login", Context.MODE_PRIVATE);
        String c=preferences2.getString("mailus","No");
        String t=preferences2.getString("telefonous","No");
        RestAdapter adapter2 = new RestAdapter.Builder()
                .setEndpoint("https://frutagolosa.com/FrutaGolosaApp/compras.php?t="+t)
                .build();

        ApiInterface4 api2 = adapter2.create(ApiInterface4.class);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        api2.comprarver(
                c,
                t,



                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {

                        BufferedReader reader = null;
                        String output = "";
                 if(response.getBody()!=null) {

                     try {
                         reader = new BufferedReader(new InputStreamReader(result.getBody().in()));


                         output = reader.readLine();

                         if (output.equals("0")) {

                             Precf.setVisibility(View.INVISIBLE);

                             contadorpedidos = "1";
                         }

                         if (output.equals("1")) {
                             Precf.setVisibility(View.INVISIBLE);
                             descuentotxt.setText("Descuento de 10%");
                             btndescuento.setVisibility(View.VISIBLE);
                             descuentotxt.setVisibility(View.VISIBLE);
                             Toast.makeText(ResumenPedidoActivity.this, "Pulse para aplicar descuento de 10%", Toast.LENGTH_SHORT).show();

                         }

                         if (output.equals("2")) {

                             Precf.setVisibility(View.INVISIBLE);
                             descuentotxt.setText("Descuento de 20%");
                             btndescuento.setVisibility(View.VISIBLE);
                             descuentotxt.setVisibility(View.VISIBLE);
                             Toast.makeText(ResumenPedidoActivity.this, "Pulse para aplicar descuento de 20%", Toast.LENGTH_SHORT).show();


                             contadorpedidos = "3";


                         }


                         if (output.equals("3")) {

                             Precf.setVisibility(View.INVISIBLE);
                             descuentotxt.setText("Descuento de 30%");
                             btndescuento.setVisibility(View.VISIBLE);
                             descuentotxt.setVisibility(View.VISIBLE);
                             Toast.makeText(ResumenPedidoActivity.this, "Pulse para aplicar descuento de 20%", Toast.LENGTH_SHORT).show();


                             contadorpedidos = "4";
                         }

                         if (output.equals("4")) {
                             Precf.setVisibility(View.INVISIBLE);
                             descuentotxt.setText("Descuento de 50%");
                             btndescuento.setVisibility(View.VISIBLE);
                             descuentotxt.setVisibility(View.VISIBLE);
                             Toast.makeText(ResumenPedidoActivity.this, "Pulse para aplicar descuento de 50%", Toast.LENGTH_SHORT).show();

                             contadorpedidos = "0";
                         }


                     } catch (IOException e) {
                         e.printStackTrace();
                     }

                 }   }

                    @Override
                    public void failure(RetrofitError error) {

                        Toast.makeText(ResumenPedidoActivity.this, "No se puede aplicar descuentos, revise su internet", Toast.LENGTH_LONG).show();

                    }
                }

        );


        NA.setText("ARREGLO: "+IdArreglo.toUpperCase());
        PA.setText("PRECIO ARREGLO: "+precioArreglo+"USD");
        NP.setText("RECIBE: "+Nombpass);
        TP.setText("TELEFONO: "+TelPass);
        FecR.setText("FECHA ENTREGA: "+FechaPass);
        HoraRec.setText("FRANJA HORARIA: "+Horapass);
        DirecPR.setText("COSTO DE ENVIO: "+PrecioViajePassa);

        CallePrin.setText("CALLLE PRIN: "+CallePrinPassad);
        Calles.setText("CALLE SEC: "+CalleSecPassa);
        Refer.setText("REFERENCIA: "+ReferPass);
        DetalleUBICA.setText("DETALLE EXTRA: "+DetaUBPassa);
        GloboTA.setText("MOTIVO: "+GloboTarjPass);
        Detagg.setText("EN TARJETA: "+DetaAggPassa);
        Numerac.setText("NUMERACION O PISO :"+numeracion);
        Especificaciontxa.setText("ESPECIFICACION :"+especificacion);
        sectortxt.setText("SECTOR: "+sector);



        if (precioArreglo.equals("19")) {
            precio=19;
        }
        if (precioArreglo.equals("20")) {
            precio=20;
        }

        if (precioArreglo.equals("21")) {
            precio=21;
        }
        if (precioArreglo.equals("22")) {
            precio=22;
        }

        if (precioArreglo.equals("23")) {
            precio=23;
        }
        if (precioArreglo.equals("24")) {
            precio=24;
        }

        if (precioArreglo.equals("25")) {
            precio=25;
        }
        if (precioArreglo.equals("26")) {
            precio=26;
        }
        if (precioArreglo.equals("27")) {
            precio=27;
        }

        if (precioArreglo.equals("28")) {
            precio=28;
        }



        if (precioArreglo.equals("29")) {
            precio=29;
        }

        if (precioArreglo.equals("30")) {
            precio=30;
        }

        if (precioArreglo.equals("31")) {
            precio=31;
        }

        if (precioArreglo.equals("32")) {
            precio=32;
        }

        if (precioArreglo.equals("33")) {
            precio=33;
        }


        if (precioArreglo.equals("34")) {
            precio=34;
        }

        if (precioArreglo.equals("36")) {
            precio=35;
        }

        if (precioArreglo.equals("36")) {
            precio=36;
        }

        if (precioArreglo.equals("37")) {
            precio=37;
        }

        if (precioArreglo.equals("38")) {
            precio=38;
        }

        if (precioArreglo.equals("39")) {
            precio=39;
        }

        if (precioArreglo.equals("40")) {
            precio=40;
        }

        if (precioArreglo.equals("41")) {
            precio=41;
        }

        if (precioArreglo.equals("42")) {
            precio=42;
        }

        if (precioArreglo.equals("43")) {
            precio=43;
        }

        if (precioArreglo.equals("44")) {
            precio=44;
        }

        if (precioArreglo.equals("45")) {
            precio=45;
        }

        if (precioArreglo.equals("46")) {
            precio=46;
        }

        if (precioArreglo.equals("47")) {
            precio=47;
        }

        if (precioArreglo.equals("48")) {
            precio=48;
        }

        if (precioArreglo.equals("49")) {
            precio=49;
        }

        if (precioArreglo.equals("50")) {
            precio=50;
        }

        if (precioArreglo.equals("51")) {
            precio=51;
        }

        if (precioArreglo.equals("52")) {
            precio=52;
        }

        if (precioArreglo.equals("53")) {
            precio=53;
        }

        if (precioArreglo.equals("54")) {
            precio=54;
        }

        if (precioArreglo.equals("55")) {
            precio=55;
        }

        if (precioArreglo.equals("56")) {
            precio=56;
        }

        if (precioArreglo.equals("57")) {
            precio=57;
        }

        if (precioArreglo.equals("58")) {
            precio=58;
        }

        if (precioArreglo.equals("59")) {
            precio=59;
        }

        if (precioArreglo.equals("60")) {
            precio=60;
        }

        if (precioArreglo.equals("61")) {
            precio=61;
        }

        if (precioArreglo.equals("62")) {
            precio=62;
        }

        if (precioArreglo.equals("63")) {
            precio=63;
        }

        if (precioArreglo.equals("64")) {
            precio=64;
        }

        if (precioArreglo.equals("65")) {
            precio=65;
        }

        //--------------------------------------------

        if (PrecioViajePassa.equals("$0")){

            preciof=0;

        }

        if (PrecioViajePassa.equals("$1")){

            preciof=1;

        }

        if (PrecioViajePassa.equals("$2")){

            preciof=2;

        }
        if (PrecioViajePassa.equals("$3")){

            preciof=3;

        }
        if (PrecioViajePassa.equals("$4")){

            preciof=4;

        }
        if (PrecioViajePassa.equals("$5")){

            preciof=5;

        }
        if (PrecioViajePassa.equals("$6")){

            preciof=6;

        }
        if (PrecioViajePassa.equals("$7")){

            preciof=7;

        }
        if (PrecioViajePassa.equals("$8")){

            preciof=8;

        }

        if (PrecioViajePassa.equals("$9")){

            preciof=9;

        }

        if (PrecioViajePassa.equals("%10")){

            preciof=10;

        }



        preciototal=precio+preciof;
        preciototale=precio+preciof;
        textpreciofinal.setText("PRECIO TOTAL: "+String.valueOf(preciototale)+" USD");

        btndescuento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String desct=descuentotxt.getText().toString().trim();

                if(desct.equals("Descuento de 10%")){
                    dac=precio*10;
                    dac=dac/100;
                    precio=precio-dac;
                    preciototale=precio+preciof;
                    textpreciofinal.setText("PRECIO TOTAL: "+String.valueOf(preciototale)+" USD");
                    btndescuento.setVisibility(View.GONE);
                    descuentotxt.setVisibility(View.GONE);

                    preciototal2= String.valueOf(preciototale);
                }


                if(desct.equals("Descuento de 20%")){
                    dac=precio*20;
                    dac=dac/100;
                    precio=precio-dac;
                    preciototale=precio+preciof;
                    textpreciofinal.setText("PRECIO TOTAL: "+String.valueOf(preciototale)+" USD");
                    btndescuento.setVisibility(View.GONE);
                    descuentotxt.setVisibility(View.GONE);

                    preciototal2= String.valueOf(preciototale);
                }

                if(desct.equals("Descuento de 30%")){
dac=precio*30;
                    dac=dac/100;
                    precio=precio-dac;
                    preciototale=precio+preciof;
                    textpreciofinal.setText("PRECIO TOTAL: "+String.valueOf(preciototale)+" USD");
                    btndescuento.setVisibility(View.GONE);
                    descuentotxt.setVisibility(View.GONE);

   preciototal2= String.valueOf(preciototale);
                }

                if(desct.equals("Descuento de 50%")){
                    dac=precio*50;
                    dac=dac/100;
                    precio=precio-dac;
                    preciototale=precio+preciof;
                    textpreciofinal.setText("PRECIO TOTAL: "+String.valueOf(preciototale)+" USD");
                    btndescuento.setVisibility(View.GONE);
                    descuentotxt.setVisibility(View.GONE);
       preciototal2= String.valueOf(preciototale);


                }
            }
        });




        final Button btnb = (Button) findViewById(R.id.btnPagar);
        final Button btnb2 = (Button) findViewById(R.id.btnpagar2);


        preciototal2= String.valueOf(preciototale);


        btnb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent d = new Intent(ResumenPedidoActivity.this, PagoActivity.class);
String tp="t";
                final String Ciudad = getIntent().getStringExtra(DetalleUbicacionesActivity.CiudadA);
             preciototal2= String.valueOf(preciototale);
                d.putExtra(NombreQuienRecibe, Nombpass);
                d.putExtra(TelefonoQuienRecibe, TelPass);
                d.putExtra(DiaEntrega, FechaPass);
                d.putExtra(FranjaHorariaQueRecibe, Horapass);
                d.putExtra(Cantidadpassm,cantidadpass);
                d.putExtra(Direccionpass,DireccionPass);
                d.putExtra(PrecioViajePass,PrecioViajePassa);
                d.putExtra(CallePrincPassss,CallePrinPassad);
                d.putExtra(CalleSecPass,CalleSecPassa);
                d.putExtra(RefereciaPass,ReferPass);
                d.putExtra(DetalleUbicacionPass,DetaUBPassa);
                d.putExtra(DetalleAGGPass,DetaAggPassa);
                d.putExtra(GloboOTarjetaPass,GloboTarjPass);
                d.putExtra(PrecioArreglo, precioArreglo);
                d.putExtra(NombreArreglo,IdArreglo.toUpperCase());
                d.putExtra(PrecioTotal,preciototal2);
                d.putExtra(Sector,sector);
                d.putExtra(Numeracion,numeracion);
                d.putExtra(Especificacion,especificacion);
                d.putExtra(TPp,tp);
                d.putExtra(PrecioTotalA,preciototale);
                d.putExtra(ContadorpedidosA,contadorpedidos);
                d.putExtra(CiudadA,Ciudad);
                startActivity(d);

            }
        }); //boton del clicklister

        btnb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preciototal2= String.valueOf(preciototale);
                Intent d = new Intent(ResumenPedidoActivity.this, PagoActivity.class);

                String tp="p";
                d.putExtra(NombreQuienRecibe, Nombpass);
                d.putExtra(TelefonoQuienRecibe, TelPass);
                d.putExtra(DiaEntrega, FechaPass);
                d.putExtra(FranjaHorariaQueRecibe, Horapass);
                d.putExtra(Cantidadpassm,cantidadpass);
                d.putExtra(Direccionpass,DireccionPass);
                d.putExtra(PrecioViajePass,PrecioViajePassa);
                d.putExtra(CallePrincPassss,CallePrinPassad);
                d.putExtra(CalleSecPass,CalleSecPassa);
                d.putExtra(RefereciaPass,ReferPass);
                d.putExtra(DetalleUbicacionPass,DetaUBPassa);
                d.putExtra(DetalleAGGPass,DetaAggPassa);
                d.putExtra(GloboOTarjetaPass,GloboTarjPass);
                d.putExtra(PrecioArreglo, precioArreglo);
                d.putExtra(NombreArreglo,IdArreglo.toUpperCase());
                d.putExtra(PrecioTotal,preciototal2);
                d.putExtra(Sector,sector);
                d.putExtra(TPp,tp);
                d.putExtra(Numeracion,numeracion);
                d.putExtra(Especificacion,especificacion);
                d.putExtra(PrecioTotalA,preciototale);
                d.putExtra(ContadorpedidosA,contadorpedidos);
                startActivity(d);

            }
        });
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
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
