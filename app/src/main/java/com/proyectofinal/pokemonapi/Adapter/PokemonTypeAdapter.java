package com.proyectofinal.pokemonapi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.proyectofinal.pokemonapi.Common.Common;
import com.proyectofinal.pokemonapi.Interface.IItemClickListener;
import com.proyectofinal.pokemonapi.R;
import com.robertlevonyan.views.chip.Chip;
import java.util.List;

public class PokemonTypeAdapter extends RecyclerView.Adapter<PokemonTypeAdapter.MyViewHolder> {

    Context context;
    List<String> typeList;

    public PokemonTypeAdapter(Context context, List<String> typeList) {
        this.context = context;
        this.typeList = typeList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.chip_item,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.chip.setText(typeList.get(position));
        holder.chip.setChipBackgroundColor(Common.getColorByType(typeList.get(position)));
        holder.setiItemClickListener(new IItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Evita el Crasheo de la app
            }
        });

    }

    @Override
    public int getItemCount() {
        return typeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

    Chip chip;
    IItemClickListener iItemClickListener;

        public void setiItemClickListener(IItemClickListener iItemClickListener) {
            this.iItemClickListener = iItemClickListener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            chip = (Chip)itemView.findViewById(R.id.chip);
            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iItemClickListener.onClick(v, getAdapterPosition());
                }
            });
        }
    }
}
