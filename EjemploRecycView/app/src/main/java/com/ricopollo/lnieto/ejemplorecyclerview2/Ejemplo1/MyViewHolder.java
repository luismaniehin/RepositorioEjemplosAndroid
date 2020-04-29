package com.ricopollo.lnieto.ejemplorecyclerview2.Ejemplo1;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ricopollo.lnieto.ejemplorecyclerview2.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView name, description, price;
    public ImageView thumbnail;
    public RelativeLayout viewBackground, viewForeground;

    public MyViewHolder(View view){
        super(view);
        name = view.findViewById(R.id.name);
        description = view.findViewById(R.id.description);
        price = view.findViewById(R.id.price);
        thumbnail = view.findViewById(R.id.thumbnail);
        viewBackground = view.findViewById(R.id.view_background);
        viewForeground = view.findViewById(R.id.view_foreground);
    }
}
