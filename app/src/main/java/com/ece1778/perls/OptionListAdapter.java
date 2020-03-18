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
    private int mPosition;

    public OptionListAdapter(Context context, ArrayList<String> optionList, ExerciseViewModel model){
        mInflater = LayoutInflater.from(context);
        this.mOptionList = optionList;
        mContext = context;
        mViewModel = model;
        //mPosition = position;
        //mViewModel = new ViewModelProvider(context.get).get(ExerciseViewModel.class);
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
        //final int size= mImageList.get(position).getWh();
        //holder.imageItemView.getHe
        //holder.imageItemView.
        /*Picasso.get()
                .load(mCurrent.toString())
                .resize(size, size)
                .centerCrop()
                .into(holder.imageItemView);*/
        //mCurrent.getPath()
        holder.optionCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Click listener working", Toast.LENGTH_SHORT).show();
                String answer = holder.optionTextView.getText().toString();

                switch (mViewModel.getPosition()) {
                    case 0:
                        Question q1 = mViewModel.getQ1().getValue();
                        q1.setAnswer(answer);
                        mViewModel.getQ1().setValue(q1);
                        break;

                    case 1:
                        Question q2 = mViewModel.getQ2().getValue();
                        q2.setAnswer(answer);
                        mViewModel.getQ2().setValue(q2);
                        break;

                    case 2:
                        Question q3 = mViewModel.getQ3().getValue();
                        q3.setAnswer(answer);
                        mViewModel.getQ3().setValue(q3);
                        break;

                    case 3:
                        Question q4 = mViewModel.getQ4().getValue();
                        q4.setAnswer(answer);
                        mViewModel.getQ4().setValue(q4);
                        break;

                    case 4:
                        Question q5 = mViewModel.getQ5().getValue();
                        q5.setAnswer(answer);
                        mViewModel.getQ5().setValue(q5);
                        break;

                    case 5:
                        Question actions = mViewModel.getActions().getValue();
                        actions.setAnswer(answer);
                        mViewModel.getActions().setValue(actions);
                        mViewModel.getAction().setValue(answer);
                        break;
                    default:

                        //return null;
                }

            }
        });



    }

    @Override
    public int getItemCount() {
        return mOptionList.size();
    }
}
