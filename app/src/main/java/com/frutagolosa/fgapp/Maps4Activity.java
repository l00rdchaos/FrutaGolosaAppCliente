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
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.frutagolosa.fgapp.api.ApiInterfaceCoord;
import com.frutagolosa.fgapp.model.Motorizado;
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
    public static final String NombreQuienRecibe="NombreQuienRecibe" ;
    public static final String TelefonoQuienRecibe="TelfQuienRecibe" ;
    public static final String DiaEntrega="DiaQueRecibe" ;
    public static final String FranjaHorariaQueRecibe="HoraQueRecibe" ;
    public static final String Cantidadpassm="u" ;
    public static final String Direccionpassa="hola" ;
    public static final String PrecioViajePass="precioviaje" ;
    public static final String NombreArreglo="nombrearreglo" ;
    public static final String cantidadArreglos="cantidadArreglos" ;
    public static final String PrecioArreglo="precioarreglo" ;
    public static final String Sector="sector" ;
    public static final String CiudadA="ciudadd" ;
    private GoogleMap mMap;
    protected static final int REQUEST_CHECK_SETTINGS = 0x1;
    private int per=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps4);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        final int cantidadpass=getIntent().getIntExtra(UbicacionEnvioActiviy.canipass,-1);
        final String Nombpass=getIntent().getStringExtra(UbicacionEnvioActiviy.NombreQuienRecibe);
        final String TelPass=getIntent().getStringExtra(UbicacionEnvioActiviy.TelefonoQuienRecibe);
        final String Horapass=getIntent().getStringExtra(UbicacionEnvioActiviy.FranjaHorariaQueRecibe);
        final String FechaPass=getIntent().getStringExtra(UbicacionEnvioActiviy.DiaEntrega);
        final String precioArreglo = getIntent().getStringExtra(UbicacionEnvioActiviy.PrecioArreglo);
        final String IdArreglo = getIntent().getStringExtra(UbicacionEnvioActiviy.NombreArreglo);
        final String Ciudad = getIntent().getStringExtra(UbicacionEnvioActiviy.CiudadA);



        Button envdc= (Button) findViewById(R.id.btnenviadicax);

       final Spinner Ubicaciones;

        Ubicaciones= (Spinner) findViewById(R.id.SpUbicaciones);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        try {


            if(Ciudad.equals("QUITO")){
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Ubicaciones, android.R.layout.simple_spinner_item);
                Ubicaciones.setAdapter(adapter);
            }else {   if(Ciudad.equals("GUAYAQUIL")){
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.UbicacionesG, android.R.layout.simple_spinner_item);
                Ubicaciones.setAdapter(adapter);}else {
                TextView prec2 = (TextView) findViewById(R.id.txtprecDol);
                TextView prec = (TextView) findViewById(R.id.textprecio);
                prec.setVisibility(View.GONE);
                prec2.setVisibility(View.GONE);
                Ubicaciones.setVisibility(View.GONE);
                envdc.setVisibility(View.GONE);

            }


            }
        }
    catch (Exception e){}



