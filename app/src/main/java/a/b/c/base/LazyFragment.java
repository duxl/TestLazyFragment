package a.b.c.base;

/**
 * create by duxl 2021/1/28
 */
public abstract class LazyFragment extends BaseFragment {

    private boolean isVisibleToUser = true;
    private boolean isVisible; // 是否可见
    private boolean isFirstVisible = true; // 是否第一次

    @Override
    public void onResume() {
        super.onResume();
        lazyHiddenChanged(!isHidden() && isVisibleToUser);
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
        lazyHiddenChanged(isVisibleToUser);
    }

    protected void lazyHiddenChanged(boolean visible) {
        if (isVisible != visible) {
            isVisible = visible;
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
    abstract void onLazyHiddenChanged(boolean isVisible, boolean isFirstVisible);
}
