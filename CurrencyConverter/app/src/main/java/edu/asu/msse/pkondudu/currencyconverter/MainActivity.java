package edu.asu.msse.pkondudu.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void convertCurrency(View view) {
        EditText dollarsText = (EditText) findViewById(R.id.dollars);
        Double dollarsInDouble = Double.parseDouble(dollarsText.getText().toString());
        Log.i("INFO", dollarsInDouble.toString());
        Double rupeesInDouble = dollarsInDouble * 68.11;
        Toast.makeText(MainActivity.this, "₹ " + String.format("%.2f", rupeesInDouble), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
