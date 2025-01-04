package com.example.itemlist_app;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import adapters.CustomAdapter;
import models.Item;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ArrayList<Item> itemList;
    CustomAdapter customAdapter;

    private final ActivityResultLauncher<Intent> addItemLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String name = result.getData().getStringExtra("new_item_name");
                    String price = result.getData().getStringExtra("new_item_price");
                    String id = String.valueOf(itemList.size() + 1);  // Assign ID based on list size (1-based index)
                    itemList.add(new Item(name, price, id));
                    customAdapter.notifyDataSetChanged();
                    updateEmptyListMessage();
                }
            }
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize item list and adapter
        itemList = new ArrayList<>();
        ListView listView = findViewById(R.id.customlistview);
        customAdapter = new CustomAdapter(MainActivity.this, itemList);
        listView.setAdapter(customAdapter);  // This line was missing

        listView.setOnItemClickListener(this);

        // Set up Floating Action Button (FAB)
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            // Open AddItemActivity to add a new item
            Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
            addItemLauncher.launch(intent);
        });

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Item list = itemList.get(position);
        Toast.makeText(MainActivity.this, "item name" + list.getName(), Toast.LENGTH_SHORT).show();
    }
    private void updateEmptyListMessage() {
        TextView emptyListText = findViewById(R.id.emptyList);
        if (itemList.isEmpty()) {
            emptyListText.setVisibility(View.VISIBLE); // Show "empty list" message
        } else {
            emptyListText.setVisibility(View.GONE); // Hide "empty list" message
        }
    }
}