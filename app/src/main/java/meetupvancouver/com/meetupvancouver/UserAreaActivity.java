package meetupvancouver.com.meetupvancouver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class UserAreaActivity extends AppCompatActivity {
    Button findEventButton;
    Button hostEventButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);
        findEventButton = (Button) findViewById(R.id.findEvent);
        hostEventButton = (Button) findViewById(R.id.hostEvent);

        findEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserAreaActivity.this, FindEventsMap.class);
                startActivity(i);
            }
        });

        hostEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(UserAreaActivity.this, MapsActivity.class);
                startActivity(j);
            }
        });

        }
}
