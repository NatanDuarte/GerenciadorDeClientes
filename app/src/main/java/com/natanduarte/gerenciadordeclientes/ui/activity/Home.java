package com.natanduarte.gerenciadordeclientes.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.natanduarte.gerenciadordeclientes.R;

import java.util.Objects;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Objects.requireNonNull(getSupportActionBar()).hide();
        handleCostumerListButton();
    }

    private void handleCostumerListButton() {
        Button costumersListButton = findViewById(R.id.home_my_costumers_button);

        costumersListButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, CustomerList.class);
            startActivity(intent);
        });
    }
}