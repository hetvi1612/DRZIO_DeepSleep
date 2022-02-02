package drzio.insomnia.treatment.bedtime.yoga.sleep;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore.modal.SubcatData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore.modal.TestDatalist;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.Badge;
import drzio.insomnia.treatment.bedtime.yoga.sleep.BlogActivity.BlogData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model.Selecteditems;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.ApiInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model.HistoryData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model.covid19.CovidTimeDetailsModel;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.Allexercises;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.Banner1;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.BannerData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.webservice.BackpainAPIClient;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Constants {
    public static String isPAuse="false";
    public static final String BMR = "bmr";
    public static final String USERID = "userid";
    public static final String PRESENTBLOGCOUNTSBLOG = "PRESENTBLOGCOUNTSBLOG";
    public static final String SKIP_NOINTERNET = "skipnointernet";
    public static final String DISCOUNT_TXT = "dicount_txt";
    public static final String DISCOUNT_TXT2 = "dicount_txt2";

    public static String TOTALBLOG_KEY = "totalblog_kay";

    public static String RATEDONE_KEY = "ratedonekey";
    public static String WEIGHT_KEY = "weight";
    public static String HEIGHT_KEY = "height";
    public static String AGE_KEY = "age";
    public static String MEALTYPE_KEY = "mealtype";
    public static String USERTYPEKEY = "usertype";
    public static String GENDER_KEY = "gender";
    public static String BMI_KEY = "BMI";
    public static String ISKG_KEY = "iskg";
    public static String ISCM_KEY = "iscm";
    public static String ISLOGIN_KEY = "islogin";
    public static String COUNTRYNAME_KEY = "country";
    public static String COUNTRYID_KEY = "country_id";
    public static String STATENAME_KEY = "state";
    public static String STATEID_KEY = "state_id";
    public static String CITYNAME_KEY = "city";
    public static String CITYID_KEY = "city_id";
    public static String COUNTRY_CODE = "countrycode_id1";
    public static String COUNTRY_CODEID = "countrycode_id";
    public static String USERID_KEY = "userid";
    public static String FCMTOKEN_KEY = "fcmtoken";
    public static String ISGUIDEON_KEY = "isguide";
    public static String ISTIPSON_KEY = "istips";
    public static String ISMUTEON_KEY = "ismute";
    public static String TOTALKCAL_KEY = "totalkcal";
    public static String TOTALEXE_KEY = "allexe";
    public static String TOTALTIME_KEY = "allprogress";
    public static String ALLDAYSPROGRESS_KEY = "alldayprogress";
    public static String ALLDAYSPROGRESS_LV2KEY = "lv2alldayprogress";
    public static String ALLDAYSPROGRESS_LV3KEY = "lv3alldayprogress";
    public static String DAYCLICK_KEY = "dayclick";
    public static String ALV2DAYCLICK_KEY = "alv2dayclick";
    public static String ALV3DAYCLICK_KEY = "alv3dayclick";

    public static String DAYSLEFT_KEY = "dayleft";
    public static String DAYSLEFT_LV2KEY = "daylv2left";
    public static String DAYSLEFT_LV3KEY = "daylv3left";

    public static String DIETPLANDATE_KEY = "adddate";
    public static String GRAPHDATE_KEY = "graph_date";
    public static String WEIGHTGRAPHDATE_KEY = "weight_graph_date";
    public static String PREMIUN_KEY = "ispurchesd";
    public static String ALEVEL_KEY = "alevel_key";
    public static String BLEVEL_KEY = "blevel_key";

    public static String REMOVEAD_KEY = "removead_key";

    public static String FIXDIET1_KEY = "fixdiet1_key";
    public static String FIXDIET2_KEY = "fixdiet2_key";
    public static String ADDEDFIXDIET = "addedfixdie";


    public static String AF1KCALLIST_KEY = "afragment1calorielist";
    public static String AF2KCALLIST_KEY = "afragment2calorielist";
    public static String AF3KCALLIST_KEY = "afragment3calorielist";

    public static String AF1TIMELIST_KEY = "afragment1timelist";
    public static String AF2TIMELIST_KEY = "afragment2timelist";
    public static String AF3TIMELIST_KEY = "afragment3timelist";

//    Below Age Level Keys

    public static String DAYCLICK_BLV1KEY = "dayclickblv1";
    public static String DAYCLICK_BLV2KEY = "dayclickblv2";
    public static String DAYCLICK_BLV3KEY = "dayclickblv3";

    public static String ALLDAYSPROGRESS_BLV1KEY = "alldayprogressblv1";
    public static String ALLDAYSPROGRESS_BLV2KEY = "alldayprogressblv2";
    public static String ALLDAYSPROGRESS_BLV3KEY = "alldayprogressblv3";

    public static String DAYSLEFT_BLV1KEY = "dayleftblv1";
    public static String DAYSLEFT_BLV2KEY = "dayleftblv2";
    public static String DAYSLEFT_BLV3KEY = "dayleftblv3";

    public static final String CHANNEL_ID = "backpain_channel";
    public static final String CHANNEL_NAME = "Backpain Notification";
    public static final String CHANNEL_DESCRIPTION = "simple notification channel";


    public static boolean isRatedialShown = false;

    public static String USEREMAIL_KEY = "useremail_kay";
    public static String USERFIRSTNAME_KEY = "userfirstname_kay";
    public static String USERLASTNAME_KEY = "userlastname_kay";


//    Apis Constants


    public static String BANNERAPI_KEY = "bannerapi";
    public static String BLOGAPI_KEY = "blogapi";
    public static String LIKEAPI_KEY = "likeapi";
    public static String REGISTERAPI_KEY = "registerapi";
    public static String LOGINAPI_KEY = "loginapi";
    public static String CITYAPI_KEY = "cityapi";
    public static String UPDATEPROFILEAPI_KEY = "profileapi";
    public static String INBANNERAPI_KEY = "inbannerapi";
    public static String DIETCATAPI_KEY = "dietcatapi";
    public static String DIETLISTAPI_KEY = "dietlistapi";


   /* public static final String SKU_DELAROY_MONTHLY = "backpain_1month";
    public static final String SKU_DELAROY_THREEMONTH = "delaroy_threemonth";
    public static final String SKU_DELAROY_SIXMONTH = "backpain_6month";
    public static final String SKU_DELAROY_YEARLY = "delaroy_yearly";*/

    public static final String SKU_ONETIME_PURCHASE = "height_increase_level_2_3";
    public static final String SKU_DELAROY_MONTHLY = "sku_monthly";
    public static final String SKU_DELAROY_THREEMONTH = "delaroy_threemonth";
    public static final String SKU_DELAROY_SIXMONTH = "sku_yearly";
    public static final String SKU_DELAROY_YEARLY = "delaroy_yearly";
    public static final String ITEM_SKU1 = "sku_life";

    public static String ONETIMEPURCHASE = "onetimepurchesd";


    public static String FIRSTBLOGADDED = "isblogadded";
    public static String PRESENTBLOGCOUNTS = "blogsizes";
    public static String NEWBLOGSCOUNTS = "newblogs";


    public static String TWODAYREMIDERDATE_KEY = "twodayreminder";
    public static String FIVEDAYREMIDERDATE_KEY = "fivedayreminder";
    public static String RATEDIALOGSHOW_KEY = "ratedialogekey";


    public static String LASTOPENHOUR = "lastopenhour";
    public static String LASTOPENMINUTE = "lastopenminute";

    public static String admob_banner = "";
    public static String admob_Interstitial = "";
    public static String admob_nativead = "";
    public static String admob_rewardad = "";

    public static String facebook_banner = "";
    public static String facebook_native = "";
    public static String facebooknative_banner = "";
    public static String facebook_rectangle = "";
    public static String facebook_interstitial = "";

    public static int drwaerselected = 0;

    public static ArrayList<String> subcatobjectlist = new ArrayList<>();
    public static ArrayList<SubcatData.Datalist> subcatModelArrayList = new ArrayList<>();

    public static ArrayList<Integer> mPoslist = new ArrayList<>();

    public static ArrayList<Allexercises> mUpdatesenddata = new ArrayList<>();

    public static String CPLANNAME = "";
    public static boolean isARewardgiven2;
    public static boolean isARewardgiven3;

    public static boolean isDRewardgiven4;
    public static boolean isDRewardgiven6;
    public static boolean isDRewardgiven7;
    public static boolean isDRewardgiven8;
    public static boolean isDRewardgiven9;

    public static String BLOGAD_KEY = "blogad_key";
    public static String TOKENSEND_KEY = "tokensend_kay";

    public static boolean isReportBanner = false;
    public static boolean isBlogBanner = false;
    public static boolean isReminderBanner = false;
    public static boolean isTokanAdd = false;
    public static boolean isDieatBanner = true;
    public static boolean isDieatBanner2 = true;
    public static boolean isDieatBanner3 = true;
    public static boolean isSettingBanner = true;


    public static ArrayList<BlogData.Datalist> blogdatalist = new ArrayList<>();

    public static String AppstoreUrl = "AppstoreApi";
    public static String CelluliteUrl = "BackPainApi";
    public static String YoutubeApi = "youtubeApi";
    public static String Appstore_Appid = "appstore_appid";
    public static String Storebanner_id = "storebanner_id";

    public static String GoogleInterstitial = "GoogleInterstitial";

    public static String Purchasedetails = "https://drzio.com/app-purchase-policy";

    public static String Indiagraphtype = "cases";
    public static String Globgraphtype = "cases";
    public static List<CovidTimeDetailsModel> TimeDetailsModelList = new ArrayList();
    public static List<HistoryData.Datalist> Globhistorydatalist = new ArrayList();
    public static ArrayList<BannerData.Datalist> homebannerdatalist = new ArrayList<>();
    public static ArrayList<TestDatalist> storedatalist = new ArrayList<>();
    public static ArrayList<String> catnamearray = new ArrayList<>();
    // public static int[] songids = new int[]{R.raw.yoga_ancient_calm_music, R.raw.workout_special,R.raw.yoga_peaceful_soul_music,R.raw.energetic_workout_sound};
    public static String[] songnames = new String[]{"Yoga Ancient Calm Music", "Workout Special", "Yoga Peaceful Soul Music", "Energetic Workout Sound"};
    public static String DISCOUNTDIALO_TIME = "discountdial";
    public static boolean isAdded = false;
    public static String ISMUSICON_KEY = "isMusic";

    public static String SONGID_KEY = "songid_key";
    public static boolean isFromnotification = true;

    public static String Hindi = "hi";
    public static String English = "en";
    public static String French = "fr";
    public static String Chinish = "zh";
    public static String Portugues = "pt";
    public static String Russian = "ru";
    public static String Aarbic = "ar";
    public static String Spanish = "es";
    public static final String BENGALI = "bn";
    public static final String THAI = "th";
    public static final String DANISH = "da";
    public static final String DUTCH = "nl";
    public static final String ENGLISH = "en";
    public static final String ESTONIAN = "et";
    public static final String FILIPINO = "tl";
    public static final String FINNISH = "fi";
    public static final String FRENCH = "fr";
    public static final String GALICIAN = "gl";
    public static final String GEORGIAN = "ka";
    public static final String GERMAN = "de";
    public static final String GREEK = "el";
    public static final String GUJARATI = "gu";
    public static final String HAITIAN_CREOLE = "ht";
    public static final String HEBREW = "iw";
    public static final String HINDI = "hi";
    public static final String HUNGARIAN = "hu";
    public static final String ICELANDIC = "is";
    public static final String INDONESIAN = "id";
    public static final String IRISH = "ga";
    public static final String ITALIAN = "it";
    public static final String JAPANESE = "ja";
    public static final String KANNADA = "kn";
    public static final String KOREAN = "ko";
    public static final String LATIN = "la";
    public static final String LATVIAN = "lv";
    public static final String LITHUANIAN = "lt";
    public static final String MACEDONIAN = "mk";
    public static final String MALAY = "ms";
    public static final String MALTESE = "mt";
    public static final String NORWEGIAN = "no";
    public static final String PERSIAN = "fa";
    public static final String POLISH = "pl";
    public static final String PORTUGUESE = "pt";
    public static final String ROMANIAN = "ro";
    public static final String RUSSIAN = "ru";
    public static final String SERBIAN = "sr";
    public static final String SLOVAK = "sk";
    public static final String SLOVENIAN = "sl";
    public static final String SPANISH = "es";
    public static final String SWAHILI = "sw";
    public static final String SWEDISH = "sv";
    public static final String TAMIL = "ta";
    public static final String TELUGU = "te";
    public static final String PUNJABI = "pa";

    public static final String TURKISH = "tr";
    public static final String UKRAINIAN = "uk";
    public static final String URDU = "ur";
    public static final String VIETNAMESE = "vi";
    public static final String WELSH = "cy";
    public static final String YIDDISH = "yi";
    public static final String CHINESE_SIMPLIFIED = "zh-CN";
    public static final String CHINESE_TRADITIONAL = "zh-TW";
    public static String GFITON_KEY = "googlefitkey";

    public static String Language = "Language";
    public static String AppLanguage = "AppLanguage";
    public static String Authtoken = "authtoken";
    public static String USERID_KEYs = "userid";
    public static ArrayList<Banner1.Doc> homebannerdatalist1 = new ArrayList<>();
    public static ArrayList<Selecteditems> mTemplist = new ArrayList<>();
    public static String NewBaseUrl = "NewWeightGainApi";
    public static String Backimageurl = "Backimageurl";
    public static String Genderchoose = "genderchoose";

    public static String ReadyDietPlan = "ReadyDietPlan";
    public static String admob_openad = "";
    public static String ReasonKey = "reason";
    public static String PurchaseDialog = "PurchaseDialog";
    public static String Month_Value = "month_value";
    public static String Year_Value = "year_value";
    public static String Life_Value = "life_value";
    public static String lifetime;
    public static String Success_Country="Success_Country";

    public static void languagechange(Context context, String coins) {
        //  String languageToLoad = "en"; // your language
        final Locale loc = new Locale(coins);
        Locale.setDefault(loc);
        final Configuration cfg = new Configuration();
        cfg.locale = loc;
        context.getResources().updateConfiguration(cfg, null);

       /* Resources activityRes = context.getResources();
        Configuration activityConf = activityRes.getConfiguration();
        Locale newLocale = new Locale(coins);
             activityConf.setLocale(newLocale);
        //activityConf.locale = newLocale;
        activityConf.setLayoutDirection(newLocale);
        activityRes.updateConfiguration(activityConf, activityRes.getDisplayMetrics());*/


    }


    public static void UpdatTime(Context context, String coins1, String time1, String totalexe1, String calaory1) {
        try {
      /*  try {
            TinyDB tinyDB = new TinyDB(context);
            Log.e("timetime",time);
            CelluliteAPIInterface apiInterface = (CelluliteAPIInterface) Objects.requireNonNull(CelluliteAPIClient.getLocalClient()).create(CelluliteAPIInterface.class);
            String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("device_id", androidId)
                    .addFormDataPart("coin", coins)
                    .addFormDataPart("time", time)

                    .build();
            apiInterface.UpdateTime(requestBody).enqueue(new Callback<Updatetime>() {
                @Override
                public void onResponse(@NotNull Call<Updatetime> call, @NotNull Response<Updatetime> response) {
                    Updatetime bannerData = (Updatetime) response.body();
                    Log.e("responseresponse", String.valueOf(response));
                    Log.e("responseresponse", String.valueOf(response));
                }

                @Override
                public void onFailure(@NotNull Call<Updatetime> call, @NotNull Throwable t) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }*/
            TinyDB tinyDB = new TinyDB(context);
            long totalprogress = tinyDB.getLong(Constants.TOTALTIME_KEY, 0);
            //tinyDB.putLong(Constants.TOTALTIME_KEY, Long.parseLong(time));
            // Log.e("exexexexetimetime", time);
            //   Log.e("totalprogresstotalprogress", String.valueOf(totalprogress));
            String totalexe = String.valueOf(tinyDB.getInt(Constants.TOTALEXE_KEY));
            String calaory = String.valueOf(round(tinyDB.getFloat(Constants.TOTALKCAL_KEY), 1));
            String token = tinyDB.getString(Authtoken);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(loggingInterceptor);

            OkHttpClient client = httpClient.build();
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            String BaseUrl23 = tinyDB.getString(Constants.NewBaseUrl);
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseUrl23).addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
            // ApiInterface   apiInterface = retrofit.create(ApiInterface.class);
            ApiInterface apiInterface = (ApiInterface) BackpainAPIClient.getCelluliteClient1().create(ApiInterface.class);
            //  apiInterface = (ApiInterface) CelluliteAPIClient.getCelluliteClient1().create(ApiInterface.class);
            try {
                JSONObject paramObject = new JSONObject();
                paramObject.put("time", totalprogress);
                paramObject.put("exercise", totalexe);
                paramObject.put("cal", calaory);
                //  paramObject.put("country_name", "INR");


                Call<Badge> userCall = apiInterface.getbadge(paramObject.toString(), "Bearer " + token);
                userCall.enqueue(new Callback<Badge>() {
                    @Override
                    public void onResponse(Call<Badge> call, Response<Badge> response) {
                        //      Log.e("response", String.valueOf(response.body()));

                        try {
                            Badge badge = response.body();
                            Boolean bg = badge.getStatus();
                            //       Log.e("getBadge", String.valueOf(bg));

                        } catch (Exception w) {

                        }
                   /* List<Badge> loginData=response.body();
                    String[] heroes = new String[loginData.size()];

                    for (int i = 0; i < loginData.size(); i++) {
                        heroes[i] = loginData.get(i).getCountry_name();
                        Log.e("id", String.valueOf(loginData.get(i).getId()));
                        Log.e("getCountryName", String.valueOf(loginData.get(i).getCountry_name()));
                        Log.e("getMonthPrice", String.valueOf(loginData.get(i).getMonthPrice()));
                        Log.e("getSixMPrice", String.valueOf(loginData.get(i).getSixMPrice()));
                        Log.e("getLifeTPrice", String.valueOf(loginData.get(i).getLifeTPrice()));

                        Log.e("getLifeTPrice", String.valueOf(loginData.get(i).getLifeTPrice()));
                    }*/
                        //   String id =loginData.get(0).getId();

                        Log.e("w5", String.valueOf(response.body()));
                        //List<Pricedata>. datalists = loginData.result;
                        //  Log.e("id", String.valueOf(List<Pricedata>.id));
                  /*  Log.e("w25", String.valueOf(loginData.w25));
                    Log.e("w5", String.valueOf(loginData.w5));
                    Log.e("w1", String.valueOf(loginData.w1));*/
                    }

                    @Override
                    public void onFailure(Call<Badge> call, Throwable t) {
                        //     Log.e("response1", String.valueOf("response"));
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {

        }
    }


    public static float round(float d, int decimalPlace) {
        return BigDecimal.valueOf(d).setScale(decimalPlace, BigDecimal.ROUND_HALF_UP).floatValue();
    }


    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


}