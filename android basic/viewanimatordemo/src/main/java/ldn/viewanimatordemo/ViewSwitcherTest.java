package ldn.viewanimatordemo;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ViewSwitcherTest extends AppCompatActivity {

    public static int NUMBER_PER_SCREEN = 12;
    private List<DataItem> items = new ArrayList<>();
    private int screenNo = -1;
    private int screenCount = 0;
    private ViewSwitcher switcher;
    private LayoutInflater inflater;
    private BaseAdapter adapter = new BaseAdapter() {
        @Override
        public int getCount() {
            if (screenNo==screenCount-1 && items.size()%NUMBER_PER_SCREEN!=0){
                return items.size()%NUMBER_PER_SCREEN;
            }else {
                return NUMBER_PER_SCREEN;
            }
        }

        @Override
        public DataItem getItem(int position) {
            return items.get(screenNo*NUMBER_PER_SCREEN+position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            ViewHolder viewHolder;
            if (convertView == null){
                view = inflater.inflate(R.layout.labelicon, null);
                ImageView imageView = view.findViewById(R.id.imageView);
                TextView textView = view.findViewById(R.id.textView);
                viewHolder = new ViewHolder(imageView, textView);
                view.setTag(viewHolder);
            }else {
                view = convertView;
                viewHolder = (ViewHolder)view.getTag();
            }
            viewHolder.imageView.setImageDrawable(getItem(position).drawable);
            viewHolder.textView.setText(getItem(position).dataName);
            return view;
        }
    };

    public static class DataItem{
        String dataName;
        Drawable drawable;
        DataItem(String dataName, Drawable drawable){
            this.dataName = dataName;
            this.drawable = drawable;
        }
    }

    public static class ViewHolder{
        ImageView imageView;
        TextView textView;
        ViewHolder(ImageView imageView, TextView textView){
            this.imageView = imageView;
            this.textView = textView;
        }
    }

    public void next(View v){
        if (screenNo < screenCount-1){
            screenNo++;
            switcher.setInAnimation(this, R.anim.slide_in_right);
            switcher.setOutAnimation(this, R.anim.slide_out_left);
            ((GridView)switcher.getNextView()).setAdapter(adapter);
            switcher.showNext();
        }
    }

    public void prev(View v){
        if (screenNo > 0){
            screenNo--;
            switcher.setInAnimation(this, R.anim.slide_in_left);
            switcher.setOutAnimation(this, R.anim.slide_out_right);
            ((GridView)switcher.getNextView()).setAdapter(adapter);
            switcher.showPrevious();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_switcher_test);
        inflater = LayoutInflater.from(this);
        for (int i=0; i<40; i++){
            String label = ""+i;
            Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher, null);
            DataItem item = new DataItem(label, drawable);
            items.add(item);
        }
        screenCount = items.size()%NUMBER_PER_SCREEN == 0 ? items.size()/NUMBER_PER_SCREEN : items.size()/NUMBER_PER_SCREEN+1;
        switcher = findViewById(R.id.viewSwitcher);
        switcher.setFactory(()->{
            return inflater.inflate(R.layout.slide_list_view, null);
        });
        next(null);
    }
}
