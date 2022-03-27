package com.frutagolosa.fgapp;


import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.CountDownTimer;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.frutagolosa.fgapp.api.ApiInterfaceCoord;
import com.frutagolosa.fgapp.model.Pedido;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Maps4Activity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    protected static final int REQUEST_CHECK_SETTINGS = 0x1;
    private int per=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps4);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        SupportMapFragment mapFragment = (SupportMapFragment)  getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Button envdc= (Button) findViewById(R.id.btnenviadicax);

       final Spinner Ubicaciones;

        Ubicaciones= (Spinner) findViewById(R.id.SpUbicaciones);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final Pedido pedido = (Pedido) getIntent().getSerializableExtra("PEDIDO");


        try {


            if(pedido.getCiudad().equals("QUITO")){
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Ubicaciones, android.R.layout.simple_spinner_item);
                Ubicaciones.setAdapter(adapter);
            }else {   if(pedido.getCiudad().equals("GUAYAQUIL")){
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.UbicacionesG, android.R.layout.simple_spinner_item);
                Ubicaciones.setAdapter(adapter);}else {
                TextView prec2 = (TextView) findViewById(R.id.txtprecDol);
                TextView prec = (TextView) findViewById(R.id.textprecio);
                prec.setVisibility(View.GONE);

                Ubicaciones.setVisibility(View.GONE);
                envdc.setVisibility(View.GONE);

            }


            }
        }
    catch (Exception e){}




envdc.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

            Intent re = new Intent(Maps4Activity.this, DetalleUbicacionesActivity.class);
            TextView cordenada = (TextView) findViewById(R.id.textDistancia);
            String corde = cordenada.getText().toString();
            String Precio = Ubicaciones.getSelectedItem().toString().substring(1,2);
            String sector =Ubicaciones.getSelectedItem().toString();
            pedido.setPrecio_viaje(Precio);
            pedido.setSector(sector);
            pedido.setCoordenadas(corde);
            re.putExtra("PEDIDO",pedido);
        Toast.makeText(Maps4Activity.this, Precio, Toast.LENGTH_SHORT).show();
            startActivity(re);
        }


});

setTitle("Fruta Golosa");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }



    }






    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
             per=1;
             mMap.setMyLocationEnabled(true);
            }
        }
    }



    public void onMapSearch(View view) {
        final Pedido pedido = (Pedido) getIntent().getSerializableExtra("PEDIDO");
        final String Ciudad = pedido.getCiudad();
        Spinner Ubicacionesw;

        Ubicacionesw= (Spinner) findViewById(R.id.SpUbicaciones);
        String location =  Ubicacionesw.getSelectedItem().toString()+" "+Ciudad;
        List<Address> addressList = null;
        TextView prec2 = (TextView) findViewById(R.id.txtprecDol);

        Button envdc= (Button) findViewById(R.id.btnenviadicax);
        mMap.clear();
        envdc.setVisibility(View.INVISIBLE);

        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);

            } catch (IOException e) {
                Toast.makeText(this, "Problemas de conexion", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }


    Double longitudOrigen, latitudOrigen , latitudLimite , longitudlimite;
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        TextView prec = (TextView) findViewById(R.id.textprecio);
        final Pedido pedido = (Pedido) getIntent().getSerializableExtra("PEDIDO");
        TextView Coord = (TextView) findViewById(R.id.textDistancia);
        final String Ciudad = pedido.getCiudad();
        mMap.getUiSettings().setZoomControlsEnabled(true);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            per=1;
        } else {

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            per=0;
        }

        if (per==1){

            mMap.setMyLocationEnabled(true);

        }

        float zoom=16.0f;

try {


    if (Ciudad.equals("QUITO")) {
        final LatLng cord = new LatLng(-0.1847862, -78.5095457);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cord, zoom));


    }else {

        if (Ciudad.equals("GUAYAQUIL")) {

            final LatLng cord = new LatLng(-2.190755, -79.889706);

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cord, zoom));
        }



    }


}catch (Exception e){}


        prec.setText("De un toque a la pantalla para marcar ubicacion o deje pulsado.");
        Coord.setText("De un toque a la pantalla para marcar ubicacion o deje pulsado.");
        TextView prec2 = (TextView) findViewById(R.id.txtprecDol);

