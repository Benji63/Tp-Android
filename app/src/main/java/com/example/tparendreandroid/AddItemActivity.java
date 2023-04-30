
package com.example.tparendreandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
    private static final int REQUEST_SELECT_IMAGE = 1;
    private Uri selectedImageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        nameEditText = findViewById(R.id.edit_text_string_value);
        valueEditText = findViewById(R.id.edit_text_double_value);
        addButton = findViewById(R.id.fab_save_item);

        Button btnSelectImage = findViewById(R.id.button_add_image);
        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_SELECT_IMAGE);
            }
        });



        addButton.setOnClickListener(view -> {
            String name = nameEditText.getText().toString();
            String value = valueEditText.getText().toString();

            if (name.isEmpty() || value.isEmpty()) {
                Toast.makeText(AddItemActivity.this, "Remplissez les cases", Toast.LENGTH_SHORT).show();
            } else {
                double doubleValue = Double.parseDouble(value);
                //int imageResId = R.drawable.bonhomme;
                String path = selectedImageUri.getLastPathSegment();
                int image_value = Integer.parseInt(path);

                Intent resultIntent = new Intent();
                //resultIntent.putExtra("imageResId", imageResId);
                resultIntent.putExtra("imageResId", image_value);
                resultIntent.putExtra("doubleValue", doubleValue);
                resultIntent.putExtra("stringValue", name);
                setResult(RESULT_OK, resultIntent);
                finish();
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_SELECT_IMAGE && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
        }


    }
}


