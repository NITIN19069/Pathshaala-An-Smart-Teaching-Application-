package com.example.paathshaala;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class PostCourse extends AppCompatActivity {
    EditText name,course,venue,timings,duration,fees,date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_course);

        name=(EditText)findViewById(R.id.name);
        course=(EditText)findViewById(R.id.course);
        venue=(EditText)findViewById(R.id.venue);
        timings=(EditText)findViewById(R.id.timings);
        duration=(EditText)findViewById(R.id.duration);
        fees=(EditText)findViewById(R.id.fees);
        date=(EditText)findViewById(R.id.date);
    }

    public void post(View view)
    {SharedPreferences sp=getSharedPreferences("sp1",MODE_PRIVATE);
        String s1=sp.getString("email","");
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
final  String s3=s2;

        // String name1,course1,venue1,timings1,duration1,fees1;
        PostCourseinfo n1=new PostCourseinfo();
        n1.setName1(name.getText().toString());
        n1.setEmail(s1);
        n1.setCourse1(course.getText().toString());
        n1.setTotalstudents("0");
        n1.setStudentname("");
        n1.setVenue1(venue.getText().toString());
        n1.setTimings1(timings.getText().toString());
        n1.setDuration1(duration.getText().toString());
        n1.setFees1(fees.getText().toString());
        n1.setDate1(date.getText().toString());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref1 = database.getReference().child("NewsFeed").push();
        ref1.setValue(n1);
        String tempo=ref1.getKey();
        updateInfokey(tempo,s3);



    }

    public void updateInfokey(final String lastkey, String s2)
    {
        //-----------update in student info for coursepublised --------------------
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final   DatabaseReference ref2 = database.getReference().child("Studentinfo").child(s2);
        try {
            Query q=ref2.orderByKey();
            // For counting Users
            q.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    Studentinfo p1=dataSnapshot.getValue(Studentinfo.class);

                    p1.setCoursepublished(p1.getCoursepublished()+"||"+lastkey);



                    ref2.setValue(p1);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


                public void onCancelled(FirebaseError firebaseError) {

                }
            });

        }catch(Exception ex){}


//--------------------------------------------------------


    }
}

