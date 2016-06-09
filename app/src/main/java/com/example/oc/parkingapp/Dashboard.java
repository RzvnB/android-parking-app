package com.example.oc.parkingapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;


import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import io.socket.client.Socket;
import io.socket.client.IO;
import io.socket.emitter.Emitter;

public class Dashboard extends AppCompatActivity {

    private ArrayList<Parking> parkingList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ParkingAdapter mAdapter;
    private Socket mSocket = SocketSingleton.getSocket();
    private String toDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new ParkingAdapter(parkingList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        prepareParkingData();
        connectWebSocket();
    }

    private void prepareParkingData() {
        Parking p = new Parking("Parking 1", "Address 1", "5");
        if(parkingList.size() < 7)
            parkingList.add(p);

        p = new Parking("Parking 2", "Address 2", "15");
        if(parkingList.size() < 7)
            parkingList.add(p);

        p = new Parking("Parking 3", "Address 3", "33");
        if(parkingList.size() < 7)
            parkingList.add(p);

        p = new Parking("Parking 4", "Address 4", "15");
        if(parkingList.size() < 7)
            parkingList.add(p);

        p = new Parking("Parking 5", "Address 5", "33");
        if(parkingList.size() < 7)
            parkingList.add(p);

        p = new Parking("Parking 6", "Address 6", "15");
        if(parkingList.size() < 7)
            parkingList.add(p);

        p = new Parking("Parking 7", "Address 7", "0");
        if(parkingList.size() < 7)
            parkingList.add(p);

        mAdapter.notifyDataSetChanged();


    }


    public void showMap(View view) {
        TextView v = (TextView) ((ViewGroup)view).getChildAt(0);
        Intent intent = new Intent(this, Map.class);
        String viewName = v.getText().toString();
        intent.putExtra("viewName", viewName);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private void runThread() {
        runOnUiThread(new Thread(new Runnable() {
            @Override
            public void run() {
                mAdapter.notifyDataSetChanged();
            }
        }));
    }

    private void connectWebSocket() {
        mSocket.on("sentData", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject obj = (JSONObject) args[0];
                try {
                    toDisplay = obj.getString("number");
                    Parking p = parkingList.get(0);
                    p.setOpenSpots(toDisplay);
                    runThread();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                mSocket.emit("msg", "Thank you!");

            }
        }).on("hello", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                mSocket.emit("msg", "Hello Back");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}
