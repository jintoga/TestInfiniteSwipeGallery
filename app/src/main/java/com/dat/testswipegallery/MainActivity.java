package com.dat.testswipegallery;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.dat.testswipegallery.LoopViewPager.LoopViewPager;
import com.dat.testswipegallery.adapters.MyFragmentPagerAdapter;
import com.dat.testswipegallery.adapters.MyPagerAdapter;
import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.viewpagerObject)
    protected LoopViewPager objectViewPager;
    @Bind(R.id.imageNoObject)
    protected TextView imageNoObject;
    @Bind(R.id.indicatorObject)
    protected CircleIndicator indicatorObject;

    @Bind(R.id.viewpagerFragment)
    protected LoopViewPager fragmentViewPager;
    @Bind(R.id.imageNoFragment)
    protected TextView imageNoFragment;
    @Bind(R.id.indicatorFragment)
    protected CircleIndicator indicatorFragment;

    private MyFragmentPagerAdapter fragmentPagerAdapter;
    private MyPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {

        final String[] images = new String[] {
            "http://s11.postimg.org/aft369v1v/dog_how_to_select_your_new_best_friend_thinkstoc.jpg",
            "http://s22.postimg.org/3ydo64c3l/cutest_cat_ever_snoopy_face_2.jpg",
            "http://www.zoo22.ru/upload/iblock/05a/05ab85cdf16792f2efeb1a279ba399b0.jpg",
            "http://www.zoo22.ru/upload/iblock/024/024d113a2d4b8f44554eef348fc9affb.png",
            "http://www.zoo22.ru/upload/iblock/e55/e55f7897ac7a6f628900f1ef41558f26.png"
        };

        fragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), images);

        fragmentViewPager.setAdapter(fragmentPagerAdapter);
        fragmentViewPager.setBoundaryCaching(true);
        indicatorFragment.setViewPager(fragmentViewPager);
        fragmentViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                imageNoFragment.setText(
                    position + 1 + "/" + fragmentViewPager.getAdapter().getCount());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        pagerAdapter = new MyPagerAdapter(images);
        objectViewPager.setAdapter(pagerAdapter);
        objectViewPager.setBoundaryCaching(true);
        indicatorObject.setViewPager(objectViewPager);
        objectViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                imageNoObject.setText(position + 1 + "/" + objectViewPager.getAdapter().getCount());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
