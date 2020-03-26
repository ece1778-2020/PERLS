package com.ece1778.perls;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class ExerciseViewModel extends ViewModel {

    private MutableLiveData<Question> q1, q2, q3, q4, q5, actions;
    private MutableLiveData<String> action;
    private String timestamp;
    private String uid;
    private String emotion;
    private String session_ts;
    private String session_uid;
    private int position;
    private String exercise_name;
    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }



    public String getExercise_name() {
        return exercise_name;
    }

    public void setExercise_name(String exercise_name) {
        this.exercise_name = exercise_name;
    }



    public String getSession_ts() {
        return session_ts;
    }

    public void setSession_ts(String session_ts) {
        this.session_ts = session_ts;
    }

    public String getSession_uid() {
        return session_uid;
    }

    public void setSession_uid(String session_uid) {
        this.session_uid = session_uid;
    }



    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }




    public MutableLiveData<Question> getQ1() {
        if (q1 == null){
            q1 = new MutableLiveData<Question>();
            loadData(1);
        }
        return q1;
    }


    public MutableLiveData<Question> getQ2() {
        if (q2 == null){
            q2 = new MutableLiveData<Question>();
            loadData(2);
        }
        return q2;
    }


    public MutableLiveData<Question> getQ3() {
        if (q3 == null){
            q3 = new MutableLiveData<Question>();
            loadData(3);
        }
        return q3;
    }

    private void loadData(int i) {

        Question qn;
        String text;
        ArrayList<String> options;
        switch (i) {
            case 1:
                text = "What are you reacting to?";
                options = new ArrayList<String>();
                options.add("Goal blocked.");
                options.add("Activity prevented/interrupted.");
                options.add("Someone hurt/attacked.");
                options.add("Someone insulted/threatened.");
                options.add("Integrity or morals offended. ");
                options.add("Physical or emotional pain.");
                options.add("None of these.");
                q1.setValue(new Question(text, options, ""));
                break;
            case 2:
                text = "Did you think any of the following?";
                options = new ArrayList<String>();
                options.add("Unfair treatment. ");
                options.add("This shouldn't have happened.");
                options.add("Others disagree.");
                options.add("This is wrong/unacceptable.");
                options.add("Ruminating about situation.");
                options.add("Feelings hurt.");
                q2.setValue(new Question(text, options, ""));
                break;

            case 3:
                text = "Did you feel any of the following?";
                options = new ArrayList<String>();
                options.add("Muscles tightening.");
                options.add("Teeth clamping.");
                options.add("Hands clenching.");
                options.add("Feeling flush/hot.");
                options.add("Being tearful/overwhelmed.");
                options.add("Wanting to attack/lash out.");
                q3.setValue(new Question(text, options, ""));
                break;

            case 4:
                text = "Do you feel your emotion is a valid reaction to the situation?";
                options = new ArrayList<String>();
                options.add("Yes");
                options.add("No");
                options.add("Maybe");
                options.add("Unsure");
                q4.setValue(new Question(text, options, ""));
                break;

            case 5:
                Log.d("exercise_name", exercise_name);
                Log.d("exercise_name2", context.getString(R.string.opposite_action));

                if (exercise_name.equals(context.getString(R.string.opposite_action))) {
                    text = "What is your urge to act on this emotion?";
                    options = new ArrayList<String>();
                    options.add("Physically/verbally attacking");
                    options.add("Making aggressive/threatening gestures");
                    options.add("Pounding, throwing, breaking");
                    options.add("Walking heavily, stomping, slamming");
                    options.add("Walking out");
                    options.add("Using a loud, quarrelsome, sarcastic voice");
                    options.add("Clenching your hands and fists");
                    options.add("Frowning or showing a mean expression");
                    options.add("Brooding or withdrawing from others");
                    options.add("Crying");
                    q5.setValue(new Question(text, options, ""));
                } else {
                    text = "What has to happen for you to think you have made some progress?";
                    options = new ArrayList<String>();
                    options.add("Relaxed hands");
                    options.add("Steady breath");
                    options.add("Stop crying");
                    options.add("Restrain yourself");
                    q5.setValue(new Question(text, options, ""));
                }
                
                break;

            case 6:
                if (exercise_name.equals(context.getString(R.string.opposite_action))) {
                    text = "If you want to feel differently, choose one of the opposite action:";
                } else {
                    text= "Here are some solutions, choose one to continue:";
                }

                options = new ArrayList<String>();
                options.add("See the situation from their point of view");
                options.add("Relax muscles/unclench");
                options.add("Complete a half-smile with mouth.");
                options.add("Hold palms face up on lap.");
                options.add("Lower pace of breathing slowly.");
                options.add("Gently avoid the person/situation.");
                options.add("Half-agree or compliment angry person.");
                options.add("Physical but non-violent activity");
                actions.setValue(new Question(text, options, ""));
                break;

            default:
                //return null;
        }
    }


    public MutableLiveData<Question> getQ4() {
        if (q4 == null){
            q4 = new MutableLiveData<Question>();
            loadData(4);
        }
        return q4;
    }

    public MutableLiveData<Question> getQ5() {
        if (q5 == null){
            q5 = new MutableLiveData<Question>();
            loadData(5);
        }
        return q5;
    }


    public MutableLiveData<String> getAction() {
        if (action == null){
            action = new MutableLiveData<String>();
        }
        return action;
    }


    public MutableLiveData<Question> getActions() {

        if (actions == null){
            actions = new MutableLiveData<Question>();
            loadData(6);
        }

        return actions;
    }
}
