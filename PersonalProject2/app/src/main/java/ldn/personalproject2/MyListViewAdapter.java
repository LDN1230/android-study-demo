package ldn.personalproject2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: LDN
 * @date: 2020/5/6
 */
public class MyListViewAdapter extends BaseAdapter {

    private static List<Food> list;

    private class ViewHolder {
        public TextView mTv, mIcon;
    }

    public MyListViewAdapter() {

        list = new ArrayList<Food>();
        list.add(new Food("收藏夹", "*", "*" , "#000000"));
    }

    @Override
    public int getCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override
    public Food getItem(int position) {
        if (list == null) {
            return null;
        }
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 新声明一个ViewHoleder变量
        ViewHolder viewHolder;
        // 当view为空时才加载布局，否则，直接修改内容
        if (convertView == null) {
            // 通过inflate的方法加载布局，context需要在使用这个Adapter的Activity中传入。
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_single_recyclerview, null);
            viewHolder = new ViewHolder();
            viewHolder.mTv = (TextView) convertView.findViewById(R.id.rv_text);
            viewHolder.mIcon= (TextView) convertView.findViewById(R.id.rv_icon);
            convertView.setTag(viewHolder); // 用setTag方法将处理好的viewHolder放入view中
        } else { // 否则，让convertView等于view，然后从中取出ViewHolder即可
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // 从viewHolder中取出对应的对象，然后赋值给他们
        viewHolder.mTv.setText(list.get(position).getName());
        viewHolder.mIcon.setText(list.get(position).getCategory().subSequence(0,1));
        // 将这个处理好的view返回
        return convertView;
    }

    /**
     * @description 删除收藏夹的元素
     * @param name: 要删除的食物的名称
     * @return
     * @author LDN
     * @time 2020/5/6 17:21
     */
    public void deleteItemByName(String name) {
        if(list == null || list.isEmpty()) {
            return;
        }
        for(Food i : list){
            if(i.getName().equals(name)){
                list.remove(i);
                System.out.print("successful");
                break;
            }
        }
        notifyDataSetChanged();
    }


    public void deleteItem(int pos) {
        if(list == null || list.isEmpty()) {
            return;
        }
        list.remove(pos);
        notifyDataSetChanged();
    }

    public void addNewItem(Food f) {
        if(list == null) {
            list = new ArrayList<>();
        }
        for(Food i : list){
            if(i.getName().equals(f.getName())){
                notifyDataSetChanged();
                return;
            }
        }
        list.add(f);
        notifyDataSetChanged();
    }

}
