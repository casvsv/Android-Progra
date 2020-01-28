package com.example.progra.vistas.fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.progra.R;
import com.example.progra.modelo.Artista;
import com.example.progra.vistas.adapter.ArtistaAdapter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FrgSD.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FrgSD#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FrgSD extends Fragment implements View.OnClickListener{
    EditText cajaNombres, cajaApellidos,cajaNombreArtistico;
    Button botonEscribir, botonLeer;
    RecyclerView reciclerSD;
    ArtistaAdapter adapter;
    List<Artista> listaArtistas;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FrgSD() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FrgSD.
     */
    // TODO: Rename and change types and number of parameters
    public static FrgSD newInstance(String param1, String param2) {
        FrgSD fragment = new FrgSD();
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
        return inflater.inflate(R.layout.activity_actividad_archivos_sd, container, false);
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cajaNombres = (EditText) getActivity().findViewById(R.id.txtNombresSD);
        cajaApellidos= (EditText) getActivity().findViewById(R.id.txtApellidosSD);
        cajaNombreArtistico=getActivity().findViewById(R.id.txtNombreArtisticoSD);
        botonEscribir = (Button) getActivity().findViewById(R.id.btnAgregarSD);
        botonLeer = (Button) getActivity().findViewById(R.id.btnListarSD);
        reciclerSD = (RecyclerView) getActivity().findViewById(R.id.recyclerViewSD);
        botonEscribir.setOnClickListener(this);
        botonLeer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAgregarSD:
                BufferedWriter bufferedWriter = null;
                FileWriter fileWriter = null;
                try {
                    File file = Environment.getExternalStorageDirectory(); // ruta del SD
                    File ruta = new File(file.getAbsoluteFile(), "archivoSD2.txt");
                    fileWriter = new FileWriter(ruta.getAbsoluteFile(), true);
                    bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(cajaNombres.getText().toString()+","+cajaApellidos.getText().toString()+","+cajaNombreArtistico.getText().toString()+";");
                    bufferedWriter.close();
                    Toast.makeText(getActivity(), "Se ha guardado correctamente", Toast.LENGTH_SHORT).show();
                    limpiar();
                }catch (Exception ex){
                    Log.e("Error SD", ex.getMessage());
                }
                break;
            case R.id.btnListarSD:
                try {
                    File ruta = Environment.getExternalStorageDirectory(); // ruta del SD
                    File file = new File(ruta.getAbsoluteFile(), "archivoSD2.txt");
                    listaArtistas = new ArrayList<Artista>();
                    BufferedReader lector = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                    String lineas = lector.readLine();
                    String[] artis = lineas.split(";");
                    String[] arrayArchivo;
                    for (int i = 0; i < artis.length; i++) {
                        arrayArchivo = artis[i].split(",");
                        Artista artista = new Artista();
                        artista.setNombres(arrayArchivo[0]);
                        artista.setApellidos(arrayArchivo[1]);
                        artista.setNombreArtistico(arrayArchivo[2]);
                        listaArtistas.add(artista);
                    }
                    adapter = new ArtistaAdapter(listaArtistas);
                    reciclerSD.setLayoutManager(new LinearLayoutManager(getContext()));
                    reciclerSD.setAdapter(adapter);
                    lector.close();
                }catch (Exception ex){
                    Log.e("Error SD", ex.getMessage());
                }
                break;
        }
    }


    private void limpiar(){
        cajaNombres.setText("");
        cajaApellidos.setText("");
        cajaNombreArtistico.setText("");
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
