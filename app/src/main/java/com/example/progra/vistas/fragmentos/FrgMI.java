package com.example.progra.vistas.fragmentos;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;







import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.progra.R;
import com.example.progra.modelo.Artista;
import com.example.progra.vistas.adapter.ArtistaAdapter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FrgMI.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FrgMI#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FrgMI extends Fragment implements  View.OnClickListener {
    String informacion;
    Button botonguardar, botonBuscar, botonCargarImagen;
    EditText cajaNombres, cajaApellidos, cajaNombreArtistico;
    TextView datos, cajaFotoPath;
    RecyclerView recyclerArtistas;
    ArtistaAdapter adapter;
    List<Artista> listaArtistas;
    ImageView imagen;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FrgMI() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FrgMI.
     */
    // TODO: Rename and change types and number of parameters
    public static FrgMI newInstance(String param1, String param2) {
        FrgMI fragment = new FrgMI();
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
        return inflater.inflate(R.layout.activity_actividad_memoria_interna, container, false);
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imagen = (ImageView) getActivity().findViewById(R.id.imgFotoSeleccionada);
        botonguardar = (Button)getActivity().findViewById(R.id.btnGuardar);
        botonBuscar = (Button)getActivity().findViewById(R.id.btnBuscar);
        botonCargarImagen = (Button)getActivity().findViewById(R.id.btnCargarImg);

        cajaNombres = (EditText) getActivity().findViewById(R.id.txtNombres);
        cajaApellidos = (EditText) getActivity().findViewById(R.id.txtApellidos);
        cajaNombreArtistico = (EditText) getActivity().findViewById(R.id.txtNombreArtistico);
        datos = (TextView) getActivity().findViewById(R.id.lblDatosMI);
        recyclerArtistas = (RecyclerView)getActivity().findViewById(R.id.RecyclerListado);

        cajaFotoPath = (TextView) getActivity().findViewById(R.id.lblpathdeFoto);

        botonCargarImagen.setOnClickListener(this);
        botonguardar.setOnClickListener(this);
        botonBuscar.setOnClickListener(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCargarImg:
                cargarImg();
                break;
            case R.id.btnGuardar:
                try {
                    Artista artista = new Artista();
                    artista.setNombres(cajaNombres.getText().toString());
                    artista.setApellidos(cajaApellidos.getText().toString());
                    artista.setNombreArtistico(cajaNombreArtistico.getText().toString());
                    artista.setImgfoto(R.id.imgFotoSeleccionada);
                    artista.setPathFoto(cajaFotoPath.getText().toString());
                    OutputStreamWriter escritor = new OutputStreamWriter(getActivity().openFileOutput("archivo21.txt", Context.MODE_APPEND));
                    escritor.write(artista.getNombres() + "," + artista.getApellidos() + "," + artista.getNombreArtistico() + "," + artista.getPathFoto() + ","+ artista.getImgfoto() +";");
                    escritor.close();
                    Toast.makeText(getActivity(), "Se ha guardado correctamente", Toast.LENGTH_SHORT).show();
                    limpiar();
                } catch (Exception ex) {
                    Toast.makeText(getActivity(), "No se ha podido guardar", Toast.LENGTH_SHORT).show();
                    limpiar();
                }
                break;
            case R.id.btnBuscar:
                try {

                    listaArtistas = new ArrayList<Artista>();
                    BufferedReader lector = new BufferedReader(new InputStreamReader(getActivity().openFileInput("archivo21.txt")));
                    String lineas = lector.readLine();
                    String[] artis = lineas.split(";");
                    String[] arrayArchivo;
                    for (int i = 0; i < artis.length; i++) {
                        arrayArchivo = artis[i].split(",");
                        Artista artista = new Artista();
                        artista.setNombres(arrayArchivo[0]);
                        artista.setApellidos(arrayArchivo[1]);
                        artista.setNombreArtistico(arrayArchivo[2]);
                        artista.setPathFoto(Uri.parse(arrayArchivo[3]).toString());
                        listaArtistas.add(artista);
                    }
                    adapter = new ArtistaAdapter(listaArtistas);
                    recyclerArtistas.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter.setOnclickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cargarDialogo(v);
                        }
                    });
                    recyclerArtistas.setAdapter(adapter);
                    lector.close();
                } catch (Exception ex) {
                    Log.e("archivoMI", "error de lectura" + ex.getMessage());
                    break;

                }
        }

    }

    private void cargarDialogo(View view) {
        Dialog dlgDetalles = new Dialog(getContext());
        dlgDetalles.setContentView(R.layout.dlg_datos_artistas);
        TextView nombresDetalles = dlgDetalles.findViewById(R.id.lblDatos1);
        TextView apellidosDetalles = dlgDetalles.findViewById(R.id.lblDatos2);
        ImageView fotoDetalles= dlgDetalles.findViewById(R.id.imgDlgDetalles);
        nombresDetalles.setText("Nombres: "+ listaArtistas.get(recyclerArtistas.getChildAdapterPosition(view)).getNombres());
        apellidosDetalles.setText("Apellidos: "+ listaArtistas.get(recyclerArtistas.getChildAdapterPosition(view)).getApellidos());
        fotoDetalles.setImageURI(Uri.parse(listaArtistas.get(recyclerArtistas.getChildAdapterPosition(view)).getPathFoto()));
        dlgDetalles.show();
    }

    private void cargarImg() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccione la aplicacion"),10);
    }


    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            Uri path = data.getData();
            informacion = path.toString();
            imagen.setImageURI(path);
            cajaFotoPath.setText(informacion);
        }
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void limpiar(){
        cajaNombres.setText("");
        cajaApellidos.setText("");
        cajaNombreArtistico.setText("");
        cajaFotoPath.setText("");
    }
}
