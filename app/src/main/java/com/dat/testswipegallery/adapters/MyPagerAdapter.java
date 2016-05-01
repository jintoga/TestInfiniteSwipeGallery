package com.dat.testswipegallery.adapters;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.dat.testswipegallery.InfiniteViewPagerWithCircularIndicator.PagerAdapter;
import com.dat.testswipegallery.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by DAT on 29-Apr-16.
 */
public class MyPagerAdapter extends PagerAdapter {

    @Bind(R.id.imageView)
    protected SimpleDraweeView imageView;

    private String[] data;

    private boolean shouldShowChildren = false;

    public MyPagerAdapter(String[] data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup view, int position, Object object) {
        view.removeView((View) object);
    }

    @Override
    public Object instantiateItem(final ViewGroup collection, final int position) {
        LayoutInflater inflater = (LayoutInflater) collection.getContext()
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_photo_picasso, null);
        collection.addView(view);
        ButterKnife.bind(this, view);

        Uri imageUri = Uri.parse(data[position]);
        imageView.setImageURI(imageUri);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Clicked ", position + 1 + " ");
            }
        });
        return view;
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