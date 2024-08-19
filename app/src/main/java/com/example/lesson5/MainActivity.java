package com.example.lesson5;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private int firstOperand = 0;
    private int secondOperand = 0;
    private boolean isOperationClick = false;
    private String operator = "";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);


        assignNumberButtonClickListener(findViewById(R.id.btn_clear));
        assignNumberButtonClickListener(findViewById(R.id.bt_plus));
        assignNumberButtonClickListener(findViewById(R.id.bt_minus));
        assignNumberButtonClickListener(findViewById(R.id.bt_division));
        assignNumberButtonClickListener(findViewById(R.id.bt_multiplication));
        assignNumberButtonClickListener(findViewById(R.id.bt_equals));


        findViewById(R.id.bt_plus).setOnClickListener(this::oneOperationClick);
        findViewById(R.id.bt_minus).setOnClickListener(this::oneOperationClick);
        findViewById(R.id.bt_division).setOnClickListener(this::oneOperationClick);
        findViewById(R.id.bt_multiplication).setOnClickListener(this::oneOperationClick);
        findViewById(R.id.bt_equals).setOnClickListener(this::onEqualsClick);
    }

    private void assignNumberButtonClickListener(View view) {
        view.setOnClickListener(this::oneNumberClick);
    }

    public void oneNumberClick(View view) {
        if (view.getId() == R.id.btn_clear) {
            resetCalculator();
        } else {
            String text = ((MaterialButton) view).getText().toString();
            if (textView.getText().toString().equals("0") || isOperationClick) {
                textView.setText(text);
            } else {
                textView.append(text);
            }
            isOperationClick = false;
        }
    }

    @SuppressLint("NonConstantResourceId")
    public void oneOperationClick(View view) {
        String currentText = textView.getText().toString();
        isOperationClick = true;

        if (!operator.isEmpty()) {
            secondOperand = Integer.parseInt(currentText);
            int result = calculateResult(firstOperand, secondOperand, operator);
            if (operator.equals("/") && secondOperand == 0) {
                resetCalculator();
                return;
            }
            textView.setText(String.valueOf(result));
            firstOperand = result;
        } else {
            firstOperand = Integer.parseInt(currentText);
        }

        switch (view.getId()) {
            case R.id.bt_plus:
                operator = "+";
                break;
            case R.id.bt_minus:
                operator = "-";
                break;
            case R.id.bt_division:
                operator = "/";
                break;
            case R.id.bt_multiplication:
                operator = "*";
                break;
        }
    }

    public void onEqualsClick(View view) {
        String currentText = textView.getText().toString();
        secondOperand = Integer.parseInt(currentText);
        int result = calculateResult(firstOperand, secondOperand, operator);
        if (operator.equals("/") && secondOperand == 0) {
            resetCalculator();
            return;
        }
        textView.setText(String.valueOf(result));
        operator = ""; 
        firstOperand = result;
        isOperationClick = true;
    }

    private int calculateResult(int firstOperand, int secondOperand, String operator) {
        switch (operator) {
            case "+":
                return firstOperand + secondOperand;
            case "-":
                return firstOperand - secondOperand;
            case "/":
                if (secondOperand != 0) {
                    return firstOperand / secondOperand;
                } else {
                    textView.setText("Error");
                    return 0;
                }
            case "*":
                return firstOperand * secondOperand;
            default:
                return 0;
        }
    }

    private void resetCalculator() {
        textView.setText("0");
        firstOperand = 0;
        secondOperand = 0;
        operator = "";
        isOperationClick = false;
    }
}
