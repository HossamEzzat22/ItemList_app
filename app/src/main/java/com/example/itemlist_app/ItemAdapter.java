//package com.example.itemlist_app;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
//import java.util.List;
//
//import models.Item;
//
//public class ItemAdapter extends ArrayAdapter {
//
//    private Context context;
//    private List<Item> items;
//
//    public ItemAdapter(Context context, List<Item> items) {
//        super(context, 0, items);
//        this.context = context;
//        this.items = items;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if (convertView == null) {
//            convertView = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false);
//        }
//
//        Item item = (Item) getItem(position);
//
//        TextView nameTextView = convertView.findViewById(R.id.tvItemName);
//        TextView priceTextView = convertView.findViewById(R.id.tvItemPrice);
//        TextView idTextView = convertView.findViewById(R.id.tvItemId);
//
//        nameTextView.setText(item.getName());
//        priceTextView.setText(item.getPrice());
//        idTextView.setText(item.getId());
//
//        return convertView;
//    }
//
//}
