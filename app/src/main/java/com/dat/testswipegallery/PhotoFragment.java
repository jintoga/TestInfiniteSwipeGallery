package com.dat.testswipegallery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;

/**
 * Created by DAT on 28-Apr-16.
 */
public class PhotoFragment extends Fragment {

    public static final String ARGUMENT_PHOTO = "Photo";

    @Bind(R.id.imageView)
    protected ImageView imageView;

    private String photo;

    private View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        photo = getArguments().getString(ARGUMENT_PHOTO);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_photo, container, false);
        ButterKnife.bind(this, view);
        if (photo != null) {
            Picasso.with(getContext())
                .load(photo)
                .placeholder(R.drawable.placeholder)
                .into(imageView);
            //loadImage(photo);
        }
        return view;
    }

    /*private void loadImage(String imageUrl) {
        Transformation transformation = new Transformation() {

            @Override
            public Bitmap transform(Bitmap source) {
                int targetWidth = imageView.getWidth();

                double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
                int targetHeight = (int) (targetWidth * aspectRatio);
                Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
                if (result != source) {
                    // Same bitmap is returned if sizes are the same
                    source.recycle();
                }
                return result;
            }

            @Override
            public String key() {
                return "transformation" + " desiredWidth";
            }
        };

        Picasso.with(getContext())
            .load(imageUrl)
            .error(android.R.drawable.stat_notify_error)
            .transform(transformation)
            .into(imageView, new Callback() {
                @Override
                public void onSuccess() {
                }

                @Override
                public void onError() {
                }
            });
    }*/
}

