package meetupvancouver.com.meetupvancouver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FirebaseTest extends AppCompatActivity {

    Button submitbutton;
    EditText example;
    private static final String TAG = FirebaseTest.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_test);

        example = (EditText)findViewById(R.id.editText);
        submitbutton = (Button)findViewById(R.id.submitbutton);

        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"clicked");

            }
        });

    }





}
