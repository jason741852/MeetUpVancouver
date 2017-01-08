package meetupvancouver.com.meetupvancouver;

import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseTest extends AppCompatActivity {

    Button submitbutton;
    EditText example;
    private static final String TAG = FirebaseTest.class.getSimpleName();
    Firebase firebaseReference;

    //DatabaseReference database = FirebaseDatabase.getInstance().getReference();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_test);

        example = (EditText) findViewById(R.id.editText);
        submitbutton = (Button) findViewById(R.id.submitbutton);
//        firebaseReference = new Firebase("https://my-project-1483693737987.firebaseio.com/data/type");
//        firebaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String text = dataSnapshot.getValue(String.class);
//                Log.d(TAG,text);
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });

        Firebase.setAndroidContext(this);

        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,example.toString());

                Firebase myFirebaseRef = new Firebase("https://my-project-1483693737987.firebaseio.com/");
                myFirebaseRef.child("Events").child("1").child("EventID").setValue(example.getText().toString());
                myFirebaseRef.child("Events").child("1").child("Name").setValue(example.getText().toString());
                //DatabaseReference myRef1.setValue("Hello, World!");

                myFirebaseRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.d("Count: ",(String) dataSnapshot.child("Events").child("1").child("Name").getValue());
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });

            }
        });




    }





}
