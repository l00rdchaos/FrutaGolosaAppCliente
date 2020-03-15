package com.frutagolosa.fgapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.frutagolosa.fgapp.api.ApiInterface4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,ConChocolateFragment.OnFragmentInteractionListener,SinChocolateFragment.OnFragmentInteractionListener,FrutasFloresFragm.OnFragmentInteractionListener, BlankFragmenta.OnFragmentInteractionListener, FestivosFragment.OnFragmentInteractionListener,CajasConFrutasFragment.OnFragmentInteractionListener {

  public static final String ska="codigoarreglo" ;
  public static final String ska2="codigoarreglo" ;
  CountDownTimer mcountdowntimer;
  ViewPager viewPager;
  int n=1;
  int descuento;

  @Override
  protected void onCreate(Bundle savedInstanceState) {


    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main2);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    viewPager= findViewById(R.id.imageView4);

    final ImageAdapter adapter= new ImageAdapter(this);
    viewPager.setAdapter(adapter);


    SharedPreferences preferences=getSharedPreferences("login", Context.MODE_PRIVATE);
    final String nombreus=preferences.getString("nombreus","Registrese");
    String mailus=preferences.getString("mailus","No2");
    String telefonous=preferences.getString("telefonous","No");

    chargepreferences();
    prueba();

    Button btnConCho = (Button) findViewById(R.id.btnConCHoc);
    Button btnFF = (Button) findViewById(R.id.btnFrutaFlo);
    Button btnSC = (Button) findViewById(R.id.btnSINchoc);
    Button btnNC=(Button) findViewById(R.id.btnNcontacto);
    Button btnCF = (Button) findViewById(R.id.btnCajasConF);
    Button btnAF=(Button) findViewById(R.id.btnAFestivos);
      Button btnCCF=(Button) findViewById(R.id.btnCajasConF);

    setTitle("Fruta Golosa");

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();
    setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
    navigationView.setItemIconTintList(null); // <----- HERE


    View frg1= findViewById(R.id.fragmentar);
    frg1.setVisibility(View.VISIBLE);
    View frg= findViewById(R.id.fragmentar2);
    frg.setVisibility(View.GONE);
    View frg2= findViewById(R.id.fragmentar3);
    frg2.setVisibility(View.GONE);
    View frg4= findViewById(R.id.fragmentar4);
    frg4.setVisibility(View.GONE);
    View frg5= findViewById(R.id.fragmentar5);
    frg5.setVisibility(View.GONE);
    View frg6= findViewById(R.id.fragmentar6);
    frg6.setVisibility(View.GONE);

    //////////////////////////////////////////////////////////////////////
    btnAF.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        View frg= findViewById(R.id.fragmentar);
        frg.setVisibility(View.GONE);
        View frg2= findViewById(R.id.fragmentar2);
        frg2.setVisibility(View.GONE);
        View frg3= findViewById(R.id.fragmentar3);
        frg3.setVisibility(View.GONE);
        View frg4= findViewById(R.id.fragmentar4);
        frg4.setVisibility(View.GONE);
        View frg5= findViewById(R.id.fragmentar5);
        frg5.setVisibility(View.VISIBLE);
        View frg6= findViewById(R.id.fragmentar6);
        frg6.setVisibility(View.GONE);
        
      }
    });

    btnCF.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          View frg= findViewById(R.id.fragmentar);
          frg.setVisibility(View.GONE);
          View frg2= findViewById(R.id.fragmentar2);
          frg2.setVisibility(View.GONE);
          View frg3= findViewById(R.id.fragmentar3);
          frg3.setVisibility(View.GONE);
          View frg4= findViewById(R.id.fragmentar4);
          frg4.setVisibility(View.GONE);
          View frg5= findViewById(R.id.fragmentar5);
          frg5.setVisibility(View.GONE);
          View frg6= findViewById(R.id.fragmentar6);
          frg6.setVisibility(View.VISIBLE);
      }
    });


    Button pendbtn= (Button) findViewById(R.id.PedidopendienteBTN);
    pendbtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {


        Intent e = new Intent(Main2Activity.this, PedidoPendienteActiviy.class);


        startActivity(e);

      }
    }); //boton del clicklister

    mcountdowntimer = new CountDownTimer(10000, 1000) {


      @Override
      public void onTick(long millisUntilFinished) {

      }

      @Override
      public void onFinish() {
        start();

        int a=viewPager.getCurrentItem();
        viewPager.setCurrentItem(n);
        n=n+1;

        if (n==8){n=0;}

      }

    }.start();

