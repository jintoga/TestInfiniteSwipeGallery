package com.dat.testswipegallery.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.dat.testswipegallery.InfiniteViewPagerWithCircularIndicator.FragmentPagerAdapter;
import com.dat.testswipegallery.PhotoFragment;

/**
 * Created by DAT on 02-May-16.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] data;

    private boolean shouldShowChildren = false;

    public MyFragmentPagerAdapter(FragmentManager fm, String[] data) {
        super(fm);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public int getActualCount() {
        return data.length;
    }

    @Override
    public Fragment getItem(int position) {
        PhotoFragment fragment = new PhotoFragment();
        Bundle args = new Bundle();
        args.putString(PhotoFragment.ARGUMENT_PHOTO, data[position]);
        fragment.setArguments(args);
        return fragment;
    }

    public void setShouldShowChildren(boolean shouldShowChildren) {
        this.shouldShowChildren = shouldShowChildren;
    }

    @Override
    public float getPageWidth(int position) {
        if (shouldShowChildren) {
            return 0.6f;
        }
        return 1f;
    }
}
