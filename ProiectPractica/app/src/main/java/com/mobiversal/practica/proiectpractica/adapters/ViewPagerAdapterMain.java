package com.mobiversal.practica.proiectpractica.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mobiversal.practica.proiectpractica.fragments.ConversationFragments;
import com.mobiversal.practica.proiectpractica.fragments.GroupFragments;

/**
 * Created by Lenovo on 10.07.2017.
 */

public class ViewPagerAdapterMain extends FragmentPagerAdapter {

    private Fragment[] fragments;



    public ViewPagerAdapterMain(FragmentManager fm, Fragment[] fragments) {
        super(fm);
        this.fragments = fragments;
    }

    public ViewPagerAdapterMain(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                GroupFragments groupFragments = new GroupFragments();
                return groupFragments;
            case 1:
                ConversationFragments conversationFragments = new ConversationFragments();
                return conversationFragments;
            default: return fragments[position];
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "GRUPURI";
            case 1:
                return "CONVERSATII";
            default: return null;
        }

    }


}
