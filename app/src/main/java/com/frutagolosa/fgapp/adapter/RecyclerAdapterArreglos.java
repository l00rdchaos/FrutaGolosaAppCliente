package com.frutagolosa.fgapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
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
import  com.frutagolosa.fgapp.R;

import com.frutagolosa.fgapp.model.Arreglos;

import java.util.List;

public class RecyclerAdapterArreglos extends RecyclerView.Adapter<RecyclerAdapterArreglos.MyViewHolder> implements View.OnClickListener {
    private static Context context;

    List<Arreglos> arreglos;

    private View.OnClickListener listener;
    public RecyclerAdapterArreglos(List<Arreglos> arreglos) {
        this.arreglos = arreglos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        view.setOnClickListener(this);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        String x;
        holder.cardView.setBackgroundColor(Color.parseColor("#3DE09ED8"));

        holder.txtInformacion.setText(arreglos.get(position).getValor());

        String a=arreglos.get(position).getNombre().substring(0,1);
        holder.etiNombre.setText(a.substring(1).toLowerCase());
        holder.etiNombre2.setText(a.toUpperCase()+arreglos.get(position).getNombre().substring(1));
        String ax=arreglos.get(position).getNombre().toLowerCase().replace(" ","").trim();

        Glide.with(holder.foto.getContext()).asBitmap().load("https://frutagolosa.com/FrutaGolosaApp/Administrador/images/" +ax+".jpg").transition(BitmapTransitionOptions.withCrossFade(1000)).diskCacheStrategy(DiskCacheStrategy.ALL).skipMemoryCache(true).transform(new CenterCrop(),new RoundedCorners(40)).apply(new RequestOptions().override(270,270)).into(holder.foto).waitForLayout();




    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);

        }
    }

    @Override
    public int getItemCount() {
        return arreglos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{


        CardView cardView;
        TextView etiNombre,etiNombre2,txtInformacion;
        ImageView foto;


        public MyViewHolder(View itemView) {
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