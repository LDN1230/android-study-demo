package ldn.KingOfHonor.adapter;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ldn.KingOfHonor.R;
import ldn.KingOfHonor.model.Hero;
import ldn.KingOfHonor.sqlite.HeroSQLiteHelper;

/**
 * @author: LDN
 * @date: 2020/6/3
 */
public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.ViewHolder> {
    private static List<Hero> mDatas;
    private HeroAdapter.OnItemClickListener onItemClickListener;
    // 将英雄列表传入
    public HeroAdapter(List<Hero> list){
        mDatas = list;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    @NonNull
    @Override
    public HeroAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hero_list, parent, false);
        // 实例化viewholder
        HeroAdapter.ViewHolder viewHolder = new HeroAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HeroAdapter.ViewHolder holder, int position) {
        // 绑定数据
        //holder.hero_name.setText(mDatas.get(position).getName());
        holder.hero_image.setImageURI(Uri.parse(mDatas.get(position).getIcon()));
        //listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(onItemClickListener != null) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, pos);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(onItemClickListener != null) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemLongClick(holder.itemView, pos);
                }
                return true;//表示此事件已经消费，不会触发单击事件
            }
        });
    }



    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView hero_name;
        ImageView hero_image;
        public ViewHolder(View itemView) {
            super(itemView); //RecyclerView.ViewHolder中有一个itemView变量，通过构造函数赋值
//            hero_name = itemView.findViewById(R.id.hero_name);
            hero_image = itemView.findViewById(R.id.hero_image);
        }
    }

    public void addNewItem(Hero f) {
        if(mDatas == null) {
            mDatas = new ArrayList<>();
        }
        mDatas.add(0, f);
        notifyItemInserted(0);
    }

    public void deleteItem(int pos) {
        if(mDatas == null || mDatas.isEmpty()) {
            return;
        }
        mDatas.remove(pos);
        notifyItemRemoved(pos);
    }

    public Hero getItem(int pos){
        if(mDatas == null || mDatas.isEmpty())
            return null;
        return mDatas.get(pos);
    }

    public Hero getItemByName(String name){
        if(mDatas == null || mDatas.isEmpty())
            return null;
        for(Hero h : mDatas){
            if(h.getName().equals(name))
                return h;
        }
        return null;
    }

    //修改某个数据，实现方法是删除再添加
    public void updateSingleHero(int pos, Hero hero){
        if(mDatas == null || mDatas.isEmpty())
            return;
        if(!mDatas.get(pos).getName().equals(hero.getName())){
            Log.d("Update Error", "updateSingleHero: "+ hero.getName());
            return;
        }
        mDatas.remove(pos);
        mDatas.add(0, hero);
        notifyItemRemoved(pos);
        notifyItemInserted(0);
    }

    //通过英雄职业改变显示的数据
    public void updateWithCategory(List<Hero> total, String category){
        mDatas.clear();
        if(category.equals("全部")){
            mDatas = total;
            notifyDataSetChanged();
        }
        for(Hero i: total){
            if(i.getCategory().equals(category))
                mDatas.add(i);
        }
        notifyDataSetChanged();
    }

    //返回所有的英雄名字
    public ArrayList<String> getAllNames(){
        ArrayList<String> data = new ArrayList<>();
        for(Hero temp : mDatas){
            data.add(temp.getName());
        }
        return data;
    }

    //返回所有已收藏HERO图片
    public String[] getAllFavoriteHeroes(HeroSQLiteHelper heroSQLiteHelper){
        ArrayList<String> favoriteHeroes = new ArrayList<>();
        for(Hero h : heroSQLiteHelper.getAllHeroes()){
            if(h.getFavorite())
                favoriteHeroes.add(h.getImage());
        }
        return favoriteHeroes.toArray(new String[favoriteHeroes.size()]);
    }

    public void setOnItemClickListener(HeroAdapter.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
}
