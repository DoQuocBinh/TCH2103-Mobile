package com.example.androidreview;

import androidx.annotation.NonNull;

public class Exam {
    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    protected String name;
    protected String description;
    protected String examDate;

    @NonNull
    @Override
    public String toString() {
        return name +  "-" + examDate;
    }
}
