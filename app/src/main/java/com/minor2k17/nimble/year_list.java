package com.minor2k17.nimble;

/**
 * Created by shaur on 02-11-2017.
 */

public class year_list {
    private String mlistyeartitle, mlistyearlink;
    private int mImageResourceId;

    public year_list(String vlistyeartile, String vlistyearlink, int imageId)
    {
        mlistyeartitle=vlistyeartile;
        mlistyearlink=vlistyearlink;
        mImageResourceId=imageId;

    }

    public String getlisttitle(){
        return mlistyeartitle;
    }
    public String getlistlink(){
        return mlistyearlink;
    }
    public int getmImageResourceId(){
        return mImageResourceId;
    }


}
