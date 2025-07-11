package com.example.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView display;
    String current = "";
    String result = "";
    String operator = "";
    boolean isNew = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
        GridLayout grid = findViewById(R.id.buttonGrid);

        for (int i = 0; i < grid.getChildCount(); i++) {
            View v = grid.getChildAt(i);
            if (v instanceof Button) {
                ((Button) v).setOnClickListener(this::onButtonClick);
            }
        }
    }

    public void onButtonClick(View view) {
        Button b = (Button) view;
        String text = b.getText().toString();

        switch (text) {
            case "C":
                current = "";
                result = "";
                operator = "";
                display.setText("0");
                break;

            case "DEL":
                if (!current.isEmpty()) {
                    current = current.substring(0, current.length() - 1);
                    display.setText(current.isEmpty() ? "0" : current);
                }
                break;

            case "+":
            case "-":
            case "*":
            case "/":
                if (!current.isEmpty()) {
                    result = current;
                    operator = text;
                    current = "";
                }
                break;

            case "=":
                calculate();
                break;

            default: // digits or "."
                current += text;
                display.setText(current);
                break;
        }
    }

    private void calculate() {
        if (result.isEmpty() || current.isEmpty() || operator.isEmpty()) return;

        double num1 = Double.parseDouble(result);
        double num2 = Double.parseDouble(current);
        double calc = 0;

        switch (operator) {
            case "+": calc = num1 + num2; break;
            case "-": calc = num1 - num2; break;
            case "*": calc = num1 * num2; break;
            case "/":
                if (num2 == 0) {
                    display.setText("Error");
                    return;
                }
                calc = num1 / num2;
                break;
        }

        display.setText(String.valueOf(calc));
        current = String.valueOf(calc);
        result = "";
        operator = "";
    }
}