envdc.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        TextView prec2 = (TextView) findViewById(R.id.txtprecDol);
        if(prec2.getText().toString().equals("Precio")){

            Toast.makeText(Maps4Activity.this, "Revise la direccion, no es valida", Toast.LENGTH_SHORT).show();

        }else {
            Intent re = new Intent(Maps4Activity.this, DetalleUbicacionesActivity.class);
            TextView cordenada = (TextView) findViewById(R.id.textDistancia);
            TextView prec = (TextView) findViewById(R.id.txtprecDol);
            TextView sectortxt = (TextView) findViewById(R.id.textprecio);
            String corde = cordenada.getText().toString();
            String Precio = prec2.getText().toString();
            String sector =Precio+" "+sectortxt.getText().toString();
            re.putExtra(PrecioViajePass, Precio);
            re.putExtra(NombreQuienRecibe, Nombpass);
            re.putExtra(TelefonoQuienRecibe, TelPass);
            re.putExtra(DiaEntrega, FechaPass);
            re.putExtra(FranjaHorariaQueRecibe, Horapass);
            re.putExtra(Cantidadpassm, cantidadpass);
            re.putExtra(Direccionpassa, corde);
            re.putExtra(PrecioArreglo, precioArreglo);
            re.putExtra(NombreArreglo, IdArreglo);
            re.putExtra(Sector, sector);
            re.putExtra(CiudadA,Ciudad);
            startActivity(re);
        }

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
        final String Ciudad = getIntent().getStringExtra(UbicacionEnvioActiviy.CiudadA);
        Spinner Ubicacionesw;

        Ubicacionesw= (Spinner) findViewById(R.id.SpUbicaciones);
        String location =  Ubicacionesw.getSelectedItem().toString()+" "+Ciudad;
        List<Address> addressList = null;
        TextView prec2 = (TextView) findViewById(R.id.txtprecDol);
        prec2.setText("Precio");
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
            mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }


    Double longitudOrigen, latitudOrigen , latitudLimite , longitudlimite;
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        TextView prec = (TextView) findViewById(R.id.textprecio);
        TextView Coord = (TextView) findViewById(R.id.textDistancia);
        final String Ciudad = getIntent().getStringExtra(UbicacionEnvioActiviy.CiudadA);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        final String Coordenadas = getIntent().getStringExtra(VerMotorizado.COORDENADAS);



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

        else {if (Ciudad.equals("si")){
Button BtnEnvDic=(findViewById(R.id.BtnEnvDic));
            BtnEnvDic.setVisibility(View.GONE);
            ImageView fotomoto=findViewById(R.id.imgFotoMoto);
            final String Coordena = getIntent().getStringExtra(VerMotorizado.COORDENADAS);
            final String imgmoto = getIntent().getStringExtra(VerMotorizado.IMAGENMOTO);
            fotomoto.setVisibility(View.VISIBLE);
            Glide.with(this).load(imgmoto).into(fotomoto);
            String[] latlong =  Coordena.split(",");
            double latitude = Double.parseDouble(latlong[0]);
            double longitude = Double.parseDouble(latlong[1]);
            LatLng location = new LatLng(latitude, longitude);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,zoom));
            mMap.addMarker(new MarkerOptions()
                    .anchor(0.0f, 1.0f)
                    .position(location)


            );


            final CountDownTimer mcountdowntimer = new CountDownTimer(200000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    start();
                    MarcarMotorizado();


                }
            }.start();



        }


        }


    }





try{




}catch (Exception e){




}





}catch (Exception e){}


        prec.setText("De un toque a la pantalla para marcar ubicacion o deje pulsado.");
        Coord.setText("De un toque a la pantalla para marcar ubicacion o deje pulsado.");
        TextView prec2 = (TextView) findViewById(R.id.txtprecDol);
