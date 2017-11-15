package com.minor2k17.nimble;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class year2 extends Fragment {

    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_year2, container, false);
        textView = view.findViewById(R.id.year2);
        textView.setText(SharedPreManager.getInstance(getActivity()).getToken());
        return view;
    }
}
