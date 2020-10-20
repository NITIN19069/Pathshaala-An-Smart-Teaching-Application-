package com.example.paathshaala;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class showStudentDetail extends AppCompatActivity {
    TextView course_published,dob,experience,gender,student_name,contact_number,qualification,skills;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_student_detail);
        course_published=(TextView)findViewById(R.id.course_published_value);
        dob=(TextView)findViewById(R.id.dob_value);
        experience=(TextView)findViewById(R.id.experiance_value);
        gender=(TextView)findViewById(R.id.gender_value);
        student_name=(TextView)findViewById(R.id.stud_name_value);
        contact_number=(TextView)findViewById(R.id.phone_value);
        qualification=(TextView)findViewById(R.id.qualification_value);
        skills=(TextView)findViewById(R.id.skills_value);

        //------------Fetch info From Db--------
        Intent i=getIntent();
        String key=i.getStringExtra("key");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref1 = database.getReference().child("Studentinfo").child(key);

        try {
            Query q = ref1.orderByKey();
            // For counting Users
            q.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    Studentinfo p1 = dataSnapshot.getValue(Studentinfo.class);
                    course_published.setText(p1.getCoursepublished());
                    dob.setText(p1.getDOB());
                    experience.setText(p1.getExperience());
                    gender.setText(p1.getGender());
                    student_name.setText(p1.getName());
                    contact_number.setText(p1.getPhoneNo());
                    qualification.setText(p1.getQualification());
                    skills.setText(p1.getSkills());

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


                public void onCancelled(FirebaseError firebaseError) {

                }
            });


        }catch(Exception ex){}   //----------------------------------------
        }
}
