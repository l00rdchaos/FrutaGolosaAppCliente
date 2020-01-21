package com.frutagolosa.fgapp.adapter;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.frutagolosa.fgapp.R;

import java.io.File;
import java.util.ArrayList;

import com.frutagolosa.fgapp.PersonajeVo;



/**
 * Created by CHENAO on 13/07/2017.
 */

public class PersonajesAdapter extends RecyclerView.Adapter<PersonajesAdapter.PersonajeViewHolder>
        implements View.OnClickListener{

  ArrayList<PersonajeVo> listaPersonaje;

  private View.OnClickListener listener;
  public PersonajesAdapter(ArrayList<PersonajeVo> listaPersonaje) {
    this.listaPersonaje=listaPersonaje;
  }

  @Override
  public PersonajeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);
    view.setOnClickListener(this);



    return new PersonajeViewHolder(view);
  }

  @Override
  public void onBindViewHolder(PersonajeViewHolder holder, int position) {



    holder.cardView.setBackgroundColor(Color.parseColor("#3DE09ED8"));

    holder.txtInformacion.setText(listaPersonaje.get(position).getInfo());

    String a=listaPersonaje.get(position).getNombre().substring(0,1);
    holder.etiNombre.setText(listaPersonaje.get(position).getNombre().substring(1));
    holder.etiNombre2.setText(a.toUpperCase()+listaPersonaje.get(position).getNombre().substring(1));
    String ax=listaPersonaje.get(position).getNombre().toLowerCase().replace(" ","").trim();





   // ImageLoader.getInstance().displayImage("https://frutagolosa.com/FrutaGolosaApp/Administrador/images/" +ax+".jpg",holder.foto);


    Glide.with(holder.foto.getContext()).asBitmap().load("https://frutagolosa.com/FrutaGolosaApp/Administrador/images/" +ax+".jpg").transition(BitmapTransitionOptions.withCrossFade(1000)).placeholder(R.drawable.frutagolosados).diskCacheStrategy(DiskCacheStrategy.RESOURCE).skipMemoryCache(true).transform(new CenterCrop(),new RoundedCorners(40)).apply(new RequestOptions().override(300,300)).into(holder.foto).waitForLayout();



  }

  @Override
  public int getItemCount() {
    return listaPersonaje.size();
  }
  public void setOnClickListener(View.OnClickListener listener){
    this.listener=listener;
  }

  @Override
  public void onClick(View view) {
    if (listener!=null){
      listener.onClick(view);

    }
  }


  public class PersonajeViewHolder extends RecyclerView.ViewHolder {

    CardView cardView;
    TextView etiNombre,etiNombre2,txtInformacion;
    ImageView foto;


    public PersonajeViewHolder(View itemView) {
      super(itemView);
      cardView=(CardView) itemView.findViewById(R.id.idCardview);
      CountDownTimer timer;

      txtInformacion= (TextView) itemView.findViewById(R.id.idInfo);
      etiNombre=(TextView) itemView.findViewById(R.id.idNombre);
      etiNombre2=(TextView) itemView.findViewById(R.id.idNombre2);
      foto= (ImageView) itemView.findViewById(R.id.idImagen);

    }
  }




}