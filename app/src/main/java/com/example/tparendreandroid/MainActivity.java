package com.example.tparendreandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener {
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Créer une liste d'objets Item
        itemList = new ArrayList<>();
        itemList.add(new Item(R.drawable.s1mple,0.86,"S1mple"));
        itemList.add(new Item(R.drawable.zywoo,0.8,"ZywOo"));
        itemList.add(new Item(R.drawable.twistzz,0.71, "Twistzz"));
        itemList.add(new Item(R.drawable.niko,0.8,"Niko"));
        itemList.add(new Item(R.drawable.ropz,0.72,"Ropz"));
        itemList.add(new Item(R.drawable.scream,1.09,"Scream"));
        itemList.add(new Item(R.drawable.kennys,0.58,"Kennys"));
        itemList.add(new Item(R.drawable.stewie2k,0.63,"Stewie2K"));

        // Initialiser le RecyclerView et l'adapter
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(itemList, this);
        recyclerView.setAdapter(adapter);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
            startActivityForResult(intent, 1);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            int imageResId = data.getIntExtra("imageResId", 0);
            double doubleValue = data.getDoubleExtra("doubleValue", 0);
            String stringValue = data.getStringExtra("stringValue");

            Log.d("MainActivity", "imageResId: " + imageResId);
            Log.d("MainActivity", "doubleValue: " + doubleValue);
            Log.d("MainActivity", "stringValue: " + stringValue);

            addData(imageResId, doubleValue, stringValue);
        }
    }


    @Override
    public void onItemClick(Item item) {
        Intent intent = new Intent(this, ItemDetailActivity.class);
        intent.putExtra("imageResId", item.getImageResId());
        intent.putExtra("doubleValue", item.getDoubleValue());
        intent.putExtra("stringValue", item.getStringValue());
        startActivity(intent);
    }

    public void addData(int imageResId, double doubleValue, String stringValue) {
        Item item = new Item(imageResId, doubleValue, stringValue);
        itemList.add(item);
        adapter.notifyDataSetChanged();
    }

    public void removeData(int position) {
        itemList.remove(position);
        adapter.notifyItemRemoved(position);
    }

    public void editData(int position, int imageResId, double doubleValue, String stringValue) {
        Item item = itemList.get(position);
        item.setImageResId(imageResId);
        item.setDoubleValue(doubleValue);
        item.setStringValue(stringValue);
        adapter.notifyItemChanged(position);
    }
}
