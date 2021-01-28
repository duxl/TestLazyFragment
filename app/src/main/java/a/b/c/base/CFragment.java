package a.b.c.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import a.b.c.EmptyActivity;
import a.b.c.R;

/**
 * create by duxl 2021/1/28
 */
public class CFragment extends LazyFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_c, container, false);
        showLog("onCreateView");
        v.findViewById(R.id.textView).setOnClickListener(v1 -> {
            startActivity(new Intent(CFragment.this.getContext(), EmptyActivity.class));
        });
        return v;
    }

    public static CFragment newInstance() {

        Bundle args = new Bundle();

        CFragment fragment = new CFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    void onLazyHiddenChanged(boolean isVisible, boolean isFirst) {
        showLog("onLazyHiddenChanged---------isVisible=" + isVisible + ", isFirst=" + isFirst);
    }
}
