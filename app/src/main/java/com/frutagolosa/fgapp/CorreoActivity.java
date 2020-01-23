package com.frutagolosa.fgapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.frutagolosa.fgapp.api.RegisterAPI2;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CorreoActivity extends AppCompatActivity {
  FirebaseAuth.AuthStateListener authStateListener;
  FirebaseAuth auth;

  public static final String ROOT_URL="https://frutagolosa.com/FrutaGolosaApp";
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_correo);
    auth = FirebaseAuth.getInstance();
    TextView tex=(TextView)findViewById(R.id.textviewcorreo);
    final EditText corre=(EditText)findViewById(R.id.GetCorreoTXT);
    Button btreic=(Button)findViewById(R.id.buttonhev);
    Button btnpedsms=(Button)findViewById(R.id.buttonpedsms);
    final Button Envicore=(Button)findViewById(R.id.EnviarCorreobtn);
    SharedPreferences preferences=getSharedPreferences("login", Context.MODE_PRIVATE);
    final String nombreus2=preferences.getString("nombreus2","Nope");
    final String mailus=preferences.getString("mailus","No");
    final String telefonous=preferences.getString("telefonous","No");
    final String verificado=preferences.getString("verificado","Nope");



    Envicore.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        EnviarCorreo();
        corre.setEnabled(false);
        Envicore.setVisibility(View.GONE);
      }
    });



    tex.setText("Hola "+ nombreus2+", por favor coloque de nuevo su correo debajo, u otro correo que quiera que le enviemos la confirmacion, pulse enviar correo, vaya a su bandeja de entrada y luego vuelva a la app y pulse he verificado mi correo" );

    btnpedsms.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        SharedPreferences preferences=getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("verificado","Nope");
        editor.commit();
        Intent f = new Intent(CorreoActivity.this,Login_ValidActivity.class);
        startActivity(f);
      }
    });

    btreic.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        try {
          FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
          FirebaseAuth.getInstance().signOut();
        }catch (Exception e){

        }
        Intent intent = getIntent();
        finish();
        startActivity(intent);
      }
    });

    auth.signInWithEmailAndPassword(mailus,"123456").addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
      @Override
      public void onComplete(@NonNull Task<AuthResult> task) {
        if(task.isSuccessful()){

          FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
          if(user.isEmailVerified()){

            Toast.makeText(CorreoActivity.this, "Se verifico su correo, se esta registrando su usuario.", Toast.LENGTH_SHORT).show();




            String telefono=telefonous;
            String nombre=nombreus2;
            String correo=mailus;

            RestAdapter adapter = new RestAdapter.Builder()
                    .setEndpoint(ROOT_URL)
                    .build();

            RegisterAPI2 api = adapter.create(RegisterAPI2.class);
            api.inserCliente(
                    telefono,
                    nombre,
                    correo,

                    new Callback<Response>() {
                      @Override
                      public void success(retrofit.client.Response result, Response response) {
                        BufferedReader reader = null;

                        String output = "";

                        try {
                          reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                          output = reader.readLine();
                        } catch (IOException e) {
                          e.printStackTrace();
                        }

                        Toast.makeText(CorreoActivity.this, output, Toast.LENGTH_LONG).show();
                        if(output.equals("No se registro")){
                          Toast.makeText(CorreoActivity.this, "Problemas con el servidor reinicie la app", Toast.LENGTH_SHORT).show();

                        }
                        else {
                          Intent f = new Intent(CorreoActivity.this, Main2Activity.class);
                          startActivity(f);
                          try {  SharedPreferences preferences=getSharedPreferences("login", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor=preferences.edit();
                            editor.putString("nombreus",nombreus2);
                            editor.putString("verificado","si");
                            editor.commit();

                          }catch (Exception e){

                          }
                          finish();
                        }

                      }

                      @Override
                      public void failure(RetrofitError error) {
                        Toast.makeText(CorreoActivity.this, error.toString(), Toast.LENGTH_LONG).show();

                      }
                    }
            );


          }
          else {
            Toast.makeText(CorreoActivity.this, "No se ha verifico su correo,revise su bandeja de entrada.", Toast.LENGTH_SHORT).show();

          }

        }
      }
    });




  }
  private void EnviarCorreo(){
    EditText corre=(EditText)findViewById(R.id.GetCorreoTXT);
    String Correo=corre.getText().toString().toLowerCase().trim();
    final String Password="123456";
    SharedPreferences preferences=getSharedPreferences("login", Context.MODE_PRIVATE);
    final String nombreus2=preferences.getString("nombreus2","Nope");
    final String mailus=preferences.getString("mailus","No");
    final String telefonous=preferences.getString("telefonous","No");
    final String verificado=preferences.getString("verificado","Nope");
    auth.createUserWithEmailAndPassword(Correo,Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
      @Override
      public void onComplete(@NonNull Task<AuthResult> task) {
        if(!task.isSuccessful()){


          Toast.makeText(CorreoActivity.this, "No se envio su correo", Toast.LENGTH_SHORT).show();
        }else{
          try {

            EditText corre=(EditText)findViewById(R.id.GetCorreoTXT);
            String Correo=corre.getText().toString().toLowerCase().trim();
            FirebaseUser user = auth.getCurrentUser();
            user.sendEmailVerification();
            SharedPreferences preferences=getSharedPreferences("login", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=preferences.edit();
            editor.putString("mailus",Correo);
            editor.putString("verificado","no");
            editor.commit();
            Toast.makeText(CorreoActivity.this, "Ya se envio un correo a su direccion mail.", Toast.LENGTH_SHORT).show();
          }catch (Exception e){

            Toast.makeText(CorreoActivity.this, "Ya se envio un correo a su direccion mail.", Toast.LENGTH_SHORT).show();

          }

        }

      }
    });


  }}