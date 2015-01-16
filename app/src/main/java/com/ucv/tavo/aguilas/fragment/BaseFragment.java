package com.ucv.tavo.aguilas.fragment;

/**
 * Created by gustavo on 12/12/14.
 */

import android.os.Bundle;

import com.ucv.tavo.aguilas.LVBPApp;

import roboguice.activity.RoboFragmentActivity;

/**
 * @author gonzalo.martin
 */
public class BaseFragment extends RoboFragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LVBPApp.getInstance().setCurrentActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LVBPApp.getInstance().activityResumed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        LVBPApp.getInstance().activityPaused();
    }

    /*@Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home:
                Intent intent = new Intent(this, MainFragment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                this.startActivity(intent);
                break;
        }
        return true;
    }*/
}

