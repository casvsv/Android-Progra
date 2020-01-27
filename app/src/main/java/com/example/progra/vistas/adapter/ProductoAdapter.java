package com.example.progra.vistas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.progra.R;
import com.example.progra.modelo.Producto;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolderProducto> implements View.OnClickListener {

    List<Producto> lista;

    public ProductoAdapter(List<Producto> lista){
        this.lista = lista;
    }

    private View.OnClickListener botonClick;
    @NonNull
    @Override
    public ViewHolderProducto onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_producto, null);
        view.setOnClickListener(this);
        return new ProductoAdapter.ViewHolderProducto(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProducto viewHolderProducto, int position) {
        viewHolderProducto.datoCodigo.setText(""+lista.get(position).getCodigo());
        viewHolderProducto.datoDescripcion.setText(lista.get(position).getDescripcion());
        viewHolderProducto.datoPrecio.setText(""+ lista.get(position).getPrecio());
        viewHolderProducto.datoCantidad.setText(""+lista.get(position).getCantidad());

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
    public void setOnclickListener(View.OnClickListener onclickListener){
        this.botonClick = onclickListener;
    }

    @Override
    public void onClick(View v) {
        if(botonClick!= null){
            botonClick.onClick(v);
        }
    }

    public class ViewHolderProducto extends RecyclerView.ViewHolder {
        TextView datoCodigo, datoDescripcion, datoPrecio, datoCantidad;
        public ViewHolderProducto(@NonNull View itemView) {
            super(itemView);
            datoCodigo = itemView.findViewById(R.id.lblCodigoDB);
            datoDescripcion = itemView.findViewById(R.id.lblDescripcionDB);
            datoPrecio = itemView.findViewById(R.id.lblPrecioDB);
            datoCantidad = itemView.findViewById(R.id.lblCantidadDB);
        }
    }
}
