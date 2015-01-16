package com.ucv.tavo.aguilas.activity;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;

import com.ucv.tavo.aguilas.R;
import com.ucv.tavo.aguilas.adapter.DashboardAdapter;
import com.ucv.tavo.aguilas.dto.ContainerDTO;
import com.viewpagerindicator.TabPageIndicator;


public class MainActivity extends ActionBarActivity {

    private FragmentPagerAdapter dashboardAdapter;
    ViewPager dashboardPager;
    TabPageIndicator dashboardIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTabs();
        getSupportActionBar().setTitle("Aguilas del Zulia");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);
    }

    private void initTabs() {
        ContainerDTO tab0 = new ContainerDTO();
        tab0.setTitle("Posiciones");
        tab0.setIconResId(R.drawable.ic_calendar);
        ContainerDTO tab1 = new ContainerDTO();
        tab1.setTitle("Resultados");
        tab1.setIconResId(R.drawable.ic_games);
        ContainerDTO tab2 = new ContainerDTO();
        tab2.setTitle("Calendario");
        tab2.setIconResId(R.drawable.ic_calendar);

        ContainerDTO[] dto = {tab0, tab1, tab2};
        dashboardAdapter = new DashboardAdapter(getSupportFragmentManager(), dto);
        dashboardPager = (ViewPager)findViewById(R.id.dashboard_pager);
        dashboardPager.setOffscreenPageLimit(dto.length);
        dashboardPager.removeAllViews();
        dashboardPager.setAdapter(dashboardAdapter);
        dashboardPager.setCurrentItem(0);

        dashboardIndicator = (TabPageIndicator)findViewById(R.id.dashboard_indicator);
        dashboardIndicator.setViewPager(dashboardPager);
        dashboardIndicator.setCurrentItem(1);
        dashboardIndicator.setVisibility(View.VISIBLE);
    }



}
