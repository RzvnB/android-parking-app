package com.example.oc.parkingapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class Map extends AppCompatActivity implements OnMapReadyCallback {

    private List<CircleOptions> circleOptionsList = new ArrayList<>();
    private Socket mSocket = SocketSingleton.getSocket();
    private GoogleMap mMap;
    private int STATE = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String label = intent.getStringExtra("viewName");
        setTitle(label);
        setContentView(R.layout.activity_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        createCircleOptions();

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        setWebSocketEvent();
    }

    @Override
    public void onMapReady(final GoogleMap map) {
        mMap = map;
        for(CircleOptions c : circleOptionsList) {
            mMap.addCircle(c);
        }

    }

    private void runThread(final CircleOptions c) {
        runOnUiThread(new Thread(new Runnable() {
            @Override
            public void run() {
                mMap.addCircle(c);
            }
        }));
    }

    private void setWebSocketEvent() {
        mSocket.on("sentData", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject obj = (JSONObject) args[0];
                CircleOptions c = circleOptionsList.get(0);
                try {
                    String s = obj.getString("number");
                    if(Integer.parseInt(s) < 5)
                        c.fillColor(Color.RED);
                    else
                        c.fillColor(Color.GREEN);
                    runThread(c);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }

    private void createCircleOptions() {
        CircleOptions c = new CircleOptions()
                .center(new LatLng(45.749130, 21.241272))
                .radius(2)
                .strokeColor(Color.TRANSPARENT)
                .fillColor(Color.GREEN);
        circleOptionsList.add(c);

        c = new CircleOptions()
                .center(new LatLng(45.749170, 21.241242))
                .radius(2)
                .strokeColor(Color.TRANSPARENT)
                .fillColor(Color.GREEN);
        circleOptionsList.add(c);

        c = new CircleOptions()
                .center(new LatLng(45.749210, 21.241212))
                .radius(2)
                .strokeColor(Color.TRANSPARENT)
                .fillColor(Color.GREEN);
        circleOptionsList.add(c);

        c = new CircleOptions()
                .center(new LatLng(45.749250, 21.241182))
                .radius(2)
                .strokeColor(Color.TRANSPARENT)
                .fillColor(Color.GREEN);
        circleOptionsList.add(c);

        c = new CircleOptions()
                .center(new LatLng(45.749290, 21.241152))
                .radius(2)
                .strokeColor(Color.TRANSPARENT)
                .fillColor(Color.GREEN);
        circleOptionsList.add(c);
    }

}
