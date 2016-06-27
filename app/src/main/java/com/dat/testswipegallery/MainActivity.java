package com.dat.testswipegallery;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.dat.testswipegallery.InfiniteViewPagerWithCircularIndicator.CircularIndicator;
import com.dat.testswipegallery.InfiniteViewPagerWithCircularIndicator.InfiniteViewPager.InfinitePagerAdapter;
import com.dat.testswipegallery.InfiniteViewPagerWithCircularIndicator.InfiniteViewPager.InfiniteViewPager;
import com.dat.testswipegallery.InfiniteViewPagerWithCircularIndicator.PagerAdapter;
import com.dat.testswipegallery.InfiniteViewPagerWithCircularIndicator.ViewPager;
import com.dat.testswipegallery.adapters.MyFragmentPagerAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.viewpagerObject)
    protected InfiniteViewPager objectViewPager;
    @Bind(R.id.imageNoObject)
    protected TextView imageNoObject;
    @Bind(R.id.indicatorObject)
    protected CircularIndicator indicatorObject;

    private MyFragmentPagerAdapter fragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    int counter = 0;

    private void init() {

        final String[] images = new String[] {
            "http://s11.postimg.org/aft369v1v/dog_how_to_select_your_new_best_friend_thinkstoc.jpg",
            "http://s22.postimg.org/3ydo64c3l/cutest_cat_ever_snoopy_face_2.jpg",
            "http://www.zoo22.ru/upload/iblock/05a/05ab85cdf16792f2efeb1a279ba399b0.jpg",
            "http://www.zoo22.ru/upload/iblock/024/024d113a2d4b8f44554eef348fc9affb.png",
            "http://s32.postimg.org/b0z8uv1sl/afro_samurai_resurrection_original.jpg"
        };
        List<String> data = multiplyItems(images, 2);
        fragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), this, data);
        final PagerAdapter wrappedFragmentPagerAdapter =
            new InfinitePagerAdapter(fragmentPagerAdapter);
        objectViewPager.setAdapter(wrappedFragmentPagerAdapter);
        indicatorObject.setViewPager(objectViewPager);
        objectViewPager.setOffscreenPageLimit(2);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            fragmentPagerAdapter.setShouldShowChildren(true);
            objectViewPager.setPageMargin(-1);
        } else {
            fragmentPagerAdapter.setShouldShowChildren(false);
            objectViewPager.setPageMargin(0);
        }
        objectViewPager.setClipToPadding(false);
        objectViewPager.enableCenterLockOfChilds();
        objectViewPager.setCurrentItemInCenter(0);
        imageNoObject.setText(
            objectViewPager.getCurrentItem() + 1 + "/" + objectViewPager.getActualItemCount());
        objectViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                fragmentPagerAdapter.highlightCurrentFragment(position);
                imageNoObject.setText(objectViewPager.getCurrentItem()
                    + 1
                    + "/"
                    + objectViewPager.getActualItemCount());
                Log.d("Swipe", "Times: " + counter++);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private List<String> multiplyItems(String[] images, int n) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            result.addAll(Arrays.asList(images));
        }
        return result;
    }
}
