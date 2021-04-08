package metehanersoy.bmi_calculator_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.MathContext;

public class MainActivity extends AppCompatActivity {
    float weight;
    float height;
    float result;

    TextView tw_Result;
    EditText tw_weight;
    EditText tw_height;


    Button  button_calculate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("BMI Calculator");

        tw_Result = findViewById(R.id.textView_Result);
        tw_weight = findViewById(R.id.editTextTextWeight);
        tw_height = findViewById(R.id.editTextTextHeight);
        button_calculate =  findViewById(R.id.button);



        button_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!findViewById(R.id.editTextTextWeight).toString().isEmpty() && !findViewById(R.id.editTextTextHeight).toString().isEmpty()) {
                    try {
                        weight =Float.parseFloat(tw_weight.getText().toString());
                        height = Float.parseFloat(tw_height.getText().toString());
                        height /= 100;

                        result = calculateBMI ( weight,  height);

                        //Rounding the value
                        BigDecimal bd = new BigDecimal(result);
                        bd = bd.round(new MathContext(3));
                        result = bd.floatValue();

                        tw_Result.setText(Float.toString(result) +" : " + interpretBMI(result));

                        tw_weight.setText("");
                        tw_height.setText("");

                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "You must enter number!", Toast.LENGTH_SHORT).show();
                        tw_weight.setText("");
                        tw_height.setText("");
                    }



                }




            }
        });

    }


    private float calculateBMI (float weight, float height) {
        return (float) (weight / (height * height));
    }
    private String interpretBMI(float bmiValue) {
        if (bmiValue < 16) {
            return "Severely underweight";
        } else if (bmiValue < 18.5) {
            return "Underweight";
        } else if (bmiValue < 25) {
            return "Normal";
        } else if (bmiValue < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }}

}