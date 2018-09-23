package com.zendrive.zendrivesample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.zendrive.zendrivesample.fragments.LoginFragment;
import com.zendrive.zendrivesample.fragments.OffDutyFragment;
import com.zendrive.zendrivesample.fragments.OnDutyFragment;
import com.zendrive.zendrivesample.manager.SharedPrefsManager;
import com.zendrive.zendrivesample.manager.TripManager;
import com.zendrive.zendrivesample.manager.ZendriveManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loadFirstFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        String driverId = SharedPrefsManager.sharedInstance(this).getDriverId();
        // if the user is already logged in or setup previously failed, Zendrive should be set up.
        if (driverId != null ||
                SharedPrefsManager.sharedInstance(this).shouldRetryZendriveSetup()) {
            ZendriveManager.sharedInstance().initializeZendriveSDK(this, driverId);
        }
        // check Zendrive settings on app resume if there are errors/warnings present
        ZendriveManager.sharedInstance().maybeCheckZendriveSettings(this);
    }

    private void loadFirstFragment() {
        Fragment firstFragment;
        if (SharedPrefsManager.sharedInstance(this).getDriverId() != null) {
            if (TripManager.sharedInstance(this).getTripManagerState().isUserOnDuty()) {
                firstFragment = new OnDutyFragment();
            }
            else {
                firstFragment = new OffDutyFragment();
            }
        }
        else {
            firstFragment = new LoginFragment();
        }
        replaceFragment(firstFragment);
    }

    public void replaceFragment(Fragment newFragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainContentView, newFragment);
        ft.commit();
    }
}
