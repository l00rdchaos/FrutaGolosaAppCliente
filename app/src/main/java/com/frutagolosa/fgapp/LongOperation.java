package com.frutagolosa.fgapp;


import android.os.AsyncTask;


import android.util.Log;
import android.widget.Toast;


/**


 * Created by GsolC on 2/24/2017.


 */





public class LongOperation extends AsyncTask<Void, Void, String> {
  EnvCodTransfActivity En= new EnvCodTransfActivity();
  final String a= En.Asunto;

  @Override


  protected String doInBackground(Void... params) {

    try {

      GMailSender sender = new GMailSender("frutagolosa@gmail.com", "fruta0210");


      sender.sendMail("Pedido App",


              a, "frutagolosa@gmail.com",



              "info@frutagolosa.com,info@frutagolosa.com");




    } catch (Exception e) {


      Log.e("error", e.getMessage(), e);
      Toast.makeText(En, "no se envio correo", Toast.LENGTH_SHORT).show();

      return "Email Not Sent";


    }


    return "Email Sent";


  }


  @Override


  protected void onPostExecute(String result) {


    Log.e("LongOperation", result + "");


  }


  @Override


  protected void onPreExecute() {


  }


  @Override


  protected void onProgressUpdate(Void... values) {


  }


}