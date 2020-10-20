package com.example.paathshaala;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StudentHome extends AppCompatActivity {
EditText e1;
String ans="";
int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
        e1=(EditText)findViewById(R.id.editText3);

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



    public void onfind (View view){

        flag=0;
       final String s=e1.getText().toString();


        //--------------------------search Course  in firebase ---------------------

        final List<String> valuelist=new ArrayList<>();
        final List<String> keylist=new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref1 = database.getReference().child("NewsFeed");

        try {
            Query q=ref1.orderByKey();
            // For counting Users
            q.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot sn : dataSnapshot.getChildren()) {
                        PostCourseinfo p1=sn.getValue(PostCourseinfo.class);
                        if (p1.getCourse1().equals(s)) {
                            String c1 = p1.getCourse1();
                            String n1=p1.getName1();
                            String e1=p1.getEmail();
                            String d1=p1.getDate1();
                            String k1=sn.getKey();
                            valuelist.add("Key:"+k1+"\n"+"Course Name:"+c1+"    "+"Instructor:"+n1+"\n"+"Email:"+e1+"    "+"Date:"+d1);


                            keylist.add(k1);


                        }



                        /*
                        if (p1.getCourse1().equals(s)){
                         ans+=sn.getKey();
                           ans+="||";
                        }

                        e1.setText(ans);

                        */
                    }
                   // e1.setText(keylist.get(1));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, valuelist);

            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);


listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {




      //  Toast.makeText(getApplicationContext(),String.valueOf(keylist.get(position)),Toast.LENGTH_LONG).show();

    }
});












        }catch(Exception ex){}


        //---------------------------------------------------------------------------

    }
    public void onFindInstructor(View view)
    {
        final String s=e1.getText().toString();
        flag=1;

        //--------------------------search Course  in firebase ---------------------

        final List<String> valuelist=new ArrayList<>();
        final List<String> keylist=new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref1 = database.getReference().child("Studentinfo");

        try {
            Query q=ref1.orderByKey();
            // For counting Users
            q.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot sn : dataSnapshot.getChildren()) {
                        Studentinfo p1=sn.getValue(Studentinfo.class);
                        if (p1.getSkills().contains(s)) {
                            String c1=s;
                            String n1=p1.getName();
                            String e1=sn.getKey()+"@iiitd.ac.in";
                            String k1=sn.getKey();

                            valuelist.add("Instructor:"+n1+"\n"+"Email:"+e1+"    "+"Course:"+c1);


                            keylist.add(k1);


                        }



                        /*
                        if (p1.getCourse1().equals(s)){
                         ans+=sn.getKey();
                           ans+="||";
                        }

                        e1.setText(ans);

                        */
                    }
                    // e1.setText(keylist.get(1));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, valuelist);

            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                    Toast.makeText(getApplicationContext(),String.valueOf(keylist.get(position)),Toast.LENGTH_LONG).show();
                    Intent i=new Intent(getApplicationContext(),showStudentDetail.class);
                    i.putExtra("key",keylist.get(position));
                    startActivity(i);
                }
            });












        }catch(Exception ex){}



    }

public void onEnroll(View view){


    final String s2=e1.getText().toString();
    SharedPreferences sp=getSharedPreferences("sp1",MODE_PRIVATE);
   final String s1 =sp.getString("email","");



    String semail ="";
    for(int i=0;i<s1.length();i++)
    {
        if(s1.charAt(i)!='@')
        {
            semail=semail+s1.charAt(i);
        }
        else
        {
            break;
        }
    }

   final String temp=semail;




    FirebaseDatabase database = FirebaseDatabase.getInstance();
  final   DatabaseReference ref1 = database.getReference().child("NewsFeed").child(s2);



    try {
        Query q=ref1.orderByKey();
        // For counting Users
        q.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                    PostCourseinfo p1=dataSnapshot.getValue(PostCourseinfo.class);

              String a= String.valueOf(Integer.parseInt(p1.getTotalstudents())+1);

                   // e1.setText(ans);
                p1.setTotalstudents(a);
                p1.setStudentname(p1.getStudentname()+"||"+temp);
                ref1.setValue(p1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }catch(Exception ex){}

//----------------------------------update student info-------------------
    final   DatabaseReference ref2 = database.getReference().child("Studentinfo").child(temp);
    try {
        Query q=ref2.orderByKey();
        // For counting Users
        q.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            Studentinfo p1=dataSnapshot.getValue(Studentinfo.class);

               p1.setCoursetaken(p1.getCoursetaken()+"||"+s2);



                ref2.setValue(p1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }catch(Exception ex){}







}




}
