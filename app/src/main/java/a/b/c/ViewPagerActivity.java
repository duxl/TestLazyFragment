package a.b.c;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import a.b.c.base.AFragment;
import a.b.c.base.BFragment;
import a.b.c.base.BaseActivity;
import a.b.c.base.CFragment;

/**
 * create by duxl 2021/1/28
 */
public class ViewPagerActivity extends BaseActivity {

    private ViewPager mViewPager;
    private Fragment[] fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        mViewPager = findViewById(R.id.viewPager);

        fragments = new Fragment[]{
                AFragment.newInstance(),
                BFragment.newInstance(),
                CFragment.newInstance()

        };

        mViewPager.setOffscreenPageLimit(fragments.length);

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
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
