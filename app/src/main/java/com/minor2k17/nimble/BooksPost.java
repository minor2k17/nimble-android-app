package com.minor2k17.nimble;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BooksPost extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_post);

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);


        // Create an adapter that knows which fragment should be shown on each page
        SimpleBookFragmentAdapter adapter = new SimpleBookFragmentAdapter(this,getSupportFragmentManager());
        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs_books);
        tabLayout.setupWithViewPager(viewPager);


    }
}