prec2.setText("Precio");
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

    private void MarcarMotorizado() {
        final String m = getIntent().getStringExtra(VerMotorizado.MOTORIZADOTELFONO);
        RestAdapter adapter2 = new RestAdapter.Builder()
                .setEndpoint("https://frutagolosa.com/FrutaGolosaApp/ObtenCoord.php?m="+m)
                .build();

        ApiInterfaceCoord api5 = adapter2.create(ApiInterfaceCoord.class);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        api5.traecoord(
               m,



                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {

                        BufferedReader reader = null;
                        String output = "";


                        try {
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));


                            output = reader.readLine();
                            if(output.equals("no")||output.equals("GPS Desactivado")){

                                Toast.makeText(Maps4Activity.this, "Motorizado tiene GPS Desactivado", Toast.LENGTH_SHORT).show();
                            }
                            else {

                                Toast.makeText(Maps4Activity.this, "Fruta Golosa: Ubicacion de motorizado actualizada", Toast.LENGTH_SHORT).show();


                                final String Coordena = getIntent().getStringExtra(VerMotorizado.COORDENADAS);

                                float zoom=16.0f;
                                String[] latlong =  output.split(",");
                                double latitude = Double.parseDouble(latlong[0]);
                                double longitude = Double.parseDouble(latlong[1]);
                                LatLng location = new LatLng(latitude, longitude);
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,zoom));
                                mMap.addMarker(new MarkerOptions()
                                        .anchor(0.0f, 1.0f)
                                        .position(location)


                                );

                            }



                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void failure(RetrofitError error) {

                        Toast.makeText(Maps4Activity.this, "No se puede aplicar descuentos, revise su internet", Toast.LENGTH_LONG).show();

                    }
                }

        );



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
        prec2.setText("Precio");
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


            }


            //chimbacalle
            if (direccion.equals("")) {

                prec.setText("Lo sentimos no vamos a esta direccion");
            }







            if (area.equals("Amaguaña")) {

                prec2.setText("$10");


            }


            if (area.equals("Calacalí")&&latLng.longitude<=-78.47131613641977){

                prec2.setText("$18");


            }
