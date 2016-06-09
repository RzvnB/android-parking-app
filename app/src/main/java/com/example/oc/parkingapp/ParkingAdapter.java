package com.example.oc.parkingapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Razvan's on 18/May/16.
 */
public class ParkingAdapter extends RecyclerView.Adapter<ParkingAdapter.MyViewHolder> {

    private List<Parking> parkingList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, address, openSpots;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            address = (TextView) view.findViewById(R.id.address);
            openSpots = (TextView) view.findViewById(R.id.openSpots);
//            name.setOnClickListener(this);
//            address.setOnClickListener(this);
//            openSpots.setOnClickListener(this);
        }
    }

    public ParkingAdapter(List<Parking> parkingList) {
        this.parkingList = parkingList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.parking_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Parking parking = parkingList.get(position);
        holder.name.setText(parking.getName());
        holder.address.setText(parking.getAddress());
        holder.openSpots.setText(parking.getOpenSpots());
        if(Integer.parseInt(parking.getOpenSpots()) <= 0) {
            holder.openSpots.setBackgroundResource(R.drawable.shape_notification_red);
        } else {
            holder.openSpots.setBackgroundResource(R.drawable.shape_notification);
        }
    }

//    @Override
//    public void onClick(View v) {
//
//    }

    @Override
    public int getItemCount() {
        return parkingList.size();
    }
}
