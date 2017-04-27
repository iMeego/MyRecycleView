package com.example.meego.myrecycleview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String[] TAB_TITLES = {"TAB1", "TAB2", "TAB3", "TAB4"};

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FloatingActionButton mFloatingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.vp_main_pager);
        mViewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));

        mTabLayout = (TabLayout) findViewById(R.id.tl_main_pager_tabs);
        mTabLayout.setupWithViewPager(mViewPager);

        mFloatingButton = (FloatingActionButton) findViewById(R.id.fab_action);
        mFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(findViewById(R.id.cl_main_layout),
                        getString(R.string.page_num, mViewPager.getCurrentItem() + 1),
                        Snackbar.LENGTH_SHORT)
                        .show();
            }
        });
    }

    private class MainPagerAdapter extends FragmentPagerAdapter {

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ListFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return TAB_TITLES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TAB_TITLES[position];
        }
    }
}
