# TestLazyFragment

通过测试fragment在普通activity和viewPager中的各种生命周期调用过程log打印，最终实现懒加载的[LazyFragment](/app/src/main/java/a/b/c/base/LazyFragment.java)

```java
@Override
public void onResume() {
    super.onResume();
    boolean parentIsVisibleToUser = true;
    if (getParentFragment() != null) {
        parentIsVisibleToUser = getParentFragment().getUserVisibleHint();
    }
    lazyHiddenChanged(!isHidden() && isVisibleToUser && parentIsVisibleToUser);
}
```