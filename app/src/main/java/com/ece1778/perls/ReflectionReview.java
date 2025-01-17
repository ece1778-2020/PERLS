package com.ece1778.perls;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ReflectionReview extends AppCompatActivity {

    private static final String EXERCISE_COLLECTION = "exercises";
    private static final String REFLECTION_COLLECTION = "reflections";
    private static final String TAG = "ReflectionReview";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ReflectionAdapter mReflectionAdapter;
    private ArrayList<Reflection> reflections;
    private HashMap<String, HashMap<String, String>> exercises;
    private HashMap<String, HashMap<String, String>> responses;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflection_review);

        recyclerView = findViewById(R.id.reflection_recycler_view);
        recyclerView.setHasFixedSize(true);

        exercises = new HashMap<>();
        responses = new HashMap<>();
        reflections = new ArrayList<>();
        getData();


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setupList(){
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        reflections.sort(new SortTime());
        mReflectionAdapter = new ReflectionAdapter(this, reflections);
        recyclerView.setAdapter(mReflectionAdapter);
    }

    private void getData(){
        db = FirebaseFirestore.getInstance();

        db.collection(EXERCISE_COLLECTION)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                HashMap<String, String> exercise = new HashMap<>();
                                if (document.get("name").equals(getString(R.string.opposite_action))) {
                                    exercise.put("uid", document.get("uid").toString());
                                    exercise.put("timestamp", document.get("timestamp").toString());
                                    exercise.put("emotion",document.get("emotion").toString());
                                    exercise.put("name", document.get("name").toString());
                                    exercise.put("q1", document.get("q1").toString());
                                    exercise.put("q2", document.get("q2").toString());
                                    exercise.put("q3", document.get("q3").toString());
                                    exercise.put("q4", document.get("q4").toString());
                                    exercise.put("q5", document.get("q5").toString());
                                    exercise.put("action", document.get("action").toString());
                                    exercises.put(document.get("uid").toString(), exercise);
                                } else {
                                    exercise.put("uid", document.get("uid").toString());
                                    exercise.put("timestamp", document.get("timestamp").toString());
                                    exercise.put("emotion",document.get("emotion").toString());
                                    exercise.put("name", document.get("name").toString());
                                    exercise.put("q1", "");
                                    exercise.put("q2", "");
                                    exercise.put("q3", "");
                                    exercise.put("q4", "");
                                    exercise.put("q5", "");
                                    exercise.put("action", "");
                                    exercises.put(document.get("uid").toString(), exercise);
                                }

                            }
                            getReflections();
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });



    }

    private void getReflections(){
        db.collection(REFLECTION_COLLECTION)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                HashMap<String, String> reflection = new HashMap<>();
                                reflection.put("uid", document.get("uid").toString());
                                reflection.put("prompt", document.get("prompt").toString());
                                reflection.put("response", document.get("response").toString());
                                responses.put(document.get("uid").toString(), reflection);
                            }
                            mergeData();
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void mergeData(){
        Iterator it = responses.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();

            HashMap<String, String> response = (HashMap<String, String>)pair.getValue();
            HashMap<String, String> exercise = exercises.get(response.get("uid"));
            Reflection ref = new Reflection(
                    exercise.get("emotion"),
                    exercise.get("name"),
                    exercise.get("q1"),
                    exercise.get("q2"),
                    exercise.get("q3"),
                    exercise.get("q4"),
                    exercise.get("q5"),
                    exercise.get("action"),
                    exercise.get("timestamp"),
                    response.get("response"));
            reflections.add(ref);
        }
        setupList();
    }

    public class SortTime implements Comparator<Reflection>{
        @Override
        public int compare(Reflection reflection, Reflection t1) {
            return (int)( Long.parseLong(t1.getTimestamp()) - Long.parseLong(reflection.getTimestamp()));
        }
    }
}
