package com.dat.testswipegallery;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
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
            /*"http://s11.postimg.org/aft369v1v/dog_how_to_select_your_new_best_friend_thinkstoc.jpg",
            "http://s22.postimg.org/3ydo64c3l/cutest_cat_ever_snoopy_face_2.jpg",
            "http://www.zoo22.ru/upload/iblock/05a/05ab85cdf16792f2efeb1a279ba399b0.jpg",
            "http://www.zoo22.ru/upload/iblock/024/024d113a2d4b8f44554eef348fc9affb.png",
            "http://www.zoo22.ru/upload/iblock/e55/e55f7897ac7a6f628900f1ef41558f26.png",
            "http://s32.postimg.org/bu2cb8dlh/018.jpg",
            "http://s32.postimg.org/mi63a2nkl/10472795_1516097865378010_1206966512854576988_o.jpg",
            "http://s32.postimg.org/lothhghjp/11014977_419568654912989_2640509535362674658_n.jpg",
            "http://s32.postimg.org/jlj29shqt/12791057_657347101072467_2630471624444555902_n.jpg"*/
            "http://s32.postimg.org/b0z8uv1sl/afro_samurai_resurrection_original.jpg",
            "http://s32.postimg.org/n6og59gid/asuras_wrath_wallpaper_hd_2_1080p.jpg",
            "http://s32.postimg.org/8or8x9p79/barret_M107_by_mimi3d.jpg",
            "http://s32.postimg.org/vz5esy1n9/barrett_m107_by_deargruadher_d4dikw8.jpg",
            "http://s32.postimg.org/wiai27t1x/Darksiders_Wrath_of_War_1920x1080.jpg"
        };

        fragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), images);

        fragmentViewPager.setAdapter(fragmentPagerAdapter);
        fragmentViewPager.setBoundaryCaching(false);
        indicatorFragment.setViewPager(fragmentViewPager);
        fragmentViewPager.setClipToPadding(false);
        fragmentViewPager.setOffscreenPageLimit(3);
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
        objectViewPager.setOffscreenPageLimit(3);
        objectViewPager.setBoundaryCaching(true);
        indicatorObject.setViewPager(objectViewPager);
        objectViewPager.setClipToPadding(false);
        int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100 * 2,
            getResources().getDisplayMetrics());
        objectViewPager.setPageMargin(-margin);
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
