package com.example.paathshaala;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.dynamitechetan.flowinggradient.FlowingGradientClass;

public class MainActivity extends AppCompatActivity {
    EditText email, password;
    Button btnSignIn;
    TextView tvSignIn;
    FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if(android.os.Build.VERSION.SDK_INT >=21)
        {
            getWindow().setStatusBarColor(getResources().getColor(R.color.purple_500));
        }

        SharedPreferences sp=getSharedPreferences("sp1",MODE_PRIVATE);
        String s1=sp.getString("email","");
        if(s1.length()==0){}
        else {
            Intent intentToHome=new Intent(MainActivity.this,Home.class);
            startActivity(intentToHome);
        }


        mFirebaseAuth=FirebaseAuth.getInstance();


        RelativeLayout rl = (RelativeLayout) findViewById(R.id.activity_main);
        FlowingGradientClass grad = new FlowingGradientClass();
        grad.setBackgroundResource(R.drawable.translate)
                .onRelativeLayout(rl)
                .setTransitionDuration(4000)
                .start();



        email=(EditText)findViewById(R.id.email_edit_text_signup);
        password=(EditText)findViewById(R.id.password_edit_text_signup);
        btnSignIn=(Button)findViewById(R.id.sign_in_btn);
        tvSignIn=(TextView)findViewById(R.id.tv_signin);

       // Intent it = new Intent(this.getApplicationContext(), Login.class);
       // startActivity(it);



        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent=new Intent(MainActivity.this,Signup.class);
                startActivity(loginIntent);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onLogin(View view)
    {
        String emailId=email.getText().toString();
        String pwd=password.getText().toString();


        if(emailId.isEmpty())
        {
            email.setError("Please enter the email id");
            email.requestFocus();
        }
        else if(pwd.isEmpty())
        {
            password.setError("Please enter the password");
            password.requestFocus();
        }
        else if(emailId.isEmpty()&& pwd.isEmpty())
        {
            Toast.makeText(MainActivity.this,"Fields are empty!!", Toast.LENGTH_SHORT).show();
        }
        else if(!(emailId.isEmpty()&& pwd.isEmpty()))
        {
            mFirebaseAuth.signInWithEmailAndPassword(emailId,pwd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful())
                    {
                        Toast.makeText(MainActivity.this,"Incorrect Password!!",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {

                       //for login

                        SharedPreferences sp=getSharedPreferences("sp1",MODE_PRIVATE);

                        String s1=sp.getString("email","");

                        SharedPreferences.Editor ed=sp.edit();
                        ed.putString("email",email.getText().toString());

                       ed.commit();


                        Intent intentToHome=new Intent(MainActivity.this,Home.class);
                        startActivity(intentToHome);
                    }
                }
            });
        }
        else
        {
            Toast.makeText(MainActivity.this,"Error occured! :(", Toast.LENGTH_SHORT).show();
        }

    }


}
