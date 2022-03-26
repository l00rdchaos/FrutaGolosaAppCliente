package com.frutagolosa.fgapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.frutagolosa.fgapp.api.RegisterApiPendiente;
import com.frutagolosa.fgapp.model.Pedido;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DetalleUbicacionesActivity extends AppCompatActivity {

    public static final String ROOT_URL = "https://frutagolosa.com/FrutaGolosaApp";


    private final int xf = (int) (Math.random() * 10000);
    private final String xf2 = String.valueOf(xf);

    //Recibe variables del intent anterior---------------------------------------------
    final Pedido pedido = (Pedido) getIntent().getSerializableExtra("PEDIDO");
    //---------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_ubicaciones);



        final EditText CallePrin=(EditText) findViewById(R.id.EdtCallePrin);
        final EditText CallSec=(EditText) findViewById(R.id.EdtCalleSec);
        final EditText Refe=(EditText) findViewById(R.id.EdtRefe);
        final EditText EditNumerac=(EditText) findViewById(R.id.EditNumerac);
        final EditText DetalleAGG=(EditText) findViewById(R.id.EdtDetaAG);
        final EditText Especificaciona=(EditText) findViewById(R.id.TxtEspecifiacion);
        final EditText CasaADD=(EditText) findViewById(R.id.EdtNombCEE);
        final TextView SectorTxt=(TextView)findViewById(R.id.SectorTXt);
        SectorTxt.setText(pedido.getSector());
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        final Button btn = (Button) findViewById(R.id.acepbtn);
        btn.setBackgroundColor(getResources().getColor(R.color.gris2));
        final Spinner  TipoTG = (Spinner) findViewById(R.id.SpGlobT);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.TarjetaGlobo, android.R.layout.simple_spinner_item);
        TipoTG.setAdapter(adapter);

        final Spinner  CasaEmpEd = (Spinner) findViewById(R.id.SpCasaEd);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.CasaEmpresaEdificio, android.R.layout.simple_spinner_item);
      CasaEmpEd.setAdapter(adapter2);





        TextWatcher watcher = new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().length()==0||CallePrin.getText().toString().trim().isEmpty()||CallSec.getText().toString().trim().isEmpty()
                        ||Refe.getText().toString().trim().isEmpty()||EditNumerac.getText().toString().trim().isEmpty()||DetalleAGG.getText().toString().trim().isEmpty()
                        ||Especificaciona.getText().toString().trim().isEmpty()||CasaADD.getText().toString().trim().isEmpty()){


                    btn.setBackgroundColor(getResources().getColor(R.color.gris2));



                } else {

                    btn.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //YOUR CODE
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        };


        CallePrin.addTextChangedListener(watcher);
        CallSec.addTextChangedListener(watcher);
        Refe.addTextChangedListener(watcher);
        EditNumerac.addTextChangedListener(watcher);
        DetalleAGG.addTextChangedListener(watcher);
        Especificaciona.addTextChangedListener(watcher);
        CasaADD.addTextChangedListener(watcher);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
