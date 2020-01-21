package com.example.progra.vistas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progra.R;
import com.example.progra.modelo.Reyes;

import java.util.List;

public class ReyesAdapter extends RecyclerView.Adapter<ReyesAdapter.ViewHolderGodos> implements View.OnClickListener {

    private View.OnClickListener clickDetalles;
    List<Reyes> lista;

    public ReyesAdapter(List<Reyes> lista) {
        this.lista = lista;
    }

    @Override
    public ReyesAdapter.ViewHolderGodos onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_reyes, null);
        view.setOnClickListener(this);
        return new ReyesAdapter.ViewHolderGodos(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderGodos viewHolderGodos, int i) {
        viewHolderGodos.datoNombre.setText(lista.get(i).getNombresReyes());
        viewHolderGodos.datoPeriodo.setText(lista.get(i).getPeriodo());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.clickDetalles = onClickListener;
    }

    @Override
    public void onClick(View v) {
        if (clickDetalles != null) {
            clickDetalles.onClick(v);
        }
    }


    public static class ViewHolderGodos extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView datoNombre;
        TextView datoPeriodo;

        public ViewHolderGodos(@NonNull View itemView) {
            super(itemView);
            datoNombre = itemView.findViewById(R.id.txtNombreReyes);
            datoPeriodo = itemView.findViewById(R.id.txtPeriodo);

        }

        @Override
        public void onClick(View v) {
            Toast.makeText(null, "Godo: ",
                    Toast.LENGTH_LONG).show();
        }
    }
}
