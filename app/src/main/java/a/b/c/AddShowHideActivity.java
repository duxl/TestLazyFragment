package a.b.c;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import a.b.c.base.AFragment;
import a.b.c.base.BFragment;
import a.b.c.base.BaseActivity;

/**
 * create by duxl 2021/1/28
 */
public class AddShowHideActivity extends BaseActivity {

    Fragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_show_hide);

        fragment = AFragment.newInstance();

        findViewById(R.id.btn_add).setOnClickListener(this::add);
        findViewById(R.id.btn_add_b).setOnClickListener(this::addB);
        findViewById(R.id.btn_show).setOnClickListener(this::show);
        findViewById(R.id.btn_hide).setOnClickListener(this::hide);
        findViewById(R.id.btn_remove).setOnClickListener(this::remove);
    }

    private void add(View view) {
        showLog("----------- add -------------");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container, fragment);
        transaction.commit();

    }

    private void addB(View view) {
        showLog("----------- add-B -------------");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container, ParentFragment.newInstance());
        transaction.commit();

    }


    private void show(View view) {
        showLog("----------- show -------------");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.show(fragment);
        transaction.commit();

    }

    private void hide(View view) {
        showLog("----------- hide -------------");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragment);
        transaction.commit();

    }

    private void remove(View view) {
        showLog("----------- remove -------------");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.remove(fragment);
        transaction.commit();

    }
}
