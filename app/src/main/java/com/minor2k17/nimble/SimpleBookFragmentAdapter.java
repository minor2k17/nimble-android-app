package com.minor2k17.nimble;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by shaur on 30-10-2017.
 */

public class SimpleBookFragmentAdapter extends FragmentPagerAdapter{


    private Context mContext;
    public SimpleBookFragmentAdapter(Context context,FragmentManager fm)
    {
        super(fm);
        mContext = context;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0)
        {
            return mContext.getString(R.string.bookall);
        }
        else if(position == 1)
        {
            return mContext.getString(R.string.simple);
        }
        else
        {
            return mContext.getString(R.string.smart);
        }
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new all_books();
        } else if (position == 1){
            return new simple_posting_books();
        } else {
            return new smart_posting_books();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

}
