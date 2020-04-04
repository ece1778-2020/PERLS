package com.ece1778.perls;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OptionListAdapter extends RecyclerView.Adapter<OptionListAdapter.OptionViewHolder>{
    private final ArrayList<String> mOptionList;
    private LayoutInflater mInflater;
    private Context mContext;
    private ExerciseViewModel mViewModel;

    public OptionListAdapter(Context context, ArrayList<String> optionList, ExerciseViewModel model){
        mInflater = LayoutInflater.from(context);
        this.mOptionList = optionList;
        mContext = context;
        mViewModel = model;
    }


    class OptionViewHolder extends RecyclerView.ViewHolder {
        public final TextView optionTextView; //maybe make this the cardView
        public final CardView optionCardView;
        final OptionListAdapter mAdapter;

        public OptionViewHolder(View itemView, OptionListAdapter adapter){
            super(itemView);
            optionTextView = itemView.findViewById(R.id.optionTextView);
            optionCardView = itemView.findViewById(R.id.optionCardView);
            this.mAdapter = adapter;

        }

    }
    @NonNull
    @Override
    public OptionListAdapter.OptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.option_item, parent, false);
        return new OptionViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull final OptionListAdapter.OptionViewHolder holder, int position) {
        final String mCurrent = mOptionList.get(position);
        holder.optionTextView.setText(mCurrent);
        holder.optionCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext, "Click listener working", Toast.LENGTH_SHORT).show();
                if (holder.optionCardView.getCardBackgroundColor().equals(mContext.getColor(R.color.dark_purple))){
                    holder.optionCardView.setCardBackgroundColor(mContext.getColor(R.color.lighter_purple));
                }
                holder.optionCardView.setCardBackgroundColor(mContext.getColor(R.color.dark_purple));
                String answer = holder.optionTextView.getText().toString();
                ArrayList<String> answers = new ArrayList<>();
                answers = mViewModel.getAnswers();
                answers.add(answer);
                mViewModel.setAnswers(answers);

            }
        });



    }

    @Override
    public int getItemCount() {
        return mOptionList.size();
    }
}
