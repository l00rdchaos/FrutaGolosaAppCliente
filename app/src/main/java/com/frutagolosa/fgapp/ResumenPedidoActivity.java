package com.frutagolosa.fgapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
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
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.frutagolosa.fgapp.api.ApiInterface4;
import com.frutagolosa.fgapp.model.Pedido;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ResumenPedidoActivity extends AppCompatActivity {
    //Recibe variables del intent anterior---------------------------------------------

    //---------------------------------------------------------------------------------

    public  int preciototal=1;
    public  String preciototal2="a";
    public  int preciototale=1;
    public String contadorpedidos;
    public  int precio;
    public int dac;
   public int preciof;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_pedido);
        final Pedido pedido = (Pedido) getIntent().getSerializableExtra("PEDIDO");


        ImageView imgcomparr=findViewById(R.id.ImgCompArr);
        Glide.with(this).asBitmap().transform(new CenterCrop(),new RoundedCorners(10)).load("https://frutagolosa.com/FrutaGolosaApp/Administrador/images/" +pedido.getNombre_arreglo().toLowerCase()+"1.jpg").into(imgcomparr);

        TextView sectortxt=(TextView) findViewById(R.id.TxtSector);
        TextView NA=(TextView) findViewById(R.id.NombreArregloPr);
        TextView PA=(TextView) findViewById(R.id.PrecioArreglo);
        TextView NP=(TextView) findViewById(R.id.NombrePrecibr);
        final TextView TP=(TextView) findViewById(R.id.TelefonoPrRecibe);
        TextView FecR=(TextView) findViewById(R.id.FechaRecibepr);
        TextView HoraRec=(TextView) findViewById(R.id.HoraRecibepr);
        TextView DirecPR=(TextView) findViewById(R.id.AdicionalPorEnvio);

        TextView CallePrin=(TextView) findViewById(R.id.CallePrincipalPr);
        TextView Calles=(TextView) findViewById(R.id.CalleSecPR);
        TextView Refer=(TextView) findViewById(R.id.ReferenciaPr);
        TextView DetalleUBICA=(TextView) findViewById(R.id.DetalleUBPR);
        TextView GloboTA=(TextView) findViewById(R.id.GloboOTG);
        TextView Detagg=(TextView) findViewById(R.id.Detagg);
        final TextView Precf=(TextView) findViewById(R.id.PrecioFinal);
        TextView Numerac=(TextView) findViewById(R.id.TxtNumeracion);
        TextView Especificaciontxa=(TextView) findViewById(R.id.TxtEspecificacionrp);
        final TextView textpreciofinal=(TextView) findViewById(R.id.textpreciofinal);
        final TextView descuentotxt=(TextView) findViewById(R.id.txtdescuento);
        final Button btndescuento=(Button)findViewById(R.id.btnDescuento);
        SharedPreferences preferences2=getSharedPreferences("login", Context.MODE_PRIVATE);
        String c=preferences2.getString("mailus","No");
        String t=preferences2.getString("telefonous","No");



        NA.setText("ARREGLO: "+pedido.getNombre_arreglo());
        PA.setText("PRECIO ARREGLO: "+pedido.getPrecio_arreglo()+" USD");
        NP.setText("RECIBE: "+pedido.getNombre_recibe());
        TP.setText("TELEFONO: "+pedido.getTelefono_recibe());
        FecR.setText("FECHA ENTREGA: "+pedido.getDia_entrega());
        HoraRec.setText("FRANJA HORARIA: "+pedido.getFranja_horaria());
        DirecPR.setText("COSTO DE ENVIO: "+pedido.getPrecio_viaje());

        CallePrin.setText("CALLLE PRIN: "+pedido.getCalle_principal());
        Calles.setText("CALLE SEC: "+pedido.getCalle_secundaria());
        Refer.setText("REFERENCIA: "+pedido.getReferencia());
        DetalleUBICA.setText("DETALLE EXTRA: "+pedido.getDetalle_ubicacion());
        GloboTA.setText("MOTIVO: "+pedido.getMotivo());
        Detagg.setText("EN TARJETA: "+pedido.getDetaAgg());
        Numerac.setText("NUMERACION O PISO :"+pedido.getNumeracion());
        Especificaciontxa.setText("");
        sectortxt.setText("SECTOR: "+pedido.getSector());



        try{
             precio = Integer.parseInt(pedido.getPrecio_arreglo());
             preciof=Integer.parseInt(pedido.getPrecio_viaje());
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }




        preciototale=precio+preciof;
        textpreciofinal.setText("PRECIO TOTAL: "+String.valueOf(preciototale)+" USD");


        btndescuento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String desct=descuentotxt.getText().toString().trim();

                if(desct.equals("Descuento de 10%")){
                    dac=precio*10;
                    dac=dac/100;
                    precio=precio-dac;
                    preciototale=precio+preciof;
                    textpreciofinal.setText("PRECIO TOTAL: "+String.valueOf(preciototale)+" USD");
                    btndescuento.setVisibility(View.GONE);
                    descuentotxt.setVisibility(View.GONE);

                    preciototal2= String.valueOf(preciototale);
                }


                if(desct.equals("Descuento de 20%")){
                    dac=precio*20;
                    dac=dac/100;
                    precio=precio-dac;
                    preciototale=precio+preciof;
                    textpreciofinal.setText("PRECIO TOTAL: "+String.valueOf(preciototale)+" USD");
                    btndescuento.setVisibility(View.GONE);
                    descuentotxt.setVisibility(View.GONE);

                    preciototal2= String.valueOf(preciototale);
                }

                if(desct.equals("Descuento de 30%")){
dac=precio*30;
                    dac=dac/100;
                    precio=precio-dac;
                    preciototale=precio+preciof;
                    textpreciofinal.setText("PRECIO TOTAL: "+String.valueOf(preciototale)+" USD");
                    btndescuento.setVisibility(View.GONE);
                    descuentotxt.setVisibility(View.GONE);

   preciototal2= String.valueOf(preciototale);
                }

                if(desct.equals("Descuento de 50%")){
                    dac=precio*50;
                    dac=dac/100;
                    precio=precio-dac;
                    preciototale=precio+preciof;
                    textpreciofinal.setText("PRECIO TOTAL: "+String.valueOf(preciototale)+" USD");
                    btndescuento.setVisibility(View.GONE);
                    descuentotxt.setVisibility(View.GONE);
       preciototal2= String.valueOf(preciototale);


                }
            }
        });




        final Button btnb = (Button) findViewById(R.id.btnPagar);
        final Button btnb2 = (Button) findViewById(R.id.btnpagar2);
        final Button btnb3=(Button)findViewById(R.id.btnpagar3);


        preciototal2= String.valueOf(preciototale);


        btnb.setOnClickListener(new View.OnClickListener() {




            @Override
            public void onClick(View v) {

                Intent d = new Intent(ResumenPedidoActivity.this, PagoActivity.class);
                String tp="t";
                pedido.setPrecioTotal(String.valueOf(preciototale));
                pedido.setTipo(tp);
                d.putExtra("PEDIDO",pedido);
                startActivity(d);

            }
        }); //boton del clicklister

        btnb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent d = new Intent(ResumenPedidoActivity.this, PagoActivity.class);
                String tp="p";
                pedido.setPrecioTotal(String.valueOf(preciototale));
                pedido.setTipo(tp);
                d.putExtra("PEDIDO",pedido);
                startActivity(d);
            }
        });

        btnb3.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent d = new Intent(ResumenPedidoActivity.this, PagoActivity.class);
                String tp="pp";
                pedido.setPrecioTotal(String.valueOf(preciototale));
                pedido.setTipo(tp);
                d.putExtra("PEDIDO",pedido);
                startActivity(d);
                startActivity(d);

            }
        }); //boton del clicklister
//
//
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

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
