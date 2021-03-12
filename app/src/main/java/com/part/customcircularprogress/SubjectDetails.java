package com.part.customcircularprogress;

public class SubjectDetails {

    private String subjectTitle;
    private int courseId;
    private int subjectId;

    public int getCourseId() {
        return courseId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public SubjectDetails(String subjectTitle, int courseId, int subjectId) {
        this.subjectTitle = subjectTitle;
        this.courseId = courseId;
        this.subjectId = subjectId;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }
}