//mitad del mundo
            if (latLng.latitude>-0.05126941264935195&&latLng.longitude>-78.47131613641977&&area.equals("Quito")) {

                prec2.setText("$12");


            }

            //chimbacalle
            if (postal.equals("170101")) {

                prec2.setText("$1");
            }

            //Barcino
            if (postal.equals("170310")) {

                prec2.setText("$2");
            }


            //sangolqui
            if (postal.equals("171103")) {

                prec2.setText("$6");
            }


            //benalcazar
            if (postal.equals("170102")) {

                prec2.setText("$0");
            }


            //cotocollao

            if (postal.equals("170103")) {

                prec2.setText("$0");
            }

            //Chaupicruz

            if (postal.equals("170104")) {

                prec2.setText("$2");
            }

            //chillogallo
            if (postal.equals("170105")) {

                prec2.setText("$3");
            }

            //elsalvador

            if (postal.equals("170106")) {

                prec2.setText("$0");
            }
            //gonzales suarez

            if (postal.equals("170107")) {

                prec2.setText("$0");
            }

            //guapulo

            if (postal.equals("170108")) {

                prec2.setText("$3");
            }

            //guapulo 2
            if (postal.equals("170109")) {

                prec2.setText("$3");
            }

            //la libertad
            if (postal.equals("170110")) {

                prec2.setText("$2");
            }

            //la magdalena

            if (postal.equals("170111")) {

                prec2.setText("$1");
            }


            //San Blas
            if (postal.equals("170113")) {

                prec2.setText("$");
            }


            //San Roque

            if (postal.equals("170115")) {

                prec2.setText("$");
            }


            //San Sebastian

            if (postal.equals("170116")) {

                prec2.setText("$");
            }


            //Santa Barbara
            if (postal.equals("170117")) {

                prec2.setText("$");
            }


            //Santa Prisca
            if (postal.equals("170118")) {

                prec2.setText("$3");
            }


            //Villaflora
            if (postal.equals("170119")) {

                prec2.setText("$3");
            }


            //Carcelen
            if (postal.equals("170120") && (results[0] <= 13355.933)) {

                prec2.setText("$4");
            }

            //Pusuqui
            if (postal.equals("170120") && (results[0] >= 13355.933)) {

                prec2.setText("$6");
            }


            //Chimbacalle
            if (postal.equals("170121")) {

                prec2.setText("$1");
            }


            //El Batan
            if (postal.equals("170122")) {

                prec2.setText("$2");
            }

            //El Beaterio
            if (postal.equals("170123")) {

                prec2.setText("$");
            }


            //Monteserrin
            if (postal.equals("170124")) {

                prec2.setText("$3");
            }


            //Eloy Alfaro
            if (postal.equals("170125")) {

                prec2.setText("$3");
            }


            //Guamani
            if (postal.equals("170126")) {

                prec2.setText("$6");
            }


            //Concepcion
            if (postal.equals("170127")) {

                prec2.setText("$0");
            }


            //Las Cuadras
            if (postal.equals("170128")) {

                prec2.setText("$");
            }


            //Belisario Quevedo
            if (postal.equals("170129")) {

                prec2.setText("$0");
            }


            //Centro Historico
            if (postal.equals("170130")) {

                prec2.setText("$0");
            }


            //Chilibulo
            if (postal.equals("170131")) {

                prec2.setText("$2");
            }


            //Pucara

            if (postal.equals("170132")) {

                prec2.setText("$0");
            }


            //Comite del pueblo
            if (postal.equals("170133")) {

                prec2.setText("$4");
            }


            //Condado
            if (postal.equals("170134")) {

                prec2.setText("$3");
            }


            //inaquito
            if (postal.equals("170135")) {

                prec2.setText("$0");
            }


            //itchimbia

            if (postal.equals("170136") && (results[0] <= 3687.9023)) {

                prec2.setText("$0");
            }

            //guapulo
            if (postal.equals("170136") && (results[0] > 3687.9023)) {

                prec2.setText("$0");
            }


            //Kennedy
            if (postal.equals("170138")) {

                prec2.setText("$2");
            }


            // la argelia

            if (postal.equals("170139")) {

                prec2.setText("$3");
            }


            //la ecuatoriana

            if (postal.equals("170140")) {

                prec2.setText("$5");
            }


            //la ferroviaria

            if (postal.equals("170141")) {

                prec2.setText("$2");
            }


            //la mena

            if (postal.equals("170142")) {

                prec2.setText("$2");
            }


            //mariscal sucre
            if (postal.equals("170143")) {

                prec2.setText("$0");
            }


            //ponceano
            if (postal.equals("170144")) {

                prec2.setText("$2");
            }

            //ponceano
            if (postal.equals("170145")) {

                prec2.setText("$3");
            }

            //ARMENIA
            if (postal.equals("170801")) {

                prec2.setText("$8");
            }


            //Quitumbe
            if (postal.equals("170146")) {

                prec2.setText("$3");
            }


            //Rumipamba
            if (postal.equals("170147")) {

                prec2.setText("$0");
            }

            if (postal.equals("170148")) {

                prec2.setText("$2");
            }


            //Alangasi
            if (postal.equals("170151")) {

                prec2.setText("");
            }


            //amaguana
            if (postal.equals("170152")) {

                prec2.setText("");
            }


            //Chabaspamba
            if (postal.equals("170153")) {

                prec2.setText("$10");
            }

            //calacali
            if (postal.equals("170154")) {

                prec2.setText("$10");
            }


            //Carapungo
            if (postal.equals("170155")) {

                prec2.setText("$10");
            }


            //Conocoto
            if (postal.equals("170156")) {

                prec2.setText("$6");
            }


            //Cumbaya
            if (postal.equals("170157")) {

                prec2.setText("$5");
            }


            //Chavezpamba
            if (postal.equals("170158")) {

                prec2.setText("");
            }

            //Chilpa
            if (postal.equals("170159")) {

                prec2.setText("");
            }


            //eL quinche
            if (postal.equals("170160")) {

                prec2.setText("");
            }

            //Gualea
            if (postal.equals("170161")) {

                prec2.setText("");
            }


            //Guanopolo
            if (postal.equals("170162")) {

                prec2.setText("");
            }


            //Guayllabamba
            if (postal.equals("170163")) {

                prec2.setText("");
            }


            //La merced
            if (postal.equals("170164")) {

                prec2.setText("");
            }


            //Llano chico
            if (postal.equals("170165")) {

                prec2.setText("");
            }


            //Lloa
            if (postal.equals("170166")) {


            }



            //Nanegal
            if (postal.equals("170168")) {


            }


            //Nanegalito
            if (postal.equals("170169")) {


            }


            //Nayon
            if (postal.equals("170170")) {
                prec2.setText("$6");

            }


            //Nono
            if (postal.equals("170171")) {


            }

            //Pacto
            if (postal.equals("170172")) {


            }

            //Pedro Vicente Maldonado
            if (postal.equals("170173")) {
                prec2.setText("");

            }

            //Perucho
            if (postal.equals("170174")) {


            }

            //Pifo
            if (postal.equals("170175")) {


            }


            //Pintag
            if (postal.equals("170176")) {


            }

            //Pomasqui
            if (postal.equals("170177")) {
                prec2.setText("$6");

            }

            //Puellaro
            if (postal.equals("170178")) {


            }

            //Puembo
            if (postal.equals("170179")) {
                prec2.setText("$12");

            }

            //San Antonio
            if (postal.equals("170180")) {
                prec2.setText("$10");

            }

            //San Jose Minas
            if (postal.equals("170181")) {


            }


            //Puerto Quito
            if (postal.equals("170182")) {


            }


            //Tabebela
            if (postal.equals("170183")) {
                prec2.setText("$10");

            }

            //Tumbaco
            if (postal.equals("170184")) {
                prec2.setText("$6");

            }

            //Yaruqui
            if (postal.equals("170185")) {


            }

            //Zambiza
            if (postal.equals("170186")) {


            }


            //carapungo
            if (postal.equals("170203")) {
                prec2.setText("$6");

            }


            //carapungo
            if (postal.equals("170201")) {
                prec2.setText("$6");

            }


            //pomasqui llegando a mitad del mudndo
            if (postal.equals("170303")) {
                prec2.setText("$8");

            }

            //pomasqui llegando a mitad del mudndo
            if (postal.equals("171103")) {
                prec2.setText("$8");

            }

            // cumbaya 2
            if (postal.equals("170902")) {
                prec2.setText("$5");

            }

            // chaupicruz
            if (postal.equals("170521")) {
                prec2.setText("$0");

            }

            // Batan Alto
            if (postal.equals("170158")) {
                prec2.setText("$0");

            }
            // Gonzales Suarez
            if (postal.equals("170517")) {
                prec2.setText("$0");

            }

            if (postal.equals("170515")) {
                prec2.setText("$0");

            }

            if (postal.equals("170513")) {
                prec2.setText("$1");

            }


            //carapungo lejos
            if (postal.equals("170202")) {
                prec2.setText("$6");

            }
            //La esperanza
            if (postal.equals("170205")) {
                prec2.setText("$7");

            }


            if (postal.equals("170310")) {
                prec2.setText("$3");

            }

            if (area.equals("Sangolquí")) {

                prec2.setText("$7");


            }


            if(prec2.getText().toString().equals("Precio")){
                envdc.setVisibility(View.INVISIBLE);
                Toast.makeText(Maps4Activity.this, "Verifique la direccion por favor", Toast.LENGTH_SHORT).show();

            }else {  envdc.setVisibility(View.VISIBLE);}

        }catch (Exception e){


        }




    }

    public void marcarmapaG(LatLng latLng){
        final String Ciudad = getIntent().getStringExtra(UbicacionEnvioActiviy.CiudadA);
        TextView prec = (TextView) findViewById(R.id.textprecio);
        TextView prec2 = (TextView) findViewById(R.id.txtprecDol);
        TextView Coord = (TextView) findViewById(R.id.textDistancia);
        TextView dist2 = (TextView) findViewById(R.id.textDistancia2);
        Button envdc = (Button) findViewById(R.id.btnenviadicax);
        prec2.setText("Precio");
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


            }


            //chimbacalle
            if (direccion.equals("")) {

                prec.setText("Lo sentimos no vamos a esta direccion");
            }










            if (postal.equals("093015")) {
                prec2.setText("$3");

            }



            if (postal.equals("090101")) {
                prec2.setText("$2");

            }

            if (postal.equals("090102")) {
                prec2.setText("$2");

            }

            if (postal.equals("090103")) {
                prec2.setText("$3");

            }

            if (postal.equals("090104")) {
                prec2.setText("$3");

            }

            if (postal.equals("090105")) {
                prec2.setText("$4");

            }


            if (postal.equals("090106")) {
                prec2.setText("$3");

            }

            if (postal.equals("090107")) {
                prec2.setText("$2");

            }
            if (postal.equals("090108")) {
                prec2.setText("$3");

            }
            if (postal.equals("090109")) {
                prec2.setText("$3");

            }
            if (postal.equals("090110")) {
                prec2.setText("$5");

            }
            if (postal.equals("090111")) {
                prec2.setText("$4");

            }

            if (postal.equals("090112")) {
                prec2.setText("$3");

            }


            if (postal.equals("090202")) {
                prec2.setText("$2");

            }

            if (postal.equals("090203")) {
                prec2.setText("$2");

            }


            if (postal.equals("090204")) {
                prec2.setText("$3");

            }

            if (postal.equals("090205")) {
                prec2.setText("$3");

            }


            if (postal.equals("090207")) {
                prec2.setText("$4");

            }

            if (postal.equals("090208")) {
                prec2.setText("$4");

            }

            if (postal.equals("090301")) {
                prec2.setText("$2");
            }

                if (postal.equals("090304")) {
                prec2.setText("$2");
            }

            if (postal.equals("090305")) {
                prec2.setText("$2");
            }
            if (postal.equals("090306")) {
                prec2.setText("$2");
            }

            if (postal.equals("090314")) {
                prec2.setText("$0");
            }

            if (postal.equals("090306")) {
                prec2.setText("$2");
            }
            if (postal.equals("090307")) {
                prec2.setText("$2");
            }


            if (postal.equals("09603")) {
                prec2.setText("$2");
            }

            if (postal.equals("090303")) {
                prec2.setText("$2");
            }

            if (postal.equals("090308")) {
                prec2.setText("$2");
            }

            if (postal.equals("090309")) {
                prec2.setText("$3");
            }

            if (postal.equals("090310")) {
                prec2.setText("$3");
            }
            if (postal.equals("090311")) {
                prec2.setText("$1");
            }



            if (postal.equals("090312")) {
                prec2.setText("$0");
            }
            if (postal.equals("090313")) {
                prec2.setText("$0");
            }


            if (postal.equals("090314")) {
                prec2.setText("$0");
            }

            if (postal.equals("090315")) {
                prec2.setText("$0");
            }

            if (postal.equals("090403")) {
                prec2.setText("$3");
            }

            if (postal.equals("090404")) {
                prec2.setText("$3");
            }

            if (postal.equals("090405")) {
                prec2.setText("$3");
            }

            if (postal.equals("090407")) {
                prec2.setText("$3");
            }

            if (postal.equals("090409")) {
                prec2.setText("$2");
            }


            if (postal.equals("090507")) {
                prec2.setText("$2");
            }
            if (postal.equals("090508")) {
                prec2.setText("$2");
            }

            if (postal.equals("090503")) {
                prec2.setText("$2");
            }

            if (postal.equals("090501")) {
                prec2.setText("$2");
            }

            if (postal.equals("090502")) {
                prec2.setText("$2");
            }

            if (postal.equals("090504")) {
                prec2.setText("$2");
            }
            if (postal.equals("090505")) {
                prec2.setText("$2");
            }

            if (postal.equals("090506")) {
                prec2.setText("$2");
            }
            if (postal.equals("090507")) {
                prec2.setText("$2");
            }


            if (postal.equals("090508")) {
                prec2.setText("$2");
            }

            if (postal.equals("090509")) {
                prec2.setText("$2");
            }

            if (postal.equals("090510")) {
                prec2.setText("$2");
            }

            if (postal.equals("090511")) {
                prec2.setText("$3");
            }
            if (postal.equals("090512")) {
                prec2.setText("$2");
            }



            if (postal.equals("090514")) {
                prec2.setText("$2");
            }

            if (postal.equals("090513")) {
                prec2.setText("$3");
            }

            if (postal.equals("090313")) {
                prec2.setText("$0");
            }

            if (postal.equals("090402")) {
                prec2.setText("$3");
            }

            if (postal.equals("090403")) {
                prec2.setText("$3");
            }


            if (postal.equals("090405")) {
                prec2.setText("$3");
            }

            if (postal.equals("090408")) {
                prec2.setText("$3");
            }


            if (postal.equals("090410")) {
                prec2.setText("$1");
            }

            if (postal.equals("090412")) {
                prec2.setText("$2");
            }

            if (postal.equals("090413")) {
                prec2.setText("$2");
            }
            if (postal.equals("090414")) {
                prec2.setText("$3");
            }

            if (postal.equals("090415")) {
                prec2.setText("$3");
            }


            if (postal.equals("090416")) {
                prec2.setText("$3");
            }
            if (postal.equals("090417")) {
                prec2.setText("$3");
            }

            if (postal.equals("090418")) {
                prec2.setText("$3");
            }

            if (postal.equals("090506")) {
                prec2.setText("$2");
            }




            if (postal.equals("090601")) {
                prec2.setText("$3");
            }
            if (postal.equals("090602")) {
                prec2.setText("$3");
            }

            if (postal.equals("090604")) {
                prec2.setText("$3");
            }

            if (postal.equals("090606")) {
                prec2.setText("$3");
            }

            if (postal.equals("090607")) {
                prec2.setText("$2");
            }
            if (postal.equals("090608")) {
                prec2.setText("$2");
            }
            if (postal.equals("090609")) {
                prec2.setText("$3");
            }
            if (postal.equals("090605")) {
                prec2.setText("$3");
            }
            if (postal.equals("090610")) {
                prec2.setText("$3");
            }

            if (postal.equals("090615")) {
                prec2.setText("$4");
            }

            if (postal.equals("090701")) {
                prec2.setText("$3");
            }

            if (postal.equals("090702")) {
                prec2.setText("$5");
            }

            if (postal.equals("090703")) {
                prec2.setText("$4");
            }
            if (postal.equals("090704")) {
                prec2.setText("$4");
            }

            if (postal.equals("090705")) {
                prec2.setText("$3");
            }

            if (postal.equals("090804")) {
                prec2.setText("$5");
            }

            if (postal.equals("090904")) {
                prec2.setText("$3");
            }

            if (postal.equals("090112")) {
                prec2.setText("$4");
            }

            if (postal.equals("092301")) {
                prec2.setText("$3");
            }

            if (postal.equals("092401")) {
                prec2.setText("$3");
            }

            if (postal.equals("092402")) {
                prec2.setText("$3");
            }

            if (postal.equals("092404")) {
                prec2.setText("$6");
            }

            if (postal.equals("092405")) {
                prec2.setText("$5");
            }

            if (postal.equals("092408")) {
                prec2.setText("$3");
            }
            if (postal.equals("092409")) {
                prec2.setText("$3");
            }

            if (postal.equals("092410")) {
                prec2.setText("$3");
            }



            if (area.equals("Durán")) {

                prec2.setText("$3");


            }


            if(prec2.getText().toString().equals("Precio")){
                envdc.setVisibility(View.INVISIBLE);
                Toast.makeText(Maps4Activity.this, "Verifique la direccion por favor", Toast.LENGTH_SHORT).show();

            }else {  envdc.setVisibility(View.VISIBLE);}

        }catch (Exception e){


        }


    }


}

