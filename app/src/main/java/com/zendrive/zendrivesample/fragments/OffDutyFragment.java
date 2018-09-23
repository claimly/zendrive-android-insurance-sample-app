package com.zendrive.zendrivesample.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zendrive.zendrivesample.MainActivity;
import com.zendrive.zendrivesample.R;
import com.zendrive.zendrivesample.Constants;
import com.zendrive.zendrivesample.manager.TripManager;

public class OffDutyFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_offduty, container, false);
        layout.findViewById(R.id.goOnDutyButton).setOnClickListener(this);
        return layout;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.goOnDutyButton:
                goOnDutyButtonClicked();
                break;
        }
    }

    private void goOnDutyButtonClicked() {
        Log.d(Constants.LOG_TAG_DEBUG, "goOnDutyButtonClicked");
        TripManager.sharedInstance(getContext()).goOnDuty(getContext());
        ((MainActivity)getActivity()).replaceFragment(new OnDutyFragment());
    }
}
