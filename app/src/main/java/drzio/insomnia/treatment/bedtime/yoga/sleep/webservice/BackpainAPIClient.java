package drzio.insomnia.treatment.bedtime.yoga.sleep.webservice;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class BackpainAPIClient {
    public static Context context;
    private static Retrofit retrofit;
    private static TinyDB tinyDB;
    public static String BaseUrl;
    public static String BaseUrl2;
    static String BASEURL;

    public static Retrofit getCelluliteClient() {
        try {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            tinyDB = new TinyDB(FitnessApplication.getInstance());
            BaseUrl = tinyDB.getString(Constants.CelluliteUrl);
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(Level.BODY);
            retrofit = new Builder().baseUrl(BaseUrl)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build())
                    .build();
            return retrofit;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Retrofit getCelluliteClient1() {
        try {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(100, TimeUnit.SECONDS).build();
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            tinyDB = new TinyDB(FitnessApplication.getInstance());
            BASEURL = tinyDB.getString(Constants.NewBaseUrl);
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(Level.BODY);

            retrofit = new Builder().baseUrl(BASEURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
            return retrofit;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /*public static Retrofit getCelluliteClient1() {
        try {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            tinyDB = new TinyDB(FitnessApplication.getInstance());
           // BaseUrl = "http://65.1.209.22:7070/";
            BASEURL=tinyDB.getString(Constants.NewBaseUrl);
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(Level.BODY);
            retrofit = new Builder().baseUrl(BASEURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build())
                    .build();
            return retrofit;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
*/

    public static Retrofit getAppstoreClient() {
        try {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            tinyDB = new TinyDB(FitnessApplication.getInstance());
            BaseUrl2 = tinyDB.getString(Constants.AppstoreUrl);
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(Level.BODY);
            retrofit = new Builder().baseUrl(BaseUrl2)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build())
                    .build();
            return retrofit;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
