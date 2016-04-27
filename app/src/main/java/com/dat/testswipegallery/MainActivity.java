package com.dat.testswipegallery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.lsjwzh.widget.recyclerviewpager.LoopRecyclerViewPager;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.viewpager)
    protected LoopRecyclerViewPager viewPager;
    @Bind(R.id.imageNo)
    protected TextView imageNo;
    @Bind(R.id.indicator)
    protected MyCircleIndicator circleIndicator;

    private MyCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        LinearLayoutManager layoutManager =
            new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        viewPager.setLayoutManager(layoutManager);
        final String[] images = new String[] {
            "http://s11.postimg.org/aft369v1v/dog_how_to_select_your_new_best_friend_thinkstoc.jpg",
            "http://s22.postimg.org/3ydo64c3l/cutest_cat_ever_snoopy_face_2.jpg",
            "http://www.zoo22.ru/upload/iblock/05a/05ab85cdf16792f2efeb1a279ba399b0.jpg",
            "http://www.zoo22.ru/upload/iblock/024/024d113a2d4b8f44554eef348fc9affb.png",
            "http://www.zoo22.ru/upload/iblock/e55/e55f7897ac7a6f628900f1ef41558f26.png"
        };
        List<String> data = Arrays.asList(images);
        adapter = new MyCustomAdapter(data, this);

        viewPager.setAdapter(adapter);
        circleIndicator.setViewPager(viewPager);
        viewPager.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                imageNo.setText(
                    viewPager.getActualCurrentPosition() + 1 + "/" + adapter.getItemCount());
            }
        });
    }
}
