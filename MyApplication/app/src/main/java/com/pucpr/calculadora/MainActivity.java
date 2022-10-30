package com.pucpr.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText heightExitText;
    private EditText weightEditText;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        heightExitText = findViewById(R.id.heightEditText);
        weightEditText = findViewById(R.id.weightEditText);
        result = findViewById(R.id.bmiResultTextView);
    }

    public void calculate(View v) {
        String heightString = heightExitText.getText().toString();
        String weightString = weightEditText.getText().toString();

        if (heightString != null && !"".equals(heightString)
                && weightString != null && !"".equals(weightString)) {
            float weightValue = Float.parseFloat(weightString);
            float heightValue = Float.parseFloat(heightString) / 100;

            float bodyMassIndex = weightValue / (heightValue * heightValue);

            displayBodyMassIndex(bodyMassIndex);

        } else result.setText(R.string.error);
    }

    private void displayBodyMassIndex(Float bodyMassIndex) {
        String bmiText;

        if (Float.compare(bodyMassIndex, 15f) <= 0) {
            bmiText = getString(R.string.very_severely_underweight);
        } else if (Float.compare(bodyMassIndex, 15f) > 0 && Float.compare(bodyMassIndex, 16f) <= 0) {
            bmiText = getString(R.string.severely_underweight);
        } else if (Float.compare(bodyMassIndex, 16f) > 0 && Float.compare(bodyMassIndex, 18.5f) <= 0) {
            bmiText = getString(R.string.underweight);
        } else if (Float.compare(bodyMassIndex, 18.5f) > 0 && Float.compare(bodyMassIndex, 25f) <= 0) {
            bmiText = getString(R.string.normal);
        } else if (Float.compare(bodyMassIndex, 25f) > 0 && Float.compare(bodyMassIndex, 30f) <= 0) {
            bmiText = getString(R.string.overweight);
        } else if (Float.compare(bodyMassIndex, 30f) > 0 && Float.compare(bodyMassIndex, 35f) <= 0) {
            bmiText = getString(R.string.obese_class_i);
        } else if (Float.compare(bodyMassIndex, 35f) > 0 && Float.compare(bodyMassIndex, 40f) <= 0) {
            bmiText = getString(R.string.obese_class_ii);
        } else {
            bmiText = getString(R.string.obese_class_iii);
        }

        bmiText = bodyMassIndex + "\n\n" + bmiText;
        result.setText(bmiText);
    }
}