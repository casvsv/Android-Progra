package com.example.progra.vistas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.progra.R;
import com.example.progra.modelo.Clima;

import java.util.List;

public class ClimaAdapter extends RecyclerView.Adapter<ClimaAdapter.ViewHolderClima> implements View.OnClickListener {

    List<Clima> lista;
    public ClimaAdapter(List<Clima> lista){
        this.lista = lista;
    }
    private View.OnClickListener botonClick;

    // inflate sirve para cargar las vistas
    //se carga la vista
    @Override
    public ViewHolderClima onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_clima, null);
        view.setOnClickListener(this);
        return new ViewHolderClima(view);
    }
    //se cargar los datos de la vista
    @Override
    public void onBindViewHolder(ViewHolderClima viewHolderClima, int pos) {
        viewHolderClima.lon.setText(""+lista.get(pos).getLon());
        viewHolderClima.lat.setText(""+lista.get(pos).getLat());
        viewHolderClima.weatherid.setText(""+lista.get(pos).getId_clima());
        viewHolderClima.weathermain.setText(""+lista.get(pos).getMain());
        viewHolderClima.weatherdescription.setText(""+lista.get(pos).getDescription());
        viewHolderClima.weathericon.setText(""+lista.get(pos).getIcon());
        viewHolderClima.base.setText(""+lista.get(pos).getBase());
        viewHolderClima.maintemp.setText(""+lista.get(pos).getTemp());
        viewHolderClima.mainpressure.setText(""+lista.get(pos).getPressure());
        viewHolderClima.mainhumidity.setText(""+lista.get(pos).getHumidity());
        viewHolderClima.maintempmin.setText(""+lista.get(pos).getTemp_min());
        viewHolderClima.maintempmax.setText(""+lista.get(pos).getTemp_max());
        viewHolderClima.visibility.setText(""+lista.get(pos).getVisibility());
        viewHolderClima.windspeed.setText(""+lista.get(pos).getSpeed());
        viewHolderClima.winddeg.setText(""+lista.get(pos).getDeg());
        viewHolderClima.cloudsall.setText(""+lista.get(pos).getAll());
        viewHolderClima.dt.setText(""+lista.get(pos).getDt());
        viewHolderClima.systype.setText(""+lista.get(pos).getType());
        viewHolderClima.sysid.setText(""+lista.get(pos).getId_sys());
        viewHolderClima.sysmessage.setText(""+lista.get(pos).getMessage());
        viewHolderClima.syscountry.setText(""+lista.get(pos).getCountry());
        viewHolderClima.syssunrise.setText(""+lista.get(pos).getSunrise());
        viewHolderClima.syssunset.setText(""+lista.get(pos).getSunset());
        viewHolderClima.id.setText(""+lista.get(pos).getId());
        viewHolderClima.name.setText(""+lista.get(pos).getName());
        viewHolderClima.cod.setText(""+lista.get(pos).getCod());
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

    public class ViewHolderClima extends RecyclerView.ViewHolder{
        TextView lon,lat,weatherid,weathermain,weatherdescription,weathericon,base,maintemp,mainpressure,mainhumidity,maintempmin,maintempmax,visibility,windspeed,winddeg,cloudsall,dt,systype,sysid,sysmessage,syscountry,syssunrise,syssunset,id,name,cod;
        public ViewHolderClima(View itemView) {
            super(itemView);

            lon= itemView.findViewById(R.id.lblSWClimaLon);
            lat= itemView.findViewById(R.id.lblSWClimaLat);
            weatherid= itemView.findViewById(R.id.lblSWClimaClimaID);
            weathermain= itemView.findViewById(R.id.lblSWClimaClimaMain);
            weatherdescription= itemView.findViewById(R.id.lblSWClimaClimaIDecription);
            weathericon= itemView.findViewById(R.id.lblSWClimaClimaIcon);
            base= itemView.findViewById(R.id.lblSWClimaBase);
            maintemp= itemView.findViewById(R.id.lblSWClimaMainTemp);
            mainpressure= itemView.findViewById(R.id.lblSWClimaMainPressure);
            mainhumidity= itemView.findViewById(R.id.lblSWClimaMainHumidity);
            maintempmin= itemView.findViewById(R.id.lblSWClimaMainTempMin);
            maintempmax= itemView.findViewById(R.id.lblSWClimaMainTempMax);
            visibility= itemView.findViewById(R.id.lblSWClimaMainVisibility);
            windspeed= itemView.findViewById(R.id.lblSWClimaWindSpeed);
            winddeg= itemView.findViewById(R.id.lblSWClimaWindDeg);
            cloudsall= itemView.findViewById(R.id.lblSWClimaCloudsAll);
            dt= itemView.findViewById(R.id.lblSWClimaDT);
            systype= itemView.findViewById(R.id.lblSWClimaSysType);
            sysid= itemView.findViewById(R.id.lblSWClimaSysID);
            sysmessage= itemView.findViewById(R.id.lblSWClimaSysMessage);
            syscountry= itemView.findViewById(R.id.lblSWClimaSysCountry);
            syssunrise= itemView.findViewById(R.id.lblSWClimaSysSunrise);
            syssunset= itemView.findViewById(R.id.lblSWClimaSysSunset);
            id= itemView.findViewById(R.id.lblSWClimaID);
            name= itemView.findViewById(R.id.lblSWClimaName);
            cod= itemView.findViewById(R.id.lblSWClimaCod);
        }
    }
}

