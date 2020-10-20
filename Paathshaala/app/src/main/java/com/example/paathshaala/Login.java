package com.example.paathshaala;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
EditText e1,e2;
    EditText email, password;
    FirebaseAuth mFirebaseAuth;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        email=(EditText)findViewById(R.id.editText);
        password=(EditText)findViewById(R.id.editText2);
        mAuth = FirebaseAuth.getInstance();
    }
    public void onlogin(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref1 = database.getReference().child("Studentinfo");
        //-----------------insertion---------------------------------
//        //Firebase ref = new Firebase("https://geca-b330c.firebaseio.com/");
//
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference();
//        Studentinfo s1= new Studentinfo();
//        s1.setEmail(e1.getText().toString());
//        s1.setS1(e2.getText().toString());
//
//        myRef.child("Studentinfo").child("1").setValue(s1);
//        Toast.makeText(this,"done",Toast.LENGTH_SHORT).show();
//--------------------------end insertion--------------------------------


        String email = "akshaj19111@iiitd.ac.in";
        String password = "ganesh";

        //-------------------------signup--------------------------
//        mAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//
//                            Log.d("abc", "createUserWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//
//                        } else {
//
//                            Toast.makeText(Login.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//
//                        }
//
//
//                    }
//                });


        //-----------------------------end signup-----------------------------


        //--------------------------------__signin-----------------


//        mAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d("hdsjhf", "signInWithEmail:success");
//                            Toast.makeText(Login.this, "Sucess.",
//                                    Toast.LENGTH_SHORT).show();
//                            FirebaseUser user = mAuth.getCurrentUser();
//
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w("dsafs", "signInWithEmail:failure", task.getException());
//                            Toast.makeText(Login.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//
//                        }
//
//                        // ...
//                    }
//                });

//----------------------------------end sign in----------------------------------------

//-----------------------------get use info-----------------------
//
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//            // Name, email address, and profile photo Url
//            String name = user.getDisplayName();
//            String email1 = user.getEmail();
//
//
//            // Check if user's email is verified
//            boolean emailVerified = user.isEmailVerified();
//
//            String uid = user.getUid();
      //  }

    //-----------------------------------------------------------

        //---------------------count users----------------------













//
//
//try{
//
//        Query q=ref1.orderByKey();
//
//
//        q.addListenerForSingleValueEvent(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                for(DataSnapshot sn:dataSnapshot.getChildren())
//                {
//                 Studentinfo p1=sn.getValue( Studentinfo.class);
//
//                    if(p1.getEmail().equals("Name32") ){
//
//
//                        Toast.makeText(Login.this, "when got="+p1.getS1(), Toast.LENGTH_SHORT).show();
//                            break;
//
//                    }else {
//
//                        Toast.makeText(Login.this, "not got=", Toast.LENGTH_SHORT).show();
//
//                    }
//
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//
//
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//    }
//        catch (Exception e)
//    {
//        Toast.makeText(Login.this," here exception : "+e,Toast.LENGTH_LONG).show();
//
//    }
}
















    }


