package com.example.small_project.Api;

public class ApiService {
    //网址
    public static final String  BASE_URL="http://172.17.8.100/";
    //首页网址
    public static final String  HOME_URL="small/commodity/v1/commodityList";
    //圈子网址
    public static final String  CICLE_URL="small/circle/v1/findCircleList";
    //详情网址
    public static final String  DETAILS_URL="small/commodity/v1/findCommodityDetailsById?commodityId=";
    //登陆网址
    public static final String  LOGIN_URL="small/user/v1/login";
    //注册网址
    public static final String  REG_URL="small/user/v1/register";
    //轮播图网址
    public static final String  BANNER_URL="small/commodity/v1/bannerShow";
    //搜索
    public static final String  SEEK_URL="small/commodity/v1/findCommodityByKeyword?page=1&count=10";
    //同步购物车
    public static final String SYNCSHOP_URL="small/order/verify/v1/syncShoppingCart";
    //查询购物车
    public static final String SELECTCAT_URL="small/order/verify/v1/findShoppingCart";
    //收货列表
    public static final String ADDRESS_URL="small/user/verify/v1/receiveAddressList";
    //添加收货地址
    public static final String ADDSITE_URL="small/user/verify/v1/addReceiveAddress";
    //我的足迹
    public static final String FOOT_URL="small/commodity/verify/v1/browseList?page=1&count=5";
    //创建订单
    public static final String CREATE_URL="small/order/verify/v1/createOrder";
    //修改昵称
    public static final String NAME_URL="small/order/verify/v1/createOrder";
    //修改密码
    public static final String WORD_URL="small/user/verify/v1/modifyUserPwd";
    //查询钱包余额
    public static final String MONEY_URL="small/user/verify/v1/findUserWallet?page=1&count=1";
    //查询所有订单
    public static final String ORDER_URL="small/order/verify/v1/findOrderListByStatus?page=1&count=10";
    //查询用户信息
    public static final String MY_URL="small/user/verify/v1/getUserById";
    //支付
    public static final String PAY_URL="small/order/verify/v1/pay";
    //删除订单
    public static final String DELETE_URL="small/order/verify/v1/deleteOrder";
    //查询一级商品类目
    //http://172.17.8.100/
    public static final String SELECT_LV_ONE = "small/commodity/v1/findFirstCategory";
    //查询二级商品类目
    public static final String SELECT_LV_TWO = "small/commodity/v1/findSecondCategory";
    //查询三级商品类目
    //http://172.17.8.100/
    public static final String SELECT_LV_THREE = "small/commodity/v1/findCommodityByCategory?count=10";

}
