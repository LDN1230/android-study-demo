package ldn.KingOfHonor.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * @author: LDN
 * @date: 2020/6/11
 */
public class MyViewPagerAdapter extends PagerAdapter {
    private List<View> viewList;

    public MyViewPagerAdapter(List<View> viewList){
        this.viewList = viewList;
    }

    /**
     * @description 获取当前窗体界面数，也就是数据的个数
     * @author LDN
     * @time 2020/6/11 9:22
     */
    @Override
    public int getCount() {
        return viewList != null ? viewList.size():0;
    }

    /**
     * @description 判断是否由对象生成界面，官方建议直接返回 return view == object
     * @author LDN
     * @time 2020/6/11 9:23
     */
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    /**
     * @description 要显示的页面或需要缓存的页面，会调用这个方法进行布局的初始化
     * @author LDN
     * @time 2020/6/11 9:24
     */
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    /**
     * @description 如果页面不是当前显示的页面也不是要缓存的页面，会调用这个方法，将页面销毁
     * @author LDN
     * @time 2020/6/11 9:25
     */
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        // 最简单解决 notifyDataSetChanged() 页面不刷新问题的方法
        return POSITION_NONE;
    }
}
