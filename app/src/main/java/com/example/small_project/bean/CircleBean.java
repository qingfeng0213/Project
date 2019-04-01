package com.example.small_project.bean;

import java.util.List;

public class CircleBean {

    /**
     * result : [{"commodityId":1,"content":"哈哈哈","createTime":1547572996000,"greatNum":3,"headPic":"http://172.17.8.100/images/small/head_pic/2019-01-09/20190109114509.png","id":468,"image":"http://172.17.8.100/images/small/circle_pic/2019-01-15/2410720190115112316.jpg","nickName":"上善若水","userId":571,"whetherGreat":2},{"commodityId":1,"content":"天天开心","createTime":1547572877000,"greatNum":5,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":467,"image":"","nickName":"WJ_hw0z3","userId":980,"whetherGreat":2},{"commodityId":1,"content":"DataBean就是任性","createTime":1547572281000,"greatNum":20,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":466,"image":"http://172.17.8.100/images/small/circle_pic/2019-01-15/9082720190115111121.png","nickName":"lO_4O24o","userId":604,"whetherGreat":2},{"commodityId":1,"content":"美美哒","createTime":1547568830000,"greatNum":43,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":465,"image":"http://172.17.8.100/images/small/circle_pic/2019-01-15/0784220190115101350.jpg,http://172.17.8.100/images/small/circle_pic/2019-01-15/8973520190115101350.jpg","nickName":"DY_3994m","userId":827,"whetherGreat":2}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commodityId : 1
         * content : 哈哈哈
         * createTime : 1547572996000
         * greatNum : 3
         * headPic : http://172.17.8.100/images/small/head_pic/2019-01-09/20190109114509.png
         * id : 468
         * image : http://172.17.8.100/images/small/circle_pic/2019-01-15/2410720190115112316.jpg
         * nickName : 上善若水
         * userId : 571
         * whetherGreat : 2
         */

        private int commodityId;
        private String content;
        private long createTime;
        private int greatNum;
        private String headPic;
        private int id;
        private String image;
        private String nickName;
        private int userId;
        private int whetherGreat;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getGreatNum() {
            return greatNum;
        }

        public void setGreatNum(int greatNum) {
            this.greatNum = greatNum;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getWhetherGreat() {
            return whetherGreat;
        }

        public void setWhetherGreat(int whetherGreat) {
            this.whetherGreat = whetherGreat;
        }
    }
}
