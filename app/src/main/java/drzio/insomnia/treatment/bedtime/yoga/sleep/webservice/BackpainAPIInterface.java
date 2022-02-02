package drzio.insomnia.treatment.bedtime.yoga.sleep.webservice;


import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore.modal.Appdata;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore.modal.CategoryData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore.modal.SubcatData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore.modal.TestDatamodel;
import drzio.insomnia.treatment.bedtime.yoga.sleep.BlogActivity.BlogData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.BlogActivity.model.AddLike;
import drzio.insomnia.treatment.bedtime.yoga.sleep.BlogActivity.model.BlogList;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Diet.AddFood;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Diet.Customplancat;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model.DietCateData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model.DietitemsData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.Register_Api;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.Userupdate_Api;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.BannerData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.CityData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.InappBannerModal;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.LoginData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.RegisterData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.StoreBannerData;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BackpainAPIInterface {

    //NEW_API DATAS

    String URL_BASE = "http://65.1.209.22:7070/";




    @Headers("Content-Type: application/json")
    @POST("user")
    Call<Register_Api> getUser(@Body String toString);




    @Headers("Content-Type: application/json")
    @POST("user/update")
    Call<Userupdate_Api> getUserupdate(@Body String toString, @Header("Authorization") String auth);

    @POST("getdietpaln")
    Call<DietitemsData> getDietplan(@Body RequestBody body);

    @POST("getCategory")
    Call<DietCateData> getCategory();

    @POST("getbanner")
    Call<BannerData> getBanners(@Body RequestBody body);

    @POST("getblog")
    Call<BlogData> getBlogs(@Body RequestBody body);

    @POST("bloglike")
    Call<String> sendBloglike(@Body RequestBody body);

    @POST("userregister")
    Call<RegisterData> sendUser(@Body RequestBody body);

    @POST("sociallogin")
    Call<LoginData> getLogin(@Body RequestBody body);

    @POST("getcitylist")
    Call<CityData> getCity(@Body RequestBody body);

    @POST("updateprofile")
    Call<String> updateProfile(@Body RequestBody body);

    @POST("getinapp")
    Call<InappBannerModal> getInAppsplesh(@Body RequestBody body);

    @POST("rating")
    Call<String> sendRetings(@Body RequestBody body);

    @POST("usertoken")
    Call<String> sendToken(@Body RequestBody body);

    @POST("testtoken")
    Call<String> sendTetToken(@Body RequestBody body);


    @POST("getCategory")
    Call<CategoryData> getAppCategory();

//    @POST("getbanner")
//    Call<StoreBannerData> getStorebanner(@Body RequestBody body);

    @POST("gettestbanner")
    Call<StoreBannerData> getStorebanner(@Body RequestBody body);

    @POST("getSubcsategory")
    Call<SubcatData> getSubcat(@Body RequestBody body);

    @POST("getapp")
    Call<Appdata> getAppdata(@Body RequestBody body);

    @POST("gettestapp")
    Call<TestDatamodel> getStoredata(@Body RequestBody body);

    @Headers("Content-Type: application/json")
    @POST("blog/displayBlog")
    Call<List<BlogList>> getBlog1(@Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @POST("blog/displayBlog")
    Call<List<BlogList>> getBlogs1(@Query("page")Integer page, @Query("limit")Integer limit, @Header("Authorization") String auth);
    @Headers("Content-Type: application/json")
    @POST("blog/like/{blogid}")
    Call<AddLike> getLike(@Path("blogid") String blogid, @Body String body, @Header("Authorization") String auth);


    @Headers("Content-Type: application/json")
    @POST("diet/addNewFood")
    Call<AddFood> getaddfood(@Body String body);

    @Headers("Content-Type: application/json")
    @POST("dietPlan/autocomplete")
    Call<List<customplan>> getcustomplan(@Body String body);

    @GET("dietPlan/catFood/{catagoryid}")
    Call<List<Customplancat>> getcustomplancatagory(@Path("catagoryid") String catagoryid);
}