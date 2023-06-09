package com.example.tparendreandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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
        String imageUriString = intent.getStringExtra("imageUriString");
        double doubleValue = intent.getDoubleExtra("doubleValue", 0);
        String stringValue  = intent.getStringExtra("stringValue");

        // Afficher les données dans les TextView et ImageView correspondants
        imageView = findViewById(R.id.image_view);
        if (imageUriString != null) {
            Glide.with(this)
                    .load(imageUriString)
                    .error(R.drawable.bonhomme)
                    .into(imageView);
        } else {
            imageView.setImageResource(R.drawable.bonhomme);
        }

        doubleTextView = findViewById(R.id.double_value_view);
        doubleTextView.setText(String.format("KDA : %.1f", doubleValue));

        stringTextView = findViewById(R.id.string_value_view);
        stringTextView.setText(stringValue);

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

