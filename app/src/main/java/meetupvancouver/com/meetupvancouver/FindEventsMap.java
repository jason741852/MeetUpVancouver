package meetupvancouver.com.meetupvancouver;

/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * This shows how to create a simple activity with a map and a marker on the map.
 */
public class FindEventsMap extends AppCompatActivity implements OnMapReadyCallback {

    Long EventCounter;
    Double[] tempLoc;
    String[] tempInfo;
    Event e;
    String locationLat;
    String locationLon;
    GoogleMap mMap;
    LatLng tempLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_events_map);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we
     * just add a marker near Africa.
     */
    @Override
    public void onMapReady(final GoogleMap map) {
        mMap = map;
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(49.234221, -122.8145), 9));




        Firebase.setAndroidContext(this);
        Firebase myFirebaseRef = new Firebase("https://my-project-1483693737987.firebaseio.com/");
        myFirebaseRef.addListenerForSingleValueEvent((new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                EventCounter = dataSnapshot.child("Events").getChildrenCount();

                tempLoc = new Double[2*EventCounter.intValue()];
                tempInfo = new String[EventCounter.intValue()];
                Log.d("location",String.valueOf(tempLoc.length));
                Integer i = 0;
                Integer j = 0;
                for (DataSnapshot event : dataSnapshot.child("Events").getChildren()) {
                    e = event.getValue(Event.class);
                    locationLat = e.getLocationLat();
                    locationLon = e.getLocationLon();
                    //tempLocation = new LatLng(Double.parseDouble(locationLat),Double.parseDouble(locationLon));
//                    mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(locationLat),Double.parseDouble(locationLon))));
                    tempInfo[j] = e.toString();
                    j++;
                    tempLoc[i] = Double.parseDouble(locationLat);
                    i++;
                    tempLoc[i] = Double.parseDouble(locationLon);
                    i++;


                }
                Log.d("location",tempLoc[0].toString());
                Integer jj;
                for (Integer ii = 0; ii < tempLoc.length; ii++){
                    Log.d("lat",tempLoc[ii].toString());
                    jj = ii/2;

                    map.addMarker(new MarkerOptions()
                            .position(new LatLng(tempLoc[ii], tempLoc[++ii]))
                            .title(tempInfo[jj]));
                    Log.d("lng",tempLoc[ii].toString());
                    Log.d("title",tempInfo[jj]);


                }


                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Intent i = new Intent(FindEventsMap.this, ShowEvent.class);
                        i.putExtra("info",marker.getTitle());
                        startActivity(i);

                        return false;
                    }
                });
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        }));
//        Log.d("location",tempLoc[0].toString());
//        for (Integer ii = 0; ii <= tempLoc.length; ii++){
//            map.addMarker(new MarkerOptions().position(new LatLng(tempLoc[ii], tempLoc[ii++])));
//        }


    }
}
