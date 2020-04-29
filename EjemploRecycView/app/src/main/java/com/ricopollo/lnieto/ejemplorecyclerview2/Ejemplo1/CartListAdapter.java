package com.ricopollo.lnieto.ejemplorecyclerview2.Ejemplo1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.ricopollo.lnieto.ejemplorecyclerview2.R;

import java.util.List;

public class CartListAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<Item> cartList;

    public CartListAdapter(Context context, List<Item> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cart_list_item, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final Item item = cartList.get(i);

        myViewHolder.name.setText(item.getName());
        myViewHolder.description.setText(item.getDescription());
        myViewHolder.price.setText(item.getPrice() + " S/.");

        Glide.with(context)
                .load(item.getThumbnail())
                .into(myViewHolder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public void removeItem(int position) {
        cartList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(Item item, int position) {
        cartList.add(position, item);
        notifyItemInserted(position);
    }
}