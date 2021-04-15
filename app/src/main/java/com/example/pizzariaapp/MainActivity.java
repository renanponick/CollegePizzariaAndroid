package com.example.pizzariaapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

class Pizza implements Parcelable {
    public int cod;
    public String nome;
    public String desc;
    public String tam;

    public Pizza(String nome, String end, String nasc, String[] notas, String med){
        this.cod = nome;
        this.nome = end;
        this.desc = nasc;
        this.tam = notas;
    }

    protected Pizza(Parcel in) {
        nome = in.readString();
        end = in.readString();
        nasc = in.readString();
        notas = in.createStringArray();
    }
}

public class MainActivity extends AppCompatActivity {
    ArrayList<Pizza> pizzas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btSalvar = (Button) findViewById(R.id.calcular);
        btSalvar.setOnClickListener(onClickSalvar());
    }
    private View.OnClickListener onClickSalvar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tNome = (TextView) findViewById(R.id.edtNome);
                TextView tEnd = (TextView) findViewById(R.id.edtEnd);
                TextView tData = (TextView) findViewById(R.id.edtData);
                TextView tN1 = (TextView) findViewById(R.id.edtNota1);
                TextView tN2 = (TextView) findViewById(R.id.edtNota2);
                TextView tN3 = (TextView) findViewById(R.id.edtNota3);
                TextView tN4 = (TextView) findViewById(R.id.edtNota4);

                String nome = tNome.getText().toString();
                String endereco = tEnd.getText().toString();
                String data = tData.getText().toString();
                float n1 = Float.parseFloat(tN1.getText().toString());
                float n2 = Float.parseFloat(tN2.getText().toString());
                float n3 = Float.parseFloat(tN3.getText().toString());
                float n4 = Float.parseFloat(tN4.getText().toString());
                float med = ((n1+n2+n3+n4)/4);

                String [] notas = {
                        String.valueOf(n1),
                        String.valueOf(n2),
                        String.valueOf(n3),
                        String.valueOf(n4)
                };
               Aluno aluno = new Aluno(nome, endereco, data, notas, String.valueOf(med));

                int length = alunosNotas.size()+1;
                alunosNotas.add(aluno);
                // arrayList que armazena todos os alunos com suas respectivas informações
                if(length<=30){
                    Intent intent = new Intent(MainActivity.this, exibir.class);
                    Bundle params = new Bundle();
                    params.putParcelableArrayList("aluno", alunosNotas);
                    intent.putExtras(params);
                    startActivityForResult(intent, 2);
                } else {
                    throw new Error("Limite 30 alunos");
                }
            }
        };
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            Intent it = data;
            if (it != null) {
                TextView tNome = (TextView) findViewById(R.id.edtNome);
                TextView tEnd = (TextView) findViewById(R.id.edtEnd);
                TextView tData = (TextView) findViewById(R.id.edtData);
                TextView tN1 = (TextView) findViewById(R.id.edtNota1);
                TextView tN2 = (TextView) findViewById(R.id.edtNota2);
                TextView tN3 = (TextView) findViewById(R.id.edtNota3);
                TextView tN4 = (TextView) findViewById(R.id.edtNota4);
                tNome.setText("");
                tEnd.setText("");
                tData.setText("");
                tN1.setText("");
                tN2.setText("");
                tN3.setText("");
                tN4.setText("");
                Bundle args = it.getExtras();
                alunosNotas = args.getParcelableArrayList("aluno");
            }
        }
    }
}