package a.b.c.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import a.b.c.R;

/**
 * create by duxl 2021/1/28
 */
public class BFragment extends LazyFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_b, container, false);
        showLog("onCreateView");
        return v;
    }

    public static BFragment newInstance() {

        Bundle args = new Bundle();

        BFragment fragment = new BFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onLazyHiddenChanged(boolean isVisible, boolean isFirst) {
        showLog("onLazyHiddenChanged---------isVisible=" + isVisible + ", isFirst=" + isFirst);
    }
}
