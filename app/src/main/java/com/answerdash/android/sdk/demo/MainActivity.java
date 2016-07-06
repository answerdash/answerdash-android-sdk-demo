package com.answerdash.android.sdk.demo;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.answerdash.android.sdk.AnswerDash;

public class MainActivity extends AppCompatActivity {

    @StringRes
    private static final int[] tab_titles = {R.string.tab_home, R.string.tab_list, R.string.tab_custom};

    @DrawableRes
    private static final int[] tab_drawables = {R.drawable.ic_home, R.drawable.ic_list, R.drawable.ic_controls};

    private static final int NUMBER_OF_TABS = 3;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private DemoOnPageChangeListener onPageChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setupToolbar();

        DemoFragmentPagerAdapter pagerAdapter = new DemoFragmentPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(pagerAdapter);

        onPageChangeListener = new DemoOnPageChangeListener();
        viewPager.addOnPageChangeListener(onPageChangeListener);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        setupTabs();
    }

    @Override
    protected void onDestroy() {

        viewPager.removeOnPageChangeListener(onPageChangeListener);

        super.onDestroy();
    }

    private void setupToolbar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setupTabs() {

        if (tabLayout == null) {
            return;
        }

        int tabCount = tabLayout.getTabCount();
        for (int i = 0; i < tabCount; ++i) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setIcon(tab_drawables[i]);
                tab.setText(tab_titles[i]);
            }
        }
    }

    private class DemoFragmentPagerAdapter extends FragmentPagerAdapter {

        public DemoFragmentPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            switch (position) {
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new ListFragment();
                    break;
                case 2:
                    fragment = new CustomFragment();
                    break;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return NUMBER_OF_TABS;
        }
    }

    private class DemoOnPageChangeListener extends ViewPager.SimpleOnPageChangeListener {

        @Override
        public void onPageSelected(int position) {

            // for list tab, set custom appstate - for other tabs, use default appstate
            // usually setAppState will be called from onCreate or onResume, but with ViewPager this is not working
            AnswerDash.INSTANCE.setAppState(getApplication(), position == 1 ? "testappstate" : "");
        }
    }
}
