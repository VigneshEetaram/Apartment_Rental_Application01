package com.example.apartment_rental_application;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AdAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView prdName, prdPrice;
    ImageView imgProduct;
    itemCLickListener listener;
    CardView cardView;
    public AdAdapter(@NonNull View itemView) {
        super(itemView);

        imgProduct = (ImageView) itemView.findViewById(R.id.prdImage);
        prdName = (TextView) itemView.findViewById(R.id.txtPrdItemName);
        prdPrice = (TextView) itemView.findViewById(R.id.txtPrdItemPrice);
        cardView = (CardView)itemView.findViewById(R.id.cardView1);
    }

    @Override
    public void onClick(View view) {
        listener.onClick(view, getAdapterPosition(), false);
    }
}
