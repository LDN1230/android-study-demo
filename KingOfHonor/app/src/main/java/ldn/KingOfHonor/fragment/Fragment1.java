package ldn.KingOfHonor.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import ldn.KingOfHonor.HeroAdd;
import ldn.KingOfHonor.HeroDetail;
import ldn.KingOfHonor.R;
import ldn.KingOfHonor.adapter.HeroAdapter;
import ldn.KingOfHonor.model.Hero;
import ldn.KingOfHonor.sqlite.HeroSQLiteHelper;

/**
 * @author: LDN
 * @date: 2020/5/17
 */
public class Fragment1 extends Fragment {

    private View view; //该Fragment的布局
    private HeroSQLiteHelper mySQLiteHelper;
    private HeroAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private ArrayAdapter<String> searchAdapter;
    private RadioGroup hero_category_select;//英雄类别选择
    private ArrayList<ImageView> imgList;
    private int lastPointPosition;//上一个页面的位置索引
    private LinearLayout pointGroup;//滑动的指示
    private boolean isRunning = true;
    private ViewPager imageViewPager; //图片轮换
    private String[] imageArr={"android.resource://ldn.KingOfHonor/" + R.mipmap.juyoujing,
            "android.resource://ldn.KingOfHonor/" + R.mipmap.libai}; // 喜欢的英雄图片
    private int clickPosition;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==1 && isRunning){
                //收到消息,开始滑动
                if(imageViewPager != null){
                    int currentItem = imageViewPager.getCurrentItem();//获取当前显示的界面的索引
                    //如果当前显示的是最后一个页面,就显示第一张,否则显示下一张
                    if(currentItem==imgList.size()-1){
                        imageViewPager.setCurrentItem(0);
                    }else{
                        imageViewPager.setCurrentItem(currentItem+1);
                    }
                }

                //2ms后再发送消息,实现循环
                handler.sendEmptyMessageDelayed(1, 3000);
            }
        }
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Hero h){
        if(h.getAdded()){
            h.setAdded(false);
            searchAdapter.add(h.getName());
            searchAdapter.notifyDataSetChanged();
            mAdapter.addNewItem(h);
            mySQLiteHelper.addHero(h);
            //更新当前选中的英雄类型的列表
            mAdapter.updateWithCategory(mySQLiteHelper.getAllHeroes(), ((RadioButton)view.findViewById(hero_category_select.getCheckedRadioButtonId())).getText().toString());
            Log.d("添加英雄", "onMessageEvent: add" + h.getName());
        } else if(h.getDeleted()){
            h.setDeleted(false);
            if(mAdapter != null) {
                mAdapter.deleteItem(clickPosition);
                searchAdapter.remove(h.getName());
                searchAdapter.notifyDataSetChanged();
            }
            mySQLiteHelper.deleteHero(h);
            Log.d("删除英雄", "onMessageEvent: delete" + h.getName());
        }else if(h.getModified()){
            h.setModified(false);
            if(mAdapter != null) {
                mAdapter.updateSingleHero(clickPosition, h);
            }
            mySQLiteHelper.deleteHero(h);
            mySQLiteHelper.addHero(h);
            Log.d("修改英雄", "onMessageEvent: modify" + h.getName());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 注册 EventBus
        EventBus.getDefault().register(this);
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment1, container, false);
        mySQLiteHelper= new HeroSQLiteHelper(getContext());

        //配置RecyclerView
        mLayoutManager = new GridLayoutManager(getActivity(), 5);
        mAdapter = new HeroAdapter(mySQLiteHelper.getAllHeroes());
        mRecyclerView = view.findViewById(R.id.hero_recyclerview);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new HeroAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), HeroDetail.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("Click_Hero", mAdapter.getItem(position));
                intent.putExtras(bundle);
                clickPosition = position;
                startActivityForResult(intent, 0);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

        // ToolBar
        Toolbar toolbar = view.findViewById(R.id.hero_toolbar);
        toolbar.inflateMenu(R.menu.hero_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener(){

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    //添加英雄
                    case R.id.action_hero_add:
                        Intent intent = new Intent(getActivity(), HeroAdd.class);
                        intent.putStringArrayListExtra("hero_names", mAdapter.getAllNames());
                        startActivityForResult(intent, 1);
                        break;
                }
                return true;
            }
        });

        //SearchView
        final SearchView searchView = view.findViewById(R.id.hero_edit_search);
        searchView.setIconified(false);//设置searchView处于展开状态
        searchView.onActionViewExpanded();// 当展开无输入内容的时候，没有关闭的图标
        searchView.setQueryHint("输入查找的英雄名");//设置默认无内容时的文字提示
        searchView.setIconifiedByDefault(false);//默认为true在框内，设置false则在框外
