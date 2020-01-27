package com.example.progra.vistas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progra.R;
import com.example.progra.modelo.Carro;

import java.util.List;

public class CarroAdapter extends RecyclerView.Adapter<CarroAdapter.ViewHolderCarro> implements View.OnClickListener {

    List<Carro> lista;
    // la lista va a tener objetos de la clase artista
    public CarroAdapter(List<Carro> lista){
        this.lista = lista;
    }
    private View.OnClickListener botonClick;

    // inflate sirve para cargar las vistas
    //se carga la vista
    @Override
    public ViewHolderCarro onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_carro, null);
        view.setOnClickListener(this);
        return new ViewHolderCarro(view);
    }
    //se cargar los datos de la vista
    @Override
    public void onBindViewHolder(ViewHolderCarro viewHolderCarro, int pos) {
        viewHolderCarro.datoPlaca.setText(lista.get(pos).getPlaca());
        viewHolderCarro.datoModelo.setText(lista.get(pos).getModelo());
        viewHolderCarro.datoMarca.setText(lista.get(pos).getMarca());
        viewHolderCarro.datoAnio.setText(""+lista.get(pos).getAnio());
    }

    //Es para saber cuantos items va a tener la lista
    @Override
    public int getItemCount() {
        return lista.size();
    }


    public void setOnclickListener(View.OnClickListener onclickListener){
        this.botonClick = onclickListener;
    }
    @Override
    public void onClick(View view) {
        if(botonClick!= null){
            botonClick.onClick(view);
        }
    }

    public class ViewHolderCarro extends RecyclerView.ViewHolder{
        TextView datoPlaca,datoModelo,datoMarca,datoAnio;
        public ViewHolderCarro(View itemView) {
            super(itemView);
            datoPlaca = itemView.findViewById(R.id.lblPlacaORM);
            datoModelo = itemView.findViewById(R.id.lblModeloORM);
            datoMarca= itemView.findViewById(R.id.lblMarcaORM);
            datoAnio = itemView.findViewById((R.id.lblAnioORM));
        }
    }
}

