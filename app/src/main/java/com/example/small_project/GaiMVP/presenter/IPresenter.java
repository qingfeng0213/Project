package com.example.small_project.GaiMVP.presenter;

import java.util.HashMap;

public interface IPresenter {
    void getname(String newname, HashMap<String, Object> hashMap);
    void getword(String oldpwd,String newpwd, HashMap<String, Object> hashMap);
}
