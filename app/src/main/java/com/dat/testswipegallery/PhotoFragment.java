package com.dat.testswipegallery;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.image.QualityInfo;

/**
 * Created by DAT on 28-Apr-16.
 */
public class PhotoFragment extends Fragment {

    public static final String ARGUMENT_PHOTO = "Photo";

    @Bind(R.id.imageView)
    protected SimpleDraweeView imageView;
    @Bind(R.id.progressBar)
    protected ProgressBar progressBar;

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
            ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
                @Override
                public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo,
                    @Nullable Animatable anim) {
                    if (imageInfo == null) {
                        return;
                    }
                    QualityInfo qualityInfo = imageInfo.getQualityInfo();
                    Log.d("Final image received! ", "Image's Size "
                        + imageInfo.getWidth()
                        + "x"
                        + imageInfo.getHeight()
                        + " Quality level "
                        + qualityInfo.isOfGoodEnoughQuality());
                    //progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
                    Log.d("onIntermediateImageSet", "Intermediate image received");
                    //progressBar.setVisibility(View.VISIBLE);
                }

                @Override
                public void onFailure(String id, Throwable throwable) {
                    //progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onSubmit(String id, Object callerContext) {
                    super.onSubmit(id, callerContext);
                    Log.d("onSubmit", "onSubmit");
                }
            };
            Uri imageUri = Uri.parse(photo);
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setControllerListener(controllerListener)
                .setUri(imageUri)
                // other setters
                .build();
            imageView.setController(controller);
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

