package com.example.quiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public RadioGroup radioGroup;
    public TextView textoPergunta;

    public RadioButton opcaoA;
    public RadioButton opcaoB;
    public RadioButton opcaoC;

    String Perguntas[] = {"Primeira Pergunta: ",
            "Segunda Pergunta: ",
            "Terceira Pergunta: ",
            "Quarta Pergunta: ",
            "Quinta Pergunta: "};

    String OpcaoA[] = {"Resposta A primeira pergunta.",
            "Resposta A segunda pergunta.",
            "Resposta A terceira pergunta.",
            "Resposta A quarta pergunta.",
            "Resposta A quinta pergunta.", };

    String OpcaoB[] = {"Resposta B primeira pergunta.",
            "Resposta B segunda pergunta.",
            "Resposta B terceira pergunta.",
            "Resposta B quarta pergunta.",
            "Resposta B quinta pergunta.", };
    String OpcaoC[] = {"Resposta C primeira pergunta.",
            "Resposta C segunda pergunta.",
            "Resposta C terceira pergunta.",
            "Resposta C quarta pergunta.",
            "Resposta C quinta pergunta.", };

    int[] listaRespostas = new int[Perguntas.length];
    int[] listaGabarito = {1,2,3,1,2};
    int respostasCorretas = 0;
    int numeroPergunta = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button botaoOk = findViewById(R.id.botaoOk);
        textoPergunta = findViewById(R.id.campoTexto);
        radioGroup = findViewById(R.id.grupoRadio);

        opcaoA = findViewById(R.id.opcaoA);
        opcaoB = findViewById(R.id.opcaoB);
        opcaoC = findViewById(R.id.opcaoC);

        botaoOk.setEnabled(false);

        atualizaPerguntas(botaoOk);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId)
                {
                    case R.id.opcaoA:
                        Log.d("s","Opção A!");
                        listaRespostas[numeroPergunta-1] = 1;
                        break;
                    case R.id.opcaoB:
                        Log.d("s","Opção B!");
                        listaRespostas[numeroPergunta-1] = 2;
                        break;
                    case R.id.opcaoC:
                        Log.d("s","Opção C!");
                        listaRespostas[numeroPergunta-1] = 3;
                        break;
                }
                botaoOk.setEnabled(true);
            }
        });

    }

    public void atualizaPerguntas(View view) {


        if(numeroPergunta == Perguntas.length) {
            confereResultado();
            alertaResultado(view);
        }
        else {
            textoPergunta.setText(Perguntas[numeroPergunta]);
            opcaoA.setText(OpcaoA[numeroPergunta]);
            opcaoB.setText(OpcaoB[numeroPergunta]);
            opcaoC.setText(OpcaoC[numeroPergunta]);
            numeroPergunta++;
            radioGroup.clearCheck();
            view.setEnabled(false);
        }

    };

    public void confereResultado() {
        for(int i=0; i < listaRespostas.length; i++)
        {
            if(listaRespostas[i] == listaGabarito[i])
            {
                respostasCorretas++;
            }
        }
    }

    public void alertaResultado(View view) {
        AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Funcionou!");
        alertDialog.setMessage("Você acertou "+respostasCorretas+ " questões!");
        alertDialog.show();
    }


}
