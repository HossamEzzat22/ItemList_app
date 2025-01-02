package com.example.itemlist_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import models.Item;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    FloatingActionButton fab;
    LinearLayout itemListContainer;
    TextView emptyListTextView;
    ArrayList<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        itemListContainer = findViewById(R.id.itemListContainer);
        emptyListTextView = findViewById(R.id.emptyList);
        itemList = new ArrayList<>();

        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);

        // Set toolbar
        setSupportActionBar(toolbar);

        // Setup FAB to open AddItemActivity
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
                startActivityForResult(intent, 1);  // Start AddItemActivity and expect a result
            }
        });

        // Check if there are any items added already
        if (itemList.isEmpty()) {
            emptyListTextView.setVisibility(View.VISIBLE);
        } else {
            emptyListTextView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            String name = data.getStringExtra("input_value_name");
            String price = data.getStringExtra("input_value_price");
            String id = data.getStringExtra("input_value_id");

            if (name != null && price != null && id != null) {
                Item newItem = new Item(name, price, id);
                itemList.add(newItem);
                addItemToView(newItem); // Add the item to the container

                // Hide the empty list message if items are added
                emptyListTextView.setVisibility(View.GONE);
            }
        }
    }

    private void addItemToView(Item item) {
        // Inflate the item card layout and set data
        View itemView = LayoutInflater.from(this).inflate(R.layout.item_card, itemListContainer, false);

        TextView itemName = itemView.findViewById(R.id.tvItemName);
        TextView itemPrice = itemView.findViewById(R.id.tvItemPrice);
        TextView itemId = itemView.findViewById(R.id.tvItemId);

        itemName.setText(item.getName());
        itemPrice.setText(item.getPrice());
        itemId.setText(item.getId());

        // Add the item card to the LinearLayout
        itemListContainer.addView(itemView);
    }
}
