package com.zendrive.zendrivesample.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zendrive.zendrivesample.MainActivity;
import com.zendrive.zendrivesample.R;
import com.zendrive.zendrivesample.Constants;
import com.zendrive.zendrivesample.manager.TripManager;

import java.util.Locale;

public class OnDutyFragment extends Fragment implements View.OnClickListener {

    private TextView currentStateTextView;
    private Button pickupAPassengerButton;
    private Button cancelRequestButton;
    private Button dropAPassengerButton;
    private Button offDutyButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_onduty, container, false);

        currentStateTextView = layout.findViewById(R.id.currentStateTextView);

        layout.findViewById(R.id.acceptNewRideReqButton).setOnClickListener(this);

        pickupAPassengerButton = layout.findViewById(R.id.pickupAPassengerButton);
        pickupAPassengerButton.setOnClickListener(this);

        cancelRequestButton = layout.findViewById(R.id.cancelRequestButton);
        cancelRequestButton.setOnClickListener(this);

        dropAPassengerButton = layout.findViewById(R.id.dropAPassengerButton);
        dropAPassengerButton.setOnClickListener(this);

        offDutyButton = layout.findViewById(R.id.offDutyButton);
        offDutyButton.setOnClickListener(this);
        refreshUI();
        return layout;
    }

    private void refreshUI() {
        TripManager.State tripManagerState = TripManager.sharedInstance(getContext()).getTripManagerState();
        int passengersInCar = tripManagerState.getPassengersInCar();
        int passengerWaitingForPickup = tripManagerState.getPassengersWaitingForPickup();

        int insurancePeriod = 0;
        if (passengersInCar > 0) {
            insurancePeriod = 3;
        } else if (passengerWaitingForPickup > 0) {
            insurancePeriod = 2;
        } else if (tripManagerState.isUserOnDuty()) {
            insurancePeriod = 1;
        }

        currentStateTextView.setText(
                String.format(Locale.US, "Insurance Period: %d\nPassengers In Car: %d" +
                                "\nPassengers Waiting For Pickup: %d", insurancePeriod,
                        passengersInCar,
                        passengerWaitingForPickup));

        pickupAPassengerButton.setEnabled(passengerWaitingForPickup > 0);
        cancelRequestButton.setEnabled(passengerWaitingForPickup > 0);
        dropAPassengerButton.setEnabled(passengersInCar > 0);
        offDutyButton.setEnabled(passengersInCar == 0 && passengerWaitingForPickup == 0);
    }

    @Override
    public void onClick(View view) {
        TripManager tripManager = TripManager.sharedInstance(getContext());
        switch (view.getId()) {
            case R.id.acceptNewRideReqButton:
                Log.d(Constants.LOG_TAG_DEBUG, "acceptNewRideReqButton tapped");
                tripManager.acceptNewPassengerRequest(getContext());
                break;
            case R.id.pickupAPassengerButton:
                Log.d(Constants.LOG_TAG_DEBUG, "pickupAPassengerButton tapped");
                tripManager.pickupAPassenger(getContext());
                break;
            case R.id.cancelRequestButton:
                Log.d(Constants.LOG_TAG_DEBUG, "cancelRequestButton tapped");
                tripManager.cancelARequest(getContext());
                break;
            case R.id.dropAPassengerButton:
                Log.d(Constants.LOG_TAG_DEBUG, "dropAPassengerButton tapped");
                tripManager.dropAPassenger(getContext());
                break;
            case R.id.offDutyButton:
                Log.d(Constants.LOG_TAG_DEBUG, "offDutyButton tapped");
                tripManager.goOffDuty(getContext());
                ((MainActivity) getActivity()).replaceFragment(new OffDutyFragment());
                break;
        }
        refreshUI();
    }
}
