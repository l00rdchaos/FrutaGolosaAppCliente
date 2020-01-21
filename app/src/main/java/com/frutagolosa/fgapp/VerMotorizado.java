package com.frutagolosa.fgapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
    setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
        contacts = response.body();
        adapter = new RecyclerAdapter2(contacts);
        recyclerView.setAdapter(adapter);




        adapter.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {


            final String coordenadas=contacts.get(recyclerView.getChildAdapterPosition(view)).getCoordenadas();
            final String telfmoto=contacts.get(recyclerView.getChildAdapterPosition(view)).getTelefono();
            final String imagenmoto=contacts.get(recyclerView.getChildAdapterPosition(view)).getFoto();
            final String coorde=contacts.get(recyclerView.getChildAdapterPosition(view)).getCoordenadas();
            if(coordenadas.substring(1).equals("G")||coordenadas.equals("no")||coordenadas.equals(" ")){

              Toast.makeText(VerMotorizado.this, "Ubicacion no disponible actualmente", Toast.LENGTH_SHORT).show();
            }



            else {



              Intent b = new Intent(VerMotorizado.this, Maps4Activity.class);
              String Ciudad="si";
              b.putExtra(MOTORIZADOTELFONO,telfmoto);
              b.putExtra(IMAGENMOTO,imagenmoto);
              b.putExtra(COORDENADAS,coorde);
              b.putExtra(CiudadA,Ciudad);
              startActivity(b);
              finish();




            }

          }
        });

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
