
package com.example.tparendreandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AddItemActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText valueEditText;
    private FloatingActionButton addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        nameEditText = findViewById(R.id.edit_text_string_value);
        valueEditText = findViewById(R.id.edit_text_double_value);
        addButton = findViewById(R.id.fab_save_item);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEditText.getText().toString();
                String value = valueEditText.getText().toString();

                if (name.isEmpty() || value.isEmpty()) {
                    Toast.makeText(AddItemActivity.this, "Remplissez les cases", Toast.LENGTH_SHORT).show();
                } else {
                    double doubleValue = Double.parseDouble(value);
                    int imageResId = R.drawable.bonhomme;
                    Item item = new Item(imageResId, doubleValue, name);
                    MainActivity activity = (MainActivity) view.getContext();
                    activity.addData(item.getImageResId(), item.getDoubleValue(), item.getStringValue());
                    finish();
                }
            }
        });
        Button retourButton = findViewById(R.id.button_back);

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


