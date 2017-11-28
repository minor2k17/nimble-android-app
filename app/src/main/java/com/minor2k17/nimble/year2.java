package com.minor2k17.nimble;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class year2 extends Fragment {

    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_year2,container,false);

        String url1 = "Random Download Link";
        final ArrayList<year_list> androidYear = new ArrayList<year_list>();
        androidYear.add(new year_list("Computer Networks", url1, R.drawable.pdficon));
        androidYear.add(new year_list("OSSP", url1, R.drawable.pdficon));
        androidYear.add(new year_list("SDF", url1, R.drawable.pdficon));

        year_adapter adapter = new year_adapter(getActivity(), androidYear);
        ListView listview = (ListView) view.findViewById(R.id.listview_year2);
        listview.setAdapter(adapter);

        return view;
    }
}
