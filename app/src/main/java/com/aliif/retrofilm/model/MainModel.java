package com.aliif.retrofilm.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainModel {

    @SerializedName("result")
    private List<Result> result;

    public List<Result> getResults() {
        return result;
    }

    public void setResults(List<Result> result) {
        this.result = result;
    }

    public class Result {

        @SerializedName("id")
        private int id;

        @SerializedName("title")
        private String title;

        @SerializedName("image")
        private String image;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        @NonNull
        @Override
        public String toString() {
            return "Result{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", image='" + image + '\'' +
                    '}';
        }
    }
}
