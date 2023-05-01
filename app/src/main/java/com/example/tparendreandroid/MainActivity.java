package com.example.tparendreandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyAdapter.ItemClickListener {
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Créer une liste d'objets Item
        itemList = new ArrayList<>();
        itemList.add(new Item("android.resource://com.example.tparendreandroid/drawable/s1mple", 0.86, "S1mple"));
        itemList.add(new Item("android.resource://com.example.tparendreandroid/drawable/zywoo", 0.8, "ZywOo"));
        itemList.add(new Item("android.resource://com.example.tparendreandroid/drawable/twistzz", 0.71, "Twistzz"));
        itemList.add(new Item("android.resource://com.example.tparendreandroid/drawable/niko", 0.8, "Niko"));
        itemList.add(new Item("android.resource://com.example.tparendreandroid/drawable/ropz", 0.72, "Ropz"));
        itemList.add(new Item("android.resource://com.example.tparendreandroid/drawable/scream", 1.09, "Scream"));
        itemList.add(new Item("android.resource://com.example.tparendreandroid/drawable/kennys", 0.58, "Kennys"));
        itemList.add(new Item("android.resource://com.example.tparendreandroid/drawable/stewie2k", 0.63, "Stewie2K"));

        // Initialiser le RecyclerView et l'adapter
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(itemList, this);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

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
            String imageUriString = data.getStringExtra("imageUriString");
            double doubleValue = data.getDoubleExtra("doubleValue", 0);
            String stringValue = data.getStringExtra("stringValue");

            addData(imageUriString, doubleValue, stringValue);
        }
    }

    @Override
    public void onItemClick(Item item, int position) {
        Intent intent = new Intent(MainActivity.this, ItemDetailActivity.class);
        intent.putExtra("imageUriString", item.getImageUriString());
        intent.putExtra("doubleValue", item.getDoubleValue());
        intent.putExtra("stringValue", item.getStringValue());
        startActivityForResult(intent, position);
    }


    public void addData(String imageUriString, double doubleValue, String stringValue) {
        Item item = new Item(imageUriString, doubleValue, stringValue);
        itemList.add(item);
        adapter.notifyDataSetChanged();
    }

    public void removeData(int position) {
        itemList.remove(position);
        adapter.notifyItemRemoved(position);
    }

    public void editData(int position, String imageUriString, double doubleValue, String stringValue) {
        Item item = itemList.get(position);
        item.setImageUriString(imageUriString);
        item.setDoubleValue(doubleValue);
        item.setStringValue(stringValue);
        adapter.notifyItemChanged(position);
    }
}
