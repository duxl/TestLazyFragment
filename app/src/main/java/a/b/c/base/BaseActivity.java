package a.b.c.base;

import androidx.appcompat.app.AppCompatActivity;

/**
 * create by duxl 2021/1/28
 */
public class BaseActivity extends AppCompatActivity {

    protected void showLog(String msg) {
        LogUtils.show(getClass().getSimpleName() + "(" + hashCode() + ")#" + msg);
    }
}
