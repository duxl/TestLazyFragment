package a.b.c;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import a.b.c.base.LazyFragment;

/**
 * create by duxl 2021/1/28
 */
public class ChildFragment extends LazyFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_child, container, false);
        showLog("onCreateView");
        return v;
    }

    public static ChildFragment newInstance() {

        Bundle args = new Bundle();

        ChildFragment fragment = new ChildFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onLazyHiddenChanged(boolean isVisible, boolean isFirst) {
        showLog("onLazyHiddenChanged---------isVisible=" + isVisible + ", isFirst=" + isFirst);
    }

    @Override
    protected int getLayoutResId() {
        return 0;
    }
}
