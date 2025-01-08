package com.example.itemlist_app;

import static java.lang.Integer.MAX_VALUE;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import adapters.CustomAdapter;
import models.ItemEditListener;
import models.Item;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, ItemEditListener {
    private ArrayList<Item> itemList;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent serviceIntent = new Intent(getApplicationContext(), MyForegroundService.class);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            startService(serviceIntent);
//
//        }
//        foregroundServiceRunning();






        itemList = new ArrayList<>();
        ListView listView = findViewById(R.id.customlistview);
        customAdapter = new CustomAdapter(MainActivity.this, itemList);
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
            addItemLauncher.launch(intent);
        });

        updateEmptyListMessage();
    }
    public  boolean foregroundServiceRunning (){
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);

        for (ActivityManager.RunningServiceInfo service : activityManager.getRunningServices(MAX_VALUE)){
            if (MyForegroundService.class.getName().equals(service.service.getClassName())){
                return true;
            }
        }
        return false;
    }

    private final ActivityResultLauncher<Intent> addItemLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String name = result.getData().getStringExtra("new_item_name");
                    String price = result.getData().getStringExtra("new_item_price");
                    int position = result.getData().getIntExtra("item_position", -1);

                    if (position != -1) {
                        // Modify the item in the list
                        itemList.get(position).setName(name);
                        itemList.get(position).setPrice(price);
                        customAdapter.notifyDataSetChanged();
                    } else {
                        // Add new item if position is -1
                        int id = itemList.size()+1;
                        itemList.add(new Item(name, price, String.valueOf(id)));
                        customAdapter.notifyDataSetChanged();
                    }

                    updateEmptyListMessage();
                }
            }
    );

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Item list = itemList.get(position);
        Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
        intent.putExtra("edit_item_name", list.getName());
        intent.putExtra("edit_item_price", list.getPrice());
        intent.putExtra("item_position", position);
        addItemLauncher.launch(intent);
    }

    @Override
    public void onEditItem(int position, Item item) {
        Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
        intent.putExtra("edit_item_name", item.getName());
        intent.putExtra("edit_item_price", item.getPrice());
        intent.putExtra("item_position", position);
        addItemLauncher.launch(intent);
    }

    public void updateEmptyListMessage() {
        TextView emptyListText = findViewById(R.id.emptyList);
        if (itemList.isEmpty()) {
            emptyListText.setVisibility(View.VISIBLE);
        } else {
            emptyListText.setVisibility(View.GONE);
        }
    }
}
