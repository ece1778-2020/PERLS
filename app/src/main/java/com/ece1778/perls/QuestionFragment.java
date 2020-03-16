package com.ece1778.perls;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class QuestionFragment extends Fragment {

    private int mPosition;
    private static final String ARG_POSITION = "position";
    private ExerciseViewModel mViewModel;
    private RadioGroup mRadioGroup;
    private TextView mTextView;
    private View mView;
    private RecyclerView mRecyclerView;
    private OptionListAdapter mAdapter;
    private ArrayList<String> mOptionList;


    public QuestionFragment() {
        // Required empty public constructor
    }

    public static QuestionFragment newInstance(int position) {

        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPosition = getArguments().getInt(ARG_POSITION);
        }

        mViewModel = new ViewModelProvider(requireActivity()).get(ExerciseViewModel.class);
        mViewModel.setPosition(mPosition);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_question, container, false);
        //mRadioGroup = mView.findViewById(R.id.options);
        //mRadioGroup.removeAllViewsInLayout();
        mTextView = mView.findViewById(R.id.question);
        mOptionList = new ArrayList<>();
        mRecyclerView = mView.findViewById(R.id.optionRecyclerView);
        mAdapter = new OptionListAdapter(requireActivity(), mOptionList, mViewModel);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        loadQuestion();

        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String answer = ((RadioButton) mView.findViewById(checkedId)).getText().toString();

                switch (mPosition) {
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
        });*/
    }


    private void loadQuestion() {
        //Retrieve the appropriate question depending on position
        switch (mPosition) {
            case 0:
                Question q1 = mViewModel.getQ1().getValue();
                mTextView.setText(q1.getQuestion());
                for (String option : q1.getOptions()) {
                    /*RadioButton btn = new RadioButton(mView.getContext());
                    btn.setText(option);
                    btn.setId(View.generateViewId());
                    mRadioGroup.addView(btn);*/
                    mOptionList.add(option);
                    mAdapter.notifyItemInserted(mOptionList.size() - 1);
                    mRecyclerView.scrollToPosition(mOptionList.size() - 1);
                }
                break;

            case 1:
                Question q2 = mViewModel.getQ2().getValue();
                mTextView.setText(q2.getQuestion());
                for (String option : q2.getOptions()) {
                    /*RadioButton btn = new RadioButton(mView.getContext());
                    btn.setText(option);
                    btn.setId(View.generateViewId());
                    mRadioGroup.addView(btn);*/
                    mOptionList.add(option);
                    mAdapter.notifyItemInserted(mOptionList.size() - 1);
                    mRecyclerView.scrollToPosition(mOptionList.size() - 1);
                }
                break;

            case 2:
                Question q3 = mViewModel.getQ3().getValue();
                mTextView.setText(q3.getQuestion());
                for (String option : q3.getOptions()) {
                    /*RadioButton btn = new RadioButton(mView.getContext());
                    btn.setText(option);
                    btn.setId(View.generateViewId());
                    mRadioGroup.addView(btn);*/
                    mOptionList.add(option);
                    mAdapter.notifyItemInserted(mOptionList.size() - 1);
                    mRecyclerView.scrollToPosition(mOptionList.size() - 1);
                }
                break;

            case 3:
                Question q4 = mViewModel.getQ4().getValue();
                mTextView.setText(q4.getQuestion());
                for (String option : q4.getOptions()) {
                    /*RadioButton btn = new RadioButton(mView.getContext());
                    btn.setText(option);
                    btn.setId(View.generateViewId());
                    mRadioGroup.addView(btn);*/
                    mOptionList.add(option);
                    mAdapter.notifyItemInserted(mOptionList.size() - 1);
                    mRecyclerView.scrollToPosition(mOptionList.size() - 1);
                }
                break;

            case 4:
                Question q5 = mViewModel.getQ5().getValue();
                mTextView.setText(q5.getQuestion());
                for (String option : q5.getOptions()) {
                    /*RadioButton btn = new RadioButton(mView.getContext());
                    btn.setText(option);
                    btn.setId(View.generateViewId());
                    mRadioGroup.addView(btn);*/
                    mOptionList.add(option);
                    mAdapter.notifyItemInserted(mOptionList.size() - 1);
                    mRecyclerView.scrollToPosition(mOptionList.size() - 1);
                }
                break;

            case 5:
                Question actions = mViewModel.getActions().getValue();
                mTextView.setText(actions.getQuestion());
                for (String option : actions.getOptions()) {
                    /*RadioButton btn = new RadioButton(mView.getContext());
                    btn.setText(option);
                    btn.setId(View.generateViewId());
                    mRadioGroup.addView(btn);*/
                    mOptionList.add(option);
                    mAdapter.notifyItemInserted(mOptionList.size() - 1);
                    mRecyclerView.scrollToPosition(mOptionList.size() - 1);
                }
                break;
            default:

                //return null;
        }

        //Load the question into the view
    }

}
