package com.example.paathshaala;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class  Signup_Userinfo extends AppCompatActivity {

    final Calendar mCalender = Calendar.getInstance();

    private Button mSignUp_btn;
    private DatabaseReference mDatabase;
    private EditText mName, mPhoneNo, mDOB, mSkills, mExperience;
    private String mGender;
    private Spinner mQualification;
    private String text;
    Studentinfo studentInfo;

    public void onRadioButtonClicked(View v) {

        boolean checked = ((RadioButton) v).isChecked();
        switch (v.getId()) {
            case R.id.radioMale:
                if (checked) {
                    mGender = "Male";
                    break;
                }
            case R.id.radioFemale:
                if (checked) {
                    mGender = "Female";
                }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup__userinfo);
        mSignUp_btn = (Button) findViewById(R.id.btnSignUp);
        mName = (EditText) findViewById(R.id.edName);
        mPhoneNo = (EditText) findViewById(R.id.edPhoneno);
        mDOB = (EditText) findViewById(R.id.edDOB);
        mQualification = (Spinner) findViewById(R.id.spinQual);
        mSkills = (EditText) findViewById(R.id.edSkills);
        mExperience = (EditText) findViewById(R.id.edExp);

        studentInfo = new Studentinfo();
        mSignUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref1 = database.getReference().child("Studentinfo");
                DatabaseReference myRef = database.getReference();
                studentInfo.setName(mName.getText().toString().trim());
                studentInfo.setDOB(mDOB.getText().toString().trim());
                studentInfo.setPhoneNo(mPhoneNo.getText().toString().trim());
                text = mQualification.getSelectedItem().toString();
                studentInfo.setQualification(text);
                studentInfo.setCoursepublished("");
                studentInfo.setCoursetaken("");
                studentInfo.setSkills(mSkills.getText().toString().trim());
                studentInfo.setExperience(mExperience.getText().toString().trim());
                studentInfo.setGender(mGender);
                Intent it=getIntent();

                 String s1=it.getStringExtra("email");
                 String s2="";
                 for(int i=0;i<s1.length();i++)
                 {
                     if(s1.charAt(i)!='@')
                     {
                         s2=s2+s1.charAt(i);
                     }
                     else
                     {
                         break;
                     }
                 }


                myRef.child("Studentinfo").child(s2).setValue(studentInfo);


                Intent intToHome=new Intent(Signup_Userinfo.this,MainActivity.class);
                startActivity(intToHome);

            }
        });
        mPhoneNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!validatePhoneNo()){
                    mSignUp_btn.setEnabled(false);
                } else {
                    mSignUp_btn.setEnabled(true);
                }

            }



            @Override
            public void afterTextChanged(Editable editable) {
                if(validatePhoneNo()){

                }
                else{
                    Toast.makeText(getApplicationContext(), "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                }
            }
        });



        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                mCalender.set(Calendar.YEAR,year);
                mCalender.set(Calendar.MONTH,month);
                mCalender.set(Calendar.DAY_OF_MONTH,day);

                String mFormat = "DD/MM/YYYY";
                SimpleDateFormat sdf = new SimpleDateFormat(mFormat);
                mDOB.setText(sdf.format(mCalender.getTime()));
            }
        };

        mDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Signup_Userinfo.this, date, mCalender.get(Calendar.YEAR), mCalender.get(Calendar.MONTH), mCalender.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }



    private boolean validatePhoneNo(){
        String PHONE_PATTERN = "[789][0-9]{9}";
        String phoneno =  mPhoneNo.getText().toString().trim();

        Pattern phonePattern = Pattern.compile(PHONE_PATTERN);
        Matcher match = phonePattern.matcher(phoneno);

        return match.matches();
    }
}


