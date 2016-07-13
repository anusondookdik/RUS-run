package com.duangsangrit.anuson.rusrun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.RenamingDelegatingContext;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SignUpActivity extends AppCompatActivity {



    //Explicit

    private EditText nameEditText, userEditText, passwordEditext;
    private RadioGroup RadioGroup;
    private RadioButton  avata0RedioButton,avata1RedioButton ,avata2RedioButton,avata3RedioButton,
            avata4RedioButton;
    private String nameString, userString, passwordString, avataString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        //Bind Widget
        nameEditText = (EditText)findViewById(R.id.editText);
        userEditText = (EditText)findViewById(R.id.editText2);
        passwordEditext =(EditText)findViewById(R.id.editText3);
        RadioGroup = (RadioGroup) findViewById(R.id.radAvsta);
        avata0RedioButton = (RadioButton)findViewById(R.id.radioButton);
        avata1RedioButton = (RadioButton)findViewById(R.id.radioButton2);
        avata2RedioButton = (RadioButton)findViewById(R.id.radioButton3);
        avata3RedioButton = (RadioButton)findViewById(R.id.radioButton4);
        avata4RedioButton = (RadioButton)findViewById(R.id.radioButton5);



    } //Main Method


    public void  clickSignUpSign(View view ) {

        // Get value from Edit Text

        nameString = nameEditText.getText().toString().trim();
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditext.getText().toString().trim();

        //Check Space
        if (nameString.equals("") || userString.equals("") || passwordString.equals("")) {

            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "มีช่องว่าง","กรุณากรอกทุกช่อง อิสัส");
        }

    } //clickSignUp

}  // Main Class
