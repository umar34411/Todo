package com.umarfarooqdogar.todoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {


    ArrayList<task> list;
    MainActivity context;


    public RecyclerAdapter(ArrayList<task> list, Context context) {
        this.list = list;
        this.context = (MainActivity) context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tv_TaskName.setText(list.get(position).taskname);
        holder.tv_items.setText(list.get(position).items);

        Integer priority=list.get(position).priority;

        if(priority==3)
        {
            holder.iv_Priority.setImageDrawable(context.getResources().getDrawable(R.drawable.red));
        }else if(priority==2)
        {
            holder.iv_Priority.setImageDrawable(context.getResources().getDrawable(R.drawable.yellow));
        }else{
            holder.iv_Priority.setImageDrawable(context.getResources().getDrawable(R.drawable.green));
        }

       holder.tv_TaskName.setTag(position);


    }

    public class  ViewHolder extends  RecyclerView.ViewHolder{
        TextView tv_TaskName,tv_items;
        ImageView iv_Priority;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_TaskName=itemView.findViewById(R.id.tv_taskName);
            tv_items=itemView.findViewById(R.id.tv_items);
            iv_Priority=itemView.findViewById(R.id.iv_priority);

           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   context.HandleClick((Integer) tv_TaskName.getTag());
               }
           });

           itemView.setOnLongClickListener(new View.OnLongClickListener() {
               @Override
               public boolean onLongClick(View v) {
                   context.HandleLongClick((Integer) tv_TaskName.getTag());
                   return false;
               }
           });

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
