package com.part.customcircularprogress;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.part.customcircularprogress.NestedRecycler.LectureChapterActivity;

import java.util.List;

public class MyGridViewAdapter extends RecyclerView.Adapter<MyGridViewAdapter.MyGridViewHolder> {
    private Context mContext;
    private List<SubjectDetails> subjectDetailsList;
    private final RecyclerViewClickInterface listener;

    //getting the context and product list with constructor
    public MyGridViewAdapter(Context mContext, List<SubjectDetails> subjectDetails, RecyclerViewClickInterface listener) {
        this.mContext = mContext;
        this.subjectDetailsList = subjectDetails;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyGridViewAdapter.MyGridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        View view = LayoutInflater.from(mContext).inflate(R.layout.progressbar_item, parent, false);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        return new MyGridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyGridViewAdapter.MyGridViewHolder holder, int position) {

        SubjectDetails subjectDetails = subjectDetailsList.get(position);
        holder.progressBar.setProgress(10);
        holder.txtPercentage.setText("30");
        holder.txtSubject.setText(subjectDetails.getSubjectTitle());
        holder.progressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "clicked on ", Toast.LENGTH_LONG).show();
                int reqdCourseId = subjectDetails.getCourseId();
                Log.d("Fromadapter", String.valueOf(reqdCourseId));
                int reqdSubjectId = subjectDetails.getSubjectId();
                String reqdTitle = subjectDetails.getSubjectTitle();

                Intent intent = new Intent(mContext.getApplicationContext(), LectureChapterActivity.class);
                intent.putExtra("subjectid", reqdSubjectId);
                intent.putExtra("courseid", reqdCourseId);
                intent.putExtra("title", reqdTitle);
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return subjectDetailsList.size();
    }

    public class MyGridViewHolder extends RecyclerView.ViewHolder {
        TextView txtSubject, txtPercentage;
        ProgressBar progressBar;

        public MyGridViewHolder(@NonNull View itemView) {
            super(itemView);

            txtSubject = itemView.findViewById(R.id.txtSub);
            txtPercentage = itemView.findViewById(R.id.txtPercentage);
            progressBar = itemView.findViewById(R.id.myProgress);

        }
    }
}
