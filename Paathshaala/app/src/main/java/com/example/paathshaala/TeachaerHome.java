package com.example.paathshaala;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class TeachaerHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachaer_home);


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
    public void onDashboard(View view)
    {
        Intent das=new Intent(this,Dashboard.class);
        startActivity(das);
    }


   public void onPostCourse(View view){

       Intent das=new Intent(this,PostCourse.class);
       startActivity(das);

   }

}
