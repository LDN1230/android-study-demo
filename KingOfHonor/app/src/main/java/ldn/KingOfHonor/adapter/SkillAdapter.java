package ldn.KingOfHonor.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ldn.KingOfHonor.R;
import ldn.KingOfHonor.model.Skill;

/**
 * @author: LDN
 * @date: 2020/6/12
 */
public class SkillAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Skill> list;
    private int mIndex;
    private int mPagerSize;

    public SkillAdapter(Context context, ArrayList<Skill> list, int mIndex, int mPagerSize){
        this.context = context;
        this.list = list;
        this.mIndex = mIndex;
        this.mPagerSize = mPagerSize;
    }

    @Override
    public int getCount() {
        return list.size() > (mIndex + 1) * mPagerSize ? mPagerSize : (list.size() - mIndex * mPagerSize);
    }

    @Override
    public Object getItem(int position) {
        return list.get(position + mPagerSize*mIndex);
    }

    @Override
    public long getItemId(int position) {
        return position + mPagerSize*mIndex;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.skill_detail, null);
            holder.skill_name = (TextView) convertView.findViewById(R.id.skill_name);
            holder.skill_image = (ImageView) convertView.findViewById(R.id.skill_image);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        final int pos = position + mIndex*mPagerSize;
        holder.skill_image.setImageResource(list.get(pos).getImage());
        holder.skill_name.setText(list.get(pos).getName());
        return convertView;
    }

    class ViewHolder{
        private TextView skill_name;
        private ImageView skill_image;
    }
}
