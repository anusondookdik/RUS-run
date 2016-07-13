package com.duangsangrit.anuson.rusrun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.RenamingDelegatingContext;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SignUpActivity extends AppCompatActivity {



    //Explicit

    private EditText nameEditText, userEditText, passwordEditext;
    private RadioGroup radioGroup;
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
        radioGroup = (RadioGroup) findViewById(R.id.radAvsta);
        avata0RedioButton = (RadioButton)findViewById(R.id.radioButton);
        avata1RedioButton = (RadioButton)findViewById(R.id.radioButton2);
        avata2RedioButton = (RadioButton)findViewById(R.id.radioButton3);
        avata3RedioButton = (RadioButton)findViewById(R.id.radioButton4);
        avata4RedioButton = (RadioButton)findViewById(R.id.radioButton5);

        //Radio Controller

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {

                    case R.id.radioButton:

                        avataString = "0";
                        break;
                    case R.id.radioButton2:

                        avataString = "1";
                        break;
                    case R.id.radioButton3:

                        avataString = "2";
                        break;
                    case R.id.radioButton4:

                        avataString ="3";
                        break;
                    case R.id.radioButton5:

                        avataString ="4";
                        break;

                }   //switch

            }
        });


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

        } else if (checkChoose()) {
            //Checked
        } else {

            //Un Check
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog (this, "ยังไม่เลือก Avata","กรุณาเลือก Avata อิสัส ");


        }




    } //clickSignUp

    private boolean checkChoose() {

        boolean status = true;

        status = avata0RedioButton.isChecked() ||
                avata1RedioButton.isChecked() ||
                avata2RedioButton.isChecked() ||
                avata3RedioButton.isChecked() ||
                avata4RedioButton.isChecked();

        Log.d("test","stauts == " + status);

        return status;
    }

}  // Main Class
