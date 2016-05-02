package com.dat.testswipegallery;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by DAT on 02-May-16.
 */
public class PhotoFragment extends Fragment {
    public static final String ARGUMENT_PHOTO = "Photo";

    @Bind(R.id.imageView)
    protected SimpleDraweeView imageView;

    View view;

    private Uri photo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String photoUrl = getArguments().getString(ARGUMENT_PHOTO);
        if (photoUrl != null) {
            photo = Uri.parse(photoUrl);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.photo_page, container, false);
        ButterKnife.bind(this, view);
        if (photo != null) {
            imageView.setImageURI(photo);
        }
        return view;
    }
}
