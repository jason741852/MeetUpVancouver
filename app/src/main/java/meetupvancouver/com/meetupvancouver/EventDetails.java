package meetupvancouver.com.meetupvancouver;

import android.content.Intent;
import android.os.Debug;
import android.provider.CalendarContract;
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

import java.util.Calendar;


public class EventDetails extends AppCompatActivity {

	Button submitbutton;
	EditText EventName;
	EditText HostName;
    EditText Location;
    EditText EventDescription;
    EditText Time;
    EditText Date;
    long EventCounter;

    private static final String TAG = meetupvancouver.com.meetupvancouver.EventDetails.class.getSimpleName();
    Firebase firebaseReference;

    //DatabaseReference database = FirebaseDatabase.getInstance().getReference();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        EventName = (EditText) findViewById(R.id.EventName);
        HostName = (EditText) findViewById(R.id.HostName);
        Location = (EditText) findViewById(R.id.Location);
        EventDescription = (EditText) findViewById(R.id.EventDescription);
        Time = (EditText) findViewById(R.id.Time);
        Date = (EditText) findViewById(R.id.Date);
        submitbutton = (Button) findViewById(R.id.button2);

        Firebase.setAndroidContext(this);

        submitbutton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
                Firebase myFirebaseRef = new Firebase("https://my-project-1483693737987.firebaseio.com/");

                    myFirebaseRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            EventCounter = dataSnapshot.child("Events").getChildrenCount();
                            EventCounter++;
                            Log.d("Countinsnapshot: ",String.valueOf(EventCounter));
                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                });



                    Log.d("Count: ",String.valueOf(EventCounter));
				myFirebaseRef.child("Events").child(String.valueOf(EventCounter)).child("EventID").setValue(EventName.getText().toString());
				myFirebaseRef.child("Events").child(String.valueOf(EventCounter)).child("HostName").setValue(HostName.getText().toString());
                myFirebaseRef.child("Events").child(String.valueOf(EventCounter)).child("Location").setValue(Location.getText().toString());
                myFirebaseRef.child("Events").child(String.valueOf(EventCounter)).child("EventDescription").setValue(EventDescription.getText().toString());
                myFirebaseRef.child("Events").child(String.valueOf(EventCounter)).child("Time").setValue(Time.getText().toString());
                myFirebaseRef.child("Events").child(String.valueOf(EventCounter)).child("Date").setValue(Date.getText().toString());

                    //DatabaseReference myRef1.setValue("Hello, World!");

//                    myFirebaseRef.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            Log.d("Count: ",(String) dataSnapshot.child("Events").child("1").child("Name").getValue());
//                        }
//
//                        @Override
//                        public void onCancelled(FirebaseError firebaseError) {
//
//                        }
//                    });
//                    Intent i  = new Intent(EventDetails.this, CalendarActivity.class);
//                    startActivity(i);
				}


			});





    }
    public void onAddEventClicked(View view){
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.item/event");

        Calendar cal = Calendar.getInstance();
//        long startTime = cal.getTimeInMillis();
//        long endTime = cal.getTimeInMillis()  + 60 * 60 * 1000;
//
//        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime);
//        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,endTime);
//        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

        intent.putExtra(CalendarContract.Events.TITLE, EventName.getText().toString());
        intent.putExtra(CalendarContract.Events.DESCRIPTION,  EventDescription.getText().toString());
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, Location.getText().toString());
//        intent.putExtra(CalendarContract.Events.RRULE, "FREQ=YEARLY");

        startActivity(intent);
    }
}
