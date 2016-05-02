package com.dat.testswipegallery.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.ViewGroup;
import com.dat.testswipegallery.InfiniteViewPagerWithCircularIndicator.FragmentPagerAdapter;
import com.dat.testswipegallery.PhotoFragment;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DAT on 02-May-16.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] data;
    private Context context;
    private boolean shouldShowChildren = false;
    private FragmentManager mFragmentManager;
    private Map<Integer, String> mFragmentTags;
    private int mLastPosition = -1;

    public MyFragmentPagerAdapter(FragmentManager fm, Context context, String[] data) {
        super(fm);
        this.mFragmentManager = fm;
        this.context = context;
        mFragmentTags = new HashMap<>();
        this.data = data;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        highlightCurrentFragment(position);
        super.setPrimaryItem(container, position, object);
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
        return Fragment.instantiate(context, PhotoFragment.class.getName(), null);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position = position % getActualCount();
        Object obj = super.instantiateItem(container, position);
        if (obj instanceof PhotoFragment) {
            // record the fragment tag here.
            PhotoFragment fragment = (PhotoFragment) obj;
            if (fragment.getArguments() == null) {
                Bundle args = new Bundle();
                args.putString(PhotoFragment.ARGUMENT_PHOTO, data[position]);
                fragment.setArguments(args);
            }
            String tag = fragment.getTag();
            mFragmentTags.put(position, tag);
        }
        return obj;
    }

    public Fragment getFragment(int position) {
        String tag = mFragmentTags.get(position);
        if (tag == null) {
            return null;
        }
        return mFragmentManager.findFragmentByTag(tag);
    }

    public void highlightCurrentFragment(int position) {
        position = position % getActualCount();
        Fragment lastFragment = getFragment(mLastPosition);
        if (mLastPosition >= 0 && lastFragment != null && lastFragment instanceof PhotoFragment) {
            ((PhotoFragment) lastFragment).displayOverGroundImage(true);
        }
        Fragment curFragment = getFragment(position);
        if (curFragment != null && curFragment instanceof PhotoFragment) {
            ((PhotoFragment) curFragment).displayOverGroundImage(false);
        }

        mLastPosition = position;
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
