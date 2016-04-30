package com.dat.testswipegallery.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.dat.testswipegallery.LoopViewPager.LoopViewPager;
import com.dat.testswipegallery.PhotoFragment;

/**
 * Created by DAT on 29-Apr-16.
 */
public class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private String[] data;

    public MyFragmentPagerAdapter(FragmentManager fm, String[] data) {
        super(fm);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Fragment getItem(int position) {
        position = LoopViewPager.toRealPosition(position, getCount());
        PhotoFragment fragment = new PhotoFragment();

        Bundle args = new Bundle();
        args.putSerializable(PhotoFragment.ARGUMENT_PHOTO, data[position]);
        fragment.setArguments(args);

        return fragment;
    }

    public float getPageWidth(int position) {
        return 0.8f;
    }
}
