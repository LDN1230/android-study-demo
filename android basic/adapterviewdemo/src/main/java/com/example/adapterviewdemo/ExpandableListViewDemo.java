package com.example.adapterviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ExpandableListViewDemo extends AppCompatActivity {

    class ViewHolder{
        ImageView imageView;
        TextView textView;
        public ViewHolder(ImageView imageView, TextView textView){
            this.imageView = imageView;
            this.textView = textView;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view_demo);
        BaseExpandableListAdapter adapter = new BaseExpandableListAdapter() {
            int[] logos = new int[]{R.drawable.dog, R.drawable.cat, R.drawable.pig};
            String[] animalTypes = new String[]{"狗狗", "猫咪", "猪猪"};
            String[][] animals = new String[][]{
                    new String[]{"黑狗", "白狗", "花狗", "野狗"},
                    new String[]{"大猫", "小猫", "猫仔"},
                    new String[]{"死肥猪", "猪猪侠"}
            };

            @Override
            public int getGroupCount() {
                return animalTypes.length;
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return animals[groupPosition].length;
            }

            @Override
            public Object getGroup(int groupPosition) {
                return animalTypes[groupPosition];
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return animals[groupPosition][childPosition];
            }

            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            private TextView getTextView(){
                TextView textView = new TextView(ExpandableListViewDemo.this);
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                textView.setLayoutParams(lp);
                textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.START);
                textView.setPadding(36, 10, 0, 10);
                textView.setTextSize(20f);
                return  textView;
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                LinearLayout ll;
                ViewHolder viewHolder;
                if(convertView == null){
                   ll = new LinearLayout(ExpandableListViewDemo.this);
                   ll.setOrientation(LinearLayout.HORIZONTAL);
                   ImageView logo = new ImageView(ExpandableListViewDemo.this);
                   ll.addView(logo);
                   TextView textView = this.getTextView();
                   ll.addView(textView);
                   viewHolder = new ViewHolder(logo, textView);
                   ll.setTag(viewHolder);
                }else{
                    ll = (LinearLayout)convertView;
                    viewHolder = (ViewHolder)ll.getTag();
                }
                viewHolder.imageView.setImageResource(logos[groupPosition]);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100,100);
                viewHolder.imageView.setLayoutParams(params);
                viewHolder.textView.setText(getGroup(groupPosition).toString());
                return ll;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                TextView textView;
                if (convertView == null){
                    textView = this.getTextView();
                }else {
                    textView = (TextView)convertView;
                }
                textView.setText(getChild(groupPosition, childPosition).toString());
                return textView;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }
        };
        ExpandableListView expandableList = findViewById(R.id.expandableList);
        expandableList.setAdapter(adapter);
    }
}
