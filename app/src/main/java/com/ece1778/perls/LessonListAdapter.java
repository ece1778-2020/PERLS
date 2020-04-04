package com.ece1778.perls;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LessonListAdapter extends RecyclerView.Adapter<LessonListAdapter.LessonViewHolder>{
    private final ArrayList<String> mLessonList;
    private LayoutInflater mInflater;
    private Context mContext;



    public LessonListAdapter(Context context, ArrayList<String> lessonList){
        mInflater = LayoutInflater.from(context);
        this.mLessonList = lessonList;
        mContext = context;
        //mPosition = position;
        //mViewModel = new ViewModelProvider(context.get).get(ExerciseViewModel.class);
    }


    class LessonViewHolder extends RecyclerView.ViewHolder {
        public final TextView lessonTextView; //maybe make this the cardView
        //public final CardView optionCardView;
        final LessonListAdapter mAdapter;

        public LessonViewHolder(View itemView, LessonListAdapter adapter){
            super(itemView);
            lessonTextView = itemView.findViewById(R.id.lessonTextView);
            Log.d("test view holder:", "yay" + lessonTextView.getText().toString());
            //optionCardView = itemView.findViewById(R.id.optionCardView);
            this.mAdapter = adapter;

        }

    }
    @NonNull
    @Override
    public LessonListAdapter.LessonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.lesson_item, parent, false);
        return new LessonViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull final LessonListAdapter.LessonViewHolder holder, int position) {
        final String mCurrent = mLessonList.get(position);
        Log.d("load lesson fcn", "got into bind view holder");
        Log.d("holder:", holder.lessonTextView.toString());
        //Log.d("text:", mCurrent);
        //Log.d("text:", "yayayyay");
        //Log.d("text:", holder.lessonTextView.getText().toString());
        holder.lessonTextView.setText(mCurrent);
        //Log.d("adding text:", holder.lessonTextView.getText().toString());


    }

    @Override
    public int getItemCount() {
        return mLessonList.size();
    }
}
