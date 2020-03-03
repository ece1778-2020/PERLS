package com.ece1778.perls;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class ExerciseViewModel extends ViewModel {

    private MutableLiveData<Question> q1, q2, q3, q4, q5, actions;
    private MutableLiveData<String> action;
    private String timestamp, uid, emotion;

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
                options.add("An important goal was blocked.");
                options.add("A desired activity got interrupted or prevented.");
                options.add("You or someone you care about was hurt/attacked by others.");
                options.add("You or someone you care about was insulted/threatened by others.");
                options.add("Your integrity or that of your social group was offended/threatened. ");
                options.add("Physical or emotional pain.");
                options.add("None of these.");
                q1.setValue(new Question(text, options, ""));
                break;
            case 2:
                text = "Did you think any of the following?";
                options = new ArrayList<String>();
                options.add("I've been treated unfairly. ");
                options.add("This shouldn't have happened/things should be different. ");
                options.add("That I'm right and others don't agree.");
                options.add("What happened was wrong and/or unacceptable.");
                options.add("I've been ruminating about the situation.");
                options.add("None of these.");
                q2.setValue(new Question(text, options, ""));
                break;

            case 3:
                text = "Did you feel any of the following?";
                options = new ArrayList<String>();
                options.add("Muscles tightening");
                options.add("Teeth clamping");
                options.add("Hands clenching");
                options.add("Feeling flush/hot ");
                options.add("Being unable to stop tears");
                options.add("Wanting to attack or lash out");
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
                break;

            case 6:
                text = "If you want to feel differently, choose one of the opposite action:";
                options = new ArrayList<String>();
                options.add("step into the other person's shoes/see the situation from their point of view");
                options.add("change your posture/relax chest and stomach muscles/unclench");
                options.add("slowly bring the corners of your mouth up to a half-smile");
                options.add("relax your hands and hold your palms face up on your lap");
                options.add("pace your breathing slowly and deliberately");
                options.add("gently avoid the person/situation");
                options.add("be a little nice, half-agree, or compliment");
                options.add("engage in another physically energetic but non-violent activity (i.e., push-ups)");
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
