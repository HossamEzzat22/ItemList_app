package com.example.itemlist_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;

public class AddItemActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextInputEditText editTextName;
    TextInputEditText editTextPrice;
    Button add;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        toolbar = findViewById(R.id.toolbar);
        editTextName = findViewById(R.id.itemName);
        editTextPrice = findViewById(R.id.itemPrice);
        add = findViewById(R.id.add);
        cancel = findViewById(R.id.cancel);

        setSupportActionBar(toolbar);

        if (getIntent() != null && getIntent().hasExtra("edit_item_name") && getIntent().hasExtra("edit_item_price")) {
            String itemName = getIntent().getStringExtra("edit_item_name");
            String itemPrice = getIntent().getStringExtra("edit_item_price");

            editTextName.setText(itemName);
            editTextPrice.setText(itemPrice);

            int position = getIntent().getIntExtra("item_position", -1);

            add.setText("Save");
            add.setOnClickListener(v -> {
                String updatedName = editTextName.getText().toString();
                String updatedPrice = editTextPrice.getText().toString();

                if (updatedName.isEmpty() || updatedPrice.isEmpty()) {
                    Toast.makeText(AddItemActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent resultIntent = new Intent();
                resultIntent.putExtra("new_item_name", updatedName);
                resultIntent.putExtra("new_item_price", updatedPrice);
                resultIntent.putExtra("item_position", position);
                setResult(RESULT_OK, resultIntent);
                finish();
            });
        } else {
            add.setOnClickListener(v -> {
                String name = editTextName.getText().toString();
                String price = editTextPrice.getText().toString();

                if (name.isEmpty() || price.isEmpty()) {
                    Toast.makeText(AddItemActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                int id = getIntent().getIntExtra("new_item_id", -1);  // The ID passed as an index

                Intent resultIntent = new Intent();
                resultIntent.putExtra("new_item_name", name);
                resultIntent.putExtra("new_item_price", price);
                resultIntent.putExtra("new_item_id", String.valueOf(id));  // Pass index as ID
                setResult(RESULT_OK, resultIntent);
                finish();
            });
        }

        cancel.setOnClickListener(v -> finish());
    }
}
