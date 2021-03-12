package com.part.customcircularprogress;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button btn;
    ProgressBar progressBar;
    private int progr = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.myrecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        myList();
        //creating recyclerview adapter
    }

    public void myList() {
        RequestQueue requestQueue;
        RecyclerViewClickInterface listener;
        listener = null;
        ArrayList<SubjectDetails> subjectDetailsList = new ArrayList<>();
        MyGridViewAdapter adapter = new MyGridViewAdapter(this, subjectDetailsList, listener);
        requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://classroom.rkmshillong.org/classRoom/getSubject?courseID=1034", null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Response", response.toString());
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject obj = response.getJSONObject(i);
                                Log.d("myobject", obj.getString("title"));
                                String subjectTitle = obj.getString("title");
                                SubjectDetails subjectDetails = new SubjectDetails(obj.getString("title"), obj.getInt("courseID"), obj.getInt("subjectID"));
                                Log.d("Test", String.valueOf(subjectDetails.getSubjectId()));
                                subjectDetailsList.add(subjectDetails);
                                Log.d("InsideVolley", String.valueOf(subjectDetailsList.get(i)));
                                adapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(jsonArrayRequest);
        Log.d("InsideVolley", subjectDetailsList.toString());
        Log.d("Adapter", subjectDetailsList.toString());
        recyclerView.setAdapter(adapter);

    }
}