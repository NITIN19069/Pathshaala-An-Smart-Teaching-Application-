package com.example.paathshaala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    public void onStudent(View view)
    {
        Intent das=new Intent(this,StudentHome.class);
        startActivity(das);
    }
    public void onTeacher(View view)
    {
        Intent das=new Intent(this,TeachaerHome.class);
        startActivity(das);
    }


    public void onProfile(View view)
    {
        Intent das=new Intent(this,Profile.class);
        startActivity(das);
    }
    public void onDashboard(View view)
    {
        Intent das=new Intent(this,Dashboard.class);
        startActivity(das);
    }

}