insertPendiente();





            }
        }); //boton del clicklister

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }


    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void insertPendiente() {




        final EditText CallePrin=(EditText) findViewById(R.id.EdtCallePrin);
        final EditText CallSec=(EditText) findViewById(R.id.EdtCalleSec);
        final EditText Refe=(EditText) findViewById(R.id.EdtRefe);
        final EditText EditNumerac=(EditText) findViewById(R.id.EditNumerac);
        final EditText DetalleAGG=(EditText) findViewById(R.id.EdtDetaAG);
        final EditText Especificaciona=(EditText) findViewById(R.id.TxtEspecifiacion);
        final EditText CasaADD=(EditText) findViewById(R.id.EdtNombCEE);
        final Spinner  TipoTG = (Spinner) findViewById(R.id.SpGlobT);
        final Spinner  CasaEmpEd = (Spinner) findViewById(R.id.SpCasaEd);

        if (CallePrin.getText().toString().trim().isEmpty()||CallSec.getText().toString().trim().isEmpty()||Refe.getText().toString().trim().isEmpty()||EditNumerac.getText().toString().trim().isEmpty()
                ||DetalleAGG.getText().toString().trim().isEmpty()||Especificaciona.getText().toString().trim().isEmpty()||CasaADD.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Por favor, rellene todos los campos en blanco", Toast.LENGTH_SHORT).show();

        }else {if (!compruebaConexion(getApplicationContext())) {
            Toast.makeText(getBaseContext(), "Necesaria conexión a internet para comprar ", Toast.LENGTH_SHORT).show();
        }else {
            final ProgressDialog loading = ProgressDialog.show(DetalleUbicacionesActivity.this, "Cargando...", "Espere por favor");
            SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
            String nombreus = preferences.getString("nombreus", "Registrese");
            String mailus = preferences.getString("mailus", "No");
            String telefonous = preferences.getString("telefonous", "No");

            final int cantidadpass=getIntent().getIntExtra(Maps4Activity.Cantidadpassm,-1);
            final String Nombpass=getIntent().getStringExtra(Maps4Activity.NombreQuienRecibe);
            final String TelPass=getIntent().getStringExtra(Maps4Activity.TelefonoQuienRecibe);
            final String Horapass=getIntent().getStringExtra(Maps4Activity.FranjaHorariaQueRecibe);
            final String FechaPass=getIntent().getStringExtra(Maps4Activity.DiaEntrega);
            final String DireccionPassr=getIntent().getStringExtra(Maps4Activity.Direccionpassa);

            final String PrecioViajePassa=getIntent().getStringExtra(Maps4Activity.PrecioViajePass);
            final String precioArreglo = getIntent().getStringExtra(Maps4Activity.PrecioArreglo);
            final String IdArreglo = getIntent().getStringExtra(Maps4Activity.NombreArreglo);
            final String sector = getIntent().getStringExtra(Maps4Activity.Sector);

            final String Ciudad = getIntent().getStringExtra(Maps4Activity.CiudadA);
            final String callprin=CallePrin.getText().toString().replace(","," ").trim();
            final String callsec=CallSec.getText().toString().replace(","," ").trim();
            final String refer=Refe.getText().toString().trim();
            final String Numerac=EditNumerac.getText().toString().replace(","," ").trim();
            final String detagg=DetalleAGG.getText().toString().replace(","," ").trim();
            final String MotivGlob =TipoTG.getSelectedItem().toString();
            final String CasaEmpEdif =CasaEmpEd.getSelectedItem().toString()+": "+CasaADD.getText().toString().replace(","," ").trim();
            final String especificacion = Especificaciona.getText().toString().replace(","," ").trim();

            String datea = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

            String a = datea.replace("-","/");
            String b = telefonous;
            String c = nombreus;
            String d = mailus;
            final String e = Nombpass;
            String f = TelPass;
            String g = FechaPass;
            String h = Horapass;
            String i = callprin;
            String j = Numerac;
            String k = callsec;
            String l = CasaEmpEdif;
            String m = refer;
            String n = IdArreglo;
            String o = precioArreglo;
            String p = "NO";
            String q = Nombpass;
            String r = detagg;
            String s = especificacion;
            String t = "keyaccount";
            String v =sector;
            String w = "FRUTAGOLOSA";
            String x = "NO";
            String cc = "NO";
            String y  =MotivGlob;
            String z = "Compra Pendiente";
            String aa = "motorizado";
            String bb = DireccionPassr;
            String dd = Ciudad;
            String ee = PrecioViajePassa.replace("$","");

            RestAdapter adapter = new RestAdapter.Builder()
                    .setEndpoint(ROOT_URL)
                    .build();

            RegisterApiPendiente api = adapter.create(RegisterApiPendiente.class);


            api.inserpendiente(
                    a,
                    b,
                    c,
                    d,
                    e,
                    f,
                    g,
                    h,
                    i,
                    j,
                    k,
                    l,
                    m,
                    n,
                    o,
                    p,
                    q,
                    r,
                    s,
                    t,
                    v,
                    w,
                    x,
                    y,
                    z,
                    aa,
                    bb,
                    cc,
                    dd,
                    ee,


                    new Callback<Response>() {
                        @Override
                        public void success(Response result, Response response) {
                                        if(response.getBody()!=null) {
                                            BufferedReader reader = null;

                                            String output = "";

                                            try {
                                                reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                                                output = reader.readLine();

                                                String id = output;


                                                String callprin = CallePrin.getText().toString().replace(",", " ").trim();
                                                String callsec = CallSec.getText().toString().replace(",", " ").trim();
                                                String refer = Refe.getText().toString().trim();
                                                String Numerac = EditNumerac.getText().toString().replace(",", " ").trim();
                                                String detagg = DetalleAGG.getText().toString().replace(",", " ").trim();
                                                String MotivGlob = TipoTG.getSelectedItem().toString();
                                                String CasaEmpEdif = CasaEmpEd.getSelectedItem().toString() + ": " + CasaADD.getText().toString().replace(",", " ").trim();
                                                String especificacion = Especificaciona.getText().toString().replace(",", " ").trim();


                                                Intent ba = new Intent(DetalleUbicacionesActivity.this, ResumenPedidoActivity.class);


                                                pedido.setCalle_principal(callprin);
                                                pedido.setCalle_secundaria(callsec);
                                                pedido.setReferencia(refer);
                                                pedido.setDetalle_ubicacion(CasaEmpEdif);
                                                pedido.setDetaAgg(detagg);
                                                pedido.setMotivo(MotivGlob);
                                                pedido.setNumeracion(Numerac);
                                                pedido.setEspecificacion(especificacion);
                                                loading.dismiss();
                                                ba.putExtra("PEDIDO",pedido);
                                                startActivity(ba);


                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }


                                        } }

                        @Override
                        public void failure(RetrofitError error) {

                            loading.dismiss();
                            Toast.makeText(DetalleUbicacionesActivity.this, "Intente de nuevo por favor, fallo la conexion", Toast.LENGTH_SHORT).show();
                        }
                    }
            );




        }


        }




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
