package com.example.progra.vistas.fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.progra.R;
import com.example.progra.modelo.Artista;
import com.example.progra.vistas.adapter.ArtistaAdapter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FrgProgramaArt.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FrgProgramaArt#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FrgProgramaArt extends Fragment implements View.OnClickListener{
    TextView datos;
    Button boton;
    InputStream input;
    BufferedReader lector;
    ArtistaAdapter adapter;
    RecyclerView recyclerArtista;
    List<Artista> listaArtista;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FrgProgramaArt() {
        // Required empty public constructor
    }

    public static FrgProgramaArt newInstance(String param1, String param2) {
        FrgProgramaArt fragment = new FrgProgramaArt();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_actividad_memoria_programa, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.btnMemoriaPrograma):
                try {
                    listaArtista = new ArrayList<Artista>();
                    String cadena = lector.readLine();
                    String[] artis = cadena.split(";");
                    String[] arrayArchivo;
                    for (int i = 0; i < artis.length; i++) {
                        arrayArchivo = artis[i].split(",");
                        Artista artista = new Artista();
                        artista.setNombres(arrayArchivo[0]);
                        artista.setApellidos(arrayArchivo[1]);
                        artista.setNombreArtistico(arrayArchivo[2]);
                        //artista.setImgfoto(Integer.parseInt(arrayArchivo[3]));
                        listaArtista.add(artista);
                    }
                    adapter = new ArtistaAdapter(listaArtista);
                    recyclerArtista.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerArtista.setAdapter(adapter);
                    lector.close();
                }catch (Exception ex){
                    Log.e("error: ", ex.getMessage());
                }
                break;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        input = getResources().openRawResource(R.raw.archivo_raw);
        lector = new BufferedReader(new InputStreamReader(input));
        datos = (TextView) getActivity().findViewById(R.id.lblMemoriaPrograma);
        boton = (Button) getActivity().findViewById(R.id.btnMemoriaPrograma);
        recyclerArtista = (RecyclerView) getActivity().findViewById(R.id.RecyclerMemoriaPrograma);
        boton.setOnClickListener(this);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
