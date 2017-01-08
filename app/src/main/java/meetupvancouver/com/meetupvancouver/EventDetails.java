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
import android.content.Intent;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.api.client.util.DateTime;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.view.Menu;
import android.view.MenuItem;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.TimePicker;
import android.widget.DatePicker;
import java.lang.Object.*;
import java.util.Date;

import java.util.GregorianCalendar;

public class EventDetails extends AppCompatActivity implements View.OnClickListener{

	Button submitbutton;
    Button cancelbutton;
	EditText EventName;
	EditText HostName;
    EditText Location;
    EditText EventDescription;
    EditText Time;
    EditText Date;
    LatLng location;
    Long EventCounter = 0L;

    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;

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
        cancelbutton = (Button) findViewById(R.id.button3) ;
        Intent intent = getIntent();
        location = intent.getParcelableExtra("point");

        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        txtDate=(EditText)findViewById(R.id.in_date);
        txtTime=(EditText)findViewById(R.id.in_time);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);

        Firebase.setAndroidContext(this);

        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EventDetails.this, MapsActivity.class);
                startActivity(i);
            }
        });

        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Firebase myFirebaseRef = new Firebase("https://my-project-1483693737987.firebaseio.com/");

                myFirebaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        EventCounter = dataSnapshot.child("Events").getChildrenCount();
                        Log.d("Countinsnapshot",String.valueOf(EventCounter));
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });



                    Log.d("Count: ",String.valueOf(EventCounter));
				myFirebaseRef.child("Events").child(String.valueOf(EventCounter)).child("EventID").setValue(String.valueOf(EventCounter));
				myFirebaseRef.child("Events").child(String.valueOf(EventCounter)).child("HostName").setValue(HostName.getText().toString());
                myFirebaseRef.child("Events").child(String.valueOf(EventCounter)).child("EventName").setValue(EventName.getText().toString());
                myFirebaseRef.child("Events").child(String.valueOf(EventCounter)).child("LocationLat").setValue(String.valueOf(location.latitude));
                myFirebaseRef.child("Events").child(String.valueOf(EventCounter)).child("LocationLon").setValue(String.valueOf(location.longitude));
                myFirebaseRef.child("Events").child(String.valueOf(EventCounter)).child("EventDescription").setValue(EventDescription.getText().toString());
                myFirebaseRef.child("Events").child(String.valueOf(EventCounter)).child("Time").setValue(Time.getText().toString());
                myFirebaseRef.child("Events").child(String.valueOf(EventCounter)).child("Date").setValue(Date.getText().toString());

                Toast.makeText(EventDetails.this,"Submission successful!",Toast.LENGTH_LONG).show();
                Intent i = new Intent(EventDetails.this, FindEventsMap.class);
                startActivity(i);

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

//        Calendar cal = Calendar.getInstance();
//        long startTime = cal.getTimeInMillis();
//        long endTime = cal.getTimeInMillis()  + 60 * 60 * 1000;
//
//        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime);
//        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,endTime);
//        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

        intent.putExtra(CalendarContract.Events.TITLE, EventName.getText().toString());
        intent.putExtra(CalendarContract.Events.DESCRIPTION,  EventDescription.getText().toString());
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, Location.getText().toString());


//        Calendar c = Calendar.getInstance();
//        c.set(Calendar.YEAR, mYear);
//        c.set(Calendar.MONTH, mMonth);
//        c.set(Calendar.MINUTE, mMinute);
//        c.set(Calendar.HOUR_OF_DAY, mHour);
//        c.set(Calendar.MINUTE, mMinute);
//        c.getTimeInMillis();
//        Log.d("MYINT", "value: " + c.getTimeInMillis());

//        intent.putExtra(CalendarContract.Events.RRULE, "FREQ=YEARLY");

        startActivity(intent);
    }

    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText((monthOfYear + 1)+"-"+dayOfMonth + "-" + year + "");

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            if(minute<10){
                                txtTime.setText(hourOfDay + ":0" + minute + "");
                            }
                            else{
                                txtTime.setText(hourOfDay + ":" + minute + "");
                            }


                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }
}
