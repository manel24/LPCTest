package fr.lepotcommun.lpctest.networking;

import java.util.List;

import fr.lepotcommun.lpctest.model.Pot;
import rx.Observable;


public class RestClient {

    private static RestClient INSTANCE  = new RestClient();

    private static final String BASE_URL = "https://recrutement.lepotcommuntest.fr/recruting/";


    private  RestClient(){


    }

    public static RestClient getSingleton() {
        return  INSTANCE;
    }


    public Observable<List<Pot>> pots(){

        //TODO implement request
        return null;
    }

    public Observable<Void> createPot(){

       //TODO implement request
        return  null;
    }
}
