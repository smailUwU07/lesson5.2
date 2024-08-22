package com.example.lesson5;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Integer firstOperand;
    private Integer secondOperand;
    private Integer result;
    private Button button;
    private Boolean isOperationClick;
    private String operation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view);
        button = findViewById(R.id.btn_2);
    }
    public void OnNumberClick(View view) {
        if (view.getId() == R.id.btn_clear) {
            textView.setText("0");
        } else {
            String text = ((MaterialButton) view).getText().toString();
            if (textView.getText().toString().equals("0") || isOperationClick) {
                textView.setText(text);
            } else {
                textView.append(text);
            }
        }
        button.setVisibility(View.GONE);
        isOperationClick = false;
    }

    public void OnOperationClick(View view) {
        button.setVisibility(View.GONE);
        if (view.getId() == R.id.btn_plus) {
            firstOperand = Integer.valueOf(textView.getText().toString());
            operation = "+";

        } else if (view.getId() == R.id.btn_division) {
            firstOperand = Integer.valueOf(textView.getText().toString());
            operation = "/";

        } else if (view.getId() == R.id.btn_minus) {
            firstOperand = Integer.valueOf(textView.getText().toString());
            operation = "-";

        } else if (view.getId() == R.id.btn_multiplication) {
            firstOperand = Integer.valueOf(textView.getText().toString());
            operation = "*";

        } else if (view.getId() == R.id.btn_egual) {
            secondOperand = Integer.valueOf(textView.getText().toString());
            if (operation.equals("+")) {
                result = firstOperand + secondOperand;
                textView.setText(result.toString());
            } else if (operation.equals("-")) {
                result = firstOperand - secondOperand;
                textView.setText(result.toString());
            } else if (operation.equals("*")) {
                result = firstOperand * secondOperand;
                textView.setText(result.toString());
            } else if (operation.equals("/")) {
                if (secondOperand == 0) {
                    textView.setText("Error");
                } else {
                    result = firstOperand / secondOperand;
                }
            }
            textView.setText(result.toString());
            button.setVisibility(View.VISIBLE);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("result", result);
                    startActivity(intent);
                    finish();
                }
            });

        }
        isOperationClick = true;
    }
}