prec.setText("De un toque a la pantalla para marcar ubicacion o deje pulsado");



        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {



            try {

                if (Ciudad.equals("QUITO")) {
                    mMap.clear();
                    mMap.addMarker(new MarkerOptions()
                            .anchor(0.0f, 1.0f)
                            .position(latLng)


                    );
                    marcarmapa(latLng);
                }else {
                    if (Ciudad.equals("GUAYAQUIL")) {
                    mMap.clear();
                    mMap.addMarker(new MarkerOptions()
                            .anchor(0.0f, 1.0f)
                            .position(latLng)


                    );
                    marcarmapaG(latLng);
                }else {}}

            }catch (Exception e){


            }

            }




        });



        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {

                try {

                    if (Ciudad.equals("QUITO")) {

                        mMap.clear();
                        mMap.addMarker(new MarkerOptions()
                                .anchor(0.0f, 1.0f)
                                .position(latLng)


                        );
                        marcarmapa(latLng);
                    }else {  if (Ciudad.equals("GUAYAQUIL")) {
                        mMap.clear();
                        mMap.addMarker(new MarkerOptions()
                                .anchor(0.0f, 1.0f)
                                .position(latLng)


                        );
                        marcarmapaG(latLng);
                    }else {}}

                }catch (Exception e){


                }



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

    public void marcarmapa(LatLng latLng){
        TextView prec = (TextView) findViewById(R.id.textprecio);
        TextView prec2 = (TextView) findViewById(R.id.txtprecDol);
        TextView Coord = (TextView) findViewById(R.id.textDistancia);
        TextView dist2 = (TextView) findViewById(R.id.textDistancia2);
        Button envdc = (Button) findViewById(R.id.btnenviadicax);

        latitudOrigen = latLng.latitude;
        longitudOrigen = latLng.longitude;
        String strcoord = String.valueOf(latLng);

        Geocoder geocoder = new Geocoder(getApplicationContext());
        List<Address> direcciones = null;
        try {
            direcciones = geocoder.getFromLocation(latitudOrigen, longitudOrigen, 1);
        } catch (Exception e) {
            Log.d("Error", "Error en geocoder:" + e.toString());
        }

        try {
            Address direccion = direcciones.get(0);

            // Creamos el string a partir del elemento direccion
            String direccionText = String.format("%s, %s, %s",
                    direccion.getMaxAddressLineIndex() > 0 ? direccion.getAddressLine(0) : "",
                    direccion.getAddressLine(0),
                    direccion.getPostalCode());
            String area = direccion.getLocality().trim();
            String postal2 = direccion.getPostalCode();
            String postal = direccion.getPostalCode();
            float results[] = new float[10];

            Location.distanceBetween(-0.18471168023710308, -78.507034778599497, latitudOrigen, longitudOrigen, results);
            String str = String.valueOf(results[0]);
            prec.setText(direccionText.substring(1));
            Coord.setText(strcoord);
            dist2.setText(postal2);



            if (area.equals("null") || area.equals("") || area.isEmpty()) {

                prec.setText("Lo sentimos no vamos a esta direccion");

                envdc.setVisibility(View.INVISIBLE);
                Toast.makeText(Maps4Activity.this, "Verifique la direccion por favor", Toast.LENGTH_SHORT).show();



            }else {  envdc.setVisibility(View.VISIBLE);
                //elimar esta linea de codigo para devolver los precios orignales

            }
            //chimbacalle
            if (direccion.equals("")) {

                prec.setText("Lo sentimos no vamos a esta direccion");

                envdc.setVisibility(View.INVISIBLE);
                Toast.makeText(Maps4Activity.this, "Verifique la direccion por favor", Toast.LENGTH_SHORT).show();

            }else {  envdc.setVisibility(View.VISIBLE);
                //elimar esta linea de codigo para devolver los precios orignales

            }





        }catch (Exception e){


        }




    }

    public void marcarmapaG(LatLng latLng){
        final Pedido pedido = (Pedido) getIntent().getSerializableExtra("PEDIDO");
        TextView prec = (TextView) findViewById(R.id.textprecio);
        TextView prec2 = (TextView) findViewById(R.id.txtprecDol);
        TextView Coord = (TextView) findViewById(R.id.textDistancia);
        TextView dist2 = (TextView) findViewById(R.id.textDistancia2);
        Button envdc = (Button) findViewById(R.id.btnenviadicax);

        latitudOrigen = latLng.latitude;
        longitudOrigen = latLng.longitude;
        String strcoord = String.valueOf(latLng);

        Geocoder geocoder = new Geocoder(getApplicationContext());
        List<Address> direcciones = null;
        try {
            direcciones = geocoder.getFromLocation(latitudOrigen, longitudOrigen, 1);
        } catch (Exception e) {
            Log.d("Error", "Error en geocoder:" + e.toString());
        }

        try {
            Address direccion = direcciones.get(0);

            // Creamos el string a partir del elemento direccion
            String direccionText = String.format("%s, %s, %s",
                    direccion.getMaxAddressLineIndex() > 0 ? direccion.getAddressLine(0) : "",
                    direccion.getAddressLine(0),
                    direccion.getPostalCode());
            String area = direccion.getLocality().trim();
            String postal2 = direccion.getPostalCode();
            String postal = direccion.getPostalCode();
            float results[] = new float[10];

            Location.distanceBetween(-0.18471168023710308, -78.507034778599497, latitudOrigen, longitudOrigen, results);
            String str = String.valueOf(results[0]);
            prec.setText(direccionText.substring(1));
            Coord.setText(strcoord);
            dist2.setText(postal2);



            if (area.equals("null") || area.equals("") || area.isEmpty()) {

                prec.setText("Lo sentimos no vamos a esta direccion");

                    envdc.setVisibility(View.INVISIBLE);
                    Toast.makeText(Maps4Activity.this, "Verifique la direccion por favor", Toast.LENGTH_SHORT).show();



            }else {  envdc.setVisibility(View.VISIBLE);
                //elimar esta linea de codigo para devolver los precios orignales

            }
            //chimbacalle
            if (direccion.equals("")) {

                prec.setText("Lo sentimos no vamos a esta direccion");

                    envdc.setVisibility(View.INVISIBLE);
                    Toast.makeText(Maps4Activity.this, "Verifique la direccion por favor", Toast.LENGTH_SHORT).show();

            }else {  envdc.setVisibility(View.VISIBLE);
                //elimar esta linea de codigo para devolver los precios orignales

            }








        }catch (Exception e){


        }


    }


}

