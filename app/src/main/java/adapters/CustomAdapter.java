package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.itemlist_app.MainActivity;
import com.example.itemlist_app.R;

import java.util.ArrayList;

import models.Item;

public class CustomAdapter extends BaseAdapter{

    private final Context context;
    private final ArrayList<Item> myItems;

    public CustomAdapter(MainActivity mainActivity, ArrayList<Item> myItems) {
        this.context = mainActivity;
        this.myItems = myItems;
    }


    @Override
    public int getCount() {
        return myItems.size();
    }

    @Override
    public Object getItem(int position) {
        return myItems.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holderView;

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_card,parent,false);
            holderView=new HolderView(convertView);
            convertView.setTag(holderView);
        }else {
            holderView=(HolderView) convertView.getTag();
        }
        Item list = myItems.get(position);
        holderView.textName.setText(list.getName());
        holderView.textPrice.setText(list.getPrice());
        holderView.textId.setText(list.getId()); // Setting the ID here


        return convertView;
    }

    public static class HolderView{
        private final TextView textName;
        private final TextView textPrice;
        private final TextView textId;
        public HolderView(View view) {
            textName = view.findViewById(R.id.tvItemName);
            textPrice = view.findViewById(R.id.tvItemPrice);
            textId = view.findViewById(R.id.tvItemId);
        }
    }
}