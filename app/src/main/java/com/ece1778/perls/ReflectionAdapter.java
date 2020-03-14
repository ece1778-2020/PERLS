package com.ece1778.perls;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class ReflectionAdapter extends RecyclerView.Adapter<ReflectionAdapter.ReflectionViewHolder> {

    private ArrayList<Reflection> reflections;

    public ReflectionAdapter(ArrayList<Reflection> reflections){
        this.reflections = reflections;
    }

    @NonNull
    @Override
    public ReflectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reflection_layout, parent, false);
        ReflectionViewHolder reflectionViewHolder = new ReflectionViewHolder(view);

        return reflectionViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReflectionViewHolder holder, int position) {
        Reflection reflection = reflections.get(position);
        holder.emotion.setText(reflection.getEmotion());
        holder.q1.setText(reflection.getQ1());
        holder.q2.setText(reflection.getQ2());
        holder.q3.setText(reflection.getQ3());
        holder.q4.setText(reflection.getQ4());
        holder.q5.setText(reflection.getQ5());
        holder.action.setText(reflection.getAction());
        holder.reflection.setText(reflection.getReflection());

    }

    @Override
    public int getItemCount() {
        if(reflections != null){
            return reflections.size();
        }
        return 0;
    }

    public static class ReflectionViewHolder extends  RecyclerView.ViewHolder{
        TextView emotion, q1, q2, q3, q4, q5, action, reflection;

        public ReflectionViewHolder(View itemView){
            super(itemView);
            emotion = itemView.findViewById(R.id.emotion);
            q1 = itemView.findViewById(R.id.q1);
            q2 = itemView.findViewById(R.id.q2);
            q3 = itemView.findViewById(R.id.q3);
            q4 = itemView.findViewById(R.id.q4);
            q5 = itemView.findViewById(R.id.q5);
            action = itemView.findViewById(R.id.action);
            reflection = itemView.findViewById(R.id.reflection);
        }
    }
}
