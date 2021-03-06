package com.frutagolosa.fgapp;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import com.frutagolosa.fgapp.api.RegisterAPI2;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Login_ValidActivity extends AppCompatActivity {
  FirebaseAuth.AuthStateListener authStateListener;
  FirebaseAuth auth;
  PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
  String verification_code;
  public static final String ROOT_URL="https://frutagolosa.com/FrutaGolosaApp";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login__valid);
    FirebaseApp.initializeApp(this);


    TextView dig = (TextView) findViewById(R.id.txtdigitcode);
    EditText codee = (EditText) findViewById(R.id.txtcod);
    Button envcodcod = (Button) findViewById(R.id.btnEnviacod);
    TextView contt = (TextView) findViewById(R.id.conteotxt);
    Button btnpdcod = (Button) findViewById(R.id.btnpedcod);

    dig.setVisibility(View.INVISIBLE);
    codee.setVisibility(View.INVISIBLE);
    envcodcod.setVisibility(View.INVISIBLE);
    contt.setVisibility(View.INVISIBLE);
    btnpdcod.setVisibility(View.VISIBLE);

    auth = FirebaseAuth.getInstance();
    SharedPreferences preferences=getSharedPreferences("login", Context.MODE_PRIVATE);
    final String nombreus2=preferences.getString("nombreus2","Nope");
    String mailus=preferences.getString("mailus","No");
    String telefonous=preferences.getString("telefonous","No");
    String verificado=preferences.getString("verificado","Nope");

    if(verificado.equals("no")){


      Intent f = new Intent(Login_ValidActivity.this, CorreoActivity.class);
      startActivity(f);


    }

    final Button otrometdo = (Button) findViewById(R.id.btnotrometod);
    otrometdo.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        AnotherVerification();
      }
    });

    mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
      @Override
      public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

      }

      @Override
      public void onVerificationFailed(FirebaseException e) {
        Toast.makeText(getApplicationContext(), "Codigo no enviado, revise el numero por favor", Toast.LENGTH_SHORT).show();
        Button btnpdcod = (Button) findViewById(R.id.btnpedcod);
        btnpdcod.setEnabled(true);
      }

      @Override
      public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
        super.onCodeSent(s, forceResendingToken);
        verification_code = s;


        Toast.makeText(getApplicationContext(), "Codigo enviado a su numero", Toast.LENGTH_SHORT).show();
        TextView dig = (TextView) findViewById(R.id.txtdigitcode);
        Button envcodcod = (Button) findViewById(R.id.btnEnviacod);
        TextView txtnombreus = (TextView) findViewById(R.id.txtnombreus);
        TextView txtcorreous = (TextView) findViewById(R.id.txtcorreous);
        EditText codee = (EditText) findViewById(R.id.txtcod);
        EditText n= (EditText) findViewById(R.id.editTextName);
        EditText t= (EditText) findViewById(R.id.editTextMail);

        TextView contt = (TextView) findViewById(R.id.conteotxt);
        Button btnpdcod = (Button) findViewById(R.id.btnpedcod);
        dig.setVisibility(View.VISIBLE);
        codee.setVisibility(View.VISIBLE);
        envcodcod.setVisibility(View.VISIBLE);
        contt.setVisibility(View.VISIBLE);
        txtcorreous.setVisibility(View.GONE);
        txtnombreus.setVisibility(View.GONE);
        n.setVisibility(View.GONE);
        t.setVisibility(View.GONE);

        conteo();
      }
    };


  }


  public void send_sms(View v) {
    EditText n= (EditText) findViewById(R.id.editTextName);
    EditText t= (EditText) findViewById(R.id.editTextMail);
    EditText c= (EditText) findViewById(R.id.txtpohne);
    String telefono=t.getText().toString();
    String nombre=n.getText().toString();
    String correo=c.getText().toString();

    if(n.getText().toString().trim().isEmpty()||t.getText().toString().trim().isEmpty()||c.getText().toString().trim().isEmpty()){
      Toast.makeText(this, "Por favor rellene los campos en blanco", Toast.LENGTH_SHORT).show();
    }
    else {
      EditText phones = (EditText) findViewById(R.id.txtpohne);
      String number = phones.getText().toString().replaceFirst("0","+593").trim();
      PhoneAuthProvider.getInstance().verifyPhoneNumber(
              number, 60,
              TimeUnit.SECONDS,

              this, mCallback
      );



    }
  }

  public void singInWithPhone(PhoneAuthCredential credential) {
    auth.signInWithCredential(credential)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                  savepreferences();
                  Toast.makeText(getApplicationContext(), "Usuario registrado con exito", Toast.LENGTH_SHORT).show();
                  EditText n= (EditText) findViewById(R.id.editTextName);
                  EditText t= (EditText) findViewById(R.id.txtpohne);
                  final EditText c= (EditText) findViewById(R.id.editTextMail);
                  String telefono=t.getText().toString().trim().replace(" ","");
                  String nombre=n.getText().toString().trim();
                  String correo=c.getText().toString().trim().replace(" ","").toLowerCase();

                  RestAdapter adapter = new RestAdapter.Builder()
                          .setEndpoint(ROOT_URL)
                          .build();

                  RegisterAPI2 api = adapter.create(RegisterAPI2.class);
                  api.inserCliente(
                          telefono,
                          nombre,
                          correo,

                          new Callback<retrofit.client.Response>() {
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

                              Toast.makeText(Login_ValidActivity.this, output, Toast.LENGTH_LONG).show();
                              if(output.equals("No se registro")){
                                Toast.makeText(Login_ValidActivity.this, "Problemas con el servidor", Toast.LENGTH_SHORT).show();
                                c.setVisibility(View.VISIBLE);
                                Button envcodcod = (Button) findViewById(R.id.btnEnviacod);
                                envcodcod.setEnabled(true);
                              }
                              else {
                                Intent f = new Intent(Login_ValidActivity.this, Inicio.class);
                                notificacion();
                                startActivity(f);
                                try {
                                  FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                  user.delete();
                                }catch (Exception e){

                                }
                                finish();
                              }

                            }

                            @Override
                            public void failure(RetrofitError error) {
                              Toast.makeText(Login_ValidActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                              c.setVisibility(View.VISIBLE);
                              Button envcodcod = (Button) findViewById(R.id.btnEnviacod);
                              envcodcod.setEnabled(true);
                            }
                          }
                  );


                }

                else {  Toast.makeText(getApplicationContext(), "Codigo erroneo", Toast.LENGTH_SHORT).show();
                  Button envcodcod = (Button) findViewById(R.id.btnEnviacod);
                  envcodcod.setEnabled(true);
                }


              }
            });


  }

  public void verify(View v) {
    Button envcodcod = (Button) findViewById(R.id.btnEnviacod);
    envcodcod.setEnabled(false);
    EditText cod = (EditText) findViewById(R.id.txtcod);
    String input_code = cod.getText().toString();
    if (input_code.equals("")) {

      Toast.makeText(getApplicationContext(), "Codigo en blanco", Toast.LENGTH_SHORT).show();
      envcodcod.setEnabled(true);
    }
    else {
      verifyPhoneNumber(verification_code, input_code);



    }
  }

  public void verifyPhoneNumber(String verifyCode, String input_code) {

    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verifyCode, input_code);
    singInWithPhone(credential);

  }





  public void conteo() {

    final Button otrometdo = (Button) findViewById(R.id.btnotrometod);
    final EditText n= (EditText) findViewById(R.id.editTextName);
    final EditText t= (EditText) findViewById(R.id.editTextMail);
    final EditText c= (EditText) findViewById(R.id.txtpohne);
    final TextView contto = (TextView) findViewById(R.id.conteotxt);
    final Button btnpdcodo = (Button) findViewById(R.id.btnpedcod);
    btnpdcodo.setEnabled(false);
    final TextView tt = (TextView) findViewById(R.id.textView47);
    String a=c.getText().toString();
    tt.setText("El numero ingresado fue "+a);
    c.setVisibility(View.GONE);
    final TextView txtnombreus = (TextView) findViewById(R.id.txtnombreus);
    final TextView txtcorreous = (TextView) findViewById(R.id.txtcorreous);

    otrometdo.setVisibility(View.VISIBLE);
    final CountDownTimer mcountdowntimer = new CountDownTimer(90000, 1000) {
      @Override
      public void onTick(long millisUntilFinished) {
        EditText cod = (EditText) findViewById(R.id.txtcod);
        btnpdcodo.setText("Solicitar en: " + (millisUntilFinished /  1000));
        Button otromet=(Button)findViewById(R.id.btnotrometod);
        TextView dig = (TextView) findViewById(R.id.txtdigitcode);
        Button envcodcod = (Button) findViewById(R.id.btnEnviacod);
        dig.setVisibility(View.VISIBLE);
        envcodcod.setVisibility(View.VISIBLE);
        cod.setVisibility(View.VISIBLE);
        contto.setVisibility(View.VISIBLE);
        contto.setVisibility(View.VISIBLE);
        txtcorreous.setVisibility(View.GONE);
        txtnombreus.setVisibility(View.GONE);
      }

      @Override
      public void onFinish() {
        AnotherVerification();
        c.setVisibility(View.VISIBLE);
        Button otromet=(Button)findViewById(R.id.btnotrometod);
        otromet.setVisibility(View.VISIBLE);
        btnpdcodo.setEnabled(true);
        btnpdcodo.setText("Solicitar Otro Codigo");
        btnpdcodo.setVisibility(View.VISIBLE);
        contto.setText("");
        TextView dig = (TextView) findViewById(R.id.txtdigitcode);
        Button envcodcod = (Button) findViewById(R.id.btnEnviacod);
        EditText cod = (EditText) findViewById(R.id.txtcod);
        envcodcod.setVisibility(View.GONE);
        cod.setVisibility(View.GONE);
        n.setVisibility(View.VISIBLE);
        t.setVisibility(View.VISIBLE);
        c.setVisibility(View.VISIBLE);
        EditText phones = (EditText) findViewById(R.id.txtpohne);
        phones.setFocusable(true);
        txtcorreous.setVisibility(View.VISIBLE);
        txtnombreus.setVisibility(View.VISIBLE);
        try {
          FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
          user.delete();
          FirebaseAuth.getInstance().signOut();
        }catch (Exception e){

        }


      }
    }.start();

  }

  private void savepreferences(){
    EditText n= (EditText) findViewById(R.id.editTextName);
    EditText t= (EditText) findViewById(R.id.editTextMail);
    EditText c= (EditText) findViewById(R.id.txtpohne);
    SharedPreferences preferences=getSharedPreferences("login", Context.MODE_PRIVATE);
    String nombre=n.getText().toString();
    String mail=t.getText().toString().trim().replace(" ","").toLowerCase();
    String telefono=c.getText().toString().trim().replace(" ","");

    SharedPreferences.Editor editor=preferences.edit();
    editor.putString("nombreus",nombre);
    editor.putString("mailus",mail);
    editor.putString("telefonous",telefono);
    editor.putString("verificado","si");
    editor.commit();

  }

  private void savepreferences2(){
    EditText n= (EditText) findViewById(R.id.editTextName);
    EditText t= (EditText) findViewById(R.id.editTextMail);
    EditText c= (EditText) findViewById(R.id.txtpohne);
    SharedPreferences preferences=getSharedPreferences("login", Context.MODE_PRIVATE);
    String nombre="Registrese";
    String nombre2= n.getText().toString();
    String mail=t.getText().toString().trim().replace(" ","").toLowerCase();
    String telefono=c.getText().toString().trim().replace(" ","");

    SharedPreferences.Editor editor=preferences.edit();
    editor.putString("nombreus",nombre);
    editor.putString("nombreus2",nombre2);
    editor.putString("mailus",mail);
    editor.putString("telefonous",telefono);
    editor.putString("verificado","no");
    editor.commit();

  }



  public boolean onOptionsItemSelected(MenuItem item){
    switch (item.getItemId()) {
      case android.R.id.home:
        finish();
        return true;
    }
    return super.onOptionsItemSelected(item);
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
    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
    notificationBuilder.setAutoCancel(true)
            .setDefaults(Notification.DEFAULT_ALL)
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.mipmap.frutagolosa2)
            .setTicker("FrutaGolosa")
            //.setPriority(Notification.PRIORITY_MAX)
            .setContentTitle("Fruta Golosa App")
            .setContentText("Registro exitoso")
            .setContentInfo("Ya puede acceder a nuestros golosos productos");
    notificationManager.notify(1, notificationBuilder.build());
  }

  private void AnotherVerification(){

    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("No recibio su codigo?");
    builder.setMessage("Si no pudo recibir la verificacion de su codigo, puede probar otro metodo de verificacion, como" +
            "el envio de correo electronico, o solicitar que se le agregue un codigo de verificacion manualmente," +
            "escribiendonos a nuestro whatsapp. Seleccione una de las dos opciones debajo.");
    builder.setPositiveButton("Correo", new DialogInterface.OnClickListener() {
      @Override

      public void onClick(DialogInterface dialog, int which) {
        savepreferences2();
        EnviarCorreo();


      }
    });

    builder.setNegativeButton("SMS", new DialogInterface.OnClickListener() {
      @Override

      public void onClick(DialogInterface dialog, int which) {



      }
    });


    builder.create();
    builder.show();


  }

  private void EnviarCorreo(){
    FirebaseUser user = auth.getCurrentUser();
    FirebaseAuth.getInstance().signOut();
    Intent f = new Intent(Login_ValidActivity.this, CorreoActivity.class);
    startActivity(f);
    finish();


  }


}