package edu.asu.msse.pkondudu.numbershapes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void checkNumber(View view)  {
        Number myNumber = new Number();
        String result = "";
        EditText numberTextField = (EditText) findViewById(R.id.numberTextField);
        if (numberTextField.getText().toString().isEmpty()) {
            result = "Please enter a number!";
        }
        myNumber.number = Integer.parseInt(numberTextField.getText().toString());

        if (myNumber.isSquare()) {
            if (myNumber.isTriangular()) {
                result = myNumber.number + " is Triangular and Square";
            } else {
                result = myNumber.number + " is Square";
            }
        } else if (myNumber.isTriangular()){
            result = myNumber.number + " is Triangular";
        } else {
            result = myNumber.number + " is neither Triangular nor Square";
        }

        Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
