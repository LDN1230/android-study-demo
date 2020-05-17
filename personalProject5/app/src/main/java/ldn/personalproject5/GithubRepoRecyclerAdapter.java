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
public class GithubRepoRecyclerAdapter extends RecyclerView.Adapter<GithubRepoRecyclerAdapter.ViewHolder> {

    private ArrayList<GithubRepoObj> repos;
    private GithubRepoRecyclerAdapter.OnItemClickListener onItemClickListener;
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public GithubRepoRecyclerAdapter(){
        repos = new ArrayList<>();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView repo_name, repo_id, repo_description, repo_issues_count;
        public ViewHolder(View itemView) {
            super(itemView);
            repo_name = itemView.findViewById(R.id.repo_name);
            repo_id = itemView.findViewById(R.id.repo_id);
            repo_description = itemView.findViewById(R.id.repo_description);
            repo_issues_count = itemView.findViewById(R.id.repo_issues_count);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_recycler_item, parent, false);
        // 实例化viewholder
        GithubRepoRecyclerAdapter.ViewHolder viewHolder = new GithubRepoRecyclerAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        // 绑定数据
        holder.repo_name.setText(repos.get(position).getName());
        //老生常谈！！！int转String
        holder.repo_issues_count.setText(String.valueOf(repos.get(position).getOpen_issues_count()));
        holder.repo_description.setText(repos.get(position).getDescription());
        holder.repo_id.setText(repos.get(position).getId());
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
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    public void setOnItemClickListener(GithubRepoRecyclerAdapter.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public String getRepoName(int position){
        return repos.get(position).getName();
    }

    //清空RecyclerView列表
    public void reset(){
        int count = repos.size();
        repos.clear();
        notifyItemRangeRemoved(0,count);
    }

    public void addItem(GithubRepoObj githubRepoObj){
        if(repos != null){
            repos.add(githubRepoObj);
            notifyItemInserted(repos.size() - 1);
        }
    }
}
