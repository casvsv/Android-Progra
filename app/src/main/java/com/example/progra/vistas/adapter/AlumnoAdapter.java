package com.example.progra.vistas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.progra.R;
import com.example.progra.modelo.Alumnos;

import java.util.List;

public class AlumnoAdapter extends RecyclerView.Adapter<AlumnoAdapter.ViewHolderAlumno> implements View.OnClickListener {

    List<Alumnos> lista;
    // la lista va a tener objetos de la clase artista
    public AlumnoAdapter(List<Alumnos> lista){
        this.lista = lista;
    }
    private View.OnClickListener botonClick;

    // inflate sirve para cargar las vistas
    //se carga la vista
    @Override
    public ViewHolderAlumno onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_alumno, null);
        view.setOnClickListener(this);
        return new ViewHolderAlumno(view);
    }
    //se cargar los datos de la vista
    @Override
    public void onBindViewHolder(ViewHolderAlumno viewHolderAlumno, int pos) {
        viewHolderAlumno.datoID.setText(""+lista.get(pos).getIdalumno());
        viewHolderAlumno.datoNombre.setText(lista.get(pos).getNombre());
        viewHolderAlumno.datoDireccion.setText(lista.get(pos).getDireccion());
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

    public class ViewHolderAlumno extends RecyclerView.ViewHolder{
        TextView datoID,datoNombre,datoDireccion;
        public ViewHolderAlumno(View itemView) {
            super(itemView);
            datoID = itemView.findViewById(R.id.lblSWAlumnoID);
            datoNombre = itemView.findViewById(R.id.lblSWAlumnoNombre);
            datoDireccion= itemView.findViewById(R.id.lblSWAlumnoDireccion);
        }
    }
}
