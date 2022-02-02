package drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model;


import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.AppUpdateApi;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.GetcountBulb;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.AddLastTime;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.AppstoreList;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.Appstore_Addclick;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.Appstore_Downloadclick;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.Appstore_new;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.Badge;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.DiscountPrice;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.ExituserData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.Purchasenotification;
import drzio.insomnia.treatment.bedtime.yoga.sleep.BlogActivity.model.BlogList;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.Banner1;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.InappBannerModal1;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    String URL_BASE = "http://65.1.209.22:7070/";


    @Headers("Content-Type: application/json")
    @POST("banner/getBanner")
    Call<List<InappBannerModal1>> getinapp(@Body String body, @Header("Authorization") String auth);
    @Headers("Content-Type: application/json")
    @POST("blog/displayBlog")
    Call<List<BlogList>> getBlogs1(@Query("page")Integer page, @Query("limit")Integer limit, @Header("Authorization") String auth);


    @Headers("Content-Type: application/json")
    @POST("user/updateLocation")
    Call<UpdateLocation> updateLocation(@Body String body, @Header("Authorization") String auth);
/*
    @Headers("Content-Type: application/json")
    @POST("user")
    Call<Usertoken> getUser(@Body String body, @Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @POST("price/priceapi")
    Call<List<Pricedata>> getprice(@Body String body);




    @Headers("Content-Type: application/json")
    @POST("purchase/priceCheck")
    Call<DiscountPrice> getdiscountprice(@Body String body, @Header("Authorization") String auth);



    @Headers("Content-Type: application/json")
    @POST("user/coin")
    Call<Coinupdate> getcoin(@Body String body, @Header("Authorization") String auth);



    @Headers("Content-Type: application/json")
    @GET("user/coin")
    Call<CoinGet> getcoin1(@Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @POST("user/badge")
    Call<Badge> getbadge(@Body String body, @Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @POST("user/getBadge")
    Call<Badge> getBadge( @Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @POST("user/leaderBoard")
    Call<leaderbord1> get7dayleaderbord(@Header("Authorization") String auth);


    @Headers("Content-Type: application/json")
    @GET("user/lBDay")
    Call<alldayleaderbord> getleaderbord(@Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @POST("user/verify_code")
    Call<Referral_codes> getReferral_code(@Body String body,@Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @POST("rating")
    Call<RateModel> getrating(@Body String body);

    @Headers("Content-Type: application/json")
    @POST("banner/getBanner")
    Call<List<Banner>> getbanner(@Body String body);

    @Headers("Content-Type: application/json")
    @POST("banner/getBanner")
    Call<List<InappBannerModal>> getinapp(@Body String body);

    @Headers("Content-Type: application/json")
    @POST("purchase/addPurchase")
    Call<PurchaseDone> getpurchasedone(@Body String body,@Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @POST("banner/add_click")
    Call<bannerClick> getbannerClick(@Body String body);


    @Headers("Content-Type: application/json")
    @POST("user/isExist")
    Call<ExituserData> getisExit(@Body String body);


    @Headers("Content-Type: application/json")
    @POST("user/isExist")
    Call<ExituserData> getisExit1(@Header("Authorization") String auth);*/

    @Headers("Content-Type: application/json")
    @GET("appList/getApp/{catagoryid}")
    Call<AppstoreList> getAppstorelist(@Path("catagoryid") int catagoryid, @Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @GET("appList/getPart/{catagoryid}")
    Call<List<Appstore_new>> getAppstore(@Path("catagoryid") String catagoryid, @Header("Authorization") String auth);
    @Headers("Content-Type: application/json")
    @POST("banner/add_click")
    Call<bannerClick> getbannerClick(@Body String body);

    @Headers("Content-Type: application/json")
    @POST("user/updateLocation")
    Call<UpdateLocation>updatelocation(@Body String toString, @Header("Authorization") String auth);

  /*  @Headers("Content-Type: application/json")
    @POST("dietPlan/defaultPlan1")
    Call<DietData> getdefalutdiet(@Body String body, @Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @POST("dietPlan/defaultPlan2")
    Call<DietData2> getdefalutdiet2(@Body String body, @Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @POST("dietPlan/autocomplete")
    Call<List<customplan>> getcustomplan(@Body String body);

    @GET("dietPlan/catFood/{catagoryid}")
    Call<List<Customplancat>> getcustomplancatagory(@Path("catagoryid") String catagoryid);

    @Headers("Content-Type: application/json")
    @POST("diet/addNewFood")
    Call<AddFood> getaddfood(@Body String body);
*/
    @Headers("Content-Type: application/json")
    @POST("appList/addClick")
    Call<Appstore_Addclick> addappstoreclick(@Body String toString, @Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @POST("appList/addDownload")
    Call<Appstore_Downloadclick> adddowappstoreclick(@Body String toString, @Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @POST("rating")
    Call<RateModel> getrating(@Body String body);

    @Headers("Content-Type: application/json")
    @POST("user/changeLang")
    Call<LanguageApi> getaddLanguage(@Body String toString, @Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @POST("banner/getBanner")
    Call<List<Banner1>> getbanner(@Body String body, @Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @POST("price/priceapi")
    Call<Pricedata> getprice(@Body String body, @Header("Authorization") String auth);


    @Headers("Content-Type: application/json")
    @POST("price/priceApi")
    Call<Pricedata> getprice_new(@Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @GET("blog/getCount")
    Call<GetcountBulb> getCountBlog(@Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @POST("appList/getStatus")
    Call<AppUpdateApi> appupdateapi(@Body String toString, @Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @POST("user")
    Call<Register_Api> getUser(@Body String toString);

    @Headers("Content-Type: application/json")
    @POST("/user")
    Call<Register_Api> getUser1(@Body String toString);

    @Headers("Content-Type: application/json")
    @POST("banner/getBMR")
    Call<Bmrdata> getBmrdata(@Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @POST("purchase/purchaseNoti")
    Call<Purchasenotification> addPurchasenotification(@Body String toString, @Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @POST("purchase/priceCheck")
    Call<DiscountPrice> getdiscountprice(@Body String body, @Header("Authorization") String auth);


    @Headers("Content-Type: application/json")
    @POST("user/addLastTime")
    Call<AddLastTime> addlasttime(@Body String toString, @Header("Authorization") String auth);




    @Headers("Content-Type: application/json")
    @POST("user/isExist")
    Call<ExituserData> getisExit(@Body String body);


    @Headers("Content-Type: application/json")
    @POST("user/isExist")
    Call<ExituserData> getisExit1(@Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @POST("user/addTime")
    Call<Badge> getbadge(@Body String body, @Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @POST("blog/addComment/{blogid}")
    Call<AddComment> addComment(@Path("blogid") String blogid, @Body String body, @Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @POST("blog/getComment/{blogid}")
    Call<GetComment> getComment(@Path("blogid") String blogid, @Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @POST("purchase/addPurchase")
    Call<PurchaseDone> getpurchasedone(String toString, String s);


  /*  @Headers("Content-Type: application/json")
    @POST("user/addLastTime")
    Call<AddLastTime> addlasttime(@Body String toString, @Header("Authorization") String auth);


    @Headers("Content-Type: application/json")
    @POST("ad/getStatus")
    Call<AdsStatus> getAdstatus(@Body String toString);

    @Headers("Content-Type: application/json")
    @POST("ad/displayAds/{catagoryid}")
    Call<AdsDisplayLink> getDisPlayAds(@Body String toString,@Path("catagoryid") String catagoryid);

    @Headers("Content-Type: application/json")
    @POST("bannerPlan/addClick")
    Call<focusbannerClick> getfocusbannerClick(@Body String body);

    @Headers("Content-Type: application/json")
    @POST("ad/adClick")
    Call<AdsClick> getAdsrClick(@Body String body);

    @Headers("Content-Type: application/json")
    @POST("user/changeLang")
    Call<LanguageApi> getaddLanguage(@Body String toString, @Header("Authorization") String auth);


    @Headers("Content-Type: application/json")
    @POST("purchase/purchaseNoti")
    Call<Purchasenotification> addPurchasenotification(@Body String toString, @Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @POST("user/updateLocation")
    Call<UpdateLocation>updatelocation(@Body String toString, @Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @GET("music/displayMusic")
    Call<List<MusicApi>> getMusic();

    *//*  @Headers("Content-Type: application/json")
      @POST("user/login")
      Call<Logindata> getlogin(@Body String body);

      @Headers("Content-Type: application/json")
      @POST("price/priceapi")
      Call<List<Pricedata>> getprice(@Body String body);






      @Headers("Content-Type: application/json")
      @POST("dietPlan/defaultPlan1")
      Call<DietData> getdefalutdiet(@Body String body);


      @Headers("Content-Type: application/json")
      @POST("dietPlan/defaultPlan2")
      Call<DietData2> getdefalutdiet2(@Body String body);




      @Headers("Content-Type: application/json")
      @POST("rating")
      Call<RateModel> getrating(@Body String body);

      @Headers("Content-Type: application/json")
      @POST("user/lBDay")
      Call<List<leaderbord>> getleaderbord();



      @Headers("Content-Type: application/json")
      @POST("user/leaderBoard")
      Call<leaderbord1> get7dayleaderbord();



      @Headers("Content-Type: application/json")
      @POST("user/badge")
      Call<Badge> getbadge(@Body String body);



      @Headers("Content-Type: application/json")
      @POST("user/coin")
      Call<Coinupdate> getcoin(@Body String body);



      @Headers("Content-Type: application/json")
      @POST("banner/getBanner")
      Call<List<Banner> > getbanner(@Body String body);



      @Headers("Content-Type: application/json")
          @POST("dietPlan/autocomplete")
      Call<List<customplan>> getcustomplan(@Body String body);



      @Headers("Content-Type: application/json")
      @GET("appList/getApp")
      Call<Appstore> getAppstore();





      @GET("dietPlan/catFood/{catagoryid}")
      Call<List<Customplancat>> getcustomplancatagory(@Path("catagoryid") String catagoryid);





      @Headers("Content-Type: application/json")
      @POST("diet/addNewFood")
      Call<AddFood> getaddfood(@Body String body);



      @Headers("Content-Type: application/json")
      @POST("/blog/like/{blogid}")
      Call<Bloglist> getLike(@Path("blogid") String blogid, @Body String body);*//*
    @Headers("Content-Type: application/json")
    @POST("blog/displayBlog")
    Call<Bloglist> getBlogs(@Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @POST("blog/addComment/{blogid}")
    Call<Bloglist> addComment(@Path("blogid") String blogid, @Body String body, @Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @POST("blog/getComment/{blogid}")
    Call<GetComment> getComment(@Path("blogid") String blogid, @Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @POST("blog/addFollow")
    Call<List<AddFollowData>> addFollow(@Body String body, @Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @POST("blog/like/{blogid}")
    Call<AddLike> getLike(@Path("blogid") String blogid, @Body String body, @Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @POST("blog/share/{blogid}")
    Call<Bloglist> getShare(@Path("blogid") String blogid, @Body String body, @Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @POST("blog/searchUCat")
    Call<List<SerachFollowing>> getSerachFollowing(@Body String body, @Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @POST("blog/searchBCat")
    Call<List<SerachFollowing>> getSerachAll(@Body String body, @Header("Authorization") String auth);

    @Headers({"Content-Type: application/json"})
    @GET("blog/fCategory")
    Call<List<FollowingCategory>> getFollowing(@Header("Authorization") String auth);

    @Headers({"Content-Type: application/json"})
    @POST("blog/catBlogList/{catagoryname}")
    Call<List<Catagorylist>> getCatagoryBlogs(@Path("catagoryname") String catagoryname, @Header("Authorization") String auth);



    @Headers("Content-Type: application/json")
    @GET("blog/getCount")
    Call<GetcountBulb> getCountBlog(@Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @POST("bannerPlan/getPlan")
    Call<List<FocusBanner> >getfocusbanner(@Body String toString);




    @Headers("Content-Type: application/json")
    @POST("blog/time")
    Call<AddtimeData> addTime(@Body String body, @Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @POST("clickEvent/focusPlan")
    Call<PLANClickEvent> addfocusplanClick(@Body String body, @Header("Authorization") String auth);
    @Headers("Content-Type: application/json")
    @POST("clickEvent/earnSection")
    Call<PLANClickEvent> addEarnClick(@Body String body, @Header("Authorization") String auth);
    @Headers("Content-Type: application/json")
    @POST("clickEvent/homePlan")
    Call<PLANClickEvent> addmainplanClick(@Body String body, @Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @POST("clickEvent/report")
    Call<PLANClickEvent> addReportClick(@Body String body, @Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @POST("clickEvent/shareApp")
    Call<PLANClickEvent> addshareClick(@Body String body, @Header("Authorization") String auth);


    @Headers("Content-Type: application/json")
    @POST("clickEvent/inviteEarn")
    Call<PLANClickEvent> addinvitefrienfClick(@Body String body, @Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @POST("clickEvent/nutritionPlan")
    Call<PLANClickEvent> addnutrationClick(@Body String body, @Header("Authorization") String auth);


    @Headers("Content-Type: application/json")
    @POST("clickEvent/blogSection")
    Call<PLANClickEvent> addBlogClick(@Body String body, @Header("Authorization") String auth);
*/

}