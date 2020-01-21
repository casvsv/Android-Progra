package com.example.progra.vistas.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.example.progra.R;
import com.example.progra.modelo.Reyes;
import com.example.progra.vistas.adapter.ReyesAdapter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class ActividadMemoriaPrograma2 extends AppCompatActivity  {

    TextView datos;
    RecyclerView recycler;
    List<Reyes> listaReyes;
    ReyesAdapter reyesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_memoria_programa2);
        cargarComponentes();
        obtenerReyes();

    }

    private void cargarComponentes(){
        datos = findViewById(R.id.lblReyes);
        recycler = findViewById(R.id.recyclerReyes);
    }

    private void obtenerReyes(){
        listaReyes = new ArrayList<Reyes>();
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.reyes_raw);
            DocumentBuilderFactory  factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);
            document.getDocumentElement().normalize();
            NodeList  Listgodos= document.getElementsByTagName("godo");
            datos.setText("Número de reyes: " + Listgodos.getLength());
            for (int i = 0;i<Listgodos.getLength(); i ++){
                Node  nod = Listgodos.item(i);
                if (nod.getNodeType() == Node.ELEMENT_NODE ) {
                    Element element = (Element) nod;
                    String nombres = element.getElementsByTagName("nombre").item(0).getTextContent();
                    String periodo = element.getElementsByTagName("periodo").item(0).getTextContent();

                    Reyes reyes  = new Reyes();
                    reyes.setNombresReyes(nombres);
                    reyes.setPeriodo(periodo);
                    listaReyes.add(reyes);
                }
            }
            recycler.setLayoutManager(new LinearLayoutManager(this));
            reyesAdapter = new ReyesAdapter(listaReyes);
            recycler.setAdapter(reyesAdapter);

        }catch (Exception ec){
            Log.e("Error","Datos de la lista" + listaReyes);
        }
    }
}
