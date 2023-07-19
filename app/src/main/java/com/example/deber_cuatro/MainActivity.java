package com.example.deber_cuatro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.deber_cuatro.Adapter.AdaptadorProductos;
import com.example.deber_cuatro.Model.Productos;
import com.example.deber_cuatro.WebServices.Asynchtask;
import com.example.deber_cuatro.WebServices.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.lstProductos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Map<String, String> datos = new HashMap<>();
        WebService ws= new WebService("https://dummyjson.com/products",
                datos, MainActivity.this,MainActivity.this );
        ws.execute("GET");
    }


    @Override
    public void processFinish(String result) throws JSONException {
        ArrayList<Productos> lstProduct = new ArrayList<Productos>();
        try {
            JSONObject JSONlista = new JSONObject(result);
            JSONArray JSONlistaProductos = JSONlista.getJSONArray("products");
            lstProduct = Productos.JsonObjectsBuild(JSONlistaProductos);
            AdaptadorProductos adaptadorProductos= new AdaptadorProductos(this, lstProduct);
            recyclerView.setAdapter(adaptadorProductos);
        }
        catch (JSONException e){
            Toast.makeText(this.getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG);

        }
    }
}