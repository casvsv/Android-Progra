package com.example.progra.vistas.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.progra.R;
import com.example.progra.modelo.Artista;

import java.util.List;

public class ArtistaAdapter extends RecyclerView.Adapter<ArtistaAdapter.ViewHolderArtista> implements View.OnClickListener {

    List<Artista> lista;
    // la lista va a tener objetos de la clase artista
    public ArtistaAdapter(List<Artista> lista){
        this.lista = lista;
    }
    private View.OnClickListener botonClick;

    // inflate sirve para cargar las vistas
    //se carga la vista
    @Override
    public ViewHolderArtista onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_artista, null);
        view.setOnClickListener(this);
        return new ViewHolderArtista(view);
    }
    //se cargar los datos de la vista
    @Override
    public void onBindViewHolder(ViewHolderArtista viewHolderArtista, int pos) {
        viewHolderArtista.datoNombres.setText(lista.get(pos).getNombres());
        viewHolderArtista.datoNombreArt.setText(lista.get(pos).getNombreArtistico());
        viewHolderArtista.datoApellidos.setText(lista.get(pos).getApellidos());
        if (lista.get(pos).getPathFoto() != null) {
            viewHolderArtista.datoFoto.setImageURI(Uri.parse(lista.get(pos).getPathFoto()));
        } else {
            viewHolderArtista.datoFoto.setImageResource(lista.get(pos).getImgfoto());
        }
    }

    //es para saber cuantos items va a tener la lista
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

    public class ViewHolderArtista extends RecyclerView.ViewHolder{
        TextView datoNombres;
        TextView datoNombreArt;
        TextView datoApellidos;
        ImageView datoFoto;
        ImageView datopathFoto;
        public ViewHolderArtista(View itemView) {
            super(itemView);
            datoNombres = itemView.findViewById(R.id.lblNombresArtista);
            datoApellidos = itemView.findViewById(R.id.lblApellidoArtista);
            datoNombreArt= itemView.findViewById(R.id.lblNombreArtistico);
            datoFoto = itemView.findViewById((R.id.ImgFotoArtista));


        }
    }
}