//        searchView.setIconified(false);//展开状态
        searchView.clearFocus();//清除焦点
        searchView.isSubmitButtonEnabled();//键盘上显示搜索图标

        AutoCompleteTextView completeText = searchView.findViewById(R.id.search_src_text) ;
        completeText.setTextColor(getResources().getColor(android.R.color.black, null));//设置内容文字颜色
        completeText.setHintTextColor(getResources().getColor(R.color.white, null));//设置提示文字颜色
        completeText.setThreshold(0);
        searchAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, mAdapter.getAllNames());
        completeText.setAdapter(searchAdapter);
        completeText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                searchView.setQuery(searchAdapter.getItem(position),true);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Hero temp_hero = mAdapter.getItemByName(query);
                if(temp_hero != null){
                    Intent intent = new Intent(getActivity(), HeroDetail.class);
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("Click_Hero", temp_hero);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, 0);
                }else{
                    Toast.makeText(getActivity(),"该英雄不存在" ,Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        //改变显示的英雄类别
        hero_category_select = view.findViewById(R.id.hero_category);
        hero_category_select.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mAdapter.updateWithCategory(mySQLiteHelper.getAllHeroes(), ((RadioButton)view.findViewById(checkedId)).getText().toString());
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        lastPointPosition = 0;
        isRunning = true;
        //view pager
        pointGroup = view.findViewById(R.id.point_group);
        imageViewPager = view.findViewById(R.id.hero_upper_pager);
        //添加图片列表
        imageArr = mAdapter.getAllFavoriteHeroes(mySQLiteHelper);
        imgList= new ArrayList<>();
        for (int i=0;i<imageArr.length;i++) {
            //初始化图片
            ImageView image=new ImageView(getActivity());
            image.setImageURI(Uri.parse(imageArr[i]));
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            imgList.add(image);
            //添加图片的指示点
            ImageView point=new ImageView(getActivity());
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(30,30);//布局参数,point的布局宽与高
            params.rightMargin = 40;//右边距
            point.setLayoutParams(params);//设置布局参数
            point.setBackgroundResource(R.drawable.point_bg);//point_bg是根据setEnabled的值来确定形状的
            if(i==0){
                point.setEnabled(true);//初始化的时候设置第一张图片的形状
            }else{
                point.setEnabled(false);//根据该属性来确定这个图片的显示形状
            }
            pointGroup.addView(point);//将该指示的图片添加到布局中
        }
        imageViewPager.setAdapter(new ImagePagerAdapter());
        imageViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //页面正在滑动的时候调用,position指的是左侧页面的索引,positionOffset代表偏移量[0,1]的范围,
            // positionOffsetPixels也是偏移量,不过是像素点的偏移量 范围[0,显示的控件的绝对长度]
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            //页面改变的时候调用(稳定),positon表示被选中的索引
            @Override
            public void onPageSelected(int position) {
                if(pointGroup.getChildAt(lastPointPosition)!=null)
                    pointGroup.getChildAt(lastPointPosition).setEnabled(false);//将上一个点设置为false
                else
                    Log.e("PointGroupRrror:", "onPageSelected: "+ lastPointPosition);
                lastPointPosition=position;
                //改变指示点的状态
                pointGroup.getChildAt(position).setEnabled(true);//将当前点enbale设置为true
            }

            //页面滚动状态发送改变的时候回调
            @Override
            public void onPageScrollStateChanged(int state) {
            //当手指点击屏幕滚动的时状态码为1,当手指离开viewpager自动滚动的状态码为2,自动滚动选中了显示了页面的时候状态码为0
            }
        });
        handler.sendEmptyMessageDelayed(1, 3000);
    }

    @Override
    public void onPause() {
        super.onPause();
        isRunning = false;
        pointGroup.removeAllViews();
        imageViewPager.removeAllViews();
        imageViewPager.clearOnPageChangeListeners();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    //图片轮换的适配器
    private class ImagePagerAdapter extends PagerAdapter {
        /**
         * 获得页面的总数
         */
        @Override
        public int getCount() {
            return imageArr.length;
        }
        /**
         * 判断view和object的对应关系,如果当前要显示的控件是来之于instantiateItem方法创建的就显示,否则不显示
         * object 为instantiateItem方法返回的对象
         * 如果为false就不会显示该视图
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
        /**
         * 实例化下一个要显示的子条目,获取相应位置上的view,这个为当前显示的视图的下一个需要显示的控件
         * container  view的容器,其实就是viewager自身
         * position   ViewPager相应的位置
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imgList.get(position));
            return imgList.get(position);
        }
        /**
         * 销毁一个子条目,object就为instantiateItem方法创建的返回的对象,也是滑出去需要销毁了的视图对象
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
            object=null;
        }
    }
}