/////////////////////////////////////////////////////////////////////////////

    btnConCho.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        final ProgressDialog loading = ProgressDialog.show(Main2Activity.this, "Cargando...", "Espere por favor");
        loading.show();
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
          @Override
          public void run() {

            View frg1= findViewById(R.id.fragmentar);
            frg1.setVisibility(View.VISIBLE);
            View frg= findViewById(R.id.fragmentar2);
            frg.setVisibility(View.GONE);
            View frg2= findViewById(R.id.fragmentar3);
            frg2.setVisibility(View.GONE);
            View frg3= findViewById(R.id.fragmentar3);
            frg3.setVisibility(View.GONE);
            View frg4= findViewById(R.id.fragmentar4);
            frg4.setVisibility(View.GONE);
            View frg5= findViewById(R.id.fragmentar5);
            frg5.setVisibility(View.GONE);


            loading.dismiss();

          }
        },1000);
        loading.show();

      }
    });


    btnSC.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        View frg1= findViewById(R.id.fragmentar);
        frg1.setVisibility(View.GONE);
        View frg2= findViewById(R.id.fragmentar2);
        frg2.setVisibility(View.VISIBLE);
        View frg3= findViewById(R.id.fragmentar3);
        frg3.setVisibility(View.GONE);
        View frg4= findViewById(R.id.fragmentar4);
        frg4.setVisibility(View.GONE);
        View frg5= findViewById(R.id.fragmentar5);
        frg5.setVisibility(View.GONE);
        View frg6= findViewById(R.id.fragmentar6);
        frg6.setVisibility(View.GONE);

      }
    });

    btnFF.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        View frg= findViewById(R.id.fragmentar);
        frg.setVisibility(View.GONE);
        View frg2= findViewById(R.id.fragmentar2);
        frg2.setVisibility(View.GONE);
        View frg3= findViewById(R.id.fragmentar3);
        frg3.setVisibility(View.VISIBLE);
        View frg4= findViewById(R.id.fragmentar4);
        frg4.setVisibility(View.GONE);
        View frg5= findViewById(R.id.fragmentar5);
        frg5.setVisibility(View.GONE);
        View frg6= findViewById(R.id.fragmentar6);
        frg6.setVisibility(View.GONE);
      }
    });

    btnNC.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        View frg= findViewById(R.id.fragmentar);
        frg.setVisibility(View.GONE);
        View frg2= findViewById(R.id.fragmentar2);
        frg2.setVisibility(View.GONE);
        View frg3= findViewById(R.id.fragmentar3);
        frg3.setVisibility(View.GONE);
        View frg4= findViewById(R.id.fragmentar4);
        frg4.setVisibility(View.VISIBLE);
        View frg5= findViewById(R.id.fragmentar5);
        frg5.setVisibility(View.GONE);
        View frg6= findViewById(R.id.fragmentar6);
        frg6.setVisibility(View.GONE);
      }
    });

    final AlertDialog.Builder builder = new AlertDialog.Builder(this);









  }

  private void chargepreferences() {
    TextView txtUser= findViewById(R.id.UsuarioTxt);
    SharedPreferences preferences=getSharedPreferences("login", Context.MODE_PRIVATE);
    String nombreus=preferences.getString("nombreus","Registrese");
    String mailus=preferences.getString("mailus","No");
    String telefonous=preferences.getString("telefonous","No");
    txtUser.setText("Bienvenido "+ nombreus+",ten un goloso dia.");
    if(nombreus.equals("Registrese")) {
      txtUser.setText("Bienvenido, ten un goloso dia");

    }

  }

  @Override
  public void onBackPressed() {
    this.moveTaskToBack(true);

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main2, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();





    //noinspection SimplifiableIfStatement
    if (id == R.id.action_add) {
      String numberWithCountryCode="+593984727881";
      String message="Hola, necesito informacion.";
      Uri uri = Uri.parse("https://api.whatsapp.com/send?phone=" + numberWithCountryCode + "&text=" + message);
      Intent sendIntent = new Intent(Intent.ACTION_VIEW, uri);
      sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      getApplicationContext().startActivity(sendIntent);
    }
    if (id == R.id.insta) {


      Uri uri = Uri.parse("https://www.instagram.com/frutagolosa/");
      Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
      likeIng.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      likeIng.setPackage("com.instagram.android");

      try {
        startActivity(likeIng);
      } catch (ActivityNotFoundException e) {
        Toast.makeText(getApplicationContext(), "Instagram no se encuentra instalada", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Intent.ACTION_VIEW,

                Uri.parse("https://www.instagram.com/frutagolosa/")));
      }


      return true;
    }


    if (id == R.id.facebo) {





      String facebookId = "fb://page/293037744044159";
      String urlPage = "http://www.facebook.com/frutagolosa";

      try {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebookId )));
      } catch (Exception e) {
        Toast.makeText(getApplicationContext(), "Facebook no se encuentra instalada", Toast.LENGTH_SHORT).show();
        //Abre url de pagina.
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlPage)).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
      }


      return true;
    }


    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();


    Fragment Ifragment=null;
    boolean fragmentsele=false;

    if (id == R.id.nav_Choco) {
      final ProgressDialog loading = ProgressDialog.show(Main2Activity.this, "Cargando...", "Espere por favor");

      Handler handler=new Handler();
      handler.postDelayed(new Runnable() {
        @Override
        public void run() {

          View frg1= findViewById(R.id.fragmentar);
          frg1.setVisibility(View.VISIBLE);
          View frg= findViewById(R.id.fragmentar2);
          frg.setVisibility(View.GONE);
          View frg2= findViewById(R.id.fragmentar3);
          frg2.setVisibility(View.GONE);
          View frg4= findViewById(R.id.fragmentar4);
          frg4.setVisibility(View.GONE);
          View frg5= findViewById(R.id.fragmentar5);
          frg5.setVisibility(View.GONE);

          loading.dismiss();
        }
      },1000);


    }

    else if (id == R.id.nav_SinChoco) {

      View frg= findViewById(R.id.fragmentar);
      frg.setVisibility(View.GONE);
      View frg2= findViewById(R.id.fragmentar2);
      frg2.setVisibility(View.VISIBLE);
      View frg3= findViewById(R.id.fragmentar3);
      frg3.setVisibility(View.GONE);
      View frg4= findViewById(R.id.fragmentar4);
      frg4.setVisibility(View.GONE);
      View frg5= findViewById(R.id.fragmentar5);
      frg5.setVisibility(View.GONE);
    }

    else if (id == R.id.nav_FrutaFa) {

      View frg= findViewById(R.id.fragmentar);
      frg.setVisibility(View.GONE);
      View frg2= findViewById(R.id.fragmentar2);
      frg2.setVisibility(View.GONE);
      View frg3= findViewById(R.id.fragmentar3);
      frg3.setVisibility(View.VISIBLE);
      View frg4= findViewById(R.id.fragmentar4);
      frg4.setVisibility(View.GONE);
      View frg5= findViewById(R.id.fragmentar5);
      frg5.setVisibility(View.GONE);
    }

    else if (id == R.id.nav_Contacto) {

      View frg= findViewById(R.id.fragmentar);
      frg.setVisibility(View.GONE);
      View frg2= findViewById(R.id.fragmentar2);
      frg2.setVisibility(View.GONE);
      View frg3= findViewById(R.id.fragmentar3);
      frg3.setVisibility(View.GONE);
      View frg4= findViewById(R.id.fragmentar4);
      frg4.setVisibility(View.VISIBLE);
      View frg5= findViewById(R.id.fragmentar5);
      frg5.setVisibility(View.GONE);
    }


    else if (id == R.id.nav_Politicas) {

      String url = "https://frutagolosa.com/FrutaGolosaApp/politicas/politicasdeprivacidadapp.html";
      Intent i = new Intent(Intent.ACTION_VIEW);
      i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      i.setData(Uri.parse(url));
      startActivity(i);
    }

    else if (id == R.id.nav_Siga) {

      Intent e = new Intent(Main2Activity.this, PedidoPendienteActiviy.class);
      
      startActivity(e);
    }



    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);




    return true;
  }

  @Override
  public void onFragmentInteraction(Uri uri) {

  }



  private void notificacion10() {
    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    String NOTIFICATION_CHANNEL_ID = "frutagolosa_32";
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
    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
    notificationBuilder.setAutoCancel(true)
            .setDefaults(Notification.DEFAULT_ALL)
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.mipmap.frutagolosa2)
            .setTicker("FrutaGolosa")
            //.setPriority(Notification.PRIORITY_MAX)
            .setContentTitle("Fruta Golosa App")
            .setContentText("10% DE DESCUENTO EN SU COMPRA")
            .setContentInfo("Ya puede acceder a nuestros golosos productos");
    notificationManager.notify(1, notificationBuilder.build());
  }

  private void notificacion20() {
    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    String NOTIFICATION_CHANNEL_ID = "frutagolosa_32";
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
    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
    notificationBuilder.setAutoCancel(true)
            .setDefaults(Notification.DEFAULT_ALL)
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.mipmap.frutagolosa2)
            .setTicker("FrutaGolosa")
            //.setPriority(Notification.PRIORITY_MAX)
            .setContentTitle("Fruta Golosa App")
            .setContentText("20% DE DESCUENTO EN SU COMPRA")
            .setContentInfo("Ya puede acceder a nuestros golosos productos");
    notificationManager.notify(1, notificationBuilder.build());
  }


  private void notificacion30() {
    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    String NOTIFICATION_CHANNEL_ID = "frutagolosa_32";
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
    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
    notificationBuilder.setAutoCancel(true)
            .setDefaults(Notification.DEFAULT_ALL)
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.mipmap.frutagolosa2)
            .setTicker("FrutaGolosa")
            //.setPriority(Notification.PRIORITY_MAX)
            .setContentTitle("Fruta Golosa App")
            .setContentText("30% DE DESCUENTO EN SU COMPRA")
            .setContentInfo("Ya puede acceder a nuestros golosos productos");
    notificationManager.notify(1, notificationBuilder.build());
  }

  private void notificacion50() {
    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    String NOTIFICATION_CHANNEL_ID = "frutagolosa_32";
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
    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
    notificationBuilder.setAutoCancel(true)
            .setDefaults(Notification.DEFAULT_ALL)
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.mipmap.frutagolosa2)
            .setTicker("FrutaGolosa")
            //.setPriority(Notification.PRIORITY_MAX)
            .setContentTitle("Fruta Golosa App")
            .setContentText("50% DE DESCUENTO EN SU COMPRA")
            .setContentInfo("Ya puede acceder a nuestros golosos productos");
    notificationManager.notify(1, notificationBuilder.build());
  }

  public void prueba(){

    SharedPreferences preferences=getSharedPreferences("login", Context.MODE_PRIVATE);
    final String nombreus=preferences.getString("nombreus","Registrese");
    String c=preferences.getString("mailus","No");
    String t=preferences.getString("telefonous","No");

    if(nombreus.equals("Registrese")) {


    }
    else {
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


                  try {
                    reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                    if (reader.readLine() != null) {
                      output = reader.readLine();


                      if (output.equals("1")) {

                        notificacion10();
                        TextView txtUser = findViewById(R.id.UsuarioTxt);
                        txtUser.setText("Bienvenido " + nombreus + ",tienes 10% de descuento en tu compra.");

                      }


                      if (output.equals("2")) {

                        TextView txtUser = findViewById(R.id.UsuarioTxt);
                        txtUser.setText("Bienvenido " + nombreus + ", tienes 20% de descuento en tu compra");
                        notificacion20();


                      }

                      if (output.equals("3")) {

                        TextView txtUser = findViewById(R.id.UsuarioTxt);
                        txtUser.setText("Bienvenido " + nombreus + ", tienes 30% de descuento en tu compra");
                        notificacion30();


                      }

                      if (output.equals("4")) {
                        notificacion50();
                        TextView txtUser = findViewById(R.id.UsuarioTxt);
                        txtUser.setText("Bienvenido " + nombreus + ", tienes 50% de descuento en tu compra.");


                      }

                    }  } catch(IOException e){
                      e.printStackTrace();
                    }

                  }

                @Override
                public void failure(RetrofitError error) {

                  Toast.makeText(Main2Activity.this, "No se puede revisar promociones, revise su internet", Toast.LENGTH_LONG).show();

                }
              }

      );


    }


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
