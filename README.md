# TestLazyFragment

#### 通过测试fragment在activity和viewPager中的各种生命周期调用过程log打印，最终实现懒加载的[LazyFragment](/app/src/main/java/a/b/c/base/LazyFragment.java)

#### Fragment生命周期方法

###### *前半段：*

- *`onAttach`*

- *`onCreate`*

- *`onCreateView`*

- *`onViewCreated`*
- *`onActivityCreated`*
- *onViewStateRestored*

- *`onStart`*
- *`onResume`*

###### *后半段：*

- *`onPause`*
- *`onStop`*
- *`onDestroyView`*
- *`onDestroy`*
- *`onDetach`*

------

1. 如果调用了FragmentTransaction的`show`和`hide`方法还会触发Fragment的`onHiddenChanged`(boolean hidden)
2. 如果在ViewPager中使用FragmentPagerAdapter，关于`setUserVisibleHint(boolean isVisibleToUser)`的调用时机如下
   1. 在`onAttach`生命周期之前会调用并传入参数是false
   2. 如果是当前Fragment会重复再次调用并传入true。
   3. 切换ViewPager时，离开的fragment传入false、进入的fragment传入true
   4. Fragment嵌套的子Fragment不会调用`setUserVisibleHint`方法
3. 在AndroidX下ViewPager使用 FragmentPagerAdapter(FragmentManager fm, int behavior)，behavior参数传入`FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT`参数，控制只有当前fragment的生命周期会执行`onResume`，其它的fragment只会执行到`onStart`。并且`setUserVisibleHint`也**不再会调用**。