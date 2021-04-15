package com.example.pizzariaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class exibir extends AppCompatActivity {
    ArrayList<Aluno> alunosNotas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir);

        Bundle args = getIntent().getExtras();
        alunosNotas = args.getParcelableArrayList("aluno");

        TextView tNome = (TextView) findViewById(R.id.textName);
        TextView tEnd = (TextView) findViewById(R.id.textEnd);
        TextView tData = (TextView) findViewById(R.id.textData);
        TextView tN1 = (TextView) findViewById(R.id.textN1);
        TextView tN2 = (TextView) findViewById(R.id.textN2);
        TextView tN3 = (TextView) findViewById(R.id.textN3);
        TextView tN4 = (TextView) findViewById(R.id.textN4);
        TextView tMed = (TextView) findViewById(R.id.textMed);

        alunosNotas.forEach(aluno -> {
            tNome.setText(aluno.nome);
            tEnd.setText(aluno.end);
            tData.setText(aluno.nasc);
            tN1.setText(aluno.notas[0]);
            tN2.setText(aluno.notas[1]);
            tN3.setText(aluno.notas[2]);
            tN4.setText(aluno.notas[3]);
            tMed.setText(aluno.med);
        });

        Button btAdd = (Button) findViewById(R.id.btnAdd);
        btAdd.setOnClickListener(onClickAdicionar());
    }
    private View.OnClickListener onClickAdicionar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int length = alunosNotas.size();
                if(length<=30){
                    Intent intent = new Intent();
                    Bundle params = new Bundle();
                    params.putParcelableArrayList("aluno", alunosNotas);
                    intent.putExtras(params);
                    setResult(2,intent);
                    finish();
                } else {
                    throw new Error("Limite 30 alunos");
                }
            }
        };
    }
}