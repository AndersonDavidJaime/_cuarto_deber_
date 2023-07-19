package com.example.deber_cuatro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ResultadoActivity extends AppCompatActivity {

    private ImageView imgThumbnail;
    private RecyclerView lstResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        imgThumbnail = findViewById(R.id.imgThumbnail);
        lstResultado = findViewById(R.id.lstResultado);
        String tituloProducto = getIntent().getStringExtra("title");
        TextView txtTitulo = findViewById(R.id.txtTitulo);
        txtTitulo.setText(tituloProducto);
        List<String> images = getIntent().getStringArrayListExtra("images");
        if (images != null && !images.isEmpty()) {
            String thumbnailUrl = images.get(0);
            Glide.with(this)
                    .load(thumbnailUrl)
                    .into(imgThumbnail);
        }
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 2);
        lstResultado.setLayoutManager(layoutManager);
        ImagenesAdapter adapter = new ImagenesAdapter(images);
        lstResultado.setAdapter(adapter);
    }
    private class ImagenesAdapter extends RecyclerView.Adapter<ImagenesAdapter.ImagenViewHolder> {

        private List<String> images;
        public ImagenesAdapter(List<String> images) {
            this.images = images;
        }

        @NonNull
        @Override
        public ImagenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.item_imagen, parent, false);
            return new ImagenViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ImagenViewHolder holder, int position) {
            String imageUrl = images.get(position);
            Glide.with(holder.itemView.getContext())
                    .load(imageUrl)
                    .into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return images != null ? images.size() : 0;
        }

        class ImagenViewHolder extends RecyclerView.ViewHolder {

            ImageView imageView;

            public ImagenViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imageView);
            }
        }
    }
}
