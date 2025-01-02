package com.example.itemlist_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import adapters.CustomAdapter;
import models.Item;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static final int ADD_ITEM_REQUEST = 1;
    private ArrayList<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize item list and adapter
        itemList = new ArrayList<>();
        ListView listView = findViewById(R.id.customlistview);
        CustomAdapter customAdapter= new CustomAdapter(MainActivity.this,itemList);
        // Set up RecyclerView
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(this);

        // Set up Floating Action Button (FAB)
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            // Open AddItemActivity to add a new item
            Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
            startActivityForResult(intent, ADD_ITEM_REQUEST);
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) { // Request code 1 corresponds to AddItemActivity
            // Retrieve the item details from the result Intent
            String itemName = data.getStringExtra("new_item_name");
            String itemPrice = data.getStringExtra("new_item_price");
            String itemId = String.valueOf(itemList.size() + 1); // Generate ID based on list size

            // Create a new Item object
            Item newItem = new Item(itemName, itemPrice, itemId);

            // Add the new item to the list and notify the adapter
            itemList.add(newItem);
            .notifyItemInserted(itemList.size() - 1);
        } else {
            Toast.makeText(this, "Item not added.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Item list= itemList.get(position);
        Toast.makeText(MainActivity.this,"item name"+list.getName(),Toast.LENGTH_SHORT).show();
    }
}
