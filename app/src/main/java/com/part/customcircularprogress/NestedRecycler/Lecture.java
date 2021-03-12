package com.part.customcircularprogress.NestedRecycler;

public class Lecture {
    private String title;
    private int lectureId;
    private String lectureUrl;

    public Lecture(String title, int lectureId, String lectureUrl) {
        this.title = title;
        this.lectureId = lectureId;
        this.lectureUrl = lectureUrl;
    }

    public String getTitle() {
        return title;
    }

    public int getLectureId() {
        return lectureId;
    }

    public String getLectureUrl() {
        return lectureUrl;
    }
}
