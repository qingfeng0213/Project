package com.example.small_project.Cicle.model;

public interface ICircleModelList {
    //获取数据
    public void getCircleDate(String url,Cteanview cteanview );

    interface  Cteanview{
        void Succes(Object data);
        void Fuils();

    }
}
