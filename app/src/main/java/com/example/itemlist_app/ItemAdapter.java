package com.example.itemlist_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import java.util.List;

import models.Item;

public class ItemAdapter extends ArrayAdapter<Item> {

    public ItemAdapter(Context context, List<Item> items) {
        super(context, 0, items);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Get the item for this position
        Item item = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_card, parent, false);
        }

        // Lookup view for data population
        TextView tvItemName = convertView.findViewById(R.id.tvItemName);
        TextView tvItemPrice = convertView.findViewById(R.id.tvItemPrice);
        TextView tvItemId = convertView.findViewById(R.id.tvItemId);

        // Populate the data into the template view
        assert item != null;
        tvItemName.setText(item.getName());
        tvItemPrice.setText(item.getPrice());
        tvItemId.setText(item.getId());

        // Set up any button listeners (optional)


        // Return the completed view to render on screen
        return convertView;
    }
}
