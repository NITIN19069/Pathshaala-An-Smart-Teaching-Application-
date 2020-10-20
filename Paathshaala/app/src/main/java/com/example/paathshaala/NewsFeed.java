package com.example.paathshaala;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class NewsFeed extends AppCompatActivity {
    EditText name,course,venue,timings,duration,fees,date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);

        name=(EditText)findViewById(R.id.name);
        course=(EditText)findViewById(R.id.course);
        venue=(EditText)findViewById(R.id.venue);
        timings=(EditText)findViewById(R.id.timings);
        duration=(EditText)findViewById(R.id.duration);
        fees=(EditText)findViewById(R.id.fees);
        date=(EditText)findViewById(R.id.date);
    }

    public void post(View view)
    {
       // String name1,course1,venue1,timings1,duration1,fees1;
        NewsFeeds n1=new NewsFeeds();
        n1.setName1(name.getText().toString());
        n1.setCourse1(course.getText().toString());
        n1.setVenue1(venue.getText().toString());
        n1.setTimings1(timings.getText().toString());
        n1.setDuration1(duration.getText().toString());
        n1.setFees1(fees.getText().toString());
        n1.setDate1(date.getText().toString());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref1 = database.getReference().child("NewsFeed").child(n1.getName1());
        ref1.setValue(n1);


    }
}
