package com.frutagolosa.fgapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class cerrar extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cerrar);
    this.finish();
  }
}
