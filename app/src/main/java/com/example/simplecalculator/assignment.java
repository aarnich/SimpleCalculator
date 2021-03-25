package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class assignment extends AppCompatActivity {

    TextInputLayout val1, val2;
    TextView additionResult, subtractionResult, multiplicationResult, divisionResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);

        val1 = findViewById(R.id.valOne);
        val2 = findViewById(R.id.valTwo);
        additionResult = findViewById(R.id.addResult);
        subtractionResult = findViewById(R.id.subtractResult);
        multiplicationResult = findViewById(R.id.multiplyResult);
        divisionResult = findViewById(R.id.divideResult);

    }
    public boolean isNumeric(String str){
        try{
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e ){
            return false;
        }
    }
    public boolean validation(String val, TextInputLayout v){
        if(val.isEmpty()){
            v.setError("Field cannot be empty");
            return false;
        }
        else if(!(isNumeric(val))){
            v.setError("Field has to be numerical");
            return false;
        }
        else{
            v.setError(null);
            v.setErrorEnabled(false);
            return true;
        }

    }

    public void addInputs(View view) {
        String eval1 = val1.getEditText().getText().toString();
        String eval2 = val2.getEditText().getText().toString();
        if(validation(eval1, val1) && validation(eval2, val2)){
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
            Double result = null;
            String formula = eval1 + "+" + eval2;
            try {
                result = (double) engine.eval(formula);
            } catch (ScriptException e ){
                Toast.makeText(this,"Invalid Input", Toast.LENGTH_SHORT).show();
            }
            if(result!=null){
                additionResult.setText("Addition Result: " + result);
            }
        }

    }

    public void subtractInputs(View view) {
        String eval1 = val1.getEditText().getText().toString();
        String eval2 = val2.getEditText().getText().toString();
        if(validation(eval1, val1) && validation(eval2, val2)){
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
            Double result = null;
            String formula = eval1 + "-" + eval2;
            try {
                result = (double) engine.eval(formula);
            } catch (ScriptException e ){
                Toast.makeText(this,"Invalid Input", Toast.LENGTH_SHORT).show();
            }
            if(result!=null){
                subtractionResult.setText("Subtraction Result: " + result);
            }
        }

    }

    public void multiplyInputs(View view) {
        String eval1 = val1.getEditText().getText().toString();
        String eval2 = val2.getEditText().getText().toString();
        if(validation(eval1, val1) && validation(eval2, val2)){
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
            Double result = null;
            String formula = eval1 + "*" + eval2;
            try {
                result = (double) engine.eval(formula);
            } catch (ScriptException e ){
                Toast.makeText(this,"Invalid Input", Toast.LENGTH_SHORT).show();
            }
            if(result!=null){
                multiplicationResult.setText("Multiply Result: " + result);
            }
        }

    }

    public void divideInputs(View view) {
        String eval1 = val1.getEditText().getText().toString();
        String eval2 = val2.getEditText().getText().toString();
        if(validation(eval1, val1) && validation(eval2, val2)){
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
            Double result = null;
            String formula = eval1 + "/" + eval2;
            try {
                result = (double) engine.eval(formula);
            } catch (ScriptException e ){
                Toast.makeText(this,"Invalid Input", Toast.LENGTH_SHORT).show();
            }
            if(result!=null){
                divisionResult.setText("Division Result: " + result);
            }
        }

    }

    public void doAll(View view) {
        addInputs(view);
        subtractInputs(view);
        multiplyInputs(view);
        divideInputs(view);
    }

    public void clearInputs(View view) {
        val1.getEditText().setText(null);
        val2.getEditText().setText(null);
        additionResult.setText(null);
        subtractionResult.setText(null);
        multiplicationResult.setText(null);
        divisionResult.setText(null);
    }
}