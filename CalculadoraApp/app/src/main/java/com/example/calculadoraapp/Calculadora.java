package com.example.calculadoraapp;

import java.text.Format;

public class Calculadora {
    private String previousOperation;
    private String currentOperation;
    private String operation;

    // Constructor
    public Calculadora(){
        this.operation = "";
        this.previousOperation = "";
        this.currentOperation = "";
    }

    // Main
    public String addDigit(String digit){

        // Impedindo de começar operação com ponto
        if(digit.equals(".") && this.getCurrentOperation().isEmpty()){
            return this.getCurrentOperation();
        }

        // Se já conter ponto, não permitir digitar outros pontos
        if(digit.equals(".") && this.getCurrentOperation().contains(".")){
            return this.getCurrentOperation();
        }

        this.setCurrentOperation(this.currentOperation + digit);
        return this.getCurrentOperation();
    }

    public void clearCurrent(){
        this.setCurrentOperation("");
    }

    public void clearAll(){
        this.setOperation("");
        this.setPreviousOperation("");
        this.setCurrentOperation("");
    }

    public String executeOperation(){
        String result = null;

        switch (this.operation){
            case "+":
                result = sum();
                break;
            case "-":
                result = sub();
                break;
            case "*":
                result = multi();
                break;
            case "/":
                result = div();
                break;
        }

        return result;
    }

    // Operations
    public String sum(){
        float result = Float.parseFloat(this.previousOperation) + Float.parseFloat(this.currentOperation);
        return String.valueOf(result);
    }

    public String sub(){
        float result = Float.parseFloat(this.previousOperation) - Float.parseFloat(this.currentOperation);
        return String.valueOf(result);
    }

    public String multi(){
        float result = Float.parseFloat(this.previousOperation) * Float.parseFloat(this.currentOperation);
        return String.valueOf(result);
    }

    public String div(){
        float result = Float.parseFloat(this.previousOperation) / Float.parseFloat(this.currentOperation);
        return String.valueOf(result);
    }

    // Getters & Setters
    public String getPreviousOperation() {
        return previousOperation;
    }

    public void setPreviousOperation(String previousOperation) {
        this.previousOperation = previousOperation;
    }

    public String getCurrentOperation() {
        return currentOperation;
    }

    public void setCurrentOperation(String currentOperation) {
        this.currentOperation = currentOperation;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
