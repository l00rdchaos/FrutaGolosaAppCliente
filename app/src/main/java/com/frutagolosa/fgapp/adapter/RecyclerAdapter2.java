package com.frutagolosa.fgapp.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import com.frutagolosa.fgapp.R;
import com.frutagolosa.fgapp.model.Motorizado;

import java.util.List;

public class RecyclerAdapter2 extends RecyclerView.Adapter<RecyclerAdapter2.MyViewHolder> implements View.OnClickListener {

    List<Motorizado> contacts;
    private View.OnClickListener listener;

    public RecyclerAdapter2(List<Motorizado> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item2, parent, false);
        view.setOnClickListener(this);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.id.setText("ID de motorizado: "+contacts.get(position).getId());
        holder.telefono.setText("Telefono: "+contacts.get(position).getTelefono());
        holder.nombre.setText("Nombre: "+contacts.get(position).getNombre());
        holder.correo.setText("Correo: "+contacts.get(position).getCorreo());
        holder.ubicacion.setText("Ubicacion: "+contacts.get(position).getUbicacion());
        holder.coordenadas.setText("Coordenadas: "+contacts.get(position).getCoordenadas());
        Glide.with(holder.fotomotorizado.getContext()).load(contacts.get(position).getFoto()).into(holder.fotomotorizado);


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
        return contacts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id, telefono, nombre, correo, ubicacion,coordenadas;
        ImageView fotomotorizado;

        public MyViewHolder(View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.idtxt);
            telefono= itemView.findViewById(R.id.telefonotxt);
            nombre = itemView.findViewById(R.id.nombretxt);
            correo = itemView.findViewById(R.id.correotxt);
            ubicacion=itemView.findViewById(R.id.ubicaciontxt);
            coordenadas=itemView.findViewById(R.id.coordenadastxt);
            fotomotorizado=itemView.findViewById(R.id.fotomotorizadoimg);


        }
    }
}