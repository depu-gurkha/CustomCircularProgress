package com.part.customcircularprogress.NestedRecycler;

public class ChapterDetails {

    private String subjectTitle;
    private int items;

    public ChapterDetails(String subjectTitle, int items) {
        this.subjectTitle = subjectTitle;
        this.items = items;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public int getItems() {
        return items;
    }
}
