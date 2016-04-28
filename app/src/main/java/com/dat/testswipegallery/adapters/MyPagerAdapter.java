package com.dat.testswipegallery.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.dat.testswipegallery.R;
import com.squareup.picasso.Picasso;

/**
 * Created by DAT on 29-Apr-16.
 */
public class MyPagerAdapter extends PagerAdapter {

    @Bind(R.id.imageView)
    protected ImageView imageView;

    private String[] data;

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
        View view = inflater.inflate(R.layout.fragment_photo, null);
        collection.addView(view);
        ButterKnife.bind(this, view);
        Picasso.with(collection.getContext())
            .load(data[position])
            .placeholder(R.drawable.placeholder)
            .into(imageView);
        return view;
    }
}