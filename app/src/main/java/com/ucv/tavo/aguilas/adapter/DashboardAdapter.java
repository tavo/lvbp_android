package com.ucv.tavo.aguilas.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ucv.tavo.aguilas.dto.ContainerDTO;
import com.ucv.tavo.aguilas.fragment.CalendarFragment;
import com.ucv.tavo.aguilas.fragment.HomeFragment;
import com.ucv.tavo.aguilas.fragment.PositionsFragment;
import com.viewpagerindicator.IconPagerAdapter;

/**
 * @author pablo.carballo
 */
public class DashboardAdapter extends FragmentPagerAdapter implements IconPagerAdapter {

    private Fragment[] fragments;
    private ContainerDTO[] dto;

    public DashboardAdapter(FragmentManager fm, ContainerDTO[] dto) {
        super(fm);
        fragments = new Fragment[dto.length];
        this.dto = dto;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return PositionsFragment.newInstance();
            case 1:
                return HomeFragment.newInstance();
            case 2:
                return CalendarFragment.newInstance();
            default:
                return HomeFragment.newInstance();
        }

    }

    @Override
    public int getIconResId(int index) {
        return dto[index].getIconResId();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return getTitle(position);
    }

    @Override
    public int getCount() {
        return dto.length;
    }

    public String getTitle(int position) {
        return dto[position].getTitle();
    }

}
