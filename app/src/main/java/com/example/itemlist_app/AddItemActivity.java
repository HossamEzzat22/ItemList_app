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

public class AddItemActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText editTextName ;
    EditText editTextPrice;
    EditText editId;
    String textName;
    String textPrice;
    String id;


    Button add;
    Button cancel;

    Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        editTextName = (EditText) findViewById(R.id.itemName);
        editTextPrice = (EditText) findViewById(R.id.itemPrice);
        add = (Button) findViewById(R.id.add);
        cancel = (Button) findViewById(R.id.cancel);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textName=editTextName.getText().toString();
                textPrice=editTextPrice.getText().toString();
//                id = editId.getText().toString();

                // Pass the text to the next screen using Intent
                intent = new Intent(AddItemActivity.this, MainActivity.class);
                intent.putExtra("input_value_name",textName);
                intent.putExtra("input_value_price",textPrice);
//                intent.putExtra("input_value_id",id);
                startActivity(intent);
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
