package com.example.deber_cuatro.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.deber_cuatro.Model.Productos;
import com.example.deber_cuatro.R;
import com.example.deber_cuatro.ResultadoActivity;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorProductos extends RecyclerView.Adapter<AdaptadorProductos.ProductosViewHolder> {
    private Context ctx;
    private List<Productos> lstProducto;
    public AdaptadorProductos(Context CTX, List<Productos> productos) {
        this.lstProducto = productos;
        ctx = CTX;
    }
    @NonNull
    @Override
    public ProductosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.it_productos, parent, false);
        return new ProductosViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ProductosViewHolder holder, int position) {

        Productos productos = lstProducto.get(position);
        holder.txtNombre.setText(productos.getTitle());
        holder.txtDescripcion.setText(productos.getDescription());
        Glide.with(ctx)
                .load(productos.getThumbnail())
                .into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> images = productos.getImages();
                String tituloProducto = productos.getTitle();
                Intent intent = new Intent(ctx, ResultadoActivity.class);
                intent.putStringArrayListExtra("images", new ArrayList<>(images));
                intent.putExtra("title", tituloProducto);
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstProducto.size();
    }

    static class ProductosViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre, txtDescripcion;
        ImageView imageView;

        public ProductosViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtDescripcion = itemView.findViewById(R.id.txtDescripcion);
            imageView = itemView.findViewById(R.id.imgAvatar);
        }
    }
}
