package com.example.itemlist_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;

import models.Item;

public class AddItemActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextInputEditText editTextName ;
    TextInputEditText editTextPrice;
    EditText editId;
    String textName;
    String textPrice;
    int id;


    Button add;
    Button cancel;

    Intent intent;




    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        editTextName = (TextInputEditText) findViewById(R.id.itemName);
        editTextPrice = (TextInputEditText) findViewById(R.id.itemPrice);
        add = (Button) findViewById(R.id.add);
        cancel = (Button) findViewById(R.id.cancel);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textName = editTextName.getText().toString();
                textPrice = editTextPrice.getText().toString();

                if (textName.isEmpty() || textPrice.isEmpty()) {
                    Toast.makeText(AddItemActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create a new Item object and set its values
                Item newItem = new Item("", "", ""); // Temporary values
                newItem.setName(textName);
                newItem.setPrice(textPrice);
                newItem.setName(String.valueOf(id));

                // Send the data back to MainActivity
                Intent intent = new Intent(AddItemActivity.this,MainActivity.class);
                intent.putExtra("new_item_name", newItem.getName());
                intent.putExtra("new_item_price", newItem.getPrice());
                startActivity(intent);
                finish();
            }
        });


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
        // Handle action bar item clicks here.
//        int id = item.getItemId();
//
//        // Handle menu item clicks
//        if (id == R.id.mainMenu) {
//            Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show();
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }


}
