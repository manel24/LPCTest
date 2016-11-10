package fr.lepotcommun.lpctest.networking;

import android.annotation.TargetApi;

import java.util.List;

import fr.lepotcommun.lpctest.model.Pot;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.POST;

import rx.Observable;


public class RestClient {

    private static final String BASE_URL = "https://recrutement.lepotcommuntest.fr/recruting/";
    private static RestClient INSTANCE  = new RestClient();
    //the Retrofit Interface providing REST API
    private PotsApi mPotsApi;


    private RestClient() {

        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Accept", "application/json");
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setRequestInterceptor(requestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        mPotsApi = restAdapter.create(PotsApi.class);

    }
    public static RestClient getSingleton() {
        return  INSTANCE;
    }

    public PotsApi getApi() {

        return mPotsApi;
    }

    public interface PotsApi {
        //retrieve all pots
        @GET("get-pots")
        public Observable<List<Pot>> pots();

        //add a new post
        @POST("create-pot")
        public Observable<Void> createPot();
    }

}
