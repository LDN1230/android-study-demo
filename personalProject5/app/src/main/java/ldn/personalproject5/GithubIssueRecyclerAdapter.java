package ldn.personalproject5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * @author: LDN
 * @date: 2020/5/13
 */
public class GithubIssueRecyclerAdapter extends RecyclerView.Adapter<GithubIssueRecyclerAdapter.ViewHolder> {
    private ArrayList<GithubIssueObj> issues;

    public GithubIssueRecyclerAdapter(){
        issues = new ArrayList<>();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView issue_title, issue_create_date, issue_body, issue_state;
        public ViewHolder(View itemView) {
            super(itemView);
            issue_title = itemView.findViewById(R.id.issue_title);
            issue_create_date = itemView.findViewById(R.id.issue_create_date);
            issue_body = itemView.findViewById(R.id.issue_body);
            issue_state = itemView.findViewById(R.id.issue_state);
        }
    }

    @NonNull
    @Override
    public GithubIssueRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.issue_recycler_item, parent, false);
        // 实例化viewholder
        GithubIssueRecyclerAdapter.ViewHolder viewHolder = new GithubIssueRecyclerAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GithubIssueRecyclerAdapter.ViewHolder holder, int position) {
        // 绑定数据
        holder.issue_body.setText(issues.get(position).getBody());
        holder.issue_create_date.setText(issues.get(position).getCreated_at());
        holder.issue_state.setText(issues.get(position).getState());
        holder.issue_title.setText(issues.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return issues.size();
    }

    public void addItem(GithubIssueObj githubIssueObj){
        if(issues != null){
            issues.add(githubIssueObj);
            notifyItemInserted(issues.size() - 1);
        }
    }

    //同样有个清空列表的操作
    public void reset(){
        int count = issues.size();
        issues.clear();
        notifyItemRangeRemoved(0,count);
    }

}
