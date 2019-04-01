package com.example.small_project.Api;

import com.example.small_project.adapter.NewAddressBean;
import com.example.small_project.bean.AddRessBean;
import com.example.small_project.bean.AllOrderBean;
import com.example.small_project.bean.BannerBean;
import com.example.small_project.bean.CircleBean;
import com.example.small_project.bean.CreateBean;
import com.example.small_project.bean.DetailsBean;
import com.example.small_project.bean.FIndShopBean;
import com.example.small_project.bean.GoodsLvOneBean;
import com.example.small_project.bean.GoodsLvThreeBean;
import com.example.small_project.bean.GoodsLvTwoBean;
import com.example.small_project.bean.LoginBean;
import com.example.small_project.bean.MoneyBean;
import com.example.small_project.bean.MyBean;
import com.example.small_project.bean.MyMessageBean;
import com.example.small_project.bean.NameBean;
import com.example.small_project.bean.PayBean;
import com.example.small_project.bean.RegBean;
import com.example.small_project.bean.SouBean;
import com.example.small_project.bean.SyncShopBean;
import com.example.small_project.bean.WordBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface  UserApiService {
    //首页
    @GET(ApiService.HOME_URL)
    Observable<MyBean> gethomedata();
    //首页的轮播图
    @GET
    Observable<BannerBean> getBannerData(@Url String url);
    //详情
    @GET
    Observable<DetailsBean> getDetails(@Url String url);
    //搜索
    @GET
    Observable<SouBean> getSeek(@Url String url, @Query("keyword")String keyword);
    //圈子
    @GET
    Observable<CircleBean> getCircle(@Url String url);
    //登陆
    @POST
    Observable<LoginBean> getLoginData(@Url String url, @Query("phone") String number,@Query("pwd") String password);
    //注册
    @POST
    Observable<RegBean> getRegData(@Url String url, @Query("phone") String number, @Query("pwd") String password);
    //同步购物车
    @PUT
    Observable<SyncShopBean>  getSyncShop(@Url String url, @Query("data") String data,@HeaderMap HashMap<String,Object > hashMap);
    //查询购物车
    @GET
    Observable<FIndShopBean>  getFindShop(@Url String url,@HeaderMap HashMap<String,Object> hashMap);
    //收货列表
    @GET
    Observable<NewAddressBean> getAddress(@Url String url,@HeaderMap HashMap<String,Object> hashMap);
    //新增收货列表
    @POST
    Observable<AddRessBean> getAddSite(@Url String url, @QueryMap HashMap<String ,String> bodymap, @HeaderMap HashMap<String,Object> hashMap);
    //创建订单
    @POST
    @FormUrlEncoded
    Observable<CreateBean>  getCreate(@Url String url,@Query("orderInfo") String orderInfo,@Field ("totalPrice") double totalPrice,@Field("addressId") int addressId,@HeaderMap HashMap<String,Object> hashMap);
    //修改昵称
    @PUT
    Observable<NameBean> getnewname(@Url String url,@Query("nickName") String newname,@HeaderMap HashMap<String,Object> hashMap);
    //修改密码
    @PUT
    Observable<WordBean> getpassword(@Url String url,@Query("oldPwd") String oldpwd,@Query("newPwd") String newpwd,@HeaderMap HashMap<String,Object > hashMap);
    //查询钱余额
    @GET
    Observable<MoneyBean> getMoney(@Url String url,@HeaderMap HashMap<String,Object> hashMap);
    //根据订单状态查询订单
    @GET
    Observable<AllOrderBean> getAllOrder(@Url String url,@Query("status") int status,@HeaderMap HashMap<String,Object> hashMap);
    //查询用户信息
    @GET
    Observable<MyMessageBean> getMessage(@Url String url,@HeaderMap HashMap<String,Object> hashMap);
    //支付
    @POST
    @FormUrlEncoded
    Observable<PayBean>  getPayData(@Url String url,@Field("orderId") String orderId,@Field("payType") int payType, @HeaderMap HashMap<String,Object> hashMap);
    //删除订单


    //查询一级商品类目
    @GET(ApiService.SELECT_LV_ONE)
    Observable<GoodsLvOneBean> getLvOneData();
    //查询二级商品类目
    @GET(ApiService.SELECT_LV_TWO)
    Observable<GoodsLvTwoBean> getLvTwoData(@Query("firstCategoryId")String firstCategoryId);
    //查询三级商品类目
    @GET(ApiService.SELECT_LV_THREE)
    Observable<GoodsLvThreeBean> getLvThreeData(@Query("categoryId")String categoryId, @Query("page")int page);

}
