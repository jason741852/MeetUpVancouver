package meetupvancouver.com.meetupvancouver;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraIdleListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import android.util.Log;


import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * This shows how to listen to some {@link GoogleMap} events.
 */
public class MapsActivity extends AppCompatActivity
        implements OnMapClickListener, OnMapLongClickListener, OnCameraIdleListener,
        OnMapReadyCallback {

    private TextView mTapTextView;
    private TextView mCameraTextView;
    private GoogleMap mMap;
    Marker marker;
    Button hostButton;
    LatLng hostPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mTapTextView = (TextView) findViewById(R.id.tap_text);
        mCameraTextView = (TextView) findViewById(R.id.camera_text);
        hostButton = (Button) findViewById(R.id.hostButton);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        hostButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("hi","button clicked");
                Intent i = new Intent(MapsActivity.this, EventDetails.class);
                i.putExtra("point",hostPoint);
                startActivity(i);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(49.234221, -122.8145), 9));

        mMap.setOnMapClickListener(this);
        mMap.setOnMapLongClickListener(this);
        mMap.setOnCameraIdleListener(this);
    }

    @Override
    public void onMapClick(LatLng point) {

        mTapTextView.setText("tapped, point=" + point);
        if (marker != null) {
            marker.remove();
        }
        marker = mMap.addMarker(new MarkerOptions().position(point));
        hostPoint = point;
    }

    @Override
    public void onMapLongClick(LatLng point) {

        mTapTextView.setText("long pressed, point=" + point);
        if (marker != null) {
            marker.remove();
        }
        marker = mMap.addMarker(new MarkerOptions().position(point));
        hostPoint = point;

    }

    @Override
    public void onCameraIdle() {
        mCameraTextView.setText(mMap.getCameraPosition().toString());
    }
}
