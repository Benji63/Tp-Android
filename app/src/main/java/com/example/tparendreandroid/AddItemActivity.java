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

public class AddItemActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 100;
    private EditText editTextDoubleValue;
    private EditText editTextStringValue;
    private ImageView imageView;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        imageView = findViewById(R.id.image_view);
        Button buttonAddImage = findViewById(R.id.button_add_image);
        buttonAddImage.setOnClickListener(v -> openImageChooser());

        editTextDoubleValue = findViewById(R.id.edit_text_double_value);
        editTextStringValue = findViewById(R.id.edit_text_string_value);

        FloatingActionButton addButton = findViewById(R.id.fab_save_item);
        addButton.setOnClickListener(view -> {
            String name = editTextStringValue.getText().toString();
            String value = editTextDoubleValue.getText().toString();

            if (name.isEmpty() || value.isEmpty()) {
                Toast.makeText(AddItemActivity.this, "Remplissez les cases", Toast.LENGTH_SHORT).show();
            } else {
                double doubleValue = Double.parseDouble(value);
                String imageUriString = (imageUri != null) ? imageUri.toString() : null;

                Intent resultIntent = new Intent();
                resultIntent.putExtra("imageUriString", imageUriString);
                resultIntent.putExtra("doubleValue", doubleValue);
                resultIntent.putExtra("stringValue", name);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        Button retourButton = findViewById(R.id.button_back);
        retourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retour à la première activité
                finish();
            }
        });
    }

    private void openImageChooser() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            if (imageUri != null) {
                imageView.setImageURI(imageUri);
            } else {
                imageView.setImageResource(R.drawable.bonhomme);
            }
        }
    }
}