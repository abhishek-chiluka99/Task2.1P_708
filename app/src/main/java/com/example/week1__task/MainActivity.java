package com.example.week1__task;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.conversionUnits, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner fromSpinner = (Spinner) findViewById(R.id.spinner_from);
        Spinner toSpinner = (Spinner)findViewById(R.id.spinner_to);
        Button convert_button = findViewById(R.id.button_convert);
        EditText input_text_value = (EditText) findViewById(R.id.editTextText_from);
        TextView final_value = (TextView) findViewById(R.id.result_view);
        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);

        convert_button.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){

                String convert_unit_from = fromSpinner.getSelectedItem().toString();
                String convert_unit_to = toSpinner.getSelectedItem().toString();
                int flag =0;
                String input_value = input_text_value.getText().toString();

                if(input_value.isEmpty() || Double.parseDouble(input_value)==0){
                    flag = 1;
                    Toast.makeText(getApplicationContext(), "Kindly choose a valid input value", Toast.LENGTH_SHORT).show();
                }

                if(convert_unit_from == convert_unit_to){
                    flag = 1 ;
                    Toast.makeText(getApplicationContext(), "Please select different value for source/destination unit", Toast.LENGTH_SHORT).show();
                }

                double value = 0;
                try{
                    value = Double.parseDouble(input_value);
                }catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Please enter a valid input ", Toast.LENGTH_SHORT).show();
                    return;
                }

                double result = 0;

                if(convert_unit_from.equals("centimeter")){
                    switch(convert_unit_to) {
                        case "centimeter": result = value;
                                            break;
                        case "yard": result = value / 91.44;
                                     break;
                        case "foot": result = value / 30.48;
                                     break;
                        case "inch": result = value / 2.54;
                                      break;
                    }
                } else if (convert_unit_from.equals("foot")){
                    switch(convert_unit_to) {
                        case "centimeter": result = value * 30.48;
                            break;
                        case "yard": result = value / 3;
                            break;
                        case "foot": result = value ;
                            break;
                        case "inch": result = value * 12;
                            break;
                    }} else if(convert_unit_from.equals("yard")){
                        switch(convert_unit_to) {
                            case "centimeter": result = value * 91.44;
                                break;
                            case "yard": result = value ;
                                break;
                            case "foot": result = value * 3;
                                break;
                            case "inch": result = value * 36;
                                break;
                    }
                }else if(convert_unit_from.equals("inch")){
                    switch(convert_unit_to) {
                        case "centimeter": result = value * 2.54;
                            break;
                        case "yard": result = value / 2.54 ;
                            break;
                        case "foot": result = value * 12;
                            break;
                        case "inch": result = value ;
                            break;
                    }
                }else if(convert_unit_from.equals("kilogram")){
                    switch(convert_unit_to) {
                        case "kilogram": result = value ;
                            break;
                        case "gram": result = value * 1000 ;
                            break;
                        case "pound": result = value * 2.205;
                            break;
                        case "ounce": result = value * 35.274 ;
                            break;
                    }
                }else if(convert_unit_from.equals("gram")){
                    switch(convert_unit_to) {
                        case "kilogram": result = value / 1000;
                            break;
                        case "gram": result = value  ;
                            break;
                        case "pound": result = value / 453.592;
                            break;
                        case "ounce": result = value / 28.35 ;
                            break;
                    }
                } else if(convert_unit_from.equals("pound")){
                    switch(convert_unit_to) {
                        case "kilogram": result = value / 2.205;
                            break;
                        case "gram": result = value * 453.592  ;
                            break;
                        case "pound": result = value ;
                            break;
                        case "ounce": result = value * 16 ;
                            break;
                    }
                }else if(convert_unit_from.equals("ounce")){
                    switch(convert_unit_to) {
                        case "kilogram": result = value / 35.274;
                            break;
                        case "gram": result = value * 28.35 ;
                            break;
                        case "pound": result = value / 16;
                            break;
                        case "ounce": result = value  ;
                            break;
                    }
                } else if(convert_unit_from.equals("celsius")){
                    if(convert_unit_to.equals("celsius")){
                        result = value;
                    }else if(convert_unit_to.equals("fahrenheit")){
                        result = value * 1.8 + 32;
                    }
                }else if(convert_unit_from.equals("fahrenheit")){
                    if(convert_unit_to.equals("celsius")){
                        result = (value - 32)/1.8;
                    }else if(convert_unit_to.equals("fahrenheit")){
                        result = value;
                    }
                }

                if(result == 0){
                    Toast.makeText(getApplicationContext(), "Please choose a valid conversion values", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(flag==0) {
                    final_value.setText(String.valueOf(result));
                    Toast.makeText(getApplicationContext(), String.valueOf(result), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    }