package com.example.tparendreandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class ItemDetailActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView doubleTextView;
    private TextView stringTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_layout_large);

        // Récupérer les données de l'intent
        Intent intent = getIntent();
        int imageResId = intent.getIntExtra("imageResId", 0);
        double doubleValue = intent.getDoubleExtra("doubleValue", 0);
        String stringValue  = intent.getStringExtra("stringValue");

        // Afficher les données dans les TextView et ImageView correspondants
        imageView = findViewById(R.id.image_view);
        imageView.setImageResource(imageResId);

        doubleTextView = findViewById(R.id.double_value_view);
        doubleTextView.setText(String.format("KDA : %.1f", doubleValue));

        stringTextView = findViewById(R.id.string_value_view);
        stringTextView.setText(stringValue);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ItemDetailActivity.this, AddItemActivity.class);
                startActivity(intent);
            }
        });

        Button retourButton = findViewById(R.id.back_button);

// Définition de l'événement onClickListener pour le bouton retour
        retourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retour à la première activité
                finish();
            }
        });
    }
}
