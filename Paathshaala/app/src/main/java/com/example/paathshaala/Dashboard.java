package com.example.paathshaala;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity {
    TextView numusers;
    TextView rating2;
    EditText rate;
    Button rateButton;
    AlertDialog.Builder builder;
    private String m_Text="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        numusers=(TextView)findViewById(R.id.tvusercount);
        rating2=(TextView)findViewById(R.id.edrating2);
        rate=(EditText)findViewById(R.id.edrating);
        rateButton=(Button)findViewById(R.id.brate);
        builder = new AlertDialog.Builder(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref1 = database.getReference().child("Dashboard").child("users");
        DatabaseReference ref2 = database.getReference().child("Dashboard").child("Rating");
        try {
            Query q=ref1.orderByKey();
            Query q1=ref2.orderByKey();
            // For counting Users
            q.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot sn : dataSnapshot.getChildren()) {
                        String count=String.valueOf(sn.getValue());
                        numusers.setText(count);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


                public void onCancelled(FirebaseError firebaseError) {

                }
            });
            //For Overall Rating
            q1.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot sn : dataSnapshot.getChildren()) {
                        String count=String.valueOf(sn.getValue());
                        rating2.setText(count);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }catch(Exception ex){}

        feedback();
    }

    public void feedback()
    {
        //final String[] feedbacks = new String[Integer.parseInt(numusers.getText().toString())];
        final List<String> feedbacks=new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        final DatabaseReference ref2 = database.getReference().child("Dashboard").child("feedback");
        Query q1=ref2.orderByKey();
        q1.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i=0;
                for (DataSnapshot sn : dataSnapshot.getChildren()) {
                    String feed =String.valueOf(sn.getValue());
                    String name=String.valueOf(sn.getKey());
                    feedbacks.add(name+":\n"+feed);
                    i=i++;
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, feedbacks);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    public void rate(View view)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        final DatabaseReference ref2 = database.getReference().child("Dashboard").child("Rating");
        Query q1=ref2.orderByKey();
        q1.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot sn : dataSnapshot.getChildren()) {
                    String count =String.valueOf(sn.getValue());
                    float count1=(Float.parseFloat(rate.getText().toString())+Float.parseFloat(count))/2;
                    rating2.setText(String.valueOf(count1));
                    ref2.child("OverallRating").setValue(String.valueOf(count1));
                }

                rate.setEnabled(false);
                rateButton.setEnabled(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void submitFeedback(View view)
    {
        //Uncomment the below code to Set the message and title from the strings.xml file
        builder.setTitle("Submit Feedback");


        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_LONG_MESSAGE);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String feedback1 = input.getText().toString();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref1 = database.getReference().child("Dashboard").child("feedback").child("Ganesh");
                ref1.setValue(feedback1);
                feedback();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

}