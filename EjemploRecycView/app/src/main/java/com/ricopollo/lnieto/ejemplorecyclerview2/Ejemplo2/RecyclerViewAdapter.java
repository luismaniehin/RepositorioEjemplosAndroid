package com.ricopollo.lnieto.ejemplorecyclerview2.Ejemplo2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ricopollo.lnieto.ejemplorecyclerview2.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder2> {

    private ArrayList<String> datos;

    public class MyViewHolder2 extends RecyclerView.ViewHolder{
        private TextView titulo;
        RelativeLayout relativeLayout;

        public MyViewHolder2(View itemView){
            super(itemView);
            titulo = itemView.findViewById(R.id.txcTitulo);
        }
    }

    public RecyclerViewAdapter(ArrayList<String> datos){
        this.datos = datos;
    }

    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_row, parent, false);

        return new MyViewHolder2(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder, int position) {
        holder.titulo.setText(datos.get(position));
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void removeItem(int position){
        datos.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(String item, int position){
        datos.add(position, item);
        notifyItemInserted(position);
    }

    public ArrayList<String> getDatos(){
        return datos;
    }
}
