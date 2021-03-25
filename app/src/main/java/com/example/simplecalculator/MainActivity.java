package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {

    TextView workingsTV, resultsTV;

    String workings = "";
    String formula = "";
    String tempFormula = "";

    LinearLayout container;
    Button newBtn;
    Button equal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextView();
        equal = findViewById(R.id.equalBtn);
        equal.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(MainActivity.this,assignment.class);
                startActivity(intent);
                return false;
            }
        });
    }

    private void initTextView() {

    workingsTV = (TextView)findViewById(R.id.workingsTextView);
    resultsTV = (TextView)findViewById(R.id.resultTextView);

    }
    private void setWorkings(String givenValue){
        workings = workings + givenValue;
        workingsTV.setText(workings);
    }
    public void replaceWorkings(String btnClick){
        workingsTV.setText(btnClick);
        workings = btnClick;
    }

    public void clearOnClick(View view) {
        workingsTV.setText("");
        workings = "";
        resultsTV.setText("");
        leftBracket = true;
    }

    public void equalOnClick(View view) {
        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        checkForPowerOf();

        try {
            result = (double) engine.eval(formula);
        } catch (ScriptException e ){
            Toast.makeText(this,"Invalid Input", Toast.LENGTH_SHORT).show();
        }
        if(result!=null){
            resultsTV.setText(String.valueOf(result.doubleValue()));
            createHistoryEntry(workings);

        }
    }

    public void createHistoryEntry(String workingsButton) {
        container = findViewById(R.id.btnContainer);
        newBtn = new Button(this);
        newBtn.setText(workingsButton);
        newBtn.setBackgroundColor(Color.rgb(0,0,0));
        newBtn.setTextColor(Color.rgb(255,255,255));
        newBtn.setBackground(null);
        newBtn.setTextSize(15);
        newBtn.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        newBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceWorkings(workingsButton);
            }
        });
        container.addView(newBtn);
    }

    private void checkForPowerOf() {
        ArrayList<Integer> powersIndex = new ArrayList<>();
        for (int i = 0; i < workings.length(); i++) {
            if(workings.charAt(i) == '^'){
                powersIndex.add(i);
            }
        }
        formula = workings;
        tempFormula = workings;
        for (Integer i: powersIndex
             ) {

            changeFormula(i);

        }
        formula = tempFormula;
    }

    private void changeFormula(Integer index) {
        String left = "";
        String right = "";
        for (int j = index+1; j < workings.length(); j++) {
            if(isNumeric(workings.charAt(j))){
                right = right + workings.charAt(j);
            }
            else
                break;

        }
        for (int i = index - 1; i >= 0 ; i++) {
            if(isNumeric(workings.charAt(i))){
               left = left + workings.charAt(i);
            }
            else{
                break;
            }
        }
        String originalForm = left + "^" + right;
        String changedForm = "Math.pow("+left+","+right+")";
        tempFormula = tempFormula.replace(originalForm,changedForm);
    }
    private boolean isNumeric(char c){
        if((c <= '9' && c >= '0') || c == '.'){
            return true;
        }
        return false;
    }

    public void addOnClick(View view) {
        setWorkings("+");
    }

    public void decimalOnClick(View view) {
        setWorkings(".");
    }

    public void zeroOnClick(View view) {
        setWorkings("0");

    }
    boolean leftBracket = true;
    public void bracketsOnClick(View view) {
        if(leftBracket) {
            setWorkings("(");
            leftBracket = false;
        }
        else{
            setWorkings(")");
            leftBracket = true;
        }
    }

    public void powerOfOnClick(View view) {

    setWorkings("^");

    }

    public void divisionOnClick(View view) {

        setWorkings("/");

    }

    public void sevenOnClick(View view) {
        setWorkings("7");
    }

    public void eightOnClick(View view) {
        setWorkings("8");
    }

    public void nineOnClick(View view) {
        setWorkings("9");
    }

    public void multiplyOnClick(View view) {
        setWorkings("*");
    }

    public void fourOnClick(View view) {
        setWorkings("4");
    }

    public void fiveOnClick(View view) {
        setWorkings("5");
    }

    public void sixOnClick(View view) {
        setWorkings("6");
    }

    public void minusOnClick(View view) {
        setWorkings("-");
    }

    public void oneOnClick(View view) {
        setWorkings("1");
    }

    public void twoOnClick(View view) {
        setWorkings("2");
    }

    public void threeOnClick(View view) {
        setWorkings("3");
    }

    public void deleteOnClick(View view) {
        StringBuilder sb = new StringBuilder(workings);
        if(workings.length() >= 1){
            sb.deleteCharAt(workings.length() - 1);
            workings = sb.toString();
            workingsTV.setText(workings);
        }

    }
}