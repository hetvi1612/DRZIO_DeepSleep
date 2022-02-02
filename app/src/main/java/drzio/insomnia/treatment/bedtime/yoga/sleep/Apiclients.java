package drzio.insomnia.treatment.bedtime.yoga.sleep;


import java.util.ArrayList;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model.Selecteditems;

public class Apiclients {

    public static String GIF_CATEGORIES = "https://parinfotech.com/backpain/index.php/api/index/getCategory";
    public static String GIF_IMAGES = "https://parinfotech.com/backpain/index.php/api/index/getdietpaln";
    public static String BANNER_API = "https://parinfotech.com/backpain/index.php/api/index/getbanner";
    public static String BLOG_API = "https://parinfotech.com/backpain/index.php/api/index/getblog";
    public static String LIKE_API = "https://parinfotech.com/backpain/index.php/api/index/bloglike";
    public static String USERREGISTER_API = "https://parinfotech.com/backpain/index.php/api/index/userregister";
    public static String LOGIN_API = "https://parinfotech.com/backpain/index.php/api/index/sociallogin";
    public static String CITY_API = "https://parinfotech.com/backpain/index.php/api/index/getcitylist";
    public static String UPDATE_PROFILE = "https://parinfotech.com/backpain/index.php/api/index/updateprofile";
    public static String INAPPBANNER_API = "https://parinfotech.com/backpain/index.php/api/index/getinapp";
    public static String USERFEEDBACK_API = "https://parinfotech.com/backpain/index.php/api/index/rating";
    public static String STOREBANNER_API = "https://parinfotech.com/appstore/index.php/api/index/getbanner";


    public static String MAINCATEGORY_API = "https://parinfotech.com/appstore/index.php/api/index/getCategory";
    public static String ALLAPSS_API = "https://parinfotech.com/appstore/index.php/api/index/getapp";
    public static String SUBCATEGORY_API = "https://parinfotech.com/appstore/index.php/api/index/getSubcsategory";

    public static String TOKEN_API = "https://parinfotech.com/backpain/index.php/api/index/usertoken";

    public static TinyDB tinyDB = new TinyDB(FitnessApplication.getInstance());



    public static ArrayList<Selecteditems> mTemplist = new ArrayList<>();

    public static String WEIGHT_KEY = "weight";


}
