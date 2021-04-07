package a.b.c.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import a.b.c.R;

/**
 * create by duxl 2021/1/28
 */
public abstract class LazyFragment extends BaseFragment {

    private boolean isVisibleToUser = true;
    private boolean isVisible; // 是否可见
    private boolean isFirstVisible = true; // 是否第一次
    private boolean isAttach;

    private ViewGroup mRoot;
    private ViewStub mViewStub;
    private boolean mHasInflated; // 是否已经加载了真实的布局文件
    private boolean mInflatedImmediately; // 是否立即加载布局文件

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        isAttach = true;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        isAttach = false;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRoot = (ViewGroup) inflater.inflate(R.layout.fragment_lazy, container, false);
        mViewStub = mRoot.findViewById(R.id.view_stub);
        mViewStub.setLayoutResource(getLayoutResId());
        if (mInflatedImmediately) {
            innerInflateLayout();
        }
        return mRoot;
    }

    @Override
    public void onResume() {
        super.onResume();
        boolean parentIsVisibleToUser = true;
        if (getParentFragment() != null) {
            // 如果是ViewPager中嵌套的子Fragment，需要判断父Fragment是否可见，
            // 因为嵌套的子Fragment的生命周期是跟外面的Activity相关，即使是用
            // getChildFragmentManager添加进来的
            parentIsVisibleToUser = getParentFragment().getUserVisibleHint();
        }
        // 判断isVisibleToUser也是因为ViewPager的原因，在viewpager中非当前
        // fragment也会执行onResume，并且!isHidden()也是true
        lazyHiddenChanged(!isHidden() && isVisibleToUser && parentIsVisibleToUser);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        lazyHiddenChanged(!hidden);
    }

    @Override
    public void onPause() {
        super.onPause();
        lazyHiddenChanged(false);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if (isAttach) {
            lazyHiddenChanged(isVisibleToUser);
            // ViewPager中Fragment嵌套的子Fragment不会调用setUserVisibleHint方法
            // 这里手动调用子Fragment的setUserVisibleHint方法
            if (getChildFragmentManager().getFragments() != null) {
                for (Fragment childFragment : getChildFragmentManager().getFragments()) {
                    childFragment.setUserVisibleHint(isVisibleToUser);
                }
            }
        }
    }

    /**
     * 加载布局文件，调用了此方法的Fragment被创建后布局文件会被立即加载
     *
     * @return
     */
    public LazyFragment inflateLayout() {
        mInflatedImmediately = true;
        innerInflateLayout();
        return this;
    }

    /**
     * 加载布局文件
     */
    protected void innerInflateLayout() {
        if (mViewStub != null && !mHasInflated) {
            //mViewStub.setVisibility(View.VISIBLE);
            mViewStub.inflate();
            mHasInflated = true;
            onLazyViewCreated(mRoot.getChildAt(0));
        }
    }

    /**
     * 可见状态改变处理函数
     *
     * @param visible
     */
    protected void lazyHiddenChanged(boolean visible) {
        if (isVisible != visible) {
            isVisible = visible;
            if (isVisible && isFirstVisible) {
                innerInflateLayout();
            }
            onLazyHiddenChanged(isVisible, isFirstVisible);
            isFirstVisible = false;
        }
    }

    /**
     * fragment可见状态改变的时候回调
     *
     * @param isVisible      是否可见
     * @param isFirstVisible 第一次可见的时候为true，其它为false
     */
    protected abstract void onLazyHiddenChanged(boolean isVisible, boolean isFirstVisible);

    /**
     * 懒加载的布局文件被加载了的回调函数，通常情况下当Fragment第一次可见的时候才回调此函数，
     * 也可以调用{@link #inflateLayout()}让Fragment创建后立即加载布局并回调此函数
     *
     * @param view
     */
    protected void onLazyViewCreated(View view) {

    }

    /**
     * 布局文件
     *
     * @return
     */
    protected abstract int getLayoutResId();
}
