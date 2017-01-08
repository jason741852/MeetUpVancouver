package meetupvancouver.com.meetupvancouver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ShowEvent extends AppCompatActivity {

    TextView eventView, dateView, descView, hostView, timeView;


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









    }
}
