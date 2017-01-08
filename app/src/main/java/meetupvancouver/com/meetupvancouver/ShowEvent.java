package meetupvancouver.com.meetupvancouver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ShowEvent extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_event);

        String info = getIntent().getStringExtra("info");
        Log.d("FULL string",info);
        String[] infoTokens;

//        infoTokens = info.split("\\$");
//        for(Integer i=0; i<infoTokens.length;i++){
//            Log.d("tokens",infoTokens[i]);
//        }
//









    }
}
