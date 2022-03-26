package com.frutagolosa.fgapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.frutagolosa.fgapp.api.ApiInterfaceVersion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Inicio extends AppCompatActivity {
  private ApiInterfaceVersion apiInterface;
  String version="2.0.4";
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_inicio);
    final Button btnreiniciarapp=findViewById(R.id.btnReiniciarApp);
    final Button btncerrarapp=findViewById(R.id.btnCerrarApp);
    final Button btnActualizarApp=findViewById(R.id.btnActualizarApp);
    final Button btncnt=findViewById(R.id.buttonctn);






    SharedPreferences preferencesx=getSharedPreferences("birthdate", Context.MODE_PRIVATE);
    String vd=preferencesx.getString("vd","0");
    int i= Integer.parseInt(vd);
    if(i<=3){

       i=i+1;
      SharedPreferences preferences = getSharedPreferences("birthdate", Context.MODE_PRIVATE);
      SharedPreferences.Editor editor = preferences.edit();
      String j=String.valueOf(i);
      editor.putString("vd", j);
      editor.commit();
      final VideoView videoview = (VideoView) findViewById(R.id.videoView);
      Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.videofg);
      videoview.setVideoURI(uri);
      videoview.start();

      videoview.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          if(videoview.isPlaying()){
            videoview.pause();

          }else{

            videoview.start();
          }


        }
      });

      btncnt.setVisibility(View.VISIBLE);
      btncnt.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

          cargar();

        }
      });

    }else {
      final VideoView videoview = (VideoView) findViewById(R.id.videoView);
      videoview.setVisibility(View.GONE);
      TextView tt=(TextView)findViewById(R.id.textView49);
      tt.setVisibility(View.GONE);
      cargar();
    }





  }


private void cargar(){


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


      SharedPreferences preferences=getSharedPreferences("login", Context.MODE_PRIVATE);
      String nombreus=preferences.getString("nombreus","Registrese");
      String mailus=preferences.getString("mailus","No");
      String telefonous=preferences.getString("telefonous","No");


      if(nombreus.equals("Registrese")) {



        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("https://frutagolosa.com/FrutaGolosaApp/DayCode.php")
                .build();

        ApiInterfaceVersion api = adapter.create(ApiInterfaceVersion.class);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
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
                        SharedPreferences preferences=getSharedPreferences("code", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=preferences.edit();
                        editor.putString("codee",output);
                        editor.commit();
                        Intent d = new Intent(Inicio.this, Main2Activity.class);
                        startActivity(d);
                        finish();


                      } catch (IOException e) {
                        e.printStackTrace();
                      }


                    }  }

                  @Override
                  public void failure(RetrofitError error) {
                    asycdialog.dismiss();
                    Toast.makeText(Inicio.this, "Problemas de conexion, servidor o red, revise que este conectado a datos o tenga Wifi", Toast.LENGTH_LONG).show();

                    final Button btnreiniciarapp=findViewById(R.id.btnReiniciarApp);
                    final Button btncerrarapp=findViewById(R.id.btnCerrarApp);
                    final Button btnActualizarApp=findViewById(R.id.btnActualizarApp);
                    btnreiniciarapp.setVisibility(View.VISIBLE);
                    btncerrarapp.setVisibility(View.VISIBLE);
                  }
                }
        );


        asycdialog.dismiss();


      }
      else{

        SharedPreferences preferencesx=getSharedPreferences("birthdate", Context.MODE_PRIVATE);
        String bd=preferencesx.getString("bdate","no");


        if(bd.equals("no")){

          Intent d = new Intent(Inicio.this,birthdayactivity.class);
          startActivity(d);

          finish();
          asycdialog.dismiss();

        }else{

          Intent d = new Intent(Inicio.this, Main2Activity.class);
          startActivity(d);

          finish();

          asycdialog.dismiss();
        }


        asycdialog.dismiss();}
    }
  },3000);

  final Button btnreiniciarapp=findViewById(R.id.btnReiniciarApp);
  final Button btncerrarapp=findViewById(R.id.btnCerrarApp);
  final Button btnActualizarApp=findViewById(R.id.btnActualizarApp);
  final Button btncnt=findViewById(R.id.buttonctn);
  final VideoView videoview = (VideoView) findViewById(R.id.videoView);
  btncnt.setVisibility(View.INVISIBLE);
  videoview.setVisibility(View.GONE);
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
