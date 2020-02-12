package com.example.progra.vistas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.progra.R;
import com.example.progra.modelo.Usuario;

import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.ViewHolderUsuario> implements View.OnClickListener {

    List<Usuario> lista;
    // la lista va a tener objetos de la clase artista
    public UsuarioAdapter(List<Usuario> lista){
        this.lista = lista;
    }
    private View.OnClickListener botonClick;

    // inflate sirve para cargar las vistas
    //se carga la vista
    @Override
    public ViewHolderUsuario onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_usuario, null);
        view.setOnClickListener(this);
        return new ViewHolderUsuario(view);
    }
    //se cargar los datos de la vista
    @Override
    public void onBindViewHolder(ViewHolderUsuario viewHolderUsuario, int pos) {
        viewHolderUsuario.datoDocumento.setText(""+lista.get(pos).getDocumento());
        viewHolderUsuario.datoNombre.setText(lista.get(pos).getNombre());
        viewHolderUsuario.datoProfesion.setText(lista.get(pos).getProfesion());
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

    public class ViewHolderUsuario extends RecyclerView.ViewHolder{
        TextView datoDocumento,datoNombre,datoProfesion;
        public ViewHolderUsuario(View itemView) {
            super(itemView);
            datoDocumento = itemView.findViewById(R.id.lblUsuarioDocumento);
            datoNombre = itemView.findViewById(R.id.lblUsuarioNombre);
            datoProfesion= itemView.findViewById(R.id.lblUsuarioProfesion);
        }
    }
}


