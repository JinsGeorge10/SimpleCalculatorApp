package com.example.simplecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private String currentNumber = "";
    private String operand = "";
    private double firstNumber = 0;
    private double secondNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);

        // Set click listeners for the calculator buttons
        Button[] numberButtons = new Button[10];
        for (int i = 0; i < 10; i++) {
            int resID = getResources().getIdentifier("button" + i, "id", getPackageName());
            numberButtons[i] = findViewById(resID);
            numberButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNumberButtonClick(((Button) v).getText().toString());
                }
            });
        }

        Button addButton = findViewById(R.id.addButton);
        Button subtractButton = findViewById(R.id.subtractButton);
        Button multiplyButton = findViewById(R.id.multiplyButton);
        Button divideButton = findViewById(R.id.divideButton);
        Button clearButton = findViewById(R.id.clearButton);
        Button equalsButton = findViewById(R.id.equalsButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperatorClick("+");
            }
        });

        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperatorClick("-");
            }
        });

        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperatorClick("*");
            }
        });

        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperatorClick("/");
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClearClick();
            }
        });

        equalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEqualsClick();
            }
        });
    }

    private void onNumberButtonClick(String number) {
        currentNumber += number;
        updateResultTextView();
    }

    private void onOperatorClick(String newOperand) {
        if (!currentNumber.isEmpty()) {
            operand = newOperand;
            firstNumber = Double.parseDouble(currentNumber);
            currentNumber = "";
        }
    }

    private void onClearClick() {
        currentNumber = "";
        operand = "";
        firstNumber = 0;
        secondNumber = 0;
        updateResultTextView();
    }

    private void onEqualsClick() {
        if (!currentNumber.isEmpty() && !operand.isEmpty()) {
            secondNumber = Double.parseDouble(currentNumber);
            double result = calculate();
            currentNumber = String.valueOf(result);
            updateResultTextView();
        }
    }

    private double calculate() {
        switch (operand) {
            case "+":
                return firstNumber + secondNumber;
            case "-":
                return firstNumber - secondNumber;
            case "*":
                return firstNumber * secondNumber;
            case "/":
                if (secondNumber != 0) {
                    return firstNumber / secondNumber;
                } else {
                    return Double.NaN; // Handle division by zero
                }
            default:
                return 0;
        }
    }

    private void updateResultTextView() {
        resultTextView.setText(currentNumber);
    }
}
