package com.betx.matka.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.betx.matka.fragments.JodiCrossFragment;
import com.betx.matka.fragments.JodiFastCrossFragment;

public class JodiTabsAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public JodiTabsAdapter(FragmentManager fm, int NoofTabs) {
        super(fm);
        this.mNumOfTabs = NoofTabs;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                JodiCrossFragment JCF = new JodiCrossFragment();
                return JCF;
            case 1:
                JodiFastCrossFragment JFCF = new JodiFastCrossFragment();
                return JFCF;



            default:
                return null;
        }
    }

}
