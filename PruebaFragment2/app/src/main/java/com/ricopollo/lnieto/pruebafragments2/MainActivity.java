package com.ricopollo.lnieto.pruebafragments2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.os.Bundle;

import com.eftimoff.viewpagertransformers.FlipHorizontalTransformer;
import com.eftimoff.viewpagertransformers.RotateUpTransformer;

public class MainActivity extends FragmentActivity  {

    private static final int NUM_PAGES = 5;

    private PageListener page_listener;
    private int currentPage;

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private TextView tv_Number;

    private ImageButton btnLeft, btnRight, btnPrueba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.view_pager);
        tv_Number = (TextView) findViewById(R.id.tv_Number);

        btnLeft = (ImageButton) findViewById(R.id.btnLeft);
        btnRight = (ImageButton) findViewById(R.id.btnRight);
        btnPrueba = (ImageButton) findViewById(R.id.btnprueba);

        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setPageTransformer(true, new FlipHorizontalTransformer());

        page_listener = new PageListener();
        mPager.setOnPageChangeListener(page_listener);

        tv_Number.setText("Page :" + currentPage);

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPager.getCurrentItem() > 0) {
                    mPager.setCurrentItem(mPager.getCurrentItem() - 1, true);
                }
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPager.getCurrentItem() < mPager.getAdapter().getCount()){
                    mPager.setCurrentItem(mPager.getCurrentItem() + 1, true);
                }
            }
        });

        btnPrueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPager.getCurrentItem() > 0) {
                    mPager.setCurrentItem(mPager.getCurrentItem() - 1, true);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }


    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new fragmentViews();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    private class PageListener extends SimpleOnPageChangeListener {
        public void onPageSelected(int position) {
            currentPage = position;

            tv_Number.setText("Page :" + currentPage);

        }
    }
}
