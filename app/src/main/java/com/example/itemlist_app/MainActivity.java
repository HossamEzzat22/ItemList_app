package com.example.itemlist_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    FloatingActionButton fab;
    TextView itemName;
    String recievedItemName;
    TextView itemPrice;
    String recievedItemPrice;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        itemName = (TextView) findViewById(R.id.tvItemName);
        itemPrice = (TextView) findViewById(R.id.tvItemPrice);
        fab = (FloatingActionButton) findViewById(R.id.fab);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
                startActivity(intent);
            }
        });
        recievedItemName= getIntent().getStringExtra("input_value_name");
        recievedItemPrice= getIntent().getStringExtra("input_value_price");
        itemName.setText(recievedItemName);
        itemPrice.setText(recievedItemPrice);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

//        switch (item.getItemId()) {
//            case R.id.settings :
//                startActivity(new Intent(this, AddItemActivity.class));
//                return true;
//            case R.id.logout:
//                startActivity(new Intent(this, LoginActivity.class));
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
        return false;
    }
}