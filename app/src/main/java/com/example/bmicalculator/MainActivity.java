package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity {
TextView textWeight,textHeight,textResult1,textResult2;
EditText editWeight,editHeight;
Button btncal,btnclear;

float weight;
float height;
float result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textWeight=(TextView) findViewById(R.id.textWeight);
        textHeight=(TextView) findViewById(R.id.textHeight);
        textResult1=(TextView) findViewById(R.id.textResult1);
        textResult2=(TextView) findViewById(R.id.textResult2);

        editHeight=(EditText) findViewById(R.id.editHeight);
        editWeight=(EditText) findViewById(R.id.editWeight);

        btncal=(Button) findViewById(R.id.btncal);
        btnclear=(Button) findViewById(R.id.btnclear);

       try{


               btncal.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       if(!"".contentEquals(editHeight.getText().toString())&&!"".contentEquals(editWeight.getText().toString())) {


                           weight = Float.parseFloat(editWeight.getText().toString());
                           height = Float.parseFloat(editHeight.getText().toString());
                           result = weight / (height * height);
                           textResult1.setText(String.valueOf(result));

                           if (result >= 25) {
                               textResult2.setText("OverWeight");
                           } else if (result < 18) {
                               textResult2.setText("UnderWeight");
                           } else {
                               textResult2.setText("Normal");
                           }



                       switch (view.getId()) {
                           case R.id.btncal:
                               hideKeybaord(view);
                               break;
                       }


                       }
                       else{
                           Toasty.warning(MainActivity.this,
                                   "PLease fill required fields", Toast.LENGTH_LONG).show();
                       }
                   }
               });


       }catch (Exception e){
           Toast.makeText(MainActivity.this,
                   e.getMessage(), Toast.LENGTH_LONG).show();
       }


       btnclear.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               editHeight.setText("");
               editWeight.setText("");
               textResult1.setText("");
               textResult2.setText("");

           }
       });


    }
    private void hideKeybaord(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }
}