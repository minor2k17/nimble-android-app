package com.minor2k17.nimble;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by shaur on 28-10-2017.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter{

    private Context mContext;
    public SimpleFragmentPagerAdapter(Context context,FragmentManager fm)
    {
        super(fm);
        mContext = context;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0)
        {
            return mContext.getString(R.string.year1);
        }
        else if(position == 1)
        {
            return mContext.getString(R.string.year2);
        }
        else if(position == 2)
        {
            return mContext.getString(R.string.year3);
        }
        else{
            return mContext.getString(R.string.year4);
        }
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new year1();
        } else if (position == 1){
            return new year2();
        } else if (position == 2) {
            return new year3();
        } else if (position == 3) {
            return new year4();
        }
        else return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
