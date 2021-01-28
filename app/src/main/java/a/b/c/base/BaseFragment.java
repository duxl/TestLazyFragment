package a.b.c.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    protected void showLog(String msg) {
        LogUtils.show(getClass().getSimpleName() + "(" + hashCode() + ")#" + msg);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        showLog("onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showLog("onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showLog("onActivityCreated");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showLog("onViewCreated");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        showLog("onViewStateRestored");
    }

    @Override
    public void onStart() {
        super.onStart();
        showLog("onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        showLog("onResume");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        showLog("onHiddenChanged.hidden=" + hidden);
    }

    @Override
    public void onPause() {
        super.onPause();
        showLog("onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        showLog("onStop");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        showLog("onSaveInstanceState");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        showLog("onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        showLog("onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        showLog("onDetach");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        showLog("setUserVisibleHint.isVisibleToUser=" + isVisibleToUser);
    }
}

