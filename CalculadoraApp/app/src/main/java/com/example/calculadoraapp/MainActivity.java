package com.example.calculadoraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Calculadora calculadora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculadora = new Calculadora();
    }

    @SuppressLint("SetTextI18n")
    public void addNumber(View button){
        Button number = (Button) button;
        TextView inputCurrentOperation = findViewById(R.id.inputCurrentOperation);

        inputCurrentOperation.setText(calculadora.addDigit(number.getText().toString()));
    }

    public void clearCurrent(View view){
        calculadora.clearCurrent();
        TextView inputCurrentOperation = findViewById(R.id.inputCurrentOperation);
        inputCurrentOperation.setText("");
    }

    public void clearAll(View view){
        calculadora.clearAll();

        // inputs
        TextView inputCurrentOperation = findViewById(R.id.inputCurrentOperation);
        TextView inputPreviousOperation = findViewById(R.id.inputPreviousOperation);

        // Zerando inputs
        inputPreviousOperation.setText("");
        inputCurrentOperation.setText("");
    }

    @SuppressLint("SetTextI18n")
    public void addOperation(View button){
        // button
        Button operation = (Button) button;

        // inputs
        TextView inputCurrentOperation = findViewById(R.id.inputCurrentOperation);
        TextView inputPreviousOperation = findViewById(R.id.inputPreviousOperation);

        // Impedindo de começar operação com sinal
        if(calculadora.getCurrentOperation().isEmpty()){

            if(calculadora.getPreviousOperation().isEmpty()){
                return;
            }

            calculadora.setOperation(operation.getText().toString());
            inputPreviousOperation.setText(calculadora.getPreviousOperation() + calculadora.getOperation());
            return;
        }

        // Depois que tiver as duas operações preenchidas, não é possível trocar o sinal
        if(!calculadora.getOperation().isEmpty()){
            return;
        }

        // Definindo ou substituindo operação a ser realizada
        calculadora.setOperation(operation.getText().toString());

        // Modificando valores dos inputs
        calculadora.setPreviousOperation(calculadora.getCurrentOperation());
        calculadora.setCurrentOperation("");

        // Exibindo valores nos inputs
        inputPreviousOperation.setText(calculadora.getPreviousOperation() + calculadora.getOperation());
        inputCurrentOperation.setText(calculadora.getCurrentOperation());

    }

    public void equalOperation(View view){
        TextView inputCurrentOperation = findViewById(R.id.inputCurrentOperation);
        TextView inputPreviousOperation = findViewById(R.id.inputPreviousOperation);

        // Divisão por zero
        if(calculadora.getOperation().equals("/") && calculadora.getCurrentOperation().equals("0")){
            Toast.makeText(view.getContext(), "Impossível dividir por zero!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Realizando operação
        if(!calculadora.getPreviousOperation().isEmpty() && !calculadora.getCurrentOperation().isEmpty()){
            // Modificando valores dos inputs
            calculadora.setCurrentOperation(calculadora.executeOperation());
            calculadora.setPreviousOperation("");

            // Exibindo valores nos inputs
            inputPreviousOperation.setText(calculadora.getPreviousOperation());
            inputCurrentOperation.setText(calculadora.getCurrentOperation());
            calculadora.setOperation("");
        }else{
            Toast.makeText(view.getContext(), "Insira todos os valores primeiro", Toast.LENGTH_SHORT).show();
        }
    }

}