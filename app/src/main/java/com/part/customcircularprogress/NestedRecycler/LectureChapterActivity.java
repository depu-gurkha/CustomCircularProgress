package com.part.customcircularprogress.NestedRecycler;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.part.customcircularprogress.R;
import com.part.customcircularprogress.RecyclerViewClickInterface;
import com.part.customcircularprogress.SubjectDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LectureChapterActivity extends AppCompatActivity {
    RecyclerView rvChapter;
    LinearLayoutManager layoutManagerGroup;
    ChapterAd adapterChapter;
    SubjectDetails subjectDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture_chapter);
        Log.d("inDifferentActivity", "hello from here");
        rvChapter = findViewById(R.id.rv_Chapter);
        layoutManagerGroup = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvChapter.setLayoutManager(layoutManagerGroup);
        Bundle b = getIntent().getExtras();
        subjectDetails = new SubjectDetails(b.getString("title"), b.getInt("courseid"), b.getInt("subjectid"));
        getList();

    }

    public void getList() {
        RequestQueue requestQueue;
        RecyclerViewClickInterface listener;
        listener = null;
        ArrayList<ChapterDetails> chapterDetailsArrayList = new ArrayList<>();

        ArrayList<Lecture> lecturesDetailsArray = new ArrayList<>();
        ChapterAd adapter = new ChapterAd(this, chapterDetailsArrayList, lecturesDetailsArray);
        requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://classroom.rkmshillong.org/classRoom/getContent?subject=" + subjectDetails.getSubjectId() + "&courseID=" + subjectDetails.getCourseId(), null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Response", response.toString());
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject obj = response.getJSONObject(i);
                                Log.d("myobject", obj.getString("title"));
                                ChapterDetails chapterDetails = new ChapterDetails(obj.getString("title"), obj.getInt("items"));
                                Log.d("Test", String.valueOf(chapterDetails.getItems()));
                                chapterDetailsArrayList.add(chapterDetails);
                                Log.d("InsideVolley", String.valueOf(chapterDetailsArrayList.get(i)));
                                JSONArray jsonArrayLecture = new JSONArray(obj.getString("content"));
                                for (int j = 0; j < jsonArrayLecture.length(); j++) {
                                    JSONObject jsonObjectLecture = jsonArrayLecture.getJSONObject(j);
                                    Lecture lecture = new Lecture(jsonObjectLecture.getString("title"), jsonObjectLecture.getInt("lectureID"), jsonObjectLecture.getString("file"));
                                    lecturesDetailsArray.add(lecture);
                                    Log.d("jthadded", lecturesDetailsArray.toString());
                                }
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
        Log.d("InsideVolley", chapterDetailsArrayList.toString());
        Log.d("Adapter", chapterDetailsArrayList.toString());
        rvChapter.setAdapter(adapter);
    }
}