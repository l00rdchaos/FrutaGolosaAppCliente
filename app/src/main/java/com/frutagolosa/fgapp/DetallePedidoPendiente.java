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
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.request.RequestOptions;
import com.frutagolosa.fgapp.api.RegisterApiContador;
import com.frutagolosa.fgapp.api.RegisterEstado;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class DetallePedidoPendiente extends AppCompatActivity {
    public static final String IDPEDIDOA="id0" ;
    public static final String IdArregloA="id2" ;
    public static final String FechaPedidoA="id3" ;
    public static final String NombreClientesA="id4" ;
    public static final String CorreoClienteA="id5" ;
    public static final String TelefonoClienteA="id6" ;
    public static final String NombreqRecibeA="id7" ;
    public static final String FechaQrecibeA="id8" ;
    public static final String TelefonoQrecibeA="id9" ;
    public static final String FranjaHorariaA="id10" ;
    public static final String CallePrincipalA="id11" ;
    public static final String CalleSecundariaA="id12" ;
    public static final String CasaempresaedifcioA="id13" ;
    public static final String referenciaA="id14" ;
    public static final String PortadaTarjetaA="id15" ;
    public static final String TextoTarjetaA="id16" ;
    public static final String EspecificacionA="id17" ;
    public static final String EstadoA="id18" ;
    public static final String EstadoB="id188" ;
    public static final String KeyAccountA="id19" ;
    public static final String ParroquiaA="id20" ;
    public static final String Costo_EnvioA="id21" ;
    public static final String GloboA="id22" ;
    public static final String SectorA="id22" ;
    public static final String MotorizadoA="id23klkl" ;
    public static final String CoordenadaA="id23" ;
    public static final String ROOT_URL="https://frutagolosa.com/FrutaGolosaApp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pedido_pendiente);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        final String IDPEDIDO=getIntent().getStringExtra(PedidoPendienteActiviy.IdPEDIDOA);
        final String idarrelgo=getIntent().getStringExtra(PedidoPendienteActiviy.IdArregloA);
        final String Fecha_Pedido=getIntent().getStringExtra(PedidoPendienteActiviy.FechaPedidoA);
        final String Nombre_Cliente=getIntent().getStringExtra(PedidoPendienteActiviy.NombreClientesA);
        final String Telefono_Cliente=getIntent().getStringExtra(PedidoPendienteActiviy.TelefonoClienteA);
        final String Correo_Cliente=getIntent().getStringExtra(PedidoPendienteActiviy.CorreoClienteA);
        final  String Nombre_qRecibe=getIntent().getStringExtra(PedidoPendienteActiviy.NombreqRecibeA);
        final String Fecha_qRecibe=getIntent().getStringExtra(PedidoPendienteActiviy.FechaQrecibeA);
        final String Telefono_qrecibe=getIntent().getStringExtra(PedidoPendienteActiviy.TelefonoQrecibeA);
        final String Franaja_horara=getIntent().getStringExtra(PedidoPendienteActiviy.FranjaHorariaA);
        final String Calle_principal=getIntent().getStringExtra(PedidoPendienteActiviy.CallePrincipalA);
        final String Calle_secundaria=getIntent().getStringExtra(PedidoPendienteActiviy.CalleSecundariaA);
        final String Casaempresaedificio=getIntent().getStringExtra(PedidoPendienteActiviy.CasaempresaedifcioA);
        final String referencia=getIntent().getStringExtra(PedidoPendienteActiviy.referenciaA);
        final  String Portada_tarjeta=getIntent().getStringExtra(PedidoPendienteActiviy.PortadaTarjetaA);
        final String Texto_tarjeta=getIntent().getStringExtra(PedidoPendienteActiviy.TextoTarjetaA);
        final String Especificacion=getIntent().getStringExtra(PedidoPendienteActiviy.EspecificacionA);
        final String Estado=getIntent().getStringExtra(PedidoPendienteActiviy.EstadoA);
        final String Keyaccount=getIntent().getStringExtra(PedidoPendienteActiviy.KeyAccountA);
        final String Parroquia=getIntent().getStringExtra(PedidoPendienteActiviy.ParroquiaA);
        final String Costo_envio=getIntent().getStringExtra(PedidoPendienteActiviy.Costo_EnvioA);
        final String Globo=getIntent().getStringExtra(PedidoPendienteActiviy.GloboA);
        final String sector=getIntent().getStringExtra(PedidoPendienteActiviy.SectorA);
        final  String coordenadas=getIntent().getStringExtra(PedidoPendienteActiviy.CoordenadaA);
        final  String imgal=getIntent().getStringExtra(PedidoPendienteActiviy.imgalA);
        final  String imgaent=getIntent().getStringExtra(PedidoPendienteActiviy.imgaentA);
        final  String TiempoFab=getIntent().getStringExtra(PedidoPendienteActiviy.TiempoFAB);
        TextView Fechapidetxt =findViewById(R.id.vfechaPedidoTxt);

        TextView FechaEnttxt=findViewById(R.id.vFechaEnt);
        TextView Arreglotxt=findViewById(R.id.vArreglo);
        TextView Recibetxt=findViewById(R.id.vNombreQrecibetxt);
        TextView TelefonoQuienRtxt =findViewById(R.id.vTelefonoQrecibetxt);
        TextView FranjaHorariatxt=findViewById(R.id.vFranjaHorariatxt);
        TextView CallePrintx=findViewById(R.id.vCallePrincipaltxt);
        TextView CalleSecundatxt=findViewById(R.id.vCalleSecunadariatxt);
        TextView CasaEmpreseDtxt =findViewById(R.id.vCasaEmpresaEdifciotxt);
        TextView Referenciatxt =findViewById(R.id.vReferenciatxt);
        TextView PortadaTxt=findViewById(R.id.vPortadaTarjetatxt);
        TextView TextoTarjetatxt=findViewById(R.id.vtextoTarjetatxt);
        TextView Especificaciontxt=findViewById(R.id.vEspecificaciontxt);
        TextView Globotxt =findViewById(R.id.vglobotxt);
        TextView Sectortxt =findViewById(R.id.vSectortxt);
        TextView CostoEnviotxt =findViewById(R.id.vcostoenviotxt);
        TextView ViewEncuesta=findViewById(R.id.ViewEncuesta);

        TextView Estadotxt=findViewById(R.id.vEstadoTxt);
        ImageView imgar=findViewById(R.id.imageView2);
        ImageView ImgArreglolisto=findViewById(R.id.ImgArregloListo);
        ImageView ImgArregloEnt=findViewById(R.id.imgArregloEnt);


        Fechapidetxt.setText("FECHA PEDIDO\n"+Fecha_Pedido);
        FechaEnttxt.setText("FECHA DE ENTREGA\n"+Fecha_qRecibe);
        Arreglotxt.setText("ARREGLO\n"+idarrelgo);
        Recibetxt.setText("RERCIBE\n"+Nombre_qRecibe);
        FranjaHorariatxt.setText("HORA\n"+Franaja_horara);
        TelefonoQuienRtxt.setText("TELEFONO\n"+Telefono_qrecibe);
        CallePrintx.setText("PRINCIPAL\n"+Calle_principal);
        CalleSecundatxt.setText("SECUNDARIA\n"+Calle_secundaria);
        CasaEmpreseDtxt.setText("ESTRUCTURA\n"+Casaempresaedificio);
        Referenciatxt.setText("REFERENCIA\n"+referencia);
        PortadaTxt.setText("PORTADA TARJETA (2 PALABRAS)\n"+Portada_tarjeta);
        TextoTarjetatxt.setText("TEXTO TARJETA (600 CARACTERES)\n"+Texto_tarjeta.replace("<br>",""));
        Especificaciontxt.setText("ESPECIFICACION\n" + Especificacion);
        Globotxt.setText("GLOBO\n"+ Globo);
        Sectortxt.setText("SECTOR\n"+sector);
        CostoEnviotxt.setText("COSTO DE ENVIO\n"+Costo_envio);
        Estadotxt.setText("ESTADO: "+Estado);

        Glide.with(imgar.getContext()).asBitmap().load("https://frutagolosa.com/FrutaGolosaApp/Administrador/images/" +idarrelgo.toLowerCase().replace(" ","")+"1.jpg").transition(BitmapTransitionOptions.withCrossFade(1000)).placeholder(R.drawable.frutagolosa).diskCacheStrategy(DiskCacheStrategy.ALL).skipMemoryCache(true).transform(new FitCenter()).apply(new RequestOptions()).into(imgar).waitForLayout();


        Glide.with(this).load(imgal).into(ImgArreglolisto);


        Glide.with(this).load(imgaent).into(ImgArregloEnt);

       // ImgArreglolisto.setRotation(ImgArreglolisto.getRotation()+90);
   //    ImgArregloEnt.setRotation(ImgArregloEnt.getRotation()+90);
        Button btnVerMOTO=findViewById(R.id.btnVerMOTO);
        Button btnVerEncuesta=findViewById(R.id.btnEncuesta);
        Button btnBorrarPedido=findViewById(R.id.btnBorrarPedido);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        final AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        btnVerEncuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                builder2.setTitle("Elegir Ciudad");
                builder2.setMessage("¿A que ciudad quiere calificar?");
                builder2.setPositiveButton("Quito", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Uri uri = Uri.parse("https://docs.google.com/forms/d/1SRfJgcegserO8Rx8glBPqQzYVQP8GlWkgfriGiFlKJA/edit?ts=5cc22222");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);


                    }
                });
                builder2.setNegativeButton("Guayaquil", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Uri uri = Uri.parse("https://goo.gl/forms/Bx0m4Sh4hQ5oewXi1");
                        Intent intent2 = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent2);


                    }
                });
                builder2.create();
                builder2.show();



            }
        });

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        btnBorrarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Eliminar Pedido");
                builder.setMessage("¿Esta seguro que desea eliminar su pedido?");
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
BorrarPedido();



                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.create();
                builder.show();
            }
        });

        final  String motorizado=getIntent().getStringExtra(PedidoPendienteActiviy.MotorizadoA);
        btnVerMOTO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

