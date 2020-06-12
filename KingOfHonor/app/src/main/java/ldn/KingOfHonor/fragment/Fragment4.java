package ldn.KingOfHonor.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import ldn.KingOfHonor.R;
import ldn.KingOfHonor.adapter.MyViewPagerAdapter;
import ldn.KingOfHonor.adapter.SkillAdapter;
import ldn.KingOfHonor.model.Skill;
import ldn.KingOfHonor.sqlite.HeroSQLiteHelper;

/**
 * @author: LDN
 * @date: 2020/5/17
 */
public class Fragment4 extends Fragment {

    private View view;
    private int totalPage;
    private ImageView skillDetailImage;
    private TextView skillNameText;
    private TextView skillDetail1;
    private TextView skillDetail2;
    private GridView skill_gridView;
    private LinearLayout group;
    private ViewPager viewPager;
    private ArrayList<Skill> skillArrayList;
    private int mPageSize = 12;
    private HeroSQLiteHelper heroSQLiteHelper;
    private LayoutInflater myinflater;
    private ArrayList<View> viewPagerList;
    private SkillAdapter mSkillAdapter;
    private MyViewPagerAdapter viewPagerAdapter;
    private ImageView[] ivPoints;
    private int currentPage;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment4, container, false);
        initView();
        initData();
        totalPage = (int) Math.ceil(skillArrayList.size() * 1.0 / mPageSize);

        return view;
    }

    private void initData() {
        skillArrayList = new ArrayList<Skill>();
        heroSQLiteHelper = new HeroSQLiteHelper(getContext());
        heroSQLiteHelper.addSkill(new Skill("惩击",
                R.mipmap.chengji,
                R.mipmap.chengji_detail,
                "LV.1解锁",
                "30秒CD：对身边的野怪和小兵造成真实伤害并眩晕1秒"));
        heroSQLiteHelper.addSkill(new Skill("终结",
                R.mipmap.zhongjie,
                R.mipmap.zhongjie_detail,
                "LV.3解锁",
                "90秒CD：立即对身边敌军英雄造成其已损失生命值14%的真实伤害"));
        heroSQLiteHelper.addSkill(new Skill("狂暴",
                R.mipmap.kuangbao,
                R.mipmap.kuangbao_detail,
                "LV.5解锁",
                "60秒CD：增加攻击速度60%，并增加物理攻击力10%持续5秒"));
        heroSQLiteHelper.addSkill(new Skill("疾跑",
                R.mipmap.jipao,
                R.mipmap.jipao_detail,
                "LV.7解锁",
                "100秒CD：增加30%移动速度持续10秒"));
        heroSQLiteHelper.addSkill(new Skill("治疗术",
                R.mipmap.zhiliaoshu,
                R.mipmap.zhiliaoshu_detail,
                "LV.9解锁",
                "120秒CD：回复自己与附近队友15%生命值，提高附近友军移动速度15%持续2秒"));
        heroSQLiteHelper.addSkill(new Skill("干扰",
                R.mipmap.ganrao,
                R.mipmap.ganrao_detail,
                "LV.11解锁",
                "60秒CD：沉默机关持续5秒"));
        heroSQLiteHelper.addSkill(new Skill("晕眩",
                R.mipmap.yunxuan,
                R.mipmap.yunxuan_detail,
                "LV.13解锁",
                "90秒CD：晕眩身边所有敌人0.75秒，并附带持续1秒的减速效果"));
        heroSQLiteHelper.addSkill(new Skill("净化",
                R.mipmap.jinghua,
                R.mipmap.jinghua_detail,
                "LV.15解锁",
                "120秒CD：解除自身所有负面和控制效果并免疫控制持续1.5秒"));
        heroSQLiteHelper.addSkill(new Skill("弱化",
                R.mipmap.ruohua,
                R.mipmap.ruohua_detail,
                "LV.17解锁",
                "90秒CD：减少身边敌人伤害输出50%持续3秒"));
        heroSQLiteHelper.addSkill(new Skill("闪现",
                R.mipmap.shanxian,
                R.mipmap.shanxian_detail,
                "LV.19解锁",
                "120秒CD：向指定方向位移一段距离"));
        skillArrayList = heroSQLiteHelper.getAllSkill();
        myinflater = LayoutInflater.from(getContext());
        setViewPagerWithGridview();

        ivPoints = new ImageView[totalPage];
        for (int i = 0; i < totalPage; i++){
            ImageView imageView = new ImageView(getActivity());
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(30,30);
            params.rightMargin = 40;
            imageView.setLayoutParams(params);
            imageView.setBackgroundResource(R.drawable.point_bg);
            if (i == 0){
                imageView.setEnabled(true);
            }
            else {
                imageView.setEnabled(false);
            }
            ivPoints[i] = imageView;
            group.addView(imageView);
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                setImageBackground(i);
                currentPage = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void setImageBackground(int selectItems) {
        for (int i = 0; i < ivPoints.length; i++) {
            if (i == selectItems) {
                ivPoints[i].setBackgroundResource(R.drawable.point_focus);
            } else {
                ivPoints[i].setBackgroundResource(R.drawable.point_normal);
            }
        }
    }

    private void setViewPagerWithGridview() {
        totalPage = (int) Math.ceil(skillArrayList.size() * 1.0 / mPageSize);
        viewPagerList = new ArrayList<View>();
        for (int i = 0; i < totalPage; i++){
            mSkillAdapter = new SkillAdapter(getContext(),skillArrayList, i, mPageSize);
            final GridView gridView = (GridView) myinflater.inflate(R.layout.gridview,viewPager,false);
            gridView.setAdapter(mSkillAdapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Skill skill = (Skill) adapterView.getItemAtPosition(i);
                    skillDetailImage.setImageResource(skill.getImage_detail());
                    skillNameText.setText(skill.getName());
                    skillDetail1.setText(skill.getDetail1());
                    skillDetail2.setText(skill.getDetail2());

                }
            });
            viewPagerList.add(gridView);
        }
        viewPagerAdapter = new MyViewPagerAdapter(viewPagerList);
        viewPager.setAdapter(viewPagerAdapter);
    }

    private void initView() {
        skillDetailImage = (ImageView) view.findViewById(R.id.skill_detail_image);
        skillNameText = (TextView)view.findViewById(R.id.skill_name_text);
        skillDetail1 = (TextView)view.findViewById(R.id.skill_detail1);
        skillDetail2 = (TextView)view.findViewById(R.id.skill_detail2);
        skill_gridView = (GridView)view.findViewById(R.id.skill_gridview);
        group = (LinearLayout) view.findViewById(R.id.points);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        skillNameText.setText("惩击");
        skillDetailImage.setImageResource(R.mipmap.chengji_detail);
        skillDetail1.setText("LV.1解锁");
        skillDetail2.setText("30秒CD：对身边的野怪和小兵造成真实伤害并眩晕1秒");
    }
}
