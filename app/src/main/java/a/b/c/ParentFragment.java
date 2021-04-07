package a.b.c;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import a.b.c.base.LazyFragment;

/**
 * fragment嵌套fragment的情况
 * create by duxl 2021/1/29
 */
public class ParentFragment extends LazyFragment {

    public static ParentFragment newInstance() {

        Bundle args = new Bundle();

        ParentFragment fragment = new ParentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_parent, container, false);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.container, ChildFragment.newInstance());
        transaction.commit();
        return v;
    }

    @Override
    public void onLazyHiddenChanged(boolean isVisible, boolean isFirstVisible) {
        showLog("onLazyHiddenChanged-------isVisible=" + isVisible + ",  isFirstVisible=" + isFirstVisible);
    }

    @Override
    protected int getLayoutResId() {
        return 0;
    }
}
