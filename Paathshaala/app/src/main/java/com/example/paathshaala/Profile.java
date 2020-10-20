package com.example.paathshaala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

public class Profile extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPreferences sp=getSharedPreferences("sp1",MODE_PRIVATE);
        String s1=sp.getString("email","");

    }

    public void onLogout(View view)
    {
        SharedPreferences sp=getSharedPreferences("sp1",MODE_PRIVATE);
    SharedPreferences.Editor ed=sp.edit();
    ed.putString("email","");
    ed.commit();

        Intent intentTomain=new Intent(this,MainActivity.class);
        startActivity(intentTomain);

    }

}
