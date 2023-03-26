package com.example.as1;


import androidx.appcompat.app.AppCompatActivity ;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Calculator extends AppCompatActivity implements View.OnClickListener {

    //declare the variable
    private TextView display1, userid;
    private Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    private Button buttonAdd, buttonSubtract, buttonMultiply, buttonDivide, buttonEqual, buttonClear;
    private double num1 = 0, num2 = 0;
    private boolean add, subtract, multiply, divide;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        String USER_ID = getIntent().getStringExtra("USER_ID");
        userid = findViewById(R.id.textUsername);
        userid.setText("User- "+ USER_ID.toUpperCase());

        button0 = findViewById(R.id.btn0);
        button1 = findViewById(R.id.btn1);
        button2 = findViewById(R.id.btn2);
        button3 = findViewById(R.id.btn3);
        button4 = findViewById(R.id.btn4);
        button5 = findViewById(R.id.btn5);
        button6 = findViewById(R.id.btn6);
        button7 = findViewById(R.id.btn7);
        button8 = findViewById(R.id.btn8);
        button9 = findViewById(R.id.btn9);
        buttonAdd = findViewById(R.id.btnAdd);
        buttonSubtract = findViewById(R.id.btnSubt);
        buttonMultiply = findViewById(R.id.btnMulti);
        buttonDivide = findViewById(R.id.btnDiv);
        buttonEqual = findViewById(R.id.btnRun);
        buttonClear = findViewById(R.id.btnClear);
        display1 = findViewById(R.id.display1);


        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);
        buttonSubtract.setOnClickListener(this);
        buttonMultiply.setOnClickListener(this);
        buttonDivide.setOnClickListener(this);
        buttonEqual.setOnClickListener(this);
        buttonClear.setOnClickListener(this);
     }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn0:
                display1.append("0");

                Log.d("msg", "0 is clicked");
                break;

            case R.id.btn1:
                display1.append("1");

                Log.d("msg", "1 is clicked");
                break;
            case R.id.btn2:
                display1.append("2");

                Log.d("msg", "2 is clicked");
                break;
            case R.id.btn3:
                display1.append("3");

                Log.d("msg", "3 is clicked");
                break;
            case R.id.btn4:
                display1.append("4");

                Log.d("msg", "4 is clicked");
                break;
            case R.id.btn5:
                display1.append("5");

                Log.d("msg", "5 is clicked");
                break;
            case R.id.btn6:
                display1.append("6");
                break;
            case R.id.btn7:
                display1.append("7");
                break;
            case R.id.btn8:
                display1.append("8");
                break;
            case R.id.btn9:
                display1.append("9");
                break;
            case R.id.btnAdd:
                if (display1.getText().toString().isEmpty()) {
                    break;}
                String str = display1.getText().toString();
                if (str.endsWith("+") || str.endsWith("-") || str.endsWith("*") || str.endsWith("/")) {
                    display1.setText(str.substring(0, str.length() - 1) + "+");
                } else {
                    try {
                        num1 = Double.parseDouble(display1.getText().toString());
                        display1.append("+");
                        add = true;
                    } catch (NumberFormatException e) {
                        // Handle the exception here, e.g. show an error message to the user
                        //since only two numbers can be calculated for now I have catched exception if the third number is
                        // added and change the whole string of calculation to empty.
                        //same applies to all operators!!
                        display1.setText("");
                    }
                }

                break;
            case R.id.btnSubt:
                if (display1.getText().toString().isEmpty()) {
                    break;}
                String str2 = display1.getText().toString();
                if (str2.endsWith("+") || str2.endsWith("-") || str2.endsWith("*") || str2.endsWith("/")) {
                    display1.setText(str2.substring(0, str2.length() - 1) + "-");
                } else {
                    try {
                        num1 = Double.parseDouble(display1.getText().toString());
                        display1.append("-");
                        subtract = true;
                    } catch (NumberFormatException e) {
                        display1.setText("");
                    }

                }

                break;
            case R.id.btnMulti:
                if (display1.getText().toString().isEmpty()) {
                    break;}
                String str3 = display1.getText().toString();
                if (str3.endsWith("+") || str3.endsWith("-") || str3.endsWith("*") || str3.endsWith("/")) {
                    display1.setText(str3.substring(0, str3.length() - 1) + "*");
                } else {
                    try {
                    num1 = Double.parseDouble(display1.getText().toString());
                    display1.append("*");
                    multiply = true;
                } catch (NumberFormatException e) {
                    display1.setText("");
                }
                }

                break;
            case R.id.btnDiv:
                if (display1.getText().toString().isEmpty()) {
                    break;}
                String str4 = display1.getText().toString();
                if (str4.endsWith("+") || str4.endsWith("-") || str4.endsWith("*") || str4.endsWith("/")) {
                    display1.setText(str4.substring(0, str4.length() - 1) + "/");
                } else {
                    try {
                        num1 = Double.parseDouble(display1.getText().toString());
                        display1.append("/");
                        multiply = true;
                    } catch (NumberFormatException e) {
                        display1.setText("");
                    }
                }

                break;
            case R.id.btnRun:
                if (display1.getText().toString().isEmpty()) {
                    break;
                }
                String expression = display1.getText().toString();
                if (expression.contains("+")) {
                    String[] operands = expression.split("\\+");
                    if (operands.length != 2) {
                        break;
                    }
                    num2 = Double.parseDouble(operands[1]);
                    double result = num1 + num2;
                    display1.setText(Double.toString(result));
                } else if (expression.contains("-")) {
                    String[] operands = expression.split("-");
                    if (operands.length != 2) {
                        break;
                    }
                    num2 = Double.parseDouble(operands[1]);
                    double result = num1 - num2;
                    display1.setText(Double.toString(result));
                } else if (expression.contains("*")) {
                    String[] operands = expression.split("\\*");
                    if (operands.length != 2) {
                        break;
                    }
                    num2 = Double.parseDouble(operands[1]);
                    double result = num1 * num2;
                    display1.setText(Double.toString(result));
                } else if (expression.contains("/")) {
                    String[] operands = expression.split("/");
                    if (operands.length != 2) {
                        break;
                    }
                    num2 = Double.parseDouble(operands[1]);
                    if (num2 == 0) {
                        display1.setText("Error");
                    } else {
                        double result = num1 / num2;
                        display1.setText(Double.toString(result));
                    }
                }
                add = false;
                subtract = false;
                multiply = false;
                divide = false;
                num1 = 0;
                num2 = 0;
                break;
            case R.id.btnClear:
                display1.setText("");
                add = false;
                subtract = false;
                multiply = false;
                divide = false;
                num1 = 0;
                num2 = 0;
                break;
        }
    }
}

