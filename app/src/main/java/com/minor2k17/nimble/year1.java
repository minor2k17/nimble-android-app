package com.minor2k17.nimble;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class year1 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_year1,container,false);

        final ArrayList<year_list> androidYear = new ArrayList<year_list>();
        androidYear.add(new year_list("Computer Networks",R.drawable.nimblelogo));
        androidYear.add(new year_list("OSSP",R.drawable.smile));
        androidYear.add(new year_list("SDF",R.drawable.sad));

        year_adapter adapter = new year_adapter(getActivity(), androidYear);
        ListView listview = (ListView) rootView.findViewById(R.id.listview_year1);
        listview.setAdapter(adapter);

        return rootView;
    }

}
