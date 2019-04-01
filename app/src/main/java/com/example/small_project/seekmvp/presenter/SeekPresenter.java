package com.example.small_project.seekmvp.presenter;

import com.example.small_project.Api.ApiService;
import com.example.small_project.SeekActivity;
import com.example.small_project.bean.SouBean;
import com.example.small_project.seekmvp.model.IModel;
import com.example.small_project.seekmvp.model.SeekModel;

public class SeekPresenter implements IPresenter {
    SeekActivity seekActivity;
    private final SeekModel seekModel;

    public SeekPresenter(SeekActivity seekActivity) {
        this.seekActivity = seekActivity;
        seekModel = new SeekModel();
    }

    @Override
    public void getseekdata(String keyword) {
        seekModel.gethome(ApiService.SEEK_URL, keyword, new IModel.CteanView() {
            @Override
            public void onSuccess(Object obj) {
                SouBean souBean = (SouBean) obj;
                if (souBean.getStatus().equals("0000")) {
                    seekActivity.getPreData(souBean);
                }else {
                    seekActivity.getjump();
                }

            }

            @Override
            public void onFailure() {

            }
        });
    }
}
