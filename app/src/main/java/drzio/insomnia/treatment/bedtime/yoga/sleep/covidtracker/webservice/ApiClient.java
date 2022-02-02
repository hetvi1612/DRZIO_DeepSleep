package drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.webservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient.Builder;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;
    private static Retrofit retrofit_covid = null;
    private static Retrofit retrofit_mathdro = null;
    private static Retrofit retrofit_World = null;
    private static Retrofit retrofit_World2 = null;

    public static Retrofit getClient(Boolean status) {
        try {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            retrofit = new Retrofit.Builder().baseUrl("https://covidapp.in/quarantined/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(new Builder().addInterceptor(httpLoggingInterceptor).build())
                    .build();
            return retrofit;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retrofit;
    }

    public static Retrofit getCOVIDClient(Boolean status) {
        try {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            retrofit_covid = new Retrofit.Builder().baseUrl("https://api.covid19india.org/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(new Builder().addInterceptor(httpLoggingInterceptor).build())
                    .build();
            return retrofit_covid;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retrofit_covid;
    }

    public static Retrofit getMathdroClient(Boolean status) {
        try {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            retrofit_mathdro = new Retrofit.Builder().baseUrl("https://covid19.mathdro.id/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(new Builder().addInterceptor(httpLoggingInterceptor).build())
                    .build();
            return retrofit_mathdro;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retrofit_mathdro;
    }

    public static Retrofit getWorldClient() {
        try {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            retrofit_World = new Retrofit.Builder().baseUrl("https://corona.lmao.ninja/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(new Builder().addInterceptor(httpLoggingInterceptor).build())
                    .build();
            return retrofit_World;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retrofit_World;
    }


    public static Retrofit getWorldClient2() {
        try {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            retrofit_World2 = new Retrofit.Builder().baseUrl("https://covid-api.com/api/reports/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(new Builder().addInterceptor(httpLoggingInterceptor).build())
                    .build();
            return retrofit_World2;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retrofit_World2;
    }
}
