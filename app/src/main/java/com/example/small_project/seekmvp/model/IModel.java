package com.example.small_project.seekmvp.model;

public interface IModel {
    void gethome(String url,String keyword, CteanView cteanView);
    interface CteanView {
        void onSuccess(Object obj);
        void onFailure();
    }
}