if(motorizado.equals("motorizado")){

    Toast.makeText(DetallePedidoPendiente.this, "No tiene motorizado todavia.", Toast.LENGTH_SHORT).show();

}else {

    Intent vermoto= new Intent(DetallePedidoPendiente.this,VerMotorizado.class);
    vermoto.putExtra(MotorizadoA,motorizado);

    startActivity(vermoto);



}

            }
        });

TextView ViewFabicado=findViewById(R.id.ViewFabticado);
        TextView ViewEntregado=findViewById(R.id.ViewEntregado);
        if(imgal.equals("https://frutagolosa.com/FrutaGolosaApp/imgapp/frutagolosa.png")){
            ViewFabicado.setVisibility(View.GONE);
            ImgArreglolisto.setVisibility(View.GONE);
        }

        if(imgaent.equals("https://frutagolosa.com/FrutaGolosaApp/imgapp/frutagolosa.png")){
            ViewEntregado.setVisibility(View.GONE);
            ImgArregloEnt.setVisibility(View.GONE);
        }

        if (Estado.equals("En Ruta")){

            ViewFabicado.setText("FABRICADO - hora: "+TiempoFab);
            if(imgal.equals("https://frutagolosa.com/FrutaGolosaApp/imgapp/frutagolosa.png")){
                ViewFabicado.setVisibility(View.GONE);
                ImgArreglolisto.setVisibility(View.GONE);
            }
            ViewEntregado.setVisibility(View.GONE);
            ImgArregloEnt.setVisibility(View.GONE);
            btnVerMOTO.setVisibility(View.VISIBLE);

        }
        if (Estado.equals("Por Confirmar")){
            ImgArreglolisto.setVisibility(View.GONE);
            ImgArregloEnt.setVisibility(View.GONE);
            ViewFabicado.setVisibility(View.GONE);
            ViewEntregado.setVisibility(View.GONE);


        }
        if (Estado.equals("En Espera")){
            ImgArreglolisto.setVisibility(View.GONE);
            ImgArregloEnt.setVisibility(View.GONE);
            ViewFabicado.setVisibility(View.GONE);
            ViewEntregado.setVisibility(View.GONE);

        }

        if (Estado.equals("Fabricado")){
            if(imgal.equals("https://frutagolosa.com/FrutaGolosaApp/imgapp/frutagolosa.png")){
                ViewFabicado.setVisibility(View.GONE);
                ImgArreglolisto.setVisibility(View.GONE);
            }
            ViewFabicado.setText("Foto de fabricado\n hora: "+TiempoFab);
            ImgArregloEnt.setVisibility(View.GONE);
            ViewEntregado.setVisibility(View.GONE);

        }

        if (Estado.equals("Entregado")){
            if(imgal.equals("https://frutagolosa.com/FrutaGolosaApp/imgapp/frutagolosa.png")){
                ViewFabicado.setVisibility(View.GONE);
                ImgArreglolisto.setVisibility(View.GONE);
            }
            if(imgaent.equals("https://frutagolosa.com/FrutaGolosaApp/imgapp/frutagolosa.png")){
                ImgArregloEnt.setVisibility(View.GONE);
                ViewEntregado.setVisibility(View.GONE);
            }

            ViewFabicado.setText("Foto de fabricado\n hora: "+TiempoFab);
            btnVerEncuesta.setVisibility(View.VISIBLE);
            ViewEncuesta.setVisibility(View.VISIBLE);
        }

        if (Estado.equals("Completado")){
            ImgArregloEnt.setVisibility(View.GONE);
            ViewEntregado.setVisibility(View.GONE);
            btnVerEncuesta.setVisibility(View.GONE);
            ViewEncuesta.setVisibility(View.GONE);
            ImgArreglolisto.setVisibility(View.GONE);
            ViewFabicado.setVisibility(View.GONE);
            btnBorrarPedido.setVisibility(View.VISIBLE);
        }


Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
finish();
            }
        },300000);



    }

    private void BorrarPedido()

    {
        final ProgressDialog loading = ProgressDialog.show(this, "Borrando...", "Espere por favor");
        String a="Borrado";
        final String b=getIntent().getStringExtra(PedidoPendienteActiviy.IdPEDIDOA);
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build();

        RegisterEstado api = adapter.create(RegisterEstado.class);


        api.inserBorrado(
                a,
                b,





                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {

                        BufferedReader reader = null;

                        String output = "";

                        try {
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));
                            output = reader.readLine();
                            loading.dismiss();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (output.equals("Se borro pedido exitosamente")) {
                            Toast.makeText(DetallePedidoPendiente.this, output, Toast.LENGTH_LONG).show();

                            Intent c= new Intent(DetallePedidoPendiente.this, PedidoPendienteActiviy.class);

                            startActivity(c);
                            loading.dismiss();
                            finish();



                        } else {


                            Toast.makeText(DetallePedidoPendiente.this, output, Toast.LENGTH_SHORT).show();

                        }


                    }

                    @Override
                    public void failure(RetrofitError error) {
                        loading.dismiss();
                        Toast.makeText(DetallePedidoPendiente.this, "No se borro el pedido, intente mas tarde", Toast.LENGTH_LONG).show();

                    }
                }
        );



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
        final String Estado=getIntent().getStringExtra(PedidoPendienteActiviy.EstadoA);
        final String idarrelgo=getIntent().getStringExtra(PedidoPendienteActiviy.IdArregloA);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.frutagolosa2)
                .setTicker("FrutaGolosa")
                //.setPriority(Notification.PRIORITY_MAX)
                .setContentTitle("Fruta Golosa App")
                .setContentText("Pedido: "+idarrelgo.toUpperCase()+" Estado: "+Estado.toUpperCase())
                .setContentInfo("Estado de su pedido")
                .setContentIntent(contentIntent)
        ;

        notificationManager.notify(1, notificationBuilder.build());
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
