package meetupvancouver.com.meetupvancouver;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static android.provider.UserDictionary.Words.APP_ID;


public class MapsActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final int LOCATION_REQUEST_CODE = 100;

    private LocationManager mLocationManager;
    private double lati = 49.2488,longi = 122.98;


    GoogleMap mMap;

    private GoogleApiClient mGoogleApiClient;

    TextView latView, longView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        buildGoogleApiClient();// create the google api client


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_COARSE_LOCATION },
                    2000);

        }






        mGoogleApiClient = new GoogleApiClient.Builder(this, this, this).addApi(LocationServices.API).build();




    }

    private synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)//request location services
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this).build();
    }






//
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
//        // Add a marker in Paris and move the camera
//        LatLng paris = new LatLng(lati, longi);
//        mMap.addMarker(new MarkerOptions().position(paris).title("You are here"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(paris));}

    @Override
    public void onConnected(Bundle bundle) {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Location lastLocation = LocationServices.FusedLocation/Api.getLastLocation(mGoogleApiClient);

            double lat = lastLocation.getLatitude(), lon = lastLocation.getLongitude();


            latView = (TextView)findViewById(R.id.latView);
            longView = (TextView)findViewById(R.id.longView);

            latView.setText(String.valueOf(lat));
            longView.setText(String.valueOf(lon));
            Toast.makeText(this, String.valueOf(lat), Toast.LENGTH_SHORT).show();




            LatLng mLoc = new LatLng(lat,lon);
//            mMap.addMarker(new MarkerOptions().position(mLoc).title("You are here"));
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(mLoc));



        }
    }




    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }


    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // All good!
                } else {
                    Toast.makeText(this, "Need your location!", Toast.LENGTH_SHORT).show();
                }



    }
}
