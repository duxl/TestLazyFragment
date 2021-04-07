package a.b.c;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * 测试很多Fragment加载卡顿问题
 * create by duxl 2021/4/6
 */
public class ManyFragmentActivity extends AppCompatActivity {


    private ViewPager mViewPager;
    private ListFragment[] fragments;
    private int fragmentCount = 100;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_many_fragment);

        mViewPager = findViewById(R.id.viewPager);
        mViewPager.setOffscreenPageLimit(fragmentCount);
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                setTitle((position + 1) + "/" + mViewPager.getChildCount());

                // ViewPager切换的时候，将上一个和下一个Fragment都预加载出来
                if (position - 1 >= 0) {
                    fragments[position - 1].inflateLayout();
                }
                if (position + 1 < fragments.length) {
                    fragments[position + 1].inflateLayout();
                }
            }
        });


        fragments = new ListFragment[fragmentCount];
        for (int i = 0; i < fragmentCount; i++) {
            fragments[i] = ListFragment.newInstance(String.format("第%d个Fragment", i + 1));
        }


        fragments[1].inflateLayout(); // 预加载
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }
        });
    }
}
