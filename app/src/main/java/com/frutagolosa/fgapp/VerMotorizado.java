package com.frutagolosa.fgapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.frutagolosa.fgapp.adapter.RecyclerAdapter2;
import com.frutagolosa.fgapp.api.ApiClient;
import com.frutagolosa.fgapp.api.ApiInterfaceMoto;
import com.frutagolosa.fgapp.model.Motorizado;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerMotorizado extends AppCompatActivity {
  private RecyclerView recyclerView;
  private RecyclerView.LayoutManager layoutManager;
  private RecyclerAdapter2 adapter;
  private List<Motorizado> contacts;
  private ApiInterfaceMoto apiInterface;
  public static String MOTORIZADOTELFONO="asd";
  public static String COORDENADAS="kdasd";
  public static String IMAGENMOTO="lkflam";
  public static final String CiudadA="czxc";
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_ver_motorizado);
    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
    setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
    recyclerView = findViewById(R.id.recyclerViewM);
    layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setHasFixedSize(true);
    setTitle("Detalle Motorizado");
    apiInterface = ApiClient.getApiClient().create(ApiInterfaceMoto.class);
    final  String motorizado=getIntent().getStringExtra(DetallePedidoPendiente.MotorizadoA);
    Call<List<Motorizado>> call = apiInterface.getContacts("https://frutagolosa.com/FrutaGolosaApp/ListarMotorizado.php?a="+motorizado);

    call.enqueue(new Callback<List<Motorizado>>() {
      @Override
      public void onResponse(Call<List<Motorizado>> call, Response<List<Motorizado>> response) {
        if(response.body()!=null) {


          contacts = response.body();
          adapter = new RecyclerAdapter2(contacts);
          recyclerView.setAdapter(adapter);
         final String nm=contacts.get(0).getTelefono();


          adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


              final String coordenadas = contacts.get(recyclerView.getChildAdapterPosition(view)).getCoordenadas();
              final String telfmoto = contacts.get(recyclerView.getChildAdapterPosition(view)).getTelefono();
              final String imagenmoto = contacts.get(recyclerView.getChildAdapterPosition(view)).getFoto();
              final String coorde = contacts.get(recyclerView.getChildAdapterPosition(view)).getCoordenadas();
              if (coordenadas.substring(1).equals("G") || coordenadas.equals("no") || coordenadas.equals(" ")) {

                Toast.makeText(VerMotorizado.this, "Ubicacion no disponible actualmente", Toast.LENGTH_SHORT).show();
              } else {


                Intent b = new Intent(VerMotorizado.this, Maps4Activity.class);
                String Ciudad = "si";
                b.putExtra(MOTORIZADOTELFONO, telfmoto);
                b.putExtra(IMAGENMOTO, imagenmoto);
                b.putExtra(COORDENADAS, coorde);
                b.putExtra(CiudadA, Ciudad);
                startActivity(b);
                finish();


              }

            }
          });

          Button wsp=(Button)findViewById(R.id.btnwsp);
          wsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              String numberWithCountryCode="+593"+nm.substring(1,nm.length());
              String message="Buenas, me gustaria conocer la ubicacion de mi pedido";
              Uri uri = Uri.parse("https://api.whatsapp.com/send?phone=" + numberWithCountryCode + "&text=" + message);
              Intent sendIntent = new Intent(Intent.ACTION_VIEW, uri);
              sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
              getApplicationContext().startActivity(sendIntent);

            }
          });
        }



      }



      @Override
      public void onFailure(Call<List<Motorizado>> call, Throwable t) {

      }
    });

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
