package com.example.p4paysecurepayment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Pin_verification extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4;
    String pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_verification);
        ed1=(EditText)findViewById(R.id.et_1);
        ed2=(EditText)findViewById(R.id.et_2);
        ed3=(EditText)findViewById(R.id.et_3);
        ed4=(EditText)findViewById(R.id.et_4);

        ed1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (ed1.getText().length() == 1){
                    ed2.setEnabled(true);
                    ed2.requestFocus();
                }else{
                    ed2.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                pin = ed1.getText().toString();
            }
        });
        ed2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (ed2.getText().length() == 1){
                    ed3.setEnabled(true);
                    ed3.requestFocus();
                }else {
                    ed2.setEnabled(false);
                    ed1.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                pin = pin+ed2.getText().toString();
            }
        });
        ed3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (ed3.getText().length() == 1){
                    ed4.setEnabled(true);
                    ed4.requestFocus();
                }else {
                    ed3.setEnabled(false);
                    ed2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                pin = pin+ed3.getText().toString();
            }
        });
        ed4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (ed4.getText().length() == 1){
                    //ed2.requestFocus();
                }else{
                    ed4.setEnabled(false);
                    ed3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                pin = pin+ed4.getText().toString();
                Toast.makeText(getApplicationContext(),pin,Toast.LENGTH_SHORT).show();
            }
        });

    }
}

