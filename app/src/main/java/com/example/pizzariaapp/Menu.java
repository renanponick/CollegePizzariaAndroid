package com.example.pizzariaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Menu extends AppCompatActivity {
    ArrayList<Pizza> pizzas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Bundle args = getIntent().getExtras();
        pizzas = args.getParcelableArrayList("pizzas");

        TextView tNome = (TextView) findViewById(R.id.textName);
        int size = pizzas.size();

        tNome.setText(String.valueOf(size));


        Button btAdd = (Button) findViewById(R.id.btnAdd);
        btAdd.setOnClickListener(onClickAdicionar());
    }
    private View.OnClickListener onClickAdicionar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle params = new Bundle();
                params.putParcelableArrayList("pizzas", pizzas);
                intent.putExtras(params);
                setResult(2,intent);
                finish();
            }
        };
    }
}