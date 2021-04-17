package com.example.pizzariaapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

class Pizza implements Parcelable {
    public int cod;
    public String nome;
    public String desc;
    public String tam;

    public Pizza(int cod, String nome, String desc, String tam){
        this.cod = cod;
        this.nome = nome;
        this.desc = desc;
        this.tam = tam;
    }

    protected Pizza(Parcel in) {
        cod = in.readInt();
        nome = in.readString();
        desc = in.readString();
        tam = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(cod);
        dest.writeString(nome);
        dest.writeString(desc);
        dest.writeString(tam);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Pizza> CREATOR = new Creator<Pizza>() {
        @Override
        public Pizza createFromParcel(Parcel in) {
            return new Pizza(in);
        }

        @Override
        public Pizza[] newArray(int size) {
            return new Pizza[size];
        }
    };
}

public class MainActivity extends AppCompatActivity {
    ArrayList<Pizza> pizzas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btSalvar = (Button) findViewById(R.id.gravar);
        btSalvar.setOnClickListener(onClickSalvar());

        Button btMenu = (Button) findViewById(R.id.menu);
        btMenu.setOnClickListener(onClickMenu());
    }

    private View.OnClickListener onClickSalvar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tNome = (TextView) findViewById(R.id.edtNome);
                String nome = tNome.getText().toString();

                TextView tDesc = (TextView) findViewById(R.id.edtDesc);
                String descricao = tDesc.getText().toString();

                RadioGroup rTam = (RadioGroup) findViewById(R.id.tamanhos);
                int radioId = rTam.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(radioId);
                String tamanho = radioButton.getText().toString();

                int cod = pizzas.size()+1;
                Pizza pizza = new Pizza(cod, nome, descricao, tamanho);
                pizzas.add(pizza);

                tNome.setText("");
                tDesc.setText("");
                rTam.clearCheck();

                Toast.makeText(MainActivity.this, "Pizza Salva com sucesso!",Toast.LENGTH_SHORT).show();
            }
        };
    }

    private View.OnClickListener onClickMenu() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Menu.class);
                Bundle params = new Bundle();
                params.putParcelableArrayList("pizzas", pizzas);
                intent.putExtras(params);
                startActivityForResult(intent, 2);
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            Intent it = data;
            if (it != null) {
                Bundle args = it.getExtras();
                pizzas = args.getParcelableArrayList("pizzas");
            }
        }
    }
}