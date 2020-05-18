package com.frutagolosa.fgapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Handler;
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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.frutagolosa.fgapp.api.ApiInterfaceVersion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CompArreglo extends AppCompatActivity {
  public static final String NombreArreglo="nombrearreglo" ;
  public static final String cantidadArreglos="cantidadArreglos" ;
  public static final String PrecioArreglo="precioarreglo" ;
  int x;
  CountDownTimer mcountdowntimer;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_comp_arreglo);

    final TextView cantidadtext = (TextView) (findViewById(R.id.cantidadTxt));


    final String IdArreglo = getIntent().getStringExtra(ConChocolateFragment.IdArreglo).replace(" ","").toLowerCase();

    final String IdArreglo2 = getIntent().getStringExtra(ConChocolateFragment.IdArreglo);
    final String precio = getIntent().getStringExtra(ConChocolateFragment.Precio).trim();
    final int id = getResources().getIdentifier(IdArreglo+1, "drawable", getPackageName());
    final ImageView AD = (ImageView) findViewById(R.id.ImgCompPrin);

    //----------------------------------------------------Accion de botones
    TextView na = (TextView) findViewById(R.id.NombArreglo);
    TextView na2 = (TextView) findViewById(R.id.NombArreglo2);
    TextView prec = (TextView) findViewById(R.id.PrecioArreglo);
    TextView prec2arr = (TextView) findViewById(R.id.ViewPrecio);

    // Cambios de imagen segun el arreglo---------------------------------


      Glide.with(this).load("https://frutagolosa.com/FrutaGolosaApp/Administrador/images/" +IdArreglo+"1.jpg").diskCacheStrategy(DiskCacheStrategy.ALL).into(AD);



    prec2arr.setText(precio+ " USD");


    //----------------------------------------------------------------------
    na.setText(IdArreglo);
    na2.setText(IdArreglo2);
    prec.setText(precio + " USD");







    mcountdowntimer = new CountDownTimer(5000, 1000) {


      @Override
      public void onTick(long millisUntilFinished) {

      }

      @Override
      public void onFinish() {
        start();

        llenarimagen();



      }

    }.start();




    Button flechaizquierda = (Button) (findViewById(R.id.flechaizq));
    Button flechaderecha = (Button) (findViewById(R.id.flechadec));
    final int[] cantidad = {1};

    flechaizquierda.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (cantidad[0] > 1) {
          cantidad[0] = cantidad[0] - 1;

          String a = "" + cantidad[0];
          cantidadtext.setText(a);
        }

      }
    }); //boton del clicklister

    flechaderecha.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        cantidad[0] = cantidad[0] + 1;
        String b = "" + cantidad[0];
        cantidadtext.setText(b);
      }
    }); //boton del clicklister

    //---------------Radio botones

    final Button LPRG = (Button) (findViewById(R.id.LimpiarRadio));
    LPRG.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {


        cantidad[0] = 1;
        String c = "" + cantidad[0];
        cantidadtext.setText(c);
      }
    }); //boton del clicklister







    setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


    //btn comprar----------------
    Button btncomprar = (Button) (findViewById(R.id.btnComp));
    final AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
    btncomprar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        SharedPreferences preferences=getSharedPreferences("login", Context.MODE_PRIVATE);
        String nombreus=preferences.getString("nombreus","Registrese");
        String mailus=preferences.getString("mailus","No");
        String telefonous=preferences.getString("telefonous","No");


        if(nombreus.equals("Registrese")) {
          Intent ra = new Intent(CompArreglo.this, Login_ValidActivity.class);

          startActivity(ra);

        }
        else {



          String tomastring = cantidadtext.toString();
          int cant = cantidad[0];
          if (!compruebaConexion(getApplicationContext())) {
            Toast.makeText(getBaseContext(), "Necesaria conexión a internet para comprar ", Toast.LENGTH_SHORT).show();
          } else {
            final ProgressDialog loading = ProgressDialog.show(CompArreglo.this, "Cargando...", "Espere por favor");



            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
              @Override
              public void run() {

                String version="1.8.2";

                RestAdapter adapter = new RestAdapter.Builder()
                        .setEndpoint("https://frutagolosa.com/FrutaGolosaApp/version.php?z="+version)
                        .build();

                ApiInterfaceVersion api = adapter.create(ApiInterfaceVersion.class);
                setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                String z=version;
                api.evaluaversion(
                        z,




                        new Callback<Response>() {
                          @Override
                          public void success(Response result, Response response) {
                    if(response.getBody()!=null) {
                      BufferedReader reader = null;
                      String output = "";

                      try {
                        reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                        output = reader.readLine();
                        if (output.equals("Actual")) {


                          Intent f = new Intent(CompArreglo.this, UbicacionEnvioActiviy.class);
                          f.putExtra(PrecioArreglo, precio);
                          f.putExtra(NombreArreglo, IdArreglo);
                          startActivity(f);
                          loading.dismiss();


                        } else {

                          builder2.setTitle("Actualice su app");
                          builder2.setMessage("Tenemos mejoras importantes en nuestra app, por favor actualiza para poder comprar y seguir disfrutando.");
                          builder2.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                              Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.frutagolosa.fgapp");
                              Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                              startActivity(intent);


                            }
                          });
                          builder2.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                            }
                          });
                          builder2.create();
                          builder2.show();


                          Toast.makeText(CompArreglo.this, output, Toast.LENGTH_SHORT).show();
                          loading.dismiss();

                        }

                      } catch (IOException e) {
                        e.printStackTrace();
                      }


                    }  }

                          @Override
                          public void failure(RetrofitError error) {
                            loading.dismiss();
                            Toast.makeText(CompArreglo.this, "Problemas de conexion, servidor o red, revise que este conectado a datos o tenga Wifi", Toast.LENGTH_LONG).show();


                          }
                        }
                );








              }
            },3000);





          }
        }

      }
    }); //boton del clicklister
    //---------------Radio botones
//--------------------------------------------------------------------

    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }


  }



  private void llenarimagen() {
    final String IdArreglo = getIntent().getStringExtra(ConChocolateFragment.IdArreglo).toLowerCase();
    //final int id = getResources().getIdentifier(IdArreglo+1, "drawable", getPackageName());
   // final int id2 = getResources().getIdentifier(IdArreglo+2, "drawable", getPackageName());
    //int id3=getResources().getIdentifier("backgroundcomp","drawable",getPackageName());
    final ImageView AD = (ImageView) findViewById(R.id.ImgCompPrin);


    if(x==1){Glide.with(getApplicationContext()).asBitmap().load("https://frutagolosa.com/FrutaGolosaApp/Administrador/images/" +IdArreglo+"1.jpg").diskCacheStrategy(DiskCacheStrategy.ALL).transition(BitmapTransitionOptions.withCrossFade(1000)).into(AD);}
    if(x==2){Glide.with(getApplicationContext()).asBitmap().load("https://frutagolosa.com/FrutaGolosaApp/Administrador/images/" +IdArreglo+"2.jpg").diskCacheStrategy(DiskCacheStrategy.ALL).transition(BitmapTransitionOptions.withCrossFade(1000)).into(AD);}
    if(x==3){x=1;}
    x=x+1;

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
