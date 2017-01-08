package meetupvancouver.com.meetupvancouver;

import android.content.Intent;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowEvent extends AppCompatActivity {

    TextView eventView, dateView, descView, hostView, timeView;
    Button homeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_event);

        String info = getIntent().getStringExtra("info");
        Log.d("FULL string",info);
        String[] infoTokens;

        infoTokens = info.split("\\$");
//        for(Integer i=0; i<infoTokens.length;i++){
//            Log.d("tokens",infoTokens[i]);
//        }
//
        eventView = (TextView) findViewById(R.id.eventText);
        dateView = (TextView) findViewById(R.id.dateText);
        descView = (TextView) findViewById(R.id.descText);
        hostView = (TextView) findViewById(R.id.hostText);
        timeView = (TextView) findViewById(R.id.timeText);


        eventView.setText(infoTokens[0]);
        dateView.setText(infoTokens[1]);
        descView.setText(infoTokens[2]);
        hostView.setText(infoTokens[3]);
        timeView.setText(infoTokens[4]);


        homeButton = (Button) findViewById(R.id.homeButton);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ShowEvent.this,UserAreaActivity.class);
                startActivity(i);
            }
        });

    }
    public void onAddEventClicked(View view){
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.item/event");

//        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime);
//        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,endTime);
//        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

        intent.putExtra(CalendarContract.Events.TITLE, eventView.getText().toString());
        intent.putExtra(CalendarContract.Events.DESCRIPTION,  descView.getText().toString());
//        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, Location.getText().toString());


        startActivity(intent);
    }
}
