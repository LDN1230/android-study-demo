package com.example.adapterviewdemo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewDemo extends AppCompatActivity {

    private RecyclerView recycler;
    private List<Person> personList = new ArrayList<>();

    private class Person{
        String name;
        String desc;
        int imageId;

        public Person(String name, String desc, int imageId) {
            this.name = name;
            this.desc = desc;
            this.imageId = imageId;
        }
    }
    private void initData(){
         String[] names = new String[]{"虎头", "弄玉", "李清照", "李白"};
         String[] descs = new String[]{"可爱的小孩", "一个擅长音乐的女孩", "一个擅长文学的女性", "浪漫主义诗人"};
         int[] imageIds = new int[]{R.drawable.tiger, R.drawable.nongyu, R.drawable.liqingzhao, R.drawable.libai};
         for (int i=0; i<names.length; i++){
             this.personList.add(new Person(names[i], descs[i], imageIds[i]));
         }
    }

    class PersonViewHolder extends RecyclerView.ViewHolder{
        View rootView;
        TextView nameTv;
        TextView descTv;
        ImageView headerTv;
        private RecyclerView.Adapter adapter;
        public PersonViewHolder(@NonNull View itemView, RecyclerView.Adapter adapter) {
            super(itemView);
            this.nameTv = itemView.findViewById(R.id.name);
            this.descTv = itemView.findViewById(R.id.desc);
            this.headerTv = itemView.findViewById(R.id.header);
            this.rootView = itemView.findViewById(R.id.item_root);
            this.adapter = adapter;
            rootView.setOnClickListener(view->{
                int i = (int)(Math.random()*(personList.size()+1));
                Person person = new Person(personList.get(i).name, personList.get(i).desc, personList.get(i).imageId);
                adapter.notifyItemInserted(2);
                personList.add(2, person);
                adapter.notifyItemRangeChanged(2, adapter.getItemCount());
            });
            rootView.setOnLongClickListener(view->{
                int position = this.getAdapterPosition();
                adapter.notifyItemRemoved(position);
                RecyclerViewDemo.this.personList.remove(position);
                adapter.notifyItemRangeChanged(position, adapter.getItemCount());
                return false;
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_demo);
        recycler = findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);
        initData();
        RecyclerView.Adapter adapter = new RecyclerView.Adapter<PersonViewHolder>() {
            @NonNull
            @Override
            public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(RecyclerViewDemo.this).inflate(R.layout.simple_item, null);
                return new PersonViewHolder(view, this);
            }

            @Override
            public void onBindViewHolder(@NonNull PersonViewHolder viewHolder, int i) {
                viewHolder.nameTv.setText(personList.get(i).name);
                viewHolder.descTv.setText(personList.get(i).desc);
                viewHolder.headerTv.setImageResource(personList.get(i).imageId);

            }

            @Override
            public int getItemCount() {
                return personList.size();
            }
        };
        recycler.setAdapter(adapter);

    }
}
