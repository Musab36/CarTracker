package com.salajim.musab.cartracker.activities;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.salajim.musab.cartracker.R;
import com.salajim.musab.cartracker.adapters.SectionsPagerAdapter;
import com.salajim.musab.cartracker.fragments.CarsFragment;
import com.salajim.musab.cartracker.fragments.MapsFragment;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private SectionsPagerAdapter mAdapter;
    private Context context;

    private static final Integer[] tabIcons = {R.drawable.baseline_map_white_24, R.drawable.baseline_local_shipping_white_24};

    ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = getSupportActionBar();

        // tab elevation setup
        View tabs = findViewById(R.id.tabs);
        ViewCompat.setElevation(tabs, getResources().getDimension(R.dimen.action_bar_elevation));

        mAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(mViewPager);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        // Setting up tabs icons
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);

        tabLayout.getTabAt(0).getIcon().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(1).getIcon().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_IN);


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
                switch (tab.getPosition()) {
                    case 0:
                        mViewPager.setCurrentItem(0);
                        toolbar.setTitle("Maps");
                        break;
                    case 1:
                        mViewPager.setCurrentItem(1);
                        toolbar.setTitle("My Cars");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_IN);
                //for removing the color of first icon when switched to next tab
                tabLayout.getTabAt(0).getIcon().clearColorFilter();
                //for other tabs
                tab.getIcon().clearColorFilter();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    // Adding Fragments and setting the adapter with ViewPager
    private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MapsFragment(), "");
        adapter.addFragment(new CarsFragment(), "");

        viewPager.setAdapter(adapter);
    }
}
